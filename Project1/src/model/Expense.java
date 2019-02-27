/**
 * Expense interface
 */
package model;

import java.util.Date;

public interface Expense {
	//Keys are amount, name, date
	public ExpenseKey getKey();
	
	//modification 
	public void add(Expense expense);
	public boolean remove(Expense expense);
	public boolean changePaymentStatus(Date date);
	public void setParent(Expense parent);
	
	//access
	public Expense get(int index);
	public ExpenseType getType();
	//return the parent in case of composite type
	public Expense getParent();
	
	public boolean iseqal(final Expense expense);
	
	public void display();
	
	
}
