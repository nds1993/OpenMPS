package nds.mpm.api.openApi.service.impl;


import java.util.List;

import javax.annotation.Resource;

import nds.mpm.api.openApi.service.OpenapiService;
import nds.mpm.api.openApi.service.OpenapiVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


/**
 * <b>class : OpenapiServiceImpl </b>
 * <b>Class Description</b><br>
 * openMPS 경락가격 Class
 * <b>History</b><br>
 * <pre>      : 2017.06.14 초기작성(박정윤)</pre>
 * @author 박정윤
 * @version 1.0
 */
@SuppressWarnings("deprecation")
@Service("openapiService")
public class OpenapiServiceImpl extends AbstractServiceImpl implements OpenapiService {
    
	@Resource(name="openapiDAO")
    private OpenapiDAO openapiDAO;
	
	// VO 선언
	OpenapiVO openapiVO = new OpenapiVO();
	
    /**
     * openMPS 경락가격
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertOpenapi(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
                openapiDAO.insert(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }

    /**
     * 이력제 저장
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertTrace(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
                openapiDAO.insertTrace(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }

    /**
     * 등급판정결과 저장
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertConfirm(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
        		openapiDAO.insertConfirm(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }
    
    /**
     * 등급판정결과 상세 저장
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertConfirmDetail(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
        		openapiDAO.insertConfirmDetail(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }
	
    /**
     * 등급판정결과 확인서 발급번호 저장
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertConfirmNo(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
        		openapiDAO.insertConfirmNo(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }
    
    /**
     * 축산물 실시간 돼지도체 등급별경매현황정보
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertGrade(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
        		openapiDAO.insertGrade(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }
    
    /**
     * 축산물 실시간 돼지도체 등급별경매현황정보(상세)
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertGradeDetail(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
        		openapiDAO.insertGradeDetail(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }

    /**
     * 돼지 권역별 경락가격현황
     * @param OpenapiVO
     * @throws Exception
     */
    public void insertApperence(List<OpenapiVO> list) throws Exception {
        try{
        	for(int i=0; i <list.size(); i++){
        		openapiDAO.insertApperence(list.get(i));
        	}
        }catch(Exception ex){
        	openapiDAO.insertLog(openapiVO);
        }       
    }

    /**
     * 로그 insert
     * @param OpenapiVO
     * @throws Exception
     */
    
    public void insertLog(List<OpenapiVO> list) throws Exception {
        try{System.out.println("size1 : "+list.size());
        	openapiDAO.insertLog(list.get(0));
        	
        	System.out.println(list.get(0).getOffi_syst());
        	System.out.println(list.get(0).getIntr_usid());
        	System.out.println(list.get(0).getIntr_name());
        	System.out.println(list.get(0).getResultcode());
        	System.out.println(list.get(0).getResultmsg());
        	
        }catch(Exception ex){

        	System.out.println("size2 : "+list.size());
        	OpenapiVO openapiVO = new OpenapiVO();

            openapiVO.setOffi_syst(list.get(0).getOffi_syst());
            openapiVO.setIntr_usid(list.get(0).getIntr_usid());
            openapiVO.setIntr_name(list.get(0).getIntr_name());
            openapiVO.setResultcode("error");
        	openapiVO.setResultmsg(ex.toString());

        	openapiDAO.insertLog(openapiVO);
        }       
    }
}