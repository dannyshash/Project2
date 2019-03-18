package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CompositePurchase extends AbstractExpense{
	private Mode mode;
	private String location;
	
	public CompositePurchase(String description, ExpenseCategories category, Mode mode, String location) {
		super(ExpenseType.COMPOSITE_PURCHASE, 0.0, "dummy comp purchase", new Date(), Status.PAID, new Date(), "dummy vendor", category);
		this.mode = mode;
		this.location = location;
		this.description=description;		
		items=new ArrayList<Expense>();
	}

	public CompositePurchase(double amount, String name, Date date,
			String description, ExpenseCategories category, Status status, Mode mode, String location) {
		super(ExpenseType.COMPOSITE_PURCHASE, amount, name, date, status, new Date(), "dummy vendor", category);
		this.mode = mode;
		this.location = location;
		this.description=description;		
		items=new ArrayList<Expense>();
	}

	public CompositePurchase(CompositePurchase from) {
		this(from.getAmount(), from.getName(), from.getDate(), from.getDescription(),
				from.getCategory(), from.getStatus(), from.getMode(), from.getLocation());
		Iterator<Expense> it = from.getSubItems().iterator();
		while(it.hasNext()) {
			Expense e = it.next();
			this.items.add(e);
		}	
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
	public Mode getMode() {
		return this.mode;
	}
	
	@Override
	public String getLocation() {
		return this.location;
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
	public Expense get(int index) {
		return items.get(index);
	}

	@Override
	public ArrayList<Expense> getSubItems() {
		return items;
	}
	
	@Override
	public Expense find(Expense exp) {
		System.out.println("find: key:"+exp.getKey()+", in exp:"+this);
		Expense returnVal = null;
		
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense e = compPurchase.next(); 
			System.out.println("find: looping:"+e);
			if(e.getType().ordinal()<2 && e.iseqal(exp)) {
				System.out.println("return Expense:"+e);
				return e;
			}
			
			if(e.getType().ordinal()>1) {
				if(exp.getType().ordinal()>1) {
					if(e.iseqal(exp)) {
						System.out.println("return comp Expense:" + e);
						return e;										
					}
				}
				CompositePurchase ce= (CompositePurchase)e;
				return ce.find(exp);
			}
		}
		
		return returnVal;
	}
	
	@Override
	public void display() {
		System.out.println("Comp Purchase:"+this.toString());
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense expenseItem = compPurchase.next();
			expenseItem.display();
		}	
	}

	public void setItems(ArrayList<Expense> items) {
		this.items = items;
		this.setNoOfSubItems(items.size());
	}
	
	@Override
	public boolean iseqal(Expense expense) {
		CompositePurchase c = (CompositePurchase)expense;
		if(super.iseqal(expense) && this.getDescription().equals(c.getDescription())) {
			return true;
		}
		System.out.println("CompositePurchase:iseqal return false");
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

	@Override
	public boolean changePaymentStatus(Date date) {
		Status lstatus = Status.PAID;
		if(getStatus() == Status.PAID)
			lstatus = Status.UNPAID;
		this.changePaymentStatus(lstatus, date);
		
		return true;
	}
	
	@Override
	public void changePaymentStatus(Status status, Date date) {
		//set status for himself
		setStatus(status); 
		//set status for children
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense expenseItem = compPurchase.next();
			expenseItem.changePaymentStatus(status, date);
		}	
	}
	
	@Override
	public String toString() {
		return getType() + super.toString() + " " + getDescription();
	}
}
