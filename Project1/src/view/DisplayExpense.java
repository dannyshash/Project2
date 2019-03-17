package view;

import java.text.SimpleDateFormat;

import model.Bill;
import model.CompositeBill;
import model.Expense;
import model.ExpenseType;
import utils.MyDate;

public class DisplayExpense {
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
	
	public static DisplayExpense copy(Expense exp) {
		DisplayExpense myExpense = new DisplayExpense();

		myExpense.no = "0";
		myExpense.expand = "-";
		{
			if(exp.getType().ordinal()>1)
				myExpense.expand = "+";
		}
		{
			myExpense.parent = "";
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
				myExpense.parent = buf.toString();
			}
		}
		myExpense.type = exp.getType().toString(); 
		myExpense.date = MyDate.getDateString(exp.getDate()); 
		myExpense.name = exp.getName(); 
		myExpense.amount = new Double(exp.getAmount()).toString(); 
		myExpense.status = exp.getStatus().toString();
		{
			myExpense.method = ""; 
			if(exp.getType()==ExpenseType.PURCHASE ||
					exp.getType()==ExpenseType.COMPOSITE_PURCHASE){				
				myExpense.method = exp.getMode().toString(); 
			}
		}
		myExpense.vendor = exp.getVendor(); 
		{
			myExpense.location = ""; 
			if(exp.getType()==ExpenseType.PURCHASE ||
					exp.getType()==ExpenseType.COMPOSITE_PURCHASE){				
				myExpense.location = exp.getLocation(); 
			}
		}
		myExpense.category = exp.getCategory().toString(); 
		{
			myExpense.dueDate = "";
			if(exp.getType()==ExpenseType.BILL)
				myExpense.dueDate = ((Bill)exp).getDueDate().toString();
		}
		{
			myExpense.interval = ""; 
			if(exp.getType()==ExpenseType.BILL ||
					exp.getType()==ExpenseType.COMPOSITE_BILL){				
				myExpense.interval = exp.getInterval().toString();
			}
		}
		
		return myExpense;
	}
}
