package nds.core.userdep.user.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.core.userdep.user.service.UserService;
import nds.core.userdep.user.service.UserVO;
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
@Service("userService")
public class UserServiceImpl extends AbstractServiceImpl implements UserService {
    
	@Resource(name="userDAO")
    private UserDAO userDAO;
    
	   /**
	    * 사용자 조회
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
	    * @param helper
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
	    * 사용자상세조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   public UserVO selectByPrimaryKey(String userId) throws Exception {
		   UserVO record = new UserVO();
	       try{
	           record = userDAO.selectByPrimaryKey(userId);
	       }catch(Exception ex){
	           throw ExceptionHelper.getException(ex
	                   , this.getClass().getName() + " : " + "selectByPrimaryKey() 에러 발생"
	                   ,"SYS001"
	                   ,new Object[] {"사용자상세조회",ex.getMessage()} 
	           );
	       }        
	       return record;
	   }
	   
	    /**
	     * 조직사용자 등록
	     * @param userVO
	     * @return List
	     * @throws Exception
	     */
	    public void insertUsr(UserVO userVO) throws Exception {
	        try{
	        	userDAO.insertUsr(userVO);
	        }catch(Exception ex){
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "insertUsr() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"조직사용자 등록",ex.getMessage()} 
	            );
	        }        
	    }
	    
	    /**
	     * 조직사용자 수정
	     * @param userVO
	     * @return List
	     * @throws Exception
	     */
	    public void updateUsr(UserVO userVO) throws Exception {
	        try{
	        	userDAO.updateByPrimaryKey(userVO);
	        }catch(Exception ex){
	            throw ExceptionHelper.getException(ex
	                    , this.getClass().getName() + " : " + "updateUsr() 에러 발생"
	                    ,"SYS001"
	                    ,new Object[] {"조직사용자 수정",ex.getMessage()} 
	            );
	        }        
	    }
	    
	   /**
	    * ID 건수조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   public int selectUserIdCount(UserVO userVO) throws Exception {
	       int intTotalCount = 0;
	       try{
	       		intTotalCount = userDAO.selectCustListCount(userVO);
			}catch(Exception ex){
	           throw ExceptionHelper.getException(ex
	                   , this.getClass().getName() + " : " + "selectUserIdCount() 에러 발생"
	                   ,"SYS001"
	                   ,new Object[] {"ID  건수조회",ex.getMessage()} 
	           );
			}        

	       return intTotalCount;
	   }
	   
	   
	   /**
	    * 사용자상세조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   public List<UserVO> selectByUserInfoByRole(UserVO userVO) throws Exception {
		   List<UserVO> record = null;
	       try{
	           record = userDAO.selectByUserInfoByRole(userVO);
	       }catch(Exception ex){
	           throw ExceptionHelper.getException(ex
	                   , this.getClass().getName() + " : " + "selectByUserInfoByRole() 에러 발생"
	                   ,"SYS001"
	                   ,new Object[] {"사용자상세조회",ex.getMessage()} 
	           );
	       }        
	       return record;
	   }
    
	   
	   /**
	    * 사용자상세조회
	    * @param helper
	    * @return int
	    * @throws Exception
	    */
	   public List<UserVO> selectByUserInfoByDepMngRole(UserVO userVO) throws Exception {
		   List<UserVO> record = null;
	       try{
	           record = userDAO.selectByUserInfoByDepMngRole(userVO);
	       }catch(Exception ex){
	           throw ExceptionHelper.getException(ex
	                   , this.getClass().getName() + " : " + "selectByUserInfoByDepMngRole() 에러 발생"
	                   ,"SYS001"
	                   ,new Object[] {"사용자상세조회",ex.getMessage()} 
	           );
	       }        
	       return record;
	   }
	   
}