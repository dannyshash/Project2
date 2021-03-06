/**
 * Expense interface
 */
package model;

import java.util.ArrayList;
import java.util.Date;

public interface Expense {
	//Keys are amount, name, date
	public ExpenseKey getKey();
	
	//modification 
	public void add(Expense expense);
	public boolean remove(Expense expense);
	public boolean changePaymentStatus(Date date);
	public void changePaymentStatus(Status status, Date date);
	public void setParent(Expense parent);
	
	//access
	public Expense get(int index);
	//flat find, only among the current children
	public Expense get(Expense exp);
	//recursive find, among the children of it's children
	public Expense find(Expense exp);
	public ArrayList<Expense> getSubItems();
	public int getNoOfSubItems();
	//return the parent in case of composite type
	public Expense getParent();
	public Expense getRoot();
	
	public boolean iseqal(final Expense expense);
	
	public void display();
	
	//Accessors for Viewer
	public ExpenseType getType();
	public double getAmount();
	public String getName();
	public Date getDate();
	public Status getStatus();
	public Mode getMode();
	public String getVendor();
	public String getLocation();
	public ExpenseCategories getCategory();
	public Date getPaymentDate();
	public String getDescription();
	public Date getDueDate();
	public RepitionInterval getInterval();
	
}
