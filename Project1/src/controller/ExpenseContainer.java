package controller;

import java.util.ArrayList;
import model.Expense;

public interface ExpenseContainer {
	public ArrayList<Expense> getPurchases();
	public ArrayList<Expense> getCompositePurchases();
	public ArrayList<Expense> getBills();
	public ArrayList<Expense> getCompositeBills();
	
	public void addExpense(Expense expense);
	public void addExpenses(ArrayList<Expense> expenseList);
	
	public boolean modifyExpense(Expense from, Expense to);
	
	public void removeExpense(Expense expense);
	
	//get matching composite expenses
	public ArrayList<Expense> getExpenses();
	/**
	 * 
	 * @param Expense object
	 * @return Expense object if match found, null if no match
	 */
	public Expense getMatching(Expense exp);
	
}
