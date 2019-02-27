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
	public void modifyExpense(Expense from, Expense to);
	public void AddExpensesToComposite(ArrayList<Expense> expenses);
	public void changePaymentStatus(Expense exp);
}
