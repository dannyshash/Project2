/**
 * Description: The ExpenseList is an ArrayList where Expense class will be loaded unto¡£¡£¡£.
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */
package misc;

import java.util.ArrayList;
import model.*;

public class ExpenseList {

	private ArrayList<AbstractExpense> expenseList;
	
	public ExpenseList() {
		expenseList = new ArrayList<AbstractExpense>();
	}
	
	public void add(AbstractExpense pl) {
		expenseList.add(pl);
	}
	
	public void remove(int index) {
		expenseList.remove(index);
	}
	
	public void markPaidUnpaid(int index) {
		expenseList.get(index).changePaymentStatus();
	}

	public ArrayList<AbstractExpense> getList() {
		return expenseList;
	}
	
	public int getSize() {
		return expenseList.size();
	}
	
}
