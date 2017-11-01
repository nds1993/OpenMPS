package nds.mpm.common.service;

import java.sql.SQLException;
import java.util.List;

import nds.mpm.common.vo.MultiDSTypeVO;
import nds.tmm.common.TMCOCD10.service.impl.TMCOCD10ServiceImpl;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10DefaultVO;
import nds.tmm.common.TMCOCD10.vo.TMCOCD10VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

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

public class TMMPPBaseDAO extends EgovAbstractDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TMMPPBaseDAO.class);

	/**
	 * 리스트타입으로 전달된 데이터들을 일괄처리한다.
	 * 한번에 요청된 모든 데이터의 저장이 한 트랜잭션으로 관리.
	 * 데이터들의 일관성을 보장한다.
	 * 
	 * */
    public int executeBatch(List<Object> vos, String sqlId) throws Exception{
    	
    	int upCnt = 0;
    	
    	try{
    		
    		this.getSqlMapClient().startTransaction();
    		
    		this.getSqlMapClient().startBatch();
    		
    		for(Object vo : vos)
    		{
    			if(sqlId.contains("insert"))
    				this.getSqlMapClient().insert(sqlId, vo);
    			else if(sqlId.contains("update"))
    				this.getSqlMapClient().update(sqlId, vo);
    			else if(sqlId.contains("delete"))
    				this.getSqlMapClient().delete(sqlId, vo);
    		}
    		
    		upCnt = this.getSqlMapClient().executeBatch();
    		this.getSqlMapClient().commitTransaction();
    	}
    	catch(SQLException se)
    	{
    		se.printStackTrace();
    		
    		LOGGER.error("TMMPPBaseDAO.executeBatch ERROR :: " + se.getErrorCode());
    		LOGGER.error("TMMPPBaseDAO.executeBatch msg :: " + se.getMessage());
    		
    		throw se;
    	}
    	finally
    	{
    		this.getSqlMapClient().endTransaction();
    	}
    	
    	LOGGER.error("TMMPPBaseDAO.executeBatch RESULT upCnt :: " + upCnt);
    	
    	return upCnt;
    }
    
    /**
	 * 리스트타입으로 전달된 데이터들을 일괄처리한다.
	 * 리스트에 insert, delete, update 가 섞여있을때 
	 * 
	 * */
    public int executeBatchMulti(List<MultiDSTypeVO> vos) throws Exception{
    	
    	int upCnt = 0;
    	
    	try{
    		
    		this.getSqlMapClient().startTransaction();
    		
    		this.getSqlMapClient().startBatch();
    		
    		for(MultiDSTypeVO vo : vos)
    		{
    			if(vo.getSqlId().contains("insert"))
    				this.getSqlMapClient().insert(vo.getSqlId(), vo.getRowData());
    			else if(vo.getSqlId().contains("update"))
    				this.getSqlMapClient().update(vo.getSqlId(), vo.getRowData());
    			else if(vo.getSqlId().contains("delete"))
    				this.getSqlMapClient().delete(vo.getSqlId(), vo.getRowData());
    		}
    		
    		upCnt = this.getSqlMapClient().executeBatch();
    		this.getSqlMapClient().commitTransaction();
    	}
    	catch(SQLException se)
    	{
    		se.printStackTrace();
    		
    		LOGGER.error("TMMPPBaseDAO.executeBatchMulti ERROR :: " + se.getErrorCode());
    		LOGGER.error("TMMPPBaseDAO.executeBatchMulti msg :: " + se.getMessage());
    		LOGGER.error("TMMPPBaseDAO.executeBatchMulti msg getNextException :: [" + se.getNextException() + "]");
    		
    		throw new SQLException(se.getNextException());
    	}
    	finally
    	{
    		this.getSqlMapClient().endTransaction();
    	}
    	
    	LOGGER.error("TMMPPBaseDAO.executeBatchMulti RESULT upCnt :: " + upCnt);
    	
    	return upCnt;
    }
}
