package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Bill;
import model.CompositeBill;
import model.CompositePurchase;
import model.Expense;
import model.ExpenseCategories;
import model.ExpenseKey;
import model.ExpenseType;
import model.Mode;
import model.Purchase;
import model.RepitionInterval;
import utils.MyDate;
import utils.Util;

public class MySqlDataBase {
	private final String user;
	private final String password;
	private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
	
    public MySqlDataBase(String user, String password) {
		this.user = user;
		this.password = password;
	}
    
	public void connect() {
        try {
            // This will load the MySQL driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/pbm?user="+user+"&password="+password);
        } catch (Exception e) {
            throw new RuntimeException("MySqlDataBase#connect error!"+e);
        }        
	}
	
    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
        	throw new RuntimeException("MySqlDataBase#close error!"+e);
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
          System.out.println("#### Value: " +
        		  resultSet.getInt(1)+", "+
        		  resultSet.getString(2)+", "+
        		  resultSet.getDouble(3)+", "+
        		  resultSet.getString(4)+", "+
        		  resultSet.getDate(5)+", "+
        		  resultSet.getInt(6)+", "+
        		  resultSet.getString(7)
          );
        }
      }

    public void readPurchases() {
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from Purchase where p_parent_id=0");
	        writeResultSet(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#readPurchases error!"+e);
		}  finally {
            close();
        }
    }

    public void readCompPurchases() {
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from CompositePurchase where cp_parent_id=0");
	        writeResultSet(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#readCompPurchases error!"+e);
		}  finally {
            close();
        }
    }

    public void readBills() {
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from Bill where b_parent_id=0");
	        writeResultSet(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#readBills error!"+e);
		}  finally {
            close();
        }
    }

    public void readCompBills() {
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from CompositeBill where cb_parent_id=0");
	        writeResultSet(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#readCompBills error!"+e);
		}  finally {
            close();
        }
    }

    private void writeResultSet(ResultSet resultSet, Map<ExpenseKey , Expense> expMap) throws SQLException {
		while (resultSet.next()) {
			System.out.println("#### writeResultSet Value: " + resultSet.getInt(1) + ", " + resultSet.getString(2) + ", "
					+ resultSet.getDouble(3) + ", " + resultSet.getString(4) + ", " + resultSet.getDate(5) + ", "
					+ resultSet.getInt(6) + ", " + resultSet.getString(7));
			Expense e = null;
			if (ExpenseType.PURCHASE.toString().equalsIgnoreCase(resultSet.getString(2))) {
				// create Purchase
				System.out.println("Creating Purchase");
				e = new Purchase(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
						Util.getStatusEnum(resultSet.getString(7)), resultSet.getString(9), resultSet.getString(11),
						Util.getModeEnum(resultSet.getString(12)), ExpenseCategories.DAFAULT);
			} else if (ExpenseType.BILL.toString().equalsIgnoreCase(resultSet.getString(2))) {
				// create Bill
				System.out.println("Creating Bill");
				e = new Bill(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
						Util.getStatusEnum(resultSet.getString(7)), resultSet.getDate(11), resultSet.getString(9),
						Util.getRepitionIntervalEnum(resultSet.getString(12)), ExpenseCategories.DAFAULT);
			} else if (ExpenseType.COMPOSITE_PURCHASE.toString().equalsIgnoreCase(resultSet.getString(2))) {
				// create composite Purchase
				System.out.println("Creating composite Purchase");
				e = new CompositePurchase(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
						resultSet.getString(13), ExpenseCategories.DAFAULT, Util.getStatusEnum(resultSet.getString(7)),
						Mode.CASH, "");
		        populateCompPurchase(1, resultSet.getInt(1), e);
			} else if (ExpenseType.COMPOSITE_BILL.toString().equalsIgnoreCase(resultSet.getString(2))) {
				// create composite Bill
				System.out.println("Creating composite Bill");
				e = new CompositeBill(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
						resultSet.getString(13), "dummy vendor", Util.getStatusEnum(resultSet.getString(7)),
						ExpenseCategories.DAFAULT, RepitionInterval.MONTHLY, resultSet.getDate(11));
				populateCompPurchase(2, resultSet.getInt(1), e);
			} else {
				throw new RuntimeException("Can not create Expense Type");
			}
			
			expMap.put(e.getKey(), e);
		}
    }

    private void addResultSet(ResultSet rSet, Expense exp) throws SQLException {
		while (rSet.next()) {
			System.out.println("#### addResultSet Value: " + rSet.getInt(1) + ", " + rSet.getString(2) + ", "
					+ rSet.getInt(3));
			
			Expense in_exp = null;
			if (ExpenseType.PURCHASE.toString().equalsIgnoreCase(rSet.getString(2))) {
				// create Purchase
				System.out.println("Creating Purchase");
		    	try {
		    		Statement statement = connect.createStatement();
		    		ResultSet resultSet = statement.executeQuery("select * from Purchase where p_id="+rSet.getInt(3));
			        while (resultSet.next()) {
						in_exp = new Purchase(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
								Util.getStatusEnum(resultSet.getString(7)), resultSet.getString(9), resultSet.getString(11),
								Util.getModeEnum(resultSet.getString(12)), ExpenseCategories.DAFAULT);
			        }
				} catch (SQLException e) {
					throw new RuntimeException("MySqlDataBase#loadPurchases error!"+e);
				}
			} else if (ExpenseType.BILL.toString().equalsIgnoreCase(rSet.getString(2))) {
				// create Bill
				System.out.println("Creating Bill");
		    	try {
		    		Statement statement = connect.createStatement();
			        ResultSet resultSet = statement.executeQuery("select * from Bill where b_id="+rSet.getInt(3));
			        while (resultSet.next()) {
						in_exp = new Bill(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
								Util.getStatusEnum(resultSet.getString(7)), resultSet.getDate(11), resultSet.getString(9),
								Util.getRepitionIntervalEnum(resultSet.getString(12)), ExpenseCategories.DAFAULT);
			        }
				} catch (SQLException e) {
					throw new RuntimeException("MySqlDataBase#loadPurchases error!"+e);
				}				
			} else if (ExpenseType.COMPOSITE_PURCHASE.toString().equalsIgnoreCase(rSet.getString(2))) {
				// create composite Purchase
				System.out.println("Creating composite Purchase");
		    	try {
		    		Statement statement = connect.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from CompositePurchase where cp_id="+rSet.getInt(3));
			        while (resultSet.next()) {
						in_exp = new CompositePurchase(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
								resultSet.getString(13), ExpenseCategories.DAFAULT, Util.getStatusEnum(resultSet.getString(7)),
								Mode.CASH, "");
				        populateCompPurchase(1, resultSet.getInt(1), in_exp);
			        }
				} catch (SQLException e) {
					throw new RuntimeException("MySqlDataBase#loadPurchases error!"+e);
				}
			} else if (ExpenseType.COMPOSITE_BILL.toString().equalsIgnoreCase(rSet.getString(2))) {
				// create composite Bill
				System.out.println("Creating composite Bill");
		    	try {
		    		Statement statement = connect.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from CompositeBill where cb_id="+rSet.getInt(3));
			        while (resultSet.next()) {
						in_exp = new CompositeBill(resultSet.getDouble(3), resultSet.getString(4), resultSet.getDate(5),
								resultSet.getString(13), "dummy vendor", Util.getStatusEnum(resultSet.getString(7)),
								ExpenseCategories.DAFAULT, RepitionInterval.MONTHLY, resultSet.getDate(11));
				        populateCompPurchase(2, resultSet.getInt(1), in_exp);
			        }
				} catch (SQLException e) {
					throw new RuntimeException("MySqlDataBase#loadPurchases error!"+e);
				}
			} else {
				throw new RuntimeException("Can not create Expense Type");
			}
			
			exp.add(in_exp);
		}    	
    }
    
    private void populateCompPurchase(int type, int id, Expense exp) {
    	String query;
    	if(type == 1) {
    		query = "select * from CP_items where cpitems_cp_id="+id;
    	} else {
    		query = "select * from CB_items where cbitems_cb_id="+id;
    	}
    	
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery(query);
	        addResultSet(resultSet, exp);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#loadPurchases error!"+e);
		}
    }
    
    public void loadPurchases(Map<ExpenseKey , Expense> expMap) {
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from Purchase where p_parent_id=0");
	        writeResultSet(resultSet, expMap);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#loadPurchases error!"+e);
		}  finally {
            close();
        }
    }

    public void loadBills(Map<ExpenseKey , Expense> expMap) {    	
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from Bill where b_parent_id=0");
	        writeResultSet(resultSet, expMap);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#readBills error!"+e);
		}  finally {
            close();
        }
    }

    public void loadCompPurchases(Map<ExpenseKey , Expense> expMap) {
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from CompositePurchase where cp_parent_id=0");
	        writeResultSet(resultSet, expMap);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#loadCompPurchases error!"+e);
		}  finally {
            close();
        }
    }

    public void loadCompBills(Map<ExpenseKey , Expense> expMap) {    
    	connect();
    	try {
			statement = connect.createStatement();
	        resultSet = statement.executeQuery("select * from CompositeBill where cb_parent_id=0");
	        writeResultSet(resultSet, expMap);
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#readCompBills error!"+e);
		}  finally {
            close();
        }
    }

	public void pushData(List<Map<ExpenseKey, Expense>> expenseData) {
    	connect();

    	clearDB();
    	addExpenses(expenseData.get(ExpenseType.PURCHASE.ordinal()));
    	addCompExpenses(expenseData.get(ExpenseType.COMPOSITE_PURCHASE.ordinal()));
    	addExpenses(expenseData.get(ExpenseType.BILL.ordinal()));
    	addCompExpenses(expenseData.get(ExpenseType.COMPOSITE_BILL.ordinal()));

    	close();
	}
	
	private void addExpense(Expense e, int parent_id) {
		String query = null;
		int type = e.getType().ordinal();
		if(type == ExpenseType.PURCHASE.ordinal()) {
			query = "insert into  Purchase values (default, ?, ?, ?, ? , "+parent_id+", ?, ?, ?, ?, ?, ?)";
		} else if (type == ExpenseType.BILL.ordinal()) {
			query = "insert into  Bill values (default, ?, ?, ?, ? , "+parent_id+", ?, ?, ?, ?, ?, ?)";
		}
		
    	try {
    			preparedStatement = connect.prepareStatement(query);
    			//(p_id,p_type,p_amount,p_name,p_date,p_parent_id,p_status,p_paymentDate,p_vendor,p_category,p_location,p_mode)
    			//(b_id,b_type,b_amount,b_name,b_date,b_parent_id,b_status,b_paymentDate,b_vendor,b_category,b_dueDate,b_interval)
                preparedStatement.setString(1, e.getType().toString());
                preparedStatement.setDouble(2, e.getAmount());
                preparedStatement.setString(3, e.getName());
                preparedStatement.setDate(4, new java.sql.Date(e.getDate().getTime()));
                preparedStatement.setString(5, e.getStatus().toString());
                preparedStatement.setDate(6, new java.sql.Date(e.getPaymentDate().getTime()));
                preparedStatement.setString(7, e.getVendor());
                preparedStatement.setString(8, e.getCategory().toString());
        		if(type == ExpenseType.PURCHASE.ordinal()) {
                    preparedStatement.setString(9, e.getLocation());
                    preparedStatement.setString(10, e.getMode().toString());
        		} else if (type == ExpenseType.BILL.ordinal()) {
                    preparedStatement.setDate(9, new java.sql.Date(e.getDueDate().getTime()));
                    preparedStatement.setString(10, e.getInterval().toString());
        		}

                preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new RuntimeException("MySqlDataBase#addExpenses error!"+ex);
		}				
	}
	
	private void addExpenses(Map<ExpenseKey , Expense> expMap) {
		ArrayList<Expense> expenses = new ArrayList<Expense>(expMap.values());
		Iterator<Expense> expIt = expenses.iterator();
		while(expIt.hasNext()) {
			Expense e = expIt.next(); 
			e.display();
			addExpense(e, 0);
		}		
	}
	
	private void addCompExpense(Expense e, int parent_id) {
		String query = null;
		int type = e.getType().ordinal();

		if(type == ExpenseType.COMPOSITE_PURCHASE.ordinal()) {
			query = "insert into  CompositePurchase values (default, ?, ?, ?, ? , "+parent_id+", ?, ?, ?, ?, ?, ?, ?)";
		} else if (type == ExpenseType.COMPOSITE_BILL.ordinal()) {
			query = "insert into  CompositeBill values (default, ?, ?, ?, ? , "+parent_id+", ?, ?, ?, ?, ?, ?, ?)";
		}

    	try {
    			preparedStatement = connect.prepareStatement(query);
    			//(cp_id,cp_type,cp_amount,cp_name,cp_date,cp_parent_id,cp_status,cp_paymentDate,cp_vendor,cp_category,cp_location,cp_mode,cp_description)
    			//(cb_id,cb_type,cb_amount,cb_name,cb_date,cb_parent_id,cb_status,cb_paymentDate,cb_vendor,cb_category,cb_dueDate,cb_interval,cb_description)
                preparedStatement.setString(1, e.getType().toString());
                preparedStatement.setDouble(2, e.getAmount());
                preparedStatement.setString(3, e.getName());
                preparedStatement.setDate(4, new java.sql.Date(e.getDate().getTime()));
                preparedStatement.setString(5, e.getStatus().toString());
                preparedStatement.setDate(6, new java.sql.Date(e.getPaymentDate().getTime()));
                preparedStatement.setString(7, e.getVendor());
                preparedStatement.setString(8, e.getCategory().toString());
        		if(type == ExpenseType.COMPOSITE_PURCHASE.ordinal()) {
                    preparedStatement.setString(9, e.getLocation());
                    preparedStatement.setString(10, e.getMode().toString());
        		} else if (type == ExpenseType.COMPOSITE_BILL.ordinal()) {
                    preparedStatement.setDate(9, new java.sql.Date(e.getDueDate().getTime()));
                    preparedStatement.setString(10, e.getInterval().toString());
        		}
        		preparedStatement.setString(11, e.getDescription());

                preparedStatement.executeUpdate();  
                addSubExpense(getId(e), e);
		} catch (SQLException ex) {
			throw new RuntimeException("MySqlDataBase#addCompExpenses error!"+ex);
		}			
	}
	
	private void addCompExpenses(Map<ExpenseKey , Expense> expMap) {
		ArrayList<Expense> expenses = new ArrayList<Expense>(expMap.values());
		Iterator<Expense> expIt = expenses.iterator();
		while(expIt.hasNext()) {
			Expense e = expIt.next(); 
			e.display();
			addCompExpense(e, 0);
		}		
	}
	
	private int getId(Expense exp) {
		int id=0;
		String inner_query = null;

		
		if(exp.getType().ordinal() == ExpenseType.PURCHASE.ordinal()) {
			inner_query = "select p_id from Purchase where p_amount='"+exp.getAmount()+"' and p_name='"+exp.getName()+"'";
		} else if (exp.getType().ordinal() == ExpenseType.BILL.ordinal()) {
			inner_query = "select b_id from Bill where b_amount='"+exp.getAmount()+"' and b_name='"+exp.getName()+"'";
		} else if(exp.getType().ordinal() == ExpenseType.COMPOSITE_PURCHASE.ordinal()) {
			inner_query = "select cp_id from CompositePurchase where cp_amount='"+exp.getAmount()+"' and cp_name='"+exp.getName()+"'";
		} else if (exp.getType().ordinal() == ExpenseType.COMPOSITE_BILL.ordinal()) {
			inner_query = "select cb_id from CompositeBill where cb_amount='"+exp.getAmount()+"' and cb_name='"+exp.getName()+"'";
		} else {
			//error
		}

		try {
			Statement inner_statement = connect.createStatement();
			ResultSet inner_resultSet = inner_statement.executeQuery(inner_query);
	        while (inner_resultSet.next()) {
				id = inner_resultSet.getInt(1);
		        System.out.println("getId ID="+id);		        
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
	
	private void addSubExpense(int id, Expense ce) {
		ArrayList<Expense> expenses = ce.getSubItems();
		Iterator<Expense> expIt = expenses.iterator();
		while(expIt.hasNext()) {
			Expense e = expIt.next(); 
			if(e.getType().ordinal()<2) {
				addExpense(e, id);				
			} else {
				addCompExpense(e, id);
			}
			int e_id = getId(e);
			//add id, type, e_id
			addCe2E(id, e.getType(), e_id);
		}
		
	}
	
	private void addCe2E(int id, ExpenseType type, int e_id) {
		String query=null;
		if(type.ordinal() == 0 || type.ordinal() == 2) {
			query = "insert into  CP_items values (?, ?, ?)";
		} else {
			query = "insert into  CB_items values (?, ?, ?)";
		}
		
    	try {
			preparedStatement = connect.prepareStatement(query);
    		preparedStatement.setInt(1, id);
    		preparedStatement.setString(2, type.toString());
    		preparedStatement.setInt(3, e_id);

            preparedStatement.executeUpdate();  
    	} catch (SQLException e) {
			e.printStackTrace();
		}


	}
	
	private void clearDB() {
    	try {
    		preparedStatement = connect.prepareStatement("delete from CP_items;");
            preparedStatement.executeUpdate();
            preparedStatement = connect.prepareStatement("delete from CompositePurchase;");
            preparedStatement.executeUpdate();
            preparedStatement = connect.prepareStatement("delete from Purchase;");
            preparedStatement.executeUpdate();
    		preparedStatement = connect.prepareStatement("delete from CB_items;");
            preparedStatement.executeUpdate();
            preparedStatement = connect.prepareStatement("delete from CompositeBill;");
            preparedStatement.executeUpdate();
            preparedStatement = connect.prepareStatement("delete from Bill;");
            preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("MySqlDataBase#clearDB error!"+e);
		}
	}
}
