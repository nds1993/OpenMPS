package nds.mpm.sales.SD0405.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.common.service.TMMPPBaseService;
import nds.mpm.sales.SD0405.service.MpOrderDDAO;
import nds.mpm.sales.SD0405.service.MpOrderHDAO;
import nds.mpm.sales.SD0405.service.MpOrderHService;
import nds.mpm.sales.SD0405.vo.MpOrderHDefaultVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Class Name :  MpOrderHServiceImpl
 *
 * @author MPM TEAM
 * @since 2017. 10. 3.
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 온라인 주문 업로드( 중복처리X 거래처, 해당일 최종주문만 유효처리. )
 * 거래처 제품, 환산계수 적용.
 * 
 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017. 10. 3.	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */
@Service("mpOrderHService")
public class MpOrderHServiceImpl extends TMMPPBaseService implements
        MpOrderHService {
        
    private static final Logger logger = LoggerFactory.getLogger(MpOrderHServiceImpl.class);

    @Resource(name="mpOrderHDAO")
    private MpOrderHDAO mpOrderHDAO;
    
    @Resource(name="mpOrderDDAO")
    private MpOrderDDAO mpOrderDDAO;
    
    /**
	 * mp_order_h 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 목록
	 * @exception Exception
	 */
    public List<?> selectMpOrderHList(MpOrderHDefaultVO searchVO) throws Exception {
        return mpOrderHDAO.selectMpOrderHList(searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectMpOrderHListTotCnt(MpOrderHDefaultVO searchVO) {
		return mpOrderHDAO.selectMpOrderHListTotCnt(searchVO);
	}
    
    public List<?> selectSD0406List(MpOrderHDefaultVO searchVO) throws Exception {
    	return mpOrderHDAO.selectSD0406List(searchVO);
    }

    /**
	 * mp_order_h 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return mp_order_h 총 갯수
	 * @exception
	 */
    public int selectSD0406ListTotCnt(MpOrderHDefaultVO searchVO) {
    	return mpOrderHDAO.selectSD0406ListTotCnt(searchVO);
    }
    
    public List<?> selectSD0406CustList(MpOrderHDefaultVO searchVO) throws Exception {
    	return mpOrderHDAO.selectSD0406CustList(searchVO);
    }
    
    public List<?> selectSD0406OrdrProList(MpOrderHDefaultVO searchVO) throws Exception {
    	return mpOrderHDAO.selectSD0406OrdrProList(searchVO);
    }
}
