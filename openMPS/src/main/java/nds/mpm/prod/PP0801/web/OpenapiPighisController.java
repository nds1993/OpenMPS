/**
 * 축산물 이력제 신고 Test용 Controller
 * 
 * TestUnitBuy() : 매입실적 신고 테스트 method
 * 설명 :
 *  MP_PIGHIS_BUY_M 테이블 내용을 담는 객체(테스트시 MpPighisBuyVO class로 테스트함)리스트를
 *  OpenapiPighisService.declarePighisBuy(param1) method에 파라미터로 넘겨주면 service level에서 축산물 이력제 Open API를 호출하여
 *  매입실적 신고결과를 리스트(OpenapiPighisResultVO 객체 리스트)형태로 리턴해 줌  
 * 
 * TestUnitSale() : 매출실적 신고 테스트 method
 * 설명 :
 *  MP_PIGHIS_SALE_M 테이블 내용을 담는 객체(테스트시 MpPighisSaleVO class로 테스트함)리스트를
 *  OpenapiPighisService.declarePighisSale(param1) method에 파라미터로 넘겨주면 service level에서 축산물 이력제 Open API를 호출하여
 *  매출실적 신고결과를 리스트(OpenapiPighisResultVO 객체 리스트)형태로 리턴해 줌
 * 
 * TestUnitPack() : 포장실적 신고 테스트 method
 * 설명 :
 *  MP_PIGHIS_PACK_M 테이블 내용을 담는 객체(테스트시 MpPighisPackVO class로 테스트함)리스트를
 *  OpenapiPighisService.declarePighisPack(param1) method에 파라미터로 넘겨주면 service level에서 축산물 이력제 Open API를 호출하여
 *  포장실적 신고결과를 리스트(OpenapiPighisResultVO 객체 리스트)형태로 리턴해 줌
 * 
 **/
package nds.mpm.prod.PP0801.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.prod.PP0801.service.OpenapiPighisService;
import nds.mpm.prod.PP0801.vo.MpPighisBuyVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleVO;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ddr/Openapi")
public class OpenapiPighisController {
	
	@Resource(name = "openapiPigService")
	private OpenapiPighisService service;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/testApiBuy.do")
	public void TestUnitBuy() throws CloneNotSupportedException, IOException, ParseException {
		List<MpPighisBuyVO> list = (ArrayList<MpPighisBuyVO>)service.getPighisBuy();
		
		for(int i=0; i<list.size(); i++){
			System.out.printf(i + ". " + list.get(i).getHis_no() + " / " + list.get(i).getGita_weig());
		}
		
		service.declarePighisBuy(list);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/testApiSale.do")
	public void TestUnitSale() throws CloneNotSupportedException, IOException, ParseException {
		List<MpPighisSaleVO> list = (ArrayList<MpPighisSaleVO>)service.getPighisSale();
		
		for(int i=0; i<list.size(); i++){
			System.out.printf(i + ". " + list.get(i).getHis_bunch_no() + " / " + list.get(i).getGagong_no());
		}
		
		service.declarePighisSale(list);
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping("/testApiPack.do")
	public void TestUnitPack() throws CloneNotSupportedException, IOException, ParseException {
		List<MpPighisPackVO> list = (ArrayList<MpPighisPackVO>)service.getPighisPack();
		
		service.declarePighisPack(list);
	}

}
