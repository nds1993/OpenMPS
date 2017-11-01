package nds.core.common.common.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.GwApprovalVO;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.NotiUserInfoVO;
import nds.core.common.common.service.PropTypeVO;
import nds.core.common.common.service.SkinMstVO;
import nds.core.common.common.service.UserMenu;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.core.logsearch.loginlog.service.impl.LoginLogDAO;
import nds.core.systemsettings.code.service.CodeVO;
import nds.core.systemsettings.code.service.impl.CodeDAO;
import nds.core.systemsettings.menu.service.impl.MenuDAO;
import nds.core.userdep.user.service.UserVO;
import nds.core.userdep.user.service.impl.UserDAO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;
import nds.frm.util.EncyptShaUtil;

import org.springframework.stereotype.Service;

//import nds.clm.claimmanageinfo.tyregist.service.TyRegistVO;


//import nds.core.common.common.service.VocCommonCodeVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : CommonServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.18 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("commonService")
public class CommonServiceImpl extends AbstractServiceImpl implements CommonService {
    
	@Resource(name="loginLogDAO")
    private LoginLogDAO loginLogDAO;
	@Resource(name="menuDAO")
    private MenuDAO menuDAO;
	@Resource(name="userDAO")
    private UserDAO userDAO;	
	@Resource(name="skinMstDAO")
    private SkinMstDAO skinMstDAO;
	@Resource(name="commonDAO")
    private CommonDAO commonDAO;
	
	@Resource(name="codeDAO")
    private CodeDAO codeDAO;
    /**
     * 스킨정보 조회
     * @param 
     * @return SkinMstVO
     * @throws Exception
     */
    public SkinMstVO getSkinInfo() throws MainException {
    	SkinMstVO info = null;
        try{
            info = skinMstDAO.selectBaseSkinInfo();
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "getSkinInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"스킨정보조회",ex.getMessage()} 
            );
        }        
        return info;
    }
    /**
     * 스킨정보 조회
     * @param skinNo
     * @return SkinMstVO
     * @throws Exception
     */
    public SkinMstVO getSkinInfo(String skinNo) throws MainException {
    	SkinMstVO info = null;
        try{
            info = skinMstDAO.selectByPrimaryKey(skinNo);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "getSkinInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"스킨정보조회",ex.getMessage()} 
            );
        }        
        return info;
    }
    
    /**
     * 사용자 정보 Update(로그인 여부)
     * @param loginLogVO
     * @return String
     * @throws Exception
     */
    public synchronized String updateUserLogin(LoginLogVO log) throws MainException{
        try
        {
            //이전에 로그인한 정보를 로그아웃으로 변경
            LoginLogVO preLog = new LoginLogVO();
            preLog.setCnctYn("N");
            preLog.setUserId(log.getUserId());
            preLog.setPreCnctYn("Y");

            loginLogDAO.updateByPrimaryKeySelective(preLog);
            
            String cnctLogNo = (String) loginLogDAO.insert(log);
            
            return cnctLogNo;

        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateUserLogin() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 정보 로그 갱신",ex.getMessage()} 
            );
        }           
    }    
    
    /**
     * 사용자 정보 Update(로그인 여부)
     * @param loginLogVO
     * @return void
     * @throws Exception
     */
    public synchronized void updateUserLogout(LoginLogVO log) throws MainException{
        try
        {
        	loginLogDAO.updateByPrimaryKeySelective(log);          
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateUserLogout() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 정보 Login 업데이트중",ex.getMessage()} 
            );
        }           
    }  
    
    /**
     * 메뉴에 대한 상세정보를 가져온다.
     * @param userMenu
     * @return List
     * @throws Exception
     */
    public List selectMenuInfo(UserMenu key) throws MainException {
        List list = null;
        try{
            list = menuDAO.selectMenuInfo(key);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMenuInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메뉴에 대한 상세정보  조회",ex.getMessage()} 
            );
        }       
        return list;        
    }
    
    /**
     * 메뉴에대한 사용자역할을 조회한다.
     * @param userMenu
     * @return List
     * @throws Exception
     */
    public List selectCstContentRoleInfo(UserMenu key) throws MainException {
        List list = null;
        try{
            list = menuDAO.selectCstContentRoleInfo(key);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCstContentRoleInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메뉴에대한 사용자역할",ex.getMessage()} 
            );
        }       
        return list;
    }
    
    /**
     * 사용로그 기록
     * @param loginLogVO
     * @return void
     * @throws Exception
     */
