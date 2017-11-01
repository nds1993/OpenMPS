package nds.mpm.api.openApi.service;

import java.util.List;

import nds.core.common.common.service.Service;

/**
 * <b>class : OpenapiService </b>
 * <b>Class Description</b><br>
 * openMPS 경락가격 Interface
 * <b>History</b><br>
 * <pre>      : 2017.06.14 초기작성(박정윤)</pre>
 * @author 박정윤</a>
 * @version 1.0
 */
public interface OpenapiService extends Service {
    
    /**
     * openMPS 경락가격 등록
     * @param OpenapiVO
     * @throws Exception
     */
    void insertOpenapi(List<OpenapiVO> list) throws Exception;

    /**
     * 이력제 등록
     * @param OpenapiVO
     * @throws Exception
     */
    void insertTrace(List<OpenapiVO> list) throws Exception;

    /**
     * 등급판정결과 등록
     * @param OpenapiVO
     * @throws Exception
     */
    void insertConfirm(List<OpenapiVO> list) throws Exception;

    /**
     * 등급판정결과 발급번호 등록
     * @param OpenapiVO
     * @throws Exception
     */
    void insertConfirmNo(List<OpenapiVO> list) throws Exception;
    
    /**
     * 등급판정결과 상세 등록
     * @param OpenapiVO
     * @throws Exception
     */
    void insertConfirmDetail(List<OpenapiVO> list) throws Exception;

    /**
     * 축산물 실시간 돼지도체 등급별경매현황정보
     * @param OpenapiVO
     * @throws Exception
     */
    void insertGrade(List<OpenapiVO> list) throws Exception;

    /**
     * 축산물 실시간 돼지도체 등급별경매현황정보(상세)
     * @param OpenapiVO
     * @throws Exception
     */
    void insertGradeDetail(List<OpenapiVO> list) throws Exception;
    
    /**
     * 돼지 권역별 경락가격현황
     * @param OpenapiVO
     * @throws Exception
     */
    void insertApperence(List<OpenapiVO> list) throws Exception;

    /**
     * 로그 insert
     * @param OpenapiVO
     * @throws Exception
     */
    void insertLog(List<OpenapiVO> list) throws Exception;
}
