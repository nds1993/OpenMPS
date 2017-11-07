package nds.mpm.sales.SD0501.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.sales.SD0501.service.SD0501DAO;
import nds.mpm.sales.SD0501.service.SD0501Service;
import nds.mpm.sales.SD0501.vo.SD0501VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name : SD0501ServiceImpl.java
 * @Description : SD0501 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.04
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("SD0501Service")
public class SD0501ServiceImpl extends TMMPPBaseService implements
        SD0501Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(SD0501ServiceImpl.class);

    @Resource(name="SD0501DAO")
    private SD0501DAO SD0501DAO;
    
    /** ID Generation */
    //@Resource(name="{egovSD0501IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * sd_claimrqxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 SD0501VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertSD0501(SD0501VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	SD0501DAO.insertSD0501(vo);
    	
        return result;
    	
    }
    
    /**
     * sd_claimpcxm을 등록한다.
     * @param vo - 등록할 정보가 담긴 SD0501VO
     * @return 등록 결과
     * @exception Exception
     */
    public ResultEx insertSdClaimpcxm(SD0501VO vo) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	SD0501DAO.insertSdClaimpcxm(vo);
    	
    	return result;
    	
    }

    /**
	 * sd_claimrqxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SD0501VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateSD0501(SD0501VO vo) throws Exception {
        SD0501DAO.updateSD0501(vo);
    }

    /**
	 * sd_claimrqxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 SD0501VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteSD0501(SD0501VO vo) throws Exception {
        SD0501DAO.deleteSD0501(vo);
    }
    
    /**
     * sd_claimrqxm을 변경한다.
     * @param vo - 변경할 정보가 담긴 SD0501VO
     * @return void형 
     * @exception Exception
     */
    public void updateProcStatusSD0501(SD0501VO vo) throws Exception {
    	SD0501DAO.updateProcStatusSD0501(vo);
    }

    /**
	 * sd_claimrqxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 SD0501VO
	 * @return 조회한 sd_claimrqxm
	 * @exception Exception
	 */
    public SD0501VO selectSD0501(SD0501VO vo) throws Exception {
        SD0501VO resultVO = SD0501DAO.selectSD0501(vo);
        return resultVO;
    }

    /**
	 * sd_claimrqxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return sd_claimrqxm 목록
	 * @exception Exception
	 */
    public List<?> selectSD0501List(SD0501VO searchVO) throws Exception {
        return SD0501DAO.selectSD0501List(searchVO);
    }

    /**
	 * sd_claimrqxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return sd_claimrqxm 총 갯수
	 * @exception
	 */
    public int selectSD0501ListTotCnt(SD0501VO searchVO) {
		return SD0501DAO.selectSD0501ListTotCnt(searchVO);
	}
    
}
