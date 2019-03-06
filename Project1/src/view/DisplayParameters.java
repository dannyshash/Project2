package view;

import java.util.Date;

import model.ExpenseType;
import model.RepitionInterval;

/**
 * information about what's being displayed in the report
 *
 */
public class DisplayParameters {
	public ExpenseType type;
	public Date time;
	public RepitionInterval period;
}
