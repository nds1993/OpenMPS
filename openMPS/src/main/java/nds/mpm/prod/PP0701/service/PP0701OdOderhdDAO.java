package nds.mpm.prod.PP0701.service;

import java.util.List;

import nds.mpm.prod.PP0701.vo.OdOderhdVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : OdOderhdDAO.java
 * @Description : OdOderhd DAO Class
 * @Modification Information
 *
 * @author  
 * @since  
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("PP0701odOderhdDAO")
public class PP0701OdOderhdDAO extends EgovAbstractDAO {

    /**
	 * od_oderhd 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return od_oderhd 목록
	 * @exception Exception
	 */
    public List<?> selectOdOderhdList(OdOderhdVO searchVO) throws Exception {
        return list("PP0701odOderhdDAO.selectOdOderhdList_D", searchVO);
    }

    /**
	 * od_oderhd 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return od_oderhd 총 갯수
	 * @exception
	 */
    public int selectOdOderhdListTotCnt(OdOderhdVO searchVO) {
        return (Integer)select("PP0701odOderhdDAO.selectOdOderhdListTotCnt_S", searchVO);
    }

}
