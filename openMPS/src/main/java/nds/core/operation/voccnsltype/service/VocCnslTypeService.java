package nds.core.operation.voccnsltype.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : LoginService </b>
 * <b>Class Description</b><br>
 * 로그인 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface VocCnslTypeService extends Service {
    
	/**
     * VOC유형코드 등록
     * @param vocCnslTypeVO
     * @throws Exception
     */    
    void insertCnslCode(VocCnslTypeVO vocCnslTypeVO) throws Exception;
    
    /**
     * VOC유형코드 수정
     * @param vocCnslTypeVO
     * @throws Exception
     */    
    void updateCnslTypeCode(VocCnslTypeVO vocCnslTypeVO) throws Exception;
    
    /**
     * VOC유형코드 삭제
     * @param vocCnslTypeVO
     * @throws Exception
     */    
    void deleteCnslTypeTree(VocCnslTypeVO vocCnslTypeVO) throws Exception;
    
    /**
     * VOC유형코드 총건수 조회
     * @param key
     * @return int
     * @throws MainException
     */    
    int selectCnslCodeCnt(VocCnslTypeVO vocCnslTypeVO) throws Exception;
    
    /**
     * VOC유형코드 조회(Tree)
     * @param vocCnslTypeVO
     * @return List
     * @throws Exception
     */
    List<VocCnslTypeVO> selectCnslTypeList(VocCnslTypeVO vocCnslTypeVO) throws Exception;
    
}
