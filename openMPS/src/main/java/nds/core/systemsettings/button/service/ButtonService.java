package nds.core.systemsettings.button.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : ButtonService </b>
 * <b>Class Description</b><br>
 * 버튼관리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface ButtonService extends Service {
    
    /**
     * 버튼코드 등록
     * @param btnVO
     * @throws Exception
     */
    void insertButton(BtnVO btnVO) throws Exception;
    /**
     * 버튼코드 수정
     * @param btnVO
     * @throws Exception
     */
    void updateButton(BtnVO btnVO) throws Exception;
    /**
     * 버튼코드 삭제
     * @param btnVO
     * @throws Exception
     */
    void deleteButton(BtnVO btnVO) throws Exception;
    
    /**
     * 버튼코드 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    List selectButton(BtnVO btnVO) throws Exception;
    /**
     * 버튼코드 건수조회
     * @param btnVO
     * @return int
     * @throws Exception
     */
    int selectButtonCount(BtnVO btnVO) throws Exception;
    
    /**
     * 버튼코드 중복건수
     * @param btnVO
     * @return int
     * @throws Exception
     */
    int selectButtonIdCount(BtnVO btnVO) throws Exception;

    
}
