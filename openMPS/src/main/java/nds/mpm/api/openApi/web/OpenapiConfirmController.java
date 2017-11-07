package nds.mpm.api.openApi.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
public class OpenapiConfirmController {
 
    public static final String KEY = "";	//발급필요
    public static final String URL = "http://data.ekape.or.kr/openapi-data/service/user/grade/confirm/pig";
    
    /** RoleService */
	@Resource(name = "openapiService")
	private OpenapiService openapiService;
    
    /**
     * 등급확인결과(확인서) 저장
     * @return String
     * @throws Exception
     */
    @RequestMapping("/openApi/openapiConfirm.do")
    public String openapiConfirm() throws Exception {
         
        return "/openApi/openapiConfirm";
    }
   
    /**
     * 등급확인결과(확인서) 검색 결과
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/Confirmlist.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String Confirmlist(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&issueNo=");
        queryUrl.append(searchTO.getIssueNo());    // 이력번호
        queryUrl.append("&issueDate=");
        queryUrl.append(searchTO.getIssueDate());  // 발급날짜

 
        // document 선언
        Document document = Jsoup.connect(queryUrl.toString()).get();

        String errorCode = document.select("resultcode").text();
        
        // 요청 결과가 정상일 경우 내용을 파싱하여 List 에 담는다.
        if ( "00".equals(errorCode) ) {
            org.jsoup.select.Elements elements = document.select("item");
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            for(Element element : elements){
            	OpenapiVO = new OpenapiVO();

            	OpenapiVO.setIssueNo(element.select("issueNo").text());               
            	OpenapiVO.setIssueDate(element.select("issueDate").text());         	         
            	OpenapiVO.setJudgeKindCd(element.select("judgeKindCd").text());             
            	OpenapiVO.setJudgeKindNm(element.select("judgeKindNm").text());                     
            	OpenapiVO.setRaterCode(element.select("raterCode").text());                     
            	OpenapiVO.setRaterNm(element.select("raterNm").text());			   		        

            	OpenapiVO.setAbattCode(element.select("abattCode").text());        
            	OpenapiVO.setMgrAbattNm(element.select("mgrAbattNm").text());         	 
            	OpenapiVO.setAbattAddr(element.select("abattAddr").text());      
            	OpenapiVO.setAbattTelNo(element.select("abattTelNo").text());      

            	OpenapiVO.setReqName(element.select("reqName").text());        
            	OpenapiVO.setReqRegNo(element.select("reqRegNo").text());         	  
            	OpenapiVO.setReqComName(element.select("reqComName").text()); 
                
            	OpenapiVO.setReqAddr(element.select("reqAddr").text());       
            	OpenapiVO.setReqTelno(element.select("reqTelno").text());         	  
            	OpenapiVO.setJudgeDate(element.select("judgeDate").text());     

            	OpenapiVO.setAbattNo2(element.select("abattNo2").text());       
            	OpenapiVO.setGradeCd(element.select("gradeCd").text());         	  
            	OpenapiVO.setGradeNm(element.select("gradeNm").text());     
            	OpenapiVO.setAbattWeight(element.select("abattWeight").text());       
            	OpenapiVO.setTotalWeight(element.select("totalWeight").text());         	  
            	OpenapiVO.setTotalIssueCnt(element.select("totalIssueCnt").text());    
                
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());     //성공코드
            	OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	   //성공메세지
                
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
     * 등급판정결과 저장(돼지)
     * @param searchTO
     * @return String
     * @throws Exception
     */
    @RequestMapping(value = "/openApi/Confirmsave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces="text/plain;charset=UTF-8")
    public @ResponseBody String Confirmsave(@ModelAttribute OpenapiSearchTO searchTO) throws Exception {
         
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 요청 URL 생성
        StringBuilder queryUrl = new StringBuilder();
        queryUrl.append(URL);
        queryUrl.append("?serviceKey=");
        queryUrl.append(KEY);
        queryUrl.append("&issueNo=");
        queryUrl.append(searchTO.getIssueNo());    // 이력번호
        queryUrl.append("&issueDate=");
        queryUrl.append(searchTO.getIssueDate());  // 발급날짜
 
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

            	OpenapiVO.setIssueNo(element.select("issueNo").text());               
            	OpenapiVO.setIssueDate(element.select("issueDate").text());         	         
            	OpenapiVO.setJudgeKindCd(element.select("judgeKindCd").text());             
            	OpenapiVO.setJudgeKindNm(element.select("judgeKindNm").text());                     
            	OpenapiVO.setRaterCode(element.select("raterCode").text());                     
            	OpenapiVO.setRaterNm(element.select("raterNm").text());			   		        

            	OpenapiVO.setAbattCode(element.select("abattCode").text());        
            	OpenapiVO.setMgrAbattNm(element.select("mgrAbattNm").text());         	 
            	OpenapiVO.setAbattAddr(element.select("abattAddr").text());      
            	OpenapiVO.setAbattTelNo(element.select("abattTelNo").text());      

            	OpenapiVO.setReqName(element.select("reqName").text());        
            	OpenapiVO.setReqRegNo(element.select("reqRegNo").text());         	  
            	OpenapiVO.setReqComName(element.select("reqComName").text()); 
                
            	OpenapiVO.setReqAddr(element.select("reqAddr").text());       
            	OpenapiVO.setReqTelno(element.select("reqTelno").text());         	  
            	OpenapiVO.setJudgeDate(element.select("judgeDate").text());     

            	OpenapiVO.setAbattNo2(element.select("abattNo2").text());       
            	OpenapiVO.setGradeCd(element.select("gradeCd").text());         	  
            	OpenapiVO.setGradeNm(element.select("gradeNm").text());     
            	OpenapiVO.setAbattWeight(element.select("abattWeight").text());       
            	OpenapiVO.setTotalWeight(element.select("totalWeight").text());         	  
            	OpenapiVO.setTotalIssueCnt(element.select("totalIssueCnt").text());    
                
            	OpenapiVO.setResultcode(successYn.select("resultcode").text());         //성공코드
                OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	        //성공메세지
                
                OpenapiVO.setOffi_syst("openAPI");                                      //대상시스템
                OpenapiVO.setIntr_usid("if_dod_1030");                                  //인터페이스ID
                OpenapiVO.setIntr_name("등급판정결과확인서연계");                                 //인터페이스명

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

                openapiService.insertConfirm(list);
        		openapiService.insertLog(list);
            	
            }
            // 요청 결과가 정상이 아닐 경우 에러 내용을 담는다.
        } else {
            org.jsoup.select.Elements successYn = document.select("header");
            
            List<OpenapiVO> list = new ArrayList<OpenapiVO>();
            OpenapiVO OpenapiVO = null;
            
            OpenapiVO = new OpenapiVO();
        	
            OpenapiVO.setResultcode(successYn.select("resultcode").text());           //성공코드
            OpenapiVO.setResultmsg(successYn.select("resultmsg").text());	          //성공메세지
        	
            OpenapiVO.setOffi_syst("openAPI");                                        //대상시스템(로그)        
            OpenapiVO.setIntr_usid("if_dod_1030");                                    //인터페이스ID(로그)
            OpenapiVO.setIntr_name("등급판정결과확인서연계실패");                                //인터페이스명(로그)
            
            list.add(OpenapiVO);

            paramMap.put("list", list);
    		openapiService.insertLog(list);
        }
        
        String mav = (new Gson()).toJson(paramMap);

        return(mav);
        
    }
    
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
        		OpenapiConfirmController.class);
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            context.close();
        }
    }
 
}