package view;

public enum DisplayColumn {
	NO("No"),
	Expand("Expand"),
	PARENT("Parent"),
	TYPE("Type"), 
	DATE("Date"), 
	NAME("Name"), 
	AMOUNT("Amount"), 
	STATUS("Status"), 
	METHOD("Method"), 
	VENDOR("Vendor"), 
	LOCATION("Location"), 
	CATEGORY("Category"), 
	DUECATE("Due Date"),
	INTERVAL("Interval");
	
	private final String value;
	
	DisplayColumn(String value) {
		this.value= value;
	}
	
	public static final DisplayColumn values[] = values();
	
	@Override
	public String toString() {
		return value;
	}

}
