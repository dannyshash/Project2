package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CompositeBill extends AbstractExpense{
	private RepitionInterval interval;

	public CompositeBill(String description, ExpenseCategories category) {
		super(ExpenseType.COMPOSITE_BILL, 0.0, "dummy purchase", new Date(), Status.PAID, new Date(), "dummy vendor", category);
		this.interval=RepitionInterval.MONTHLY;
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
	
	@Override
	public RepitionInterval getInterval() {
		return this.interval;
	}
	
	private int getIndex(Expense expense){
		return items.indexOf(expense);
	}

	@Override
	public Expense get(int index) {
		return items.get(index);
	}

	@Override
	public boolean iseqal(Expense expense) {
		CompositeBill c = (CompositeBill)expense;
		if(super.iseqal(expense) && this.getDescription().equals(c.getDescription())) {
			return true;
		}
		
		return false;
	}
	
	public ArrayList<Expense> getBillsList() {
		ArrayList<Expense> l_items = new ArrayList<Expense>();
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			l_items.add(compPurchase.next());
		}	

		return l_items;		
	}

	public Expense getMatchedBill(Expense expense) {
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense it = compPurchase.next();
			if (it.iseqal(expense))
				return it;
		}	

		return null;		
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
	public String toString() {
		return getType() + super.toString() + " " + getDescription();
	}

}
