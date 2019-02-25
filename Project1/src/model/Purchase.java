package model;

import java.util.Date;

public class Purchase extends AbstractExpense {
	private String location;
	private Mode mode;
	
	public Purchase(double amount, String name, Date date, Status status, Date dueDate, String vendor, String location, Mode mode) {
		super(ExpenseType.PURCHASE, amount, name, date, status, dueDate, vendor);
		this.setLocation(location);
		this.mode=mode;
	}
	

	public Purchase(double amount, String name, Date date, String vendor) {
		this(amount, name, date, Status.PAID, date, vendor, "dummy location", Mode.CASH);
	}

	public Purchase() {
		//Calendar.getInstance().setTime(new Date())
		this(0.0, "dummy purchase", new Date(), "dummy vendor"); 
	}


	@Override
	public void display() {
		this.toString();
		
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Mode getMode() {
		return mode;
	}


	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public String toString() {
		return getType().getValue() + super.toString() + " " + getLocation() + " " + getMode().getValue();
	}

}
