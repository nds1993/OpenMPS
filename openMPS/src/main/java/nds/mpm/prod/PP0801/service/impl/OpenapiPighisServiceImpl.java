/**
 * 
 * 축산물 이력제 신고 Service 구현 class
 * 
 * declarePighisBuy() 매입실적 신고용 method
 * 설명 :
 *  
 *  * 이 method는 축산물 이력 Open api에서 요구하는 데이터 포멧에 맞게 
 *    파라미터로 넘겨받은 List를 Json형태로 변경합니다.
 *  
 *  * try/catch 구문으로 구현하지 않았습니다. 
 *    별도의 예외처리가 필요하다면 그에 맞게 수정하시면 됩니다.   
 *  
 *  1. 축산물 이력 Open api url, 계정, apiKey 정보를 DB에서 조회하여 OpenapiCommonInfoVO 객체로 생성
 *  2. 파라미터로 받은 매입신고 대상 리스트 정렬 (정렬을 위해 MpPighisBuyComparator class 생성)
 *     파라미터로 받은 List가 이미 정렬되었다는게 보장된다면 생략해도 무방함
 *     정렬 기준은 MpPighisBuyComparator class내 compare() method 참조
 *  3. 정렬된 리스트를 Json 형태로 변경
 *     리스트에서 객체의 특정 값을 할당해 줘야하는데 이 부분은 객체의 getter method에 맞게 수정 필요
 *  4. callOpenApi() method 호출
 * 
 * 
 * 
 * declarePighisSale() 매출실적 신고용 method
 * 설명 : *매입실적 신고용 method와 유사합니다.
 * 
 * 
 * declarePighisPack() 포장실적 신고용 method
 * 설명 : *매입실적 신고용 method와 유사합니다.
 * 
 * 
 * callOpenApi() 축산물 이력 Open API호출용 method
 * 설명 : 축산물 이력 Open api로 매입/매출/포장 실적 정보 전송 후 결과를 리턴
 *  
 */
package nds.mpm.prod.PP0801.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import nds.mpm.prod.PP0801.service.OpenapiPighisDAO;
import nds.mpm.prod.PP0801.service.OpenapiPighisService;
import nds.mpm.prod.PP0801.vo.MpPighisBuyComparator;
import nds.mpm.prod.PP0801.vo.MpPighisBuyVO;
import nds.mpm.prod.PP0801.vo.OpenapiCommonInfoVO;
import nds.mpm.prod.PP0801.vo.OpenapiPighisBuydetailVO;
import nds.mpm.prod.PP0801.vo.OpenapiPighisBuyheaderVO;
import nds.mpm.prod.PP0801.vo.OpenapiPighisResultVO;
import nds.mpm.prod.PP0802.vo.MpPighisPackComparator;
import nds.mpm.prod.PP0802.vo.MpPighisPackVO;
import nds.mpm.prod.PP0802.vo.OpenapiPighisPackdetailT2VO;
import nds.mpm.prod.PP0802.vo.OpenapiPighisPackdetailT3VO;
import nds.mpm.prod.PP0802.vo.OpenapiPighisPackheaderVO;
import nds.mpm.prod.PP0803.vo.MpPighisSaleComparator;
import nds.mpm.prod.PP0803.vo.MpPighisSaleVO;
import nds.mpm.prod.PP0803.vo.OpenapiPighisSaledetailVO;
import nds.mpm.prod.PP0803.vo.OpenapiPighisSaleheaderVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;


@Service("openapiPigService")
public class OpenapiPighisServiceImpl implements OpenapiPighisService {

	@Resource(name = "openapiPigDao")
	private OpenapiPighisDAO openapiPigDao;

