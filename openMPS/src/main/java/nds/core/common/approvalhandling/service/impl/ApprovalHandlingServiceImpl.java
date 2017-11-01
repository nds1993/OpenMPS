package nds.core.common.approvalhandling.service.impl;




import java.util.List;

import javax.annotation.Resource;

import nds.core.common.approvalhandling.service.ApprovalHandlingService;
import nds.core.operation.approval.service.ApprovalVO;
import nds.core.userdep.user.service.UserVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : ApprovalHandlingServiceImpl </b>
 * <b>Class Description</b><br>
 * 승인처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2015.05.12 초기작성(김선범)</pre>
 * @author <a href="mailto:sbkim@nds.co.kr">김선범</a>
 * @version 1.0
 */
@Service("approvalHandlingService")
public class ApprovalHandlingServiceImpl extends AbstractServiceImpl implements ApprovalHandlingService {

	@Resource(name="approvalHandlingDAO")
    private ApprovalHandlingDAO approvalHandlingDAO;
	

    /**
     * 승인대기건 VOC 카운트 조회
     * 
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    public int selectChargeVocCount(ApprovalVO approvalVO) throws Exception {
        int count = 0;
        try {
            count = approvalHandlingDAO.selectChargeVocListCount(approvalVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChargeVOCCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인건 VOC 카운트 조회",ex.getMessage()} 
            );
        }
        return count;
    }

    /**
     * 승인대기건 조회
     * 
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    public List<ApprovalVO> selectChargeVocList(ApprovalVO approvalVO) throws Exception {
        List<ApprovalVO> list = null;
        try {
            list = approvalHandlingDAO.selectChargeVocList(approvalVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChargeStandList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대기건 조회",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 승인대기건 카운트 조회
     * 
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    public int selectChargeVocListCount(ApprovalVO approvalVO) throws Exception {
        int count = 0;
        try {
            count = approvalHandlingDAO.selectChargeVocListCount(approvalVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChargeVocListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대기건 카운트 조회",ex.getMessage()} 
            );
        }
        return count;
    }
    
    /**
     * 승인대기 VOC 상세조회
     * 
     * @param approvalVO
     * @return ApprovalVO
     * @throws Exception
     */
    public ApprovalVO selectChargeVoc(ApprovalVO approvalVO) throws Exception {
    	ApprovalVO result = null;
        try{
        	result = approvalHandlingDAO.selectChargeVoc(approvalVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChargeVoc() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대기 상세조회",ex.getMessage()} 
            );
        }       
        
        return result;
    }
    
    /**
     * 승인대기 제안상세조회
     * 
     * @param approvalVO
     * @return ApprovalVO
     * @throws Exception
     */
    public ApprovalVO selectChargeSug(ApprovalVO approvalVO) throws Exception {
    	ApprovalVO result = null;
        try{
        	result = approvalHandlingDAO.selectChargeSug(approvalVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectChargeSug() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대기 제안상세조회",ex.getMessage()} 
            );
        }       
        
        return result;
    }
    

	
    /**
     * 승인대기 제안승인
     * 
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    public void callApvInfoProc(ApprovalVO approvalVO) throws Exception {
        try{
        	
        	approvalHandlingDAO.callApvInfoProc(approvalVO);

        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "callApvInfoProc() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대기 승인",ex.getMessage()} 
            );
        }
    }
    
	/**
     * 승인대기 단계조회
     * 
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    public List<ApprovalVO> selectApvStag(ApprovalVO approvalVO) throws Exception {
        List<ApprovalVO> list = null;
        try {
            list = approvalHandlingDAO.selectApvStag(approvalVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectApvStag() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대기 단계조회",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 승인대상자 목록 조회 (알림메시지 발송용)
     * 
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    public List<UserVO> selectApvAlramList(ApprovalVO approvalVO) throws Exception {
        List<UserVO> list = null;
        try {
            list = approvalHandlingDAO.selectApvAlramList(approvalVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectApvAlramList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대상자 목록 조회 (알림메시지 발송용)",ex.getMessage()} 
            );
        }
        return list;
    }

    /**
     * 제안 승인 요청
     * @param ApprovalVO
     * @return void
     * @throws Exception
     */
    public void insertPropApvInfo(ApprovalVO approvalVO) throws Exception{
        try {
        	approvalHandlingDAO.insertPropApvInfo(approvalVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertPropApvInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안 승인 요청",ex.getMessage()} 
            );
        }
    }
    
}
