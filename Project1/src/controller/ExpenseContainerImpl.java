package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import misc.Constants;
import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;
import model.Purchase;
import view.ExpenseObserver;

public class ExpenseContainerImpl implements ExpenseContainer, ExpenseSubject{
	private static final ExpenseContainerImpl expenseContainer = new ExpenseContainerImpl();
	private ExpenseObserver observer;
	private boolean dataStateChanged;
	
	//data
	private List<Map<ExpenseKey , Expense>> expenseData;
	Map<ExpenseKey , Expense> purchases;
	Map<ExpenseKey , Expense> comp_purchases;
	Map<ExpenseKey , Expense> bill;
	Map<ExpenseKey , Expense> comp_bill;

	/**
	 * private constructor for the Singleton object
	 */
	private ExpenseContainerImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static ExpenseContainerImpl getInstance() {
		return expenseContainer;
	}
	
	/**
	 * initialize the data members and start
	 */
	public void init() {
		expenseData = new ArrayList<Map<ExpenseKey , Expense>>();
		purchases = new HashMap<ExpenseKey , Expense>();
		comp_purchases = new HashMap<ExpenseKey , Expense>();
		bill = new HashMap<ExpenseKey , Expense>();
		comp_bill = new HashMap<ExpenseKey , Expense>();
		
		expenseData.add(ExpenseType.PURCHASE.ordinal(), purchases);
		expenseData.add(ExpenseType.BILL.ordinal(), bill);
		expenseData.add(ExpenseType.COMPOSITE_PURCHASE.ordinal(), comp_purchases);
		expenseData.add(ExpenseType.COMPOSITE_BILL.ordinal(), comp_bill);
		
		dataStateChanged=true;
	}
	
	@Override
	public void register(ExpenseObserver observer) {
		this.observer = observer;
	}

	@Override
	public ArrayList<Expense> getPurchases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getCompositePurchases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getBills() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Expense> getCompositeBills() {
		// TODO Auto-generated method stub
		return null;
	}

	private void notifyObservers() {
		observer.update();
		setDataStateChanged(false);
	}
	
	@Override
	public void addExpense(Expense expense) {
		expense.display();
		switch(expense.getType().ordinal()){
		case 0 : purchases.put(expense.getKey(), expense);
			break;
		case 1 : bill.put(expense.getKey(), expense);
			break;
		case 2 : comp_purchases.put(expense.getKey(), expense);
			break;
		case 3 : comp_bill.put(expense.getKey(), expense);
			break;
		default:
			throw new RuntimeException("Invalid Expense type") ;
		}
		
		notifyObservers();
	}

	@Override
	public void addExpenseIntoComposite(Expense compExpense, Expense expense) {
		getCompositeExpenseFromMap(compExpense).add(expense);
		notifyObservers();		
	}

	@Override
	public boolean modifyExpense(Expense from, Expense to) {
		//Let's check if they are same
		if(from.iseqal(to)) {
			return false;
		}
		
		//Composite
		if(from.getParent() != null) {
			Expense parent = getCompositeExpenseFromMap(from.getParent());
			parent.remove(from);
			parent.remove(to);			
		}
		else {
			switch(from.getType().ordinal()){
			case 0 : purchases.put(from.getKey(), to);
				break;
			case 1 : bill.put(from.getKey(), to);
				break;
/*		
	 		case 2 : 
				comp_purchases.put(from.getKey(), to);
				break;
			case 3 : comp_bill.put(from.getKey(), to);
				break;
*/		
			default:
				throw new RuntimeException() ;
			}
		}
		
		setDataStateChanged(true);
		return true;
	}

	@Override
	public void removeExpense(Expense expense) {
		switch(expense.getType().ordinal()){
		case 0 : purchases.remove(expense.getKey(), expense);
			break;
		case 1 : bill.remove(expense.getKey(), expense);
			break;
		case 2 : comp_purchases.remove(expense.getKey(), expense);
			break;
		case 3 : comp_bill.remove(expense.getKey(), expense);
			break;
		default:
			throw new RuntimeException() ;
		}
		setDataStateChanged(true);
	}

	@Override
	public void removeExpenseFromComposite(Expense compExpense, Expense expense) {
		getCompositeExpenseFromMap(compExpense).remove(expense);
		setDataStateChanged(true);
	}

	@Override
	public ArrayList<Expense> getExpenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expense getMatching(Expense exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePaymentStatus(Expense expense, Date date) {
		return getExpenseFromMap(expense).changePaymentStatus(date);
	}

	public boolean isDataStateChanged() {
		return dataStateChanged;
	}

	public void setDataStateChanged(boolean dataStateChanged) {
		this.dataStateChanged = dataStateChanged;
	}

	private Expense getExpenseFromMap(Expense exp) {
		Expense expense = null;

		switch(exp.getType().ordinal()){
		case 0 :
			expense = purchases.get(exp.getKey());
			break;
		case 1 : 
			expense = bill.get(exp.getKey());
			break;
		case 2 :
			expense = comp_purchases.get(exp.getKey());
			break;
		case 3 : 
			expense = comp_bill.get(exp.getKey());
			break;
		default:
			throw new RuntimeException("Invalid Expense type") ;
		}

		if(expense==null){
			throw new RuntimeException("Can't find the composite expense"+exp.toString());
		}
		
		return expense;		
	}

	private Expense getCompositeExpenseFromMap(Expense compExpense) {
		Expense compositeExpense = null;

		switch(compExpense.getType().ordinal()){
		case 2 :
			compositeExpense = comp_purchases.get(compExpense.getKey());
			break;
		case 3 : 
			compositeExpense = comp_bill.get(compExpense.getKey());
			break;
		default:
			throw new RuntimeException("Invalid Expense type") ;
		}

		if(compositeExpense==null){
			throw new RuntimeException("Can't find the composite expense"+compExpense.toString());
		}
		
		return compositeExpense;		
	}
}