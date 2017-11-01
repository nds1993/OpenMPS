package nds.core.common.common.web;



import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.core.common.common.service.AttachFileTmpVO;
import nds.core.common.common.service.impl.AttachFileTmpDAO;
import nds.frm.config.StaticConfig;
import nds.frm.startup.SYSTEM;
import nds.frm.util.FileCopier;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>Title: FileController</p>
 * <p>Description: Control Class</p>
 * <p><b>History</b></p>
 * <pre>      : 2012.06.25 초기작성(소건)</pre>
 * @author <a href="mailto:gso@nds.co.kr">소건</a>
 * @version 1.0
 */
@Controller
public class FileController {

    private Map dao = new HashMap();
    
	@Resource(name="attachFileTmpDAO")
    private AttachFileTmpDAO attachFileTmpDAO;
    
    SimpleDateFormat format = new SimpleDateFormat("ddkkmmss");

    public Map getDao() {
        return dao;
    }

    public void setDao(Map dao) {
        this.dao = dao;
    }

    @RequestMapping("/co_uploadfile.do")
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {

    	FileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);
        //upload.setHeaderEncoding("utf-8");

        /* FileItem */
        List items = upload.parseRequest(request);
        String mode = null;
        String selectedValue = null;
        String texts = null;
        String values = null;
        //String p_cntnid      = null;
        String p_index = "";
        String tabId = "";
        String p_voc = "";
        int totalSize = 0;
        
        Iterator params = items.iterator();
        while (params.hasNext()) {
            FileItem it = (FileItem) params.next();
            if (it.isFormField()) {
                String name = it.getFieldName();
                String value = it.getString();

                if ("mode".equals(name)) {
                    mode = value;
                } else if (name.equals("texts")) {
                    texts = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                    //texts = URLDecoder.decode(value, "utf-8");
                    //texts = value;
                } else if (name.equals("values")) {
                    values = value;
                } else if (name.equals("selectValues")) {
                    selectedValue = value;
                } else if (name.equals("p_cntnid")) {
                    //p_cntnid = value;
                } else if (name.equals("p_index")) {
                    p_index = value;
                } else if (name.equals("tabId")) {
                	tabId = value;
                } else if (name.equals("p_voc")) {
                	p_voc = value;
                }
	            else if ("totalSize".equals(name)) {
	            	if(value.equals("")){
	            		value = "0";
	            	}
	                totalSize = Integer.parseInt(value);
	            }
            }
        }

        Map<String, String> Attachfiles = new HashMap<String, String>();

         ModelAndView mav = new ModelAndView("co_uploadfile");

