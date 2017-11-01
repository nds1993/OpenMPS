/**
 *
 * 축산물 이력제 신고 Service Interface
 * 
 * declarePighisBuy() 매입실적 신고용 method
 * declarePighisSal() 매출실적 신고용 method
 * declarePighisPack() 포장실적 신고용 method
 * 
 * callOpenApi() 축산물 이력 Open API호출용 method
 * 
 * 
 * getPighisBuy()
 * getPighisSale()
 * getPighisPack()
 * 위 3개 method는 테스트용으로 생성한 것으로 실제 개발 시에는 제거해야 합니다.
 * (db에서 매입/매출/판매 정보를 읽어와 List에 담아 Service로 넘기는 테스트용)
 * 
 **/
package nds.mpm.prod.PP0801.service;

import java.io.IOException;
import java.util.List;

import nds.core.common.common.service.Service;
import nds.mpm.prod.PP0801.vo.MpPighisBuyMVO;
import nds.mpm.prod.PP0801.vo.MpPighisBuyVO;
import nds.mpm.prod.PP0801.vo.OpenapiPighisResultVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleVO;

import org.json.simple.parser.ParseException;

public interface OpenapiPighisService extends Service {
	
	//매입실적 신고
	public List<OpenapiPighisResultVO> declarePighisBuy(List<MpPighisBuyVO> pighistbuy) throws CloneNotSupportedException,IOException, ParseException ;
	
	//매출실적 신고
	public List<OpenapiPighisResultVO> declarePighisSale(List<MpPighisSaleVO> pighistsale) throws CloneNotSupportedException, IOException, ParseException;
	
	//포장실적 신고
	public List<OpenapiPighisResultVO> declarePighisPack(List<MpPighisPackVO> pighistpack) throws CloneNotSupportedException, IOException, ParseException;

	public List<OpenapiPighisResultVO> callOpenApi(String pigFlag, String targetUrl,
			String contentType, String requestParam) throws IOException, ParseException;
	
	public List<?> getPighisBuy();
	public List<?> getPighisSale();
	public List<?> getPighisPack();

}
