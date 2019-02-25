/**
 * Expense interface
 */
package model;

public interface Expense {
	public void add(Expense expense);
	public void remove(Expense expense);
	public Expense get(int index);
	public void display();
}
