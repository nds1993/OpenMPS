package nds.core.userdep.department.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : DepartMentService </b>
 * <b>Class Description</b><br>
 * 부서 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface DepartMentService extends Service {
    

	   /**
	    * 부서 등록
	    * @param departMentVO
	    * @throws Exception
	    */
	   void insertDep(DepartMentVO departMentVO) throws Exception;
	   
	   /**
	    * 부서 수정
	    * @param departMentVO
	    * @throws Exception
	    */
	   void updateDep(DepartMentVO departMentVO) throws Exception;
	   
	   /**
	    * 부서 삭제
	    * @param departMentVO
	    * @throws Exception
	    */
	   void deleteDep(DepartMentVO departMentVO) throws Exception;
	   
	   /**
	    * 부서 조회
	    * @return List
	    * @throws Exception
	    */
	   List selectOrganization(DepartMentVO departMentVO) throws Exception;
	   
	   /**
	    * 부서 건수조회
	    * @param departMentVO
	    * @return int
	    * @throws Exception
	    */
	   int selectDepCount(DepartMentVO departMentVO) throws Exception;
	   
}
