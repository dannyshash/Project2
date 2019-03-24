package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class CompositeBill extends AbstractExpense{
	private RepitionInterval interval;
	private Date dueDate;

	@Deprecated
	public CompositeBill(String description, ExpenseCategories category) {
		super(ExpenseType.COMPOSITE_BILL, 0.0, "dummy comp Bill", new Date(), Status.PAID, new Date(), "dummy vendor", category);
		this.interval=RepitionInterval.MONTHLY;
		this.dueDate = new Date();
		this.description=description;		
		items=new ArrayList<Expense>();
	}
	
	public CompositeBill(double amount, String name, Date date,
			String description, String vendor, Status status, ExpenseCategories category, RepitionInterval interval, Date dueDate) {
		super(ExpenseType.COMPOSITE_BILL, amount, name, date, status, new Date(), vendor, category);
		this.interval=interval;
		this.dueDate = dueDate;
		this.description=description;		
		items=new ArrayList<Expense>();
	}

	public CompositeBill(CompositeBill from) {
		this(from.getAmount(), from.getName(), from.getDate(), from.getDescription(),
				from.getVendor(), from.getStatus(), from.getCategory(), from.getInterval(), from.getDueDate());
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
	public RepitionInterval getInterval() {
		return this.interval;
	}
	
	@Override
	public Date getDueDate() {
		return this.dueDate;
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
	public Expense find(Expense exp) {
		System.out.println("find: key:"+exp.getKey()+", in exp:"+this);
		Expense returnVal = null;
		
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext() && returnVal == null) {
			Expense e = compPurchase.next(); 
			if(e.getType().ordinal()<2 && e.iseqal(exp)) {
				System.out.println("return Expense:"+e);
				returnVal = e;
			}
			
			if(e.getType().ordinal()>1) {
				if(exp.getType().ordinal()>1) {
					if(e.iseqal(exp)) {
						System.out.println("return comp Expense:"+e);
						returnVal = e;
						continue;
					}
				}
				
				CompositeBill ce= (CompositeBill)e;
				returnVal = ce.find(exp);
			}
		}	

		return returnVal;
	}

	@Override
	public boolean iseqal(Expense expense) {
		CompositeBill c = (CompositeBill)expense;
		if(super.iseqal(expense) && this.getDescription().equals(c.getDescription())) {
			return true;
		}
		System.out.println("CompositeBill:iseqal return false");		
		return false;
	}
	
	public ArrayList<Expense> getBillsList() {
		ArrayList<Expense> l_items = new ArrayList<Expense>();
		return getBillsList(l_items);
	}

	private ArrayList<Expense> getBillsList(ArrayList<Expense> list) {
		Iterator<Expense> compPurchase = items.iterator();
		while(compPurchase.hasNext()) {
			Expense e = compPurchase.next(); 
			list.add(e);
			if(e.getType().ordinal()>1) {
				CompositeBill ce= (CompositeBill)e;
				ce.getBillsList(list);
			}
		}	

		return list;		
	}

	@Override
	public void display() {
		System.out.println("Comp Bill:"+this.toString());
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
	public boolean changePaymentStatus(Date date) {
		Status status = Status.PAID;
		if(getStatus() == Status.PAID)
			status = Status.UNPAID;
		this.changePaymentStatus(status, date);
		
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
