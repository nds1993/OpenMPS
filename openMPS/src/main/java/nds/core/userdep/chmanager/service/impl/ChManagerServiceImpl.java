package nds.core.userdep.chmanager.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.userdep.chmanager.service.ChManagerService;
import nds.core.userdep.chmanager.service.ChManagerVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : ChManagerServiceImpl </b>
 * <b>Class Description</b><br>
 * 채널관리자 관리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2015.05.12 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("chManagerService")
public class ChManagerServiceImpl extends AbstractServiceImpl implements ChManagerService {
    
	@Resource(name="chManagerDAO")
    private ChManagerDAO chManagerDAO;
    
    /**
     * 채널관리자 추가
     * @param chManagerVO
     * @throws Exception
     */
	public void insertChnlManager(ChManagerVO chManagerVO) throws Exception {
        try{
            chManagerDAO.insertChnlManager(chManagerVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateChnlManager() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"채널관리자 수정",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 채널관리자 삭제
     * @param chManagerVO
     * @throws Exception
     */
	public void deleteChnlManager(ChManagerVO chManagerVO) throws Exception {
        try{
        	ChManagerVO result = chManagerDAO.selectChnlManagerUserList(chManagerVO);
        	
        	String mstSubArr[] = result.getMstSub().split(",");
        	String chnlCdArr[] = result.getChnlCd().split(",");
        	
        	String mstSubResult = "";
        	String chnlCdResult = "";
        	
        	int exist = 0;
        	
        	for(int i = 0; i < chnlCdArr.length-1; i++) {
        		if(chManagerVO.getChnlCd().split(",")[0].equals(chnlCdArr[i])) {
        			continue;
        		}
        		mstSubResult = mstSubResult + mstSubArr[i] + ",";
        		chnlCdResult = chnlCdResult + chnlCdArr[i] + ",";
        		
        		exist++;
        	}
        	
        	if(exist == 0) {
        		chManagerDAO.deleteChnlManager(chManagerVO);
        	} else {
        		chManagerVO.setMstSub(mstSubResult);
            	chManagerVO.setChnlCd(chnlCdResult);
            	
                chManagerDAO.updateChnlManager(chManagerVO);
        	}
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteChnlManager() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"채널관리자 삭제",ex.getMessage()} 
            );
        }        
    }
	
    /**
     * 채널 조회
     * @param chManagerVO
     * @return List
     * @throws Exception
     */
	public List selectChnlList(ChManagerVO chManagerVO) throws Exception {
    	List list = null;
        try{
            list = chManagerDAO.selectChnlList(chManagerVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChnlList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"채널 조회",ex.getMessage()} 
            );
        }        
        return list;
    }

    /**
     * 채널관리자 조회
     * @param chManagerVO
     * @return List
     * @throws Exception
     */
	public List selectChnlManagerList(ChManagerVO chManagerVO) throws Exception {
    	List list = null;
        try{
            list = chManagerDAO.selectChnlManagerList(chManagerVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChnlManagerList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"채널관리자 조회",ex.getMessage()} 
            );
        }        
        return list;
    }

    /**
     * 채널 정부 건수 조회
     * @param chManagerVO
     * @return int
     * @throws Exception
     */
	public int selectChnlMstCount(ChManagerVO chManagerVO) throws Exception {
		int count = 0;
        try{
        	count = chManagerDAO.selectChnlMstCount(chManagerVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChnlMstCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"채널 정부 건수 조회",ex.getMessage()} 
            );
        }        
        return count;
    }

    /**
     * 채널 사용자 건수 조회
     * @param chManagerVO
     * @return int
     * @throws Exception
     */
	public int selectChnlUserCount(ChManagerVO chManagerVO) throws Exception {
    	int count = 0;
        try{
        	count = chManagerDAO.selectChnlUserCount(chManagerVO); 
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChnlUserCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"채널 사용자 건수 조회",ex.getMessage()} 
            );
        }        
        return count;
    }
}