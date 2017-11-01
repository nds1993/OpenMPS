package nds.frm.util;

//java API
import java.util.Calendar;

import nds.frm.startup.SYSTEM;


/**
 * <b>class : </b> DateTimeUtil
 * <b>Class Description</b><br>
 * Date Time에 관한 처리를 담당하는 Util Class
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@naver.com">임진식</a>
 * @version 1.0
 */
public class DateTimeUtil {

    public DateTimeUtil() {}

    public static int[] getNowDate()
    {
        int[] nowDate = new int[3];
        
        String year = SYSTEM.getInstance().getYear();
        String month =  SYSTEM.getInstance().getMonth();
        String day = SYSTEM.getInstance().getDay();
        
        nowDate[0] = Integer.parseInt(year);
        nowDate[1] = Integer.parseInt(month);
        nowDate[2] = Integer.parseInt(day);
        
        return nowDate;
    }    
   
    /**
     * 현재 시간을 가져온다.
     * 예)오후 12시 30분 10초 : PM123010
     *
     * @return 현재 시간
     */
    public static int[] getCurrentTime() {
        int[] time = new int[3];
        
        time[0] = Integer.parseInt(SYSTEM.getInstance().getHour());
        time[1] = Integer.parseInt(SYSTEM.getInstance().getMinute());
        time[2] = Integer.parseInt(SYSTEM.getInstance().getSecond());
        
        return time;
    }
    
    
    public static String makeItemDate(String name, String option, String selectedValue, int yyyyLen, String orderBy, String type)
    {
        if (type.toUpperCase().equals("YYYY"))
            return getYYYYSelect(name, option, selectedValue, yyyyLen, orderBy, "");
        else if (type.toUpperCase().equals("MM"))
            return getMMSelect(name, option, selectedValue, "");
        else 
            return "";
              
    }
    
    public static String makeItemDate(String name, String option, String selectedValue, int yyyyLen, String orderBy, String type, String shape)
    {
        if (type.toUpperCase().equals("YYYY"))
            return getYYYYSelect(name, option, selectedValue, yyyyLen, orderBy, shape);
        else if (type.toUpperCase().equals("MM"))
            return getMMSelect(name, option, selectedValue, shape);
        else 
            return "";
              
    }
    
    /**
     * 년도 목록
     *  
     * @param name    select name
     * @param option    select style or action
     * @param selectedValue selected value
     * @param yyyyLen 표시될 년도 수
     * @param 년도 정렬순서
     * @return getYYYY      년도 목록
     * @author <a href="mailto:jsyim1@naver.com">임진식</a>
     */
     public static String getYYYYSelect (String name, String option, String selectedValue, int yyyyLen, String orderBy, String shape)
     {
         String getYYYY = "";
         
         if (("none").equals(StringUtil.null2void(shape).toLowerCase()))
             getYYYY += ("<select id=\'"+name+"\' name=\'"+name+"\' "+option+">");
         else
             getYYYY += ("<script type='text/javascript' language='javascript'>NSelectEdit(\'" + name + "\',  \"<select id=\'"+name+"\' name=\'"+name+"\' "+option+">");
         
         int startIdx = 0;
         int endIdx = 0;
         
         if (orderBy.toUpperCase().equals("ASC"))
         {
             startIdx = getNowDate()[0];
             endIdx = getNowDate()[0] + yyyyLen;
         }
         else if (orderBy.toUpperCase().equals("DESC"))
         {
             startIdx = getNowDate()[0] - yyyyLen;
             endIdx = getNowDate()[0];
         }
         else
         {
             startIdx = getNowDate()[0] - yyyyLen;
             endIdx = getNowDate()[0] + yyyyLen;         
         }
       
         for(int i=startIdx; i <= endIdx; i++)
         { 
             String selected = "";
             if (selectedValue.length() > 0 && !selectedValue.equals(""))
             {
                 if (i == Integer.parseInt(selectedValue))
                     selected = "selected";
             }
             else if (i == getNowDate()[0])
                 selected = "selected";
             
             getYYYY += (" <option value=\'"+StringUtil.padLeft(String.valueOf(i), '0', 4)+"\' "+selected+">"+StringUtil.padLeft(String.valueOf(i), '0', 4)+"</option> ");
         }
         
         if (("none").equals(StringUtil.null2void(shape).toLowerCase()))
             getYYYY += (" </select>"); 
         else
             getYYYY += (" </select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>");

       return getYYYY;
     }  
     
