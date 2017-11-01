package nds.mpm.prod.PP0701.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.mpm.prod.PP0701.service.PP0701OdOderhdDAO;
import nds.mpm.prod.PP0701.service.PP0701OdOderhdService;
import nds.mpm.prod.PP0701.vo.OdOderhdVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Class Name : OdOderhdServiceImpl.java
 * @Description : OdOderhd Business Implement class
 * @Modification Information
 *
 * @author  
 * @since  
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("PP0701odOderhdService")
public class PP0701OdOderhdServiceImpl extends EgovAbstractServiceImpl implements
        PP0701OdOderhdService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(PP0701OdOderhdServiceImpl.class);

    @Resource(name="PP0701odOderhdDAO")
    private PP0701OdOderhdDAO odOderhdDAO;

    /**
	 * od_oderhd 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return od_oderhd 목록
	 * @exception Exception
	 */
    public List<?> selectOdOderhdList(OdOderhdVO searchVO) throws Exception {
        return odOderhdDAO.selectOdOderhdList(searchVO);
    }

    /**
	 * od_oderhd 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return od_oderhd 총 갯수
	 * @exception
	 */
    public int selectOdOderhdListTotCnt(OdOderhdVO searchVO) {
		return odOderhdDAO.selectOdOderhdListTotCnt(searchVO);
	}
    
}
