package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CompositePurchase extends AbstractExpense{
	
	public CompositePurchase(String description, ExpenseCategories category) {
		super(ExpenseType.COMPOSITE_PURCHASE, 0.0, "dummy purchase", new Date(), Status.PAID, new Date(), "dummy vendor", category);
		this.description=description;		
		items=new ArrayList<Expense>();
	}

	@Override
	public void add(Expense expense) {
		expense.setParent(this);
		items.add(expense);
		this.setNoOfSubItems(this.getNoOfSubItems()+1);
	}

	@Override
	public boolean remove(Expense expense) {
		if(getIndex(expense)>0) {
			items.remove(expense);
			this.setNoOfSubItems(this.getNoOfSubItems()-1);
			return true;
		}
		
		return false;
	}
	
	private int getIndex(Expense expense){
		return items.indexOf(expense);
	}

	@Override
	public Expense get(int index) {
		return items.get(index);
	}

	@Override
	public void display() {
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense expenseItem = compPurchase.next();
			expenseItem.display();
		}	
	}

	public void setItems(ArrayList<Expense> items) {
		this.items = items;
	}
	
	@Override
	public boolean iseqal(Expense expense) {
		CompositePurchase c = (CompositePurchase)expense;
		if(super.iseqal(expense) && this.getDescription().equals(c.getDescription())) {
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Expense> getPurchasesList() {
		ArrayList<Expense> l_items = new ArrayList<Expense>();
		return getPurchasesList(l_items);
	}
	private ArrayList<Expense> getPurchasesList(ArrayList<Expense> list) {
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense e = compPurchase.next(); 
			list.add(e);
			if(e.getType().ordinal()>1) {
				CompositePurchase ce= (CompositePurchase)e;
				ce.getPurchasesList(list);
			}
		}	

		return list;		
	}

	public Expense getMatchedPurchase(Expense expense) {
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense it = compPurchase.next();
			if (it.iseqal(expense))
				return it;
		}	

		return null;		
	}

	@Override
	public String toString() {
		return getType() + super.toString() + " " + getDescription();
	}
}
