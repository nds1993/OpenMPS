package nds.core.operation.approval.service.impl;



import java.util.List;

import javax.annotation.Resource;

import nds.core.operation.approval.service.ApprovalService;
import nds.core.operation.approval.service.ApprovalVO;
import nds.frm.exception.ExceptionHelper;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



/**
 * <p>Title: ApprovalServiceImpl</p>
 * <p>Description: Service Implements Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("approvalService")
public class ApprovalServiceImpl  extends AbstractServiceImpl implements ApprovalService {
    
	@Resource(name="approvalDAO")
    private ApprovalDAO approvalDAO;

	/**
     * 승인단계 등록
     * @param approvalVO
     * @return String
     * @throws Exception
     */
    public void insertApvMg(List<ApprovalVO> approvalVO) throws Exception{
        try{
        	if(approvalVO.size()>0){
        		approvalDAO.deleteAllApvMg(approvalVO.get(0).getUserEmpno());
        	}
        	for (int i = 0; i < approvalVO.size(); i++) {
        		approvalDAO.insertApvMg(approvalVO.get(i));
			}
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertApvMg() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 등록 중",ex.getMessage()} 
            );
        }
    }
    
    
    /**
     * 승인단계 수정
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    public void updateApvMg(ApprovalVO approvalVO) throws Exception{
        try{
            approvalDAO.updateApvMg(approvalVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateApvMg() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 수정 중",ex.getMessage()} 
            );
        }
    }
    
    
    /**
     * 전체 승인단계 삭제
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    public void deleteAllApvMg(String userEmpno) throws Exception{
        try{
            approvalDAO.deleteAllApvMg(userEmpno);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteAllApvMg() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 삭제 중",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 단일 승인단계 삭제
     * @param approvalVO
     * @return void
     * @throws Exception
     */
    public void deleteApvMg(ApprovalVO approvalVO) throws Exception{
        try{
            approvalDAO.deleteApvMg(approvalVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "deleteApvMg() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 삭제 중",ex.getMessage()} 
            );
        }
    }

    /**
     * 승인단계 목록조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    public List<ApprovalVO> selectApvMgList(String userEmpno) throws Exception{
        List<ApprovalVO> list  =  null;
        try {       
            list = approvalDAO.selectApvMgList(userEmpno);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectApvMgList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 목록조회 중",ex.getMessage()} 
            );
        }
        
        return list;
    }
    
    /**
     * 승인단계 목록조회 cnt
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    public int selectApvMgListCount(String userEmpno) throws Exception{
        int count  =  0;
        try {       
            count   =   approvalDAO.selectApvMgListCount(userEmpno);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCountApvMg() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 목록 count 조회 중",ex.getMessage()} 
            );
        }
        
        return count;
    }
    
    /**
     * 승인단계 목록조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    public List<ApprovalVO> selectApvStagList(String userEmpno) throws Exception{
        List<ApprovalVO> list  =  null;
        try {       
            list = approvalDAO.selectApvStagList(userEmpno);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectApvMgList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 목록조회 중",ex.getMessage()} 
            );
        }
        
        return list;
    }
    
    /**
     * 승인단계 목록조회 cnt
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    public int selectApvStagListCount(String userEmpno) throws Exception{
        int count  =  0;
        try {       
            count   =   approvalDAO.selectApvStagListCount(userEmpno);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCountApvMg() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인단계 목록 count 조회 중",ex.getMessage()} 
            );
        }
        
        return count;
    }
    
    /**
     * 단계별 승인단계 조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    public List<ApprovalVO> selectByApvMgList(ApprovalVO approvalVO) throws Exception{
        List<ApprovalVO> list  =  null;
        try {       
            list   =   approvalDAO.selectByApvMgList(approvalVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByApvMgList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"단계별 승인단계 조회 중",ex.getMessage()} 
            );
        }
        
        return list;
    }
    
    /**
     * 대상 사용자 조회
     * @param approvalVO
     * @return List
     * @throws Exception
     */
    public List<ApprovalVO> selectUserList(ApprovalVO approvalVO) throws Exception{
        List<ApprovalVO> list  =  null;
        try {       
            list   =   approvalDAO.selectUserList(approvalVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByApvMgList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"대상 사용자 조회",ex.getMessage()} 
            );
        }
        
        return list;
    }
    
    /**
     * 대상 사용자 조회 cnt
     * @param approvalVO
     * @return int
     * @throws Exception
     */
    public int selectUserListCount(ApprovalVO approvalVO) throws Exception{
        int count  =  0;
        try {       
            count   =   approvalDAO.selectUserListCount(approvalVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCountApvMg() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"대상 사용자 count 조회 중",ex.getMessage()} 
            );
        }
        
        return count;
    }
    
}