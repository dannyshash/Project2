package view;

import model.Expense;
import model.ExpenseType;

import java.util.ArrayList;

/**
 * API for the viewer according to the user options set
 */
public interface ExpenseContentApi {
	ArrayList<Expense> getData(final DisplayParameters params);
	ArrayList<Expense> getData(final ExpenseType type);
}
