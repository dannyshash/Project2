/**
 * Description: The ExpenseList is an ArrayList where Expense class will be loaded unto������.
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package misc;

import java.util.ArrayList;
import java.util.Date;

import model.*;

@Deprecated
public class ExpenseList {

	private ArrayList<Expense> expenseList;
	
	private ExpenseList() {
		expenseList = new ArrayList<Expense>();
	}
	
	public void add(Expense pl) {
		expenseList.add(pl);
	}
	
	public void remove(int index) {
		expenseList.remove(index);
	}
	
	public void markPaidUnpaid(int index) {
		expenseList.get(index).changePaymentStatus(new Date());
	}

	public ArrayList<Expense> getList() {
		return expenseList;
	}
	
	public int getSize() {
		return expenseList.size();
	}
	
	public String getType(int index) {
		return expenseList.get(index).getType().toString();
	}
	
}
