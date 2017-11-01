package nds.mpm.buy.PO0301.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.buy.PO0301.service.PO0301MpPigoxmService;
import nds.mpm.buy.PO0301.service.PO0301MpPigxxdService;
import nds.mpm.buy.PO0301.vo.MpPigoxmVO;
import nds.mpm.buy.PO0301.vo.MpPigxxdVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.TMMBaseController;
import nds.mpm.login.vo.MPUserSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name :  PigOtherController
 *
 * @author MPM TEAM
 * @since 2017.08.31
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 이상육 발생현황 관리( PO0301 )
 * 		TAB3 이상육(목심)
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.08.31	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/{corpCode}/po0301/mppigoxm")
public class PO0301Tab3MpPigoxmController extends TMMBaseController {

	@Resource(name = "PO0301MpPigxxdService")
    private PO0301MpPigxxdService PO0301MpPigxxdService;
	
    @Resource(name = "PO0301MpPigoxmService")
    private PO0301MpPigoxmService PO0301MpPigoxmService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
     * TAB3 계류사돈/도축사고/정산차액 조회
	 * mp_pidoxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 PO0301MpPigoxmDefaultVO
	 * @exception Exception
	 */
    @RequestMapping(value="/tab3/{dochDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPO0301MpPigoxmTab2List(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("dochDate") String dochDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        int pageIndex = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageIndex"),"0"));
    	int pageSize = Integer.parseInt(StringUtils.defaultIfEmpty(req.getParameter("pageSize"),"0"));

    	String custCode = req.getParameter("custCode");
    	String brandCode = req.getParameter("brandCode");
    	
    	MpPigxxdVO searchVO = new MpPigxxdVO();
    	searchVO.setCorpCode(corpCode);
    	
    	/*searchVO.setBrandCode(brandCode);*/
    	searchVO.setCustCode(custCode);
    	searchVO.setDochDate(StringUtils.remove(dochDate,"-"));
    	searchVO.setBrandCode(brandCode);
    	
    	searchVO.setPageIndex(pageIndex);
    	searchVO.setPageSize(pageSize);
    	
        List<?> PO0301MpPigoxmList = PO0301MpPigxxdService.selectMpPigxxdList(searchVO);
    	PageSet pageSet = new PageSet();
        
        pageSet.setPageIndex(pageIndex);
    	pageSet.setPageSize(pageSize);
    	
    	if(PO0301MpPigoxmList!=null)
    	pageSet.setTotalRecordCount(PO0301MpPigoxmList.size());
    	pageSet.setResult(PO0301MpPigoxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    
    /**
     * TAB3 이상육 조회 이상육일괄생성 버튼 
     * 돼지구매 및 정산내역 도축일자, 농장번호, 도체번호 합계 조회. 
	 * mp_pigdxm 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 PO0301MpPigoxmDefaultVO
	 * @exception Exception
	 */
    @RequestMapping(value="/tab3/mppigdxm/{procDate}/{dochDate}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectPO0301MpPigdxmList(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("procDate") String procDate,
    		@PathVariable("dochDate") String dochDate,
    		HttpServletRequest req,
    		HttpServletResponse res, 
    		ModelMap model)
            throws Exception {
    	
        ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	MpPigoxmVO searchVO = new MpPigoxmVO();
    	searchVO.setCorpCode(corpCode);
    	searchVO.setDochDate(dochDate);
    	searchVO.setProcDate(procDate);
    	
        List<?> PO0301MpPigoxmList = PO0301MpPigoxmService.selectPO0301Tab3MpPigdxmList_D(searchVO);

    	PageSet pageSet = new PageSet();
        
    	if(PO0301MpPigoxmList!= null)
    		pageSet.setTotalRecordCount(PO0301MpPigoxmList.size());
    	pageSet.setResult(PO0301MpPigoxmList);
		
    	result.setExtraData(pageSet);
    	
    	return _filter.makeCORSEntity( result );
    } 
    /**
     * TAB3 이상육추가 저장
	 * @param searchVO - 조회할 정보가 담긴 PO0301MpPigoxmDefaultVO
	 * @exception Exception
	 */
    @RequestMapping(value="/tab3/{dochDate}",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addPO0301Tab2MpPigoxm(
    		@PathVariable("corpCode") String corpCode,
    		@PathVariable("dochDate") String dochDate,
            @RequestBody List<EgovMap> mpPigxxdVOs,
            HttpServletRequest req,
    		HttpServletResponse res)
            throws Exception {
            	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	MPUserSession sess = getSession(req);
    	
    	List ilist = new ArrayList();
    	/***
    	 * 
    	 * 리스트가 MpPigxxd 객체 형이므로 table mp_pigoxm으로 저장하기위해 MpPigoxmVO형변환작업필요
    	 * 
    	 * */
    	if(mpPigxxdVOs != null)
    	{
    		for(EgovMap vo : mpPigxxdVOs)
        	{
    			vo.put("corpCode",corpCode);
    			vo.put("dochDate",StringUtils.remove(dochDate,"-"));
    			vo.put("carNo","0"); //구데이터 관리 고정값 
    			vo.put("othKind","0"); //이상육코드 PO004
    			vo.put("othCause",0); //구데이터 관리 고정값 
    			vo.put("othPart","목심"); 
    			vo.put("othWeig",0); //
    			vo.put("othPric","setlAmt"); //정산금액
    			vo.put("othDisu","setlMeat"); //폐기
    			vo.put("othDanga1","scraAmt"); //폐기단가
    			vo.put("other","othMeat"); //잡육
    			vo.put("othDanga2","othAmt"); //잡육단가
    			vo.put("memo","추가입력"); //memo
    			if(sess != null)
    			{
    				vo.put("crUser",sess.getUser().getId());
    				vo.put("mdUser",sess.getUser().getId());
    			}
        		ilist.add(vo);
        	}
    	}
    	
    	result.setExtraData(PO0301MpPigoxmService.insertPO0301MpPigoxm(ilist));
    	
        return _filter.makeCORSEntity( result );
    }
    
}