	// 매입신고
	@SuppressWarnings("unchecked")
	@Override
	public List<OpenapiPighisResultVO> declarePighisBuy(List<MpPighisBuyVO> pighistbuy) throws CloneNotSupportedException, IOException, ParseException {

		OpenapiCommonInfoVO info = openapiPigDao.getOpenApiInfoBuy();

		JSONObject obj = new JSONObject();
		
		obj.put("userId", info.getUserId());
		obj.put("apiKey", info.getApiKey());
		obj.put("typeCode", info.getTypeCode());

		JSONArray tArray = new JSONArray();
		
		OpenapiPighisBuyheaderVO tmpHeader = null;
		ArrayList<OpenapiPighisBuydetailVO> tmpt2List = new ArrayList<OpenapiPighisBuydetailVO>();
		double tmpTotalWeight = 0;
		
		//전달받은 List 정렬
		Collections.sort(pighistbuy, new MpPighisBuyComparator());
		
		for (int i = 0; i < pighistbuy.size(); i++) {
			
			OpenapiPighisBuydetailVO tmpDetail = new OpenapiPighisBuydetailVO();
			tmpDetail.setInType("T2"); //부위정보구분(T2:지육)
			tmpDetail.setInYmd(pighistbuy.get(i).getBuy_date()); //매입일자
			tmpDetail.setInputTypeNm("이력"); //이력/묶음구분명
			tmpDetail.setLotNo(""); //묶음번호
			tmpDetail.setPigNo(pighistbuy.get(i).getHis_no()); //이력번호
			tmpDetail.setButCheryNo(pighistbuy.get(i).getDoch_no()); //도체번호
			tmpDetail.setPartCd(pighistbuy.get(i).getBase_part_code()); //부위코드
			tmpDetail.setPartNm(pighistbuy.get(i).getBase_part_name()); //부위명
			tmpDetail.setInWeight(String.valueOf(pighistbuy.get(i).getGita_weig())); //중량
			tmpDetail.setGradeCd(pighistbuy.get(i).getGrade_gubun2()); //등급
			
			tmpt2List.add(tmpDetail);
			
			if (tmpHeader == null) {
				
				tmpHeader = new OpenapiPighisBuyheaderVO();
				tmpHeader.setInType("T1"); // 매입정보구분(T1:지육)
				tmpHeader.setInYmd(pighistbuy.get(i).getBuy_date()); // 매입일자
				tmpHeader.setInputTypeNm("이력"); // 이력/묶음구분명
				tmpHeader.setLotNo(""); // 묶음번호
				tmpHeader.setPigNo(pighistbuy.get(i).getHis_no()); // 이력번호
				tmpHeader.setGradeCd(""); // 등급
				tmpHeader.setInCorpNo(pighistbuy.get(i).getComp_ssno()); // 매입처사업자등록번호
				tmpHeader.setInCorpNm(pighistbuy.get(i).getComp_name()); // 매입처 상호
				tmpHeader.setInCorpOwnerNm(pighistbuy.get(i).getComp_owner()); // 대표자
				tmpHeader.setInCorpTel(pighistbuy.get(i).getComp_tel()); // 연락처
				tmpHeader.setInCorpAddr(pighistbuy.get(i).getComp_addr());// 매입처 주소
				tmpHeader.setInputPlaceNm("가공장"); // 매입처 유형
				tmpHeader.setPigTypeNm(pighistbuy.get(i).getBase_part_name()); // 지육/정육 구분명
				tmpHeader.setInTypeNm("자체매입분"); // 매입구분(자체매입분/외부매입분)
				tmpHeader.setTotWeight(""); //총중량
			}
			tmpTotalWeight = tmpTotalWeight + pighistbuy.get(i).getGita_weig();//총중량 합
			
			//i값이 pighistbuy Array 맨 마지막이거나,
			//i값이 pighistbuy Array 맨 마지막이 아니면서 Array 다음 항목이 현재 tmpHeader 항목과 다를경우
			//Jason 객체 생성
			if ((i < pighistbuy.size() - 1 && (!tmpHeader.getInYmd().equals(pighistbuy.get(i+1).getBuy_date()) || !tmpHeader.getPigNo().equals(pighistbuy.get(i+1).getHis_no()) || !tmpHeader.getInCorpNo().equals(pighistbuy.get(i+1).getComp_ssno())))
					|| i == pighistbuy.size() - 1) {
				
				JSONObject t = new JSONObject();
				

				tmpHeader.setTotWeight(String.valueOf(tmpTotalWeight)); //총중량 합을 문자열로 변환
				
				OpenapiPighisBuyheaderVO t1 = tmpHeader.clone();
				tmpHeader = null;
				tmpTotalWeight = 0;
				
				ArrayList<OpenapiPighisBuydetailVO> t2List = (ArrayList<OpenapiPighisBuydetailVO>) tmpt2List.clone();
				tmpt2List.clear();
				
				t.put("T1", JSONObject.fromObject(t1));
				t.put("T2", JSONArray.fromObject(t2List));
				tArray.add(t);
			}
						
		}
		obj.put("item", tArray);	
		
		//System.out.println(obj.toString());
		
		//return null;
		return this.callOpenApi("BUY", info.getSiteUrl(), "application/json", obj.toString());
	}

