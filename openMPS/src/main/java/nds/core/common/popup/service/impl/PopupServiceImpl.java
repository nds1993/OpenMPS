package nds.core.common.popup.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.common.common.service.AttachFileVO;
import nds.core.common.common.service.NeedsMstVO;
import nds.core.common.popup.service.CnslTypeVO;
import nds.core.common.popup.service.PopupAcepnoVO;
import nds.core.common.popup.service.PopupApprovalVO;
import nds.core.common.popup.service.PopupCnslTypeVO;
import nds.core.common.popup.service.PopupCustInfoVO;
import nds.core.common.popup.service.PopupCustMemoVO;
import nds.core.common.popup.service.PopupHelpReqInfoVO;
import nds.core.common.popup.service.PopupNeedsChngHistVO;
import nds.core.common.popup.service.PopupPrductVO;
import nds.core.common.popup.service.PopupPropAsgnHistVO;
import nds.core.common.popup.service.PopupPropChngHistVO;
import nds.core.common.popup.service.PopupService;
import nds.core.common.popup.service.PopupVendorVO;
import nds.core.common.popup.service.PopupWidgetSetVO;
import nds.core.operation.approval.service.ApprovalVO;
import nds.core.operation.approval.service.impl.ApprovalDAO;
import nds.core.userdep.department.service.DepartMentVO;
import nds.core.userdep.department.service.impl.DepartMentDAO;
import nds.core.userdep.user.service.UserVO;
import nds.core.userdep.user.service.impl.UserDAO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : PopupServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통팝업처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("popupService")
public class PopupServiceImpl extends AbstractServiceImpl implements PopupService {
    
	@Resource(name="popupDAO")
    private PopupDAO popupDAO;
	
	@Resource(name="departMentDAO")
    private DepartMentDAO departMentDAO;
	
	@Resource(name="userDAO")
    private UserDAO userDAO;
    
