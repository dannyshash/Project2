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
		if(items.size()>0) {
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
	
	@Override
	public Expense get(int index) {
		return items.get(index);
	}

	@Override
	public Expense get(Expense expense) {
		Iterator<Expense> it = items.iterator();
		while(it.hasNext()) {
			Expense e = it.next();
			//if (it.iseqal(expense))
			if (e.getKey() == expense.getKey())
				return e;
		}	
		
		return null;
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
