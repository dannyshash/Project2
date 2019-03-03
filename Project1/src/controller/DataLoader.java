package controller;

import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;

public interface DataLoader {
	void dataLoad(List<Map<ExpenseKey , Expense>> expenseData);
}
