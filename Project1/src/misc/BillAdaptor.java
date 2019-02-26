/**
 * This is a temporary class
 * Once we adopt the View according to the new model, this class should be removed
 * View should directly use Expense interface
 */
package misc;

import java.util.Date;

import model.Bill;
import model.RepitionInterval;
import model.Status;

public class BillAdaptor extends Bill {
	String method;
	
	public BillAdaptor(Date date, String name, double amount, String status, String category, String categoryname, String location, String method,
			Date duedate, String interval) {
		super(amount, name, date, Status.PAID, duedate, location, RepitionInterval.MONTHLY);
		for(Status s : Status.values()) {
			if(s.getValue() == status)
				super.setStatus(s);
	    }		
		for(RepitionInterval r : RepitionInterval.values()) {
			if(r.getValue() == interval)
				super.setInterval(r);
	    }	
		this.method = method;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
	}
	
	public String getMethod() {
		return method;
	}
}
