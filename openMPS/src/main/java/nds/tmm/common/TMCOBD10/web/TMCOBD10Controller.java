package nds.tmm.common.TMCOBD10.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.user.web.UserInfoController;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;
import nds.tmm.common.TMCOBD10.service.TMCOBD10Service;
import nds.tmm.common.TMCOBD10.vo.TMCOBD10VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOBD10Controller.java
 * @Description : TMCOBD10 Controller class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see Copyright (C) All right reserved.
 */

@Controller
@RequestMapping("/tmm/{corpCode}")
public class TMCOBD10Controller extends TMMBaseController {

	private static final Logger _logger = LoggerFactory
			.getLogger(UserInfoController.class);

	@Autowired
	protected CorsFilter _filter;

	@Resource(name = "TMCOBD10Service")
	private TMCOBD10Service TMCOBD10Service;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/**
	 * tm_bdcaxm 목록을 조회한다. (pageing)
	 * 
	 * @param searchVO
	 *            - 조회할 정보가 담긴 TMCOBD10DefaultVO
	 * @return "/TMCOBD10/TMCOBD10List"
	 * @exception Exception
	 */
	@RequestMapping(value = "/TMCOBD10", method = RequestMethod.GET)
	public ResponseEntity<ResultEx> selectTMCOBD10List(
			@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOBD10VO searchVO, 
			HttpServletRequest req, HttpServletResponse res, ModelMap model)
			throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
		
		List<?> TMCOBD10List = TMCOBD10Service.selectTMCOBD10List(searchVO);

		PageSet pageSet = new PageSet();
		pageSet.setResult(TMCOBD10List);
		result.setExtraData(pageSet);

		return _filter.makeCORSEntity(result);
	}
	
	@RequestMapping(value = "/TMCOBD10/bbsCode", method = RequestMethod.GET)
	public ResponseEntity<ResultEx> selectBbsCode(
			@PathVariable("corpCode") String corpCode,
    		@ModelAttribute("searchVO") TMCOBD10VO searchVO, 
			HttpServletRequest req, HttpServletResponse res, ModelMap model)
			throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);
		
    	searchVO.setCorpCode(corpCode);
    	searchVO.setUseYn("Y");
		
		List<?> TMCOBD10List = TMCOBD10Service.selectTMCOBD10List(searchVO);

		PageSet pageSet = new PageSet();
		pageSet.setResult(TMCOBD10List);
		result.setExtraData(pageSet);

		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value = "/TMCOBD10", method = RequestMethod.POST)
	public ResponseEntity<ResultEx> addTMCOBD10(
			@PathVariable("corpCode") String corpCode,
			@RequestBody List<EgovMap> listTMCOBD10VO, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);

		List ilist = new ArrayList();

		if (listTMCOBD10VO != null) {
			for (EgovMap vo : listTMCOBD10VO) {
				if (sess != null) {
					vo.put("corpCode", corpCode);
					vo.put("crUser", sess.getUser().getId());
					vo.put("mdUser", sess.getUser().getId());
				}
				ilist.add(vo);
			}
		}

		TMCOBD10Service.insertTMCOBD10(ilist);
		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value = "/TMCOBD10/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResultEx> selectTMCOBD10(
			@PathVariable("corpCode") String corpCode,
			@RequestBody EgovMap TMCOBD10VO) throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		result.setExtraData(TMCOBD10Service.selectTMCOBD10(TMCOBD10VO));
		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value = "/TMCOBD10/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResultEx> updateTMCOBD10(
			@PathVariable("corpCode") String corpCode,
			@RequestBody EgovMap TMCOBD10VO, SessionStatus status)
			throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		TMCOBD10Service.updateTMCOBD10(TMCOBD10VO);
		status.setComplete();
		return _filter.makeCORSEntity(result);
	}

	@RequestMapping(value = "/TMCOBD10/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResultEx> deleteTMCOBD10(
			@PathVariable("corpCode") String corpCode,
			@RequestBody EgovMap TMCOBD10VO, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ResultEx result = new ResultEx(Consts.ResultCode.RC_OK);

		MPUserSession sess = getSession(req);

		TMCOBD10VO.put("crUser", sess.getUser().getId());
		TMCOBD10VO.put("mdUser", sess.getUser().getId());

		TMCOBD10Service.deleteTMCOBD10(TMCOBD10VO);

		return _filter.makeCORSEntity(result);
	}

}
