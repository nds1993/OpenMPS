package nds.mpm.common.util;

/**
 * 
 * 공통 유틸 Timestamp
 * 
 * getCurrentUTCdatetimeAsString(String DATEFORMAT);
 * getStringToDate(String StrDate, String DATEFORMAT);
 * getCurrentUTCTimeString();
 * parseTimeStamp(Timestamp date, String DATEFORMAT);
 * */
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimestampUtil {

	public static String getCurrentUTCdatetimeAsString(String DATEFORMAT) {
		final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = sdf.format(new Date());

		return utcTime;
	}

	public static Date getStringToDate(String StrDate, String DATEFORMAT) {
		Date dateToReturn = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

		try {
			dateToReturn = (Date) dateFormat.parse(StrDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateToReturn;
	}
	
	public static Date getStringToUTCDate(String StrDate, String DATEFORMAT) {
		Date dateToReturn = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		try {
			dateToReturn = (Date) dateFormat.parse(StrDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateToReturn;
	}

	public static String getCurrentUTCTimeString() {

		String utcTime = null;

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			utcTime = sdf.format(new Date());

		} catch (Exception e) {
		}

		return utcTime;
	}
	
	public static String getCurrentUTCTimeString(Locale locale) {

		String utcTime = null;

		try {
			Calendar calendar = Calendar.getInstance(locale);  
			TimeZone clientTimeZone = calendar.getTimeZone();
			
			final SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(clientTimeZone);
			utcTime = sdf.format(new Date());

		} catch (Exception e) {
		}

		return utcTime;
	}
	
	public static String getCurrentUTCTimeString(String DATEFORMAT) {

		String utcTime = null;

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			utcTime = sdf.format(new Date());

		} catch (Exception e) {
		}

		return utcTime;
	}

	public static Timestamp getTimstampByString(String stringdate) {

		Timestamp currtime = null;
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			sdf.parse(stringdate);
			Calendar cal = sdf.getCalendar();

			currtime = new Timestamp(cal.getTime().getTime());
		} catch (Exception e) {
		}
		return currtime;
	}
	
	public static Timestamp getTimstampByStringFormat(String stringdate, String DATEFORMAT) {

		Timestamp currtime = null;
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);

			sdf.parse(stringdate);
			Calendar cal = sdf.getCalendar();

			currtime = new Timestamp(cal.getTime().getTime());
		} catch (Exception e) {
		}
		return currtime;
	}
	
	public static Calendar getCurrentCalendar(String stringdate) {

		Calendar cal = null;

		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			sdf.parse(stringdate);
			cal = sdf.getCalendar();

		} catch (Exception e) {
		}
		return cal;
	}
	
	public static String parseTimeStamp(Timestamp date, String DATEFORMAT) {

		String strDate = null;
		try {
			final SimpleDateFormat sdf = new SimpleDateFormat(
					DATEFORMAT);

			Calendar cal = sdf.getCalendar();
			cal.setTimeInMillis(date.getTime());
			
			strDate = sdf.format(cal.getTime());

		} catch (Exception e) {
		}
		return strDate;
	}
	
	public static long getUTCDateToLong(Date curDate, Locale locale) {
		
		Long dateToReturn = null;
		try {
			
			Calendar cal = Calendar.getInstance(locale);
			cal.setTimeInMillis(curDate.getTime());
			
			dateToReturn = cal.getTimeInMillis();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dateToReturn;
	}
	
	public static Timestamp convertUTCTimestampToLocal(Timestamp utcDate, String timeZoneID) {
		
		Timestamp dateToReturn = null;
		try {
			
			long utcTime = utcDate.getTime();
			
			TimeZone localetz = TimeZone.getTimeZone(timeZoneID); /* ex KOREA = "Asia/Seoul" */
			
			long tzOffset = localetz.getOffset(utcTime);
			
			long localTime = utcTime + tzOffset;
			
			dateToReturn = new Timestamp(localTime);

		} catch (Exception e) {
		}

		return dateToReturn;
	}
	
	public static Timestamp convertUTCLongToLocal(long curutcTime, String timeZoneID) {
		
		Timestamp dateToReturn = null;
		try {
			
			long utcTime = curutcTime;
			
			TimeZone localetz = TimeZone.getTimeZone(timeZoneID); /* ex KOREA = "Asia/Seoul" */
			
			long tzOffset = localetz.getOffset(utcTime);
			
			long localTime = utcTime + tzOffset;
			
			dateToReturn = new Timestamp(localTime);

		} catch (Exception e) {
		}

		return dateToReturn;
	}
	
	public static long convertUTCTimestampToLong(Timestamp utcDate, String timeZoneID) {
		
		long dateToReturn = 0;
		try {
			
			long utcTime = utcDate.getTime();
			
			TimeZone localetz = TimeZone.getTimeZone(timeZoneID); /* ex KOREA = "Asia/Seoul" */
			
			long tzOffset = localetz.getOffset(utcTime);
			
			dateToReturn = utcTime + tzOffset;
			
		} catch (Exception e) {
		}

		return dateToReturn;
	}
	
	public static long convertUTCDateTimeToLocal(Date date, String timeZoneID) {
		
		long dateToReturn = 0;
		try {
			
			long utcTime = date.getTime();
			
			TimeZone localetz = TimeZone.getTimeZone(timeZoneID); /* ex KOREA = "Asia/Seoul" */
			
			long tzOffset = localetz.getOffset(utcTime);
			
			long localTime = utcTime + tzOffset;
			
			dateToReturn = localTime;

		} catch (Exception e) {
		}

		return dateToReturn;
	}
	
	public static Date getUTCTime()
	{
		TimeZone tz = TimeZone.getDefault();
		Date utc = new Date( new Date().getTime() - tz.getRawOffset() );
		
		return utc;
	}
}