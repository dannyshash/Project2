package tony.experiments;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class AddExpensePanel extends UserInterface {

	private JPanel contentPane;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtChooseExpense;
	private JTextField txtEnterExpense;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddExpensePanel frame = new AddExpensePanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public AddExpensePanel(ExpenseList myList, ExpenseListTableModel tableModel) {
		
		this.myList = myList;
		this.tableModel = tableModel;
		
		setTitle("Add Expense Panel");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 330, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		// Label Creation
				JLabel lblNewLabel = new JLabel("Type");
				lblNewLabel.setBounds(30, 73, 53, 23);
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
				comboBox3.setBounds(93, 74, 200, 20);
				comboBox3.setSelectedIndex(0);
				comboBox3.setMaximumRowCount(2);
				contentPane.add(comboBox3);

				// Date
				JTextField textField_1 = new JTextField(dateFormat.format(new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1))))));
				textField_1.setBounds(90, 150, 200, 20);
				textField_1.setColumns(10);
				contentPane.add(textField_1);

				// Name
				String[] randomName = { "Tea", "Coffee", "Sandwich", "Cookie", "Toast", "Chocolate",
						"Candy"};
				JTextField textField_2 = new JTextField(randomName[new Random().nextInt(randomName.length)]);
				textField_2.setBounds(90, 185, 200, 20);
				textField_2.setColumns(10);
				contentPane.add(textField_2);

				// Amount
				JTextField textField_3 = new JTextField("" + (Math.round(Math.random() * 1000.0) / 100.0));
				textField_3.setBounds(90, 220, 200, 20);
				textField_3.setColumns(10);
				contentPane.add(textField_3);

				// Status
				JComboBox comboBox2 = new JComboBox(new String[] { "Unpaid", "Paid" });
				comboBox2.setBounds(90, 255, 200, 20);
				comboBox2.setSelectedIndex(1);
				comboBox2.setMaximumRowCount(2);
				contentPane.add(comboBox2);

				// Location
				String[] randomLocation = { "Tim Hortons", "Starbucks", "Second Cup", "Van Houtte"};
				JTextField textField_5 = new JTextField(randomLocation[new Random().nextInt(randomLocation.length)]);
				textField_5.setBounds(90, 290, 200, 20);
				textField_5.setColumns(10);
				contentPane.add(textField_5);

				// Method
				JComboBox comboBox = new JComboBox(new String[] { "Cash", "Debit", "Credit Card" });
				comboBox.setBounds(90, 325, 200, 20);
				comboBox.setSelectedIndex((int)(Math.random()*3));
				comboBox.setMaximumRowCount(3);
				contentPane.add(comboBox);
				
				// Due Date
				JTextField textField_7 = new JTextField(dateFormat.format(new Date()));
				textField_7.setBounds(90, 360, 200, 20);
				textField_7.setColumns(10);
				contentPane.add(textField_7);
				
				// Interval
				JComboBox comboBox4 = new JComboBox(new String[] { "Weekly", "Monthly", "Yearly" });
				comboBox4.setSelectedIndex(1);
				comboBox4.setMaximumRowCount(3);
				comboBox4.setBounds(90, 395, 200, 20);
				contentPane.add(comboBox4);
				
				// Default Hide DueDate and Interval
				textField_7.setVisible(false);
				comboBox4.setVisible(false);
				lblDueDate.setVisible(false);
				lblInterval.setVisible(false);
				
				comboBox3.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent event) {
		            	
		            	if(comboBox3.getSelectedItem().toString() == "Bill") {
							textField_7.setVisible(true);
							comboBox4.setVisible(true);
							lblDueDate.setVisible(true);
							lblInterval.setVisible(true);
						}
						else {
							textField_7.setVisible(false);
							comboBox4.setVisible(false);
							lblDueDate.setVisible(false);
							lblInterval.setVisible(false);
						}
		            }
		            
		         });
				
				JButton btnAddExpense1 = new JButton("Add Expense");
				btnAddExpense1.setBounds(182, 442, 108, 23);
				contentPane.add(btnAddExpense1);
				
				txtChooseExpense = new JTextField();
				txtChooseExpense.setBorder(null);
				txtChooseExpense.setBackground(UIManager.getColor("Button.background"));
				txtChooseExpense.setText("1. Choose Expense Type");
				txtChooseExpense.setBounds(30, 42, 260, 20);
				contentPane.add(txtChooseExpense);
				txtChooseExpense.setColumns(10);
				
				txtEnterExpense = new JTextField();
				txtEnterExpense.setBorder(null);
				txtEnterExpense.setBackground(UIManager.getColor("Button.background"));
				txtEnterExpense.setText("2. Enter Expense Details");
				txtEnterExpense.setColumns(10);
				txtEnterExpense.setBounds(30, 119, 260, 20);
				contentPane.add(txtEnterExpense);

				btnAddExpense1.addActionListener(new ActionListener() {
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
				
		
	}
}
