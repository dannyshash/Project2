/**
 * The Abstract implementation of Expense interface
 * Holds Expense Model
 */
package model;

import java.util.ArrayList;
import java.util.Date;

import misc.MyDate;

/**
 * this is Abstract implementation of Expense
 * TODO: should be package private, not public scope, should change after the view is adapted
 *
 */
public abstract class AbstractExpense implements Expense{
	private final ExpenseType type;
	private final double amount; //expense amount, primary key element
	private final String name; //expense name, primary key element
	private final Date date; //date of the expense, primary key element
	private Status status; //Status of the expense, paid or not
	private Date paymentDate; //payment date, when state changed from unpaid to paid
	private String vendor; //Store name
	private ExpenseCategories category; //Category
	//Composite item
	protected String description; //primary key element
	protected ArrayList<Expense> items;
	private Expense parent; //to point to the parent, in case of composite
	private int noOfSubItems; //in case of composite

	public AbstractExpense(ExpenseType type, double amount, String name, Date date, Status status, Date dueDate, String vendor, ExpenseCategories category) {
		this.type=type;
		this.amount=amount;
		this.name=name;
		this.date=date;
		this.setStatus(status);
		this.setPaymentDate(dueDate);
		this.setVendor(vendor);
		this.category=category;
		this.setParent(null);
		this.setNoOfSubItems(1);
	}
	
	public AbstractExpense(ExpenseType type, double amount, String name, Date date) {
		this(type, amount, name, date, Status.UNPAID, date, "dummy vendor", ExpenseCategories.DAFAULT);
	}
	
	@Override
	public ExpenseType getType() {
		return this.type;
	}
	
	@Override
	public double getAmount() {
		return amount;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Date getDate() {
		return date;
	}

	@Override
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

	@Override
	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date dueDate) {
		this.paymentDate = dueDate;
	}

	@Override
	public String getVendor() {
		return vendor;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	@Override
	public String getLocation() {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public Mode getMode() {
		throw new RuntimeException("Not implemented");
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
	public boolean remove(Expense expense) {
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
	public Date getDueDate() {
		throw new RuntimeException("Not implemented");
	}
	
	@Override
	public RepitionInterval getInterval() {
		throw new RuntimeException("Not implemented");
	}
	
	@Override
	public boolean changePaymentStatus(Date date) {
		if(this.getStatus() == Status.UNPAID) {
			this.setStatus(Status.PAID);
			this.paymentDate = date;
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
		return amount + " " + name + " " + MyDate.getDateString(date) + " " + getStatus().getValue()+ " "+
				getVendor()+" "+getCategory().getValue();
	}

	@Override
	public Expense getParent() {
		return parent;
	}

	@Override
	public void setParent(Expense parent) {
		this.parent = parent;
	}

	public int getNoOfSubItems() {
		return noOfSubItems;
	}

	public void setNoOfSubItems(int noOfSubItems) {
		this.noOfSubItems = noOfSubItems;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	public void setDescription() {
		throw new RuntimeException("Can't modify Description, as it's the key");
	}

	@Override
	public ExpenseCategories getCategory() {
		return category;
	}

}
