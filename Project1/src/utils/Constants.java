package utils;

import model.ExpenseType;

public final class Constants {
	//Unfortunately none of the following works 1. case Constants.PURCHASE_VAL: 2. case ExpenseType.PURCHASE.ordinal()
	final public static int PURCHASE_VAL = ExpenseType.PURCHASE.ordinal(); 
	final public static int COMPOSITE_PURCHASE_VAL = ExpenseType.COMPOSITE_PURCHASE.ordinal();
	final public static int BILL_VAL = ExpenseType.BILL.ordinal();
	final public static int COMPOSITE_BILL_VAL = ExpenseType.COMPOSITE_BILL.ordinal();
	
	final private static String FILE_NAME=System.getenv("SAMPLE_DATA_FILE");
	final public static String SAMPLE_DATA_FILENAME = (FILE_NAME==null)?"SampleExpensesData.csv":FILE_NAME; 
}
