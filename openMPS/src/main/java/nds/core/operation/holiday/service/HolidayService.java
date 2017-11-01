package nds.core.operation.holiday.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : HolidayService </b>
 * <b>Class Description</b><br>
 * 휴일 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface HolidayService extends Service {
    
	/**
	 * 휴일 수정
	 * @param holidayVO
	 * @throws Exception
	 */    
	void updateHday(HolidayVO holidayVO) throws Exception;   
	   
    /**
     * 휴일 월전체수정
     * @param holidayVO
     * @throws Exception
     */    
    void updateHdayAll(HolidayVO holidayVO) throws Exception;

    /**
     * 휴일 생성
     * @param holidayVO
     * @throws Exception
     */    
    void callSpMakeHday(HolidayVO holidayVO) throws Exception;   
    
    /**
     * 휴일 총건수 조회
     * @param holidayVO
     * @return int
     * @throws Exception
     */
    int selectHdayCount(HolidayVO holidayVO) throws Exception;
    
    /**
    * 휴일 리스트 조회
    * @param holidayVO
    * @return List
    * @throws Exception
    */
   List selectHdayList(HolidayVO holidayVO) throws Exception;
   
   /**
    * 사용자 휴일 조회
    * @param holidayVO
    * @return List
    * @throws Exception
    */    
   List selectUserWorkHdayResult(HolidayVO holidayVO) throws Exception;
   
   /**
    * 사용자 휴일 조회
    * @param holidayVO
    * @return int
    * @throws Exception
    */    
   int selectUserWorkHdayResultCount(HolidayVO holidayVO) throws Exception;
   
   /**
    * 휴일 시간조회
    * @param 
    * @return 
    * @throws Exception
    */
   HolidayVO selectHdayTime(HolidayVO holidayVO) throws Exception ;
   
   /**
    * 휴일사유입력
    * @param holidayVO
    * @throws Exception
    */    
   void updateHdayResn(HolidayVO holidayVO) throws Exception;

   /**
    * 휴일등록
    * @param holidayVO
    * @throws Exception
    */    
   void insertHdayResn(HolidayVO holidayVO) throws Exception;

   /**
    * 휴일삭제
    * @param holidayVO
    * @throws Exception
    */    
   void deleteHdayResn(HolidayVO holidayVO) throws Exception;
    
}
