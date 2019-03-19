package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIValidations {
	private static String amountPattern = "^[\\d]+[\\.]{0,1}[\\d]{0,2}$";
	private static String datePattern = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
	
	public static boolean amountValidation(String buf) {
		Pattern r = Pattern.compile(amountPattern);
	    Matcher m = r.matcher(buf);
	    if(!m.matches())
	    	return false;
		
		return true;
	}
	
	public static boolean dateValidation(String buf) {
		Pattern r = Pattern.compile(datePattern);
	    Matcher m = r.matcher(buf);
	    if(!m.matches())
	    	return false;
		
		return true;
	}
}
