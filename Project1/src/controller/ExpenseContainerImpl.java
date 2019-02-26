package controller;

import java.util.ArrayList;

import model.Expense;

public class ExpenseContainerImpl implements ExpenseContainer, ExpenseSubject{

	@Override
	public void register(ExpenseObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Expense> getPurchases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getCompositePurchases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getCompositeBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addExpense(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addExpenses(ArrayList<Expense> expenseList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean modifyExpense(Expense from, Expense to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeExpense(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Expense> getExpenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expense getMatching(Expense exp) {
		// TODO Auto-generated method stub
		return null;
	}

}
