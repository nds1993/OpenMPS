package nds.core.operation.emailTmpl.web;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.AttachFileVO;
import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.MyMenuColumnVO;
import nds.core.common.common.service.UserSession;
import nds.core.common.common.web.BaseController;
import nds.core.operation.emailTmpl.service.EmailTmplService;
import nds.core.operation.emailTmpl.service.EmailTmplVO;
import nds.frm.config.StaticConfig;
import nds.frm.util.StringUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;


/**
 * <p>Title: EmailController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2014.08.14 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Controller
public class EmailTmplController extends BaseController {

	/** EmailService */
	@Resource(name = "emailTmplService")
	private EmailTmplService emailTmplService;
	/** AttachfileService */
	@Resource(name = "attachfileService")
	private AttachfileService attachfileService;
	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;


	@RequestMapping("/operation/emailtmpl/oprat0300.do")
	public ModelAndView oprat0300(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("/operation/emailtmpl/oprat0300");

		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String usrEmpno = userSession.getLogin().getUserEmpno();

		String crudMode = StringUtil.getParam(request, "crudMode","");

		String sidx = StringUtil.getParam(request, "sidx", "");
		String sord = StringUtil.getParam(request, "sord", "");


		if ("S".equals(crudMode)){                   // 조회데이터 
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");

			int pageNo = Integer.parseInt(StringUtil.getParam(request, "page", "1"));
			int pageSize = Integer.parseInt(StringUtil.getParam(request, "rows", "20"));

			EmailTmplVO emailTmplVO = new EmailTmplVO();
			emailTmplVO.setStartNo(((pageNo * pageSize) - pageSize) + 1);
			emailTmplVO.setEndNo(pageNo * pageSize);
			emailTmplVO.setTmplNm(StringUtil.getParam(request, "schTmplNm", ""));
			emailTmplVO.setUseYn(StringUtil.getParam(request, "schUseYn", ""));
			emailTmplVO.setChnlCat(StringUtil.getParam(request, "schChnlCat", ""));
			emailTmplVO.setProcStat(StringUtil.getParam(request, "schProcStat", ""));
			emailTmplVO.setInoutDvn(StringUtil.getParam(request, "schInOutDvn", ""));
			emailTmplVO.setSidx(sidx);
			emailTmplVO.setSord(sord);

			List<EmailTmplVO> result = null;
			result = emailTmplService.selectEmailTmplList(emailTmplVO);
			int totalCount = emailTmplService.selectEmailTmplListCount(emailTmplVO);

			mav.addObject("rows", result);
			mav.addObject("records", totalCount);
			mav.addObject("total", Math.ceil((double)totalCount/pageSize));

		}
		else if ("D".equals(crudMode)){    // 삭제처리

			EmailTmplVO vo = new EmailTmplVO();
			bind(request , vo);

			AttachFileVO attachFileVO = new AttachFileVO();
			attachFileVO.setContsId("TEMPLATE");
			attachFileVO.setDocRegNo(vo.getTmplNo());
			
			emailTmplService.deleteEmailTmpl(vo);
			attachfileService.deleteAttachFile(attachFileVO);

		}

		// 컬럼 설정 조회
        String cstNo = userSession.getLogin().getUserEmpno();
        
        MyMenuColumnVO myMenuColumnVO = new MyMenuColumnVO();
        
		myMenuColumnVO.setUserId(cstNo);
		myMenuColumnVO.setMenuId("oprat0300");
		
		MyMenuColumnVO colResult = commonService.selectMenuColumn(myMenuColumnVO);
        
        mav.addObject("colResult", colResult);
        
		mav.addObject("F153", this.getCommonCode("F153"));
		mav.addObject("F002", this.getCommonCode("F002"));
		mav.addObject("F016", this.getCommonCode("F016"));
		mav.addObject("F201", this.getCommonCode("F201"));
		return mav; 

	}

	@RequestMapping("/operation/emailtmpl/oprat0310.do")
	public ModelAndView oprat0310(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("operation/emailtmpl/oprat0310");

		String crudMode = StringUtil.getParam(request, "crudMode", "");
		String tmplNo = StringUtil.getParam(request, "tmplNo", "");

		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String usrEmpno = userSession.getLogin().getUserEmpno();

		EmailTmplVO emailTmplVO = new EmailTmplVO();
			
		if ("U".equals(crudMode)){    // 수정화면

			EmailTmplVO vo = new EmailTmplVO();
			bind(request , vo);

			emailTmplService.updateEmailTmpl(vo);

			return mav;
		}
		emailTmplVO.setTmplNo(tmplNo);

		EmailTmplVO result = emailTmplService.selectEmailTmplInfo(emailTmplVO);
		
		mav.addObject("result", result);
		return mav;
	}

	@RequestMapping("/operation/emailtmpl/oprat0320.do")
	public ModelAndView oprat0320(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("operation/emailtmpl/oprat0320");
		String crudMode = StringUtil.getParam(request, "crudMode", "");

		UserSession userSession = (UserSession) WebUtils.getRequiredSessionAttribute(request, "userSession");
		String usrEmpno = userSession.getLogin().getUserEmpno();
		
		String tmplNo = "";

		if ("C".equals(crudMode)){           // 등록화면
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			
			EmailTmplVO vo = new EmailTmplVO();
			bind(request , vo);

			vo.setRegUser(usrEmpno);
			vo.setUpdtUser(usrEmpno);

			tmplNo = emailTmplService.insertEmailTmpl(vo);

			mav.addObject("tmplNo", tmplNo);
		}
		return mav;
	}
	
	@RequestMapping("/operation/emailtmpl/oprat0321_ajax.do")
	public ModelAndView oprat0321_ajax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		EmailTmplVO vo = new EmailTmplVO();
		bind(request , vo);

        int intTotalCount = Integer.parseInt(emailTmplService.selectInsertTmpl(vo));

        mav.addObject("intTotalCount", intTotalCount);
        
		return mav;
	}

	@RequestMapping("/operation/emailtmpl/oprat0330_popup.do")
	public ModelAndView oprat0330(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("/operation/emailtmpl/oprat0330_popup");

		String tmplNo   = StringUtil.getParam(request, "tmplNo", "");
		String crudMode = StringUtil.getParam(request, "crudMode", "");

		if ("C".equals(crudMode)) {

			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			//System.out.println("imageUploadRealPath>> " + PropertiesUtil.getConfigValue("app.web.dir") + PropertiesUtil.getConfigValue("attach.tmpl.dir") + tmplNo + "/");

			List items = upload.parseRequest(request);

			Iterator iter = items.iterator();

			while (iter.hasNext()) 
			{
				FileItem item = (FileItem) iter.next();

				if (!item.isFormField())
				{               
					File photoDir = new File(StaticConfig.APP_ROOT_DIR + StaticConfig.IMAGE_ATTACH_DIR + StaticConfig.ATTACH_TMPL_FILE_DIR + tmplNo + "/");
					photoDir.mkdirs();

					String fileName = item.getName();

					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
					String newFileName = formatter.format(new Date()) + System.currentTimeMillis();

					//디렉토리 저장
					File uploadedPhoto = new File(photoDir +File.separator + fileName);
					item.write(uploadedPhoto);

					AttachFileVO attachFileVO = new AttachFileVO();
					attachFileVO.setContsId(StaticConfig.ATTACH_TMPL_CODE);
					attachFileVO.setDocRegNo(tmplNo);
					attachFileVO.setAtchFileChngName(newFileName);
					attachFileVO.setAtchDataPath(StaticConfig.ATTACH_TMPL_FILE_DIR + tmplNo + "/");
					attachFileVO.setAtchOtxtFileName(fileName);
					attachFileVO.setAtchFileSize(String.valueOf(uploadedPhoto.length()));
					attachFileVO.setSendErrMsg("");
					attachFileVO.setAtchFileCat("");
					attachFileVO.setAtchFileExt(fileName.substring(fileName.lastIndexOf(".")+1));

					attachfileService.insertAttachFile(attachFileVO);                 
				}
			}

		}
		else if ("D".equals(crudMode)) {
			String[] contsId = StringUtil.getParam(request, "contsIds", "").split("[,]");
			String[] atchFileChngName = StringUtil.getParam(request, "atchFileChngNames", "").split("[,]");
			String[] docRegNo = StringUtil.getParam(request, "docRegNos", "").split("[,]");
			String[] atchOtxtFileName = StringUtil.getParam(request, "atchOtxtFileNames", "").split("[,]");
			
			List<AttachFileVO>  delList = new ArrayList<AttachFileVO>();
			AttachFileVO     delKey  = null;
			if(contsId != null) {
				for(int i=0 ; i < contsId.length; i++) {
					delKey = new AttachFileVO();                    
					delKey.setContsId(contsId[i]);
					delKey.setAtchFileChngName(atchFileChngName[i]);
					delKey.setDocRegNo(docRegNo[i]);
					delList.add(delKey);
				}
				if(null != delList && delList.size() > 0) {
					// DB첨부파일정보 삭제
					attachfileService.deleteAttachFile(delList);
					
					// 물리적 파일 삭제
					File deleteFile = null;
					for(int i=0 ; i < contsId.length; i++) {
						deleteFile = new File(StaticConfig.APP_ROOT_DIR + StaticConfig.IMAGE_ATTACH_DIR + StaticConfig.ATTACH_TMPL_FILE_DIR+ docRegNo[i]+"/" + atchOtxtFileName[i]);
						deleteFile.delete();
					}
					mav.addObject("delete", "delete"); 
				}
			}
		}
		else if ("S".equals(crudMode)) {
			mav = new ModelAndView("jsonView");
			response.setContentType("application/json; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache"); 

			AttachFileVO attachFileVO = new AttachFileVO();
			attachFileVO.setContsId(StaticConfig.ATTACH_TMPL_CODE);
			attachFileVO.setDocRegNo(tmplNo);
			//mav.addObject("result", attachfileService.selectFileList(attachFileVO));

			List result        = attachfileService.selectFileList(attachFileVO);
			mav.addObject("rows", result);
		}

		return mav;
	}

	@RequestMapping("/operation/emailtmpl/oprat0320_frame.do")
	public ModelAndView bainf0820_frame(HttpServletRequest request, HttpServletResponse reponse) throws Exception {

		AttachFileVO attachFileVO = new AttachFileVO();
		bind(request, attachFileVO);

		attachFileVO.setContsId(StaticConfig.ATTACH_TMPL_CODE);
		attachFileVO.setDocRegNo(StringUtil.getParam(request, "tmplNo", ""));

		ModelAndView mav = new ModelAndView("operation/emailtmpl/oprat0320_frame");

		mav.addObject("result", attachfileService.selectFileList(attachFileVO));        

		return mav;
	} 
}
