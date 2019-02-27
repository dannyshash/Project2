package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Expense;

/*
 * data container interface
 * should be back-end (files/database/in-memory hash) independent 
 */
public interface ExpenseContainer {
	public void init();

	//Data access methods for viewer
	public ArrayList<Expense> getPurchases();
	public ArrayList<Expense> getCompositePurchases();
	public ArrayList<Expense> getBills();
	public ArrayList<Expense> getCompositeBills();
	
	//Data write methods for viewer
	public void addExpense(Expense expense);
	public void addExpenseIntoComposite(Expense compositeExpense, Expense expense);
	public boolean modifyExpense(Expense from, Expense to);	
	public void removeExpense(Expense expense);
	public void removeExpenseFromComposite(Expense compositeExpense, Expense expense);
	public boolean changePaymentStatus(Expense expense, Date date);
	
	//get matching composite expenses
	public ArrayList<Expense> getExpenses();
	/**
	 * 
	 * @param Expense object
	 * @return Expense object if match found, null if no match
	 */
	public Expense getMatching(Expense exp);
	
}