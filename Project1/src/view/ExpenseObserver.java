package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.ExpenseSubject;
import controller.Store;
import model.Expense;
import model.ExpenseKey;

/**
 * The Observer class 
 *
 */
public interface ExpenseObserver {
	public void init(ExpenseSubject subject);
	//Notification about the data change from controller
	public void update(List<Map<ExpenseKey , Expense>> data);	
	//interface for the Viewer to get the data based on the display parameters
	public Expense find(ExpenseKey key);
	ArrayList<Expense> getData(final DisplayParameters params);
	ArrayList<Expense> getDataByExpenseType(final DisplayParameters params);
}
