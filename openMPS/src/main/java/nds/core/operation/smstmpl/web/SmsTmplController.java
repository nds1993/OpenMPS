package nds.core.operation.smstmpl.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.smstmpl.service.SmsTmplService;
import nds.core.operation.smstmpl.service.SmsTmplVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


/**
 * <p>Title: SmsController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2014.07.12 초기작성(오예솔)</pre>
 * @author <a href="mailto:ysoh@nds.co.kr">오예솔</a>
 * @version 1.0
 */
@Controller
public class SmsTmplController extends BaseController {
	
	/** SmsService */
    @Resource(name = "smstmplService")
    private SmsTmplService smstmplService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    /**
     * SMS알림관리
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/smstmpl/oprat0400.do")
	public ModelAndView oprat0400(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
     
        String schTmplNm = StringUtil.getParam(request, "schTmplNm", "");
        String schUseYn = StringUtil.getParam(request, "schUseYn", "");
        String schChnlCat = StringUtil.getParam(request, "schChnlCat", "");
        String schProcStat = StringUtil.getParam(request, "schProcStat", "");
        String schInoutDvn = StringUtil.getParam(request, "schInoutDvn", "");
        String schTypeDvn = StringUtil.getParam(request, "schTypeDvn", "C");
        
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        
		ModelAndView mav = new ModelAndView("operation/smstmpl/oprat0400");
		
		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		
		SmsTmplVO smsVO = new SmsTmplVO();

		int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개

		smsVO.setSchTmplNm(schTmplNm);
		smsVO.setSchChnlCat(schChnlCat);
		smsVO.setSchProcStat(schProcStat);
		smsVO.setSchUseYn(schUseYn);
		smsVO.setSchInoutDvn(schInoutDvn);
		
		smsVO.setTypeDvn(schTypeDvn);
		
		smsVO.setStartNo(((page * rows) - rows) + 1);
		smsVO.setEndNo(page * rows);
		
		// 정렬
		smsVO.setSidx(sidx);
		smsVO.setSord(sord);
		
        if("S".equals(crudMode)) {		//목록
        	mav = new ModelAndView("jsonView");
        	response.setContentType("application/json; charset=utf-8");
    		response.setHeader("Cache-Control", "no-cache");
    		
    		int total = smstmplService.selectSmsTmplCnt(smsVO);
    		List<SmsTmplVO> result = smstmplService.selectSmsTmplList(smsVO);
            
    		mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows",  result);
        }
		
        // 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("oprat0400");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
    	mav.addObject("F153", this.getCommonCode("F153"));
    	mav.addObject("F201", this.getCommonCode("F201"));
    	mav.addObject("F002", this.getCommonCode("F002"));
    	mav.addObject("F016", this.getCommonCode("F016"));
    	mav.addObject("F051", this.getCommonCode("F051"));
    	mav.addObject("F080", this.getCommonCode("F080"));
		return mav; 
	}
    
    
    /**
	 * SMS템플릿 등록/수정/삭제
	 * 
	 * @param request
	 * @param reponse
	 */
    @RequestMapping("/operation/smstmpl/oprat0410.do")
	public ModelAndView oprat0410(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
	    String crudMode  = StringUtil.getParam(request, "crudMode", "").toUpperCase();
		String tmplNo = StringUtil.getParam(request, "tmplNo", ""); 
		String schTypeDvn = StringUtil.getParam(request, "schTypeDvn", "V"); 
		
	    ModelAndView mav =  new ModelAndView("operation/smstmpl/oprat0410");   

	    UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		
		SmsTmplVO smsVO = new SmsTmplVO();
		bind(request , smsVO);
		
		smsVO.setTypeDvn(schTypeDvn);
		smsVO.setRegUser(userEmpno);
		smsVO.setUpdtUser(userEmpno);
		
		if ("C".equals(crudMode)) {           // 등록처리
			
			smsVO.setRegUser(userEmpno);
			smsVO.setUpdtUser(userEmpno);
			
			String msg = null;
			
			if("Y".equals(smsVO.getUseYn()))
			{
				int result = Integer.parseInt(smstmplService.selectInsertTmpl(smsVO));
				
				if(result < 1){
					//(채널분류, 진행상태, 내외부구분) 당 사용여부가 사용인 템플릿이 존재하지 않는지 검사한 후 insert
					smstmplService.insertSmsTmpl(smsVO);
				}
				else{
					//(채널분류, 진행상태, 내외부구분) 당 사용여부가 사용인 템플릿이 존재할 경우 에러메세지  
					if("V".equals(schTypeDvn)){
						msg = "해당 [진행상태, 내외부구분]에 사용중인 템플릿이 존재합니다.";
					}
					else{
						msg = "해당 [진행상태]에 사용중인 템플릿이 존재합니다.";
					}
					mav.addObject("useCheck", "Y");
					mav.addObject("result", smsVO);
					mav.addObject("msg", msg);
					return mav;
				}
			}else{
				smstmplService.insertSmsTmpl(smsVO);
			}
			mav.addObject("result", smsVO);
			mav.addObject("crudMode", "");
			mav = new ModelAndView("operation/smstmpl/oprat0400");
			
		}
		else if ("U".equals(crudMode)){    // 수정처리

			smsVO.setUpdtUser(userEmpno);
			
			SmsTmplVO result = new SmsTmplVO();
			String changeYn = StringUtil.getParam(request, "changeYn", "");
			
			String msg = "";
			if("true".equals(changeYn)){
				if("Y".equals(smsVO.getUseYn()))
				{
					int cnt = Integer.parseInt(smstmplService.selectInsertTmpl(smsVO));
					if(cnt < 1){
						try{
							smstmplService.updateSmsTmpl(smsVO);
							result = smstmplService.selectByPrimaryKey(smsVO);
							msg="저장되었습니다.";
						}catch(Exception ex){
							msg="저장에 실패하였습니다.";
						}
					}
					else{
						//(등록채널, 처리상태) 당 사용여부가 사용인 템플릿이 존재할 경우 에러메세지  
						msg = "해당 [처리상태]에 사용중인 템플릿이 존재합니다.";
						result = smsVO;
						mav.addObject("crudMode", "");
					}
				}else{
					try{
						smstmplService.updateSmsTmpl(smsVO);
						result = smstmplService.selectByPrimaryKey(smsVO);
						msg="저장되었습니다.";
					}catch(Exception e){
						msg="저장이 실패하였습니다.";
					}
					
				}
			}else{
				try{
					smstmplService.updateSmsTmpl(smsVO);
					result = smstmplService.selectByPrimaryKey(smsVO);
					msg="저장되었습니다.";
				}catch(Exception ex){
					msg="저장이 실패하였습니다.";
				}
			}
			mav.addObject("msg", msg);
			mav.addObject("result", result);
		}
		else if ("D".equals(crudMode)){    // 삭제처리
			smsVO.setTmplNo(tmplNo);
			smstmplService.deleteSmsTmpl(smsVO); 
			
			mav.addObject("crudMode", "");
			mav = new ModelAndView("operation/smstmpl/oprat0400");
			
		}
		else if ("V".equals(crudMode)){	//상세내역
			smsVO.setTmplNo(tmplNo);
			mav.addObject("result", smstmplService.selectByPrimaryKey(smsVO));
		}

	    mav.addObject("F153", this.getCommonCode("F153"));
    	mav.addObject("F002", this.getCommonCode("F002"));
    	mav.addObject("F016", this.getCommonCode("F016"));
    	mav.addObject("F080", this.getCommonCode("F080"));
    	mav.addObject("F201", this.getCommonCode("F201"));
		return mav;
	}
	
}
