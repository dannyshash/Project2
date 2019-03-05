package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;

public interface Store {
	public void put(Expense exp) throws IOException;
	public Expense get(ExpenseKey key) throws IOException;
	public List<Expense> getMatching()  throws IOException;
	public void remove(Expense expense) throws IOException;
	//Not needed, as put() can be used, with the same key, overwrites
	public boolean modify(Expense from, Expense to);
	//TODO should change, not dafe to return the pointer
	public List<Map<ExpenseKey,Expense>> getAll();
	//Temporary
	public void display();
}
