/**
 * The Abstract implementation of Expense interface
 * Holds Expense Model
 */
package model;

import java.util.Date;

public abstract class AbstractExpense implements Expense{
	private final ExpenseType type;
	private double amount; //expense amount, primary key element
	private String name; //expense name, primary key element
	private Date date; //date of the expense, primary key element
	private Status status; //Status of the expense, paid or not
	private Date dueDate; //Due date
	private String vendor; //Store name

	public AbstractExpense(ExpenseType type, double amount, String name, Date date, Status status, Date dueDate, String vendor) {
		this.type=type;
		this.setAmount(amount);
		this.setName(name);
		this.setDate(date);
		this.setStatus(status);
		this.setDueDate(dueDate);
		this.setVendor(vendor);
	}
	
	public AbstractExpense(ExpenseType type, double amount, String name, Date date) {
		this(type, amount, name, date, Status.UNPAID, date, "dummy vendor");
	}

	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Deprecated
	public void setStatus() {
		if(getStatus().getValue()==Status.PAID.getValue()) {
			setStatus(Status.UNPAID);
		}
		else if(getStatus().getValue()==Status.UNPAID.getValue()) {
			setStatus(Status.PAID);
		}
		else {
			throw new RuntimeException();
		}
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getVendor() {
		return vendor;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public ExpenseType getType() {
		return type;
	}
	
	@Override
	public ExpenseKey getKey() {
		return new ExpenseKey(amount, name, date);
	}

	@Override
	public void add(Expense expense) {
		throw new RuntimeException("Not implemented");
		
	}

	@Override
	public void remove(Expense expense) {
		throw new RuntimeException("Not implemented");
		
	}
	
	@Override
	public Expense get(int index) {
		throw new RuntimeException("Not implemented");
	}
	
	@Override
	public void display() {
		this.toString();
		
	}

	@Override
	public boolean changePaymentStatus() {
		if(this.getStatus() == Status.UNPAID) {
			this.setStatus(Status.PAID);
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean iseqal(Expense expense) {
		AbstractExpense p = (AbstractExpense)expense;
		
		if(this.getType() == p.getType() &&
				this.getAmount() == p.getAmount() &&
				this.getName().equals(p.getName()) &&
				this.getDate().equals(p.getDate())) {
			return true;			
		}
	
		return false;
	}
	
	public String toString() {
		return amount + " " + name + " " + date + " " + getStatus().getValue();
	}

}
