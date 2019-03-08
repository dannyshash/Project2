package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.ExpenseContainerImpl;
import controller.ExpenseSubject;
import model.Expense;
import model.ExpenseKey;

public class ExpenseObserverImpl implements ExpenseObserver{
	private static final ExpenseObserver expenseObserver = new ExpenseObserverImpl();
	private ExpenseSubject subject;
	private List<Map<ExpenseKey , Expense>> ldata=null;
	//private final ExpenseContentApi contentUpdator;
	
	/**
	 * private constructor for the singleton
	 */
	private ExpenseObserverImpl() {
		this.subject=null;
	}

	public static ExpenseObserver getInstance() {
		return expenseObserver;
	}
	
	public void init(ExpenseSubject subject) {
		assert(subject!=null);
		setSubject(subject);
		getSubject().register(this);
	}
	
	private void setSubject(ExpenseSubject subject) {
		this.subject = subject;
	}
	
	protected ExpenseSubject getSubject() {
		return this.subject;
	}
	
	@Override
	public void update(List<Map<ExpenseKey , Expense>> data) {
		this.ldata = data;
		System.out.println("Data state changed, please refresh the view! items="+
				ldata.get(0).size()+", " + 	//purchase
				ldata.get(1).size()+", " + 	//bill
				ldata.get(2).size()+", " + 	//comp_purchase
				ldata.get(3).size()			//comp_bill
				);
		subject.resetStateChange();		
	}

	@Override
	public ArrayList<Expense> getData(DisplayParameters params) {
		ArrayList<Expense> expList = null;
		
		if(ldata.get(0) == null) {
			System.out.println("reurning as data is null, refresh");
			return expList;
		}
		switch(params.type.ordinal()) {
		case 0:
		case 2:
			expList = new ArrayList<Expense>();
			ArrayList<Expense> purchase_List = new ArrayList<Expense>(ldata.get(0).values());
			ArrayList<Expense> comp_purchase_List = new ArrayList<Expense>(ldata.get(2).values());
			expList.addAll(purchase_List);
			expList.addAll(comp_purchase_List);
			break;			
		case 1:
		case 3:
			expList = new ArrayList<Expense>();
			ArrayList<Expense> bill_List = new ArrayList<Expense>(ldata.get(1).values());
			ArrayList<Expense> comp_bill_List = new ArrayList<Expense>(ldata.get(3).values());
			expList.addAll(bill_List);
			expList.addAll(comp_bill_List);
			break;
		default:
			throw new RuntimeException("getExpenses error");
		}
		
		return expList;
	}
}
