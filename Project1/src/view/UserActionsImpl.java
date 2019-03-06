package view;

import java.util.ArrayList;
import java.util.Date;

import controller.ExpenseContainerApi;
import model.Expense;

public class UserActionsImpl implements UserActionsApi {
	private final ExpenseContainerApi container;
	
	public UserActionsImpl(ExpenseContainerApi container) {
		this.container=container;
	}

	@Override
	public void addExpense(Expense exp) {
		container.addExpense(exp);
	}

	@Override
	public void removeExpense(Expense exp) {
		container.removeExpense(exp);
	}

	@Override
	public void modifyExpense(Expense from, Expense to) {
		container.modifyExpense(from, to);
	}

	@Override
	public void AddExpenseToComposite(Expense comp_expense, Expense expense) {
		container.addExpenseIntoComposite(comp_expense, expense);
	}

	@Override
	public void AddExpensesToComposite(Expense comp_expense, ArrayList<Expense> expenses) {
		container.addExpensesIntoComposite(comp_expense, expenses);

	}

	@Override
	public void changePaymentStatus(Expense exp) {
		//TODO check
		container.changePaymentStatus(exp, new Date());

	}

}
