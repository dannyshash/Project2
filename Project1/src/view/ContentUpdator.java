package view;

import java.util.ArrayList;

import model.Expense;
import model.ExpenseType;

public class ContentUpdator implements ExpenseContentApi {
	private final ExpenseObserver container;
	public ContentUpdator(ExpenseObserver container) {
		this.container=container;
	}

	private ArrayList<Expense> getData(DisplayParameters params) {
		return container.getDataByExpenseType(params);
	}

	private ArrayList<Expense> getData(ExpenseType type) {
		DisplayParameters params = new DisplayParameters();
		params.type = type;
		return this.getData(params);
	}
	
	@Override
	public ArrayList<Expense> getPurchases() {
		DisplayParameters params = new DisplayParameters();
		params.type = ExpenseType.PURCHASE;
		return container.getData(params);
	}
	
	@Override
	public ArrayList<Expense> getAllPurchases() {
		return this.getData(ExpenseType.PURCHASE);
	}

	@Override
	public ArrayList<Expense> getBills() {
		DisplayParameters params = new DisplayParameters();
		params.type = ExpenseType.BILL;
		return container.getData(params);
	}
	
	@Override
	public ArrayList<Expense> getAllBills() {
		return this.getData(ExpenseType.BILL);
	}

	@Override
	public ArrayList<Expense> getCompositePurchases() {
		DisplayParameters params = new DisplayParameters();
		params.type = ExpenseType.COMPOSITE_PURCHASE;
		return container.getData(params);
	}
	
	@Override
	public ArrayList<Expense> getCompositeBills() {
		DisplayParameters params = new DisplayParameters();
		params.type = ExpenseType.COMPOSITE_BILL;
		return container.getData(params);
	}
	
}
