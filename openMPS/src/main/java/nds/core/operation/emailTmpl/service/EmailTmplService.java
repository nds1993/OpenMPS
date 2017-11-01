package nds.core.operation.emailTmpl.service;

import java.util.List;

/**
 * <b>class : EmailTmplService </b>
 * <b>Class Description</b><br>
 * <b>History</b><br>
 * <pre>      : 2014.08.14 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface EmailTmplService {    

    /**
     * 템플릿 조회
     * @param emailTmplVO
     * @return List
     * @throws Exception
     */
    List<EmailTmplVO> selectEmailTmplList(EmailTmplVO emailTmplVO) throws Exception;

    /**
     * 템플릿 전체 갯수 조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    int selectEmailTmplListCount(EmailTmplVO emailTmplVO) throws Exception;
    
    
    /**
     * 템플릿 상세조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    EmailTmplVO selectEmailTmplInfo(EmailTmplVO emailTmplVO) throws Exception;
    
    
    /**
     * 템플릿 상세조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    EmailTmplVO selectEmailTmplInfoByMessage(EmailTmplVO emailTmplVO) throws Exception;
    
    /**
     * 템플릿 생성
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    String insertEmailTmpl(EmailTmplVO emailTmplVO) throws Exception;
    
    /**
     * 템플릿 수정
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    void updateEmailTmpl(EmailTmplVO emailTmplVO) throws Exception;
    
    /**
     * 템플릿 삭제
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    void deleteEmailTmpl(EmailTmplVO emailTmplVO) throws Exception;

    /**
     * 템플릿 신규 tmplNo 조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    String selectEmailTmplNo() throws Exception;
    
    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param smsVO
     * @return
     * @throws Exception
     */
    public String selectInsertTmpl(EmailTmplVO emailTmplVO) throws Exception;
    
    /**
     * VOC처리완료 단건발송 템플릿 상세조회
     * @param emailTmplVO
     * @return
     * @throws Exception
     */
    EmailTmplVO selectTmplInfoVoc(EmailTmplVO emailTmplVO) throws Exception;
    
}
