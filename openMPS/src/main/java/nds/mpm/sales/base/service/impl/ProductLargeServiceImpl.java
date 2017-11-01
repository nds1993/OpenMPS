package nds.mpm.sales.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import nds.mpm.sales.base.service.ProductLargeService;
import nds.mpm.sales.base.vo.ProductLargeDefaultVO;
import nds.mpm.sales.base.vo.ProductLargeVO;
import nds.mpm.sales.base.service.ProductLargeDAO;

/**
 * @Class Name : ProductLargeServiceImpl.java
 * @Description : ProductLarge Business Implement class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("productLargeService")
public class ProductLargeServiceImpl extends EgovAbstractServiceImpl implements
        ProductLargeService {
        
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductLargeServiceImpl.class);

    @Resource(name="productLargeDAO")
    private ProductLargeDAO productLargeDAO;
    
    /** ID Generation */
    //@Resource(name="{egovProductLargeIdGnrService}")    
    //private EgovIdGnrService egovIdGnrService;

	/**
	 * product_large을 등록한다.
	 * @param vo - 등록할 정보가 담긴 ProductLargeVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public String insertProductLarge(ProductLargeVO vo) throws Exception {
    	LOGGER.debug(vo.toString());
    	
    	/** ID Generation Service */
    	//TODO 해당 테이블 속성에 따라 ID 제너레이션 서비스 사용
    	//String id = egovIdGnrService.getNextStringId();
    	//vo.setId(id);
    	LOGGER.debug(vo.toString());
    	
    	productLargeDAO.insertProductLarge(vo);
    	//TODO 해당 테이블 정보에 맞게 수정    	
        return null;
    }

    /**
	 * product_large을 수정한다.
	 * @param vo - 수정할 정보가 담긴 ProductLargeVO
	 * @return void형
	 * @exception Exception
	 */
    public void updateProductLarge(ProductLargeVO vo) throws Exception {
        productLargeDAO.updateProductLarge(vo);
    }

    /**
	 * product_large을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 ProductLargeVO
	 * @return void형 
	 * @exception Exception
	 */
    public void deleteProductLarge(ProductLargeVO vo) throws Exception {
        productLargeDAO.deleteProductLarge(vo);
    }

    /**
	 * product_large을 조회한다.
	 * @param vo - 조회할 정보가 담긴 ProductLargeVO
	 * @return 조회한 product_large
	 * @exception Exception
	 */
    public ProductLargeVO selectProductLarge(ProductLargeVO vo) throws Exception {
        ProductLargeVO resultVO = productLargeDAO.selectProductLarge(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * product_large 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_large 목록
	 * @exception Exception
	 */
    public List<?> selectProductLargeList(ProductLargeDefaultVO searchVO) throws Exception {
        return productLargeDAO.selectProductLargeList(searchVO);
    }

    /**
	 * product_large 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return product_large 총 갯수
	 * @exception
	 */
    public int selectProductLargeListTotCnt(ProductLargeDefaultVO searchVO) {
		return productLargeDAO.selectProductLargeListTotCnt(searchVO);
	}
    
}
