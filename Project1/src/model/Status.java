package model;

public enum Status {
	PAID("Paid"),
	UNPAID("Unpaid");
	
	private final String value;
	
	Status(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
