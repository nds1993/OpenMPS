package nds.core.userdep.department.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.userdep.department.service.DepartMentService;
import nds.core.userdep.department.service.DepartMentVO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : LoginServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("departMentService")
public class DepartMentServiceImpl extends AbstractServiceImpl implements DepartMentService {
    
	@Resource(name="departMentDAO")
    private DepartMentDAO departMentDAO;
    
    /**
     * 부서 등록
     * @param tbvcMenu
     * @throws MainException
     */
	public void insertDep(DepartMentVO departMentVO) throws MainException {
		try {
			departMentDAO.insert(departMentVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertDep() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서 등록",ex.getMessage()} 
            );
		}    	
	}
    /**
     * 부서 수정
     * @param tbvcMenu
     * @throws MainException
     */
	public void updateDep(DepartMentVO departMentVO) throws MainException {
		try {
			departMentDAO.updateDep(departMentVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateDep() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서 수정",ex.getMessage()} 
            );
		}    	
	}
	
    /**
     * 부서 삭제
     * @param contsId
     * @throws MainException
     */
	public void deleteDep(DepartMentVO departMentVO) throws MainException {
		try {
			departMentDAO.deleteDep(departMentVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteDep() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서 삭제",ex.getMessage()} 
            );
		}    	
	}
	
    /**
     * 부서 조회
     * @return List
     * @throws MainException
     */
	public List selectOrganization(DepartMentVO departMentVO) throws MainException {
		List list = null;
		try {
			list = departMentDAO.selectOrganization(departMentVO);
		} catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepAll() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"컨텐츠트리 조회",ex.getMessage()} 
            );
		}
		return list;
	}
	
    /**
     * 부서 건수조회
     * @param tbvcMenu
     * @return int
     * @throws MainException
     */
    public int selectDepCount(DepartMentVO departMentVO) throws MainException {
        int intTotalCount = 0;
        try{
        	intTotalCount = departMentDAO.selectDepCount(departMentVO);
		}catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서  건수조회",ex.getMessage()} 
            );
		}        

        return intTotalCount;
    }
    
}