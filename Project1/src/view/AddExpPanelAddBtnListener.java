package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;

import misc.AddExpensePanel;
import model.Bill;
import model.Expense;
import model.ExpenseCategories;
import model.ExpenseType;
import model.Mode;
import model.Purchase;
import model.RepitionInterval;
import model.Status;
import utils.MyDate;

public class AddExpPanelAddBtnListener implements ActionListener {
	private final AddExpensePanel addExpPanel;
	private final UserActionsApi userActions;
	
	public AddExpPanelAddBtnListener(AddExpensePanel addExpPanel, UserActionsApi userActions) {
		this.addExpPanel = addExpPanel;
		this.userActions = userActions;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Expense expense = null;
		ExpenseType type = (ExpenseType)addExpPanel.expTypeCombo.getSelectedItem();

		//Define Expense Object
		double amount = new Double(addExpPanel.textField_3.getText()).doubleValue();
		String name = addExpPanel.textField_2.getText();
		Date date = addExpPanel.getDate();
		Status status = (Status)addExpPanel.expStatusCombo.getSelectedItem();
		Date dueDate = addExpPanel.getDueDate();		
		String vendor = addExpPanel.textField_8.getText();
		String location; 
		Mode mode = (Mode)addExpPanel.paymentMethodCombo.getSelectedItem();
		ExpenseCategories category = (ExpenseCategories)addExpPanel.expCategoryCombo.getSelectedItem();
		RepitionInterval interval;
					
		//Decide which object to add
		System.out.println("Adding Expense Type " + addExpPanel.expTypeCombo.getSelectedItem().toString());
		if (type == ExpenseType.PURCHASE) {
			//Define Purchase specific Object
			location = addExpPanel.textField_5.getText();

			expense = new Purchase(amount, name, date, status, dueDate, vendor, location, mode, category);
		}
		else if(type == ExpenseType.BILL) {
			//Define Bill specific Object
			interval = (RepitionInterval)addExpPanel.paymentIntervalCombo.getSelectedItem();
		
			expense = new Bill(amount, name, date, status,
					dueDate, vendor, interval, category);
		}	
		System.out.println("Adding an Expense: " + expense);
		userActions.addExpense(expense);

					
		//tableModel.fireTableDataChanged();
		
		// Re-initialize values randomly
		String exp_date = MyDate.getRandomDateStr();
		String due_date = MyDate.getDateString(MyDate.addDays(MyDate.getJustDate(exp_date), MyDate.getRandomInRange(1, 9)));
		addExpPanel.paymentMethodCombo.setSelectedIndex((int)(Math.random()*3));
		addExpPanel.dateText.setText(exp_date);
		addExpPanel.dueDateText.setText(due_date);
		addExpPanel.textField_2.setText(addExpPanel.randomName[new Random().nextInt(addExpPanel.randomName.length)]);
		addExpPanel.textField_3.setText("" + (Math.round(Math.random() * 1000.0) / 100.0));
		addExpPanel.textField_5.setText(addExpPanel.randomLocation[new Random().nextInt(addExpPanel.randomLocation.length)]);
	}
}
