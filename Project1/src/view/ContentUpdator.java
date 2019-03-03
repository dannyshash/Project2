package view;

import java.util.ArrayList;

import model.Expense;

public class ContentUpdator implements ExpenseContentApi {
	private final ExpenseObserver container;
	public ContentUpdator(ExpenseObserver container) {
		this.container=container;
	}

	@Override
	public ArrayList<Expense> getData(DisplayParameters params) {
		return container.getData(params);
	}

}
