package model;

public enum RepitionInterval {
	DAILY("Daily"),
	WEEKLY("Weekly"),
	BIWEEKLY("BiWeekly"),
	MONTHLY("Monthly"),
	QUARTERLY("Quarterly"),
	YEARLY("Yearly");
	
	private String value;
	
	RepitionInterval(String value) {
		this.value= value;
	}
	
	public String getValue() {
		return value;
	}
}