	// 매출신고
	@SuppressWarnings("unchecked")
	@Override
	public List<OpenapiPighisResultVO> declarePighisSale(List<MpPighisSaleVO> pighistsale) throws CloneNotSupportedException, IOException, ParseException {

		OpenapiCommonInfoVO info = openapiPigDao.getOpenApiInfoSale();

		JSONObject obj = new JSONObject();
		obj.put("userId", info.getUserId());
		obj.put("apiKey", info.getApiKey());
		obj.put("typeCode", info.getTypeCode());

		JSONArray tArray = new JSONArray();
		
		OpenapiPighisSaleheaderVO tmpHeader = null;
		ArrayList<OpenapiPighisSaledetailVO> tmpt2List = new ArrayList<OpenapiPighisSaledetailVO>();
		double tmpTotalWeight = 0;
		
		//전달받은 List 정렬
		Collections.sort(pighistsale, new MpPighisSaleComparator());

		for (int i = 0; i < pighistsale.size(); i++) {
			
			OpenapiPighisSaledetailVO tmpDetail = new OpenapiPighisSaledetailVO();
			tmpDetail.setOutType("T2"); //부위정보구분(T2:지육)
			tmpDetail.setOutYmd(pighistsale.get(i).getSale_date()); //반출일자
			tmpDetail.setOutputTypeNm("이력"); //이력/묶음구분명
			tmpDetail.setPigNo(pighistsale.get(i).getHis_bunch_no()); //이력번호
			tmpDetail.setLotNo(""); //묶음번호
			tmpDetail.setButCheryNo(pighistsale.get(i).getGagong_no()); //도체번호
			tmpDetail.setPartCd(pighistsale.get(i).getBase_part_code()); //부위코드
			tmpDetail.setPartNm(pighistsale.get(i).getBase_part_name()); //부위명
			tmpDetail.setOutWeight(String.valueOf(pighistsale.get(i).getSale_weig())); //중량
			tmpDetail.setGradeCd(""); //등급			
			
			tmpt2List.add(tmpDetail);
			
			if (tmpHeader == null) {				
				tmpHeader = new OpenapiPighisSaleheaderVO();
				tmpHeader.setOutType("T1"); // 매출정보구분(T1:지육)
				tmpHeader.setOutYmd(pighistsale.get(i).getSale_date()); // 반출일자
				tmpHeader.setOutTypeNm("이력"); // 이력/묶음구분명
				tmpHeader.setPigNo(pighistsale.get(i).getHis_bunch_no()); // 이력번호
				tmpHeader.setLotNo(""); // 묶음번호
				tmpHeader.setGradeCd(""); // 등급
				tmpHeader.setOutCorpNo(pighistsale.get(i).getCust_regno()); // 판매처사업자등록번호
				tmpHeader.setOutCorpNm(pighistsale.get(i).getCust_name()); // 판매처 상호
				tmpHeader.setOutCorpOwnerNm(pighistsale.get(i).getCust_owner()); // 대표자
				tmpHeader.setOutCorpTel(pighistsale.get(i).getCust_phone()); // 연락처
				tmpHeader.setOutCorpAddr(pighistsale.get(i).getCust_addr());// 판매처 주소
				tmpHeader.setOutputPlaceNm("판매장"); // 판매처 유형
				tmpHeader.setProcessGbCd("416001"); //포장처리구분
				tmpHeader.setTotWeight(""); //총중량
			}
			
			tmpTotalWeight = tmpTotalWeight + pighistsale.get(i).getSale_weig(); //총중량 합
			
			//i값이 pighistsale Array 맨 마지막이거나,
			//i값이 pighistsale Array 맨 마지막이 아니면서 Array 다음 항목이 현재 tmpHeader 항목과 다를경우
			//Jason 객체 생성
			if ((i < pighistsale.size() - 1 && (!tmpHeader.getOutYmd().equals(pighistsale.get(i+1).getSale_date()) || !tmpHeader.getPigNo().equals(pighistsale.get(i+1).getHis_bunch_no()) || !tmpHeader.getOutCorpNo().equals(pighistsale.get(i+1).getCust_regno())))
					|| i == pighistsale.size() - 1) {
				
				JSONObject t = new JSONObject();
				
				
				tmpHeader.setTotWeight(String.valueOf(tmpTotalWeight));//총중량 합을 문자열로 변환
				
				OpenapiPighisSaleheaderVO t1 = tmpHeader.clone();
				tmpHeader = null;
				tmpTotalWeight = 0;
				
				ArrayList<OpenapiPighisSaledetailVO> t2List = (ArrayList<OpenapiPighisSaledetailVO>) tmpt2List.clone();
				tmpt2List.clear();
				
				t.put("T1", JSONObject.fromObject(t1));
				t.put("T2", JSONArray.fromObject(t2List));
				tArray.add(t);
			}
						
		}
		obj.put("item", tArray);	
		
		//System.out.println(obj.toString());
		
		return null;
		//return this.callOpenApi("SALE", info.getSiteUrl(), "application/json", obj.toString());
	}

	
	// 포장신고
	@SuppressWarnings("unchecked")
	@Override
	public List<OpenapiPighisResultVO> declarePighisPack(List<MpPighisPackVO> pighistpack) throws CloneNotSupportedException, IOException, ParseException {

		OpenapiCommonInfoVO info = openapiPigDao.getOpenApiInfoPack();

		JSONObject obj = new JSONObject();
		obj.put("userId", info.getUserId());
		obj.put("apiKey", info.getApiKey());
		//포장실적처리에서는 typeCode를 넘기지 않음.
		//obj.put("typeCode", info.getTypeCode());

		JSONArray tArray = new JSONArray();
		
		OpenapiPighisPackheaderVO tmpHeader = null;
		ArrayList<OpenapiPighisPackdetailT2VO> tmpt2List = new ArrayList<OpenapiPighisPackdetailT2VO>();
		ArrayList<OpenapiPighisPackdetailT3VO> tmpt3List = new ArrayList<OpenapiPighisPackdetailT3VO>();
		
		//전달받은 List 정렬
		Collections.sort(pighistpack, new MpPighisPackComparator());

		for (int i = 0; i < pighistpack.size(); i++) {
			
			OpenapiPighisPackdetailT2VO tmpDetail2 = new OpenapiPighisPackdetailT2VO();
			tmpDetail2.setInType("T2"); //부위정보구분(T2:지육)
			tmpDetail2.setPackingYmd(pighistpack.get(i).getPack_date()); //포장일자
			tmpDetail2.setPig_lot_No(pighistpack.get(i).getBunch_no()); //이력번호
			tmpDetail2.setPartCd(pighistpack.get(i).getBase_part_code()); //부위코드
			tmpDetail2.setPartNm(pighistpack.get(i).getBase_part_name()); //부위명
			tmpDetail2.setPackingWeight(String.format("%.1f", pighistpack.get(i).getPack_weig())); //중량
			
			tmpt2List.add(tmpDetail2);
			
			//이력/묶음구성정보(T3)
			//T1과 T3가 1:N이 될 수 있는데 T3 항목 값들이 중복되지 않아야 함.
			if (tmpt3List.size() ==0) {
				OpenapiPighisPackdetailT3VO tmpDetail3 = new OpenapiPighisPackdetailT3VO();
				tmpDetail3.setInType("T3"); ////이력/묶음구성(T3)
				tmpDetail3.setPackingYmd(pighistpack.get(i).getPack_date()); //포장일자
				tmpDetail3.setPig_lot_No(pighistpack.get(i).getBunch_no()); //이력번호
				tmpDetail3.setButCheryNo(""); //도체번호
				
				tmpt3List.add(tmpDetail3);
				
			}else{
				int existFlag = 0;
				for(OpenapiPighisPackdetailT3VO t3 : tmpt3List) {
					if(t3.getPackingYmd().equals(pighistpack.get(i).getPack_date()) 
					    && t3.getPig_lot_No().equals(pighistpack.get(i).getBunch_no())){
						existFlag = 1;
						break;
					}
				}
				
				if(existFlag ==0){
					OpenapiPighisPackdetailT3VO tmpDetail3 = new OpenapiPighisPackdetailT3VO();
					tmpDetail3.setInType("T3"); ////이력/묶음구성(T3)
					tmpDetail3.setPackingYmd(pighistpack.get(i).getPack_date()); //포장일자
					tmpDetail3.setPig_lot_No(pighistpack.get(i).getBunch_no()); //이력번호
					tmpDetail3.setButCheryNo(""); //도체번호
					
					tmpt3List.add(tmpDetail3);
				}
			}
			
			if (tmpHeader == null) {				
				tmpHeader = new OpenapiPighisPackheaderVO();
				tmpHeader.setInType("T1"); // 포장정보구분(T1)
				tmpHeader.setPackingYmd(pighistpack.get(i).getPack_date()); // 포장일자
				tmpHeader.setPig_lot_No(pighistpack.get(i).getBunch_no()); // 이력번호
				tmpHeader.setBrand(""); //브랜드
				tmpHeader.setInCropNm(""); //매입처상호
				tmpHeader.setInCropNo(""); //매입처사업자등록번호				
				tmpHeader.setGradeCd(""); // 등급코드
				tmpHeader.setCallCd("416001"); //신고구분
			}
			
			//i값이 pighistpack Array 맨 마지막이거나,
			//i값이 pighistpack Array 맨 마지막이 아니면서 Array 다음 항목이 현재 tmpHeader 항목과 다를경우
			//Jason 객체 생성
			if ((i < pighistpack.size() - 1 && (!tmpHeader.getPackingYmd().equals(pighistpack.get(i+1).getPack_date()) || !tmpHeader.getPig_lot_No().equals(pighistpack.get(i+1).getBunch_no())))
					|| i == pighistpack.size() - 1) {
				
				JSONObject t = new JSONObject();
				
				OpenapiPighisPackheaderVO t1 = tmpHeader.clone();
				tmpHeader = null;
				
				ArrayList<OpenapiPighisPackdetailT2VO> t2List = (ArrayList<OpenapiPighisPackdetailT2VO>) tmpt2List.clone();
				tmpt2List.clear();
				
				ArrayList<OpenapiPighisPackdetailT3VO> t3List = (ArrayList<OpenapiPighisPackdetailT3VO>) tmpt3List.clone();
				tmpt3List.clear();
				
				t.put("T1", JSONObject.fromObject(t1));
				t.put("T2", JSONArray.fromObject(t2List));
				t.put("T3", JSONArray.fromObject(t3List));
				tArray.add(t);
			}
						
		}
		obj.put("item", tArray);	
		
		//System.out.println(obj.toString());
		
		//return null;
		return this.callOpenApi("PACK", info.getSiteUrl(), "application/json", obj.toString());
	}

