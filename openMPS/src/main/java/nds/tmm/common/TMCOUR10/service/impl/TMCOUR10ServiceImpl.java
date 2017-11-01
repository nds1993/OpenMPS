package nds.tmm.common.TMCOUR10.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.common.vo.MultiDSTypeVO;
import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.tmm.common.TMCOUR10.service.TMCOUR10DAO;
import nds.tmm.common.TMCOUR10.service.TMCOUR10Service;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10DefaultVO;
import nds.tmm.common.TMCOUR10.vo.TMCOUR10VO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : TMCOUR10ServiceImpl.java
 * @Description : TMCOUR10 Business Implement class
 * @Modification Information
 *
 * @author TMM TEAM
 * @since 2017.07.07
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("TMCOUR10Service")
public class TMCOUR10ServiceImpl extends TMMPPBaseService implements
        TMCOUR10Service {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(TMCOUR10ServiceImpl.class);

    @Resource(name="TMCOUR10DAO")
    private TMCOUR10DAO TMCOUR10DAO;
    
    /** ID Generation */
    //@Resource(name="{egovTMCOUR10IdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * tm_userxm을 등록한다.
	 * @param vo - 등록할 정보가 담긴 TMCOUR10VO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public ResultEx insertTMCOUR10(List<EgovMap> vos) throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	int iCnt = 0;
    	for(EgovMap reqVo : vos)
    	{
    		if("C".equals((String)reqVo.get("dsType")) && selectTMCOUR10(reqVo) != null)
    		{
    			return new ResultEx( Consts.ResultCode.RC_DUPLICATE );
    		}
    		
    		if("C".equals((String)reqVo.get("dsType")))
    		{
    			
    			// 사용자 비밀번호 암호화 (초기 비밀번호 생년월일) 
    			//reqVo.setUserPass(new SHA256Util().getEncrypt(reqVo.getBirth().replaceAll("-","")));
    			
    			if(StringUtils.isEmpty(reqVo.get("corpCode"))) {
    				// 팀이나 부서 미선택시 로그인한 사용자의 회사코드로 기본 설정 
    				// 이준영 과장 요청
    				// 2017.08.30
    				// 세션 설정 미적용으로 강제 설정 세션 설정 적용시 사용자의 회사코드로 설정
    				reqVo.put("corpCode", "1001");
    			}
    			
    			TMCOUR10DAO.insertTMCOUR10(reqVo);
    		}
    		else if("U".equals((String)reqVo.get("dsType")))
    		{
    			TMCOUR10DAO.updateTMCOUR10(reqVo);
    		}
    		else if("D".equals((String)reqVo.get("dsType")))
    		{
    			TMCOUR10DAO.deleteTMCOUR10(reqVo);
    		}
    		
    	}

    	PageSet pageSet = new PageSet();
        
    	pageSet.setTotalRecordCount(vos.size());
    	pageSet.setResult(vos);
    	
        return result;
        
    }

    /**
	 * tm_userxm을 수정한다.
	 * @param vo - 수정할 정보가 담긴 TMCOUR10VO
	 * @return void형
	 * @exception Exception
	 */
    public void updateTMCOUR10(EgovMap vo) throws Exception {
        TMCOUR10DAO.updateTMCOUR10(vo);
    }

    /**
	 * tm_userxm을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 TMCOUR10VO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteTMCOUR10(EgovMap vo) throws Exception {
        TMCOUR10DAO.deleteTMCOUR10(vo);
    }
    
    /**
     * tm_userxm 비밀번호를 변경한다.
     * @param vo - 변경할 정보가 담긴 TMCOUR10VO
     * @return void형 
     * @exception Exception
     */
    public void updateUserPass(TMCOUR10VO vo) throws Exception {
    	TMCOUR10DAO.updateUserPass(vo);
    }

    /**
	 * tm_userxm을 조회한다.
	 * @param vo - 조회할 정보가 담긴 TMCOUR10VO
	 * @return 조회한 tm_userxm
	 * @exception Exception
	 */
    public TMCOUR10VO selectTMCOUR10(EgovMap vo) throws Exception {
        TMCOUR10VO resultVO = TMCOUR10DAO.selectTMCOUR10(vo);
        return resultVO;
    }

    /**
	 * tm_userxm 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_userxm 목록
	 * @exception Exception
	 */
    public List<?> selectTMCOUR10List(TMCOUR10DefaultVO searchVO) throws Exception {
        return TMCOUR10DAO.selectTMCOUR10List(searchVO);
    }

    /**
	 * tm_userxm 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return tm_userxm 총 갯수
	 * @exception
	 */
    public int selectTMCOUR10ListTotCnt(TMCOUR10DefaultVO searchVO) {
		return TMCOUR10DAO.selectTMCOUR10ListTotCnt(searchVO);
	}
    
}
