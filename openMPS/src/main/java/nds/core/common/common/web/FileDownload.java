package nds.core.common.common.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nds.frm.config.StaticConfig;
import nds.frm.startup.SYSTEM;



/**
 * <b>class : FileDownload </b>
 * <b>Class Description</b><br>
 * 파일다운로드를 처리하는 Servlet Class
 * <b>History</b><br>
 * <pre>      : 2012.06.25 초기작성(소건)</pre>
 * @author <a href="mailto:gso@nds.co.kr">소건</a>
 * @version 1.0
 */
public class FileDownload extends HttpServlet {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    final static int bufferSize = 1024;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //FileInputStream fis = null;
        //ServletOutputStream fout = null;
       
        boolean fileCheck = false;

        String[][] mimeTypeArray={{"application/msword", "doc"},
                {"application/octet-stream", "lha"},
                {"application/octet-stream", "lzh"},
                {"application/octet-stream", "exe"},
                {"application/pdf", "pdf"},
                {"application/vnd.ms-excel", "xls"},
                {"application/vnd.ms-powerpoint", "ppt"},
                {"application/zip", "zip"},
                {"audio/x-ms-wma", "wma"},                             
                {"audio/mpeg", "mp2"}, 
                {"audio/mpeg", "mp3"},
                {"video/mpeg", "mpg"}, 
                {"video/mpeg", "asf"}, 
                {"video/x-ms-wmv", "wmv"},               
                {"image/bmp", "bmp"},
                {"image/tiff", "tif"},
                {"text/html", "html"},
                {"text/html", "htm"},
                {"application/x-hwp", "hwp"},       // 2003.05.06 HWP -> WORD 로 변경
                {"text/plain", "txt"}};
        

        String contentID = URLEncoder.encode(request.getParameter("CONTENT_ID"), "UTF-8");
        String registNo = URLEncoder.encode(request.getParameter("REGIST_NO"), "UTF-8");
        String fileID = URLEncoder.encode(request.getParameter("FILE_ID"), "UTF-8");
        String orgnFileName = URLEncoder.encode(request.getParameter("ORGN_FILE_NAME"), "UTF-8");
        String fullPathFileName = null;
        String fileName = null;
        String pisFile = request.getParameter("PIS_FILE");
        String rootDir = "";

