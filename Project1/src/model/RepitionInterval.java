package model;

public enum RepitionInterval {
	DAILY("Daily"),
	WEEKLY("Weekly"),
	BIWEEKLY("BiWeekly"),
	MONTHLY("Monthly"),
	QUARTERLY("Quarterly"),
	YEARLY("Yearly");
	
	private final String value;
	
	RepitionInterval(String value) {
		this.value= value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
