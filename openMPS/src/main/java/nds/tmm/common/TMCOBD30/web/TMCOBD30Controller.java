package nds.tmm.common.TMCOBD30.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.mpm.common.user.web.UserInfoController;
import nds.mpm.common.util.FileUtil;
import nds.mpm.common.vo.CommonFileVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.tmm.common.TMCOBD20.service.TMCOBD20Service;
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;
import nds.tmm.common.TMCOBD30.service.TMCOBD30Service;
import nds.tmm.common.TMCOBD30.vo.TMCOBD30VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : TMCOBD30Controller.java
 * @Description : TMCOBD30 Controller class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/tmm/{corpCode}")
public class TMCOBD30Controller extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter	_filter;	
	
	@Resource(name = "TMCOBD20Service")
	private TMCOBD20Service TMCOBD20Service;

    @Resource(name = "TMCOBD30Service")
    private TMCOBD30Service TMCOBD30Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_faqxxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOBD30VO
	 * @return "/TMCOBD30/TMCOBD30List"
	 * @exception Exception
	 */
    @RequestMapping(value="/TMCOBD30",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOBD30List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOBD30VO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	
    	List<?> TMCOBD30List = TMCOBD30Service.selectTMCOBD30List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOBD30List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * tm_faqxxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOBD30VO
	 * @return "/TMCOBD30/TMCOBD30List"
	 * @exception Exception
	 */
    @RequestMapping(value="/TMCOBD50",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOBD50List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOBD30VO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSearchMode("USER");
    	
    	List<?> TMCOBD30List = TMCOBD30Service.selectTMCOBD30List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOBD30List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOBD30/saveTMCOBD30")
    public ResponseEntity<ResultEx> addTMCOBD30(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOBD30VO TMCOBD30VO,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOBD30VO.setCrUser(sess.getUser().getId());
    	TMCOBD30VO.setCorpCode(corpCode);
    	
    	//파일 저장
		String userPath = sess.getUser().getId();
		String contextroot = jsession.getServletContext().getRealPath("/");
		CommonFileVO ufile;
		
		if (TMCOBD30VO.getFile() != null) {
			if (!TMCOBD30VO.getFile().isEmpty()) {		
				
				TMCOBD20VO TMCOBD20VO = new TMCOBD20VO();
				
				TMCOBD20VO.setFile(TMCOBD30VO.getFile());
				TMCOBD20VO.setCrUser(sess.getUser().getId());
				
				MultipartFile file = TMCOBD20VO.getFile();
				
				String uploadPath = TMCOBD20VO.getFileBasePath() + userPath;
				
				ufile = uploadAndFileInfo(file, uploadPath);
				
				// 파일 정보 설정
				TMCOBD20VO.setFileId(TMCOBD20Service.selectNextFileId(TMCOBD20VO));
				TMCOBD20VO.setFilePath(uploadPath);
				TMCOBD20VO.setSaveFlnm(ufile.getNewName());
				TMCOBD20VO.setOriginFlnm(ufile.getOrginalName());
				TMCOBD20VO.setFileExt(new FileUtil().getExtension(ufile.getOrginalName()));
				
				TMCOBD20Service.insertFileMaster(TMCOBD20VO);
				TMCOBD20Service.insertFileDetail(TMCOBD20VO);
				
				TMCOBD30VO.setFileId(TMCOBD20VO.getFileId());
			}
		}
    	
    	TMCOBD30Service.insertTMCOBD30(TMCOBD30VO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOBD30/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOBD30(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody TMCOBD30VO TMCOBD30VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOBD30Service.selectTMCOBD30(TMCOBD30VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/TMCOBD30/updateTMCOBD30")
    public ResponseEntity<ResultEx> updateTMCOBD30(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOBD30VO TMCOBD30VO,
    		HttpServletRequest req,
    		HttpServletResponse res,
            SessionStatus status,
    		HttpSession jsession)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOBD30VO.setMdUser(sess.getUser().getId());
    	
    	//파일 저장
		String userPath = sess.getUser().getId();
		String contextroot = jsession.getServletContext().getRealPath("/");
		CommonFileVO ufile;
		
		if (TMCOBD30VO.getFile() != null) {
			if (!TMCOBD30VO.getFile().isEmpty()) {		
				
				TMCOBD20VO TMCOBD20VO = new TMCOBD20VO();
				
				TMCOBD20VO.setFile(TMCOBD30VO.getFile());
				TMCOBD20VO.setMdUser(sess.getUser().getId());
				TMCOBD20VO.setCrUser(sess.getUser().getId());
				
				MultipartFile file = TMCOBD20VO.getFile();
				
				String uploadPath = TMCOBD20VO.getFileBasePath() + userPath;
				
				ufile = uploadAndFileInfo(file, uploadPath);
				
				// 파일 정보 설정
				if(StringUtils.isEmpty(TMCOBD30VO.getFileId())) {					
					TMCOBD20VO.setFileId(TMCOBD20Service.selectNextFileId(TMCOBD20VO));
					TMCOBD20Service.insertFileMaster(TMCOBD20VO);
					TMCOBD30VO.setFileId(TMCOBD20VO.getFileId());
				} else {
					TMCOBD20VO.setFileId(TMCOBD30VO.getFileId());
				}
				
				TMCOBD20VO.setFilePath(uploadPath);
				TMCOBD20VO.setSaveFlnm(ufile.getNewName());
				TMCOBD20VO.setOriginFlnm(ufile.getOrginalName());
				TMCOBD20VO.setFileExt(new FileUtil().getExtension(ufile.getOrginalName()));
				
				TMCOBD20Service.insertFileDetail(TMCOBD20VO);
			}
		}
    	
        TMCOBD30Service.updateTMCOBD30(TMCOBD30VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOBD30/deleteTMCOBD30")
    public ResponseEntity<ResultEx> deleteTMCOBD30(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOBD30VO TMCOBD30VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOBD30VO.setMdUser(sess.getUser().getId());
    	
    	TMCOBD30Service.deleteTMCOBD30(TMCOBD30VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOBD50/saveReadCnt")
    public ResponseEntity<ResultEx> saveReadCnt(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOBD30VO TMCOBD30VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOBD30VO.setCrUser(sess.getUser().getId());
    	TMCOBD30VO.setCorpCode(corpCode);
    	
    	TMCOBD30VO.setBbsCode("FAQ");
    	TMCOBD30VO.setContId(TMCOBD30VO.getFaqId());
    	TMCOBD30VO.setReadUser(sess.getUser().getId());
    	
    	TMCOBD30VO reusltVO = TMCOBD30Service.selectBdredhTMCOBD30(TMCOBD30VO);
    	
    	if (reusltVO == null) {
    		TMCOBD30Service.insertBdredhTMCOBD30(TMCOBD30VO);
    		TMCOBD30Service.updateReadCntTMCOBD30(TMCOBD30VO);
    	}
    	
        return _filter.makeCORSEntity( result );
    }

}
