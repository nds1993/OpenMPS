package nds.frm.startup;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import nds.frm.util.StringUtil;



public class SYSTEM {
    InitialContext  ctx;
    DataSource      ds;
    Connection      conn;
    private String  date;
    private String  year;
    private String  month;
    private String  day;
    private String  time;
    private String  ampm;	//오전오후
    private String  hour;	//시
    private String  hour24;	//시(24시)
    private String  minute;	//분
    private String  second; //초
    private String yoil;

    private SYSTEM() {
        try {
            ds = VocDataSource.getDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static final SYSTEM INSTANCE = new SYSTEM();

    public static synchronized SYSTEM getInstance() {
        return INSTANCE;
    }

    public String getDate() {
        return date;
    }        

    public String getDateFormat() {
        return StringUtil.getDateForm(date);
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Connection getConnection() {
        try {
            conn = ds.getConnection();
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return conn;
    }
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	
	public String getYoil() {
		return yoil;
	}
	public void setYoil(String yoil) {
		this.yoil = yoil;
	}

	public String getHour24() {
		return hour24;
	}

	public void setHour24(String hour24) {
		this.hour24 = hour24;
	}
}
