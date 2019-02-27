/**
 * This is a temporary class
 * Once we adopt the View according to the new model, this class should be removed
 * View should directly use Expense interface
 */
package misc;

import java.util.Date;

import model.ExpenseCategories;
import model.Mode;
import model.Purchase;
import model.Status;

public class PurchaseAdaptor extends Purchase {
	
	public PurchaseAdaptor(Date date, String name, Double amount, Status status, ExpenseCategories category, 
			String vendor, String location, Mode method, Date duedate) {
		super(amount, name, date, status, duedate, vendor, location, method, category);
	}
	
	@Override
	public void display() {
		//TODO Auto-generated method stub
		super.display();
	}
	
	public String getStatusByString() {
		return super.getStatus().getValue();
	}

	public String getMethodByString() {
		return super.getMode().getValue();
	}

}