     /**
      * 월 목록
      *  
      * @param name    select name
      * @param option    select style or action
      * @param selectedValue selected value
      * @return getMM      월 목록
      * @author <a href="mailto:jsyim1@naver.com">임진식</a>
      */
      public static String getMMSelect (String name, String option, String selectedValue, String shape)
      {
         String getMM = "";
         
         if (("none").equals(StringUtil.null2void(shape).toLowerCase()))
             getMM += ("<select id=\'"+name+"\' name=\'"+name+"\' "+option+">");
         else
             getMM += ("<script type='text/javascript' language='javascript'>NSelectEdit(\"" + name + "\", \"<select id=\'"+name+"\' name=\'"+name+"\' "+option+">");        
        
          for(int i=1; i <= 12; i++)
          { 
             String selected = "";
             if (selectedValue.length() > 0 && !selectedValue.equals(""))
             {
                 if (i == Integer.parseInt(selectedValue))
                     selected = "selected";
             }
             else if (i == getNowDate()[1])
                 selected = "selected";
             
            getMM += (" <option value=\'"+StringUtil.padLeft(String.valueOf(i), '0', 2)+"\' "+selected+">"+StringUtil.padLeft(String.valueOf(i), '0', 2)+"</option>");
          }          
          
         if (("none").equals(StringUtil.null2void(shape).toLowerCase()))
            getMM += (" </select>"); 
         else
             getMM += (" </select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>"); 
         
        return getMM;
      } 
      
    public static String makeItemTime(String name, String option, String selectedValue, String type)
    {
        if (type.toUpperCase().equals("HH"))
            return getHHSelect(name, option, selectedValue);
        else if (type.toUpperCase().equals("MI"))
            return getMISelect(name, option, selectedValue);
        else 
            return "";
              
    }
   
    /**
     * 시간 목록
     *  
     * @param    name    select name
     * @param    option    select style or action
     * @param   selectedValue selected value
     *
     * @return      getHH      시간 목록
     * @author <a href="mailto:jsyim1@naver.com">임진식</a>
     */
     public static String getHHSelect (String name, String option, String selectedValue)
     {
       String getHH = "";      

         getHH += ("<script type='text/javascript' language='javascript'>NSelectEdit(\"" + name + "\", \"<select id=\'"+name+"\' name=\'"+name+"\' "+option+">"); 

         for(int i=0; i <= 23; i++)
         { 
             String selected = "";
             if (selectedValue.length() > 0 && !selectedValue.equals(""))
             {
                 if (i == Integer.parseInt(selectedValue))
                     selected = "selected";
             }
             else if (i == getCurrentTime()[0])
                 selected = "selected";
             
             getHH += (" <option value=\'"+StringUtil.padLeft(String.valueOf(i), '0', 2)+"\' "+selected+">"+StringUtil.padLeft(String.valueOf(i), '0', 2)+"</option>");
         }
         
         getHH += (" </select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>"); 

       return getHH;
     }  
     
