/**
 * Description: The ExpenseListTableModel uses the ExpenseList to create a custom AbstractTableModel. 
 * It includes mandatory methods such as getColumnName, getColumnClass, getColumnCount, getRowCount.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package misc;

import javax.swing.table.AbstractTableModel;

import model.Expense;
import model.ExpenseType;
import model.Purchase;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class ExpenseListTableModel extends AbstractTableModel {

	private String[] columnNames = { "Type", "Date", "Name", "Amount", "Status", "Method", "Vendor", "Location", "Category", "Due Date", "Interval" };
	private ArrayList<Expense> myList;
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
		if (col == 0) {//Type
			temp = myList.get(row).getType().getValue();
		} else if (col == 1) {//Date
			temp = dateformat.format(myList.get(row).getDate());
		} else if (col == 2) {//Name
			temp = myList.get(row).getName();
		} else if (col == 3) {//Amount
			temp = myList.get(row).getAmount();
		} else if (col == 4) {//Status
			temp = myList.get(row).getStatus().getValue();
		} else if (col == 5) {//Method
			if(myList.get(row).getType()==ExpenseType.PURCHASE ||
					myList.get(row).getType()==ExpenseType.COMPOSITE_PURCHASE){
				temp = ((Purchase)myList.get(row)).getMode().getValue();
			}
			else if(myList.get(row).getType()==ExpenseType.BILL ||
					myList.get(row).getType()==ExpenseType.COMPOSITE_BILL){
				temp = "";
			}
			else {
				throw new RuntimeException("Invalid method");
			}
		} else if (col == 6) {//Vendor
			temp = myList.get(row).getVendor();
		} else if (col == 7) {//Location
			if(myList.get(row).getType()==ExpenseType.PURCHASE ||
					myList.get(row).getType()==ExpenseType.COMPOSITE_PURCHASE){
				temp = ((Purchase)myList.get(row)).getLocation();
			}
			else if(myList.get(row).getType()==ExpenseType.BILL ||
					myList.get(row).getType()==ExpenseType.COMPOSITE_BILL){
				temp = "";
			}
			else {
				throw new RuntimeException("Invalid method");
			}
		} else if (col == 8) {//Category
			temp = myList.get(row).getCategory().getValue();
		} else if (col == 9) {//Due Date
			temp = dateformat.format(myList.get(row).getPaymentDate());
		} else if (col == 10) {//Interval
			if(myList.get(row).getType()==ExpenseType.PURCHASE ||
					myList.get(row).getType()==ExpenseType.COMPOSITE_PURCHASE){
				temp = "";
			}
			else if(myList.get(row).getType()==ExpenseType.BILL ||
					myList.get(row).getType()==ExpenseType.COMPOSITE_BILL){
				temp = myList.get(row).getInterval().getValue();
			}
			else {
				throw new RuntimeException("Invalid method");
			}
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
