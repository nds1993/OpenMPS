package nds.core.systemsettings.code.service;



import java.util.List;

import nds.core.common.common.service.Service;



/**
 * class : CodeService
 * class Description : 코드관리
 *
 * History
 * 1.0.0 (2007-10-12)
 * 
 * @author 
 */
public interface CodeService extends Service {

    /**
     * 공통코드 조회
     * @param codeVO
     * @return List
     * @throws Exception
     */
    List selectCode2(CodeVO key) throws Exception;
    /**
     * 공통코드 조회
     * @param codeVO
     * @return List
     * @throws Exception
     */
    List<CodeVO> selectCode(CodeVO codeVO) throws Exception;
    /**
     * 공통코드 건수조회
     * @param codeVO
     * @return List
     * @throws Exception
     */
    int selectCodeCount(CodeVO codeVO) throws Exception;
    
    /**
     * 공통코드 조회(Tree)
     * @param codeVO
     * @return List
     * @throws Exception
     */
    List<CodeVO> selectCodeTree(CodeVO codeVO) throws Exception;
    /**
	 * 코드 등록
	 * @param codeVO
	 * @throws Exception
	 */
    void insertCode(CodeVO codeVO) throws Exception;
	/**
	 * 코드 수정
	 * @param codeVO
	 * @throws Exception
	 */    
    void updateCode(CodeVO codeVO) throws Exception;

    /**
     * 코드(트리) 삭제
     * @param tbvcCommCdkey
     * @throws Exception
     */
    void deleteCodeTree(CodeVO codeVO) throws Exception;
    
    /**
     * 코드 총건수 조회
     * @param codeVO
     * @return int
     * @throws Exception
     */
    int selectCodeCnt(CodeVO codeVO) throws Exception;
    
}
