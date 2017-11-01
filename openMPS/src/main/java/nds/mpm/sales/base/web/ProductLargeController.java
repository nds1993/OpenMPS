package nds.mpm.sales.base.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.mpm.common.vo.PageSet;
import nds.mpm.common.vo.ResultEx;
import nds.mpm.common.web.Consts;
import nds.mpm.common.web.CorsFilter;
import nds.mpm.sales.base.service.ProductLargeService;
import nds.mpm.sales.base.vo.ProductLargeDefaultVO;
import nds.mpm.sales.base.vo.ProductLargeVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : ProductLargeController.java
 *
 * @author MPM TEAM
 * @since 2017.06.08
 * @version 1.0
 * @see
 * <pre>
 * 화면명 : 품목코드관리.

 * << 개정이력(Modification Information) >>
 *   
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2017.06.27	 			최초생성
 *
 * </pre> 
 *  Copyright (C)  All right reserved.
 */

@Controller
@RequestMapping("/mpm/productlarge")
public class ProductLargeController {
	
	private static final Logger _logger = LoggerFactory.getLogger(ProductLargeController.class);
	
	@Autowired
	protected CorsFilter		_filter;
   
    @Resource(name = "productLargeService")
    private ProductLargeService productLargeService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    /**
	 * product_large 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 ProductLargeDefaultVO
	 * @return "/productLarge/ProductLargeList"
	 * @exception Exception
	 */
    @RequestMapping(value="",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectProductLargeList(
    		HttpServletRequest req,
    		HttpServletResponse res,
    		ModelMap model)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
    	ProductLargeVO searchVO = new ProductLargeVO();
    	
        List<?> teamInfoList = productLargeService.selectProductLargeList(searchVO);
        PageSet pageSet = new PageSet();
        pageSet.setResult(teamInfoList);
        result.setExtraData(pageSet);
        
        return _filter.makeCORSEntity( result );
    } 
    
    @RequestMapping(value="",method=RequestMethod.POST)
    public ResponseEntity<ResultEx> addProductLarge(
    		@RequestBody ProductLargeVO productLargeVO,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );
    	
        productLargeService.insertProductLarge(productLargeVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{proLcode}",method=RequestMethod.GET)
    public ResponseEntity<ResultEx> selectProductLarge(
    		@PathVariable("proLcode") String proLcode
            ) throws Exception {
        
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	ProductLargeVO productLargeVO = new ProductLargeVO();
    	productLargeVO.setProLcode(proLcode);
    	result.setExtraData( productLargeService.selectProductLarge(productLargeVO) );
        return _filter.makeCORSEntity( result );
    }

    @RequestMapping(value="/{proLcode}",method=RequestMethod.PUT)
    public ResponseEntity<ResultEx> updateProductLarge(
    		@RequestBody ProductLargeVO productLargeVO,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

        productLargeService.updateProductLarge(productLargeVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }
    
    @RequestMapping(value="/{proLcode}",method=RequestMethod.DELETE)
    public ResponseEntity<ResultEx> deleteProductLarge(
    		@PathVariable("proLcode") String proLcode,
            SessionStatus status)
            throws Exception {
    	
    	ResultEx result = new ResultEx( Consts.ResultCode.RC_OK );

    	ProductLargeVO productLargeVO = new ProductLargeVO();
    	productLargeVO.setProLcode(proLcode);
    	
        productLargeService.deleteProductLarge(productLargeVO);
        status.setComplete();
        return _filter.makeCORSEntity( result );
    }

}
