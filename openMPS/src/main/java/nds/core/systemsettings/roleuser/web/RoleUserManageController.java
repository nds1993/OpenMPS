package nds.core.systemsettings.roleuser.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.systemsettings.roleuser.service.RoleUserManageService;
import nds.core.systemsettings.roleuser.service.RoleUserVO;
import nds.core.userdep.department.service.DepartMentService;
import nds.core.userdep.department.service.DepartMentVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


/**
 * <p>
 * Title: SYController
 * </p>
 * <p>
 * Description: Control Class
 * </p>
 * <p>
 * <b>History</b>
 * </p>
 * 
 * <pre>
 * : 2009.06.11 초기작성(임진식)
 * </pre>
 * 
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
@Controller
public class RoleUserManageController extends BaseController {
    
	
	/** RoleUserManagerService */
    @Resource(name = "roleUserManageService")
    private RoleUserManageService roleUserManageService;
    
    /** departMentService */
    @Resource(name = "departMentService")
    private DepartMentService departMentService;
    
    
    /**
     * 역할별메뉴권한등록
     * 
     * @param request
     * @param reponse
     */
	@RequestMapping(value="/systemsettings/roleuser/syset0900.do")
    public ModelAndView sybe50s(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        String selUserId = StringUtil.getParam(request, "selUserId", "");
        String roleCd = StringUtil.getParam(request, "roleCd", "");
        
        RoleUserVO roleUserVO = new RoleUserVO();

        int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개
        
        // request정보를 담는다.
        bind(request, roleUserVO);
        
        roleUserVO.setStartNo(((page * rows) - rows) + 1);
        roleUserVO.setEndNo(page * rows);
        roleUserVO.setSidx(sidx);
        roleUserVO.setSidx(sord);

        ModelAndView mav = new ModelAndView("systemsettings/roleuser/syset0900");
        
        if("S".equals(crudMode)){
        	mav = new ModelAndView("jsonView");
        	response.setContentType("application/json; charset=utf-8");
    		response.setHeader("Cache-Control", "no-cache");
    		
        	List<RoleUserVO> result = roleUserManageService.selectRoleUserList(roleUserVO);
            int total = 0;
            if(result.size() > 0){
            	total = Integer.parseInt(result.get(0).getTotalCnt());            	
            }       

            mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows",  result);

        }else if("D".equals(crudMode)){
            String[] selUserIdArr = null;
            
            if(!"".equals(selUserId)) {
            	selUserIdArr = selUserId.split(",");
            }
            
            if(selUserId != null && selUserIdArr.length > 0) {
                for(int i = 0; i < selUserIdArr.length; i++) {
                    String[] usr = null;
                    if(!"".equals(selUserIdArr[i])){
                    	RoleUserVO vo = new RoleUserVO();
                    	usr = selUserIdArr[i].split("[|]");
                    	if(usr.length > 0){
                    		vo.setRoleCd(roleCd);
                    		vo.setUserId(usr[0]);
                    		vo.setUserDvn(usr[1]);
                    		roleUserManageService.deleteRoleUser(vo);
                    	}
                    }
                }
            }					
		
		
		}
        
        return mav;
    }
	
	/**
	 * 역할별메뉴권한등록
	 * 
	 * @param request
	 * @param reponse
	 */
	@RequestMapping(value="/systemsettings/roleuser/syset0910_pop.do")
	public ModelAndView syset0910_pop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
		String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");
		String schDepCd = StringUtil.getParam(request, "depCd", "");
		String selUserId = StringUtil.getParam(request, "selUserId", "");
		String roleCd = StringUtil.getParam(request, "roleCd", "");

		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "10")); // 페이지당 몇개
		
        UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
        String userId = userSession.getLogin().getUserId();
        
		RoleUserVO roleUserVO = new RoleUserVO();
		
		ModelAndView mav = new ModelAndView("systemsettings/roleuser/syset0910_pop");
		
		if("US".equals(crudMode)){
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			roleUserVO.setDepCd(schDepCd);
			roleUserVO.setStartNo(((page * rows) - rows) + 1);
			roleUserVO.setEndNo(page * rows);
			roleUserVO.setSidx(sidx);
			roleUserVO.setSord(sord);
			
			List<RoleUserVO> result =  roleUserManageService.selectCustList(roleUserVO);
			int intTotalCount = roleUserManageService.selectCustListCount(roleUserVO);
			
			mav.addObject("rows", result);
			mav.addObject("total", Math.ceil((double)intTotalCount/rows));  // total page
			mav.addObject("records", intTotalCount);         
			
		}else if("U".equals(crudMode)){
            // 전체삭제
            String[] selUserIdArr = null;
            
            if(!"".equals(selUserId)) {
            	selUserIdArr = selUserId.split(",");
            }
            
            if(selUserId != null && selUserIdArr.length > 0) {
                for(int i = 0; i < selUserIdArr.length; i++) {
                    String[] usr = null;
                    if(!"".equals(selUserIdArr[i])){
                    	RoleUserVO vo = new RoleUserVO();
                    	usr = selUserIdArr[i].split("[|]");
                    	if(usr.length > 0){
                    		vo.setRoleCd(roleCd);
                    		vo.setUserId(usr[0]);
                    		vo.setUserDvn(usr[1]);
                    		vo.setRegUser(userId);
                    		vo.setUpdtUser(userId);
                    		roleUserManageService.insertRoleUser(vo);
                    	}
                    }
                }
            }			
			
		}else{
			DepartMentVO org = new DepartMentVO();
	        List result = roleUserManageService.selectOrganization(org);
			mav.addObject("result", result);
		}
		
		
		
		return mav;
	}

}
