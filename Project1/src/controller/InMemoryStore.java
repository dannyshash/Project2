package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;
import model.ExpenseType;

public class InMemoryStore implements Store {
	private DataLoader dataLoader;
	//data
	private List<Map<ExpenseKey , Expense>> expenseData;
	private Map<ExpenseKey , Expense> purchases;
	private Map<ExpenseKey , Expense> comp_purchases;
	private Map<ExpenseKey , Expense> bill;
	private Map<ExpenseKey , Expense> comp_bill;

	public InMemoryStore(DataLoader dataLoader) {
		this.dataLoader = dataLoader;
		expenseData = new ArrayList<Map<ExpenseKey , Expense>>();
		purchases = new HashMap<ExpenseKey , Expense>();
		comp_purchases = new HashMap<ExpenseKey , Expense>();
		bill = new HashMap<ExpenseKey , Expense>();
		comp_bill = new HashMap<ExpenseKey , Expense>();
		
		expenseData.add(ExpenseType.PURCHASE.ordinal(), purchases);
		expenseData.add(ExpenseType.BILL.ordinal(), bill);
		expenseData.add(ExpenseType.COMPOSITE_PURCHASE.ordinal(), comp_purchases);
		expenseData.add(ExpenseType.COMPOSITE_BILL.ordinal(), comp_bill);
		
		dataLoader.dataLoad(expenseData);

	}
	
	@Override
	public void put(Expense expense) throws IOException {
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
			throw new RuntimeException("Invalid Expense type");
		}
	}

	@Override
	public Expense get(ExpenseKey key) throws IOException {
		Expense expense = null;

		switch(key.type.ordinal()){
		case 0 :
			expense = purchases.get(key);
			break;
		case 1 : 
			expense = bill.get(key);
			break;
		case 2 :
			expense = comp_purchases.get(key);
			break;
		case 3 : 
			expense = comp_bill.get(key);
			break;
		default:
			throw new RuntimeException("Invalid Expense type") ;
		}

		if(expense==null){
			throw new RuntimeException("Can't find the composite expense"+key.toString());
		}
		
		return expense;		
	}

	@Override
	public List<Expense> getMatching() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Expense expense) throws IOException {
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
	}

	@Override
	public boolean modify(Expense from, Expense to) {
		
		//Let's check if they are same
		if(!from.iseqal(to)) {
			return false;
		}
		
		try {
			this.remove(from);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		switch(from.getType().ordinal()){
			case 0 : purchases.put(to.getKey(), to);
				break;
			case 1 : bill.put(to.getKey(), to);
				break;
	 		case 2 : comp_purchases.put(to.getKey(), to);
				break;
			case 3 : comp_bill.put(to.getKey(), to);
				break;
			default:
				throw new RuntimeException() ;
		}
		return true;
	}

	@Override
	public List<Map<ExpenseKey, Expense>> getAll() {
		return expenseData;
	}
	
	@Override
	public void display() {
		System.out.println("*** Dsplay Purchase");
		display(purchases);
		System.out.println("*** Dsplay Comp_Purchase");
		display(comp_purchases);
		System.out.println("*** Dsplay Bill");
		display(bill);
		System.out.println("*** Dsplay Comp_Bill");
		display(comp_bill);
	}
		
	private void display(Map<ExpenseKey , Expense> map) {
		List<Expense> expList = new ArrayList<Expense>(map.values());
		Iterator<Expense> expIt = expList.iterator();
		while(expIt.hasNext()) {
			expIt.next().display();
		}
	}

}
