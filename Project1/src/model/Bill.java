package model;

import java.util.Date;

public class Bill extends AbstractExpense {
	private RepitionInterval interval;
	
	public Bill(double amount, String name, Date date, Status status, Date dueDate, String vendor, RepitionInterval interval) {
		super(ExpenseType.BILL, amount, name, date, status, dueDate, vendor);
		this.interval = interval;
	}

	public Bill(double amount, String name, Date date, String vendor) {
		this(amount, name, date, Status.UNPAID, date, vendor, RepitionInterval.MONTHLY);
	}

	public Bill() {
		this(0.0, "dummy bill", new Date(), "dummy vendor"); 
	}

	@Override
	public void display() {
		this.toString();
		
	}

	public RepitionInterval getInterval() {
		return interval;
	}

	public void setInterval(RepitionInterval interval) {
		this.interval = interval;
	}
	
	public String toString() {
		return getType().getValue() + super.toString() + " " + getInterval().getValue();
	}

}
