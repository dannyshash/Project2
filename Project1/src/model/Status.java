package model;

public enum Status {
	PAID("Paid"),
	UNPAID("Unpaid");
	
	private String value;
	
	Status(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
