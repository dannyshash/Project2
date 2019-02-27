/**
 * Description: UserInterface contains the Java Swing elements as well as the driver code. 
 * UserInterface was developed using WindowBuilder Eclipse Plugin.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package misc;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableRowSorter;

import controller.ExpenseContainerImpl;
import model.ExpenseCategories;
import model.Mode;
import model.RepitionInterval;
import model.Status;
import view.ExpenseObserverImpl;

import java.util.Arrays;

public class UserInterface extends JFrame {

	private JPanel contentPaneMain;
	public ExpenseListTableModel tableModel;
	public JTable table;
	public ExpenseList myList;
	private JTextField txtExpenseList;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/*
	 * public ArrayList<Purchase> purchaseList = new ArrayList<Purchase>(); public
	 * DefaultTableModel model = new DefaultTableModel(); public JTable table = new
	 * JTable(model); String[] header = { "Type", "Date", "Name", "Amount",
	 * "Status", "Location", "Method", "Due Date", "Interval" };
	 */

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
		ExpenseContainerImpl.getInstance().init();
		ExpenseObserverImpl.getInstance().init();
		System.out.println("Start " + new Date());
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
		
		// Insert Sample Data
		PurchaseAdaptor p1 = new PurchaseAdaptor(new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1)))), "Sandwich", 
				new Double(2.30), Status.PAID, ExpenseCategories.DINING, "Starbucks", "Campus", Mode.CASH, new Date((2019 - 1900), 0, 1));
			
		
		BillAdaptor b1 = new BillAdaptor(new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1)))), "Electricity", 
				new Double(65.09), Status.UNPAID, ExpenseCategories.UTILITIES, "Hydro Quebec", "",
				Mode.DEBIT, new Date((2019 - 1900), 0, 1), RepitionInterval.MONTHLY);

		// Jtable Definition
		myList = new ExpenseList();
		myList.add(p1);
		//myList.add(p2);
		myList.add(b1);
		tableModel = new ExpenseListTableModel(myList);
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
				   AddExpensePanel frame1 = new AddExpensePanel(myList, tableModel);
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
