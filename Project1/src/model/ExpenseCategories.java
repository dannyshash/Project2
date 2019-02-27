package model;

public enum ExpenseCategories {
	DAFAULT("Default"),
	DINING("Dining"),
	GROCERY("Grocery"),
	GAS("Gas"),
	PARKING("Parking"),
	TRANSPORTATION("Transportation"),
	TAXI("Taxi"),
	MORTGAGE("Mortgage"),
	SAVINGS("Savings"),
	INSURANCE("Insurance"),
	UTILITIES("Utilities"),
	WIRELESS("Wireless"),
	HEALTH("Health"),
	CABLE("Cable"),
	INTERNET("Internet"),
	AUTO("Automobile"),
	ENTERTAINMENT("Entertainment");
	
	private String value;
	
	ExpenseCategories(String value) {
		this.value= value;
	}
	
	public String getValue() {
		return value;
	}

}