	@Resource(name="approvalDAO")
    private ApprovalDAO approvalDAO;
	
	
	/**
	 * 제품분류 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public List<PopupPrductVO> selectPrductType(PopupPrductVO popupPrductVO) throws Exception{
		
	
		List<PopupPrductVO> list = null;
		try {
			list = popupDAO.selectPrductType(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPrductType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제품분류 조회",ex.getMessage()} 
            );
        }
		return list;
	}
	
	/**
	 * 제품 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public List<PopupPrductVO> selectPrductList(PopupPrductVO popupPrductVO) throws Exception{
		List<PopupPrductVO> list = null;
		try {
			list = popupDAO.selectPrductList(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPrductList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제품 조회",ex.getMessage()} 
            );
        }
		return list;
	}
	
	/**
	 * 자재유형별 자재조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public List<PopupPrductVO> selectPrductList_pType(PopupPrductVO popupPrductVO) throws Exception{
		List<PopupPrductVO> list = null;
		try {
			list = popupDAO.selectPrductList_pType(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPrductList_pType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"자재유형별 조회",ex.getMessage()} 
            );
        }
		return list;
	}
	/**
	 * 자재유형별 자재조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public int selectPrductList_pTypeCount(PopupPrductVO popupPrductVO) throws Exception{
		
		int count = 0;
		try {
			count = popupDAO.selectPrductList_pTypeCount(popupPrductVO);
	    } catch (Exception ex) {
	        throw ExceptionHelper.getException(ex
	                , this.getClass().getName() + " : " + "selectPrductList_pTypeCount() 에러 발생"
	                ,"SYS001"
	                ,new Object[] {"제품 건수 조회",ex.getMessage()} 
	        );
	    }
		return count;
	}
	
	
	public List<PopupPrductVO> selectPrductList_Halb(PopupPrductVO popupPrductVO) throws Exception{
		List<PopupPrductVO> list = null;
		try {
			list = popupDAO.selectPrductList_Halb(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPrductList_Halb() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제품 조회",ex.getMessage()} 
            );
        }
		return list;
	}
	
	/**
	 * 제품 즐겨찾기 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public List<PopupPrductVO> selectProdBkmkList(PopupPrductVO popupPrductVO) throws Exception{
		List<PopupPrductVO> list = null;
		try {
			list = popupDAO.selectProdBkmkList(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectProdBkmkList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제품 즐겨찾기 조회",ex.getMessage()} 
            );
        }
		return list;
	}
	/**
	 * 제품 즐겨찾기 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public List<PopupVendorVO> selectVendorList(PopupVendorVO popupVendorVO) throws Exception{
		List<PopupVendorVO> list = null;
		try {
			list = popupDAO.selectVendorList(popupVendorVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectVendorList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제품 즐겨찾기 조회",ex.getMessage()} 
            );
        }
		return list;
	}
	public int selectVendorCount(PopupVendorVO popupVendorVO) throws Exception{
		
		int count = 0;
		try {
			count = popupDAO.selectVendorCount(popupVendorVO);
	    } catch (Exception ex) {
	        throw ExceptionHelper.getException(ex
	                , this.getClass().getName() + " : " + "selectVendorCount() 에러 발생"
	                ,"SYS001"
	                ,new Object[] {"제품 건수 조회",ex.getMessage()} 
	        );
	    }
		return count;
	}
	/**
	 * 제품 건수 조회
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public int selectPrductListCount(PopupPrductVO popupPrductVO) throws Exception{
		int count = 0;
		try {
			count = popupDAO.selectPrductListCount(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPrductListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제품 건수 조회",ex.getMessage()} 
            );
        }
		return count;
	}
	
	public int selectPrductList_HalbCount(PopupPrductVO popupPrductVO) throws Exception{
		int count = 0;
		try {
			count = popupDAO.selectPrductList_HalbCount(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPrductList_HalbCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제품 건수 조회",ex.getMessage()} 
            );
        }
		return count;
	}
	
	/**
	 * 클레임 접수번호 조회(팝업)
	 * @param popupAcepnoVO
	 * @return
	 */
	public List<PopupAcepnoVO> selectClaimList(PopupAcepnoVO popupAcepnoVO) throws Exception{
		List<PopupAcepnoVO> list = null;
        try {
        	list = popupDAO.selectClaimList(popupAcepnoVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectClaimList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {" 클레임 접수번호 조회(팝업)",ex.getMessage()} 
            );
        }
        return list;
	}
	
