package nds.mpm.common.web;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import nds.mpm.common.exception.TMMControllerExcepHndlr;
import nds.mpm.common.util.FileUtil;
import nds.mpm.common.vo.CommonFileVO;
import nds.mpm.login.vo.MPUserSession;
import nds.mpm.login.vo.RoleConsts;
import nds.mpm.login.vo.UserVO;
import nds.mpm.sales.SD0402.service.SD0402Service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public class TMMBaseController extends TMMControllerExcepHndlr {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(TMMBaseController.class);
	
	@Resource(name = "SD0402Service")
    private SD0402Service userService;
	
	public MPUserSession getSession(HttpServletRequest req) throws Exception{
		
		MPUserSession session = (MPUserSession)WebUtils.getSessionAttribute(req, "mpuserSession");
    	
    	if(session == null)
    	{
    		LOGGER.debug("session is invalid !! :::::PLEASE SESSION CHECK:::::");
    		
    		/** TODO TEST user handon
    		 * session = new MPUserSession("handon", "handon", null);
    		 * ***/
    		session = new MPUserSession("handon", "handon", null);
    		
    		//throw new Exception("session is invalid !!");
    	}
    	else
    	{
    		LOGGER.debug("::::: SESSION CHECK::::: !!");
    		LOGGER.debug("session user id :: " + session.getUser().getId());
    		LOGGER.debug("::::: SESSION IS VALID::::: !!");
    	}
    	
    	/** TODO TEST
    	 * 
    	 * user info db get and setting
		 * 
    	 * */
    	/**
    	 * TEST
    	 * 
    	 * **/
    	
    	return session;
	}
	
	/***
	 * 영업사원 권한레벨값 리턴 
	 * */
	public int getSalesmanLevel(HttpServletRequest req) throws Exception{
		
		MPUserSession sess = getSession(req);
		
		/**
		 * TEST
		 * */
		//if(true) return 3;
		
		if(RoleConsts.OFCE_CEO_CODE.equals(sess.getUser().getOfceCode()))
    	{
    		return 1;
    	}
		else if(RoleConsts.OFCE_HEAD_CODE.equals(sess.getUser().getOfceCode()))
    	{
    		return 2;
    	}
		else if(RoleConsts.OFCE_TEAM_CODE.equals(sess.getUser().getOfceCode()))
    	{
			return 3;
    	}
		else if(RoleConsts.OFCE_PART_CODE.equals(sess.getUser().getOfceCode()))
    	{
			return 4;
    	}
		return 5;
    }
	
	public CommonFileVO uploadAndFileInfo(MultipartFile file, String uploadPath) throws Exception {
		
		CommonFileVO fileVo = new CommonFileVO();
		File saveFolder = new File(uploadPath);

    	if (!saveFolder.exists() || saveFolder.isFile()) {
    	    saveFolder.mkdirs();
    	}

	    if (file != null && !"".equals(file.getOriginalFilename())) {
	    	// 화면에서 전달된 이미지의 임시저장소 저장처리
	    	String nFileName = null;
			if (FileUtil.createDirectory(uploadPath))
				nFileName = FileUtil.saveFileForByte(file.getBytes(), uploadPath,
						file.getOriginalFilename());
			
			uploadPath = uploadPath + "/" + nFileName;
			
			fileVo.setFullpath(uploadPath);
			fileVo.setNewName(nFileName);
			fileVo.setMimeType(FileUtil.mimeType(uploadPath));
			fileVo.setOrginalName(file.getOriginalFilename());
	    }
	    
	    return fileVo;
	}
}
