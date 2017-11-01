package nds.mpm.common.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : TMCOCD10DAO.java
 * @Description : TMCOCD10 DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.17
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

public class TMMPPBaseService extends EgovAbstractServiceImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TMMPPBaseService.class);

	public String resolveSqlId(String dsType, String menuId) throws Exception
	{
		String genId = null;
		
		if(StringUtils.isEmpty(dsType))
			throw new Exception(" dsType empty!!");
		
		if("C".equals(dsType))
			genId = menuId+ "DAO.insert" + menuId ;
		else if("U".equals(dsType))
			genId = menuId+ "DAO.update" + menuId ;
		else if("D".equals(dsType))
			genId = menuId+ "DAO.delete" + menuId ;
	
		return genId;
	}
}