	// 축산물 이력제 신고 API
	public List<OpenapiPighisResultVO> callOpenApi(String pigFlag, String targetUrl,
			String contentType, String requestParam) throws IOException, ParseException {
		
		List<OpenapiPighisResultVO> result = new ArrayList<OpenapiPighisResultVO>();
		
		URL url = new URL(targetUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-type", contentType+"; charset=UTF-8");
		conn.setRequestProperty("Accept", contentType);
		conn.setRequestMethod("POST");
		
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(requestParam);
		wr.flush();
		
		StringBuilder sb = new StringBuilder();
		int HttpResult = conn.getResponseCode();
		if(HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String responseLine = null;
			while((responseLine = br.readLine()) != null) {
				sb.append(responseLine + " ");
			}
			br.close();
			
			System.out.println(sb.toString());
			JSONParser parser = new JSONParser();
			org.json.simple.JSONObject obj = (org.json.simple.JSONObject)parser.parse(sb.toString());
			
			org.json.simple.JSONObject arr = null;
			switch (pigFlag) {
			case "BUY":
				arr = (org.json.simple.JSONObject)obj.get("processInVo");
				break;
			case "PACK":
				arr = (org.json.simple.JSONObject)obj.get("processPackingVo");
				break;
			case "SALE":
				arr = (org.json.simple.JSONObject)obj.get("processOutVo");
				break;
			}
			
			//처리결과는 한건만 회신 됨.
			//오류가 있더라도 오류사항 최 상위 한건만 리턴됨.
			OpenapiPighisResultVO tmpResult = new OpenapiPighisResultVO();
			tmpResult.setCheckYn((String)arr.get("checkYn"));
			tmpResult.setCheckMsg((String)arr.get("checkMsg"));
			tmpResult.setReturnCd((String)arr.get("returnCd"));
			tmpResult.setPigNo((String)arr.get("pigNo"));
			result.add(tmpResult);
			
			
			/*
			 * 처리결과 한건만 회신되어 기존 회신결과 소스는 사용 불가.
			org.json.simple.JSONArray arr = null;
			switch (pigFlag) {
			case "BUY":
				arr = (org.json.simple.JSONArray)obj.get("processInVo");
				break;
			case "PACK":
				arr = (org.json.simple.JSONArray)obj.get("processPackingVo");
				break;
			case "SALE":
				arr = (org.json.simple.JSONArray)obj.get("processOutVo");
				break;
			}
			for(int i=0; i<arr.size(); i++){
				org.json.simple.JSONObject tmpObj = (org.json.simple.JSONObject)arr.get(i);
				OpenapiPighisResultVO tmpResult = new OpenapiPighisResultVO();
				tmpResult.setCheckYn((String)tmpObj.get("checkYn"));
				tmpResult.setCheckMsg((String)tmpObj.get("checkMsg"));
				tmpResult.setReturnCd((String)tmpObj.get("returnCd"));
				tmpResult.setPigNo((String)tmpObj.get("pigNo"));
				
				result.add(tmpResult);
			}
			*/
			
		}else{
			OpenapiPighisResultVO tmpResult = new OpenapiPighisResultVO();
			tmpResult.setCheckYn("N");
			tmpResult.setCheckMsg("축산물 이력신고 자료전송에 실패하였습니다");
			result.add(tmpResult);
		}
		
		for(OpenapiPighisResultVO t : result){
			System.out.println("checkYn : " + t.getCheckYn() + "\tcheckMsg : " + t.getCheckMsg() + "\treturnCd : "+ t.getReturnCd() + "\tpigNo : "+t.getPigNo());
		}
		
		return result;
	}
	
	//TEST용으로 생성한 것입니다.
	//구현시 제거해야 합니다.
	@Override
	public List<?> getPighisBuy(){
		
		return this.openapiPigDao.getPigBuy();
	}

	@Override
	public List<?> getPighisSale() {
		return this.openapiPigDao.getPigSale();
	}

	@Override
	public List<?> getPighisPack() {
		return this.openapiPigDao.getPigPack();
	}

}
