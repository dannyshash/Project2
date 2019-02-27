/**
 * Description: The ExpenseListTableModel uses the ExpenseList to create a custom AbstractTableModel. 
 * It includes mandatory methods such as getColumnName, getColumnClass, getColumnCount, getRowCount.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package misc;

import javax.swing.table.AbstractTableModel;

import model.AbstractExpense;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ExpenseListTableModel extends AbstractTableModel {

	private String[] columnNames = { "Type", "Date", "Name", "Amount", "Status", "Category", "Category Name", "Location", "Method", "Due Date", "Interval" };
	private ArrayList<AbstractExpense> myList;
	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	public ExpenseListTableModel(ExpenseList pList) {
		myList = pList.getList();
	}
	
	
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
			temp = myList.get(row).getType().getValue();
		} else if (col == 1) {
			temp = dateformat.format(myList.get(row).getDate());
		} else if (col == 2) {
			temp = myList.get(row).getName();
		} else if (col == 3) {
			temp = myList.get(row).getAmount();
		} else if (col == 4) {
			temp = myList.get(row).getStatus().getValue();
		} else if (col == 5) {
			temp = "N/A";//myList.get(row).getCategory();
		} else if (col == 6) {
			temp = "N/A";//myList.get(row).getCategoryName();
		} else if (col == 7) {
			temp = myList.get(row).getVendor();
		} else if (col == 8) {
			temp = "Should_Fix_In_View!";//TODO: myList.get(row).getMethod();
		} else if (col == 9) {
			temp = dateformat.format(myList.get(row).getPaymentDate());
		} else if (col == 10) {
			temp = "Should_Fix_In_View!";//TODO: myList.get(row).getInterval();
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
