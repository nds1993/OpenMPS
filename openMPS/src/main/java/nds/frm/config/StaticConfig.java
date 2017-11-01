/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.frm.config;


/**
 * <p>Title: 정적변수 세팅 클래스</p>
 * <p>Description: Static변수를 관리하는 객체 StaticConfig.xml과 매칭</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: NDS</p>
 * @author 이선호
 * @version 1.0
 */
public class StaticConfig {
    
    /**
     * 연계시스템 테이블을 사용할지 여부
     */
    public static String PRODUCT_MODE = "info";    // debug or info
    /**
     * Quartz 스케쥴러 실행명
     */
    public static String PRODUCT_NAME = "NDS-RMS";

    /**
     * APP_INFO
     */
    public static String APP_COMPANY        = "(주)농심";
    public static String APP_DOMAIN         = "http://localhost:10080";
    public static String APP_IMAGE_DOMAIN   = "http://localhost:10080/image";
    public static String APP_TITLE          = "RMS운영시스템";
    public static String APP_CHMN_NAME      = "(주)농심 품질보증팀";  
    public static String APP_CHMN_CALL      = "080-023-5181";  
    public static String APP_DEP_CODE       = "1211";  

    /**
     * page 이미지 경로 URL
     */
    public static String CONTEXT_PATH       = "";

    /**
     * SKIN Info
     */
    public static String SKIN_VIEW          = "skin/default";
    public static String SKIN_IMAGE         = "/html/skin/default/images";
    public static String SKIN_CSS           = "/html/skin/default/css";
    
    /**
     * DB 계정 Info
     */
    public static final String DB_SERVER_USER     = "NACL";

    /**
     * 파일 FTP서버정보 1번 오류시 2번사용// kwas01, lwas01
     */
    public static final String FTP_DIR            = "";
    
    /**
     * 코드 FTP서버정보 // bbs1, bbs2
     */
    public static final String FTP_CODE_XML_DIR    = "";
    
    /**   
     * 홈페이지 게시판 첨부파일
     */
    public static final String HOMEPAGE_DOMAIN          = "";

    /**   
     * 파일업로드 관련
     */			
    public static final String APP_ROOT_DIR             = "C:/temp";
    public static final String APP_WEB_DIR              = APP_ROOT_DIR + "/inap/NDS-ERMS/src/main/webapp";
    
    
    /**
     * 이미지 업로드 경로
     */
    public static final String IMAGE_ATTACH_DIR         = APP_ROOT_DIR + "/upload/files";
    
    /**
     * 내부업로드 경로
     */
    public static final String ATTACH_DIR               = "/attach/files";
    public static final String ATTACH_TEMP_DIR          = APP_ROOT_DIR + "/attach/temp/";
    
    public static final String ATTACH_PIS_DIR          = APP_ROOT_DIR + "/attach/temp/";
    
    /**
     * [VOC] 관련
     */
    public static final String ATTACH_VOC_CODE          = "VOCFILE";
    public static final String ATTACH_VOC_CODE_ANS      = "VOCFILEANS";
    public static final String ATTACH_VOC_DIR    		= ATTACH_DIR+"/voc/";
    
    /**
     * [개선과제] 관련
     */
    public static final String ATTACH_PRJ_CODE          = "PRJFILE";
    public static final String ATTACH_PRJ_CODE_ANS      = "PRJFILEANS";
    public static final String ATTACH_NSTEST_CODE          = "MMDET200";
    public static final String ATTACH_PRJ_DIR           = ATTACH_DIR+"/prj/";
    
    /**
     * [검토요청] 관련
     */
    public static final String ATTACH_HELP_CODE         = "HELPFILE";
    public static final String ATTACH_HELP_DIR    		= ATTACH_DIR+"/help/";
    
    /**
     * [공지사항] 관련
     */
    public static final String ATTACH_NOTI_DIR           = ATTACH_DIR+"/noti/";
    
    /**
     * [템플릿] 관련
     */
    public static final String ATTACH_TMPL_CODE          = "TEMPLATE";
    public static final String ATTACH_TMPL_FILE_DIR      = "/template/";

    
    /**
     * [클레임 FACT SHEET] 관련
     */
    public static final String ATTACH_FACTSHEET_CODE         = "FACTSHEET";
    public static final String ATTACH_FACTSHEET_DIR           = ATTACH_DIR+"/fact/";
    
