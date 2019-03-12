package utils;

import model.ExpenseType;
import model.Mode;
import model.RepitionInterval;
import model.Status;

public class Util {

	public static ExpenseType getExpenseTypeEnum(String str) {
		switch(str) {
		case "Purchase": return ExpenseType.PURCHASE;
		case "Bill": return ExpenseType.BILL;
		case "Composite_Purchase": return ExpenseType.COMPOSITE_PURCHASE;
		case "Composite_Bill": return ExpenseType.COMPOSITE_BILL;
		default: throw new RuntimeException("getExpenseTypeEnum Error");
		}
	}
	public static Status getStatusEnum(String str){
		switch(str){
		case "Paid": return Status.PAID;
		case "Unpaid": return Status.UNPAID;
		default: throw new RuntimeException("getStatusEnum Error");
		}
	}
	
	public static Mode getModeEnum(String str) {
		switch(str){
		case "Cash": return Mode.CASH;
		case "Debit": return Mode.DEBIT;
		case "Credit": return Mode.CREDIT;
		default: throw new RuntimeException("getModeEnum Error");
		}		
	}
	
	public static RepitionInterval getRepitionIntervalEnum(String str){
		switch(str){
		case "Daily": return RepitionInterval.DAILY;
		case "Weekly": return RepitionInterval.WEEKLY;
		case "BiWeekly": return RepitionInterval.BIWEEKLY;
		case "Monthly": return RepitionInterval.MONTHLY;
		case "Quarterly": return RepitionInterval.QUARTERLY;
		case "Yearly": return RepitionInterval.YEARLY;
		default: throw new RuntimeException("getRepitionIntervalEnum Error");
		}				
	}

}
