/**
 * Description: UserInterface contains the Java Swing elements as well as the driver code. 
 * UserInterface was developed using WindowBuilder Eclipse Plugin.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package misc;


import java.awt.EventQueue;
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
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.table.TableRowSorter;

import controller.DataLoader;
import controller.ExpenseContainerApi;
import controller.ExpenseContainerImpl;
import controller.ExpenseSubject;
import controller.FileLoaderImpl;
import controller.InMemoryStore;
import controller.Store;
import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;
import utils.MyDate;
import utils.Constants;
import view.ContentUpdator;
import view.DispayExpenseComboActionListener;
import view.ExpenseObserverImpl;
import view.UserActionsApi;
import view.UserActionsImpl;
import view.ExpenseContentApi;

import javax.swing.JComboBox;

public class UserInterface extends JFrame {

	private JPanel contentPaneMain;
	public ExpenseListTableModel tableModel;
	public JTable table;
	ExpenseContentApi contentUpdator;
	UserActionsApi userActions;
	JComboBox<ExpenseType> dispayExpenseCombo;
	private static UserInterface frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/**
		 * Very important to do this very first as this creates the singleton objects
		 * start the managers, and does subscriptions... 
		 */
		prestartPhase();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = getInstance();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static UserInterface getInstance() {
		if(frame == null) {
			frame = new UserInterface();
		}
		return frame;
	}
	
	/**
	 * creates Singletons
	 */
	private static void prestartPhase() {
		DataLoader loader = new FileLoaderImpl(Constants.SAMPLE_DATA_FILENAME);
		Store dataStore= new InMemoryStore(loader);
		ExpenseContainerApi container = (ExpenseContainerApi)ExpenseContainerImpl.getInstance();
		ExpenseSubject subject = (ExpenseSubject)ExpenseContainerImpl.getInstance();
		container.init(dataStore);
		ExpenseObserverImpl.getInstance().init(subject);
		subject.start();
		System.out.println("Start PBM " + new Date());
	}


	/**
	 * Create the frame.
	 */
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
		table = new JTable(tableModel);
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
				   AddExpensePanel frame1 = new AddExpensePanel(tableModel, userActions);
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
					if(expense != null) userActions.removeExpense(expense);
					tableModel.fireTableDataChanged();
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

		
		btnCreateComposite.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {   
				   if (table.getSelectedRow() >= 0) {
					   
					   int[] selection = table.getSelectedRows();
					   for (int i = 0; i < selection.length; i++) {
						     selection[i] = table.convertRowIndexToModel(selection[i]);
						   }
					   
					   int PurchaseTypeCounter=0;
					   int BillTypeCounter=0;
					     for (int i = 0; i < selection.length; i++) {
					    	 ExpenseType type = (ExpenseType)(table.getValueAt(table.getSelectedRow(), 0));
					    	 if(type == ExpenseType.PURCHASE || type == ExpenseType.COMPOSITE_PURCHASE) {
					    		 PurchaseTypeCounter++;
					    	 }
					    	 if(type == ExpenseType.BILL || type == ExpenseType.COMPOSITE_BILL) {
					    		 BillTypeCounter++;
					    	 }
						   }
					     
					     if(PurchaseTypeCounter == selection.length) {
					    	 System.out.println("Success: All the selection are purchases or composite purchases");
					     }
					     else if(BillTypeCounter == selection.length){
					    	 System.out.println("Success: All the selection are bills or composite bills");
					     }
					     else {
					    	 System.out.println("The selections have mismatch in Expense Types");
					     }
					   
					   
					   tableModel.fireTableDataChanged();
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
					if(expense != null) userActions.changePaymentStatus(expense);
					tableModel.fireTableDataChanged();
				}
			}
		});
		
	}
	
	private Expense getSelectedExpense(JTable table) {
		ExpenseType type = (ExpenseType)(table.getValueAt(table.getSelectedRow(), 0));
		double amount = (Double)(table.getValueAt(table.getSelectedRow(), 3));
		String name = (String)(table.getValueAt(table.getSelectedRow(), 2));
		Date date = MyDate.getJustDate((String)(table.getValueAt(table.getSelectedRow(), 1)));
		ExpenseKey key = new ExpenseKey(type, amount, name, date);

		System.out.println("getSelectedExpense return: " + contentUpdator.findExpense(key));
		return contentUpdator.findExpense(key);
	}
	
	public ExpenseListTableModel getTableModel() {
		return tableModel;
	}
	
}