//    public void useLogWrite(Tbcr9910 log) throws MainException {
//        try
//        {
//            Tbcr9910DAO dao = (Tbcr9910DAO) (getDaos().get("tbcr9910DAO"));
//            
//            dao.insert(log);          
//
//        }catch(Exception ex){
//        }         
//    }
    
    
/////////////////////////////////////////////////////////////////////////////////////
    /**
     * 사용자 정보조회
     * @param userVO, mode
     * @return UserVO
     * @throws Exception
     */
    public UserVO getUserInfo(UserVO userVO, boolean mode) throws MainException {
        UserVO m = null;
        try{
//          Tbcr1300DAO dao = (Tbcr1300DAO) (getDaos().get("tbcr1300DAO"));
//          Tbcr1300Helper helper = new Tbcr1300Helper();
//          Tbcr1300Helper.Criteria c1 = helper.createCriteria();
//          c1.andIdEqualTo(userVO.getUserId());
//          //c1.andWorkStatEqualTo("AAA");
//          List<String> workStats = new ArrayList<String>();                         // 근무상태가 'AAA' 및 'DDD'도 허용하도록 변경함. 2011-07-22 임기범 
//          workStats.add("AAA");
//          workStats.add("DDD");
//          c1.andWorkStatIn(workStats);
            
            if(mode){
                //비빌번호 암호처리
            	userVO.setPwd(EncyptShaUtil.encryptSHA(userVO.getPwd()));
            }
            
            List list = userDAO.selectByUserInfo(userVO);
            
            if (list != null && list.size() > 0){
                m = (UserVO) list.get(0);
            }
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "getUserInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 정보조회",ex.getMessage()} 
            );
        }        
        return m;
    }
    

    /**
     * 내 메뉴 컬럼 저장
     * @param myMenuColumnVO
     * @return void
     * @throws Exception
     */
	public void insertMenuColumn(MyMenuColumnVO myMenuColumnVO) throws Exception {
		try {
            commonDAO.insertMenuColumn(myMenuColumnVO);
        }
        catch(Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "insertMenuColumn() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"내 메뉴 컬럼 저장",ex.getMessage()} 
            );
        }
	}
	
	/**
     * 내 메뉴 컬럼을 조회한다.
     * @param myMenuColumnVO
     * @return MyMenuColumnVO
     * @throws Exception
     */
    public MyMenuColumnVO selectMenuColumn(MyMenuColumnVO myMenuColumnVO) throws Exception {
    	MyMenuColumnVO result = null;
        try {
            result = commonDAO.selectMenuColumn(myMenuColumnVO);
        }
        catch(Exception ex) {
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMenuColumn() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"내 메뉴 컬럼 조회",ex.getMessage()} 
            );
        }
        return result;
    }

    /**
     * 공통코드 조회
     * @param dbuser, codeVO
     * @return List
     * @throws Exception
     */
    public List<CodeVO> selectCommonCode(String dbuser, CodeVO codeVO) throws Exception {
    	List<CodeVO> list = null;
        try{
        	if(dbuser.equals("NACCM")){
	            list = codeDAO.selectByRecord(codeVO);
        	}
        	else{
	            list = codeDAO.selectByRecord(codeVO);
        	}
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectCommonCode() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"공통코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    
    /**
     * 제안종류 중 코드 조회
     * @param propTypeVO
     * @return List
     * @throws Exception
     */
    public List<PropTypeVO> selectPropTypeMcls(PropTypeVO propTypeVO) throws Exception {
    	List<PropTypeVO> list = null;
        try{
	        list = commonDAO.selectPropTypeMcls(propTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPropTypeMcls() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안종류 중 코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
    
    /**
     * 제안결과 중 코드 조회
     * @param propTypeVO
     * @return List
     * @throws Exception
     */
    public List<PropTypeVO> selectPropResultTypeMcls(PropTypeVO propTypeVO) throws Exception {
    	List<PropTypeVO> list = null;
        try{
	        list = commonDAO.selectPropResultTypeMcls(propTypeVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectPropResultTypeMcls() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"제안결과 중 코드 조회",ex.getMessage()} 
            );
        }        
        return list;
    }
	


	
	@Override
	public List selectLocalMenuInfo(UserMenu key) throws Exception {
        List list = null;
        try{
            list = menuDAO.selectLocalMenuInfo(key);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectMenuInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"메뉴에 대한 상세정보  조회",ex.getMessage()} 
            );
        }       
        return list;   
	}
	
	
	public void updateApprovalState(String tableNm, GwApprovalVO record) throws Exception {

        try{
    		if("RMS101".equals(tableNm)){
    			commonDAO.updateApprovalState100(record);
    		}else if("RMS102".equals(tableNm)){
    			commonDAO.updateApprovalState200(record);
    		}
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateApprovalState() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"결재 기안",ex.getMessage()} 
            );
        }       
		
	}
	


    /**
     * 알림 대상자 및 내용 조회
     * @param tyRegistVO
     * @return
     * @throws Exception
     */
	public List<NotiUserInfoVO> selectNotiUserList(NotiUserInfoVO key) throws Exception {
    	List<NotiUserInfoVO> list = null;
        try{
	        list = commonDAO.selectNotiUserList(key);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectNotiUserList() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"알림 대상자 및 내용 조회",ex.getMessage()} 
            );
        }        
        return list;
	}
    
    
}
