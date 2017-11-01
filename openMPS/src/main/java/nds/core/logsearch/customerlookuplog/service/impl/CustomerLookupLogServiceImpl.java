package nds.core.logsearch.customerlookuplog.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.logsearch.customerlookuplog.service.CustomerLookupLogService;
import nds.core.logsearch.customerlookuplog.service.CustomerLookupLogVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : UserServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("customerLookupLogService")
public class CustomerLookupLogServiceImpl extends AbstractServiceImpl implements CustomerLookupLogService {
    
	@Resource(name="customerLookupLogDAO")
    private CustomerLookupLogDAO customerLookupLogDAO;
    

    /**
     *  고객정보조회 
     * @param customerLookupLogVO
     * @return List
     * @throws Exception
     */
    public List selectCstInfoLogList(CustomerLookupLogVO customerLookupLogVO) throws Exception {
        List list = null;
        try{
            list = customerLookupLogDAO.selectCstInfoLogList(customerLookupLogVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstInfoLogList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객정보조회",ex.getMessage()} 
            );
        }        
        return list;
    } 

    /**
     * 고객정보조회  총건수
     * @param customerLookupLogVO
     * @return int
     * @throws Exception
     */    
    public int selectCstInfoLogListCnt(CustomerLookupLogVO customerLookupLogVO) throws Exception {
        int intTotalCount = 0;
        try{

            intTotalCount = customerLookupLogDAO.selectCstInfoLogListCnt(customerLookupLogVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstInfoLogListCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객정보조회 총건수 ",ex.getMessage()} 
            );
        }        

        return intTotalCount;
    }
    
    /**
     * 화면명 조회
     * @param key
     * @return List
     * @throws Exception
     */
    public CustomerLookupLogVO selectMenuNm(String key) throws Exception{
    	CustomerLookupLogVO result = null;
        try{
            result = customerLookupLogDAO.selectMenuNm(key);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMenuNm() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"화면명 조회 중",ex.getMessage()} 
            );
        }         
        return result;
    }
    
    /**
     * 고객정보조회 로그저장
     * @param key
     * @return List
     * @throws Exception
     */
    public void insertCstLogInfo(CustomerLookupLogVO customerLookupLogVO) throws Exception{
        try{
        	customerLookupLogDAO.insertCstLogInfo(customerLookupLogVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertCstLogInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객정보조회 로그 저장 중",ex.getMessage()} 
            );
        }
    }
    
}