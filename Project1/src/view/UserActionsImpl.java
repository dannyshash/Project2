package view;

import java.util.ArrayList;
import java.util.Date;

import controller.ExpenseContainerApi;
import model.CompositeBill;
import model.CompositePurchase;
import model.Expense;
import model.ExpenseType;

public class UserActionsImpl implements UserActionsApi {
	private final ExpenseContainerApi container;
	
	public UserActionsImpl(ExpenseContainerApi container) {
		this.container=container;
	}

	@Override
	public void addExpense(Expense exp) {
		container.addExpense(exp);
	}

	@Override
	public void removeExpense(Expense exp) {
		container.removeExpense(exp);
	}

	@Override
	public void removeExpense(Expense exp, Expense root) {
		System.out.println("removing one of the child:"+exp+",root:"+root);

		Expense newCopy = null;
		if(root.getType() == ExpenseType.COMPOSITE_PURCHASE) {
			newCopy = new CompositePurchase((CompositePurchase)root);
			newCopy.display();
		} else if (root.getType() == ExpenseType.COMPOSITE_BILL) {
			newCopy = new CompositeBill((CompositeBill)root);
		} else {
			throw new RuntimeException("changePaymentStatus error");
		}
		
		Expense parent = exp.getParent();
		System.out.println("parent:"+parent);
		Expense node = null;
		if(parent.iseqal(root))
			node = newCopy;
		else
			node = newCopy.find(parent);
		if(node !=null){
			node.remove(exp);			
			this.modifyExpense(root, newCopy);			
		}
	}
	
	@Override
	public void modifyExpense(Expense from, Expense to) {
		container.modifyExpense(from, to);
	}

	@Override
	public void AddExpenseToComposite(Expense comp_expense, Expense expense) {
		container.addExpenseIntoComposite(comp_expense, expense);
	}

	@Override
	public void AddExpensesToComposite(Expense comp_expense, ArrayList<Expense> expenses) {
		container.addExpensesIntoComposite(comp_expense, expenses);

	}

	@Override
	public void changePaymentStatus(Expense exp) {
		container.changePaymentStatus(exp, new Date());

	}

	@Override
	public void changePaymentStatus(Expense exp, Expense root) {
		System.out.println("changing one of the child:"+exp+",root:"+root);

		Expense newCopy = null;
		if(root.getType() == ExpenseType.COMPOSITE_PURCHASE) {
			newCopy = new CompositePurchase((CompositePurchase)root);
		} else if (root.getType() == ExpenseType.COMPOSITE_BILL) {
			newCopy = new CompositeBill((CompositeBill)root);
		} else {
			throw new RuntimeException("changePaymentStatus error");
		}
		
		Expense newExp = newCopy.find(exp);
		if(newExp != null) {
			newExp.changePaymentStatus(new Date());
			this.modifyExpense(root, newCopy);
		}		
	}
}
