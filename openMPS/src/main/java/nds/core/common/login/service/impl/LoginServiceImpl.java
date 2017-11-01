package nds.core.common.login.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.common.login.service.LoginService;
import nds.core.logsearch.loginlog.service.LoginLogVO;
import nds.core.logsearch.loginlog.service.impl.LoginLogDAO;
import nds.core.userdep.user.service.UserVO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.util.EncyptShaUtil;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : LoginServiceImpl </b>
 * <b>Class Description</b><br>
 * 공통 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Service("loginService")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {
    
	@Resource(name="loginLogDAO")
    private LoginLogDAO loginLogDAO;
	@Resource(name="loginDAO")
    private LoginDAO loginDAO;	
	
    /**
     * 사용자 정보조회
     * @param login
     * @return UserVO
     */
    public UserVO getUserInfo(UserVO userVO, boolean mode) throws Exception {
    	UserVO resultUserVO = null;
        try{
//          Tbcr1300DAO dao = (Tbcr1300DAO) (getDaos().get("tbcr1300DAO"));
//          Tbcr1300Helper helper = new Tbcr1300Helper();
//          Tbcr1300Helper.Criteria c1 = helper.createCriteria();
//          c1.andIdEqualTo(login.getUserId());
//          //c1.andWorkStatEqualTo("AAA");
//          List<String> workStats = new ArrayList<String>();                         // 근무상태가 'AAA' 및 'DDD'도 허용하도록 변경함. 2011-07-22 임기범 
//          workStats.add("AAA");
//          workStats.add("DDD");
//          c1.andWorkStatIn(workStats);
            
//            UserVO tbvcUsr = new UserVO();
//        	userVO.setUserId(userVO.getUserId());

            if(mode){
                //비빌번호 암호처리  TODO 사용안함
//            	userVO.setPwd(EncyptShaUtil.encryptSHA(userVO.getPwd()));
            }
            
            List<UserVO> list = loginDAO.selectByUserInfo(userVO);
            
            if (list != null && list.size() > 0){
            	resultUserVO = (UserVO) list.get(0);
            	
            	if(resultUserVO.getChmanagerMs() != null) {
	            	String[] chmanagerMs = resultUserVO.getChmanagerMs().split(",");
	            	String[] chmanagerChnlCd = resultUserVO.getChmanagerChnlCd().split(",");
	            	
	            	resultUserVO.setChmanagerMsArr(chmanagerMs);
	            	resultUserVO.setChmanagerChnlCdArr(chmanagerChnlCd);
            	}
            }
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "getUserInfo() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 정보조회",ex.getMessage()} 
            );
        }        
        return resultUserVO;
    }
    
    /**
     * 사용자 로그인시 로그인 여부 업데이트
     * @param login
     * @return 
     */
    public synchronized String updateUserLogin(LoginLogVO loginLogVO) throws Exception{
        try
        {
            //이전에 로그인한 정보를 로그아웃으로 변경
            LoginLogVO preLoginLogVO = new LoginLogVO();
            preLoginLogVO.setCnctYn("N");
            preLoginLogVO.setUserId(loginLogVO.getUserId());
            preLoginLogVO.setPreCnctYn("Y");

            loginLogDAO.updateByPrimaryKeySelective(preLoginLogVO);
            
            String cnctLogNo = (String) loginLogDAO.insert(loginLogVO);
            
            return cnctLogNo;

        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateUserLogin() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"사용자 정보 로그 갱신",ex.getMessage()} 
            );
        }           
    }
}