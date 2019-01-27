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

	private JPanel contentPane;
	private ExpenseListTableModel tableModel;
	private JTable table;
	private ExpenseList myList;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtExpenseList;

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
		setBounds(100, 100, 1103, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		
		// Insert Sample Data
		Purchase p1 = new Purchase("Purchase", new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1)))), "Sandwich", 2.30, "Paid", "Starbucks",
				"Cash", new Date((2019 - 1900), 0, 1));
		Purchase p2 = new Purchase("Purchase", new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1)))), "Coffee", 1.15, "Paid", "Second Cup",
				"Debit", new Date((2019 - 1900), 0, 1));

		
		// Jtable Definition
		myList = new ExpenseList();
		myList.add(p1);
		myList.add(p2);
		tableModel = new ExpenseListTableModel(myList);
		table = new JTable(tableModel);
		table.setForeground(Color.BLACK);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setName("");
		
		table.setFillsViewportHeight(true);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		scrollPane.setBounds(319, 65, 738, 350);
		contentPane.add(scrollPane);

		JTextField txtExpenseList = new JTextField();
		txtExpenseList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtExpenseList.setBorder(null);
		txtExpenseList.setBackground(UIManager.getColor("Button.background"));
		txtExpenseList.setText("Expense List");
		txtExpenseList.setHorizontalAlignment(SwingConstants.CENTER);
		txtExpenseList.setBounds(319, 30, 738, 21);
		contentPane.add(txtExpenseList);
		txtExpenseList.setColumns(10);
		
		// Label Creation
		JLabel lblNewLabel = new JLabel("Type");
		lblNewLabel.setBounds(30, 115, 53, 23);
		contentPane.add(lblNewLabel);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(30, 150, 53, 23);
		contentPane.add(lblDate);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(30, 185, 53, 23);
		contentPane.add(lblName);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(30, 220, 53, 23);
		contentPane.add(lblAmount);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(30, 255, 53, 23);
		contentPane.add(lblStatus);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(30, 290, 53, 23);
		contentPane.add(lblLocation);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setBounds(30, 325, 53, 23);
		contentPane.add(lblMethod);

		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(30, 360, 53, 23);
		contentPane.add(lblDueDate);

		JLabel lblInterval = new JLabel("Interval");
		lblInterval.setBounds(30, 395, 53, 23);
		contentPane.add(lblInterval);

		// Type
		JComboBox comboBox3 = new JComboBox(new String[] { "Purchase", "Bill" });
		comboBox3.setSelectedIndex(0);
		comboBox3.setMaximumRowCount(2);
		comboBox3.setBounds(90, 115, 200, 20);
		contentPane.add(comboBox3);

		// Date
		JTextField textField_1 = new JTextField(dateFormat.format(new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1))))));
		textField_1.setColumns(10);
		textField_1.setBounds(90, 150, 200, 20);
		contentPane.add(textField_1);

		// Name
		String[] randomName = { "Tea", "Coffee", "Sandwich", "Cookie", "Toast", "Chocolate",
				"Candy"};
		JTextField textField_2 = new JTextField(randomName[new Random().nextInt(randomName.length)]);
		textField_2.setColumns(10);
		textField_2.setBounds(90, 185, 200, 20);
		contentPane.add(textField_2);

		// Amount
		JTextField textField_3 = new JTextField("" + (Math.round(Math.random() * 1000.0) / 100.0));
		textField_3.setColumns(10);
		textField_3.setBounds(90, 220, 200, 20);
		contentPane.add(textField_3);

		// Status
		JComboBox comboBox2 = new JComboBox(new String[] { "Unpaid", "Paid" });
		comboBox2.setSelectedIndex(1);
		comboBox2.setMaximumRowCount(2);
		comboBox2.setBounds(90, 255, 200, 20);
		contentPane.add(comboBox2);

		// Location
		String[] randomLocation = { "Tim Hortons", "Starbucks", "Second Cup", "Van Houtte"};
		JTextField textField_5 = new JTextField(randomLocation[new Random().nextInt(randomLocation.length)]);
		textField_5.setColumns(10);
		textField_5.setBounds(90, 290, 200, 20);
		contentPane.add(textField_5);

		// Method
		JComboBox comboBox = new JComboBox(new String[] { "Cash", "Debit", "Credit Card" });
		comboBox.setSelectedIndex((int)(Math.random()*3));
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(90, 325, 200, 20);
		contentPane.add(comboBox);

		// Due Date
		JTextField textField_7 = new JTextField(dateFormat.format(new Date()));
		textField_7.setColumns(10);
		textField_7.setBounds(90, 360, 200, 20);
		contentPane.add(textField_7);

		// Interval
		JComboBox comboBox4 = new JComboBox(new String[] { "Weekly", "Monthly", "Yearly" });
		comboBox4.setSelectedIndex(1);
		comboBox4.setMaximumRowCount(3);
		comboBox4.setBounds(90, 395, 200, 20);
		contentPane.add(comboBox4);

		JButton btnAddExpense = new JButton("Add Expense");
		btnAddExpense.setBounds(30, 30, 108, 23);
		contentPane.add(btnAddExpense);

		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				int textField_1_year = Integer.valueOf((textField_1.getText().substring(0, 4)));
				int textField_1_month = Integer.valueOf((textField_1.getText().substring(5, 7)));
				int textField_1_day = Integer.valueOf((textField_1.getText().substring(8, 10)));

				int textField_7_year = Integer.valueOf((textField_7.getText().substring(0, 4)));
				int textField_7_month = Integer.valueOf((textField_7.getText().substring(5, 7)));
				int textField_7_day = Integer.valueOf((textField_7.getText().substring(8, 10)));

				Purchase pAdd = new Purchase(comboBox3.getSelectedItem().toString(),
						new Date((textField_1_year - 1900), textField_1_month - 1, textField_1_day),
						textField_2.getText(), new Double(textField_3.getText()),
						comboBox2.getSelectedItem().toString(), textField_5.getText(),
						comboBox.getSelectedItem().toString(),
						new Date((textField_7_year - 1900), textField_7_month - 1, textField_7_day));

				myList.add(pAdd);
				tableModel.fireTableDataChanged();
				comboBox.setSelectedIndex((int)(Math.random()*3));
				textField_1.setText(dateFormat.format(new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1))))));
				textField_2.setText(randomName[new Random().nextInt(randomName.length)]);
				textField_3.setText("" + (Math.round(Math.random() * 1000.0) / 100.0));
				textField_5.setText(randomLocation[new Random().nextInt(randomLocation.length)]);
				
			}
		});

		JButton btnRemoveExpense = new JButton("Remove Expense");
		btnRemoveExpense.setBounds(154, 30, 139, 23);
		contentPane.add(btnRemoveExpense);

		btnRemoveExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {
					myList.remove(table.getSelectedRow());
					tableModel.fireTableDataChanged();
				}
			}
		});

		JButton btnMarkPaidunpaid = new JButton("Mark Expense Paid/Unpaid");
		btnMarkPaidunpaid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnMarkPaidunpaid.setBounds(30, 65, 263, 23);
		contentPane.add(btnMarkPaidunpaid);


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
