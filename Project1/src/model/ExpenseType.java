package model;

public enum ExpenseType {
	PURCHASE("Purchase"),
	BILL("Bill"),
	COMPOSITE_PURCHASE("Composite_Purchase"),
	COMPOSITE_BILL("Composite_BILL"),
	INVALID("Invalid");

	private final String value;
	
	ExpenseType(String val) {
		this.value = val;
	}
	
	public String getValue() {
		return value;
	}
}
