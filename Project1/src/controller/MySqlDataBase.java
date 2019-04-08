package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}
