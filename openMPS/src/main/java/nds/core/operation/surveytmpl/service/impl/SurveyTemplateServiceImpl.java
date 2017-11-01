package nds.core.operation.surveytmpl.service.impl;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.core.common.common.service.impl.CommonServiceImpl;
import nds.core.operation.surveytmpl.service.SrvyProbVO;
import nds.core.operation.surveytmpl.service.SrvyViewVO;
import nds.core.operation.surveytmpl.service.SurveyTemplateService;
import nds.core.operation.surveytmpl.service.SurveyTemplateVO;
import nds.core.operation.surveytmpl.service.SurveyVocVO;
import nds.frm.util.StringUtil;

import org.springframework.stereotype.Service;




/**
 * <b>class : SurveyTemplateServiceImpl </b>
 * <b>Class Description</b><br>
 * 설문템플릿 처리를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2015.01.05 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
@Service("surveyTemplateService")
public class SurveyTemplateServiceImpl extends CommonServiceImpl implements SurveyTemplateService {

	@Resource(name="surveyTemplateDAO")
    private SurveyTemplateDAO surveyTemplateDAO;
	
	@Resource(name="srvyProbDAO")
    private SrvyProbDAO srvyProbDAO;
	
	@Resource(name="srvyViewDAO")
    private SrvyViewDAO srvyViewDAO;
	
	
    /**
     * 템플릿 내용총건수 조회
     * @author 
     * @param TemplateVO
     * @return int
     * @throws Exception
     */    
    public int selectTmplCntnCount(SurveyTemplateVO surveyTemplateVO) throws Exception {
        int intTotalCount = 0;
        intTotalCount = surveyTemplateDAO.selectTmplCntnCount(surveyTemplateVO);

        return intTotalCount;
    }  
    
    /**
     * 템플릿내용 리스트 조회
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    public List<SurveyTemplateVO> selectTmplCntnList(SurveyTemplateVO surveyTemplateVO) throws Exception {
        List<SurveyTemplateVO> list = null;
        list = surveyTemplateDAO.selectTmplCntnList(surveyTemplateVO);
        return list;
    }
    
    /**
     * 템플릿 총건수 조회
     * @author 
     * @param TemplateVO
     * @return int
     * @throws Exception
     */    
    public int selectTmplCount(SurveyTemplateVO surveyTemplateVO) throws Exception {
    	int intTotalCount = 0;
		intTotalCount = surveyTemplateDAO.selectTmplCount(surveyTemplateVO);
        return intTotalCount;
    }  
    
    /**
     * 템플릿 리스트 조회
     * @author 
     * @param TemplateVO
     * @return List
     * @throws Exception
     */    
    public List<SurveyTemplateVO> selectTmplList(SurveyTemplateVO surveyTemplateVO) throws Exception {
    	List<SurveyTemplateVO> list = null;
    	list = surveyTemplateDAO.selectTmplList(surveyTemplateVO);

        return list;
    } 
    
    /**
     * 템플릿 리스트 cnt
     * @author 
     * @param TemplateVO
     * @return List
     * @throws Exception
     */    
    public int selectTmplListCount(SurveyTemplateVO surveyTemplateVO) throws Exception {
        int count = 0;
        count = surveyTemplateDAO.selectTmplListCount(surveyTemplateVO);
        return count;
    }
    
	/**
	 * 템플릿 등록
	 * @author 
	 * @param TemplateVO
	 * @throws Exception
	 */
    public String insertTmpl(SurveyTemplateVO surveyTemplateVO) throws Exception {
    	String tmplNo = "";
		tmplNo = (String) surveyTemplateDAO.insertTmpl(surveyTemplateVO);
		
		surveyTemplateVO.setTmplNo(tmplNo);
		return tmplNo;
    }
      
    /**
     * 템플릿 상세정보
     * @author 
     * @param TemplateVO
     * @return surveyTemplateVO
     * @throws Exception
     */    
    public SurveyTemplateVO selectTmplInfo(SurveyTemplateVO surveyTemplateVO) throws Exception {
    	surveyTemplateVO = surveyTemplateDAO.selectTmplInfo(surveyTemplateVO);
        return surveyTemplateVO;
    }
    
	/**
	 * 템플릿 수정
	 * @author 
	 * @param TemplateVO
	 * @throws Exception
	 */  
    public void updateTmpl(SurveyTemplateVO surveyTemplateVO) throws Exception {
		surveyTemplateDAO.updateTmpl(surveyTemplateVO);
    }
    
    /**
	 * 템플릿 VOC만족도 사용->미사용 수정
	 * @author 
	 * @param 
	 * @throws Exception
	 */  
    public void updateUseN() throws Exception {
		surveyTemplateDAO.updateUseN();
    }
    
    /**
     * 템플릿 삭제
     * @author 
     * @param TemplateVO
     * @throws Exception
     */  
    public void deleteTmpl(SurveyTemplateVO surveyTemplateVO) throws Exception {
        //보기삭제
        SrvyViewVO srvyViewVO = new SrvyViewVO();
        srvyViewVO.setTmplNo(surveyTemplateVO.getTmplNo());
        srvyViewDAO.deleteView(srvyViewVO);

        //질문삭제
        SrvyProbVO srvyProbVO = new SrvyProbVO();
        srvyProbVO.setTmplNo(surveyTemplateVO.getTmplNo());
        srvyProbDAO.deleteQues(srvyProbVO);


        //템플릿삭제
        surveyTemplateDAO.deleteTmpl(surveyTemplateVO);
    }  
    
    
    
    /**
     * 설문항목 PROB_NO 가져오기
     * @author 
     * @param QuestionsListVO
     * @return List
     * @throws Exception
     */
    public String selectProbNo(SrvyProbVO srvyProbVO) throws Exception{
        String probno = null;
        SrvyProbVO key = new SrvyProbVO();
        key.setTmplNo(srvyProbVO.getTmplNo());
        
        probno = srvyProbDAO.selectProbNo(key);
        return probno;
    }
    
    
    /**
     * 질문 리스트 조회
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    public List<SrvyProbVO> selectQuesList(SrvyProbVO srvyProbVO) throws Exception {
        List<SrvyProbVO> list = null;
        list = srvyProbDAO.selectQuesList(srvyProbVO);
        return list;
    }      
    
    /**
     * 문항 번호 조회 
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    public int selectOrdCount(SrvyProbVO srvyProbVO) throws Exception {
        int count = 0;
        count = srvyProbDAO.selectOrdCount(srvyProbVO);
        return count;
    }
    
    /**
     * 질문 리스트 조회
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    public SrvyProbVO selectQues(SrvyProbVO srvyProbVO) throws Exception {
        srvyProbVO = srvyProbDAO.selectQues(srvyProbVO);
        return srvyProbVO;
    }
    
    /**
     * 질문 리스트 조회
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    public List<SrvyProbVO> selectByConnect(SrvyProbVO key) throws Exception {
        List<SrvyProbVO> list = null;
        list = srvyProbDAO.selectByConnect(key);
        return list;
    }
    
    /**
     * 보기 리스트 조회
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    public List<SrvyViewVO> selectViewList(SrvyViewVO srvyViewVO) throws Exception {
        List<SrvyViewVO> list = null;
        list = srvyViewDAO.selectViewList(srvyViewVO);
        return list;
    }       
    
    /**
     * 보기 리스트 max level 조회
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    public int selectViewListMaxLevel(SrvyViewVO srvyViewVO) throws Exception {
        int count = 0;
        count = srvyViewDAO.selectViewListMaxLevel(srvyViewVO);
        return count;
    }
    
    /**
     * 질문 등록
     * @author 
     * @param ArrayList
     * @throws Exception
     */
    public String insertQues(List<SrvyProbVO> paramList) throws Exception {
            SurveyTemplateVO mstKey = new SurveyTemplateVO();
            boolean isModifyData = false;

            for (int i =0; i < paramList.size(); i++)
            {
                SrvyProbVO t8110 = (SrvyProbVO)paramList.get(i);
                                
                if (t8110.getCrudGbn().equals("C")) {
                	srvyProbDAO.insertQues(t8110);
                	mstKey.setTmplNo(t8110.getTmplNo());
                	mstKey.setUpdtUser(t8110.getUpdtUser());
                	isModifyData = true;
                }
                else if (t8110.getCrudGbn().equals("U")) {
                	srvyProbDAO.updateQues(t8110);       
                	mstKey.setTmplNo(t8110.getTmplNo());
                	mstKey.setUpdtUser(t8110.getUpdtUser());
                	isModifyData = true;
                }
                else if (t8110.getCrudGbn().equals("D")) {
                    //보기삭제
                    
                    SrvyViewVO t8111 = new SrvyViewVO();
                    t8111.setTmplNo(t8110.getTmplNo());
                    t8111.setProbNo(t8110.getProbNo());                    
                    srvyViewDAO.deleteView(t8111);
                    //질문삭제
                    srvyProbDAO.deleteQues(t8110);
                }
            }
            
            if(isModifyData) {
            	surveyTemplateDAO.updateTmpl(mstKey);
            }
            
            return "";
    }    
    
    /**
     * 보기 등록
     * @author 
     * @param ArrayList
     * @throws Exception
     */
    public String insertView(List<SrvyViewVO> paramList) throws Exception {
        SurveyTemplateVO mstKey = new SurveyTemplateVO();
        boolean isModifyData = false;
        
        for (int i =0; i < paramList.size(); i++)
        {
            SrvyViewVO t8111 = (SrvyViewVO)paramList.get(i);
                            
            if (t8111.getCrudGbn().equals("C")) {
            	srvyViewDAO.insertView(t8111);
            	mstKey.setTmplNo(t8111.getTmplNo());
            	mstKey.setUpdtUser(t8111.getUpdtUser());
            	isModifyData = true;
            }
            else if (t8111.getCrudGbn().equals("U")) {
            	srvyViewDAO.updateView(t8111);          
            	mstKey.setTmplNo(t8111.getTmplNo());
            	mstKey.setUpdtUser(t8111.getUpdtUser());
            	isModifyData = true;
            }
            else if (t8111.getCrudGbn().equals("D")) {
            	srvyViewDAO.deleteView(t8111);
            }
        }
        if(isModifyData) {
        	surveyTemplateDAO.updateTmpl(mstKey);
        }
        
        return "";
    }

    /**
     * 설문항목삭제
     * @author 
     * @param srvyProbVO
     * @return List
     * @throws Exception
     */    
    public void delete(SrvyProbVO srvyProbVO, SrvyViewVO srvyViewVO) throws Exception {
        srvyViewDAO.deleteView(srvyViewVO);
        srvyProbDAO.deleteQues(srvyProbVO);
    }
    
    /**
     * 보기 리스트 삭제
     * @author 
     * @param SurveyResultVO
     * @return List
     * @throws Exception
     */    
    public void deleteView(SrvyViewVO srvyViewVO) throws Exception {
        srvyViewDAO.deleteView(srvyViewVO);
    }
    
    /**
     * 기존설문항목 복사
     * @author 
     * @param SurveyResultVO
     * @return List
     * @throws Exception
     */    
    public String insertQuesCopy(SrvyProbVO srvyProbVO) throws Exception {
    	String probNo = "";
    	probNo = (String)srvyProbDAO.insertQuesCopy(srvyProbVO);
        return probNo;
    }
    
    /**
     * 기존설문보기 복사
     * @author 
     * @param SurveyResultVO
     * @return List
     * @throws Exception
     */    
    public void insertViewsCopy(SrvyViewVO srvyViewVO) throws Exception {
        srvyViewDAO.insertViewsCopy(srvyViewVO);
    }
    

    /**
     * 설문조사 조회
     * @param 
     * @throws Exception
     */
    public List<List<?>> srvyTmpl(String vocId, String tmplNo, String cstNo) throws Exception {
    	
        // 질문
    	SrvyProbVO srvyProbVO = new SrvyProbVO();
        srvyProbVO.setTmplNo(tmplNo);
        srvyProbVO.setLineNo("0");
        
        // 보기      
        SrvyViewVO srvyViewVO = new SrvyViewVO();
        srvyViewVO.setVocId(vocId);
        srvyViewVO.setTmplNo(tmplNo);
        srvyViewVO.setCstNo(cstNo);
        
        List<SrvyProbVO> quesList = srvyProbDAO.selectQuesList(srvyProbVO);
        
        srvyProbVO.setLineNo("");
        srvyProbVO.setOrderBy("Y");
        List<SrvyProbVO> quesSubList = srvyProbDAO.selectQuesList(srvyProbVO);
        
        List<SrvyViewVO> viewList = srvyViewDAO.selectVocViewList(srvyViewVO);
        
        List<List<?>> rtnList = new ArrayList<List<?>>();
        rtnList.add(quesList);
        rtnList.add(viewList);
        rtnList.add(quesSubList);
        
        return rtnList;
    }
    
    /**
	 * VOC 발송 리스트 조회
	 * @param surveyVocVO
	 * @return List
	 * @throws Exception
	 */
    public List<SurveyVocVO> selectVocSatisList(SurveyVocVO surveyVocVO) throws Exception {
    	List<SurveyVocVO> list = null;
        list = surveyTemplateDAO.selectVocSatisList(surveyVocVO);
        return list;
	}
    
	
	/**
     * VOC 발송 리스트 cnt
     * @param SurveyVocVO surveyVocVO
     * @return List
     * @throws Exception
     */
    public int selectVocSatisListCount(SurveyVocVO surveyVocVO) throws Exception {
    	int intTotalCount = 0;
        intTotalCount = surveyTemplateDAO.selectVocSatisListCount(surveyVocVO);

        return intTotalCount;
	}
    
	/**
     * 만족도 조사 템플릿 사용중인것 조회
     * @param SurveyTemplateVO
     * @return String
     * @throws Exception
     */
    public SurveyTemplateVO selectByTmplUseY(SurveyTemplateVO surveyTemplateVO) throws Exception {
    	surveyTemplateVO = surveyTemplateDAO.selectByTmplUseY(surveyTemplateVO);
        return surveyTemplateVO;
	}
    
    /**
     * 설문 고정 문항 생성
     * @author 
     * @param surveyTemplateVO
     * @throws Exception
     */
    public void callSpSrvyInit(SurveyTemplateVO surveyTemplateVO) throws Exception {
        surveyTemplateDAO.callSpSrvyInit(surveyTemplateVO);
	}
    
    /**
     * 설문조사 결과저장
     * @author 
     * @param srvyViewVO, answ, answ4, descRspn
     * @throws Exception
     */
    public void insertSrvyResult(SrvyViewVO srvyViewVO, String[] answ, String[] answ4, String[] descRspn) throws Exception {
    	if(answ != null) {
    		for(int i=0; i < answ.length; i++) {
    			String[] tmp = answ[i].split("[;]");
    			String quesNo = tmp[0];
    			String viewNo = tmp[1];
    			String lineNo = tmp[2];
    			
    			SrvyViewVO srvyViewTemp = new SrvyViewVO();
    			srvyViewTemp.setVocId(srvyViewVO.getVocId());
    			srvyViewTemp.setCstNo(srvyViewVO.getCstNo());
    			srvyViewTemp.setTmplNo(srvyViewVO.getTmplNo());
    			srvyViewTemp.setProbNo(quesNo);
    			srvyViewTemp.setViewNo(viewNo);
    			srvyViewTemp.setLineNo(lineNo);
    			srvyViewTemp.setRegUser(srvyViewVO.getCstNo());
    			srvyViewTemp.setUpdtUser(srvyViewVO.getCstNo());
    			
    			surveyTemplateDAO.insertSrvyResult(srvyViewTemp);
    		}
    	}
    	if(answ4 != null) {
    		for(int i=0; i < answ4.length; i++) {
    			String[] tmp = answ4[i].split("[;]");
    			String quesNo = tmp[0];
    			String viewNo = tmp[1];
    			String lineNo = tmp[2];
    			
    			SrvyViewVO srvyViewTemp = new SrvyViewVO();
    			srvyViewTemp.setVocId(srvyViewVO.getVocId());
    			srvyViewTemp.setCstNo(srvyViewVO.getCstNo());
    			srvyViewTemp.setTmplNo(srvyViewVO.getTmplNo());
    			srvyViewTemp.setProbNo(quesNo);
    			srvyViewTemp.setViewNo(viewNo);
    			srvyViewTemp.setLineNo(lineNo);
    			srvyViewTemp.setDescRspn(StringUtil.null2void(descRspn[i]));
    			srvyViewTemp.setRegUser(srvyViewVO.getCstNo());
    			srvyViewTemp.setUpdtUser(srvyViewVO.getCstNo());
    			
    			surveyTemplateDAO.insertSrvyResult(srvyViewTemp);
    		}
    	}
    	
    	surveyTemplateDAO.updateNeedsMstSatiScr(srvyViewVO);
    }
}