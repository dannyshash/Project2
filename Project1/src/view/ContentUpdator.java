package view;

import java.util.ArrayList;

import model.Expense;
import model.ExpenseType;

public class ContentUpdator implements ExpenseContentApi {
	private final ExpenseObserver container;
	public ContentUpdator(ExpenseObserver container) {
		this.container=container;
	}

	@Override
	public ArrayList<Expense> getData(DisplayParameters params) {
		return container.getData(params);
	}

	@Override
	public ArrayList<Expense> getData(ExpenseType type) {
		DisplayParameters params = new DisplayParameters();
		params.type = type;
		return container.getData(params);
	}
}
