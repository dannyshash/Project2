package controller;

import view.ExpenseObserver;

public interface ExpenseSubject {
	public void start();

	public void register(ExpenseObserver observer);
	public void resetStateChange();
}
