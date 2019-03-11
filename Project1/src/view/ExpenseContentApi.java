package view;

import model.Expense;
import model.ExpenseType;

import java.util.ArrayList;

/**
 * API for the viewer according to the user options set
 */
public interface ExpenseContentApi {
	//Data access methods for viewer
	public ArrayList<Expense> getPurchases();
	public ArrayList<Expense> getAllPurchases();
	public ArrayList<Expense> getCompositePurchases();
	public ArrayList<Expense> getBills();
	public ArrayList<Expense> getAllBills();
	public ArrayList<Expense> getCompositeBills();
}
