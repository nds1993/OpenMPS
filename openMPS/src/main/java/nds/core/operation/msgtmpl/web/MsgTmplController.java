package nds.core.operation.msgtmpl.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.msgtmpl.service.MsgTmplService;
import nds.core.operation.msgtmpl.service.MsgTmplVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

/**
 * <p>Title: MsgTmplController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2015.05.13 초기작성(최찬형)</pre>
 * @author <a href="mailto:chchoi@nds.co.kr">최찬형</a>
 * @version 1.0
 */
@Controller
public class MsgTmplController extends BaseController{
	/** MsgService */
	@Resource(name="msgTmplService")
	private MsgTmplService msgTmplService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    
    @RequestMapping("/op/oprat0500.do")
	public ModelAndView oprat0500(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mav = new ModelAndView("op/oprat0500");
    	
    	UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String usrEmpno = userSession.getLogin().getUserEmpno();

		String crudMode = StringUtil.getParam(request, "crudMode","");
		
		int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
		int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "20"));

		String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");

		MsgTmplVO msgTmplVO = new MsgTmplVO();

		if ("S".equals(crudMode)){                   // 조회데이터 
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");

			msgTmplVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			msgTmplVO.setEndNo(pageNo * pageSize);
			msgTmplVO.setTmplNm(StringUtil.getParam(request, "schTmplNm", ""));
			msgTmplVO.setUseYn(StringUtil.getParam(request, "schUseYn", ""));
			msgTmplVO.setChnlCat(StringUtil.getParam(request, "schChnlCat", ""));
			msgTmplVO.setProcStat(StringUtil.getParam(request, "schProcStat", ""));
			msgTmplVO.setInoutDvn(StringUtil.getParam(request, "schInoutDvn", ""));
			msgTmplVO.setSidx(sidx);
			msgTmplVO.setSord(sord);

			List<MsgTmplVO> result = null;
			result = msgTmplService.selectMsgTmplList(msgTmplVO);
			int totalCount = msgTmplService.selectMsgTmplListCount(msgTmplVO);

			mav.addObject("rows", result);
			mav.addObject("records", totalCount);
			mav.addObject("total", Math.ceil((double)totalCount/pageSize));

		}
		
		else if ("D".equals(crudMode)){    // 삭제처리
			String tmplNo = StringUtil.getParam(request, "tmplNo", "");
			msgTmplVO.setTmplNo(tmplNo);
			msgTmplService.deleteMsgTmpl(msgTmplVO);
		}

		// 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("oprat0500");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        
        mav.addObject("F153", this.getCommonCode("F153"));
    	mav.addObject("F201", this.getCommonCode("F201"));
    	mav.addObject("F002", this.getCommonCode("F002"));
    	mav.addObject("F016", this.getCommonCode("F016"));
		return mav; 
    }
    
    @RequestMapping("/op/oprat0510.do")
	public ModelAndView oprat0510(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String crudMode  = StringUtil.getParam(request, "crudMode", "").toUpperCase();
		String tmplNo = StringUtil.getParam(request, "tmplNo", ""); 
	    ModelAndView mav =  new ModelAndView("op/oprat0510");   

	    UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String userEmpno = userSession.getLogin().getUserEmpno();
		
		MsgTmplVO msgTmplVO = new MsgTmplVO();
		bind(request , msgTmplVO);
		
		msgTmplVO.setRegUser(userEmpno);
		msgTmplVO.setUpdtUser(userEmpno);
		
		if ("C".equals(crudMode)) {           // 등록처리
			
			msgTmplVO.setRegUser(userEmpno);	//삭제예정
			msgTmplVO.setUpdtUser(userEmpno);
			
			String msg = "";
			
			if("Y".equals(msgTmplVO.getUseYn()))
			{
				int result = Integer.parseInt(msgTmplService.checkInsertYn(msgTmplVO));
				if(result < 1){
					//(채널분류, 진행상태, 내외부구분) 당 사용여부가 사용인 템플릿이 존재하지 않는지 검사한 후 insert
					msgTmplService.insertMsgTmpl(msgTmplVO);
				}
				else{
					//(채널분류, 진행상태, 내외부구분) 당 사용여부가 사용인 템플릿이 존재할 경우 에러메세지  
					msg = "해당 [채널분류, 진행상태, 내외부구분]에 사용중인 템플릿이 존재합니다.";
					mav.addObject("result", msgTmplVO);
					mav.addObject("msg", msg);
					
					return mav;
				}
			}else{
				msgTmplService.insertMsgTmpl(msgTmplVO);
			}
			mav.addObject("crudMode", "");
			mav = new ModelAndView("op/oprat0500");
			
		}
		else if ("U".equals(crudMode)){    // 수정처리

			msgTmplVO.setUpdtUser(userEmpno);
			MsgTmplVO result = new MsgTmplVO();
			
			String changeYn = StringUtil.getParam(request, "changeYn", "");
			
			String msg = "";
			if("true".equals(changeYn)){
				if("Y".equals(msgTmplVO.getUseYn()))
				{
					int cnt = Integer.parseInt(msgTmplService.checkInsertYn(msgTmplVO));
					if(cnt < 1){
						try{
							msgTmplService.updateMsgTmpl(msgTmplVO);
							result = msgTmplService.selectMsgTmplInfo(msgTmplVO);
							msg="저장이 성공하였습니다.";
						}catch(Exception ex){
							msg="저장이 실패하였습니다.";
						}
					}
					else{
						//(등록채널, 처리상태) 당 사용여부가 사용인 템플릿이 존재할 경우 에러메세지  
						msg = "해당 [등록채널, 처리상태]에 사용중인 템플릿이 존재합니다.";
						MsgTmplVO vo = new MsgTmplVO();
						vo.setTmplNo(msgTmplVO.getTmplNo());
						
						result = msgTmplService.selectMsgTmplInfo(vo);
					}
				}else{
					try{
						msgTmplService.updateMsgTmpl(msgTmplVO);
						result = msgTmplService.selectMsgTmplInfo(msgTmplVO);
						msg="저장이 성공하였습니다.";
					}catch(Exception e){
						msg="저장이 실패하였습니다.";
					}
					
				}
			}else{
				try{
					msgTmplService.updateMsgTmpl(msgTmplVO);
					result = msgTmplService.selectMsgTmplInfo(msgTmplVO);
					msg="저장이 성공하였습니다.";
				}catch(Exception ex){
					msg="저장이 실패하였습니다.";
				}
			}
			mav.addObject("msg", msg);
			mav.addObject("result", result);
		}
		
		else if ("V".equals(crudMode)){	//상세내역
			msgTmplVO.setTmplNo(tmplNo);
			mav.addObject("result", msgTmplService.selectMsgTmplInfo(msgTmplVO));
		}

	    mav.addObject("F153", this.getCommonCode("F153"));
    	mav.addObject("F002", this.getCommonCode("F002"));
    	mav.addObject("F016", this.getCommonCode("F016"));
    	mav.addObject("F201", this.getCommonCode("F201"));
		return mav;
    }
}
