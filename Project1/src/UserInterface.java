/**
 * Description: UserInterface contains the Java Swing elements as well as the driver code. 
 * UserInterface was developed using WindowBuilder Eclipse Plugin.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */

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
	 * Create the frame.
	 */
	public UserInterface() {
		setTitle("Personal Budget Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 554);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMain);
		contentPaneMain.setLayout(null);

		
		// Insert Sample Data
		Purchase p1 = new Purchase("Purchase", new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1)))), "Sandwich", 2.30, "Paid", "Starbucks",
				"Cash", new Date((2019 - 1900), 0, 1));
		Purchase p2 = new Purchase("Purchase", new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1)))), "Coffee", 1.15, "Paid", "Second Cup",
				"Debit", new Date((2019 - 1900), 0, 1));
			
		
		Bill b1 = new Bill("Bill", new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1)))), "Hydro Quebec", 60, "Unpaid", "",
				"Debit", new Date((2019 - 1900), 0, 1), "Monthly");

		// Jtable Definition
		myList = new ExpenseList();
		myList.add(p1);
		myList.add(p2);
		myList.add(b1);
		tableModel = new ExpenseListTableModel(myList);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 130, 738, 350);
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
		btnAddExpense.setBounds(30, 30, 108, 23);
		contentPaneMain.add(btnAddExpense);
		
		btnAddExpense.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {   
				   AddExpensePanel frame1 = new AddExpensePanel(myList, tableModel);
				   frame1.setVisible(true);
			   }
			});
		
		JButton btnRemoveExpense = new JButton("Remove Expense");
		btnRemoveExpense.setBounds(155, 30, 139, 23);
		contentPaneMain.add(btnRemoveExpense);

		btnRemoveExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					myList.remove(table.getSelectedRow());
					tableModel.fireTableDataChanged();
				}
			}
		});

		JButton btnMarkPaidunpaid = new JButton("Mark Expense Paid/Unpaid");
		btnMarkPaidunpaid.setBounds(30, 64, 263, 23);
		contentPaneMain.add(btnMarkPaidunpaid);


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
