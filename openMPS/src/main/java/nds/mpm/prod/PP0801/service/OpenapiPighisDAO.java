/**
 * 축산물 이력제 신고 URL, 계정, ApiKey를 DB에서 조회하기 위한 DAO
 * 
 * getOpenApiInfoBuy() 매입 Open api 정보 조회
 * getOpenApiInfoSale() 매출 Open api 정보 조회
 * getOpenApiInfoPack() 포장 Open api 정보 조회
 * 
 * 
 * getPigBuy(), getPigPack(), getPigSale()는 테스트용입니다. 개발시 제거해야 합니다.
 * 
 * sql 파일명은 "OpenApiPig_SqlMap_PostgreSQL.xml"입니다.
 * 
 */
package nds.mpm.prod.PP0801.service;

import java.util.List;

import nds.mpm.prod.PP0801.vo.OpenapiCommonInfoVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("openapiPigDao")
public class OpenapiPighisDAO extends EgovAbstractDAO {

	public OpenapiCommonInfoVO getOpenApiInfoBuy(){
		return (OpenapiCommonInfoVO)select("OpenapiPig.selectBuyInfo");
	}
	
	public OpenapiCommonInfoVO getOpenApiInfoSale(){
		return (OpenapiCommonInfoVO)select("OpenapiPig.selectSaleInfo");
	}
	
	public OpenapiCommonInfoVO getOpenApiInfoPack(){
		return (OpenapiCommonInfoVO)select("OpenapiPig.selectPackInfo");
	}
	
	public List<?> getPigBuy(){
		return list("OpenapiPig.selectPigHisBuy");
	}
	
	public List<?> getPigPack(){
		return list("OpenapiPig.selectPigHisPack");
	}
	
	public List<?> getPigSale(){
		return list("OpenapiPig.selectPigHisSale");
	}
}
