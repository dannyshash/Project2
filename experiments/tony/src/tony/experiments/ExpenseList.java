package tony.experiments;

/**
 * Description: The ExpenseList is an ArrayList where Expense class will be loaded unto¡£¡£¡£.
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */

import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class ExpenseList {

	private ArrayList<Purchase> purchaseList;
	
	public ExpenseList() {
		purchaseList = new ArrayList<Purchase>();
	}
	
	public void add(Purchase pl) {
		purchaseList.add(pl);
	}
	
	public void remove(int index) {
		purchaseList.remove(index);
	}
	
	public void markPaidUnpaid(int index) {
		purchaseList.get(index).setStatus();
	}

	public ArrayList<Purchase> getList() {
		return purchaseList;
	}
	
	public int getSize() {
		return purchaseList.size();
	}
	
}

