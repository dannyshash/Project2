package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import controller.ExpenseContainerImpl;
import controller.ExpenseSubject;
import model.CompositePurchase;
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
		/*
		 * Just for debug purpose
		ArrayList<Expense> cp = new ArrayList<Expense>(ldata.get(2).values());
		System.out.println("### composite items="+cp.size()+" ###");
		ArrayList<Expense> si = ((CompositePurchase)cp.get(0)).getSubItems();
		ArrayList<Expense> pl = ((CompositePurchase)cp.get(0)).getPurchasesList();
		int no_subitems = ((CompositePurchase)cp.get(0)).getNoOfSubItems();
		System.out.println("### sub items="+no_subitems+","+pl.size()+" ###");
		Iterator<Expense> siit = si.iterator();
		while(siit.hasNext()) {
			Expense expenseItem = siit.next();
			if(expenseItem.getType().ordinal()>1){
				System.out.println("### subItem Display ###"+expenseItem.toString()+",subitem="+expenseItem.getSubItems().size());	
				ArrayList<Expense> si2 = expenseItem.getSubItems();
				ArrayList<Expense> pl2 = ((CompositePurchase)expenseItem).getPurchasesList();
				int no_subitems2 = expenseItem.getNoOfSubItems();
				System.out.println("### sub items="+no_subitems2+","+pl2.size()+" ###");
			}
			else {
				System.out.println("### subItem Display ###"+expenseItem.toString());
			}			
			expenseItem.display();
		}	
		
		Iterator<Expense> compPurchase = pl.iterator();
		while(compPurchase.hasNext()) {
			Expense expenseItem = compPurchase.next();
			System.out.println("### Display ###"+ expenseItem.toString());			
		}	
		*/

		
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
