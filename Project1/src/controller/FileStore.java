package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import model.Expense;
import model.ExpenseKey;

public class FileStore implements Store {

	@Override
	public void put(Expense exp) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Expense get(ExpenseKey key) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expense> getMatching() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Expense expense) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean modify(Expense from, Expense to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Map<ExpenseKey, Expense>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
}
