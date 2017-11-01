package nds.tmm.common.TMCOBD20.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOBD20.service.TMCOBD20DAO;
import nds.tmm.common.TMCOBD20.service.TMCOBD20Service;
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;
import nds.tmm.common.TMCOBD20.vo.TMCOBD20VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOBD20ServiceImpl.java
 * @Description : TMCOBD20 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOBD20Service")
public class TMCOBD20ServiceImpl extends TMMPPBaseService implements
        TMCOBD20Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOBD20ServiceImpl.class);

    @Resource(name="TMCOBD20DAO")
    private TMCOBD20DAO TMCOBD20DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOBD20IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_bdctxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOBD20(TMCOBD20VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOBD20DAO.insertTMCOBD20(vo);	
    	
        return result;
    	
    }

    /**
	 * tm_bdctxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD20VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOBD20(TMCOBD20VO vo) throws Exception {
        TMCOBD20DAO.updateTMCOBD20(vo);
    }
    
    /**
     * tm_bdctxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void updateReplySortOrderTMCOBD20(TMCOBD20VO vo) throws Exception {
    	TMCOBD20DAO.updateReplySortOrderTMCOBD20(vo);
    }

    /**
	 * tm_bdctxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOBD20VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOBD20(TMCOBD20VO vo) throws Exception {
        TMCOBD20DAO.deleteTMCOBD20(vo);
    }

    /**
	 * tm_bdctxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOBD20VO
	 * @return 조회한 tm_bdctxm
	 * @exception Exception
	 */
    public TMCOBD20VO selectTMCOBD20(EgovMap vo) throws Exception {
        TMCOBD20VO resultVO = TMCOBD20DAO.selectTMCOBD20(vo);
        return resultVO;
    }

    /**
	 * tm_bdctxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdctxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOBD20List(TMCOBD20VO searchVO) throws Exception {
        return TMCOBD20DAO.selectTMCOBD20List(searchVO);
    }

    /**
	 * tm_bdctxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdctxm 총 갯수
	 * @exception
	 */
    public int selectTMCOBD20ListTotCnt(TMCOBD20VO searchVO) {
		return TMCOBD20DAO.selectTMCOBD20ListTotCnt(searchVO);
	}
    
    /**
     * tm_faqxxm을 수정한다.
     * @param vo - 수정할 정보가 담긴 TMCOBD20VO
     * @return void형
     * @exception Exception
     */
    public void updateReadCntTMCOBD20(TMCOBD20VO vo) throws Exception {
    	TMCOBD20DAO.updateReadCntTMCOBD20(vo);
    }
    
    /**
	 * tm_bdredh을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertBdredhTMCOBD20(TMCOBD20VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOBD20DAO.insertBdredhTMCOBD20(vo);
    	
        return result;
    	
    }
    
    /**
     * tm_bdredh을 조회한다.
     * @param vo - 조회할 정보가 담긴 TMCOBD20VO
     * @return 조회한 tm_bdredh
     * @exception Exception
     */
    public TMCOBD20VO selectBdredhTMCOBD20(TMCOBD20VO vo) throws Exception {
    	TMCOBD20VO resultVO = TMCOBD20DAO.selectBdredhTMCOBD20(vo);
    	return resultVO;
    }
    
    /**
	 * tm_bdcmxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOBD20VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertComment(TMCOBD20VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	TMCOBD20DAO.insertComment(vo);
    	
        return result;
    	
    }    
    
    /**
	 * tm_bdcmxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_bdctxm 목록
	 * @exception Exception
	 */
   public List<?> selectComment(TMCOBD20VO searchVO) throws Exception {
       return TMCOBD20DAO.selectComment(searchVO);
   }

   /**
	 * tm_bdcmxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOBD20VO
	 * @return void형
	 * @exception Exception
	 */
   public void updateComment(TMCOBD20VO vo) throws Exception {
       TMCOBD20DAO.updateComment(vo);
   }
   
   /**
    * tm_bdcmxm을 수정한다.
    * @param vo - 수정할 정보가 담긴 TMCOBD20VO
    * @return void형
    * @exception Exception
    */
   public void deleteComment(TMCOBD20VO vo) throws Exception {
	   TMCOBD20DAO.deleteComment(vo);
   }
   
   /**
    * tm_atflxm 목록을 조회한다.
    * @param searchMap - 조회할 정보가 담긴 Map
    * @return tm_atflxm 목록
    * @exception Exception
    */
   public String selectNextFileId(TMCOBD20VO vo) throws Exception {
	   return TMCOBD20DAO.selectNextFileId(vo);
   }
   
   /**
    * tm_atflxm을 등록한다.
    * @param vo - 수정할 정보가 담긴 TMCOBD20VO
    * @return void형
    * @exception Exception
    */
   public void insertFileMaster(TMCOBD20VO vo) throws Exception {
	   TMCOBD20DAO.insertFileMaster(vo);
   }
   
   /**
    * tm_atflxd을 등록한다.
    * @param vo - 수정할 정보가 담긴 TMCOBD20VO
    * @return void형
    * @exception Exception
    */
   public void insertFileDetail(TMCOBD20VO vo) throws Exception {
	   TMCOBD20DAO.insertFileDetail(vo);
   }
   
   /**
    * tm_atflxd을 수정한다.
    * @param vo - 수정할 정보가 담긴 TMCOBD20VO
    * @return void형
    * @exception Exception
    */
   public void deleteFile(TMCOBD20VO vo) throws Exception {
	   TMCOBD20DAO.deleteFile(vo);
   }
   
   /**
    * tm_atflxd 목록을 조회한다.
    * @param searchMap - 조회할 정보가 담긴 Map
    * @return tm_atflxd 목록
    * @exception Exception
    */
   public List<?> selectFile(TMCOBD20VO vo) throws Exception {
	   return TMCOBD20DAO.selectFile(vo);
   }    
   
}
