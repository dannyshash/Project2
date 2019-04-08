package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;

public class DatabaseStore implements Store {
	private DataLoader dataLoader;
	//data
	private List<Map<ExpenseKey , Expense>> expenseData;
	private Map<ExpenseKey , Expense> purchases;
	private Map<ExpenseKey , Expense> comp_purchases;
	private Map<ExpenseKey , Expense> bill;
	private Map<ExpenseKey , Expense> comp_bill;

	public DatabaseStore(DataLoader dataLoader) {
		this.dataLoader = dataLoader;
		
		expenseData = new ArrayList<Map<ExpenseKey , Expense>>();
		purchases = new HashMap<ExpenseKey , Expense>();
		comp_purchases = new HashMap<ExpenseKey , Expense>();
		bill = new HashMap<ExpenseKey , Expense>();
		comp_bill = new HashMap<ExpenseKey , Expense>();
		
		expenseData.add(ExpenseType.PURCHASE.ordinal(), purchases);
		expenseData.add(ExpenseType.BILL.ordinal(), bill);
		expenseData.add(ExpenseType.COMPOSITE_PURCHASE.ordinal(), comp_purchases);
		expenseData.add(ExpenseType.COMPOSITE_BILL.ordinal(), comp_bill);
		
		dataLoader.dataLoad(expenseData);
	}

	@Override
	public void put(Expense exp) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Expense get(ExpenseKey key) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expense> getMatching() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Expense expense) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean modify(Expense from, Expense to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Map<ExpenseKey, Expense>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
}
