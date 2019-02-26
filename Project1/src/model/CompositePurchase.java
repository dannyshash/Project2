package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CompositePurchase extends AbstractExpense{
	private String description; //primary key element
	private ArrayList<Expense> items;
	
	public CompositePurchase(String description) {
		super(ExpenseType.COMPOSITE_PURCHASE, 0.0, "dummy purchase", new Date(), Status.PAID, new Date(), "dummy vendor");
		this.description=description;
		
		items=new ArrayList<Expense>();
	}

	@Override
	public void add(Expense expense) {
		items.add(expense);
	}

	@Override
	public void remove(Expense expense) {
		items.remove(expense);
	}

	@Override
	public Expense get(int index) {
		return items.get(index);
	}

	@Override
	public void display() {
		System.out.println(this.toString());
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense expenseItem = compPurchase.next();
			expenseItem.display();
		}	
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			l_items.add(compPurchase.next());
		}	

		return l_items;		
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

	public String toString() {
		return getType().getValue() + super.toString() + " " + getDescription();
	}
}
