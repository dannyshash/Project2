package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Bill;
import model.CompositeBill;
import model.CompositePurchase;
import model.Expense;
import model.ExpenseCategories;
import model.ExpenseKey;
import model.ExpenseType;
import model.Mode;
import model.Purchase;
import model.RepitionInterval;
import model.Status;
import utils.Constants;
import utils.MyDate;
import utils.Util;

public class FileLoaderImpl implements DataLoader{
	private final String filename;
	Map<ExpenseKey , Expense> purchase = null;
	Map<ExpenseKey , Expense> bill = null;
	Map<ExpenseKey , Expense> comp_purchase = null;
	Map<ExpenseKey , Expense> comp_bill = null;

	public FileLoaderImpl(String filename) {
		this.filename=filename;
	}

	@Override
	public void dataLoad(List<Map<ExpenseKey , Expense>> expenseData) {
		purchase = expenseData.get(ExpenseType.PURCHASE.ordinal());
		bill = expenseData.get(ExpenseType.BILL.ordinal());
		comp_purchase = expenseData.get(ExpenseType.COMPOSITE_PURCHASE.ordinal());
		comp_bill = expenseData.get(ExpenseType.COMPOSITE_BILL.ordinal());
		
		//14 items, this can be improved. heavily depends on csv file columns.
		String[] item_name = {"Type","parent","Date","Name","Amount","Status","Mode","Payment Date","Vendor","Location","Category","Duedate","Interval","Description"};


	    try{
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		    //InputStream inputStream = FileLoaderImpl.class.getResourceAsStream("SampleExpensesData.csv");
			InputStream inputStream = classloader.getResourceAsStream(filename);
    	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
	    	String line = null;
	    	while ((line = reader.readLine()) != null) {
	    	    System.out.println(line);
	    		String[] sub_items=line.split(",");
	    		
	    		//Skip the first line
	    		if(sub_items[0].equalsIgnoreCase("Type")) continue;
	    		
	    		for(int i=0; i<sub_items.length;i++)
	    			System.out.print("sub_items: "+item_name[i]+"="+sub_items[i]+",");
	    		System.out.println();
	    		Expense e=createExpense(sub_items);
	    		e.display();
	    		
	    		if(sub_items[1].equalsIgnoreCase("null")) {
	    			addExpenseToArray(e);
	    		}
	    		else {
	    			getParent(e, sub_items[1]).add(e);
	    		}
	    	}	   
	    	reader.close();
	    } catch (Throwable e){
	    	throw  new RuntimeException(e+" Cannot open the file: "+filename);
	    } finally {
	    	//Anything else to do?
	    }
	}
	
	private Expense createExpense(String[] expenseDataItems) {
		Expense e = null;
		if(ExpenseType.PURCHASE.toString().equalsIgnoreCase(expenseDataItems[0])) {
			//create Purchase
			System.out.println("Creating Purchase");
			e = new Purchase(new Double(expenseDataItems[4]).doubleValue(), 
					expenseDataItems[3], MyDate.getJustDate(expenseDataItems[2]), Util.getStatusEnum(expenseDataItems[5]), 
					new Date(), expenseDataItems[8], expenseDataItems[9], Util.getModeEnum(expenseDataItems[6]), ExpenseCategories.DAFAULT);
		} else if(ExpenseType.BILL.toString().equalsIgnoreCase(expenseDataItems[0])) {
			//create Bill
			System.out.println("Creating Bill");
			e = new Bill(new Double(expenseDataItems[4]).doubleValue(), 
					expenseDataItems[3], MyDate.getJustDate(expenseDataItems[2]), Util.getStatusEnum(expenseDataItems[5]), 
					MyDate.getJustDate(expenseDataItems[11]), expenseDataItems[8], Util.getRepitionIntervalEnum(expenseDataItems[12]), ExpenseCategories.DAFAULT);
		} else if(ExpenseType.COMPOSITE_PURCHASE.toString().equalsIgnoreCase(expenseDataItems[0])) {
			//create composite Purchase
			System.out.println("Creating composite Purchase");
			e = new CompositePurchase(expenseDataItems[13], ExpenseCategories.DAFAULT, Mode.CASH, "");
		} else if(ExpenseType.COMPOSITE_BILL.toString().equalsIgnoreCase(expenseDataItems[0])) {
			//create composite Bill
			System.out.println("Creating composite Bill");
			e = new CompositeBill(expenseDataItems[13], ExpenseCategories.DAFAULT);
		} else {
			throw new RuntimeException("Can not create Expense Type");
		}
		
		return e;
	}
	
	private void addExpenseToArray(Expense e) {		
		switch(e.getType().ordinal()){
		case 0: purchase.put(e.getKey(), e);break;
		case 1: bill.put(e.getKey(), e);break;
		case 2: comp_purchase.put(e.getKey(), e);break;
		case 3: comp_bill.put(e.getKey(), e);break;
		default: throw new RuntimeException("addExpenseToArray error");
		}
	}
	
	private Expense getParent(Expense e, String parent) {
		switch(e.getType().ordinal()){
		case 0:
		case 2:
			return findParent(comp_purchase, e, parent);
		case 1:
		case 3:
			return findParent(comp_bill, e, parent);
		default: throw new RuntimeException("addExpenseToArray error");
		}
	}

	private Expense findParent(Map<ExpenseKey , Expense> expMap, Expense e, String parent) {
		Expense re = null;
		Expense rce = null;
		List<Expense> expList = new ArrayList<Expense>(expMap.values());
		Iterator<Expense> expIt = expList.iterator();
		while(expIt.hasNext()) {
			re = expIt.next();
			if(re.getType().ordinal()>1) rce=re;
			System.out.println("findParent: desc="+re.getDescription());
			if(re.getDescription().equalsIgnoreCase(parent)) {
				return re; 
			}
		}
		//TODO improve it
		//2nd level
		CompositePurchase cp = (CompositePurchase)re;
		List<Expense> secondLvlList = cp.getPurchasesList();
		Iterator<Expense> secIt = secondLvlList.iterator();
		Expense sre = null;
		while(secIt.hasNext()) {
			sre = secIt.next();
			if(sre.getType().ordinal()>1 && sre.getDescription().equalsIgnoreCase(parent)) {
				return sre; 
			}
		}

		throw new RuntimeException("findParent error");
	}
	
}