        if (registNo != null && registNo.length() > 0) {
            System.out.println("FileDownload java 실행");
            
            Connection conn = SYSTEM.getInstance().getConnection();
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                String sql = "";
               
                    if(pisFile!=null&&pisFile.equals("PIS_FILE")){
                      	sql =  " SELECT FILE_PATH || '/' AS ATCH_FILE_PATH, REAL_FILE AS ATCH_OTXT_FILE_NAME,'' AS ATCH_FILE_SIZE " 
                                + " FROM MMCOFILE " 
                                + " WHERE KEY_VAL01||KEY_VAL02||KEY_VAL03||KEY_VAL04 = '" + registNo + "' "
                                + "   AND SAVE_FILE = '" + fileID + "' ";
                    	 //PIS 파일 경로로 변경해야함
                    	 rootDir = "http://nspis.nongshim.com/xplatform/upload/";
                    	
                    //	 rootDir = " /datanon11g/8014/WebApp/attach/temp/"; 
                    	
                    }else{
                    	 sql =  " SELECT ATCH_FILE_PATH, ATCH_OTXT_FILE_NAME, ATCH_FILE_SIZE " 
                                 + " FROM RMS_ATCHFILE " 
                                 + " WHERE CONTS_ID = '" + contentID + "' "
                                 + "   AND DOC_REG_NO = '" + registNo + "' "
                                 + "   AND ATCH_FILE_CHNG_NAME = '" + fileID + "' ";
                    	 rootDir = StaticConfig.APP_ROOT_DIR; 
                    }
                System.out.println(sql);
              
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();

                
                //int fileSize = 0;
                while (rs.next()) {
                    
                    fullPathFileName = rootDir + rs.getString("ATCH_FILE_PATH") + fileID;
                    System.out.println("fullPathFileName : " + fullPathFileName);
                    fileName = rs.getString("ATCH_OTXT_FILE_NAME");
                    //fileSize = rs.getInt("ATCH_FILE_SIZE");
                    fileCheck = true;
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    stmt.close();
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                }
            }
        }

        if ( !fileCheck) {
            fileName = orgnFileName;
            fullPathFileName = StaticConfig.ATTACH_TEMP_DIR + fileID;
        }

        String orgFileExt = "";
        //String mimeType = "";
        StringTokenizer fileExtToken = new StringTokenizer(fileName, ".", false);

        while (fileExtToken.hasMoreTokens()) {
            orgFileExt = fileExtToken.nextToken();
        }

        for (int i = 0; i < mimeTypeArray.length; i++) {
            if (mimeTypeArray[i][1].equals(orgFileExt.toLowerCase())) {
                //mimeType = mimeTypeArray[i][0];
                break;
            }
        }
     
        if(pisFile!=null&&pisFile.equals("PIS_FILE")){
        	
        	try{
	        	//pis(타서버)에서 파일을 다운로드 하는 것임으로 
	        	//url 클래스를 활용하여 파일 다운로드를 한다.
		        URLConnection uCon = null;
		        InputStream in = null;
		        OutputStream out= null;
		        int byteWritten = 0;
		        URL Url;
		        byte[] buf;
		        int byteRead;
		        buf = new byte[2048];
	        
	       
				Url = new URL(fullPathFileName);
				uCon = Url.openConnection();
		         in = uCon.getInputStream(); 
		         out= response.getOutputStream();
		        
		         setHeader(response, new File(fullPathFileName) , fileName);
		         
		         //다운로드할 파일 컨텐츠 크기를 미리 헤더에 정의
		         //미리 정의 하지 않으면, 파일이 생성되지 않는다
		         response.setHeader("Content-Length", "" + uCon.getContentLength());
		         BufferedInputStream bin = new BufferedInputStream(in);
		         BufferedOutputStream  bos = new BufferedOutputStream(out);
		        
		      
		        while ((byteRead = bin.read(buf)) != -1) {
		        	
		        	 bos.write(buf, 0, byteRead);
		        	 byteWritten += byteRead;
		          }
		            //파일 길이를  설정해야 정상적으로 파일 생성됨		
		          	 System.out.println("Download Successfully.");
			         System.out.println("File name : " + fullPathFileName);
			         System.out.println("of bytes  : " + byteWritten);
			         System.out.println("-------Download End--------");
			         bos.close();
			         bin.close();
        	}catch(Exception e){
	        	
        		System.out.println(e.getStackTrace());
        		response.setCharacterEncoding("utf-8");
	        	response.setHeader("Content-Type", "text/html");
	        	PrintWriter pout = response.getWriter();
	        	pout.println("<html><head>");
	        	pout.println("<script type=\"text/javascript\">alert('파일이 서버에서 삭제되어 다운로드 하실 수 없습니다');</script>");
	        	pout.println("</head><body></body>");
	        	pout.println("</html>");
	        	pout.flush();
	        	pout.close();
        		
        	}
		 }else{
	        File aFile = new File(fullPathFileName);
	        /* 파일이 존재하지 않는경우 alert 스크립트 생성 */
	        if (!aFile.exists()){
	        	response.setCharacterEncoding("utf-8");
	        	response.setHeader("Content-Type", "text/html");
	        	PrintWriter out = response.getWriter();
	        	out.println("<html><head>");
	        	out.println("<script type=\"text/javascript\">alert('파일이 서버에서 삭제되어 다운로드 하실 수 없습니다');</script>");
	        	out.println("</head><body></body>");
	        	out.println("</html>");
	        	out.flush();
	        	out.close();
	        }
	        else{
	            setHeader(response, aFile , fileName);
	
	            transport(new FileInputStream(aFile), response.getOutputStream());        	
	        }
        
        }
        
        /**
        if ( !aFile.exists()) {
            //return new ModelAndView("success_script", "scriptBlock", "alert('파일이 서버에서 삭제되어 다운로드 하실 수 없습니다.2'); this.close();");
        }

        try {
            fis = new FileInputStream(fullPathFileName);

            //              if (mimeType.indexOf("video") > -1) {
            StringTokenizer st = new StringTokenizer(fileName, ".");
            String fileType = "";
            st.nextToken();
            if (st.hasMoreTokens()) fileType = st.nextToken();
            
            response.setContentType(fileType);
            fileName = new String(fileName.getBytes("KSC5601"), "8859_1");
            //out.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setHeader("Content-Length", "" + String.valueOf(aFile.length()));
            fout = response.getOutputStream(); //"application/octet-stream;");
            //fout = out.getStream("message/rfc822;");
            //              } else {
            //                out.setHeader("Content-Disposition", " filename=" + fileName);
            //                out.setHeader("Content-Length", "" + String.valueOf(aFile.length()));
            //                fout = out.getStream(mimeType);
            //          }
            //----hong test 02.05.23

            byte[] buffer = new byte[100 * 1024];

            int count = 0;
            while ((count = fis.read(buffer)) >= 0) {
                fout.write(buffer, 0, count);
                fout.flush();
            }

            fis.close();
            fout.close();
        } catch (Exception e) {
            if (fis != null) fis.close();
            if (fout != null) fout.close();
        } finally {
            if (fis != null) fis.close();
            if (fout != null) fout.close();
        }
        */
    }
    
    /**
     * Method setHeader.
     * @param response
     * @param file
     */
    private void setHeader(HttpServletResponse response, File file , String fileName) throws
        UnsupportedEncodingException {
        //filename = CharEncodingUtil.toEng(filename);
        if("info".equals(StaticConfig.PRODUCT_MODE)) {
            fileName = URLEncoder.encode(fileName, "utf-8");
        }else{
            fileName = new String(fileName.getBytes("KSC5601"), "8859_1");
        }
        
        String mime = getServletContext().getMimeType(file.toString());
        if (mime == null)
            mime = "application/octet-stream";
        response.setContentType(mime);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");            

        response.setHeader("Content-Length", "" +file.length());
    }    

    /**
     * Method transport.
     * @param in
     * @param out
     * @throws IOException
     */
    private void transport(InputStream in, OutputStream out) throws IOException {

        BufferedInputStream bin = null;
        BufferedOutputStream bos = null;

        try {
            bin = new BufferedInputStream(in);
            bos = new BufferedOutputStream(out);

            byte[] buf = new byte[2048]; //buffer size 2K.
            int read = 0;
            while ( (read = bin.read(buf)) != -1) {
                bos.write(buf, 0, read);
            }
        }catch(Exception ex){
            ex.getStackTrace();
        }
        finally {
            bos.close();
            bin.close();
        }
    }    
}