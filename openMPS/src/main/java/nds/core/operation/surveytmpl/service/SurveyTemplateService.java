package nds.core.operation.surveytmpl.service;


import java.util.List;




/**
 * <b>class : SurveyTemplateService </b>
 * <b>Class Description</b><br>
 * 설문템플릿 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.01.05 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface SurveyTemplateService {

    /**
     * 템플릿 내용총건수 조회
     * @author 
     * @param TemplateVO
     * @return int
     * @throws Exception
     */    
    int selectTmplCntnCount(SurveyTemplateVO surveyTemplateVO) throws Exception;
    
    /**
     * 템플릿내용 리스트 조회
     * @author 
     * @param helper
     * @return List
     * @throws Exception
     */    
    List<SurveyTemplateVO> selectTmplCntnList(SurveyTemplateVO surveyTemplateVO) throws Exception;
    
	/**
	 * 템플릿 총건수 조회
	 * @author 
	 * @param TemplateVO
	 * @return int
	 * @throws Exception
	 */
	int selectTmplCount(SurveyTemplateVO surveyTemplateVO) throws Exception;

	/**
	 * 템플릿 리스트 조회
	 * @author 
	 * @param TemplateVO
	 * @return List
	 * @throws Exception
	 */
	List<SurveyTemplateVO> selectTmplList(SurveyTemplateVO surveyTemplateVO) throws Exception;
	
	/**
     * 템플릿 리스트 cnt
     * @author 
     * @param TemplateVO
     * @return List
     * @throws Exception
     */
    int selectTmplListCount(SurveyTemplateVO surveyTemplateVO) throws Exception;

	/**
	 * 템플릿 등록
	 * @author 
	 * @param TemplateVO
	 * @throws Exception
	 */
	String insertTmpl(SurveyTemplateVO surveyTemplateVO) throws Exception;

	/**
	 * 템플릿 상세정보
	 * @author 
	 * @param TemplateVO
	 * @return surveyTemplateVO
	 * @throws Exception
	 */
	SurveyTemplateVO selectTmplInfo(SurveyTemplateVO surveyTemplateVO) throws Exception;

	/**
	 * 템플릿 수정
	 * @author 
	 * @param TemplateVO
	 * @throws Exception
	 */
	void updateTmpl(SurveyTemplateVO surveyTemplateVO) throws Exception;

    /**
	 * 템플릿 VOC만족도 사용->미사용 수정
	 * @author 
	 * @param 
	 * @throws Exception
	 */  
    void updateUseN() throws Exception;
    
	/**
	 * 템플릿 삭제
	 * @author 
	 * @param TemplateVO
	 * @throws Exception
	 */
	void deleteTmpl(SurveyTemplateVO surveyTemplateVO) throws Exception;
	
	/**
     * 설문항목 PROB_NO 가져오기
     * @author 
     * @param QuestionsListVO
     * @return List
     * @throws Exception
     */
    String selectProbNo(SrvyProbVO srvyProbVO) throws Exception;
    
	/**
	 * 질문 리스트 조회
	 * @author 
	 * @param QuestionsListVO
	 * @return List
	 * @throws Exception
	 */
	List<SrvyProbVO> selectQuesList(SrvyProbVO srvyProbVO) throws Exception;
	
	/**
     * 질문 리스트 조회
     * @author 
     * @param QuestionsListVO
     * @return List
     * @throws Exception
     */
    List<SrvyProbVO> selectByConnect(SrvyProbVO key) throws Exception;
	
	/**
     * 질문 리스트 조회
     * @author 
     * @param QuestionsListVO
     * @return List
     * @throws Exception
     */
    SrvyProbVO selectQues(SrvyProbVO srvyProbVO) throws Exception;
    
    /**
     * 문항번호조회
     * @author 
     * @param QuestionsListVO
     * @return List
     * @throws Exception
     */
    int selectOrdCount(SrvyProbVO srvyProbVO) throws Exception;
    
	/**
	 * 보기 리스트 조회
	 * @author 
	 * @param srvyViewVO
	 * @return List
	 * @throws Exception
	 */
	List<SrvyViewVO> selectViewList(SrvyViewVO srvyViewVO) throws Exception;
	
	/**
     * 보기 리스트 max level 조회
     * @author 
     * @param srvyViewVO
     * @return int
     * @throws Exception
     */
    int selectViewListMaxLevel(SrvyViewVO srvyViewVO) throws Exception;

	/**
	 * 질문 등록
	 * @author 
	 * @param ArrayList
	 * @throws Exception
	 */
	String insertQues(List<SrvyProbVO> paramList) throws Exception;

	/**
	 * 보기 등록
	 * @author 
	 * @param ArrayList
	 * @throws Exception
	 */
	String insertView(List<SrvyViewVO> paramList) throws Exception;
	
	/**
     * 설문항목삭제
     * @author 
     * @param srvyProbVO, srvyViewVO
     * @return List
     * @throws Exception
     */    
    void delete(SrvyProbVO srvyProbVO, SrvyViewVO srvyViewVO) throws Exception ;
        
	/**
     * 보기 삭제
     * @author 
     * @param SurveyResultVO
     * @throws Exception
     */
	void deleteView(SrvyViewVO srvyViewVO) throws Exception;
	
	/**
     * 기존설문항목 복사
     * @author 
     * @param QuestionsListVO
     * @throws Exception
     */
    String insertQuesCopy(SrvyProbVO srvyProbVO) throws Exception;
    
    /**
     * 기존설문보기 복사
     * @author 
     * @param SurveyResultVO
     * @throws Exception
     */
    void insertViewsCopy(SrvyViewVO srvyViewVO) throws Exception;
    

    /**
     * 설문조사 조회
     * @author 
     * @param vocId, tmplNo, cstNo
     * @throws Exception
     */
    List<List<?>> srvyTmpl(String vocId, String tmplNo, String cstNo) throws Exception;
    
    /**
	 * VOC 발송 리스트 조회
	 * @param surveyVocVO
	 * @return List
	 * @throws Exception
	 */
	List<SurveyVocVO> selectVocSatisList(SurveyVocVO surveyVocVO) throws Exception;
	
	/**
     * VOC 발송 리스트 cnt
     * @param SurveyVocVO surveyVocVO
     * @return List
     * @throws Exception
     */
    int selectVocSatisListCount(SurveyVocVO surveyVocVO) throws Exception;

    /**
     * 만족도 조사 템플릿 사용중인것 조회
     * @param SurveyVocVO surveyVocVO
     * @return String
     * @throws Exception
     */
    SurveyTemplateVO selectByTmplUseY(SurveyTemplateVO surveyTemplateVO) throws Exception;

    /**
     * 설문 고정 문항 생성
     * @author 
     * @param surveyTemplateVO
     * @throws Exception
     */
    void callSpSrvyInit(SurveyTemplateVO surveyTemplateVO) throws Exception;
    

    /**
     * 설문조사 결과저장
     * @author 
     * @param srvyViewVO, answ, answ4, descRspn
     * @throws Exception
     */
    void insertSrvyResult(SrvyViewVO srvyViewVO, String[] answ, String[] answ4, String[] descRspn) throws Exception;
    
}
