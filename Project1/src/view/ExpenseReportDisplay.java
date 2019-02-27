package view;

import java.util.ArrayList;

import model.Expense;

/**
 * Interface for the viewer to browse through list
 *
 */
public interface ExpenseReportDisplay {
	public ArrayList<Expense> getNextPage();
	
	public DisplayParameters getContext();
	//sets the index to the current page being displayed
	public boolean setOffset(int index);
	//set the no of rows displayed per page
	public void setPageSize();
	//reset the offset, when data state is changed 
	public void resetOffset();
	//tells to refresh the view/report, data state is changed
	public boolean shouldReload();
}
