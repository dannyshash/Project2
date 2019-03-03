package model;

public enum Mode {
	CASH("Cash"),
	DEBIT("Debit"),
	CREDIT("Credit");
	
	private final String value;
	
	Mode(String value) {
		this.value= value;
	}
	
	public String getValue() {
		return value;
	}
}
