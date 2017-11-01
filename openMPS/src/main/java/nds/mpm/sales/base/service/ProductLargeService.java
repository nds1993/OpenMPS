package nds.mpm.sales.base.service;

import java.util.List;
import nds.mpm.sales.base.vo.ProductLargeDefaultVO;
import nds.mpm.sales.base.vo.ProductLargeVO;

/**
 * @Class Name : ProductLargeService.java
 * @Description : ProductLarge Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ProductLargeService {
	
	/**
	 * product_large을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ProductLargeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertProductLarge(ProductLargeVO vo) throws Exception;
    
    /**
	 * product_large을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ProductLargeVO
	 * @return void형
	 * @exception Exception
	 */
    void updateProductLarge(ProductLargeVO vo) throws Exception;
    
    /**
	 * product_large을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ProductLargeVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteProductLarge(ProductLargeVO vo) throws Exception;
    
    /**
	 * product_large을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ProductLargeVO
	 * @return 조회한 product_large
	 * @exception Exception
	 */
    ProductLargeVO selectProductLarge(ProductLargeVO vo) throws Exception;
    
    /**
	 * product_large 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_large 목록
	 * @exception Exception
	 */
    List selectProductLargeList(ProductLargeDefaultVO searchVO) throws Exception;
    
    /**
	 * product_large 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_large 총 갯수
	 * @exception
	 */
    int selectProductLargeListTotCnt(ProductLargeDefaultVO searchVO);
    
}
