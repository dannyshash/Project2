/**
 * Description: The ExpenseListTableModel uses the ExpenseList to create a custom AbstractTableModel. 
 * It includes mandatory methods such as getColumnName, getColumnClass, getColumnCount, getRowCount.
 * 
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package misc;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.CompositeBill;
import model.CompositePurchase;
import model.Expense;
import model.ExpenseType;
import model.Purchase;
import utils.MyDate;
import view.DisplayColumn;
import view.DisplayExpense;
import view.ExpenseContentApi;

import java.util.ArrayList;
import java.util.Iterator;
import java.text.SimpleDateFormat;

public class ExpenseListTableModel extends AbstractTableModel {

	private ArrayList<DisplayExpense> myDisplayExenseList = new ArrayList<DisplayExpense>();
	private ExpenseContentApi expenseContent;
	private ExpenseType type = ExpenseType.PURCHASE;
	
	public ExpenseListTableModel(ExpenseContentApi expenseContent) {
		this.expenseContent = expenseContent;
	}
	
	void createDisplayExenseList(ArrayList<Expense> expData) {
		Iterator<Expense> it = expData.iterator();
		while(it.hasNext()) {
			Expense expense = it.next();
			myDisplayExenseList.add(DisplayExpense.copy(expense));			
		}	
	}
	
	ArrayList<DisplayExpense> getDisplayExenseList(ArrayList<Expense> expData) {
		ArrayList<DisplayExpense> list = new ArrayList<DisplayExpense>();
		Iterator<Expense> it = expData.iterator();
		while(it.hasNext()) {
			Expense expense = it.next();
			DisplayExpense de = DisplayExpense.copy(expense);
			if(expense.getType().ordinal()>1)
				de.expand = "-";
			list.add(de);			
		}		
		return list;
	}
	
	public void refresh() {
		refresh(type);
	}
	
	public void refresh(ExpenseType type) {
		myDisplayExenseList.clear();
		this.type = type;
		if(type == ExpenseType.PURCHASE) {
			createDisplayExenseList(expenseContent.getAllPurchases());
		}
		else if(type == ExpenseType.BILL){
			createDisplayExenseList(expenseContent.getAllBills());
		}
		else {
			throw new RuntimeException("Data error");
		}
		this.fireTableDataChanged();
	}
	
	public int getColumnCount() {
		return DisplayColumn.values().length;
	}

	public int getRowCount() {
		int size = 0;
		if(myDisplayExenseList != null) {
			size = myDisplayExenseList.size();
		}

		return size;
	}

	void expandComposite(Expense expense, int selectedRow) {
		System.out.println("## expandComposite ##"+expense.toString()+"items="+expense.getNoOfSubItems());
		/* this will only show the immediate children
		for(int i=0;i<expense.getNoOfSubItems();i++) {
			myList.add(selectedRow+1, expense.getSubItems().get(i));
		}
		*/
		//myList.addAll(selectedRow+1, ((CompositePurchase)expense).getPurchasesList());
		ArrayList<Expense> expItems; 
		if(expense.getType()==ExpenseType.COMPOSITE_PURCHASE)
			expItems = ((CompositePurchase)expense).getPurchasesList();
		else if(expense.getType()==ExpenseType.COMPOSITE_BILL)
			expItems = ((CompositeBill)expense).getBillsList();
		else
			throw new RuntimeException("expandComposite: invalid expense type");		
			
		myDisplayExenseList.addAll(selectedRow+1, getDisplayExenseList(expItems));
		setValueAt("-", selectedRow, DisplayColumn.Expand.ordinal());
		this.fireTableDataChanged();		
		//setValueAt("-", selectedRow, DisplayColumn.Expand.ordinal());
		//this.fireTableCellUpdated(selectedRow, DisplayColumn.Expand.ordinal());;		
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//System.out.println("#### setValueAt row:"+rowIndex+",col="+columnIndex+",val="+(String)aValue);
		DisplayExpense dExp = myDisplayExenseList.get(rowIndex);
		
		if (columnIndex == DisplayColumn.NO.ordinal()) {//No
			dExp.no = (String)aValue;
		} else if (columnIndex == DisplayColumn.Expand.ordinal()) {//Expand
			dExp.expand = (String)aValue;
		} else if (columnIndex == DisplayColumn.PARENT.ordinal()) {//Parent
			dExp.parent = (String)aValue;
		} else if (columnIndex == DisplayColumn.TYPE.ordinal()) {//Type
			dExp.type = (String)aValue;
		} else if (columnIndex == DisplayColumn.DATE.ordinal()) {//Date
			dExp.date = (String)aValue;
		} else if (columnIndex == DisplayColumn.NAME.ordinal()) {//Name
			dExp.name = (String)aValue;
		} else if (columnIndex == DisplayColumn.AMOUNT.ordinal()) {//Amount
			dExp.amount = (String)aValue;
		} else if (columnIndex == DisplayColumn.STATUS.ordinal()) {//Status
			dExp.status = (String)aValue;
		} else if (columnIndex == DisplayColumn.METHOD.ordinal()) {//Method
			dExp.method = (String)aValue;
		} else if (columnIndex == DisplayColumn.VENDOR.ordinal()) {//Vendor
			dExp.vendor = (String)aValue;
		} else if (columnIndex == DisplayColumn.LOCATION.ordinal()) {//Location
			dExp.location = (String)aValue;
		} else if (columnIndex == DisplayColumn.CATEGORY.ordinal()) {//Category
			dExp.category = (String)aValue;
		} else if (columnIndex == DisplayColumn.DUECATE.ordinal()) {//Due Date
			dExp.dueDate = (String)aValue;
		} else if (columnIndex == DisplayColumn.INTERVAL.ordinal()) {//Interval
			dExp.interval = (String)aValue;
		} else {
			throw new RuntimeException("setValueAt error");
		}
	}
	
	public Object getValueAt(int row, int col) {
		DisplayExpense dExp = myDisplayExenseList.get(row);

		if (col == DisplayColumn.NO.ordinal()) {//No
			setValueAt(new Integer(row+1).toString(), row, col);
			return dExp.no;
		} else if (col == DisplayColumn.Expand.ordinal()) {//Expand
			return dExp.expand;
		} else if (col == DisplayColumn.PARENT.ordinal()) {//Parent
			return dExp.parent;
		} else if (col == DisplayColumn.TYPE.ordinal()) {//Type
			return dExp.type;
		} else if (col == DisplayColumn.DATE.ordinal()) {//Date
			return dExp.date;
		} else if (col == DisplayColumn.NAME.ordinal()) {//Name
			return dExp.name;
		} else if (col == DisplayColumn.AMOUNT.ordinal()) {//Amount
			return dExp.amount;
		} else if (col == DisplayColumn.STATUS.ordinal()) {//Status
			return dExp.status;
		} else if (col == DisplayColumn.METHOD.ordinal()) {//Method
			return dExp.method;
		} else if (col == DisplayColumn.VENDOR.ordinal()) {//Vendor
			return dExp.vendor;
		} else if (col == DisplayColumn.LOCATION.ordinal()) {//Location
			return dExp.location;
		} else if (col == DisplayColumn.CATEGORY.ordinal()) {//Category
			return dExp.category;
		} else if (col == DisplayColumn.DUECATE.ordinal()) {//Due Date
			return dExp.dueDate;
		} else if (col == DisplayColumn.INTERVAL.ordinal()) {//Interval
			return dExp.interval;
		} else {
			throw new RuntimeException("getValueAt error");
		}
	}


	public String getColumnName(int col) {
		return DisplayColumn.values[col].toString();
	}

}
