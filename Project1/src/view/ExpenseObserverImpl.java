package view;

import controller.ExpenseContainerImpl;

public class ExpenseObserverImpl implements ExpenseObserver{
	private static final ExpenseObserverImpl expenseObserver = new ExpenseObserverImpl();
	
	/**
	 * private constructor for the singleton
	 */
	private ExpenseObserverImpl() {
		
	}

	public static ExpenseObserverImpl getInstance() {
		return expenseObserver;
	}
	
	public void init() {
		ExpenseContainerImpl.getInstance().register(this);
	}
	
	@Override
	public void update() {
		System.out.println("Data state changed, please refresh the view!");
		
	}

}
