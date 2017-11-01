package nds.core.operation.notice.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.common.common.web.CommonController;
import nds.core.operation.notice.service.NoticeService;
import nds.core.operation.notice.service.NoticeVO;
import nds.frm.config.StaticConfig;
import nds.frm.startup.SYSTEM;
import nds.frm.util.DateTimeUtil;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


/**
 * <p>Title: NoticeController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2013.12.26 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class NoticeController extends BaseController {
	
	/** AttachfileService */
    @Resource(name = "attachfileService")
    private AttachfileService attachfileService;
	/** NoticeService */
    @Resource(name = "noticeService")
    private NoticeService noticeService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService; 
    
    
    /**
     * 게시물목록조회
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/notice/oprat0100.do")
    public ModelAndView oprat0100(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
    	
    	UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();
    	
    	String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
    	String schTitCntn    = StringUtil.getParam(request, "schTitCntn", "");
        String schTit 		 = StringUtil.getParam(request, "schTit", "");
        String schInoutDvn 	 = StringUtil.getParam(request, "schInoutDvn", "");
        String schOpenYn 	 = StringUtil.getParam(request, "schOpenYn", "");
        String schStartDd    = StringUtil.getParam(request, "schStartDd", "").replace("-", "");    // 조건 시작일자
        String schEndDd      = StringUtil.getParam(request, "schEndDd", "").replace("-", "");      // 조건 종료일자
        
        ModelAndView mav = new ModelAndView("operation/notice/oprat0100");

        NoticeVO noticeVOkey = new NoticeVO();
        bind(request, noticeVOkey);

        
        //조건 일자가 없다면 1주일셋팅
        SYSTEM system   =   SYSTEM.getInstance();
        String today    =   StringUtil.getParam(request, "YYYYMMDD", system.getDate());
        
        if( schStartDd == "" && schEndDd == ""){
            schStartDd = DateTimeUtil.calculateDate(today,-30);
            schEndDd = today;
        }
        
        int page = Integer.parseInt(StringUtil.getParam(request, "page", "1"));  // 현재페이지
        int rows = Integer.parseInt(StringUtil.getParam(request, "rows", "20")); // 페이지당 몇개
        
        String sidx = StringUtil.getParam(request, "sidx", "");
        String sord = StringUtil.getParam(request, "sord", "");
        
        noticeVOkey.setStartNo(((page * rows) - rows) + 1);
        noticeVOkey.setEndNo(page * rows);
        
		noticeVOkey.setDbrdCd(StaticConfig.VOC_BOARD_NOTICE);
		noticeVOkey.setDocNo("");
		noticeVOkey.setSchTitCntn(schTitCntn);
		noticeVOkey.setTit(schTit);
		noticeVOkey.setSchStartDd(schStartDd);
		noticeVOkey.setSchEndDd(schEndDd);
		noticeVOkey.setInoutDvn(schInoutDvn);
		noticeVOkey.setOpenYn(schOpenYn);
		
		// 정렬
		noticeVOkey.setSidx(sidx);
		noticeVOkey.setSord(sord);
		
        if("S".equals(crudMode)) {

        	mav = new ModelAndView("jsonView");
        	reponse.setContentType("application/json; charset=utf-8");
    		reponse.setHeader("Cache-Control", "no-cache");
    		
    		int total = noticeService.selectDocuCnt(noticeVOkey);
    		List result = noticeService.selectDocuList(noticeVOkey);
    		
            mav.addObject("total", Math.ceil((double)total/rows));  // total page
            mav.addObject("records", total);                        // total count
            mav.addObject("rows", result);
        	
        }
        
        // 컬럼 설정 조회
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(userEmpno);
		myMenuColumnVO.setMenuId("oprat0100");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        mav.addObject("F010", this.getCommonCode("F010"));
        mav.addObject("F207", this.getCommonCode("F207"));
        mav.addObject("F208", this.getCommonCode("F208"));
        return mav;
    }

    /**
     * 게시물등록/수정
     * 
     * @param request
     * @param reponse
     */
    @RequestMapping("/operation/notice/oprat0120.do")
    public ModelAndView oprat0120(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();
        String openYn = StringUtil.getParam(request, "openYn", "");
        String attcFileYn = StringUtil.getParam(request, "attcFileYn1", "");
        
        ModelAndView mav = new ModelAndView("operation/notice/oprat0120");
        NoticeVO noticeVO = new NoticeVO();
        bind(request, noticeVO);

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        noticeVO.setDbrdCd(StaticConfig.VOC_BOARD_NOTICE);
        noticeVO.setRegUser(userEmpno);
        noticeVO.setUpdtUser(userEmpno);

        if(("V").equals(crudMode)) {
            mav = new ModelAndView("operation/notice/oprat0120");

            mav.addObject("result", noticeService.selectDbrdDocuInfo(noticeVO));

            List files = attachfileService.getAttachFileList(noticeVO.getDocNo(), noticeVO.getDbrdCd());
            mav.addObject("files", files);
        }else {

            String dbrdType = StringUtil.getParam(request, "dbrdType", "");

            // C, U, D 작업
            if(crudMode.equals("CR")) {
    	        mav = new ModelAndView("jsonView");
            	reponse.setContentType("application/json; charset=utf-8");
        		reponse.setHeader("Cache-Control", "no-cache");
        		
                noticeVO.setInqCnt("0");
                noticeVO.setDownCnt("0");
                noticeVO.setOpenYn(openYn);
                
                if(StringUtil.null2void(noticeVO.getUpDocNo()).equals(""))
                    noticeVO.setUpDocNo("00000000000000");

                String docNo = noticeService.insertDocu(noticeVO);

                // 첨부파일 추가
                if(attcFileYn.equals("Y")) {
                    this.updateAttachFile(attachfileService, noticeVO.getDbrdCd(), docNo, request.getParameter("texts1"), request.getParameter("values1"), StaticConfig.ATTACH_NOTI_DIR, "SFTP");
                    this.transFTPFiles(attachfileService, noticeVO.getDbrdCd(), docNo); //외부포털 업로드                	
                }
                
                mav.addObject("docNo", docNo);
                
            } else if(crudMode.equals("UR")) {
            	mav = new ModelAndView("jsonView");
             	reponse.setContentType("application/json; charset=utf-8");
         		reponse.setHeader("Cache-Control", "no-cache");
         		
                noticeVO.setInqCnt(StringUtil.null2String(noticeVO.getInqCnt(), "0"));
                noticeVO.setDownCnt(StringUtil.null2String(noticeVO.getDownCnt(), "0"));
                noticeVO.setOpenYn(openYn);
                noticeService.updateDocu(noticeVO);

                // 첨부파일 정보가 변경된 경우에만 갱신
                if(attcFileYn.equals("Y")) {
                    this.updateAttachFile(attachfileService, noticeVO.getDbrdCd(), noticeVO.getDocNo(), request.getParameter("texts1"), request.getParameter("values1"), StaticConfig.ATTACH_NOTI_DIR, "SFTP");
                    this.transFTPFiles(attachfileService, noticeVO.getDbrdCd(), noticeVO.getDocNo()); //외부포털 업로드
                }
                
                mav.addObject("docNo", noticeVO.getDocNo());
            } else if(crudMode.equals("DR")) {

            	String chkString = StringUtil.getParam(request, "chkItem", "");
            	
                // 전체삭제
                String[] chkItem = null;
                
                if(!"".equals(chkString)) {
                	chkItem = chkString.split(",");
                }
                
                // 개별삭제
                String[] dbrdCode = null;
                String[] docNo = null;

                if(chkItem != null && chkItem.length > 0) {
                    dbrdCode = new String[chkItem.length];
                    docNo = new String[chkItem.length];

                    for(int i = 0; i < chkItem.length; i++) {
                        String tmp = StringUtil.null2void(chkItem[i]);

                        String[] key = tmp.split("[:]");
                        dbrdCode[i] = key[0];
                        docNo[i] = key[1];
                    }
                }
                else {
                    dbrdCode = request.getParameterValues("dbrdCd");
                    docNo = request.getParameterValues("docNo");
                }

                if((chkItem != null && chkItem.length > 0) || (dbrdCode.length > 0 && docNo.length > 0)) {

                    for(int i = 0; i < dbrdCode.length; i++) {
                        NoticeVO dtblbd = new NoticeVO();
                        dtblbd.setDbrdCd(dbrdCode[i]);
                        dtblbd.setDocNo(docNo[i]);

                        ArrayList deleteList = (ArrayList) noticeService.selectDeleteDbrdDocuList(dtblbd);

                        for(int j = 0; j < deleteList.size(); j++) {
                            NoticeVO noticeVOd = (NoticeVO) deleteList.get(j);

                            noticeService.deleteDocu(noticeVOd);

                            // 첨부파일 삭제
                            this.deleteAttachFile(attachfileService, noticeVO.getDbrdCd(), dtblbd.getDocNo());
                        }
                    }
                }
            }
            // 상단 데이터 수정
    		NoticeVO searchVO = new NoticeVO();
    		searchVO.setDbrdCd(StaticConfig.VOC_BOARD_NOTICE);
    		searchVO.setSearchNum(StringUtil.getParam(request, "searchNum", "5"));
    		
    		CommonController.topNotiList = noticeService.selectTopNotiInfo(searchVO);
        }
        
        mav.addObject("F010", this.getCommonCode("F010"));
        mav.addObject("F207", this.getCommonCode("F207"));
        return mav;
    }

    /**
     * 게시물내용조회
     * 
     * @param request
     * @param reponse
     * @return
     * @throws Exception
     */
    @RequestMapping("/operation/notice/oprat0110.do")
    public ModelAndView oprat0110(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
        String crudMode = StringUtil.getParam(request, "crudMode", "").toUpperCase();

        ModelAndView mav = new ModelAndView("operation/notice/oprat0110");

        NoticeVO noticeVO = new NoticeVO();
        bind(request, noticeVO);

        // 로그인 사용자정보 가져오기
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userEmpno = userSession.getLogin().getUserEmpno();

        noticeVO.setDbrdCd(StaticConfig.VOC_BOARD_NOTICE);
        noticeVO.setRegUser(userEmpno);
        noticeVO.setUpdtUser(userEmpno);

        // 답글등록
        if(crudMode.equals("RC")) {
            NoticeVO noticeVOcmnt = new NoticeVO();
            noticeVOcmnt.setDbrdCd(noticeVO.getDbrdCd());
            noticeVOcmnt.setDocNo(noticeVO.getDocNo());
            noticeVOcmnt.setRegUser(userEmpno);
            noticeVOcmnt.setUpdtUser(userEmpno);
        }
        // 답글삭제
        else
            if(crudMode.equals("RD")) {

            }
            else {
                // 조회수 증가 시키기
                noticeService.updateDocuCnt(noticeVO);
            }

        NoticeVO noticeVOcmntkey = new NoticeVO();
        noticeVOcmntkey.setDbrdCd(noticeVO.getDbrdCd());
        noticeVOcmntkey.setDocNo(noticeVO.getDocNo());
        NoticeVO result = noticeService.selectDbrdDocuInfo(noticeVO);
        result.setCntn(result.getCntn().replaceAll("\r\n", "<br>"));
        
        mav.addObject("resultComment", noticeService.seleteDbrdDocuCommentList(noticeVOcmntkey));

        mav.addObject("result", result);

        List files = attachfileService.getAttachFileList(noticeVO.getDocNo(), noticeVO.getDbrdCd());
        mav.addObject("files", files);
        mav.addObject("F010", this.getCommonCode("F010"));
        mav.addObject("F207", this.getCommonCode("F207"));
        return mav;
    }
    
}
