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
import java.text.SimpleDateFormat;
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
import model.Purchase;
import model.Bill;
import model.ExpenseCategories;
import model.ExpenseType;
import model.Mode;
import model.RepitionInterval;
import model.Status;
import utils.MyDate;
import utils.Constants;
import view.ContentUpdator;
import view.DispayExpenseComboActionListener;
import view.ExpenseObserverImpl;
import view.UserActionsApi;
import view.UserActionsImpl;
import view.ExpenseContentApi;

import java.util.Arrays;
import javax.swing.JComboBox;

public class UserInterface extends JFrame {

	private JPanel contentPaneMain;
	public ExpenseListTableModel tableModel;
	public JTable table;
	public ExpenseList myList;
	private JTextField txtExpenseList;
	ExpenseContentApi contentUpdator;
	UserActionsApi userActions;
	JComboBox<ExpenseType> dispayExpenseCombo;

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
					UserInterface frame = new UserInterface();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	public UserInterface() {
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

	
		// Insert Sample Data
		Date purchase_date=MyDate.getRandomDateAndTime();
		Purchase p1 = new Purchase(2.30, "Sandwich", purchase_date,
				Status.PAID, purchase_date, "Starbucks", "Campus", Mode.CASH, ExpenseCategories.DINING);
		
		Purchase p2 = new Purchase(3.30, "Wrap", purchase_date,
				Status.PAID, purchase_date, "Starbucks", "Downtown", Mode.CASH, ExpenseCategories.DINING);
		
		Date bill_date=MyDate.getRandomDateAndTime();
		Bill b1 = new Bill(65.09, "Electricity", bill_date,
				Status.UNPAID, MyDate.addDays(bill_date, 7), "Hydro Quebec", RepitionInterval.MONTHLY, ExpenseCategories.UTILITIES);
		Bill b2 = new Bill(10.50, "Electricity", bill_date,
				Status.UNPAID, MyDate.addDays(bill_date, 7), "Hydro Quebec", RepitionInterval.MONTHLY, ExpenseCategories.UTILITIES);

		// Jtable Definition
		myList = new ExpenseList();
		myList.add(p1);
		myList.add(p2);
		myList.add(b1);
		myList.add(b2);
		tableModel = new ExpenseListTableModel(myList, contentUpdator);
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
				   AddExpensePanel frame1 = new AddExpensePanel(myList, tableModel, userActions);
				   frame1.setVisible(true);
			   }
			});
		
		JButton btnRemoveExpense = new JButton("Remove Expense");
		btnRemoveExpense.setBounds(30, 64, 151, 23);
		contentPaneMain.add(btnRemoveExpense);

		btnRemoveExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					myList.remove(table.getSelectedRow());
					tableModel.fireTableDataChanged();
					//ExpenseContainerImpl.getInstance().removeExpense(expense);
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
					    	 if(myList.getType(selection[i]).equals("Purchase") || myList.getType(selection[i]).equals("Composite_Purchase")) {
					    		 PurchaseTypeCounter++;
					    	 }
					    	 if(myList.getType(selection[i]).equals("Bill") || myList.getType(selection[i]).equals("Composite_Bill")) {
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
					myList.markPaidUnpaid(table.getSelectedRow());
					tableModel.fireTableDataChanged();
				}
			}
		});
		
	}
	
}

/*
class DispayExpenseComboActionListener implements ActionListener{
	private JComboBox<ExpenseType> dispayExpenseCombo;
	private ExpenseContentApi contentUpdator;
	ExpenseListTableModel tableModel;
	public DispayExpenseComboActionListener(JComboBox<ExpenseType> comboBox, ExpenseListTableModel tableModel) {
		this.dispayExpenseCombo = comboBox;
		this.contentUpdator = contentUpdator;
		this.tableModel=tableModel;
	}
    public void actionPerformed(ActionEvent event) {
    	if(dispayExpenseCombo.getSelectedItem() ==ExpenseType.PURCHASE ||
    			dispayExpenseCombo.getSelectedItem() ==ExpenseType.COMPOSITE_PURCHASE) {
    		System.out.println("#### Purchase selected, items = "+contentUpdator.getAllPurchases().size()+"####");
    		tableModel.refresh(ExpenseType.PURCHASE);
		}
    	else if(dispayExpenseCombo.getSelectedItem() ==ExpenseType.BILL ||
    			dispayExpenseCombo.getSelectedItem() ==ExpenseType.COMPOSITE_BILL) {
    		System.out.println("#### Bill selected, items="+contentUpdator.getAllBills().size()+" ####");
    		tableModel.refresh(ExpenseType.BILL);
		} 
		else {
			System.out.println("#### Invalid Item selected ####");
		}		
    }
}
*/