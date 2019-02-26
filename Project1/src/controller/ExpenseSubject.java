package controller;

import view.ExpenseObserver;

public interface ExpenseSubject {
	public void register(ExpenseObserver observer);
}
