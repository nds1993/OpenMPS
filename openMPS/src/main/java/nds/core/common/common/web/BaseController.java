package nds.core.common.common.web;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import nds.core.common.common.service.AttachFileVO;
import nds.core.common.common.service.AttachfileService;
import nds.core.common.common.service.Authority;
import nds.core.common.common.service.CommonService;
import nds.core.common.common.service.UserMenu;
import nds.core.common.common.service.UserSession;
import nds.core.operation.voccnsltype.service.VocCnslTypeVO;
import nds.core.systemsettings.code.service.CodeVO;
import nds.frm.config.StaticConfig;
import nds.frm.startup.SYSTEM;
import nds.frm.util.FTPUtil;
import nds.frm.util.FileCopier;
import nds.frm.util.SFTPUtil;
import nds.frm.util.StringUtil;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;



/**
 * <p>Title: BaseController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
@Controller
public class BaseController extends MultiActionController {

	/** CommonService */
    @Resource(name = "commonService")
    private CommonService commonService;
    
    public void replaceAuthority(HttpServletRequest request, String contsId, String UrlPath)  throws Exception {

        Authority authority = new Authority();
        List<String> roleList = new ArrayList<String>();

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userId = userSession.getLogin().getUserId();

        UserMenu key = new UserMenu();
        key.setRootConts("voc");
        key.setId(userId);
        key.setDepCd(userSession.getLogin().getDepCd());
        key.setContsId(contsId);
        key.setUrlPath(UrlPath);
        List list = commonService.selectMenuInfo(key);
        request.getSession(true).setAttribute(userId + UrlPath, list);

        if (list != null && list.size() > 0) {
            UserMenu menu = (UserMenu) list.get(0);

            String[] roles = menu.getRoleCd().split("[;]");

            for (int i = 0; i < roles.length; i++) {
                roleList.add(roles[i]);
                if (!authority.getROLE_CD().containsKey(roles[i])) {
                    authority.getROLE_CD().put(roles[i], "Y");
                }
            }
            authority.setROLE_LIST(roleList);

            String[] buttonNames = StringUtil.null2void(menu.getBtnNm()).split("[;]");
            for (int i = 0; i < buttonNames.length; i++) {
                if (!authority.getBUTTON_NM().containsKey(buttonNames[i])) {
                    authority.getBUTTON_NM().put(buttonNames[i], "Y");
                }
            }
            
            String[] imageCodes = StringUtil.null2void(menu.getImgeCd()).split("[|]");
            for (int i = 0; i < imageCodes.length; i++) {
                if (!authority.getBUTTON_CD().containsKey(buttonNames[i])) {
                    authority.getBUTTON_CD().put(buttonNames[i], imageCodes[i]);
                }
            }

            String[] buttonIds = StringUtil.null2void(menu.getBtnId()).split("[;]");
            for (int i = 0; i < buttonIds.length; i++) {
                if (!authority.getBUTTON_ID().containsKey(buttonNames[i])) {
                    authority.getBUTTON_ID().put(buttonNames[i], buttonIds[i]);
                }
            }

            request.getSession(true).setAttribute(contsId, authority);

        }         
    }
    public Authority getAuthority(HttpServletRequest request, String contsId, String UrlPath)  throws Exception {

        Authority authority = null;
        List<String> roleList = new ArrayList<String>();

        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userId = userSession.getLogin().getUserId();

        UserMenu key = new UserMenu();
        key.setRootConts("voc");
        key.setId(userId);
        key.setDepCd(userSession.getLogin().getDepCd());
        key.setContsId(contsId);
        key.setUrlPath(UrlPath);
        List list = commonService.selectMenuInfo(key);

        if (list != null && list.size() > 0) {
            authority = new Authority();
            UserMenu menu = (UserMenu) list.get(0);

            String[] roles = menu.getRoleCd().split("[;]");

            for (int i = 0; i < roles.length; i++) {
                roleList.add(roles[i]);
                if (!authority.getROLE_CD().containsKey(roles[i])) {
                    authority.getROLE_CD().put(roles[i], "Y");
                }
            }
            authority.setROLE_LIST(roleList);

            String[] buttonNames = StringUtil.null2void(menu.getBtnNm()).split("[;]");
            for (int i = 0; i < buttonNames.length; i++) {
                if (!authority.getBUTTON_NM().containsKey(buttonNames[i])) {
                    authority.getBUTTON_NM().put(buttonNames[i], "Y");
                }
            }

            String[] imageCodes = StringUtil.null2void(menu.getImgeCd()).split("[|]");
            for (int i = 0; i < imageCodes.length; i++) {
                if (!authority.getBUTTON_CD().containsKey(buttonNames[i])) {
                    authority.getBUTTON_CD().put(buttonNames[i], imageCodes[i]);
                }
            }
            
            String[] buttonIds = StringUtil.null2void(menu.getBtnId()).split("[;]");
            for (int i = 0; i < buttonIds.length; i++) {
                if (!authority.getBUTTON_ID().containsKey(buttonNames[i])) {
                    authority.getBUTTON_ID().put(buttonNames[i], buttonIds[i]);
                }
            }
        }         
        return authority;
    } 
    public Map getMenuUserRoleCode(String userId, String depCd, String contsId)  throws Exception {

        Map<String, String> ROLE_CD = new HashMap<String, String>(); 

        UserMenu key = new UserMenu();
        key.setRootConts("voc");
        key.setId(userId);
        key.setDepCd(depCd);
        key.setContsId(contsId);
        List list = commonService.selectCstContentRoleInfo(key);

        if (list != null && list.size() > 0) {
            UserMenu menu = (UserMenu) list.get(0);

            String[] roles = menu.getRoleCd().split("[;]");

            for (int i = 0; i < roles.length; i++) {
                if (!ROLE_CD.containsKey(roles[i])) {
                    ROLE_CD.put(roles[i], "Y");
                }
            }
        }         
        return ROLE_CD;
    }    
    public void loadDate() {
        Statement stmt = null;
        Connection conn = null;
        try {
            conn = SYSTEM.getInstance().getConnection();
            String strQuery  = "";
            //ORACLE 일경우
            //strQuery = strQuery + " SELECT TO_CHAR(SYSDATE,'YYYYMMDD'), TO_CHAR(SYSDATE,'YYYY'), TO_CHAR(SYSDATE,'MM'), TO_CHAR(SYSDATE,'DD'), TO_CHAR(SYSDATE,'HH24MISS'), TO_CHAR(SYSDATE,'AM'), TO_CHAR(SYSDATE,'HH12'), TO_CHAR(SYSDATE,'MI'), TO_CHAR(SYSDATE,'SS') ";
            //strQuery = strQuery + " , CASE TO_CHAR( SYSDATE, 'D') WHEN '1' THEN '일' WHEN '2' THEN '월' WHEN '3' THEN '화' WHEN '4' THEN '수' WHEN '5' THEN '목' WHEN '6' THEN '금' WHEN '7' THEN '토' END CASE, TO_CHAR(SYSDATE,'HH24') ";
            //strQuery = strQuery + " FROM DUAL ";
            
            //POSTGRESQL 일경우
            strQuery = strQuery + " SELECT TO_CHAR(NOW(),'YYYYMMDD'), TO_CHAR(NOW(),'YYYY'), TO_CHAR(NOW(),'MM'), TO_CHAR(NOW(),'DD'), TO_CHAR(NOW(),'HH24MISS'), TO_CHAR(NOW(),'AM'), TO_CHAR(NOW(),'HH12'), TO_CHAR(NOW(),'MI'), TO_CHAR(NOW(),'SS') ";
            strQuery = strQuery + " , CASE TO_CHAR(NOW(), 'D') WHEN '1' THEN '일' WHEN '2' THEN '월' WHEN '3' THEN '화' WHEN '4' THEN '수' WHEN '5' THEN '목' WHEN '6' THEN '금' WHEN '7' THEN '토' END, TO_CHAR(NOW(),'HH24') ";
            
            stmt = conn.createStatement();
            stmt.execute(strQuery);
            
            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                SYSTEM system = SYSTEM.getInstance();
                system.setDate(rs.getString(1));
                system.setYear(rs.getString(2));
                system.setMonth(rs.getString(3));
                system.setDay(rs.getString(4));
                system.setTime(rs.getString(5));
                system.setAmpm(rs.getString(6));
                system.setHour(rs.getString(7));
                system.setMinute(rs.getString(8));
                system.setSecond(rs.getString(9));
                system.setYoil(rs.getString(10));
                system.setHour24(rs.getString(11));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if(null != stmt) stmt.close();
                if(null != conn) conn.close();
                conn = null;
            } catch (Exception e) {
            }
        }
    }
    
    /**
     * 첨부파일 수정
     * @param service
     * @param cntnId
     * @param srNo
     * @param texts
     * @param values
     */
    public void updateAttachFile(AttachfileService service, String cntnId, String docRegNo, String texts, String values, String toDir, String ftpDiv) throws Exception {
        updateAttachFile(service, cntnId, docRegNo, texts, values, StaticConfig.ATTACH_TEMP_DIR, toDir + SYSTEM.getInstance().getDate() + "/", ftpDiv);
    }
   
    /**
     * 첨부파일 수정
     * @param service
     * @param cntnId
     * @param srNo
     * @param texts
     * @param values
     * @param fromDir
     * @param toDir
     */
    public void updateAttachFile(AttachfileService service, String cntnId, String docRegNo, String texts, String values, String fromDir, String toDir, String ftpDiv) throws Exception {
    	
        Connection conn = SYSTEM.getInstance().getConnection();
        Statement stmt = null;
        
        try {
            stmt = conn.createStatement();
            String sql = "select ATCH_FILE_PATH || ATCH_FILE_CHNG_NAME as FILENAME from CLT_ATCHFILE where CONTS_ID = '" + cntnId + "' and DOC_REG_NO = '" + docRegNo + "' order by ATCH_FILE_CHNG_NAME ";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                File realFile = new File(StaticConfig.APP_ROOT_DIR + rs.getString("FILENAME"));
                if(realFile.exists()) {
                    realFile.delete();
                }
            }
//            if("FTP".equals(ftpDiv)){
//                ftp1.init(host1, id, password, port);  //connect
//                ftp2.init(host2, id, password, port);
//            }
//            else if("SFTP".equals(ftpDiv)){
//                FtpAdapter ftpAdapter = new FtpAdapter();
//                
//                if (ftpAdapter.connect("172.21.2.109", 7775) == false)
//                {
//                    System.out.println("SFTPD 연결실패");
//                }
//                else{
//                    while (rs.next()) {
//                        File realFile = new File(StaticConfig.APP_ROOT_DIR + rs.getString("FILENAME"));
//                        localFileList.add(StaticConfig.ATTACH_TEMP_DIR + rs.getString("FILEPATH") + rs.getString("FILENAME"));
//                        remoteFileNameList.add(StaticConfig.WEBTOBE_HOME + StaticConfig.ATTACH_VOC_DIR + rs.getString("FILEPATH") + rs.getString("FILENAME"));
//                        if(realFile.exists()) {
//                            realFile.delete();
//                        }
//                    }
//                    try {
//                        ftpAdapter.download(localFileList, remoteFileNameList);
//                        ftpAdapter.disconnect();
//                    }
//                    catch(Exception e) {
//                        ftpAdapter.disconnect();
//                        e.printStackTrace();
//                    }
//                    
//                }
//            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if(null != stmt) stmt.close();
                if(null != conn) conn.close();
                conn = null;
            } catch (Exception e) {
            }
        }

        // 첨부화일 삭제
        AttachFileVO key = new AttachFileVO();
        key.setContsId(cntnId);
        key.setDocRegNo(docRegNo);
        service.deleteAttachFile(key);
        
        String dir = StaticConfig.APP_ROOT_DIR + toDir;
        
        //내부용
        if("FTP".equals(ftpDiv)){
        	
        	//2015-08-17 폴더에 바로 생성하므로 insert만 함
        	// 첨부화일 생성
            if(texts != null && values != null)
            {
                StringTokenizer stExistTexts    = new StringTokenizer(texts, "|");
                StringTokenizer stExistValues   = new StringTokenizer(values, "|");
                
                try {
//                    ftp1.init(host1, id, password, port);  //connect
//                    ftp2.init(host2, id, password, port);
                    while(stExistTexts.hasMoreTokens())
                    {
                        String strExistText = stExistTexts.nextToken();
                        String strExistValue= stExistValues.nextToken();
                        String fromFileName = fromDir + strExistValue;
                        String orgFileExt = "";
                        StringTokenizer fileExtToken = new StringTokenizer(strExistText, ".", false);
                        while (fileExtToken.hasMoreTokens()) {
                            orgFileExt = fileExtToken.nextToken();
                        }
                        String toFileName = StaticConfig.APP_ROOT_DIR + toDir + strExistValue;
                        
                        File fromFile = new File(fromFileName);
                        if(fromFile.exists()) {
                            AttachFileVO r0 = new AttachFileVO();
                            r0.setContsId(cntnId);
                            r0.setDocRegNo(docRegNo);
                            r0.setAtchFileChngName(strExistValue);
                            r0.setAtchDataPath(toDir);
                            r0.setAtchOtxtFileName(strExistText);
                            r0.setAtchFileSize(String.valueOf(fromFile.length()));
                            r0.setAtchFileExt(orgFileExt);

                            service.insertAttachFile(r0);
                            
//                            ftp1.upload(dir, fromFile);  //upload
//                            ftp2.upload(dir, fromFile);
                            
                            File fromFileDir = new File(fromDir);
                            if ( !fromFileDir.exists()) fromFileDir.mkdirs();
                            File toFileDir = new File(StaticConfig.APP_ROOT_DIR + toDir);
                            if ( !toFileDir.exists()) toFileDir.mkdirs();
                            
                            FileCopier.copy(fromFileName, toFileName);
                            
                            service.deleteTempFile(strExistValue);
                            fromFile.delete();
//                            ftp1.delete(fromFileName +"."+ orgFileExt);  //temp file delete
//                            ftp2.delete(fromFileName +"."+ orgFileExt);
                        }
                    }
//                    ftp1.disconnection();
//                    ftp2.disconnection();
                }
                catch(Exception e) {
//                   ftp1.disconnection();
//                   ftp2.disconnection();
                    e.printStackTrace();
                }

            }
        }	//외부용
        else if("SFTP".equals(ftpDiv)){
        	
        	String sftpKwasDir  = StaticConfig.FTP_DIR;
        	
        	String dirHome = sftpKwasDir;
          
    		if(cntnId.equals(StaticConfig.ATTACH_VOC_CODE)){
    			dirHome = dirHome + "vocqstn/";
    		}
    		else if(cntnId.equals(StaticConfig.ATTACH_VOC_CODE_ANS)){
    			dirHome = dirHome + "vocansw/";
    		}
    		else if(cntnId.equals(StaticConfig.VOC_BOARD_NOTICE)){
    			dirHome = dirHome + "vocnoti/";
    		}
        	
        	// 2015-10-19 폴더에 바로 생성하므로 insert만 함
        	// 첨부화일 생성
            if(texts != null && values != null)
            {
                StringTokenizer stExistTexts    = new StringTokenizer(texts, "|");
                StringTokenizer stExistValues   = new StringTokenizer(values, "|");
                
                try {
                    while(stExistTexts.hasMoreTokens())
                    {
                        String strExistText = stExistTexts.nextToken();
                        String strExistValue= stExistValues.nextToken();
                        String fromFileName = fromDir + strExistValue;
                        String orgFileExt = "";
                        StringTokenizer fileExtToken = new StringTokenizer(strExistText, ".", false);
                        while (fileExtToken.hasMoreTokens()) {
                            orgFileExt = fileExtToken.nextToken();
                        }
                        String toFileName = StaticConfig.APP_ROOT_DIR + toDir + strExistValue;
                        
                        File fromFile = new File(fromFileName);
                        if(fromFile.exists()) {
                            AttachFileVO r0 = new AttachFileVO();
                            r0.setContsId(cntnId);
                            r0.setDocRegNo(docRegNo);
                            r0.setAtchFileChngName(strExistValue);
                            r0.setAtchDataPath(toDir);
                            r0.setAtchOtxtFileName(strExistText);
                            r0.setAtchFileSize(String.valueOf(fromFile.length()));
                            r0.setAtchFileExt(orgFileExt);

                            service.insertAttachFile(r0);
                            
                            File fromFileDir = new File(fromDir);
                            if ( !fromFileDir.exists()) fromFileDir.mkdirs();
                            File toFileDir = new File(StaticConfig.APP_ROOT_DIR + toDir);
                            if ( !toFileDir.exists()) toFileDir.mkdirs();
                            
                            FileCopier.copy(fromFileName, toFileName);
                            FileCopier.copy(fromFileName, dirHome + strExistValue);
                            
                            service.deleteTempFile(strExistValue);
                            fromFile.delete();
                        }
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            }
            
//        	SFTPUtil util1 = new SFTPUtil();
//            SFTPUtil util2 = new SFTPUtil();
//            // 첨부화일 생성
//            if(texts != null && values != null)
//            {
//                StringTokenizer stExistTexts    = new StringTokenizer(texts, "|");
//                StringTokenizer stExistValues   = new StringTokenizer(values, "|");
//                String tempFile = "";
//                try {
//                	util1.init(host1, id, password, 7191);
//                    util2.init(host2, id, password, 7191);
//                    
//                    ftp1.init(host1, id, password, port);  //connect
//                    ftp2.init(host2, id, password, port);
//                    
//                    while(stExistTexts.hasMoreTokens())
//                    {
//                        String strExistText = stExistTexts.nextToken();
//                        String orgFileExt = "";
//                        StringTokenizer fileExtToken = new StringTokenizer(strExistText, ".", false);
//                        while (fileExtToken.hasMoreTokens()) {
//                            orgFileExt = fileExtToken.nextToken();
//                        }
//                        
//                        String strExistValue= stExistValues.nextToken();
//                        //확장자추가
//                        String fromFileName = fromDir + strExistValue +"."+ orgFileExt;
//                        tempFile = fromFileName;
//                        
//                        //System.out.println("setAtchFileChngName : " + strExistValue+"."+ orgFileExt);
//                        
//                        File fromFile = new File(fromFileName);
//                        if(fromFile.exists()) {
//                            AttachFileVO r0 = new AttachFileVO();
//                            r0.setContsId(cntnId);
//                            r0.setDocRegNo(docRegNo);
//                            r0.setAtchFileChngName(strExistValue+"."+ orgFileExt);
//                            r0.setAtchDataPath(toDir);
//                            r0.setAtchOtxtFileName(strExistText);
//                            r0.setAtchFileSize(String.valueOf(fromFile.length()));
//                            r0.setAtchFileExt(orgFileExt);
//
//                            service.insertAttachFile(r0);
//
//                            localFileList.add(fromDir + strExistValue + "." +orgFileExt);       
//                            remoteFileNameList.add(strExistValue+ "." +orgFileExt);
//                            remoteFilePathList.add(toDir);
//                        }
//                        
//                        boolean upload1 = util1.upload(dir, fromFile);
//                        boolean upload2 = util2.upload(dir, fromFile);
//                        
//                    }
//                    //외부 웹서버로 파일 전송한다.
//                    //Attach attach = new Attach();
//                    //attach.distribute(localFileList, remoteFileNameList, remoteFilePathList);
//                    
//                    ftp1.delete(tempFile);  //temp file delete
//                    ftp2.delete(tempFile);
//                    
//                    util1.disconnection();
//                    util2.disconnection();
//                    
//                    ftp1.disconnection();
//                    ftp2.disconnection();
//                }
//                catch(Exception e) {
//                	util1.disconnection();
//                    util2.disconnection();
//                    
//                    ftp1.disconnection();
//                    ftp2.disconnection();
//                     e.printStackTrace();
//                }
//            }
        }
    }
    /**
     * 첨부파일 수정
     * @param service
     * @param cntnId
     * @param srNo
     * @param texts
     * @param values
     * @param fromDir
     * @param toDir
     */
    public void updateOrignalFile(AttachfileService service, String cntnId, String docRegNo, String texts, String values, String fromDir, String toDir) throws Exception {
        // 첨부파일 정보 변경
        // 첨부정보 업데이트

        // 물리적 저장소 파일삭제
        Connection conn = SYSTEM.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT ATCH_FILE_PATH || ATCH_FILE_CHNG_NAME || '.' || ATCH_FILE_EXT as FILENAME FROM CLT_ATCHFILE WHERE CONTS_ID = '" + cntnId + "' AND DOC_REG_NO = '" + docRegNo + "' ORDER BY ATCH_FILE_CHNG_NAME ";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                File realFile = new File(StaticConfig.APP_ROOT_DIR + rs.getString("FILENAME"));
                if(realFile.exists()) {
                    realFile.delete();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if(null != stmt) stmt.close();
                if(null != conn) conn.close();
                conn = null;
            } catch (Exception e) {
            }
        }
        
        // 첨부화일 삭제
        AttachFileVO key = new AttachFileVO();
        key.setContsId(cntnId);
        key.setDocRegNo(docRegNo);
        service.deleteAttachFile(key);
        
        // 첨부화일 생성
        if(texts != null && values != null)
        {
            StringTokenizer stExistTexts    = new StringTokenizer(texts, "|");
            StringTokenizer stExistValues   = new StringTokenizer(values, "|");
            
            while(stExistTexts.hasMoreTokens())
            {
                String strExistText = stExistTexts.nextToken();
                String strExistValue= stExistValues.nextToken();
                String fromFileName = fromDir + strExistValue;
                String orgFileExt = "";
                StringTokenizer fileExtToken = new StringTokenizer(strExistText, ".", false);
                while (fileExtToken.hasMoreTokens()) {
                    orgFileExt = fileExtToken.nextToken();
                }
                String toFileName   = StaticConfig.APP_ROOT_DIR + toDir + strExistValue + "." + orgFileExt;

                //System.out.println("fromFileName : " + fromFileName);
                
                File fromFile = new File(fromFileName);
                if(fromFile.exists()) {
                    AttachFileVO r0 = new AttachFileVO();
                    r0.setContsId(cntnId);
                    r0.setDocRegNo(docRegNo);
                    r0.setAtchFileChngName(strExistValue);
                    r0.setAtchDataPath(toDir);
                    r0.setAtchOtxtFileName(strExistText);
                    r0.setAtchFileSize(String.valueOf(fromFile.length()));
                    r0.setAtchFileExt(orgFileExt);

                    service.insertAttachFile(r0);

                    File fromFileDir = new File(fromDir);
                    if ( !fromFileDir.exists()) fromFileDir.mkdir();
                    File toFileDir = new File(StaticConfig.APP_ROOT_DIR + toDir);
                    if ( !toFileDir.exists()) toFileDir.mkdir();

                    FileCopier.copy(fromFileName, toFileName);

                    service.deleteTempFile(strExistValue);
                    fromFile.delete();
                }
            }
        }
    }
    
    /**
     * 첨부파일 문서등록번호 수정
     * @param service
     * @param cntnId
     * @param srNo
     * @param texts
     * @param values
     */
    public void replaceAttachFile(String cntnId, String oldDocRegNo, String newDocRegNo) {
        Connection conn = SYSTEM.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "UPDATE CLT_ATCHFILE SET DOC_REG_NO = '" + newDocRegNo + "' WHERE CONTS_ID = '" + cntnId + "' AND DOC_REG_NO = '" + oldDocRegNo + "' ";
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if(null != stmt) stmt.close();
                if(null != conn) conn.close();
                conn = null;
            } catch (Exception e) {
            }
        }
    } 

    /**
     * 첨부파일 삭제
     * @param service
     * @param cntnId
     * @param srNo
     * @param texts
     * @param values
     */
    public void deleteAttachFile(AttachfileService service, String cntnId, String docRegNo) throws Exception {

        Connection conn = SYSTEM.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT ATCH_FILE_PATH || ATCH_FILE_CHNG_NAME as FILENAME FROM CLT_ATCHFILE WHERE CONTS_ID = '" + cntnId + "' AND DOC_REG_NO = '" + docRegNo + "' ORDER BY ATCH_FILE_CHNG_NAME ";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                File realFile = new File(StaticConfig.ATTACH_DIR + rs.getString("FILENAME"));
                if(realFile.exists()) {
                    realFile.delete();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if(null != stmt) stmt.close();
                if(null != conn) conn.close();
                conn = null;
            } catch (Exception e) {
            }
        }
        
        // 첨부화일 삭제
        AttachFileVO key = new AttachFileVO();
        key.setContsId(cntnId);
        key.setDocRegNo(docRegNo);
        service.deleteAttachFile(key);
    }     

    /**
     * 첨부파일 삭제
     * @param service
     * @param cntnId
     * @param srNo
     * @param texts
     * @param values
     */
    public void deleteOriginalFile(AttachfileService service, String cntnId, String docRegNo) throws Exception {

        Connection conn = SYSTEM.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT ATCH_FILE_PATH || ATCH_OTXT_FILE_NAME as FILENAME FROM CLT_ATCHFILE WHERE CONTS_ID = '" + cntnId + "' AND DOC_REG_NO = '" + docRegNo + "' ORDER BY ATCH_FILE_CHNG_NAME ";
            stmt.execute(sql);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                File realFile = new File(StaticConfig.APP_ROOT_DIR + rs.getString("FILENAME"));
                if(realFile.exists()) {
                    realFile.delete();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if(null != stmt) stmt.close();
                if(null != conn) conn.close();
                conn = null;
            } catch (Exception e) {
            }
        }
        
        // 첨부화일 삭제
        AttachFileVO key = new AttachFileVO();
        key.setContsId(cntnId);
        key.setDocRegNo(docRegNo);
        service.deleteAttachFile(key);
    } 
    
    /**
     * 첨부파일 FTP 등록
     * @param service
     * @param cntnId
     * @param srNo
     * @param texts
     * @param values
     */
    public void transFTPFiles(AttachfileService service, String contsId, String docRegNo) throws Exception {
    	String sftpKwasDir  = StaticConfig.FTP_DIR;
    	
    	String dir = sftpKwasDir;
      
		if(contsId.equals(StaticConfig.ATTACH_VOC_CODE)){
		    dir = dir + "vocqstn";
		}
		else if(contsId.equals(StaticConfig.ATTACH_VOC_CODE_ANS)){
		    dir = dir + "vocansw";
		}
		else if(contsId.equals(StaticConfig.VOC_BOARD_NOTICE)){
		    dir = dir + "vocnoti";
		}
    	
    	try{
			String s = null;
			BufferedReader in =  null;

			List attach = service.getAttachFileList(docRegNo, contsId);
            
            Iterator iter = attach.iterator();
            
            while(iter.hasNext()) {
                AttachFileVO file = (AttachFileVO) iter.next();

                String fileName = StringUtil.null2void(file.getAtchFileChngName());
                
                // shell 실행
    			Process proc = Runtime.getRuntime().exec("/tim/shell/jshell/jshell.sh j_dep.sh wtsdev2 " + dir + " " + fileName);

    			in =  new BufferedReader (new InputStreamReader(proc.getInputStream()));
    			while ((s = in.readLine ())!= null){
             	  	break;
            	}
    			
            }
            LogManager.getRootLogger().debug("정상적으로 동기화 되었습니다.");
        }
        catch(Exception e){
        	LogManager.getRootLogger().debug("명령 수행중 에러가 발생하였습니다");
            e.printStackTrace();
        }
    	
//        String sftpKwasIp   = StaticConfig.FTP_KWAS_IP;
//        String sftpKwasUser = StaticConfig.FTP_KWAS_USER;
//        String sftpKwasPwd  = StaticConfig.FTP_KWAS_PWD;
//        String sftpKwasDir  = StaticConfig.FTP_KWAS_DIR;
//        
//        String dir = sftpKwasDir;
//        
//        if(contsId.equals(StaticConfig.ATTACH_VOC_CODE)){
//            dir = dir + "vocqstn/";
//        }
//        else if(contsId.equals(StaticConfig.ATTACH_VOC_CODE_ANS)){
//            dir = dir + "vocansw/";
//        }
//        else if(contsId.equals(StaticConfig.VOC_BOARD_NOTICE)){
//            dir = dir + "vocnoti/";
//        }
//        
//        FTPUtil util = new FTPUtil();
//        
//        try{
//            List attach = service.getAttachFileList(docRegNo, contsId);
//            
//            util.init(sftpKwasIp, sftpKwasUser, sftpKwasPwd, 21);
//            
//            Iterator iter = attach.iterator();
//            while(iter.hasNext()) {
//                AttachFileVO file = (AttachFileVO) iter.next();
//
//                String fileName = StringUtil.null2void(file.getAtchFileChngName());
//                File fromFile = new File(StaticConfig.APP_ROOT_DIR + file.getAtchDataPath() + fileName);
//                
//                util.upload(dir, fromFile);
//            }
//            
//            util.disconnection();
//        }
//        catch (Exception e) {
//
//            String sftpKwasIp2   = StaticConfig.FTP_KWAS_IP2;
//            String sftpKwasUser2 = StaticConfig.FTP_KWAS_USER2;
//            String sftpKwasPwd2  = StaticConfig.FTP_KWAS_PWD2;
//            
//            FTPUtil util2 = new FTPUtil();
//            
//        	try{
//                List attach = service.getAttachFileList(docRegNo, contsId);
//                
//                util2.init(sftpKwasIp2, sftpKwasUser2, sftpKwasPwd2, 21);
//                
//                Iterator iter = attach.iterator();
//                while(iter.hasNext()) {
//                    AttachFileVO file = (AttachFileVO) iter.next();
//
//                    String fileName = StringUtil.null2void(file.getAtchFileChngName());
//                    File fromFile = new File(StaticConfig.APP_ROOT_DIR + file.getAtchDataPath() + fileName);
//                    
//                    util2.upload(dir, fromFile);
//                }
//                util2.disconnection();
//            }
//            catch (Exception ex) {
//            	util2.disconnection();
//            	ex.printStackTrace();
//            }
//        	util.disconnection();
//        	e.printStackTrace();
//        }
//        
//        String sftpLwasIp   = StaticConfig.FTP_LWAS_IP;
//        String sftpLwasUser = StaticConfig.FTP_LWAS_USER;
//        String sftpLwasPwd  = StaticConfig.FTP_LWAS_PWD;
//        
//        FTPUtil util3 = new FTPUtil();
//        
//        try{
//            List attach = service.getAttachFileList(docRegNo, contsId);
//            
//            util3.init(sftpLwasIp, sftpLwasUser, sftpLwasPwd, 21);
//            
//            Iterator iter = attach.iterator();
//            while(iter.hasNext()) {
//                AttachFileVO file = (AttachFileVO) iter.next();
//
//                String fileName = StringUtil.null2void(file.getAtchFileChngName());
//                File fromFile = new File(StaticConfig.APP_ROOT_DIR + file.getAtchDataPath() + fileName);
//                
//                util3.upload(dir, fromFile);
//            }
//            
//            util3.disconnection();
//        }
//        catch (Exception e) {
//
//            String sftpLwasIp2   = StaticConfig.FTP_LWAS_IP2;
//            String sftpLwasUser2 = StaticConfig.FTP_LWAS_USER2;
//            String sftpLwasPwd2  = StaticConfig.FTP_LWAS_PWD2;
//            
//            FTPUtil util4 = new FTPUtil();
//            
//        	try{
//                List attach = service.getAttachFileList(docRegNo, contsId);
//                
//                util4.init(sftpLwasIp2, sftpLwasUser2, sftpLwasPwd2, 21);
//                
//                Iterator iter = attach.iterator();
//                while(iter.hasNext()) {
//                    AttachFileVO file = (AttachFileVO) iter.next();
//
//                    String fileName = StringUtil.null2void(file.getAtchFileChngName());
//                    File fromFile = new File(StaticConfig.APP_ROOT_DIR + file.getAtchDataPath() + fileName);
//                    
//                    util4.upload(dir, fromFile);
//                }
//                util4.disconnection();
//            }
//            catch (Exception ex) {
//            	util4.disconnection();
//            	ex.printStackTrace();
//            }
//        	util3.disconnection();
//        	e.printStackTrace();
//        }
        
    }
    
    /**
     * 공통코드를 Map 형식으로 반환한다.
     * @param cdId
     * @return
     * @throws Exception
     */
    public Map<String, String> getCommonCode(String cdId)  throws Exception {
    	return this.getInnerCommonCode(StaticConfig.DB_SERVER_USER, cdId, null);
    }
    
    public Map<String, String> getCommonCode_N(String cdId, String deptcode)  throws Exception {
    	return this.getInnerCommonCode_N(StaticConfig.DB_SERVER_USER, cdId, null, deptcode);
    }

    /**
     * 공통코드를 Map 형식으로 반환한다.
     * @param cdId  
     * @return
     * @throws Exception  
     */
    public Map<String, String> getCommonUpCode(String upCdId)  throws Exception {
    	return this.getInnerCommonUpCode(StaticConfig.DB_SERVER_USER, upCdId, null);
    }
    
    /**
     * 공통코드를 Map 형식으로 반환한다.
     * @param cdId
     * @param usageYn  
     * @return
     * @throws Exception
     */
    public Map<String, String> getCommonCode(String cdId, String useYn)  throws Exception {
    	return this.getInnerCommonCode(StaticConfig.DB_SERVER_USER, cdId, useYn);
    }

    private Map<String, String> getInnerCommonCode(String dbuser, String cdId, String useYn)  throws Exception {

        Map<String, String> CMN_CD = new HashMap<String, String>(); 
        
        CodeVO key = new CodeVO();
        key.setCdId(cdId);
        key.setUseYn(useYn);
        
        List<CodeVO> list = commonService.selectCommonCode(dbuser, key);

        if (list != null && list.size() > 0) {
        	int sz = list.size();
        	CodeVO codeVo = null;
            for (int i = 0; i < sz; i++) {
            	codeVo = (CodeVO) list.get(i);
            	CMN_CD.put(codeVo.getCdTp(), codeVo.getCdKnm());
            }
        }      
        return CMN_CD;
    }
    
    private Map<String, String> getInnerCommonCode_N(String dbuser, String cdId, String useYn, String deptcode)  throws Exception {

        Map<String, String> CMN_CD = new HashMap<String, String>(); 
        
        CodeVO key = new CodeVO();
        key.setCdId(cdId);
        key.setUseYn(useYn);
        key.setDeptcode(deptcode);
        
        List<CodeVO> list = commonService.selectCommonCode(dbuser, key);

        if (list != null && list.size() > 0) {
        	int sz = list.size();
        	CodeVO codeVo = null;
            for (int i = 0; i < sz; i++) {
            	codeVo = (CodeVO) list.get(i);
            	CMN_CD.put(codeVo.getCdTp(), codeVo.getCdKnm());
            }
        }      
        return CMN_CD;
    }
    
    private Map<String, String> getInnerCommonUpCode(String dbuser, String upCdId, String useYn)  throws Exception {

        Map<String, String> CMN_CD = new HashMap<String, String>(); 
        
        CodeVO key = new CodeVO();
        key.setUpCdId(upCdId);
        key.setUseYn(useYn);
        
        List<CodeVO> list = commonService.selectCommonCode(dbuser, key);

        if (list != null && list.size() > 0) {
        	int sz = list.size();
        	CodeVO codeVo = null;
            for (int i = 0; i < sz; i++) {
            	codeVo = (CodeVO) list.get(i);
            	CMN_CD.put(codeVo.getCdTp(), codeVo.getCdKnm());
            }
        }      
        return CMN_CD;
    }
    
    /**
     * 공통코드를 List 형식으로 반환한다.
     * @param cdId
     * @return
     * @throws Exception
     */
    public List<CodeVO> getCommonCodeList(String cdId)  throws Exception {
    	return this.getInnerCommonCodeList(StaticConfig.DB_SERVER_USER, cdId, null);
    }  
    
    /**
     * 공통코드를 List 형식으로 반환한다.
     * @param cdId
     * @param usageYn
     * @return
     * @throws Exception
     */
    public List<CodeVO> getCommonCodeList(String cdId, String useYn)  throws Exception {
    	return this.getInnerCommonCodeList(StaticConfig.DB_SERVER_USER, cdId, useYn);
    }

    private List<CodeVO> getInnerCommonCodeList(String dbuser, String cdId, String useYn)  throws Exception {

        CodeVO key = new CodeVO();
        key.setCdId(cdId);
        key.setUseYn(useYn);
        
        List<CodeVO> list = commonService.selectCommonCode(dbuser, key);
  
        return list;
    }
    
    /**
     * VOC유형을 xml파일로 만든 후 FTP로 전송한다. 
     * @param cdId
     * @param usageYn
     * @return
     * @throws Exception
     */
    public boolean writeXmlVocType(List<VocCnslTypeVO> list) {
        String sftpXmlDir = StaticConfig.FTP_CODE_XML_DIR;
        
		String filename = "vocType.xml";
		FileWriter ostream = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"EUC-KR\" ?> \n");
			sb.append("<type>\n");
			
			for (int i = 0; i < list.size(); i++) {
				VocCnslTypeVO result = list.get(i);
				sb.append("<cd>\n");
				sb.append("<lcls>\n");
				sb.append(result.getLcls());
				sb.append("</lcls>\n");
				sb.append("<mcls>\n");
				sb.append(result.getMcls());
				sb.append("</mcls>\n");
				sb.append("<scls>\n");
				sb.append(result.getScls());
				sb.append("</scls>\n");
				sb.append("<lvl>\n");
				sb.append(result.getLvl());
				sb.append("</lvl>\n");
				sb.append("<cnslCatNm>\n");
				sb.append(result.getCnslCatNm());
				sb.append("</cnslCatNm>\n");
				sb.append("<useYn>\n");
				sb.append(result.getUseYn());
				sb.append("</useYn>\n");
				sb.append("<ord>\n");
				sb.append(result.getOrd());
				sb.append("</ord>\n");
				sb.append("</cd>\n");
			}
			sb.append("</type>");
			
			ostream = new FileWriter(sftpXmlDir+filename);
			ostream.write(sb.toString());
			ostream.close();
	        
	        try{
	        	String s = null;
				BufferedReader in =  null;
				
	        	// shell 실행
    			Process proc = Runtime.getRuntime().exec("/tim/shell/jshell/jshell.sh j_dep.sh wtsdev2 " + sftpXmlDir + " " + filename);

    			in =  new BufferedReader (new InputStreamReader(proc.getInputStream()));
    			while ((s = in.readLine ())!= null){
             	  	break;
            	}
    			
    			LogManager.getRootLogger().debug("정상적으로 동기화 되었습니다.");
	        }
	        catch(Exception e){
	        	LogManager.getRootLogger().debug("명령 수행중 에러가 발생하였습니다");
	        	e.printStackTrace();
	        }
		} catch (IOException e) {
			e.printStackTrace();
			try {
				ostream.close();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}	
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ostream.close();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}	
			return false;
		}
		return true;
	}
    
    /**
     * 이용매체를 xml파일로 만든 후 FTP로 전송한다. 
     * @param cdId
     * @param usageYn
     * @return
     * @throws Exception
     */
    public boolean writeXmlCommCd(List<CodeVO> list) {
        String sftpXmlDir = StaticConfig.FTP_CODE_XML_DIR;
        
		String filename = "vocCommCd.xml";
		FileWriter ostream = null;
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"EUC-KR\" ?> \n");
			sb.append("<type>\n");
			
			for (int i = 0; i < list.size(); i++) {
				CodeVO result = list.get(i);
				sb.append("<cd>\n");
				sb.append("<cdId>\n");
				sb.append(result.getCdId());
				sb.append("</cdId>\n");
				sb.append("<cdTp>\n");
				sb.append(result.getCdTp());
				sb.append("</cdTp>\n");
				sb.append("<cdKnm>\n");
				sb.append(result.getCdKnm());
				sb.append("</cdKnm>\n");
				sb.append("<cdOrd>\n");
				sb.append(result.getCdOrd());
				sb.append("</cdOrd>\n");
				sb.append("<useYn>\n");
				sb.append(result.getUseYn());
				sb.append("</useYn>\n");
				sb.append("</cd>\n");
			}
			sb.append("</type>");
			
			ostream = new FileWriter(sftpXmlDir+filename);
			ostream.write(sb.toString());
			ostream.close();
			
			try{
	        	String s = null;
				BufferedReader in =  null;
				
	        	// shell 실행
    			Process proc = Runtime.getRuntime().exec("/tim/shell/jshell/jshell.sh j_dep.sh wtsdev2 " + sftpXmlDir + " " + filename);

    			in =  new BufferedReader (new InputStreamReader(proc.getInputStream()));
    			while ((s = in.readLine ())!= null){
             	  	break;
            	}
    			
    			LogManager.getRootLogger().debug("정상적으로 동기화 되었습니다.");
	        }
	        catch(Exception e){
	        	LogManager.getRootLogger().debug("명령 수행중 에러가 발생하였습니다");
	        	e.printStackTrace();
	        }
		} catch (IOException e) {
			e.printStackTrace();
			try {
				ostream.close();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}	
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ostream.close();
			} catch (IOException e1) {				
				e1.printStackTrace();
			}	
			return false;
		}
		return true;
	}
}
