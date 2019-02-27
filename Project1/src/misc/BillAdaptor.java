/**
 * This is a temporary class
 * Once we adopt the View according to the new model, this class should be removed
 * View should directly use Expense interface
 */
package misc;

import java.util.Date;

import model.Bill;
import model.ExpenseCategories;
import model.RepitionInterval;
import model.Status;
import model.Mode;

public class BillAdaptor extends Bill {
	
	public BillAdaptor(Date date, String name, Double amount, Status status, ExpenseCategories category, String vendor, String location, Mode method,
			Date duedate, RepitionInterval interval) {
		super(amount, name, date, status, duedate, vendor, interval, category);
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
	}
	
}
