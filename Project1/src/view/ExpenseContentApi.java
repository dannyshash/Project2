package view;

import model.Expense;
import java.util.ArrayList;

/**
 * API for the viewer according to the user options set
 */
public interface ExpenseContentApi {
	ArrayList<Expense> getData(final DisplayParameters params);
}
