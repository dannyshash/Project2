package model;

public enum RepitionInterval {
	WEEKLY("Weekly"),
	BIWEEKLY("BiWeekly"),
	MONTHLY("Monthly"),
	YEARLY("Yearly");
	
	private String value;
	
	RepitionInterval(String value) {
		this.value= value;
	}
	
	public String getValue() {
		return value;
	}
}
