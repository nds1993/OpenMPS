package nds.mpm.api.openApi.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import nds.mpm.api.openApi.service.OpenapiSearchTO;
import nds.mpm.api.openApi.service.OpenapiService;
import nds.mpm.api.openApi.service.OpenapiVO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class OpenapiTraceController {
 
    public static final String KEY = "";	//발급필요
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/animalTrace/traceNoSearch";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;

    /**
     * 이력제 저장
     * @return String
     * @throws Exception
     */
    @RequestMapping("/openApi/openapiTrace.do")
    public String openapiTrace() throws Exception {
         
        return "/openApi/openapiTrace";
    }
   
    /**
     * 이력제 검색 결과
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/Tracelist.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String Tracelist(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&optionNo=");
        queryUrl.append("&corpNo=");
        queryUrl.append("&traceNo=");
        queryUrl.append(searchTO.getTraceNo());  // 이력번호
 
        // document 선언
        Document document = Jsoup.connect(queryUrl.toString()).get();

        String errorCode = document.select("resultcode").text();
        
        // 요청 결과가 정상일 경우 내용을 파싱하여 List 에 담는다.
        if ( "00".equals(errorCode) ) {
            org.jsoup.select.Elements elements = document.select("item");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            for(Element element : elements){
            	OpenapiVO = new OpenapiVO();

            	OpenapiVO.setTraceNoType(element.select("traceNoType").text());                //등급구분           
            	OpenapiVO.setInfoType(element.select("infoType").text());         	           //상세구분
            	OpenapiVO.setFarmUniqueNo(element.select("farmUniqueNo").text());              //농장식별번호
            	OpenapiVO.setFarmerNm(element.select("farmerNm").text());                      //농장경영자
            	OpenapiVO.setFarmAddr(element.select("farmAddr").text());                      //농장소재지
            	OpenapiVO.setPigNo(element.select("pigNo").text());			   		           //이력번호

            	OpenapiVO.setButcheryPlaceAddr(element.select("butcheryPlaceAddr").text());    //도축장주소    
            	OpenapiVO.setButcheryPlaceNm(element.select("butcheryPlaceNm").text());        //도축장명 	 
            	OpenapiVO.setButcheryYmd(element.select("butcheryYmd").text());      		   //도축일자
            	OpenapiVO.setInspectPassYn(element.select("inspectPassYn").text());            //위생검사결과

            	OpenapiVO.setGradeNm(element.select("gradeNm").text());        				   //등급명
            	OpenapiVO.setProcessPlaceAddr(element.select("processPlaceAddr").text());      //포장처리업소주소   	  
            	OpenapiVO.setProcessYmd(element.select("processYmd").text()); 				   //포장처리일자
                
            	OpenapiVO.setCorpNo(element.select("corpNo").text());       				   //사업자번호
            	OpenapiVO.setLotNo(element.select("lotNo").text());         	  			   //묶음번호
            	OpenapiVO.setProcessPlaceNm(element.select("processPlaceNm").text());     	   //포장처리업소명

                list.add(OpenapiVO);
            }
             
            paramMap.put("list", list);
             
         // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
        } else {
            String errorMessage = document.select("resultmsg").text();
             
            paramMap.put("resultcode", errorCode);
            paramMap.put("resultmsg", errorMessage);
        }
        
        String mav = (new Gson()).toJson(paramMap);
        	
        return(mav);
    }
    

	/**
     * 이력제 저장(돼지)
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/tracesave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String tracesave(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
        System.out.println(searchTO.getTraceNo()); //HttpServletRequest request
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&optionNo=");
        queryUrl.append("&corpNo=");
        queryUrl.append("&traceNo=");
        queryUrl.append(searchTO.getTraceNo());  // 이력번호
 
        // document 선언
        Document document = Jsoup.connect(queryUrl.toString()).get();
        
        String errorCode = document.select("resultcode").text();
      
      // 요청 결과가 정상일 경우 내용을 파싱하여 List 에 담는다.
      if ( "00".equals(errorCode)) {
            org.jsoup.select.Elements elements = document.select("item");
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            for(Element element : elements){
            	OpenapiVO = new OpenapiVO();

            	OpenapiVO.setTraceNoType(element.select("traceNoType").text());                //등급구분           
            	OpenapiVO.setInfoType(element.select("infoType").text());         	           //상세구분
            	OpenapiVO.setFarmUniqueNo(element.select("farmUniqueNo").text());              //농장식별번호
            	OpenapiVO.setFarmerNm(element.select("farmerNm").text());                      //농장경영자
            	OpenapiVO.setFarmAddr(element.select("farmAddr").text());                      //농장소재지
            	OpenapiVO.setPigNo(element.select("pigNo").text());			   		           //이력번호

            	OpenapiVO.setButcheryPlaceAddr(element.select("butcheryPlaceAddr").text());    //도축장주소    
            	OpenapiVO.setButcheryPlaceNm(element.select("butcheryPlaceNm").text());        //도축장명 	 
            	OpenapiVO.setButcheryYmd(element.select("butcheryYmd").text());      		   //도축일자
            	OpenapiVO.setInspectPassYn(element.select("inspectPassYn").text());            //위생검사결과

            	OpenapiVO.setGradeNm(element.select("gradeNm").text());        				   //등급명
            	OpenapiVO.setProcessPlaceAddr(element.select("processPlaceAddr").text());      //포장처리업소주소   	  
            	OpenapiVO.setProcessYmd(element.select("processYmd").text()); 				   //포장처리일자
                
            	OpenapiVO.setCorpNo(element.select("corpNo").text());       				   //사업자번호
            	OpenapiVO.setLotNo(element.select("lotNo").text());         	  			   //묶음번호
            	OpenapiVO.setProcessPlaceNm(element.select("processPlaceNm").text());     	   //포장처리업소명
                
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());   			   //성공코드
                OpenapiVO.setResultmsg(successYn.select("resultmsg").text());			       //성공메세지
                
                OpenapiVO.setOffi_syst("openAPI");                                 			   //대상시스템
                OpenapiVO.setIntr_usid("if_dod_1020");                                         //인터페이스ID
                OpenapiVO.setIntr_name("축산물이력제연계");                                           //인터페이스명

                list.add(OpenapiVO);
            }
            
            
            if(list.size() < 1){ 
            	
            	OpenapiVO = new OpenapiVO();
            	
            	OpenapiVO.setOffi_syst("openAPI");                                    //대상시스템
            	OpenapiVO.setIntr_usid("if_dod_1080");                                //인터페이스ID
            	OpenapiVO.setIntr_name("권역별경락가격현황연계");                               //인터페이스명
            	OpenapiVO.setResultcode("errore");							          //성공코드
            	OpenapiVO.setResultmsg("nodata");                            	      //성공메세지
            	
            	list.add(OpenapiVO);
            
                paramMap.put("list", list);

        		openapiService.insertLog(list);
            }else{

            	paramMap.put("list", list);

                openapiService.insertTrace(list);
        		openapiService.insertLog(list);
        	} 
        // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
        } else {
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            OpenapiVO = new OpenapiVO();
        	
            OpenapiVO.setResultcode(successYn.select("resultcode").text());                   //성공코드
            OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	                  //성공메세지
        	
            OpenapiVO.setOffi_syst("openAPI");                                                //대상시스템(로그)        
            OpenapiVO.setIntr_usid("if_dod_1020");                                            //인터페이스ID(로그)
            OpenapiVO.setIntr_name("축산물이력제연계실패");                                            //인터페이스명(로그)
            
            list.add(OpenapiVO);

            paramMap.put("list", list);
    		openapiService.insertLog(list);
        }
        
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
        
    }
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiTraceController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
 
}