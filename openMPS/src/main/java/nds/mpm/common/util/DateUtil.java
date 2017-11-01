/**
 * 
 */
package nds.mpm.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Jerry
 * 
 */
public class DateUtil {
	
	public static Calendar getToDay(Locale locale){
		
		Calendar cal = Calendar.getInstance(locale);
		return cal;
		
	}
	
	public static String getFormatToDay(Locale locale, String DATEFORMAT){
		
		Calendar cal = Calendar.getInstance(locale);
		
		final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		return sdf.format(cal.getTime());
		
	}
	
	public static Calendar setDay(int y, int m, int d){
		
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, y);
		cal.set(Calendar.MONTH, m - 1);
		cal.set(Calendar.DAY_OF_MONTH, d);
		
		return cal;
	}
	
	public static Calendar setDay(int y, int m, int d, Locale locale){
		
		Calendar cal = new GregorianCalendar(locale);
		cal.set(Calendar.YEAR, y);
		cal.set(Calendar.MONTH, m - 1);
		cal.set(Calendar.DAY_OF_MONTH, d);
		
		return cal;
	}

	public static List<Calendar> getWeekDays(Locale locale
			, int y, int m, int d) {

		Calendar today = setDay(y, m, d);
		
		today.add(Calendar.DATE, -today.get(Calendar.DAY_OF_WEEK)+1 );

		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH)+1;
		int day = today.get(Calendar.DATE);

		today.set(year, month, day);
		
		List<Calendar> returndays = new ArrayList();
		for (int i = 1; i < 7; i++) {

			today.add(Calendar.DATE, 1);

			year = today.get(Calendar.YEAR);
			month = today.get(Calendar.MONTH);
			day = today.get(Calendar.DATE);
			
			today.set(year, month, day);
			
			Calendar newday = setDay(year, month, day);
			returndays.add(newday);
		}

		return returndays;
	}

	public String getKoreanWeekDay(int y, int m, int d) {
		// 날짜로 요일가져오기 (년,월,일을 int로 가져온다!)
		String[] dayOfWeek = { "일", "월", "화", "수", "목", "금", "토" };
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, y);
		cal.set(Calendar.MONTH, m - 1);
		cal.set(Calendar.DAY_OF_MONTH, d);

		String weekday = dayOfWeek[cal.get(Calendar.DAY_OF_WEEK) - 1];

		return weekday;
	}
	
	public static long getTodayDateTime(Locale locale, int amount) {

		Calendar today = getToDay(locale);
		today.add(Calendar.DATE, amount );
		
		return today.getTimeInMillis();
	}
	
	public static long getDateTime(int y, int m, int d, Locale locale, int amount) {

		Calendar today = setDay(y, m, d, locale);
		today.add(Calendar.DATE, amount );
		
		return today.getTimeInMillis();
	}

}
