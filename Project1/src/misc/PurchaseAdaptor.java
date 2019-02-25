/**
 * This is a temporary class
 * Once we adopt the View according to the new model, this class should be removed
 * View should directly use Expense interface
 */
package misc;

import java.util.Date;

import model.Mode;
import model.Purchase;
import model.Status;

public class PurchaseAdaptor extends Purchase {
	private String category;
	private String categoryName;
	
	public PurchaseAdaptor(Date date, String name, double amount, String status, String category, 
			String categoryname, String location, String method, Date duedate) {
		super(amount, name, date, Status.PAID, duedate, location, "dummy location", Mode.CASH);
		for(Status s : Status.values()) {
			if(s.getValue() == status)
				super.setStatus(s);
	    }		
		for(Mode m : Mode.values()) {
			if(m.getValue() == method)
				super.setMode(m);
	    }		
		this.category = category;
		this.categoryName = categoryname;
		
	}
	

	public String getStatusByString() {
		return super.getStatus().getValue();
	}

	public String getMethodByString() {
		return super.getMode().getValue();
	}

	public String getCategory() {
		return category;
	}
	public String getCategoryName() {
		return categoryName;
	}

}
