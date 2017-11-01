package nds.mpm.sales.SD0201.service;

import java.util.List;

import nds.mpm.common.vo.ResultEx;
import nds.mpm.sales.SD0201.vo.MpStndProdDefaultVO;
import nds.mpm.sales.SD0201.vo.MpStndProdVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * @Class Name : MpStndProdService.java
 * @Description : MpStndProd Business class
 * @Modification Information
 *
 * @author ㅡ
 * @since ㅡ
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface MpStndProdService {
	
	/**
	 * mp_stnd_prod을 등록한다.
	 * @param vo - 등록할 정보가 담긴 MpStndProdVO
	 * @return 등록 결과
	 * @exception Exception
	 */
	ResultEx insertMpStndProd(List<EgovMap> vo) throws Exception;
    
    /**
	 * mp_stnd_prod을 수정한다.
	 * @param vo - 수정할 정보가 담긴 MpStndProdVO
	 * @return void형
	 * @exception Exception
	 */
    void updateMpStndProd(MpStndProdVO vo) throws Exception;
    
    /**
	 * mp_stnd_prod을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 MpStndProdVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteMpStndProd(MpStndProdVO vo) throws Exception;
    
    /**
	 * mp_stnd_prod을 조회한다.
	 * @param vo - 조회할 정보가 담긴 MpStndProdVO
	 * @return 조회한 mp_stnd_prod
	 * @exception Exception
	 */
    MpStndProdVO selectMpStndProd(EgovMap vo) throws Exception;
    
    /**
	 * mp_stnd_prod 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_prod 목록
	 * @exception Exception
	 */
    List selectMpStndProdList(MpStndProdDefaultVO searchVO) throws Exception;
    
    /**
	 * mp_stnd_prod 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_stnd_prod 총 갯수
	 * @exception
	 */
    int selectMpStndProdListTotCnt(MpStndProdDefaultVO searchVO);
    
}
