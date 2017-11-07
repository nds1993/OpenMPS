package nds.mpm.sales.SD0501.web;

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
import nds.mpm.sales.SD0501.service.SD0501Service;
import nds.mpm.sales.SD0501.vo.SD0501VO;

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
 * @Class Name : SD0501Controller.java
 * @Description : SD0501 Controller class
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
@RequestMapping("/mpm/{corpCode}")
public class SD0501Controller extends TMMBaseController {
	
	private static final Logger _logger = LoggerFactory.getLogger(UserInfoController.class);
	
	@Autowired
	protected CorsFilter	_filter;	
	
    @Resource(name = "SD0501Service")
    private SD0501Service SD0501Service;
    
    @Resource(name = "TMCOBD20Service")
    private TMCOBD20Service TMCOBD20Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * sd_claimrqxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0501VO
	 * @return "/SD0501/SD0501List"
	 * @exception Exception
	 */
    @RequestMapping(value="/SD0501",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0501List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") SD0501VO searchVO, 
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setRqstUser(sess.getUser().getId());
    	
    	List<?> SD0501List = SD0501Service.selectSD0501List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(SD0501List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * sd_claimrqxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0501VO
	 * @return "/SD0502"
	 * @exception Exception
	 */
    @RequestMapping(value="/SD0502",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0502List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") SD0501VO searchVO, 
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
    	
    	List<?> SD0501List = SD0501Service.selectSD0501List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(SD0501List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
	 * sd_claimrqxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SD0501VO
	 * @return "/SD0503"
	 * @exception Exception
	 */
    @RequestMapping(value="/SD0503",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0503List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") SD0501VO searchVO, 
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
    	
    	List<?> SD0501List = SD0501Service.selectSD0501List(searchVO);
    	
    	PageSet pageSet = new PageSet();
        pageSet.setResult(SD0501List);
		result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    /**
     * sd_claimrqxm 목록을 조회한다. (pageing)
     * @param searchVO - 조회할 정보가 담긴 SD0501VO
     * @return "/SD0504"
     * @exception Exception
     */
    @RequestMapping(value="/SD0504",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0504List(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") SD0501VO searchVO, 
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
    	
    	List<?> SD0501List = SD0501Service.selectSD0501List(searchVO);
    	
    	PageSet pageSet = new PageSet();
    	pageSet.setResult(SD0501List);
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/SD0501/saveSD0501")
    public ResponseEntity<ResultEx> addSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res,
    		HttpSession jsession)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setRqstUser(sess.getUser().getId());
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setProcStatus("SAVE");
    	SD0501VO.setCorpCode(corpCode);
    	
    	//파일 저장
		String userPath = sess.getUser().getId();
		String contextroot = jsession.getServletContext().getRealPath("/");
		CommonFileVO ufile;
		
		if (SD0501VO.getFile() != null) {
			if (!SD0501VO.getFile().isEmpty()) {		
				
				TMCOBD20VO TMCOBD20VO = new TMCOBD20VO();
				
				TMCOBD20VO.setFile(SD0501VO.getFile());
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
				
				SD0501VO.setFileId(TMCOBD20VO.getFileId());
			}
		}
    	
    	SD0501Service.insertSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/SD0501/saveApprovalSD0501")
    public ResponseEntity<ResultEx> saveApprovalSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setRqstUser(sess.getUser().getId());
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setProcStatus("APPROVAL");
    	SD0501VO.setCorpCode(corpCode);
    	
    	SD0501Service.insertSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
    	
        return _filter.makeCORSEntity( result );
    }    
    
    @RequestMapping(value="/SD0501/reApprovalSD0501")
    public ResponseEntity<ResultEx> reApprovalSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setRqstUser(sess.getUser().getId());
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setCorpCode(corpCode);
    	    	
    	// 기존 요청서 재상신 처리
    	SD0501VO.setProcStatus("REAPPROVAL");
    	
    	SD0501Service.updateProcStatusSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
    	
    	// 신규 요청서 등록
    	SD0501VO.setServId(null);
    	SD0501VO.setProcStatus("APPROVAL");
    	
    	SD0501Service.insertSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
    	
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/SD0501/{id}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@RequestBody SD0501VO SD0501VO
            ) throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	result.setExtraData(SD0501Service.selectSD0501(SD0501VO));
    	return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/SD0501/updateSD0501")
    public ResponseEntity<ResultEx> updateSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res,
            SessionStatus status,
    		HttpSession jsession)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	//파일 저장
		String userPath = sess.getUser().getId();
		String contextroot = jsession.getServletContext().getRealPath("/");
		CommonFileVO ufile;
		
		if (SD0501VO.getFile() != null) {
			if (!SD0501VO.getFile().isEmpty()) {		
				
				TMCOBD20VO TMCOBD20VO = new TMCOBD20VO();
				
				TMCOBD20VO.setFile(SD0501VO.getFile());
				TMCOBD20VO.setMdUser(sess.getUser().getId());
				TMCOBD20VO.setCrUser(sess.getUser().getId());
				
				MultipartFile file = TMCOBD20VO.getFile();
				
				String uploadPath = TMCOBD20VO.getFileBasePath() + userPath;
				
				ufile = uploadAndFileInfo(file, uploadPath);
				
				// 파일 정보 설정
				if(StringUtils.isEmpty(SD0501VO.getFileId())) {					
					TMCOBD20VO.setFileId(TMCOBD20Service.selectNextFileId(TMCOBD20VO));
					TMCOBD20Service.insertFileMaster(TMCOBD20VO);
					SD0501VO.setFileId(TMCOBD20VO.getFileId());
				} else {
					TMCOBD20VO.setFileId(SD0501VO.getFileId());
				}
				
				TMCOBD20VO.setFilePath(uploadPath);
				TMCOBD20VO.setSaveFlnm(ufile.getNewName());
				TMCOBD20VO.setOriginFlnm(ufile.getOrginalName());
				TMCOBD20VO.setFileExt(new FileUtil().getExtension(ufile.getOrginalName()));
				
				TMCOBD20Service.insertFileDetail(TMCOBD20VO);
			}
		}
    	
        SD0501Service.updateSD0501(SD0501VO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/SD0501/deleteSD0501")
    public ResponseEntity<ResultEx> deleteSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501Service.deleteSD0501(SD0501VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/SD0501/approvalSD0501")
    public ResponseEntity<ResultEx> approvalSD0501(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setProcStatus("APPROVAL");
    	
    	SD0501Service.updateProcStatusSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
        
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/SD0502/approvalSD0502")
    public ResponseEntity<ResultEx> approvalSD0502(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setProcStatus("RECIPT");
    	
    	SD0501Service.updateProcStatusSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/SD0502/rejectSD0502")
    public ResponseEntity<ResultEx> rejectSD0502(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setProcStatus("RETURN");
    	
    	SD0501Service.updateProcStatusSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/SD0503/reciptSD0503")
    public ResponseEntity<ResultEx> reciptSD0503(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setProcStatus("PROCESS");
    	
    	SD0501Service.updateProcStatusSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
        
        return _filter.makeCORSEntity( result );
    }
    
    
    @RequestMapping(value="/SD0503/finishSD0503")
    public ResponseEntity<ResultEx> finishSD0503(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute SD0501VO SD0501VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	SD0501VO.setProcUser(sess.getUser().getId());
    	SD0501VO.setProcStatus("FINISH");
    	
    	SD0501Service.updateProcStatusSD0501(SD0501VO);
    	SD0501Service.insertSdClaimpcxm(SD0501VO);
        
        return _filter.makeCORSEntity( result );
    }    

}
