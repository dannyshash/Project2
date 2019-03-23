package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

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
	private final UserActionsApi userActions;
	
	public AddExpPanelAddBtnListener(UserActionsApi userActions) {
		this.userActions = userActions;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final AddExpensePanel addPanel = (AddExpensePanel)SwingUtilities.windowForComponent((JButton)e.getSource());
		Expense expense = null;
		ExpenseType type = (ExpenseType)addPanel.expTypeCombo.getSelectedItem();

		//Define Expense Object
		double amount = new Double(addPanel.textField_3.getText()).doubleValue();
		String name = addPanel.textField_2.getText();
		Date date = addPanel.getDate();
		Status status = (Status)addPanel.expStatusCombo.getSelectedItem();
		Date dueDate = addPanel.getDueDate();	
		String vendor = addPanel.textField_8.getText();
		String location; 
		Mode mode = (Mode)addPanel.paymentMethodCombo.getSelectedItem();
		ExpenseCategories category = (ExpenseCategories)addPanel.expCategoryCombo.getSelectedItem();
		RepitionInterval interval;
					
		//Decide which object to add
		System.out.println("Adding Expense Type " + addPanel.expTypeCombo.getSelectedItem().toString());
		if (type == ExpenseType.PURCHASE) {
			//Define Purchase specific Object
			location = addPanel.textField_5.getText();

			expense = new Purchase(amount, name, date, status, vendor, location, mode, category);
		} else if(type == ExpenseType.BILL) {
			//Define Bill specific Object
			interval = (RepitionInterval)addPanel.paymentIntervalCombo.getSelectedItem();
		
			expense = new Bill(amount, name, date, status,
					dueDate, vendor, interval, category);
		} else {
			throw new RuntimeException("AddCompExpPanelAddBtnListener: bad expense type");
		}	
		System.out.println("Adding an Expense: " + expense);
		userActions.addExpense(expense);

					
		// Re-initialize values randomly
		String exp_date = MyDate.getRandomDateStr();
		String due_date = MyDate.getDateString(MyDate.addDays(MyDate.getJustDate(exp_date), MyDate.getRandomInRange(1, 9)));
		addPanel.paymentMethodCombo.setSelectedIndex((int)(Math.random()*3));
		addPanel.dateText.setText(exp_date);
		addPanel.dueDateText.setText(due_date);
		addPanel.textField_2.setText(addPanel.randomName[new Random().nextInt(addPanel.randomName.length)]);
		addPanel.textField_3.setText("" + (Math.round(Math.random() * 1000.0) / 100.0));
		addPanel.textField_5.setText(addPanel.randomLocation[new Random().nextInt(addPanel.randomLocation.length)]);
	}
}
