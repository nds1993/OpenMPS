package nds.tmm.common.TMCOBD20.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nds.frm.util.StringUtil;
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
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;
import net.sf.json.util.NewBeanInstanceStrategy;

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
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOBD20Controller.java
 * @Description : TMCOBD20 Controller class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller
@RequestMapping("/tmm/{corpCode}")
public class TMCOBD20Controller extends TMMBaseController {

	private static final Logger _logger = LoggerFactory
			.getLogger(UserInfoController.class);

	@Autowired
	protected CorsFilter _filter;

	@Resource(name = "TMCOBD20Service")
	private TMCOBD20Service TMCOBD20Service;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * tm_bdcaxm 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 TMCOBD20DefaultVO
	 * @return "/TMCOBD20/TMCOBD20List"
	 * @exception Exception
	 */
	@RequestMapping(value = "/TMCOBD20", method = RequestMethod.GET)
	public ResponseEntity<ResultEx> selectTMCOBD20List(
			@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOBD20VO searchVO, 
			HttpServletRequest req, HttpServletResponse res, ModelMap model)
			throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
		
		List<?> TMCOBD20List = TMCOBD20Service.selectTMCOBD20List(searchVO);

		PageSet pageSet = new PageSet();
		pageSet.setResult(TMCOBD20List);
		result.setExtraData(pageSet);

