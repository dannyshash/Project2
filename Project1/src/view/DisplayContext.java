package view;

import java.util.Date;

import model.ExpenseType;
import model.RepitionInterval;

/**
 * information about what's being displayed in the report
 *
 */
class DisplayContext {
	public ExpenseType type;
	public Date time;
	public RepitionInterval period;
	public int currentIndex;
	public int totalItems;
	public int rowsPerDisplayPage;
	public int totalRows;
}
