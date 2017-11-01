package nds.core.logsearch.dwloadlog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.logsearch.dwloadlog.service.DwloadLogService;
import nds.core.logsearch.dwloadlog.service.DwloadLogVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : DwloadLogServiceImpl </b>
 * <b>Class Description</b><br>
 * 다운로드로그 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2015.05.20 초기작성(김세희)</pre>
 * @author <a href="mailto:shkim@nds.co.kr">김세희</a>
 * @version 1.0
 */
@Service("dwloadLogService")
public class DwloadLogServiceImpl extends AbstractServiceImpl implements DwloadLogService {

	@Resource(name="dwloadLogDAO")
	private DwloadLogDAO dwloadLogDAO;

	 /**
     *  다운로드로그조회 
     * @param dwloadLogVO
     * @return List
     * @throws Exception
     */
    public List selectDwInfoLogList(DwloadLogVO dwloadLogVO) throws Exception {
        List list = null;
        try{
            list = dwloadLogDAO.selectDwInfoLogList(dwloadLogVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDwInfoLogList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"다운로드로그조회",ex.getMessage()} 
            );
        }        
        return list;
    } 

    /**
     * 다운로드로그조회  총건수
     * @param dwloadLogVO
     * @return int
     * @throws Exception
     */    
    public int selectDwInfoLogListCnt(DwloadLogVO dwloadLogVO) throws Exception {
        int intTotalCount = 0;
        try{

            intTotalCount = dwloadLogDAO.selectDwInfoLogListCnt(dwloadLogVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDwInfoLogListCnt() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"다운로드로그조회 총건수 ",ex.getMessage()} 
            );
        }        

        return intTotalCount;
    }
    
        
    /**
     * 다운로드로그조회 로그저장
     * @param key
     * @return List
     * @throws Exception
     */
    public void insertDwLogInfo(DwloadLogVO dwloadLogVO) throws Exception{
        try{
        	dwloadLogDAO.insertDwLogInfo(dwloadLogVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertDwLogInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"다운로드로그조회 로그 저장 중",ex.getMessage()} 
            );
        }
    }


	
}