        if (null != mode && "list".equals(mode)) {

            if (texts != null && values != null) {
                StringTokenizer stExistTexts = new StringTokenizer(texts, "|");
                StringTokenizer stExistValues = new StringTokenizer(values, "|");

                while (stExistTexts.hasMoreTokens()) {
                    String strExistText = stExistTexts.nextToken();
                    String strExistValue = stExistValues.nextToken();
                    String strRealFileName = "";

                    // 임시 파일 업로드 디렉토리에 있는 지를 검사합니다.
                    //
                    //  만약 임시 파일 업로드 디렉토리에 있다면 이 파일은 이미 임시 테이블에 입력되어 있는 상태입니다.
                    //  따라서 아무런 행동을 취하지 않아도 됩니다. 그러나 파일이 없다면 이는 실제 테이블에 입력되고 이
                    //  동된 상태이므로 임시테이블에 INSERT 및 임시 파일 디렉토리에 복사해야 합니다.

                    strRealFileName = StaticConfig.ATTACH_TEMP_DIR + strExistValue;
                    String strAtchFileChngName = "";
                    String strAtchFileExt = "";

                    File testFile = new File(strRealFileName);
                    
                    if (!testFile.exists()) {
                        Connection conn = SYSTEM.getInstance().getConnection();
                        Statement stmt = null;
                        try {
                            stmt = conn.createStatement();
                            String sql = "SELECT ATCH_FILE_PATH, ATCH_FILE_CHNG_NAME, ATCH_FILE_EXT FROM CLT_ATCHFILE WHERE ATCH_FILE_CHNG_NAME = '" + strExistValue + "' ORDER BY ATCH_FILE_CHNG_NAME ";
                            stmt.execute(sql);
                            ResultSet rs = stmt.getResultSet();

                            while (rs.next()) {
                                strRealFileName     = rs.getString("ATCH_FILE_PATH");
                                strAtchFileChngName = rs.getString("ATCH_FILE_CHNG_NAME");
                                strAtchFileExt      = rs.getString("ATCH_FILE_EXT");
                            }
                        } catch (SQLException se) {
                            se.printStackTrace();
                        } finally {
                            try {
                                if (null != stmt)
                                    stmt.close();
                            } catch (Exception e) {
                            }
                        }

                        strRealFileName = StaticConfig.APP_ROOT_DIR + strRealFileName + strAtchFileChngName;
                        //System.out.println("StaticConfig.APP_ROOT_DIR ="+ StaticConfig.APP_ROOT_DIR  + "   strRealFileName="+ strRealFileName + "   strAtchFileChngName=" + strAtchFileChngName);
                        testFile = new File(strRealFileName);
                        if (testFile.exists()) {
                            // Copy the file ...........................................................................
                            try {
                                String fromFileName = strRealFileName;
                                String toFileName = StaticConfig.ATTACH_TEMP_DIR + strAtchFileChngName;
                                FileCopier.copy(fromFileName, toFileName);
                            } catch (Exception e) {
                                System.out.println("죄송합니다. 귀하의 요청은 내부사정으로 인해 처리되지 않았습니다.\\n관리자에게 문의해 주세요.");
                                e.printStackTrace();
                            }

                            // DB 에 입력해 주세염.
                            int intChecker = 0;
                            try {
                                stmt = conn.createStatement();
                                String sql = "SELECT COUNT(1) as cnt FROM CLT_ATCHFILE_TMP WHERE ATCH_FILE_CHNG_NAME = '" + strAtchFileChngName + "' AND ROWNUM <= 1 ";
                                stmt.execute(sql);
                                ResultSet rs = stmt.getResultSet();

                                while (rs.next()) {
                                    intChecker = rs.getInt("cnt");
                                }
                            } catch (SQLException se) {
                                se.printStackTrace();
                            } finally {
                                try {
                                    if (null != stmt)
                                        stmt.close();
                                } catch (Exception e) {
                                }
                            }

                            if (intChecker != 1) {
                                try {
                                    stmt = conn.createStatement();
                                    String sql = "INSERT INTO CLT_ATCHFILE_TMP(ATCH_OTXT_FILE_NAME, ATCH_FILE_CHNG_NAME, ATCH_FILE_SIZE, ATCH_FILE_EXT) ";
                                    sql += " VALUES ('" + strExistText + "', '" + strAtchFileChngName + "', '" + testFile.length() + "', '" + strAtchFileExt + "')";
                                    stmt.execute(sql);
                                } catch (SQLException se) {
                                    se.printStackTrace();
                                } finally {
                                    try {
                                        if (null != stmt)
                                            stmt.close();
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        } else {
                            System.out.println("수정하고자 하는 파일이 서버에서 삭제되었습니다.\\n관리자에게 문의해 주세요." + strRealFileName);
                        }
                        if (null != conn)
                            conn.close();
                        conn = null;
                    }

                    if (!Attachfiles.containsKey(strExistValue)) {
                        Attachfiles.put(strExistValue, strExistText);
                    }
                }
            }
            mav.addObject("attach", Attachfiles);

        } else {

            if (texts != null && values != null) {
                StringTokenizer stTexts = new StringTokenizer(texts, "|");
                StringTokenizer stValues = new StringTokenizer(values, "|");
                while (stTexts.hasMoreTokens()) {
                    String strText = stTexts.nextToken();
                    String strValue = stValues.nextToken();

                    if (!strText.equals("") && !strValue.equals("")) {
                        Attachfiles.put(strValue,  strText);
                    }
                }
            }

            Iterator iter = items.iterator();
            int fileSize = 0;
            int delFileSize = 0;
            
            while(iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if(item.isFormField()) {

                }
                else {
                    String fileName = item.getName().substring(item.getName().lastIndexOf("\\") + 1);
                    String dirName = StaticConfig.ATTACH_TEMP_DIR;
                    File dir = new File(dirName);

                    if(mode != null && mode.equals("add")) {
                        if(!dir.exists())
                            dir.mkdirs();

                        /**
                         * 파일 ID 규칙
                         */
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                        String fileId = formatter.format(new Date()) + System.currentTimeMillis();

                        File uploadedFile = new File(dirName + fileId);
                        item.write(uploadedFile);

                        String orgFileExt = "";
                        StringTokenizer fileExtToken = new StringTokenizer(fileName, ".", false);
                        while(fileExtToken.hasMoreTokens()) {
                            orgFileExt = fileExtToken.nextToken();
                        }

                        // Store Temp File info to DataBase
                        AttachFileTmpVO m = new AttachFileTmpVO();
                        m.setAtchFileChngName(fileId);
                        m.setAtchFileSize(String.valueOf(uploadedFile.length()));
                        m.setAtchOtxtFileName(fileName);
                        m.setAtchFileExt(orgFileExt);
                        fileSize = Integer.parseInt(String.valueOf(uploadedFile.length()));

                        attachFileTmpDAO.insert(m);
                        if(!Attachfiles.containsKey(fileId)) {
                            Attachfiles.put(fileId, fileName);
                        }
                    }
                    else
                        if(mode != null && mode.equals("del")) {

                            File deleteFile = new File(dirName + selectedValue);
                            delFileSize = Integer.parseInt(String.valueOf(deleteFile.length()));
                            deleteFile.delete();

                            // Delete Temp File info from DataBase
                            attachFileTmpDAO.deleteByPrimaryKey(selectedValue);

                            Attachfiles.remove(selectedValue);

                        }
                }
            }
            mav.addObject("attach", Attachfiles);

//
//            FTPUtil ftp1 = new FTPUtil();
//            FTPUtil ftp2 = new FTPUtil();
//            String host1 = "172.21.97.101";
//            String host2 = "172.21.97.102";
//            String id = "vocap";
//            String password = "vocap";
//            int port = 21;
//            ftp1.init(host1, id, password, port);  //connect
//            ftp2.init(host2, id, password, port);
//
//            try {
//                Iterator iter = items.iterator();
//                while (iter.hasNext()) {
//                    FileItem item = (FileItem) iter.next();
//
//                    if (item.isFormField()) {
//
//                    } else {
//                        String fileName = item.getName().substring(item.getName().lastIndexOf("\\") + 1);
////                        String dirName = StaticConfig.ATTACH_TEMP_DIR;      2012-11-06 템프디렉토리를 거치지 않고 바로 리얼 폴더로 변경
//                        String dirName = StaticConfig.APP_ROOT_DIR + "/attach/files/";
//                        File dir = new File(dirName);
//
//                        if (mode != null && mode.equals("add")) {
//                            if (!dir.exists())
//                                dir.mkdir();
//
//                            /**
//                             * 파일 ID 규칙
//                             */
//                            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//                            String fileId = formatter.format(new Date()) + System.currentTimeMillis();
//
//                            String orgFileExt = "";
//                            StringTokenizer fileExtToken = new StringTokenizer(fileName, ".", false);
//                            while (fileExtToken.hasMoreTokens()) {
//                                orgFileExt = fileExtToken.nextToken();
//                            }
//                            
//                            File uploadedFile = new File(dirName + fileId + "."+orgFileExt);
//                            item.write(uploadedFile);
//                            
//                            ftp1.upload(dirName, uploadedFile);
//                            ftp2.upload(dirName, uploadedFile);
//                            //Store Temp File info to DataBase
//                            TbvcAtchFileTmp m = new TbvcAtchFileTmp();
//                            m.setAtchFileChngName(fileId);
//                            m.setAtchFileSize(String.valueOf(uploadedFile.length()));
//                            m.setAtchOtxtFileName(fileName);
//                            m.setAtchFileExt(orgFileExt);
//
//                            TbvcAtchFileTmpDAO dao = (TbvcAtchFileTmpDAO) getDao().get("tbvcAtchFileTmpDAO");
//                            dao.insert(m);
//                            if (!Attachfiles.containsKey(fileId)) {
//                                Attachfiles.put(fileId, fileName);
//                            }
//                            ftp1.disconnection();
//                            ftp2.disconnection();
//                        } else if (mode != null && mode.equals("del")) {
//
//                            File deleteFile = new File(dirName + selectedValue);
//                            deleteFile.delete();
//                            TbvcAtchFileTmpDAO dao = (TbvcAtchFileTmpDAO) getDao().get("tbvcAtchFileTmpDAO");
//                            //Delete Temp File info from DataBase
//                            dao.deleteByPrimaryKey(selectedValue);
//
//                            Attachfiles.remove(selectedValue);
//                        }
//                    }
//                }
//            }
//            catch(Exception e) {
//                ftp1.disconnection();
//                ftp2.disconnection();
//                e.printStackTrace();
//            }
//
//            mav.addObject("attach", Attachfiles);

            totalSize = totalSize + fileSize - delFileSize ;
            
            mav.addObject("totalSize", totalSize);
            mav.addObject("fileSize", fileSize);
            mav.addObject("delFileSize", delFileSize);
        }
        List fileExtList = attachFileTmpDAO.selectFileExt();

        mav.addObject("p_voc", p_voc);
        mav.addObject("fileExt", fileExtList);
        mav.addObject("p_index", p_index);
        mav.addObject("tabId", tabId);
        return mav;
        //return super.onSubmit(request, response, command, errors);
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {

        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

}
