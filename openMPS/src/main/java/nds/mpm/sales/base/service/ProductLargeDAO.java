package nds.mpm.sales.base.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import nds.mpm.sales.base.vo.ProductLargeVO;
import nds.mpm.sales.base.vo.ProductLargeDefaultVO;

/**
 * @Class Name : ProductLargeDAO.java
 * @Description : ProductLarge DAO Class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("productLargeDAO")
public class ProductLargeDAO extends EgovAbstractDAO {

	/**
	 * product_large을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ProductLargeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertProductLarge(ProductLargeVO vo) throws Exception {
        return (String)insert("productLargeDAO.insertProductLarge_S", vo);
    }

    /**
	 * product_large을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ProductLargeVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateProductLarge(ProductLargeVO vo) throws Exception {
        update("productLargeDAO.updateProductLarge_S", vo);
    }

    /**
	 * product_large을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ProductLargeVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteProductLarge(ProductLargeVO vo) throws Exception {
        delete("productLargeDAO.deleteProductLarge_S", vo);
    }

    /**
	 * product_large을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ProductLargeVO
	 * @return 조회한 product_large
	 * @exception Exception
	 */
    public ProductLargeVO selectProductLarge(ProductLargeVO vo) throws Exception {
        return (ProductLargeVO) select("productLargeDAO.selectProductLarge_S", vo);
    }

    /**
	 * product_large 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return product_large 목록
	 * @exception Exception
	 */
    public List<?> selectProductLargeList(ProductLargeDefaultVO searchVO) throws Exception {
        return list("productLargeDAO.selectProductLargeList_D", searchVO);
    }

    /**
	 * product_large 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return product_large 총 갯수
	 * @exception
	 */
    public int selectProductLargeListTotCnt(ProductLargeDefaultVO searchVO) {
        return (Integer)select("productLargeDAO.selectProductLargeListTotCnt_S", searchVO);
    }

}
