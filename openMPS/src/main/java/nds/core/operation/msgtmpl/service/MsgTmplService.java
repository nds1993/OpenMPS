package nds.core.operation.msgtmpl.service;

import java.util.List;

import nds.core.operation.emailTmpl.service.EmailTmplVO;

public interface MsgTmplService {
	
	/**
	 * Msg 템플릿 조회
	 * @param msgTmplVO
	 * @return
	 * @throws Exception
	 */
	List<MsgTmplVO> selectMsgTmplList(MsgTmplVO msgTmplVO) throws Exception;
	
	/**
     * 템플릿 전체 갯수 조회
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    int selectMsgTmplListCount(MsgTmplVO msgTmplVO) throws Exception;
    
    
    /**
     * 템플릿 상세조회
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    MsgTmplVO selectMsgTmplInfo(MsgTmplVO msgTmplVO) throws Exception;
    
    /**
     * 템플릿 생성
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    String insertMsgTmpl(MsgTmplVO msgTmplVO) throws Exception;
    
    /**
     * 템플릿 수정
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    void updateMsgTmpl(MsgTmplVO msgTmplVO) throws Exception;
    
    /**
     * 템플릿 삭제
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    void deleteMsgTmpl(MsgTmplVO msgTmplVO) throws Exception;

    /**
     * 템플릿 신규등록 전 등록가능여부 조회
     * @param msgTmplVO
     * @return
     * @throws Exception
     */
    public String checkInsertYn(MsgTmplVO msgTmplVO) throws Exception;
}
