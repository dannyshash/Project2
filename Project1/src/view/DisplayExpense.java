package view;

import java.text.SimpleDateFormat;

import model.Bill;
import model.CompositeBill;
import model.Expense;
import model.ExpenseType;
import utils.MyDate;

public class DisplayExpense {
	private Expense expRef;
	public String no;
	public String expand;
	public String parent;
	public String type; 
	public String date; 
	public String name; 
	public String amount; 
	public String status; 
	public String method; 
	public String vendor; 
	public String location; 
	public String category; 
	public String dueDate;
	public String interval;
	
	public DisplayExpense() {
		setExpRef(null);
	}
	
	public DisplayExpense copy(Expense exp) {

		this.setExpRef(exp);
		this.no = "0";
		this.expand = "-";
		{
			if(exp.getType().ordinal()>1)
				this.expand = "+";
		}
		{
			this.parent = "";
			if(exp.getParent() !=null) {
				StringBuilder buf = new StringBuilder();
				SimpleDateFormat date_format = new SimpleDateFormat("dd-MM");

				buf = buf.append(exp.getType().ordinal());
				buf = buf.append(":");
				buf = buf.append(exp.getParent().getAmount());
				buf = buf.append(":");
				buf = buf.append(exp.getParent().getName());
				buf = buf.append(":");
				buf = buf.append(date_format.format(exp.getParent().getDate()));
				this.parent = buf.toString();
			}
		}
		this.type = exp.getType().toString(); 
		this.date = MyDate.getDateString(exp.getDate()); 
		this.name = exp.getName(); 
		this.amount = new Double(exp.getAmount()).toString(); 
		this.status = exp.getStatus().toString();
		{
			this.method = ""; 
			if(exp.getType()==ExpenseType.PURCHASE ||
					exp.getType()==ExpenseType.COMPOSITE_PURCHASE){				
				this.method = exp.getMode().toString(); 
			}
		}
		this.vendor = exp.getVendor(); 
		{
			this.location = ""; 
			if(exp.getType()==ExpenseType.PURCHASE ||
					exp.getType()==ExpenseType.COMPOSITE_PURCHASE){				
				this.location = exp.getLocation(); 
			}
		}
		this.category = exp.getCategory().toString(); 
		{
			this.dueDate = "";
			if(exp.getType()==ExpenseType.BILL)
				this.dueDate = ((Bill)exp).getDueDate().toString();
		}
		{
			this.interval = ""; 
			if(exp.getType()==ExpenseType.BILL ||
					exp.getType()==ExpenseType.COMPOSITE_BILL){				
				this.interval = exp.getInterval().toString();
			}
		}
		
		return this;
	}

	public Expense getExpRef() {
		return expRef;
	}

	private void setExpRef(Expense expRef) {
		this.expRef = expRef;
	}
}
