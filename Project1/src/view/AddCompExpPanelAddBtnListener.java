package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.Bill;
import model.CompositeBill;
import model.CompositePurchase;
import model.Expense;
import model.ExpenseCategories;
import model.ExpenseType;
import model.Mode;
import model.Purchase;
import model.RepitionInterval;
import model.Status;
import utils.MyDate;

public class AddCompExpPanelAddBtnListener extends AddExpPanelAddBtnListener {
	private final UserActionsApi userActions;
	private final ArrayList<Expense> items;
	
	public AddCompExpPanelAddBtnListener(UserActionsApi userActions, ArrayList<Expense> items) {
		super(userActions);
		this.userActions = userActions;
		this.items = items;
	}
	
	public ExpenseType getType() { 
		return items.get(0).getType();
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
		String vendor = addPanel.textField_8.getText();
		String location; 
		Mode mode = (Mode)addPanel.paymentMethodCombo.getSelectedItem();
		String description = addPanel.descriptionText.getText();
		ExpenseCategories category = (ExpenseCategories)addPanel.expCategoryCombo.getSelectedItem();
		RepitionInterval interval;
					
		//Decide which object to add
		System.out.println("Adding Expense Type " + addPanel.expTypeCombo.getSelectedItem().toString());
		if (type == ExpenseType.COMPOSITE_PURCHASE) {
			//Define Purchase specific Object
			location = addPanel.textField_5.getText();

			expense = new CompositePurchase(amount, name, date, description, category, status, mode, location);
			for (Expense item : items) {
				Expense subItem = new Purchase((Purchase)item);
				expense.add(subItem);
				userActions.removeExpense(item);
			}
		} else if(type == ExpenseType.COMPOSITE_BILL) {
			//Define Bill specific Object
			interval = (RepitionInterval)addPanel.paymentIntervalCombo.getSelectedItem();
		
			expense = new CompositeBill(amount, name, date, description, vendor, status, category, interval);
			for (Expense item : items) {
				Expense subItem = new Bill((Bill)item);
				expense.add(subItem);
				userActions.removeExpense(item);
			}

		} else {
			throw new RuntimeException("AddCompExpPanelAddBtnListener: bad expense type");
		}
		System.out.println("Adding a composite Expense: " + expense);
		expense.display();
		
		userActions.addExpense(expense);
	}
}
