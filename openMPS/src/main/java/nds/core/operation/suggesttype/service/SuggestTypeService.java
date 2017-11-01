package nds.core.operation.suggesttype.service;



import java.util.List;

import nds.core.common.common.service.Service;
import nds.frm.exception.MainException;


/**
 * <b>class : LoginService </b>
 * <b>Class Description</b><br>
 * 로그인 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface SuggestTypeService extends Service {
    
	/**
     * 제안유형코드 등록
     * @param suggestTypeVO
     * @throws Exception
     */
    void insertSuggestType(SuggestTypeVO suggestTypeVO) throws Exception;
    
    /**
     * 제안유형코드 수정
     * @param suggestTypeVO
     * @throws Exception
     */    
    void updateSuggestType(SuggestTypeVO suggestTypeVO) throws Exception;
    
    /**
     * 제안유형코드 삭제
     * @param suggestTypeVO
     * @throws Exception
     */    
    void deleteSuggestType(SuggestTypeVO suggestTypeVO) throws Exception;
    
    /**
     * 제안유형코드 총건수 조회
     * @param key
     * @return int
     * @throws MainException
     */    
    int selectCnslCodeCnt(SuggestTypeVO suggestTypeVO) throws Exception;
    
    /**
     * 제안유형코드 조회(Tree)
     * @param suggestTypeVO
     * @return List
     * @throws Exception
     */
    List<SuggestTypeVO> selectPropTypeList(SuggestTypeVO suggestTypeVO) throws Exception;
    
}
