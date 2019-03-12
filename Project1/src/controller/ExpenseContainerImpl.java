package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;
import view.ExpenseObserver;

public class ExpenseContainerImpl implements ExpenseContainerApi, ExpenseSubject{
	private static final ExpenseContainerApi expenseContainer = new ExpenseContainerImpl();
	private ExpenseObserver observer;
	private boolean dataStateChanged;
	private boolean initializing =true;
	
	private Store store;
	

	/**
	 * private constructor for the Singleton object
	 */
	private ExpenseContainerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static ExpenseContainerApi getInstance() {
		return expenseContainer;
	}
	
	/**
	 * initialize the data members and start
	 */
	public void init(Store store) {
		initializing = true;
		this.store = store;
		
		store.display();
	}
	
	@Override
	public Store getStore() {
		return store;
	}
	
	@Override
	public void start() {
		notifyObservers(true);
	}
	
	@Override
	public void register(ExpenseObserver observer) {
		this.observer = observer;
	}
	
	@Override
	public void resetStateChange() {
		dataStateChanged = false;		
	}

	private void notifyObservers(boolean dataStateChange) {
		if(setDataStateChanged(dataStateChange))
			observer.update(store.getAll());
	}
	
	@Override
	public void addExpense(Expense expense) {
		addExpense(expense, true);
	}
	
	private void addExpense(Expense expense, boolean dataStateChange) {
		try {
			store.put(expense);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		notifyObservers(dataStateChange);
	}

	@Override
	public void addExpenseIntoComposite(Expense compExpense, Expense expense) {
		addExpenseIntoComposite(compExpense, expense, true);
	}
	
	@Override
	public void addExpensesIntoComposite(Expense compositeExpense, ArrayList<Expense> expenses) {
		throw new RuntimeException("addExpensesIntoComposite Not implemented");
		
	}
	
	private void addExpenseIntoComposite(Expense compExpense, Expense expense, boolean dataStateChange) {
		Expense exp=null;
		try {
			exp = store.get(compExpense.getKey());
			exp.add(expense);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			store.put(exp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		notifyObservers(dataStateChange);		
	}

	@Override
	public boolean modifyExpense(Expense from, Expense to) {
		if(store.modify(from, to)) {
			setDataStateChanged(true);
			return true;			
		}
		return false;
	}

	@Override
	public void removeExpense(Expense expense) {
		try {
			store.remove(expense);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		notifyObservers(true);
	}

	@Override
	public void removeExpenseFromComposite(Expense compExpense, Expense expense) {
		Expense exp=null;
		try {
			exp = store.get(compExpense.getKey());
			exp.remove(expense);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			store.put(exp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		notifyObservers(true);
	}

	@Override
	public ArrayList<Expense> getExpenses(int type) {
		throw new RuntimeException("getExpenses error");
	}

	@Override
	public Expense getMatching(Expense exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePaymentStatus(Expense expense, Date date) {
		Expense exp=null;
		try {
			exp = store.get(expense.getKey());
			exp.changePaymentStatus(date);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			store.put(exp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		notifyObservers(true);
		return true;
	}

	public boolean isDataStateChanged() {
		return dataStateChanged;
	}

	private boolean setDataStateChanged(boolean dataStateChanged) {
		this.dataStateChanged = dataStateChanged;
		
		return dataStateChanged;
	}

}
