/**
 * Description: UserInterface contains the Java Swing elements as well as the driver code. 
 * UserInterface was developed using WindowBuilder Eclipse Plugin.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package view;


import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import controller.ExpenseContainerImpl;
import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;
import utils.MyDate;
import utils.Util;

import javax.swing.JComboBox;

public class UserInterface extends JFrame {

	private JPanel contentPaneMain;
	public ExpenseListTableModel tableModel;
	public JTable table;
	ExpenseContentApi contentUpdator;
	UserActionsApi userActions;
	JComboBox<ExpenseType> dispayExpenseCombo;
	private static UserInterface frame;

	public static UserInterface getInstance() {
		if(frame == null) {
			frame = new UserInterface();
		}
		return frame;
	}
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	protected UserInterface() {
		setTitle("Personal Budget Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 879, 554);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMain);
		contentPaneMain.setLayout(null);
	
		/**
		 * The following 2 lines are for the view manager to use
		 */	
		contentUpdator = new ContentUpdator(ExpenseObserverImpl.getInstance());
		userActions = new UserActionsImpl(ExpenseContainerImpl.getInstance());
	
		//myList = new ExpenseList();
		tableModel = new ExpenseListTableModel(contentUpdator);
		table = new JTable(tableModel)
		{
		    public Component prepareRenderer(
		        TableCellRenderer renderer, int row, int column)
		    {
		        Component c = super.prepareRenderer(renderer, row, column);

		        //  add custom rendering here
  				if(!isRowSelected(row)) {
					c.setBackground(getBackground());

		        }

  				return c;
		    }
		};
		TableRowSorter sorter = new TableRowSorter(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 130, 823, 350);
		contentPaneMain.add(scrollPane);
		
		JTextField txtExpenseList = new JTextField();
		txtExpenseList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtExpenseList.setBorder(null);
		txtExpenseList.setBackground(UIManager.getColor("Button.background"));
		txtExpenseList.setText("Expense List");
		txtExpenseList.setHorizontalAlignment(SwingConstants.CENTER);
		txtExpenseList.setBounds(30, 98, 738, 21);
		contentPaneMain.add(txtExpenseList);
		txtExpenseList.setColumns(10);
	

		JButton btnAddExpense = new JButton("Add Expense");
		btnAddExpense.setBounds(30, 30, 151, 23);
		contentPaneMain.add(btnAddExpense);
		
		btnAddExpense.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {   
				   AddExpExpTypeComboListener expTypeListener = new AddExpExpTypeComboListener();
				   AddExpPanelAddBtnListener listener = new AddExpPanelAddBtnListener(userActions);
				   AddExpensePanel frame1 = new AddExpensePanel(userActions, listener, expTypeListener);
				   frame1.setVisible(true);
			   }
			});
		
		JButton btnRemoveExpense = new JButton("Remove Expense");
		btnRemoveExpense.setBounds(30, 64, 151, 23);
		contentPaneMain.add(btnRemoveExpense);

		btnRemoveExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Expense expense = getSelectedExpense(table);
					if(expense != null) {
						userActions.removeExpense(expense);
					} else {
						expense = getTableModel().getExpense(table.getSelectedRow());
						Expense root = contentUpdator.findExpense(expense.getRoot().getKey());
						Expense parent = root.find(expense.getParent());
						userActions.removeExpense(expense, parent, root);
					}

					tableModel.fireTableDataChanged();
				}
			}
		});

		table.addMouseListener(new MouseAdapter() { 
		    @Override
		    public void mouseClicked(MouseEvent e){
		    	if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){
		    		int row = table.getSelectedRow();
		    		Expense exp = getTableModel().getExpense(row);
		    		String expand = getTableModel().getValueAt(row, DisplayColumn.Expand.ordinal()).toString();
		    		System.out.println("#### Mouse clicked on row"+expand+",Exp="+exp); 

		    		if(exp.getType().ordinal()>1){
		    			if(expand.compareTo("+") == 0){
				    		System.out.println("####  Expanding row");
				    		getTableModel().expandComposite(exp, table.getSelectedRow());		    			
		    				
		    			} else if(expand.compareTo("-") == 0) {
			    			System.out.println("Collapse row");
			    			getTableModel().collapseComposite(exp, table.getSelectedRow());
		    			}
		    			else {
		    				throw new RuntimeException("mouseClicked event error");
		    			}
		    		}
		    		else {
		    			System.out.println("Simple Expense, do nothing");
		    			return;
		    		}
		        }
		    }
		});
		
		JButton btnMarkPaidunpaid = new JButton("Mark Expense Paid/Unpaid");
		btnMarkPaidunpaid.setBounds(209, 30, 196, 23);
		contentPaneMain.add(btnMarkPaidunpaid);
		
		JButton btnHideShow = new JButton("Hide/Show Paid Expenses");
		btnHideShow.setBounds(209, 64, 196, 23);
		contentPaneMain.add(btnHideShow);
		
		JButton btnCreateComposite = new JButton("Create Composite Expense");
		btnCreateComposite.setBounds(433, 30, 196, 23);
		contentPaneMain.add(btnCreateComposite);
		
		dispayExpenseCombo = new JComboBox<>();
		dispayExpenseCombo.setToolTipText("Choose Expense type to be displayed");
		dispayExpenseCombo.setBounds(650, 31, 109, 20);
		dispayExpenseCombo.setModel(new DefaultComboBoxModel<>(ExpenseType.values()));
		dispayExpenseCombo.setSelectedIndex(0);
		dispayExpenseCombo.setMaximumRowCount(2);
		contentPaneMain.add(dispayExpenseCombo);
		dispayExpenseCombo.addActionListener(new DispayExpenseComboActionListener(dispayExpenseCombo, tableModel));
		
		JButton btnSynchronizeDatabse = new JButton("Synchronize Databse");
		btnSynchronizeDatabse.setBounds(433, 64, 196, 23);
		contentPaneMain.add(btnSynchronizeDatabse);
		btnSynchronizeDatabse.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				ExpenseContainerImpl.getInstance().sync2Db();				
			}
		});	
		
		btnCreateComposite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {   
			   if (table.getSelectedRow() >= 0) {
				   ArrayList<Expense> items = new ArrayList<Expense>();
				   
				   int[] selection = table.getSelectedRows();
				   for (int i = 0; i < selection.length; i++) {
					   Expense exp = getSelectedExpense(table, selection[i]);
					   if(exp != null) {
						   items.add(exp);
					   }
				   }
				   
				   AddCompExpExpTypeComboListener expTypeListener = new AddCompExpExpTypeComboListener(items.get(0).getType());
				   AddCompExpPanelAddBtnListener listener = new AddCompExpPanelAddBtnListener(userActions, items);
				   AddCompositeExpensePanel frame1 = new AddCompositeExpensePanel(userActions, listener, expTypeListener);
				   frame1.setVisible(true);				   
				}
			}
		});			

		btnHideShow.addActionListener(new ActionListener() {			
			boolean HideShowSwitch = true;				
			public void actionPerformed(ActionEvent e) {
				if (HideShowSwitch) {
					sorter.setRowFilter(RowFilter.regexFilter("Unpaid"));
					table.setRowSorter(sorter);
				}
				else {
					sorter.setRowFilter(RowFilter.regexFilter("."));
					table.setRowSorter(sorter);
				}				
				HideShowSwitch = !HideShowSwitch;
			}
		});

		btnMarkPaidunpaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					Expense expense = getSelectedExpense(table);
					if(expense != null) { 
						userActions.changePaymentStatus(expense);
					} else {
						expense = getTableModel().getExpense(table.getSelectedRow());
						userActions.changePaymentStatus(expense, contentUpdator.findExpense(expense.getRoot().getKey()));
					}
					
					tableModel.fireTableDataChanged();
				}
			}
		});
		
	}
	
	private Expense getSelectedExpense(JTable table) {
		int row = table.getSelectedRow();
		
		return getSelectedExpense(table, row);		
	}
	
	private Expense getSelectedExpense(JTable table, int row) {
		ExpenseType type = Util.getExpenseTypeEnum((String)table.getValueAt(row, DisplayColumn.TYPE.ordinal()));
		double amount = new Double((String)table.getValueAt(row, DisplayColumn.AMOUNT.ordinal())).doubleValue();
		String name = (String)(table.getValueAt(row, DisplayColumn.NAME.ordinal()));
		Date date = MyDate.getJustDate((String)(table.getValueAt(row, DisplayColumn.DATE.ordinal())));
		ExpenseKey key = new ExpenseKey(type, amount, name, date);

		Expense exp = contentUpdator.findExpense(key);
		System.out.println("getSelectedExpense return: " + exp + ", at row: " + row);
		return exp;
	}
	
	public ExpenseListTableModel getTableModel() {
		return tableModel;
	}
}