     /**
      * 분목록
      *  
      * @param    name    select name
      * @param    option    select style or action
      * @param  selectedValue selected value
      *
      * @return      getMI      분 목록
      * @author <a href="mailto:jsyim1@naver.com">임진식</a>
      */
      public static String getMISelect (String name, String option, String selectedValue)
      {
        String getMI = "";

          getMI += ("<script type='text/javascript' language='javascript'>NSelectEdit(\"" + name + "\", \"<select id=\'"+name+"\' name=\'"+name+"\' "+option+">"); 
    
          for(int i=0; i <= 59; i++)
          {
             String selected = "";
             if (selectedValue.length() > 0 && !selectedValue.equals(""))
             {
                 if (i == Integer.parseInt(selectedValue))
                     selected = "selected";
             }
             else if (i == getCurrentTime()[1])
                 selected = "selected";
             
             getMI += (" <option value=\'"+StringUtil.padLeft(String.valueOf(i), '0', 2)+"\' "+selected+">"+StringUtil.padLeft(String.valueOf(i), '0', 2)+"</option>");
          }
          
          getMI += (" </select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>"); 

        return getMI;
      }
     
      /**
       * n일 후 또는 전 날짜를 계산하는 하는 메소드
       * @param param     String 기준일자
       * @param           int n일(전일 경우 '-', 후일경우 없음)
       *                  예) 30일전 : -30, 10일 후 : 10
       * @return          계산된 날짜
       */
      public static String calculateDate(String sDate, int iDay) {
          
          if (sDate == null) {
              return "";
          }       
          //date 포멧제거
          sDate = StringUtil.delDash(sDate).trim();

          Calendar oDate = Calendar.getInstance();
          oDate.set(Integer.parseInt(sDate.substring(0,4)), Integer.parseInt(sDate.substring(4,6))-1, Integer.parseInt(sDate.substring(6)));

          // 날짜 계산
          oDate.add(Calendar.DATE, iDay);

          // 날짜 저장
          String year  = String.valueOf( oDate.get(Calendar.YEAR) );
          String month = StringUtil.padLeft(String.valueOf( oDate.get(Calendar.MONTH) + 1 ), '0', 2);
          String days  = StringUtil.padLeft(String.valueOf( oDate.get(Calendar.DATE) ), '0', 2);

          // Step4. 날짜 반환
          return year + month + days;

    }
      
      /**
       * n달 후  또는 전 달을 계산하는 하는 메소드 (추가: 최성기)
       * @param param     String 기준일자
       * @param           int n달(전일 경우 '-', 후일경우 없음)
       *                  예) 3달전 : -3, 5달 후 : 5
       * @return          계산된 날짜
       */
      public static String calculateMonth(String sDate, int imonth) {
          
          if (sDate == null) {
              return "";
          }       
          //date 포멧제거
          sDate = StringUtil.delDash(sDate).trim();

          Calendar oDate = Calendar.getInstance();
          oDate.set(Integer.parseInt(sDate.substring(0,4)), Integer.parseInt(sDate.substring(4,6))-1, Integer.parseInt(sDate.substring(6)));

          // 날짜 계산
          oDate.add(Calendar.MONTH, imonth);

          // 날짜 저장
          String year  = String.valueOf( oDate.get(Calendar.YEAR) );
          String month = StringUtil.padLeft(String.valueOf( oDate.get(Calendar.MONTH) + 1 ), '0', 2);
          String days  = StringUtil.padLeft(String.valueOf( oDate.get(Calendar.DATE) ), '0', 2);

          // Step4. 날짜 반환
          return year + month + days;

    }       
      
      /** 
       * <p>입력된 년월의 마지막 일수를 return한다
       * 
       * @param year
       * @param month
       * @return 마지막 일수  
       * <p><pre>
       *  - 사용 예
       * int date = DateTimeUtil.getLastDayOfMonth("200710")
       * </pre>
       */ 
      public static String getLastDayOfMonth(String yyyymm) {

          if (yyyymm == null) {
              return "";
          } 
          
          Calendar cal = Calendar.getInstance();
          int yyyy = Integer.parseInt(yyyymm.substring(0, 4));
          int mm = Integer.parseInt(yyyymm.substring(4)) - 1;
          
          cal.set(yyyy, mm, 1);
          
          int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
          
          String dd = StringUtil.padLeft(String.valueOf(endDay),'0',2);
          
          return dd;
      }      
}