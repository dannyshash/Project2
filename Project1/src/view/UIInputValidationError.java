package view;

/**
 * Error codes for the UI input validation 
 *
 */
public enum UIInputValidationError {
	VALID("Valid input, no error"),
	AMOUNT("Invalid Amount"),
	NAME("Invalid expense name"),
	DATE_FORMAT("Invalid date format"),
	INVALID_MONTH("Invalid date, not in current month"),
	INVALID_PERIOD("Invalid date, not in the selected time period"),
	PAST_DUE("Invalid date, past due date"); //Maybe just warning?
	
	private String value;
	
	UIInputValidationError(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
