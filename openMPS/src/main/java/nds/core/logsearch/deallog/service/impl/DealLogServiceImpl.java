package nds.core.logsearch.deallog.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.logsearch.deallog.service.DealLogService;
import nds.core.logsearch.deallog.service.DealLogVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : DealLogServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2012.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("dealLogService")
public class DealLogServiceImpl extends AbstractServiceImpl implements DealLogService {
    
	@Resource(name="dealLogDAO")
    private DealLogDAO dealLogDAO;
    
    /**
     *  예외로그조회
     * @param DealLogVO
     * @return List
     * @throws Exception
     */
    public List selectExcpLogInfoList(DealLogVO dealLogVO) throws Exception {
        List list = null;
        try{
            list = dealLogDAO.selectExcpLogInfoList(dealLogVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectExcpLogInfoList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"예외로그조회",ex.getMessage()} 
            );
        }        
        return list;
    } 
            
    /**
     * 예외로그조회  총건수
     * @param DealLogVO
     * @return int
     * @throws Exception
     */    
    public int selectExcpLogInfoListCount(DealLogVO dealLogVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = dealLogDAO.selectExcpLogInfoListCount(dealLogVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectExcpLogInfoListCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"예외로그조회 총건수 ",ex.getMessage()} 
            );
        }        

        return intTotalCount;
    }

}