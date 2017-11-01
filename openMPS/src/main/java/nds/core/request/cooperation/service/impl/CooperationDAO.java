package nds.core.request.cooperation.service.impl;

import java.util.List;

import nds.core.operation.approval.service.ApprovalVO;
import nds.core.request.cooperation.service.CooperationVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;




@Repository("cooperationDAO")
public class CooperationDAO extends EgovAbstractDAO {

	public CooperationDAO(){
		super();
	}
	
    @SuppressWarnings("unchecked")
    public List selectCooList(CooperationVO cooperationVO) {
    	return list("Cooperation.selectCooList", cooperationVO);
    }
    
    public int selectCooListCount(CooperationVO cooperationVO) {
    	 return (Integer) selectByPk("Cooperation.selectCooListCount", cooperationVO);
    }

	public CooperationVO selectReqVoc(CooperationVO cooperationVO) {
	     return (CooperationVO) selectByPk("Cooperation.selectReqVoc", cooperationVO);
	}

	public CooperationVO selectReq(CooperationVO cooperationVO) {
		return (CooperationVO) selectByPk("Cooperation.selectReq", cooperationVO);
	}

	public void updateTemVoc(CooperationVO cooperationVO) {
		update("Cooperation.updateTemVoc", cooperationVO);
	}

	public void updateReqVoc(CooperationVO cooperationVO) {
		update("Cooperation.updateReqVoc", cooperationVO);
	}
    
	public void deleteReqId(String reqId) {
		CooperationVO cooperationVO = new CooperationVO();
		cooperationVO.setReqId(reqId);
		
		delete("Cooperation.deleteReqId", cooperationVO);
	}
    
}
