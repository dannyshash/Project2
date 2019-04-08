package controller;

import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;

public class DatabaseLoaderImpl implements DataLoader {
	private final MySqlDataBase myDb;
	
	public DatabaseLoaderImpl(MySqlDataBase myDb) {
		this.myDb = myDb;
	}

	@Override
	public void dataLoad(List<Map<ExpenseKey, Expense>> expenseData) {
		myDb.loadPurchases(expenseData.get(ExpenseType.PURCHASE.ordinal()));
		myDb.loadCompPurchases(expenseData.get(ExpenseType.COMPOSITE_PURCHASE.ordinal()));
		myDb.loadBills(expenseData.get(ExpenseType.BILL.ordinal()));
		myDb.loadCompBills(expenseData.get(ExpenseType.COMPOSITE_BILL.ordinal()));
	}

	@Override
	public void pushData(List<Map<ExpenseKey, Expense>> expenseData) {
		myDb.pushData(expenseData);
	}
}