		return _filter.makeCORSEntity(result);
	}
	
	/**
	 * tm_bdcaxm 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 TMCOBD20DefaultVO
	 * @return "/TMCOBD20/TMCOBD20List"
	 * @exception Exception
	 */
	@RequestMapping(value = "/TMCOBD40/{bbsCode}", method = RequestMethod.GET)
	public ResponseEntity<ResultEx> selectTMCOBD40List(
			@PathVariable("corpCode") String corpCode,
			@PathVariable("bbsCode") String bbsCode,
    		@ModelAttribute("searchVO") TMCOBD20VO searchVO, 
			HttpServletRequest req, HttpServletResponse res, ModelMap model)
			throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSearchMode("USER");
    	searchVO.setBbsCode(bbsCode);
    	searchVO.setReadId(sess.getUser().getId());
		
		List<?> TMCOBD20List = TMCOBD20Service.selectTMCOBD20List(searchVO);

		PageSet pageSet = new PageSet();
		pageSet.setResult(TMCOBD20List);
		result.setExtraData(pageSet);

		return _filter.makeCORSEntity(result);
	}
	
	/**
	 * tm_bdcaxm 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 TMCOBD20DefaultVO
	 * @return "/TMCOBD20/TMCOBD20List"
	 * @exception Exception
	 */
	@RequestMapping(value = "/TMCOBD20/selectFileList")
	public ResponseEntity<ResultEx> selectFileList(
			@PathVariable("corpCode") String corpCode,
			@ModelAttribute("searchVO") TMCOBD20VO searchVO, 
			HttpServletRequest req, HttpServletResponse res, ModelMap model)
			throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		List<?> TMCOBD20List = TMCOBD20Service.selectFile(searchVO);

		PageSet pageSet = new PageSet();
		pageSet.setResult(TMCOBD20List);
		result.setExtraData(pageSet);

		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value="/TMCOBD20/downloadFile")
	public ResponseEntity<ResultEx> downloadFile(
			@PathVariable("corpCode") String corpCode,
			@ModelAttribute TMCOBD20VO TMCOBD20VO, HttpServletRequest req,
			HttpServletResponse res,
    		HttpSession jsession) throws Exception {

		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		List<?> TMCOBD20List = TMCOBD20Service.selectFile(TMCOBD20VO);
		
		if (TMCOBD20List.size() > 0) {
			
			TMCOBD20VO resultTMCOBD20VO = (TMCOBD20VO) TMCOBD20List.get(0);
			
			FileUtil.downloadFile(resultTMCOBD20VO.getFilePath(), resultTMCOBD20VO.getSaveFlnm(), resultTMCOBD20VO.getOriginFlnm(), res);
		}
		
		return _filter.makeCORSEntity(result);
	}
	
	@RequestMapping(value="/TMCOBD20/deleteFile")
    public ResponseEntity<ResultEx> deleteFile(
    		@PathVariable("corpCode") String corpCode,
			@ModelAttribute TMCOBD20VO TMCOBD20VO, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);

		TMCOBD20VO.setMdUser(sess.getUser().getId());
		TMCOBD20VO.setCorpCode(corpCode);

		TMCOBD20Service.deleteFile(TMCOBD20VO);
		return _filter.makeCORSEntity(result);
    }
	
	@RequestMapping(value="/TMCOBD20/saveTMCOBD20")
	public ResponseEntity<ResultEx> addTMCOBD20(
			@PathVariable("corpCode") String corpCode,
			@ModelAttribute TMCOBD20VO TMCOBD20VO, HttpServletRequest req,
			HttpServletResponse res,
    		HttpSession jsession) throws Exception {

		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);

		TMCOBD20VO.setCrUser(sess.getUser().getId());
		TMCOBD20VO.setCorpCode(corpCode);
		
		//파일 저장
		String userPath = sess.getUser().getId();
		String contextroot = jsession.getServletContext().getRealPath("/");
		CommonFileVO ufile;
		
		if (TMCOBD20VO.getFile() != null) {
			if (!TMCOBD20VO.getFile().isEmpty()) {		
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
			}
		}
		
		if ("Y".equals(TMCOBD20VO.getContReyn())) {
			TMCOBD20Service.updateReplySortOrderTMCOBD20(TMCOBD20VO);
		} else {
			TMCOBD20VO.setContLv("0");
			TMCOBD20VO.setSortOrder("0");
		}

		TMCOBD20Service.insertTMCOBD20(TMCOBD20VO);
		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value = "/TMCOBD20/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResultEx> selectTMCOBD20(
			@PathVariable("corpCode") String corpCode,
			@RequestBody EgovMap TMCOBD20VO) throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		result.setExtraData(TMCOBD20Service.selectTMCOBD20(TMCOBD20VO));
		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value="/TMCOBD20/updateTMCOBD20")
    public ResponseEntity<ResultEx> updateTMCOBD20(
    		@PathVariable("corpCode") String corpCode,
			@ModelAttribute TMCOBD20VO TMCOBD20VO, HttpServletRequest req,
			HttpServletResponse res,
    		HttpSession jsession) throws Exception {

		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);

		TMCOBD20VO.setMdUser(sess.getUser().getId());
		TMCOBD20VO.setCrUser(sess.getUser().getId());
		TMCOBD20VO.setCorpCode(corpCode);
		
		//파일 저장
		String userPath = sess.getUser().getId();
		String contextroot = jsession.getServletContext().getRealPath("/");
		CommonFileVO ufile;
		
		if (TMCOBD20VO.getFile() != null) {
			if (!TMCOBD20VO.getFile().isEmpty()) {		
				MultipartFile file = TMCOBD20VO.getFile();
				
				String uploadPath = TMCOBD20VO.getFileBasePath() + userPath;
				
				ufile = uploadAndFileInfo(file, uploadPath);
				
				// 파일 정보 설정
				if(StringUtils.isEmpty(TMCOBD20VO.getFileId())) {					
					TMCOBD20VO.setFileId(TMCOBD20Service.selectNextFileId(TMCOBD20VO));
					TMCOBD20Service.insertFileMaster(TMCOBD20VO);
				}
				
				TMCOBD20VO.setFilePath(uploadPath);
				TMCOBD20VO.setSaveFlnm(ufile.getNewName());
				TMCOBD20VO.setOriginFlnm(ufile.getOrginalName());
				TMCOBD20VO.setFileExt(new FileUtil().getExtension(ufile.getOrginalName()));
				
				TMCOBD20Service.insertFileDetail(TMCOBD20VO);
			}
		}

		TMCOBD20Service.updateTMCOBD20(TMCOBD20VO);
		return _filter.makeCORSEntity(result);
    }
	
	@RequestMapping(value="/TMCOBD20/deleteTMCOBD20")
    public ResponseEntity<ResultEx> deleteTMCOBD20(
    		@PathVariable("corpCode") String corpCode,
			@ModelAttribute TMCOBD20VO TMCOBD20VO, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);

		TMCOBD20VO.setMdUser(sess.getUser().getId());
		TMCOBD20VO.setCorpCode(corpCode);

		TMCOBD20Service.deleteTMCOBD20(TMCOBD20VO);
		return _filter.makeCORSEntity(result);
    }
	
	@RequestMapping(value="/TMCOBD40/saveReadCnt")
    public ResponseEntity<ResultEx> saveReadCnt(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOBD20VO TMCOBD20VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOBD20VO.setCrUser(sess.getUser().getId());
    	TMCOBD20VO.setCorpCode(corpCode);
    	
    	TMCOBD20VO.setReadUser(sess.getUser().getId());
    	
    	TMCOBD20VO reusltVO = TMCOBD20Service.selectBdredhTMCOBD20(TMCOBD20VO);
    	
    	if (reusltVO == null) {
    		TMCOBD20Service.insertBdredhTMCOBD20(TMCOBD20VO);
    		TMCOBD20Service.updateReadCntTMCOBD20(TMCOBD20VO);
    	}
    	
        return _filter.makeCORSEntity( result );
    }
	
	@RequestMapping(value="/TMCOBD40/saveComment")
    public ResponseEntity<ResultEx> saveComment(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOBD20VO TMCOBD20VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOBD20VO.setCrUser(sess.getUser().getId());
    	TMCOBD20VO.setCrName(sess.getUser().getName());
    	TMCOBD20VO.setCorpCode(corpCode);
    	
    	TMCOBD20Service.insertComment(TMCOBD20VO);
    	
        return _filter.makeCORSEntity( result );
    }	
	
	/**
	 * tm_bdcaxm 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 TMCOBD20DefaultVO
	 * @return "/TMCOBD20/TMCOBD20List"
	 * @exception Exception
	 */
	@RequestMapping(value = "/TMCOBD40/selectComment")
	public ResponseEntity<ResultEx> selectCommentList(
			@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOBD20VO searchVO, 
			HttpServletRequest req, HttpServletResponse res, ModelMap model)
			throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setSearchMode("USER");
		
		List<?> TMCOBD20List = TMCOBD20Service.selectComment(searchVO);

		PageSet pageSet = new PageSet();
		pageSet.setResult(TMCOBD20List);
		result.setExtraData(pageSet);

		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value="/TMCOBD40/updateComment")
    public ResponseEntity<ResultEx> updateComment(
    		@PathVariable("corpCode") String corpCode,
    		@ModelAttribute TMCOBD20VO TMCOBD20VO,
    		HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MPUserSession sess = getSession(req);
    	
    	TMCOBD20VO.setMdUser(sess.getUser().getId());
    	TMCOBD20VO.setCorpCode(corpCode);
    	
    	TMCOBD20Service.updateComment(TMCOBD20VO);
    	
        return _filter.makeCORSEntity( result );
    }	
	
	@RequestMapping(value="/TMCOBD40/deleteComment")
	public ResponseEntity<ResultEx> deleteComment(
			@PathVariable("corpCode") String corpCode,
			@ModelAttribute TMCOBD20VO TMCOBD20VO,
			HttpServletRequest req,
			HttpServletResponse res)
					throws Exception {
		
		ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
		
		MPUserSession sess = getSession(req);
		
		TMCOBD20VO.setMdUser(sess.getUser().getId());
		TMCOBD20VO.setCorpCode(corpCode);
		
		TMCOBD20Service.deleteComment(TMCOBD20VO);
		
		return _filter.makeCORSEntity( result );
	}	
	
}
