package model;

import java.util.Date;

public class Bill extends AbstractExpense {
	private RepitionInterval interval;
	private Date dueDate;
	
	public Bill(double amount, String name, Date date, Status status, Date dueDate, String vendor, 
			RepitionInterval interval, ExpenseCategories category) {
		super(ExpenseType.BILL, amount, name, date, status, dueDate, vendor, category);
		this.interval = interval;
		this.dueDate = dueDate;
	}

	public Bill(double amount, String name, Date date) {
		super(ExpenseType.BILL, amount, name, date);
		this.interval = RepitionInterval.MONTHLY;
	}

	public Bill() {
		this(0.0, "dummy bill", new Date()); 
	}
	
	//Copy constructor
	public Bill(Bill from) {
		this(from.getAmount(), from.getName(), from.getDate(), from.getStatus(), from.getDueDate(),
				from.getVendor(), from.getInterval(), from.getCategory());	
	}
	
	//Assignment=

	@Override
	public void display() {
		System.out.println(this.toString());
	}

	@Override
	public RepitionInterval getInterval() {
		return interval;
	}

	public void setInterval(RepitionInterval interval) {
		this.interval = interval;
	}
	
	public String toString() {
		return getType()+":"+ super.toString() + " " + getInterval();
	}

	@Override
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

}
