package view;

import model.Expense;

/**
 *	interface for the viewer 
 *
 */
public interface UserActions {
	public void addExpense(Expense exp);
	public void removeExpense(Expense exp);
	public void modifyExpense(Expense from, Expense to);
	public void changePaymentStatus(Expense exp);

}
