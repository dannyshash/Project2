package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;

/**
 * The Observer class 
 *
 */
public interface ExpenseObserver {
	//Notification about the data change from controller
	public void update(List<Map<ExpenseKey , Expense>> data);	
	//interface for the Viewer to get the data based on the display parameters
	ArrayList<Expense> getData(final DisplayParameters params);
}