	/**
	 * 클레임 접수번호 건수 조회(팝업)
	 * @param popupAcepnoVO
	 * @return
	 */
	public int selectClaimListCount(PopupAcepnoVO popupAcepnoVO) throws Exception{
		int count = 0;
		try {
			count = popupDAO.selectClaimListCount(popupAcepnoVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectClaimListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"클레임 접수번호 건수 조회(팝업)",ex.getMessage()} 
            );
        }
		return count;
	}
	
	/**
	 * 대표제품 검색
	 * @param popupPrductVO
	 * @return
	 * @throws Exception
	 */
	public List<PopupPrductVO> selectPrductBossType(PopupPrductVO popupPrductVO)throws Exception{
		List<PopupPrductVO> list = null;
        try {
        	list = popupDAO.selectPrductBossType(popupPrductVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPrductBossType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {" 대표제품 검색",ex.getMessage()} 
            );
        }
        return list;
	}
	
    /**
     * 메인 위젯 설정
     * @param popupWidgetSetVO
     * @return void
     * @throws Exception
     */
    public void insertMaWidgets(PopupWidgetSetVO popupWidgetSetVO) throws Exception{
        try{
        	popupDAO.insertMaWidgets(popupWidgetSetVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertMaWidgets() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메인 위젯 설정",ex.getMessage()} 
            );
        }
    }

    /**
     * 메인 위젯 설정
     * @param key
     * @return void
     * @throws Exception
     */
    public void deleteMaWidgets(String key) throws Exception{
        try{
        	popupDAO.deleteMaWidgets(key);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertMaWidgets() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메인 위젯 설정",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 개인별 메인위젯설정 목록
     * @param key
     * @return List
     * @throws Exception
     */
    public List<PopupWidgetSetVO> selectMaWidgetsList(String key) throws Exception {
    	List<PopupWidgetSetVO> list = null;
    	try{
    		list = popupDAO.selectMaWidgetsList(key);
    	}catch(Exception ex){
    		throw ExceptionHelper.getException(ex
    				, this.getClass().getName() + " : " + "selectMaWidgetsList() 에러 발생"
    						,"SYS001"
    						,new Object[] {"개인별 메인위젯설정 목록 조회중",ex.getMessage()} 
    				);
    	}         
    	
    	return list;
    }
    
    /**
     * 조직정보조회
     * @param depCd
     * @return List
     * @throws Exception
     */
    public List selectOrgTree(String depCd) throws Exception{
        List list = null;
        try{
            list = departMentDAO.selectOrgTree(depCd);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectOrgTree() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"조직정보조회 중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 조직정보조회(트리)
     * @param departMentVO
     * @return List<DepartMentVO>
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public List<DepartMentVO> selectDepTree(DepartMentVO departMentVO) throws Exception{
    	List<DepartMentVO> list = null;
    	try{
    		list = departMentDAO.selectOrganization(departMentVO);
    	}catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepTree() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"조직정보조회(트리) 중",ex.getMessage()} 
            );
        }       
    	return list;
    }
    
    
    
    /**
     * 부서찾기 조회
     * @param departMentVO
     * @return List
     * @throws Exception
     */
    public List<DepartMentVO> selectDepList(DepartMentVO departMentVO) throws Exception {
        List<DepartMentVO> list = null;
        try{
            list = departMentDAO.selectByHelper(departMentVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서찾기 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    /**
     * 부서찾기 조회
     * @param departMentVO
     * @return int
     * @throws Exception
     */
    public int selectDepListCount(DepartMentVO departMentVO) throws Exception {
        int count = 0;
        try{
            count = departMentDAO.selectCountByHelper(departMentVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectDepListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"부서찾기 조회중",ex.getMessage()} 
            );
        }         

        return count;
    }
	/**
	 * 사용자 조회
	 * @param userVO
	 * @return List
	 * @throws Exception
	 */
    public List selectCustList(UserVO userVO) throws Exception {
		List list = null;
		try {
			list = userDAO.selectCustList(userVO);
		} catch (Exception ex) {
			throw ExceptionHelper.getException(ex
					, this.getClass().getName() + " : " + "selectCustList() 에러 발생"
					,"SYS001"
					,new Object[] {"사용자 조회",ex.getMessage()} 
			);
		}
		return list;
	}
	
    /**
     * 사용자 건수조회
     * @param userVO
     * @return int
     * @throws Exception
     */
    public int selectCustListCount(UserVO userVO) throws Exception {
    	int intTotalCount = 0;
    	try{
    		intTotalCount = userDAO.selectCustListCount(userVO);
		}catch(Exception ex){
           throw ExceptionHelper.getException(ex
                   , this.getClass().getName() + " : " + "selectCustListCount() 에러 발생"
                   ,"SYS001"
                   ,new Object[] {"사용자  건수조회",ex.getMessage()} 
           );
		}        

       return intTotalCount;
    }
	   
    /**
     * 승인처리
     * @param vocId
     * @return int
     * @throws Exception
     */
    public int updateApproVal(String vocId) throws Exception {
        int count = 0;
        try {
            count = popupDAO.updateApproVal(vocId);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateApproVal() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인처리",ex.getMessage()} 
            );
        }
        return count;
    }
    
    /**
     * 상담유형코드조회
     * @param popupCnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<PopupCnslTypeVO> selectCnslTypeCdList(PopupCnslTypeVO popupCnslTypeVO) throws Exception{
        List<PopupCnslTypeVO> list = null;
        try{
            list = popupDAO.selectCnslTypeCdList(popupCnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslTypeCdList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"상담유형 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 상담유형코드 건수조회
     * @param popupCnslTypeVO
     * @return int
     * @throws Exception
     */
    public int selectCnslTypeCdCount(PopupCnslTypeVO popupCnslTypeVO) throws Exception{
        int count = 0;
        try{
            count = popupDAO.selectCnslTypeCdCount(popupCnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslTypeCdCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"상담유형코드 count 조회중",ex.getMessage()} 
            );
        }         

        return count;
    }
    
    /**
     * 전체VOC유형코드조회
     * @param cnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<CnslTypeVO> selectCnslTypeList(CnslTypeVO cnslTypeVO) throws Exception{
        List<CnslTypeVO> list = null;
        try{
            list = popupDAO.selectCnslTypeList(cnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslTypeList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"전체VOC유형코드조회중",ex.getMessage()} 
            );
        }         
        return list;
    }
    
    /**
     * 전체VOC유형코드 count 조회
     * @param cnslTypeVO
     * @return int
     * @throws Exception
     */
    public int selectCnslTypeListCount(CnslTypeVO cnslTypeVO) throws Exception{
        int count = 0;
        try{
            count = popupDAO.selectCnslTypeListCount(cnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslTypeListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"전체VOC유형코드 count 조회중",ex.getMessage()} 
            );
        }         

        return count;
    }
    

    /**
     * VOC유형코드조회
     * @param cnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<CnslTypeVO> selectMyCnslType(CnslTypeVO cnslTypeVO) throws Exception{
        List<CnslTypeVO> list = null;
        try{
            list = popupDAO.selectMyCnslType(cnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMyCnslType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * VOC유형코드 단건 조회
     * @param cnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<CnslTypeVO> selectCnslType(CnslTypeVO cnslTypeVO) throws Exception{
    	List<CnslTypeVO> list = null;
        try{
            list = popupDAO.selectCnslType(cnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslType() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {" VOC유형코드 단건 조회 중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * VOC유형코드 트리 조회
     * @param cnslTypeVO
     * @return List
     * @throws Exception
     */
    public List<CnslTypeVO> selectByTree(CnslTypeVO cnslTypeVO) throws Exception{
        List<CnslTypeVO> list = null;
        try{
            list = popupDAO.selectByTree(cnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectByTree() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 트리 조회 중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * VOC유형코드 count 조회
     * @param cnslTypeVO
     * @return int
     * @throws Exception
     */
    public int selectMyCnslTypeCount(CnslTypeVO cnslTypeVO) throws Exception{
        int count = 0;
        try{
            count = popupDAO.selectMyCnslTypeCount(cnslTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMyCnslTypeCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"VOC유형코드 count 조회중",ex.getMessage()} 
            );
        }         

        return count;
    }
    
    /**
     * 고객 조회
     * @param popupCustInfoVO
     * @return List
     * @throws Exception
     */
    public List<PopupCustInfoVO> selectCstList(PopupCustInfoVO popupCustInfoVO) throws Exception {
        List<PopupCustInfoVO> list = null;
        try{
            list = popupDAO.selectCstList(popupCustInfoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }    

    /**
     * 고객건수조회
     * @param helper
     * @return List
     * @throws Exception
     */
    public int selectCstListCount(PopupCustInfoVO popupCustInfoVO) throws Exception {
        int intTotalCount = 0;
        try{
            intTotalCount = popupDAO.selectCstListCount(popupCustInfoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객 총건수조회중",ex.getMessage()} 
            );
        }         

        return intTotalCount;
    }
    
    /**
     * 고객 상세조회
     * @param popupCustInfoVO
     * @return List
     * @throws Exception
     */
    public List<PopupCustInfoVO> selectCstDetail(PopupCustInfoVO popupCustInfoVO) throws Exception {
        List<PopupCustInfoVO> list = null;
        try{
            list = popupDAO.selectCstDetail(popupCustInfoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstDetail() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객메모 등록
     * @param popupCustMemoVO
     * @return void
     * @throws Exception
     */
    public String insertCstMemo(PopupCustMemoVO popupCustMemoVO) throws Exception{
        String seqno = null;
    	try{
            seqno = (String) popupDAO.insertCstMemo(popupCustMemoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertCstMemo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객메모 등록 중",ex.getMessage()} 
            );
        }
    	return seqno;
    }
    
    
    /**
     * 고객메모 수정
     * @param popupCustMemoVO
     * @return void
     * @throws Exception
     */
    public void updateCstMemo(PopupCustMemoVO popupCustMemoVO) throws Exception{
        try{
        	popupDAO.updateCstMemo(popupCustMemoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateCstMemo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객메모 수정 중",ex.getMessage()} 
            );
        }         

    }
    
    /**
     * 고객메모 최근 표시값 조회
     * @param popupCustMemoVO
     * @return PopupCustMemoVO
     * @throws Exception
     */
    public PopupCustMemoVO selectCstMemoLast(PopupCustMemoVO popupCustMemoVO) throws Exception{
    	PopupCustMemoVO result = null;
        try{
            result = popupDAO.selectCstMemoLast(popupCustMemoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstMemoLast() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객메모 최근 표시값 조회 중",ex.getMessage()} 
            );
        }         

        return result;
    }
    
    /**
     * 고객메모 조회
     * @param popupCustMemoVO
     * @return List
     * @throws Exception
     */
    public List<PopupCustMemoVO> selectCstMemo(PopupCustMemoVO popupCustMemoVO) throws Exception{
        List<PopupCustMemoVO> list = null;
        try{
            list = popupDAO.selectCstMemo(popupCustMemoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstMemo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객메모 조회 중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객메모 상세
     * @param popupCustMemoVO
     * @return PopupCustMemoVO
     * @throws Exception
     */
    public PopupCustMemoVO selectCstMemoInfo(PopupCustMemoVO popupCustMemoVO) throws Exception{
    	PopupCustMemoVO result = null;
        try{
            result = popupDAO.selectCstMemoInfo(popupCustMemoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstMemoInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객메모  상세조회 중",ex.getMessage()} 
            );
        }         

        return result;
    }
    
    /**
     * 고객메모 조회
     * @param popupCustMemoVO
     * @return List
     * @throws Exception
     */
    public List<PopupCustMemoVO> selectCstMemoDetail(PopupCustMemoVO popupCustMemoVO) throws Exception {
        List<PopupCustMemoVO> list = null;
        try{
            list = popupDAO.selectCstMemoDetail(popupCustMemoVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstMenoDetail() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객메모 조회중",ex.getMessage()} 
            );
        }         

        return list;
    }
    
    /**
     * 고객메모 상세조회
     * @param cstNo
     * @return PopupCustMemoVO
     * @throws Exception
     */
    public PopupCustMemoVO selectCstMemoInfo(String cstNo) throws Exception {
    	PopupCustMemoVO result = null;
        try{
            result = popupDAO.selectCstMemoInfo(cstNo);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstMenoInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객메모 상세조회중",ex.getMessage()} 
            );
        }         

        return result;
    }

    /**
     * 업무정보 조회
     * @param userId
     * @return UserVO
     * @throws Exception
     */
    public UserVO selectUsrWorkInfo(String userId) throws Exception{
    	UserVO tbvcUsr = null;
        try{
            tbvcUsr = popupDAO.selectUsrWorkInfo(userId);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectUsrInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"업무정보 조회 중",ex.getMessage()} 
            );
        }         

        return tbvcUsr;
    }
    
    /**
     * 업무정보 등록
     * @param userVO
     * @return int
     * @throws Exception
     */
    public int updateUsrWorkInfo(UserVO userVO) throws Exception{
        int cnt = 0;
        try{
            cnt = popupDAO.updateUsrWorkInfo(userVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectUsrWorkInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"업무정보 등록 중",ex.getMessage()} 
            );
        }         

        return cnt;
    }
    
    /**
     * 승인단계 등록
     * @param approvalVO
     * @return String
     * @throws Exception
     */
    public String insertApvMg(ApprovalVO approvalVO) throws Exception{
        String seqNo = "";
        try{
            seqNo = approvalDAO.insertApvMg(approvalVO);
            return seqNo;  
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
     * 승인단계 삭제
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
        int count = 0;
        try {       
            count = approvalDAO.selectApvMgListCount(userEmpno);
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
     * 승인이력 조회
     * @param popupApprovalVO
     * @return List
     * @throws Exception
     */
    public List<PopupApprovalVO> selectApvHistList(PopupApprovalVO popupApprovalVO) throws Exception {
        List<PopupApprovalVO> list = null;
        try {
            list = popupDAO.selectApvHistList(popupApprovalVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectApvHistList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인이력 조회",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 승인이력 카운트 조회
     * @param popupApprovalVO
     * @return int
     * @throws Exception
     */
    public int selectApvHistListCount(PopupApprovalVO popupApprovalVO) throws Exception {
        int count = 0;
        try {
            count = popupDAO.selectApvHistListCount(popupApprovalVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectApvHistListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인이력 카운트 조회",ex.getMessage()} 
            );
        }
        return count;
    }
    
    /**
     * 배분이력 조회
     * @param NeedsMstVO
     * @return List
     * @throws Exception
     */
    public List<NeedsMstVO> selectDstrHist(NeedsMstVO needsMstVO) throws Exception {
    	List<NeedsMstVO> list = null;
    	try {
    		list = popupDAO.selectDstrHist(needsMstVO);
    	} catch (Exception ex) {
    		throw ExceptionHelper.getException(ex
    				, this.getClass().getName() + " : " + "selectDstrHist() 에러 발생"
    						,"SYS001"
    						,new Object[] {"승인이력 조회",ex.getMessage()} 
    				);
    	}
    	return list;
    }
    
    /**
     * 배분이력 카운트 조회
     * @param NeedsMstVO
     * @return int
     * @throws Exception
     */
    public int selectDstrHistCount(NeedsMstVO needsMstVO) throws Exception {
    	int count = 0;
    	try {
    		count = popupDAO.selectDstrHistCount(needsMstVO);
    	} catch (Exception ex) {
    		throw ExceptionHelper.getException(ex
    				, this.getClass().getName() + " : " + "selectDstrHistCount() 에러 발생"
    						,"SYS001"
    						,new Object[] {"승인이력 카운트 조회",ex.getMessage()} 
    				);
    	}
    	return count;
    }
    
	/**
     * 승인반려 재승인 요청
     * @param popupApprovalVO
     * @param void
     * @throws Exception
     */
    public void insertReTbvcApvInfo(PopupApprovalVO popupApprovalVO) throws Exception {
        try{
        	popupDAO.insertReTbvcApvInfo(popupApprovalVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateTbvcApvInfoCncl() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"승인대기 반려",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 고객니즈변경
     * @param popupNeedsChngHistVO
     * @return String
     * @throws Exception
     */
    public String insertNeedsChange(PopupNeedsChngHistVO  popupNeedsChngHistVO) throws Exception {
        String newVocId = "";
        try {
            newVocId = popupDAO.insertNeedsChange(popupNeedsChngHistVO);//TbvcNeedsMst 니즈변경으로 인한 신규 voc_id생성 및 데이타이관
            
            popupNeedsChngHistVO.setNewVocId(newVocId);
            //dao.updateTbvcNeedAntype(tbvcNeedsMst); //분류정보 voc_id를 update
            
            popupDAO.updateTbvcAtchfile(popupNeedsChngHistVO);   //첨부파일 DOC_REG_NO을 voc_id로 update
            
            popupNeedsChngHistVO.setAftVocId(newVocId);
            popupDAO.insertTbvcNeedsChngHist(popupNeedsChngHistVO);    //history 저장
            
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertNeedsChange() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"고객니즈변경",ex.getMessage()} 
            );
        }
        return newVocId;
    }
    
    /**
     * 고객니즈변경 이력조회
     * @param popupNeedsChngHistVO
     * @return String
     * @throws Exception
     */
    public List<PopupNeedsChngHistVO> selectTbvcNeedsChngHist(PopupNeedsChngHistVO popupNeedsChngHistVO) throws Exception {
        List<PopupNeedsChngHistVO> list = null;
        try {
            list = popupDAO.selectTbvcNeedsChngHist(popupNeedsChngHistVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNeedsList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"조건별 VOC 조회",ex.getMessage()} 
            );
        }
        return list;
    }

    /**
     * 상담유형 코드 자동완성 조회
     * @param PopupCnslTypeVO
     * @return List
     * @throws Exception
     */
	public List<PopupCnslTypeVO> selectCnslTypeAutoComplete(PopupCnslTypeVO popupCnslTypeVO) throws Exception {
		List<PopupCnslTypeVO> list = null;
		try{
			list = popupDAO.selectCnslTypeAutoComplete(popupCnslTypeVO);
		}catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCnslTypeAutoComplete() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"상담유형 코드 자동완성 조회 ",ex.getMessage()} 
            );
        }
		return list;
	}
	
    /**
     * 배정이력 등록
     * @param popupPropAsgnHistVO
     * @return void
     * @throws Exception
     */
    public void insertPropAsgnHist(PopupPropAsgnHistVO popupPropAsgnHistVO) throws Exception{
        try{
        	popupDAO.insertPropAsgnHist(popupPropAsgnHistVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "popupPropAsgnHistVO() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"배정이력 등록",ex.getMessage()} 
            );
        }
    }
    
    /**
     * 배정이력 조회
     * @param popupPropAsgnHistVO
     * @return List
     * @throws Exception
     */
	public List<PopupPropAsgnHistVO> selectPropAsgnHistList(PopupPropAsgnHistVO popupPropAsgnHistVO) throws Exception {
        List<PopupPropAsgnHistVO> list = null;
        try {
            list = popupDAO.selectPropAsgnHistList(popupPropAsgnHistVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPropAsgnHistList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"배정이력 조회",ex.getMessage()} 
            );
        }
        return list;
    }
    
    /**
     * 배정이력 카운트 조회
     * @param popupPropAsgnHistVO
     * @return int
     * @throws Exception
     */
	public int selectPropAsgnHistListCount(PopupPropAsgnHistVO popupPropAsgnHistVO) throws Exception {
        int count = 0;
        try {
            count = popupDAO.selectPropAsgnHistListCount(popupPropAsgnHistVO);
        } catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPropAsgnHistListCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"배정이력 카운트 조회",ex.getMessage()} 
            );
        }
        return count;
    }

    /**
     * 검토요청 등록
     * @param popupHelpReqInfoVO
     * @return void
     * @throws Exception
     */
    public void insertHelpReqInfo(PopupHelpReqInfoVO popupHelpReqInfoVO) throws Exception{
        try{
        	popupDAO.insertHelpReqInfo(popupHelpReqInfoVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertHelpReqInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"검토요청 등록",ex.getMessage()} 
            );
        }
    }
	
    /**
     * 변경이력
     * @param popupPropChngHistVO
     * @return List
     * @throws Exception
     */
    public List<PopupPropChngHistVO> selectPropChngHistList(PopupPropChngHistVO popupPropChngHistVO) throws Exception {
    	List<PopupPropChngHistVO> list = null;
    	try{
    		list = popupDAO.selectPropChngHistList(popupPropChngHistVO);
    	}catch(Exception ex){
    		throw ExceptionHelper.getException(ex
    				, this.getClass().getName() + " : " + "selectPropChngHistList() 에러 발생"
    						,"SYS001"
    						,new Object[] {"변경이력",ex.getMessage()} 
    				);
    	}         
    	
    	return list;
    }
    
    /**
     * 변경이력 등록
     * @param popupPropAsgnHistVO
     * @return void
     * @throws Exception
     */
    public void insertPropChngHist(PopupPropChngHistVO popupPropChngHistVO) throws Exception{
        try{
        	popupDAO.insertPropChngHist(popupPropChngHistVO);
        }catch (Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertPropChngHist() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"변경이력 등록",ex.getMessage()} 
            );
        }
    }
    
    
    
	

	public void updateClaimFileInfo(AttachFileVO key) throws Exception{
		
	}

	
	
}