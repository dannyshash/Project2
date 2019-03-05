package model;

public enum ExpenseType {
	PURCHASE("Purchase"),
	BILL("Bill"),
	COMPOSITE_PURCHASE("Composite_Purchase"),
	COMPOSITE_BILL("Composite_Bill"),
	INVALID("Invalid");

	private final String value;
	
	ExpenseType(String val) {
		this.value = val;
	}

	@Override
	public String toString() {
		return value;
	}
}
