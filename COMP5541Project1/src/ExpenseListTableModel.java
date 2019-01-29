/**
 * Description: The ExpenseListTableModel uses the ExpenseList to create a custom AbstractTableModel. 
 * It includes mandatory methods such as getColumnName, getColumnClass, getColumnCount, getRowCount.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ExpenseListTableModel extends AbstractTableModel {

	private String[] columnNames = { "Type", "Date", "Name", "Amount", "Status", "Location", "Method", "Due Date", "Interval" };
	private ArrayList<Purchase> myList;
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	public ExpenseListTableModel(ExpenseList pList) {
		myList = pList.getList();
	}
	
	/*
    public void addExpense(Purchase p) {
        // Adds the element in the last position in the list
        myList.add(p);
        fireTableDataChanged();
        fireTableRowsInserted(myList.size()-1, myList.size()-1);
    }
    */
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		int size;
		if (myList == null) {
			size = 0;
		} else {
			size = myList.size();
		}
		return size;
	}


	public Object getValueAt(int row, int col) {
		Object temp = null;
		if (col == 0) {
			temp = myList.get(row).getType();
		} else if (col == 1) {
			temp = dateformat.format(myList.get(row).getDate());
		} else if (col == 2) {
			temp = myList.get(row).getName();
		} else if (col == 3) {
			temp = myList.get(row).getAmount();
		} else if (col == 4) {
			temp = myList.get(row).getStatus();
		} else if (col == 5) {
			temp = myList.get(row).getLocation();
		} else if (col == 6) {
			temp = myList.get(row).getMethod();
		} else if (col == 7) {
			temp = dateformat.format(myList.get(row).getDueDate());
		} else if (col == 8) {
			temp = myList.get(row).getInterval();
		}
		return temp;
	}


	public String getColumnName(int col) {
		return columnNames[col];
	}

	
	public Class getColumnClass(int col) {
		return String.class;
	}

}
