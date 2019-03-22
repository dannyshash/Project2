package misc;


import java.awt.Color;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Expense;
import model.Bill;
import model.Purchase;
import model.RepitionInterval;
import model.ExpenseCategories;
import model.Status;
import utils.MyDate;
import view.AddExpPanelAddBtnListener;
import view.UIValidations;
import view.UserActionsApi;
import model.Mode;
import model.ExpenseType;

import javax.swing.UIManager;
import java.awt.Font;

public class AddExpensePanel extends JFrame {
	static final public String[] randomName = { "Tea", "Coffee", "Sandwich", "Cookie", "Toast", "Chocolate", "Candy"};
	static final public String[] randomLocation = { "Downtown", "Brossard", "Campus", "St Henri"};

	public JPanel contentPane;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public JTextField txtChooseExpense;
	
	public JTextField dateText;
	public JTextField dueDateText;
	
	public JComboBox<ExpenseType> expTypeCombo;
	public JLabel lblDateInvalid;
	public JTextField textField_2;	
	public JTextField textField_3;
	public JComboBox<Status> expStatusCombo;
	public JTextField textField_5;
	public JComboBox<Mode> paymentMethodCombo;
	public JComboBox<RepitionInterval> paymentIntervalCombo;
	public JComboBox<ExpenseCategories> expCategoryCombo;
	public JTextField textField_8;

	
	
	/**
	 * Create the frame.
	 */
	public AddExpensePanel(UserActionsApi userActions, AddExpPanelAddBtnListener listener) {
			
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

		// Type
		expTypeCombo = new JComboBox<>();
		//ExpenseType.PURCHASE,ExpenseType.BILL
		expTypeCombo.setModel(new DefaultComboBoxModel<>(ExpenseType.values()));
		expTypeCombo.setBounds(147, 51, 174, 20);
		expTypeCombo.setSelectedIndex(0);
		expTypeCombo.setMaximumRowCount(2);
		contentPane.add(expTypeCombo);

		lblDateInvalid = new JLabel("Invalid Date");
		lblDateInvalid.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblDateInvalid.setBounds(84, 84, 53, 23);
		lblDateInvalid.setForeground(contentPane.getBackground());
		contentPane.add(lblDateInvalid);
		// Date
		String expense_date = MyDate.getRandomDateStr();
		String expense_due_date = MyDate.getDateString(MyDate.addDays(MyDate.getJustDate(expense_date), MyDate.getRandomInRange(1, 9)));
		dateText = new JTextField(expense_date);
		dateText.setBounds(147, 83, 174, 20);
		dateText.setColumns(10);
		contentPane.add(dateText);
		dateText.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub			
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!UIValidations.dateValidation(dateText.getText())) {
					lblDateInvalid.setForeground(Color.RED);
					btnAddExpense1.setEnabled(false);
				} else {
					btnAddExpense1.setEnabled(true);
					lblDateInvalid.setForeground(contentPane.getBackground());
				}				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});


		// Name
		textField_2 = new JTextField(randomName[new Random().nextInt(randomName.length)]);
		textField_2.setBounds(147, 117, 174, 20);
		textField_2.setColumns(10);
		contentPane.add(textField_2);

		JLabel lblAmountInvalid = new JLabel("Invalid Amount");
		lblAmountInvalid.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblAmountInvalid.setBounds(76, 153, 61, 14);
		lblAmountInvalid.setForeground(contentPane.getBackground());
		contentPane.add(lblAmountInvalid);
		
		// Amount
		textField_3 = new JTextField("" + (Math.round(Math.random() * 1000.0) / 100.0));
		textField_3.setBounds(147, 149, 174, 20);
		textField_3.setColumns(10);
		contentPane.add(textField_3);
		textField_3.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub			
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!UIValidations.amountValidation(textField_3.getText())) {
					lblAmountInvalid.setForeground(Color.RED);
					btnAddExpense1.setEnabled(false);
				} else {
					btnAddExpense1.setEnabled(true);
					lblAmountInvalid.setForeground(contentPane.getBackground());
				}				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		// Status
		expStatusCombo = new JComboBox<>();
		expStatusCombo.setModel(new DefaultComboBoxModel<>(Status.values()));
		expStatusCombo.setBounds(147, 183, 174, 20);
		//expStatusCombo.setSelectedIndex(1);
		expStatusCombo.setMaximumRowCount(2);
		contentPane.add(expStatusCombo);

		// Location
		textField_5 = new JTextField(randomLocation[new Random().nextInt(randomLocation.length)]);
		textField_5.setBounds(147, 285, 174, 20);
		textField_5.setColumns(10);
		contentPane.add(textField_5);

		// Method
		paymentMethodCombo = new JComboBox<>();
		paymentMethodCombo.setModel(new DefaultComboBoxModel<>(Mode.values()));
		paymentMethodCombo.setBounds(147, 217, 174, 20);
		paymentMethodCombo.setSelectedIndex(Mode.CASH.ordinal());
		paymentMethodCombo.setMaximumRowCount(3);
		contentPane.add(paymentMethodCombo);
		
		// Due Date
		dueDateText = new JTextField(expense_due_date);
		dueDateText.setBounds(147, 391, 174, 20);
		dueDateText.setColumns(10);
		contentPane.add(dueDateText);
		
		// Interval
		paymentIntervalCombo = new JComboBox<>();
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
		
		expCategoryCombo = new JComboBox<>();
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
		textField_8 = new JTextField(vendorNames[new Random().nextInt(vendorNames.length)]);
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
						
		
		btnAddExpense1.addActionListener(listener);		
	}
	
	//getDate(), getDueDate() should be better implementation, 
	//calendar or system time etc...
	public Date getDate() {
		return MyDate.getJustDate(dateText.getText());
	}
	
	public Date getDueDate() {	
		return MyDate.getJustDate(dueDateText.getText());
	}
	
}
