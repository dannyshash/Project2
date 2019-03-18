package view;

import java.util.ArrayList;

import model.Expense;

/**
 *	interface for the viewer 
 *
 */
public interface UserActionsApi {
	public void addExpense(Expense exp);
	public void removeExpense(Expense exp);
	public void removeExpense(Expense exp, Expense root);
	public void modifyExpense(Expense from, Expense to);
	public void AddExpenseToComposite(Expense comp_expense, Expense expense);
	public void AddExpensesToComposite(Expense comp_expense, ArrayList<Expense> expenses);
	public void changePaymentStatus(Expense exp);
	public void changePaymentStatus(Expense exp, Expense root);
}
