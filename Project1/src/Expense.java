/**
 * Description: Definition of Expense class with 2 subclass Purchase and Bill.
 * Author: Tony Lac
 * Created Date: 2019-01-25
 */

import java.text.SimpleDateFormat;
import java.util.Date;

class Expense {
	String type;
	String name;
	String status;
	Date date;
	double amount;

	public Expense(String type, Date date, String name, double amount, String status) {
		this.type = type;
		this.date = date;
		this.name = name;
		this.amount = amount;
		this.status = status;

	}

	public String toString() {
		return type + " " + date + " " + name + " " + amount + " " + status;
	}
	
	public String getType() {
		return type;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getName() {
		return name;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus() {
		if(status=="Paid") {
			status = "Unpaid";
		}
		else if(status=="Unpaid") {
			status = "Paid";
		}
	}
	
	public Date getDueDate() {
		return new Date();
	}
	
	public String getLocation() {
		return "";
	}
	
	public String getMethod() {
		return "";
	}
	public String getInterval() {
		return "";
	}
	
}

class Purchase extends Expense {
	String location;
	String method;
	Date duedate;
	
	public Purchase(String type, Date date, String name, double amount, String status, String location, String method,
			Date duedate) {
		super(type, date, name, amount, status);
		this.duedate = duedate;
		this.location = location;
		this.method = method;
	}

	public String toString() {
		return super.toString() + " " + duedate + " " + location + " " + method;
	}

	public Date getDueDate() {
		return duedate;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getMethod() {
		return method;
	}
	public String getInterval() {
		return "";
	}
}


class Bill extends Expense {
	String location;
	String method;
	String interval;
	Date duedate;
	
	public Bill(String type, Date date, String name, double amount, String status, String location, String method,
			Date duedate, String interval) {
		super(type, date, name, amount, status);
		this.duedate = duedate;
		this.location = location;
		this.method = method;
		this.interval = interval;
	}

	public String toString() {
		return super.toString() + " " + duedate + " " + location + " " + method;
	}

	public Date getDueDate() {
		return duedate;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getMethod() {
		return method;
	}
	public String getInterval() {
		return interval;
	}
}
