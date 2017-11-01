package nds.core.operation.voccnsltype.service;



import java.util.List;

import nds.core.common.common.service.Service;


/**
 * <b>class : ScrCdService </b>
 * <b>Class Description</b><br>
 * 상세화면 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2015.09.30 초기작성(윤령희)</pre>
 * @author <a href="mailto:rhyun@nds.co.kr">윤령희</a>
 * @version 1.0
 */
public interface ScrCdService extends Service {
    
	/**
     * 화면코드 등록
     * @param scrCdVO
     * @throws Exception
     */    
    void insertScrCd(ScrCdVO scrCdVO) throws Exception;
    
    /**
     * 화면코드 수정
     * @param scrCdVO
     * @throws Exception
     */    
    void updateScrCdCode(ScrCdVO scrCdVO) throws Exception;
    
    /**
     * 화면코드 삭제
     * @param scrCdVO
     * @throws Exception
     */    
    void deleteScrCdTree(ScrCdVO scrCdVO) throws Exception;
    
    /**
     * 화면코드 총건수 조회
     * @param key
     * @return int
     * @throws MainException
     */    
    int selectScrCdCnt(ScrCdVO scrCdVO) throws Exception;
    
    /**
     * 화면코드 조회(Tree)
     * @param scrCdVO
     * @return List
     * @throws Exception
     */
    List<ScrCdVO> selectScrCdList(ScrCdVO scrCdVO) throws Exception;
    
}
