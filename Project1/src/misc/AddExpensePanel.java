package misc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ExpenseContainerImpl;
import model.Bill;
import model.Purchase;
import model.RepitionInterval;
import model.ExpenseCategories;
import model.Status;
import model.Mode;
import model.ExpenseType;

import javax.swing.UIManager;

public class AddExpensePanel extends UserInterface {

	private JPanel contentPane;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtChooseExpense;
	
	private JTextField dateText;
	private JTextField dueDateText;
	
	/**
	 * Create the frame.
	 */
	public AddExpensePanel(ExpenseList myList, ExpenseListTableModel tableModel) {
		
		this.myList = myList;
		this.tableModel = tableModel;
		
		setTitle("Add Expense Panel");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 360, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		// Label Creation
		JLabel lblNewLabel = new JLabel("Expense Type");
		lblNewLabel.setBounds(30, 50, 77, 23);
		contentPane.add(lblNewLabel);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(30, 82, 53, 23);
		contentPane.add(lblDate);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(30, 116, 53, 23);
		contentPane.add(lblName);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(30, 148, 53, 23);
		contentPane.add(lblAmount);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(30, 183, 53, 23);
		contentPane.add(lblStatus);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(30, 284, 53, 23);
		contentPane.add(lblLocation);

		JLabel lblMethod = new JLabel("Method");
		lblMethod.setBounds(30, 216, 53, 23);
		contentPane.add(lblMethod);

		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setBounds(30, 390, 53, 23);
		contentPane.add(lblDueDate);

		JLabel lblInterval = new JLabel("Interval");
		lblInterval.setBounds(30, 425, 53, 23);
		contentPane.add(lblInterval);

		// Type
		JComboBox<ExpenseType> expTypeCombo = new JComboBox<>();
		//ExpenseType.PURCHASE,ExpenseType.BILL
		expTypeCombo.setModel(new DefaultComboBoxModel<>(ExpenseType.values()));
		expTypeCombo.setBounds(147, 51, 174, 20);
		expTypeCombo.setSelectedIndex(0);
		expTypeCombo.setMaximumRowCount(2);
		contentPane.add(expTypeCombo);

		// Date
		dateText = new JTextField(dateFormat.format(new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1))))));
		dateText.setBounds(147, 83, 174, 20);
		dateText.setColumns(10);
		contentPane.add(dateText);

		// Name
		String[] randomName = { "Tea", "Coffee", "Sandwich", "Cookie", "Toast", "Chocolate",
				"Candy"};
		JTextField textField_2 = new JTextField(randomName[new Random().nextInt(randomName.length)]);
		textField_2.setBounds(147, 117, 174, 20);
		textField_2.setColumns(10);
		contentPane.add(textField_2);

		// Amount
		JTextField textField_3 = new JTextField("" + (Math.round(Math.random() * 1000.0) / 100.0));
		textField_3.setBounds(147, 149, 174, 20);
		textField_3.setColumns(10);
		contentPane.add(textField_3);

		// Status
		JComboBox<Status> expStatusCombo = new JComboBox<>();
		expStatusCombo.setModel(new DefaultComboBoxModel<>(Status.values()));
		expStatusCombo.setBounds(147, 183, 174, 20);
		//expStatusCombo.setSelectedIndex(1);
		expStatusCombo.setMaximumRowCount(2);
		contentPane.add(expStatusCombo);

		// Location
		String[] randomLocation = { "Downtown", "Brossard", "Campus", "St Henri"};
		JTextField textField_5 = new JTextField(randomLocation[new Random().nextInt(randomLocation.length)]);
		textField_5.setBounds(147, 285, 174, 20);
		textField_5.setColumns(10);
		contentPane.add(textField_5);

		// Method
		JComboBox<Mode> paymentMethodCombo = new JComboBox<>();
		paymentMethodCombo.setModel(new DefaultComboBoxModel<>(Mode.values()));
		paymentMethodCombo.setBounds(147, 217, 174, 20);
		paymentMethodCombo.setSelectedIndex(Mode.CASH.ordinal());
		paymentMethodCombo.setMaximumRowCount(3);
		contentPane.add(paymentMethodCombo);
		
		// Due Date
		dueDateText = new JTextField(dateFormat.format(new Date()));
		dueDateText.setBounds(147, 391, 174, 20);
		dueDateText.setColumns(10);
		contentPane.add(dueDateText);
		
		// Interval
		JComboBox<RepitionInterval> paymentIntervalCombo = new JComboBox<>();
		paymentIntervalCombo.setModel(new DefaultComboBoxModel<>(RepitionInterval.values()));
		//JComboBox paymentIntervalCombo = new JComboBox(RepitionInterval.values());
		paymentIntervalCombo.setSelectedIndex(1);
		paymentIntervalCombo.setMaximumRowCount(3);
		paymentIntervalCombo.setBounds(147, 426, 174, 20);
		contentPane.add(paymentIntervalCombo);
		
		// Category
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(30, 318, 53, 23);
		contentPane.add(lblCategory);
		
		JComboBox<ExpenseCategories> expCategoryCombo = new JComboBox<>();
		expCategoryCombo.setModel(new DefaultComboBoxModel<>(ExpenseCategories.values()));
		expCategoryCombo.setSelectedIndex(0);
		expCategoryCombo.setMaximumRowCount(3);
		expCategoryCombo.setBounds(147, 319, 174, 20);
		contentPane.add(expCategoryCombo);
		
		// Vendor Name
		JLabel lblVendorname = new JLabel("Vendor Name");
		lblVendorname.setBounds(30, 250, 107, 23);
		contentPane.add(lblVendorname);
		
		String[] vendorNames = { "Tim Hortons", "Starbucks", "Second Cup", "Van Houtte"};				
		JTextField textField_8 = new JTextField(vendorNames[new Random().nextInt(vendorNames.length)]);
		textField_8.setBounds(147, 251, 174, 20);
		textField_8.setColumns(10);
		contentPane.add(textField_8);

		
		// Default Hide DueDate and Interval
		dueDateText.setVisible(false);
		paymentIntervalCombo.setVisible(false);
		lblDueDate.setVisible(false);
		lblInterval.setVisible(false);
		
		
		expTypeCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(expTypeCombo.getSelectedItem() ==ExpenseType.BILL ||
            			expTypeCombo.getSelectedItem() ==ExpenseType.COMPOSITE_BILL) {
					dueDateText.setVisible(true);
					paymentIntervalCombo.setVisible(true);
					lblDueDate.setVisible(true);
					lblInterval.setVisible(true);
				}
				else {
					dueDateText.setVisible(false);
					paymentIntervalCombo.setVisible(false);
					lblDueDate.setVisible(false);
					lblInterval.setVisible(false);
				}		
            }
        });
						
		
		JButton btnAddExpense1 = new JButton("Add Expense");
		btnAddExpense1.setBounds(122, 480, 108, 23);
		contentPane.add(btnAddExpense1);
		
		txtChooseExpense = new JTextField();
		txtChooseExpense.setBorder(null);
		txtChooseExpense.setBackground(UIManager.getColor("Button.background"));
		txtChooseExpense.setText("Expense Details");
		txtChooseExpense.setBounds(110, 11, 90, 20);
		contentPane.add(txtChooseExpense);
		txtChooseExpense.setColumns(10);
		
	
		btnAddExpense1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				//Decide which object to add
				System.out.println("Expense Type selected "+expTypeCombo.getSelectedItem().toString());
				if(expTypeCombo.getSelectedItem() == ExpenseType.PURCHASE) {
					System.out.println("Expense Status selected "+expStatusCombo.getSelectedItem().toString());
					//Define Purchase Object
					Purchase purchaseAdd = new PurchaseAdaptor(getDate(), textField_2.getText(), new Double(textField_3.getText()),
							(Status)expStatusCombo.getSelectedItem(), (ExpenseCategories)expCategoryCombo.getSelectedItem(), textField_8.getText(), textField_5.getText(),
							(Mode)paymentMethodCombo.getSelectedItem(), getDueDate());

					System.out.println("Adding purchase: "+ purchaseAdd);
					myList.add(purchaseAdd);
					ExpenseContainerImpl.getInstance().addExpense(purchaseAdd);
				}
				else if(expTypeCombo.getSelectedItem() == ExpenseType.BILL) {
					//Define Bill Object
					Bill billAdd = new BillAdaptor(getDate(), textField_2.getText(), new Double(textField_3.getText()),
							(Status)expStatusCombo.getSelectedItem(), (ExpenseCategories)expCategoryCombo.getSelectedItem(), textField_8.getText(), textField_5.getText(),
							(Mode)paymentMethodCombo.getSelectedItem(), getDueDate(), (RepitionInterval)paymentIntervalCombo.getSelectedItem());
					
					myList.add(billAdd);	
					ExpenseContainerImpl.getInstance().addExpense(billAdd);
				}						

							
				tableModel.fireTableDataChanged();
				
				// Re-initialize values randomly
				paymentMethodCombo.setSelectedIndex((int)(Math.random()*3));
				dateText.setText(dateFormat.format(new Date((2018 - 1900), (int)(Math.round(Math.random() * (12 - 1))), (int)(Math.round(Math.random() * (28 - 1))))));
				textField_2.setText(randomName[new Random().nextInt(randomName.length)]);
				textField_3.setText("" + (Math.round(Math.random() * 1000.0) / 100.0));
				textField_5.setText(randomLocation[new Random().nextInt(randomLocation.length)]);
			}
		});		
	}
	
	//getDate(), getDueDate() should be better implementation, 
	//calendar or system time etc...
	private Date getDate() {
		// Transform Date into integer inputs
		int textField_1_year = Integer.valueOf((dateText.getText().substring(0, 4)));
		int textField_1_month = Integer.valueOf((dateText.getText().substring(5, 7)));
		int textField_1_day = Integer.valueOf((dateText.getText().substring(8, 10)));
		
		return new Date((textField_1_year - 1900), textField_1_month - 1, textField_1_day);
	}
	
	private Date getDueDate() {	
		int textField_7_year = Integer.valueOf((dueDateText.getText().substring(0, 4)));
		int textField_7_month = Integer.valueOf((dueDateText.getText().substring(5, 7)));
		int textField_7_day = Integer.valueOf((dueDateText.getText().substring(8, 10)));
		
		return new Date((textField_7_year - 1900), textField_7_month - 1, textField_7_day);
	}
}
