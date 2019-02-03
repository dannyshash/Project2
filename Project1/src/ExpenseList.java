/**
 * Description: The ExpenseList is an ArrayList where Expense class will be loaded unto¡£¡£¡£.
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */

import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class ExpenseList {

	private ArrayList<Expense> expenseList;
	
	public ExpenseList() {
		expenseList = new ArrayList<Expense>();
	}
	
	public void add(Expense pl) {
		expenseList.add(pl);
	}
	
	public void remove(int index) {
		expenseList.remove(index);
	}
	
	public void markPaidUnpaid(int index) {
		expenseList.get(index).setStatus();
	}

	public ArrayList<Expense> getList() {
		return expenseList;
	}
	
	public int getSize() {
		return expenseList.size();
	}
	
}
