package nds.mpm.sales.base.service;

import java.util.List;
import nds.mpm.sales.base.vo.ProductInfoDefaultVO;
import nds.mpm.sales.base.vo.ProductInfoVO;

/**
 * @Class Name : ProductInfoService.java
 * @Description : ProductInfo Business class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface ProductInfoService {
	
	/**
	 * product_info을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ProductInfoVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    String insertProductInfo(ProductInfoVO vo) throws Exception;
    
    /**
	 * product_info을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ProductInfoVO
	 * @return void형
	 * @exception Exception
	 */
    void updateProductInfo(ProductInfoVO vo) throws Exception;
    
    /**
	 * product_info을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ProductInfoVO
	 * @return void형 
	 * @exception Exception
	 */
    void deleteProductInfo(ProductInfoVO vo) throws Exception;
    
    /**
	 * product_info을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ProductInfoVO
	 * @return 조회한 product_info
	 * @exception Exception
	 */
    ProductInfoVO selectProductInfo(ProductInfoVO vo) throws Exception;
    
    /**
	 * product_info 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_info 목록
	 * @exception Exception
	 */
    List selectProductInfoList(ProductInfoDefaultVO searchVO) throws Exception;
    
    /**
	 * product_info 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_info 총 갯수
	 * @exception
	 */
    int selectProductInfoListTotCnt(ProductInfoDefaultVO searchVO);
    
}
