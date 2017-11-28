package nds.tmm.common.TMCOSM10.web;

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
import nds.tmm.common.TMCOSM10.service.TMCOSM10Service;
import nds.tmm.common.TMCOSM10.vo.TMCOSM10VO;

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
 * @Class Name : TMCOSM10Controller.java
 * @Description : TMCOSM10 Controller class
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
public class TMCOSM10Controller extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter	_filter;	
	
    @Resource(name = "TMCOSM10Service")
    private TMCOSM10Service TMCOSM10Service;
    
    @Resource(name = "TMCOBD20Service")
    private TMCOBD20Service TMCOBD20Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * tm_svrqxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOSM10VO
	 * @return "/TMCOSM10/TMCOSM10List"
	 * @exception Exception
	 */
    @RequestMapping(value="/TMCOSM10",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOSM10List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOSM10VO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setRqstUser(sess.getUser().getId());
    	
    	List<?> TMCOSM10List = TMCOSM10Service.selectTMCOSM10List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOSM10List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * tm_svrqxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOSM10VO
	 * @return "/TMCOSM20"
	 * @exception Exception
	 */
    @RequestMapping(value="/TMCOSM20",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOSM20List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOSM10VO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setProcMode("APPROVAL");
    	
    	//파트장 로그인 정보 없어서 주석 처리
    	//searchVO.setApplUser(sess.getUser().getId());
    	
    	List<?> TMCOSM10List = TMCOSM10Service.selectTMCOSM10List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOSM10List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * tm_svrqxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 TMCOSM10VO
	 * @return "/TMCOSM30"
	 * @exception Exception
	 */
    @RequestMapping(value="/TMCOSM30",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOSM30List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOSM10VO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setProcMode("RECIPT");
    	
    	//파트장 정보 없어서 주석 처리
    	//searchVO.setApplUser(sess.getUser().getId());
    	
    	List<?> TMCOSM10List = TMCOSM10Service.selectTMCOSM10List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(TMCOSM10List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * tm_svrqxm 목록을 조회한다. (pageing)
     * @param searchVO - 조회할 정보가 담긴 TMCOSM10VO
     * @return "/TMCOSM40"
     * @exception Exception
     */
    @RequestMapping(value="/TMCOSM40",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOSM40List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOSM10VO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
    				throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	searchVO.setCorpCode(corpCode);
    	searchVO.setProcMode("ADMIN");
    	
    	//파트장 정보 없어서 주석 처리
    	//searchVO.setApplUser(sess.getUser().getId());
    	
    	List<?> TMCOSM10List = TMCOSM10Service.selectTMCOSM10List(searchVO);
    	
    	PageSet pageSet = new PageSet();
    	pageSet.setResult(TMCOSM10List);
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOSM10/saveTMCOSM10")
    public ResponseEntity<ResultEx> addTMCOSM10(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setRqstUser(sess.getUser().getId());
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setProcStatus("SAVE");
    	TMCOSM10VO.setCorpCode(corpCode);
    	
    	//파일 저장
		String userPath = sess.getUser().getId();
		CommonFileVO ufile;
		
		if (TMCOSM10VO.getFile() != null) {
			if (!TMCOSM10VO.getFile().isEmpty()) {		
				
				TMCOBD20VO TMCOBD20VO = new TMCOBD20VO();
				
				TMCOBD20VO.setFile(TMCOSM10VO.getFile());
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
				
				TMCOSM10VO.setFileId(TMCOBD20VO.getFileId());
			}
		}
    	
    	TMCOSM10Service.insertTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOSM10/saveApprovalTMCOSM10")
    public ResponseEntity<ResultEx> saveApprovalTMCOSM10(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setRqstUser(sess.getUser().getId());
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setProcStatus("APPROVAL");
    	TMCOSM10VO.setCorpCode(corpCode);
    	
    	TMCOSM10Service.insertTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
    	
        return _filter.makeCORSEntity( result );
    }    
    
    @RequestMapping(value="/TMCOSM10/reApprovalTMCOSM10")
    public ResponseEntity<ResultEx> reApprovalTMCOSM10(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setRqstUser(sess.getUser().getId());
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setCorpCode(corpCode);
    	    	
    	// 기존 요청서 재상신 처리
    	TMCOSM10VO.setProcStatus("REAPPROVAL");
    	
    	TMCOSM10Service.updateProcStatusTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
    	
    	// 신규 요청서 등록
    	TMCOSM10VO.setServId(null);
    	TMCOSM10VO.setProcStatus("APPROVAL");
    	
    	TMCOSM10Service.insertTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOSM10/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectTMCOSM10(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody TMCOSM10VO TMCOSM10VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(TMCOSM10Service.selectTMCOSM10(TMCOSM10VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/TMCOSM10/updateTMCOSM10")
    public ResponseEntity<ResultEx> updateTMCOSM10(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res,
            SessionStatus status,
    		HttpSession jsession)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	//파일 저장
		String userPath = sess.getUser().getId();
		CommonFileVO ufile;
		
		if (TMCOSM10VO.getFile() != null) {
			if (!TMCOSM10VO.getFile().isEmpty()) {		
				
				TMCOBD20VO TMCOBD20VO = new TMCOBD20VO();
				
				TMCOBD20VO.setFile(TMCOSM10VO.getFile());
				TMCOBD20VO.setMdUser(sess.getUser().getId());
				TMCOBD20VO.setCrUser(sess.getUser().getId());
				
				MultipartFile file = TMCOBD20VO.getFile();
				
				String uploadPath = TMCOBD20VO.getFileBasePath() + userPath;
				
				ufile = uploadAndFileInfo(file, uploadPath);
				
				// 파일 정보 설정
				if(StringUtils.isEmpty(TMCOSM10VO.getFileId())) {					
					TMCOBD20VO.setFileId(TMCOBD20Service.selectNextFileId(TMCOBD20VO));
					TMCOBD20Service.insertFileMaster(TMCOBD20VO);
					TMCOSM10VO.setFileId(TMCOBD20VO.getFileId());
				} else {
					TMCOBD20VO.setFileId(TMCOSM10VO.getFileId());
				}
				
				TMCOBD20VO.setFilePath(uploadPath);
				TMCOBD20VO.setSaveFlnm(ufile.getNewName());
				TMCOBD20VO.setOriginFlnm(ufile.getOrginalName());
				TMCOBD20VO.setFileExt(new FileUtil().getExtension(ufile.getOrginalName()));
				
				TMCOBD20Service.insertFileDetail(TMCOBD20VO);
			}
		}
    	
        TMCOSM10Service.updateTMCOSM10(TMCOSM10VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOSM10/deleteTMCOSM10")
    public ResponseEntity<ResultEx> deleteTMCOSM10(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10Service.deleteTMCOSM10(TMCOSM10VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOSM10/approvalTMCOSM10")
    public ResponseEntity<ResultEx> approvalTMCOSM10(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setProcStatus("APPROVAL");
    	
    	TMCOSM10Service.updateProcStatusTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
        
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/TMCOSM20/approvalTMCOSM20")
    public ResponseEntity<ResultEx> approvalTMCOSM20(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setProcStatus("RECIPT");
    	
    	TMCOSM10Service.updateProcStatusTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOSM20/rejectTMCOSM20")
    public ResponseEntity<ResultEx> rejectTMCOSM20(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setProcStatus("RETURN");
    	
    	TMCOSM10Service.updateProcStatusTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/TMCOSM30/reciptTMCOSM30")
    public ResponseEntity<ResultEx> reciptTMCOSM30(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setProcStatus("PROCESS");
    	
    	TMCOSM10Service.updateProcStatusTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    
    @RequestMapping(value="/TMCOSM30/finishTMCOSM30")
    public ResponseEntity<ResultEx> finishTMCOSM30(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOSM10VO TMCOSM10VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOSM10VO.setProcUser(sess.getUser().getId());
    	TMCOSM10VO.setProcStatus("FINISH");
    	
    	TMCOSM10Service.updateProcStatusTMCOSM10(TMCOSM10VO);
    	TMCOSM10Service.insertTmSvpcmx(TMCOSM10VO);
        
        return _filter.makeCORSEntity( result );
    }    

}
