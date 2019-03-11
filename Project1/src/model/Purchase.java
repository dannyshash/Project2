package model;

import java.util.Date;

public class Purchase extends AbstractExpense {
	private String location;
	private Mode mode;
	
	public Purchase(double amount, String name, Date date, Status status, Date dueDate, String vendor, String location, Mode mode, ExpenseCategories category) {
		super(ExpenseType.PURCHASE, amount, name, date, status, dueDate, vendor, category);
		this.setLocation(location);
		this.mode=mode;
	}
	

	public Purchase(double amount, String name, Date date) {
		super(ExpenseType.PURCHASE, amount, name, date);
		this.location = "dummy location";
		this.mode = Mode.CASH;
	}

	public Purchase() {
		//Calendar.getInstance().setTime(new Date())
		this(0.0, "dummy purchase", new Date()); 
	}
	
	//Copy constructor
	Purchase(Purchase from) {
		this(from.getAmount(), from.getName(), from.getDate());	
	}

	//Assignment=
			
	@Override
	public void display() {
		System.out.println(this.toString());
	}

	@Override
	public boolean changePaymentStatus(Date date) {
		if(super.changePaymentStatus(date)) {
			//check
			this.setMode(Mode.CASH);
			return true;
		}
		
		return false;
	}
	
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public Mode getMode() {
		return this.mode;
	}


	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public String toString() {
		return getType()+":"+ super.toString() + " " + getLocation() + " " + getMode();
	}

}
