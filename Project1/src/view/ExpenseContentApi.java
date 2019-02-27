package view;

import model.Expense;
import java.util.ArrayList;

interface ExpenseContentApi {
	ArrayList<Expense> getData(final DisplayParameters params);
}
