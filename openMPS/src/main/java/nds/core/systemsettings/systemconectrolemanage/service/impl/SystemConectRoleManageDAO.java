package nds.core.systemsettings.systemconectrolemanage.service.impl;

import java.util.List;

import nds.core.systemsettings.systemconectrolemanage.service.SystemConectRoleManageVO;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("systemConectRoleManageDAO")
public class SystemConectRoleManageDAO extends EgovAbstractDAO {

	public SystemConectRoleManageDAO() {
		super();
	}
	
	/**
     * 사이트 권한 수정
     * @param systemConectRoleManageVO
     * @throws MainException
     */
	public void updateSiteRole(SystemConectRoleManageVO systemConectRoleManageVO){
		update("SystemConectRoleManage.updateSiteRole", systemConectRoleManageVO);
	}
	
	/**
     * 사이트 권한 수정
     * @param systemConectRoleManageVO
     * @throws MainException
     */
	public SystemConectRoleManageVO selectRoleDetail(SystemConectRoleManageVO systemConectRoleManageVO){
		return (SystemConectRoleManageVO) selectByPk("SystemConectRoleManage.selectRoleDetail", systemConectRoleManageVO);
	}
	
	/**
    * 사이트 목록
    * @param systemConectRoleManageVO
    * @throws MainException
    */
   @SuppressWarnings("unchecked")
   public List selectSiteList(SystemConectRoleManageVO systemConectRoleManageVO){
	   return list("SystemConectRoleManage.selectSiteList", systemConectRoleManageVO);
   }
   
   /**
    * 허가 사이트 목록
    * @param systemConectRoleManageVO
    * @throws MainException
    */
   @SuppressWarnings("unchecked")
   public List selectAssignList(SystemConectRoleManageVO systemConectRoleManageVO){
	   return list("SystemConectRoleManage.selectAssignList", systemConectRoleManageVO);
   }
}
