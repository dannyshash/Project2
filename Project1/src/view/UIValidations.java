package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UIValidations {
	private static String amountPattern = "^[\\d]+[\\.]{0,1}[\\d]{0,2}$";
	
	public static boolean amountValidation(String buf) {
		Pattern r = Pattern.compile(amountPattern);
	    Matcher m = r.matcher(buf);
	    if(!m.matches())
	    	return false;
		
		return true;
	}
}