    /**
     * [클레임 사진] 관련
     */
    public static final String ATTACH_CLAIMPOTO_CODE         = "CLAIMPOTO";
    public static final String ATTACH_CLAIMPOTO_DIR           = ATTACH_DIR+"/claimpoto/";
    
    /**
     * [클레임 사진] 관련
     */
    public static final String ATTACH_RMSFILE_CODE         = "RMSFILE";
    public static final String ATTACH_RMSFILE_DIR           = ATTACH_DIR + "/rmsfile/";
    
    /**
     * [클레임 해명서] 관련
     */
    public static final String ATTACH_EXPLDOC_CODE         = "EXP";
    public static final String ATTACH_EXPLDOC_DIR           = ATTACH_DIR+"/exp/";
    
    /**
     * [클레임 기술검토보고서] 관련
     */
    public static final String ATTACH_EXPLREPRT_CODE         = "EXPLREPRT";
    public static final String ATTACH_EXPLREPRT_DIR           = ATTACH_DIR+"/explreprt/";
    
    /**
     * [클레임 분석서] 관련
     */
    public static final String ATTACH_ANALYDOC_CODE         = "SAM";
    public static final String ATTACH_ANALYDOC_DIR           = ATTACH_DIR+"/analydoc/";
   
    /**
     * [고려대학교 등록] 관련
     */
    public static final String ATTACH_SHAREDOC_CODE         = "SHAREDOC";
    public static final String ATTACH_SHAREDOC_DIR           = ATTACH_DIR+"/sharedoc/";
    
    /**
     * [식약청 등록] 관련
     */
    public static final String ATTACH_KFDADOC_CODE         = "KFDADOC";
    public static final String ATTACH_KFDADOC_DIR           = ATTACH_DIR+"/kfdadoc/";
    
    /**
     * 게시물 코드 정의
     */
    public static final String VOC_BOARD_NOTICE          = "NOTICE"; //공지사항
    
    /**
     * 만족도 설문조사화면 경로
     */
    public static final String SRVY_URL                  = APP_DOMAIN + "/op/vocsatisfaction.do";
    
    /**
     * SMS 발송시 발송자번호
     */
    public static final String SMS_FROM                  = "028207590";
    public static final String SMS_FROM_NM               = "(주)농심";

    /**
     * EMAIL 발송시 발송자번호
     */
    public static final String EMAIL_FROM                = "nsask@nongshim.com";
    public static final String EMAIL_FROM_NM             = "(주)농심";
    
    /**
     * PUSH 발송시 발송자번호
     */
    public static final String PUSH_FROM            = "00000";
    public static final String PUSH_FROM_NM         = "농심";
    
    /**
     * CCM 발송시 발송자번호
     */
    public static final String CCM_FROM            = "00000";
    public static final String CCM_FROM_NM         = "농심";
    
    public static final String PUSH_URL            = "http://203.249.160.88:8180/push_jdk1.6/ReceivePushLegacyHtml";
    
    public static final String GW_CREATE_SYSTEM   = "RMS_DEV";
    public static final String GW_SERVER          = "dev.nsgportal.net";
    public static final String GW_PORT            = "80";
    public static final String GW_START_URL       = "/ekp/eapp/nongshim/intra.do?cmpID=CN";
    public static final String GW_OPEN_URL        = "/ekp/user.do?cmpID=CN&cmd=goNsDraft";
    public static final String GW_PROT_ID         = "createDoc";
    public static final String GW_FILE_PATH       = "http://newintra.nongshim.com:7002/cms/co/file/cms_fileDown.ns?changeNm=37b295ed-a682-4aab-94d6-7cc177b7ea48.jpg&amp;orgnNm=nstalk_image_1398156952052.jpg&amp;filPath=/datanon/8002/WebApp/sal_svr/WEB-INF/works/cms/file/2014/";
    

    public static final String RMS101 = "NHAPR100";

    public static final String RMS102 = "NHAPR200";

    public static final String EA102 = "NEAPR080";

    public static final String EA103 = "NEAPR100";	
    
    public static final String VOC_PLAYER_URL = "http://172.25.1.89/recsee/interface/VocPlayer.php?file=";
    
    public static final String WEB_AGENT = "http://gwsso.nongshim.com:8000/redirectlogin/ns/ns_appl2015057.jsp";

    public static final String HP_ATCHFILE_URL = "http://www.nongshim.com/common/download?fileName=";
    
}
