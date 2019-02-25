package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CompositeBill extends AbstractExpense{
	private final String description;
	private ArrayList<Expense> items;


	public CompositeBill(String description) {
		super(ExpenseType.COMPOSITE_BILL, 0.0, "dummy purchase", new Date(), Status.PAID, new Date(), "dummy vendor");
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

	public void setItems(ArrayList<Expense> items) {
		this.items = items;
	}
	
	public String toString() {
		return getType().getValue() + super.toString() + " " + getDescription();
	}

}
