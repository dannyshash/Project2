package misc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MyDate {
	public static SimpleDateFormat DATE_TIME_PATTERN = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
	public static SimpleDateFormat DATE_PATTERN = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * @param date, Date object
	 * @return a String in the format "yyyy-MM-dd"
	 */
	public static String getDateString(Date date) {
		return DATE_PATTERN.format(date);
	}

	/**
	 * @param date, Date object
	 * @return a String in the format "yyyy-MM-dd H:mm:ss"
	 */
	public static String getDateTimeString(Date date) {
		return DATE_TIME_PATTERN.format(date);
	}

	/**
	 * @param pattern, a String in date pattern "yyyy-MM-dd" 
	 * @return a date object, set to given date
	 */
	public static Date getJustDate(String pattern) {
		Date date = null;
		try{			
			date = DATE_PATTERN.parse(pattern);
		} catch (ParseException e) {
			System.out.println("Unable to parse the date");
		}
		return date;
	}
	
	/**
	 * @param pattern, a String in date pattern "yyyy-MM-dd H:mm:ss" 
	 * @return the date object, set to given date&time
	 */
	public static Date getDateAndTime(String pattern) {
		Date date = null;
		try{			
			date = DATE_TIME_PATTERN.parse(pattern);
		} catch (ParseException e) {
			System.out.println("Unable to parse the date");
		}
		return date;
	}
	
	public static String getRandomDateStr() {
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		String str_month = ConvertToLeadingZero(month);
		int min_day =1;
		int max_day = calendar.getMaximum(Calendar.DAY_OF_MONTH);
			
		String randomDate = year+"-"+str_month+"-"+(getRandomInRange(min_day,max_day));	
		return randomDate;
	}
	
	public static Date getRandomDate() {
		return getJustDate(getRandomDateStr());
	}
	
	public static String getRandomTimeStr() {
		String hour = ConvertToLeadingZero(getRandomInRange(0,23));
		String min = ConvertToLeadingZero(getRandomInRange(0,59));
		String sec = ConvertToLeadingZero(getRandomInRange(0,59));
		
		String randomTime = hour+":"+min+":"+sec;
		return randomTime;
	}
	
	public static Date getRandomDateAndTime() {
		return getDateAndTime(getRandomDateStr()+" "+getRandomTimeStr());
	}
	
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
	
	private static String ConvertToLeadingZero(int num) {
		if(num==0) 
			return "00";
		return (num<10)?("0"+num):(""+num);
		
	}
	
	public static int getRandomInRange(int min, int max){
		Random random=new Random();
		
		return random.nextInt((max-min)+1)+min;
	}

}
