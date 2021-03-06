package model;

import java.util.Date;

public class Purchase extends AbstractExpense {
	private String location;
	private Mode mode;
	
	public Purchase(double amount, String name, Date date, Status status, 
			String vendor, String location, Mode mode, ExpenseCategories category) {
		super(ExpenseType.PURCHASE, amount, name, date, status, new Date(), vendor, category);
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
	public Purchase(Purchase from) {
		this(from.getAmount(), from.getName(), from.getDate(), from.getStatus(),
				from.getVendor(), from.getLocation(), from.getMode(), from.getCategory());	
	}

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
