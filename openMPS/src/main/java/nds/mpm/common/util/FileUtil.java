package nds.mpm.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;

/**
 * 2014.03.06 @ author Jerry FileUtil
 * 
 */
public class FileUtil {
	
    public static final Pattern dosSeperator = Pattern.compile("\\\\");

	public FileUtil() {
	}

	/**
	 * 경로 가져오기
	 * 
	 * @param strFullPath
	 *            경로
	 * @return
	 */
	public static String getPath(String strFullPath) {
		if (strFullPath == null || strFullPath.length() < 1) {
			return strFullPath;
		}

		int nPosLast = strFullPath.lastIndexOf("/");

		// 구분자 '/' 없고, 확장자가 없을 경우
		if (nPosLast == -1 && strFullPath.indexOf(".") == -1)
			return strFullPath;

		return strFullPath.substring(0, nPosLast);
	}

	/**
	 * 디렉토리 생성하기
	 * 
	 * @param strDirectoryPath
	 *            생성할 디렉토리명
	 * @return 생성 성공 : true, 실패 : false
	 */
	public static boolean createDirectory(String strDirectoryPath) {
		boolean retValue = false;

		File f = new File(strDirectoryPath);

		if (!f.exists())
			retValue = f.mkdirs();
		else
			retValue = true;

		return retValue;
	}

	/**
	 * 지정한 경로 값의 디렉토리 여부
	 * 
	 * @param strFileName
	 *            경로
	 * @return 디렉토리일 경우 true, 아닐 경우 false
	 */
	public static boolean isDirectory(String strFileName) {
		boolean retValue = false;
		try {
			retValue = new File(strFileName).isDirectory();
		} catch (Exception e) {
			e.printStackTrace();
			retValue = false;
		}
		return retValue;
	}

	/**
	 * 파일 또는 디렉토리가 한글일 경우 인코딩 처리하기
	 * 
	 * @param sString
	 *            파일명 또는 디렉토리명
	 * @return 인코딩 처리된 파일명 또는 디렉토리명
	 */
	private static String toEncoding(String strString) {
		String retValue = null;
		try {
			retValue = new String(strString.getBytes("8859_1"), "KSC5601");
		} catch (Exception e) {
			e.printStackTrace();
			retValue = strString;
		}
		return retValue;
	}

	/**
	 * 파일 또는 디렉토리 존재 여부
	 * 
	 * @param strFileName
	 *            파일명 또는 디렉토리명
	 * @return 존재한다고면 true, 존재하지 않는다면 false
	 */
	public static boolean isExists(String strFileName) {
		File objFile = new File(strFileName);

		return objFile.exists();
	}

	/**
	 * 파일 또는 디렉토리 삭제하기
	 * 
	 * @param strFileName
	 *            삭제할 파일 또는 디렉토리
	 * @param bChild
	 *            하위 디렉토리 및 파일 삭제 여부
	 * @return
	 */
	public static boolean delete(String strFileName, boolean bChild) {

		File objFile = new File(strFileName);

		try {
			if (!objFile.exists()) { // 파일 또는 디렉토리가 존재하지 않는다면
				return false;
			}

			if (objFile.delete()) {
				return true;
			} else if (objFile.isDirectory()) {
				if (!bChild) {
					return false;
				}

				if (!strFileName.substring(strFileName.length() - 1)
						.equals("/")) {
					strFileName = strFileName + "/";
				}

				boolean bDelete = true;

				/***********************************************************************************
				 * 하위 폴더는 존재하지 않는다는 가정하에 디렉토리를 삭제한다.
				 **********************************************************************************/
				String[] arsFileList = objFile.list();

				for (int i = 0; i < arsFileList.length; i++) {
					System.out.println(strFileName + arsFileList[i]);

					if (!delete(strFileName + arsFileList[i])) {
						if (bDelete) {
							bDelete = false;
						}
					}
				}

				if (bDelete) {
					bDelete = objFile.delete();
				}

				return bDelete;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 파일 또는 디렉토리 삭제하기
	 * 
	 * @param sFileName
	 *            삭제 파일명 또는 디렉토리
	 * @return
	 */
	public static boolean delete(String strFileName) {
		File objDeleteFile = new File(strFileName);

		return objDeleteFile.delete();
	}

	/**
	 * 파일명 또는 디렉토리명 변경하기
	 * 
	 * @param strSrc
	 *            기존 파일명 또는 디렉토리명
	 * @param strDest
	 *            변경될 파일명 또는 디렉토리명
	 * @return 변경 성공 여부
	 */
	public static boolean rename(String strSrc, String strDest) {
		boolean retValue = false;

		File objSrcFile = new File(toEncoding(strSrc)); // 파일 또는 디렉토리가 한글 일 경우
		File objDestFile = new File(toEncoding(strDest)); // 파일 또는 디렉토리가 한글 일 경우

		try {
			retValue = objSrcFile.renameTo(objDestFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 확장자 가져오기
	 * 
	 * @param name
	 *            파일명
	 * @return 확장자( . 제외)
	 */
	public static String getExtension(String name) {
		int index = name.lastIndexOf('.');
		return (index < 0) ? "" : name.substring(index + 1);
	}

	/**
	 * 파일 쓰기.
	 * 
	 * @param data
	 *            파일로 기록할 byte[] 데이
	 * @param filepath
	 *            파일경로(파일명 제외)
	 * 
	 * 
	 * @return 성공시 파일명, 실패시 null
	 * 
	 */
	public static String saveFileForByte(byte[] data, String oFilepath,
			String oFilename) throws Exception {

		FileOutputStream fos = null;
		String nFilepath = oFilepath;
		String nFilename = null;
		String extFile = getExtension(oFilename);

		nFilename = new Long(new Date().getTime()).toString();
		
		if( extFile.length() > 0 )
		{
			nFilename += "."+ extFile;
		}
		
		try {

			fos = new FileOutputStream(nFilepath + File.separator + nFilename);
			fos.write(data);
			fos.close();

		} catch (IOException ioe) {

			nFilename = null;

		} finally {

			if (fos != null)
				fos.close();
		}

		return nFilename;
	}
	/**
	 * 파일 쓰기.
	 * 
	 * @param data
	 *            파일로 기록할 byte[] 데이
	 * @param filepath
	 *            파일경로(파일명 제외)
	 * @param oFilename
	 *            원본파일파일명 
	 * @param nFilename
	 *            새파일명
	 *            
	 * @return 성공시 파일명, 실패시 null
	 * 
	 */
	public static String saveFileForByte(byte[] data, String oFilepath,
			String oFilename
			,String nFilename) throws Exception {

		FileOutputStream fos = null;
		String nFilepath = oFilepath;
		String extFile = getExtension(oFilename);
		
		if(nFilename == null) nFilename = oFilename;

		if( extFile.length() > 0 )
		{
			nFilename += "."+ extFile;
		}
		
		try {

			fos = new FileOutputStream(nFilepath + File.separator + nFilename);
			fos.write(data);
			fos.close();

		} catch (IOException ioe) {

			nFilename = null;

		} finally {

			if (fos != null)
				fos.close();
		}

		return nFilename;
	}
	/**
	 * 존재하는 파일의 디렉토리 이동.
	 * 
	 * @param sourceFile
	 *            이동할 대상 파일 경로
	 * @param targetPath
	 *            이동할 파일경로(파일명 제외)
	 * 
	 * 
	 * @return 성공시 최종 경로가 포함된 파일명, 실패시 Exception( msg )
	 * 
	 */
	public static String moveFile(String sourceFile, String targetPath,
			String filename) throws Exception {

		String nFilename = null;

		File srcFile = new File(sourceFile);
		File destFile = null;

		if (srcFile.isFile()) {

			destFile = new File(targetPath);

			if (destFile.isDirectory()) {
				
				nFilename = targetPath + File.separator + filename;
				
				destFile = new File(nFilename);

				FileUtils.copyFile(srcFile, destFile);

				if (destFile.exists()) {

					srcFile.delete();
					
				} else {

					throw new Exception("Files.move fail!!");
				}

			} else {

				throw new Exception("targetPath is not Directory!!");
			}

		} else {

			throw new Exception("sourceFile is not file!!");
		}

		return nFilename;
	}
	/**
	 * 파일을 다른 경로로 복사.
	 * 
	 * @param sourceFile
	 *            복사할 대상 파일 경로
	 * @param targetPath
	 *            복사할 파일경로(파일명 제외)
	 * 
	 * 
	 * @return 성공시 최종 경로가 포함된 파일명, 실패시 Exception( msg )
	 * 
	 */
	public static String copyFile(String sourceFile, String targetPath,
			String filename) throws Exception {

		String nFilename = null;

		File srcFile = new File(sourceFile);
		File destFile = null;

		if (srcFile.isFile()) {

			destFile = new File(targetPath);

			if (destFile.isDirectory()) {
				
				nFilename = targetPath + File.separator + filename;
				
				destFile = new File(nFilename);

				FileUtils.copyFile(srcFile, destFile);

				if (destFile.exists()) {} else {

					throw new Exception("Files.copy fail!!");
				}

			} else {

				throw new Exception("targetPath is not Directory!!");
			}

		} else {

			throw new Exception("sourceFile is not file!!");
		}

		return nFilename;
	}
	
	public static String mimeType(String path)
	{
		return new MimetypesFileTypeMap().getContentType( new File( path ) );
	}
	
	public static String downloadFile(
			String filePath,
			String fileName,
			String downloadfilename,
			HttpServletResponse res) throws Exception {
		
		File uFile = new File(filePath, fileName);
		int fSize = (int) uFile.length();
		
		System.out.println("fSize :: " + fSize);
		String mimetype = mimeType(filePath + "/" +fileName);
		System.out.println("mimetype :: " + mimetype);
		System.out.println("filePath+fileName :: " + filePath + "/" +fileName);
		if (fSize > 0) {

			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(uFile));
			
			
			

			res.setBufferSize(fSize);
			res.setContentType(mimetype);
			res.setHeader("Content-Disposition", "attachment; filename=\""
					+ URLEncoder.encode(downloadfilename,"UTF-8") + "\"");
			res.setContentLength(fSize);

			FileCopyUtils.copy(in, res.getOutputStream());
			in.close();
			res.getOutputStream().flush();
			res.getOutputStream().close();
			
			return fileName;
		} else {
			return null;
		}
	}
	
	public static String downloadFile(
			String filePath,
			String fileName,
			HttpServletResponse res) throws Exception {
		
		return downloadFile(
				 filePath,
				 fileName,
				 fileName,
				 res);
		
	}
	
	/**
	    * 주어진 파일의 fullpath중 path부분을 제외한 filname part만 분리하여 리턴한다.<BR>
	    * (new File(fullpath)).getName()과 동일하나 File 객체를 사용하지 않고 문자열 패턴만으로 분석한다.<BR>
	    * 만약 fullpath에 / 혹은 \가 존재하지 않는 경우라면 "./" 을 리턴할 것이다.
	    *
	    * @param fullpath Path와 filename으로 이루어진 파일의 fullpath
	    *
	    * @return fullpath중 filename part
	    */
	    public static String getFileNameChop(String fullpath) {
	        if (null == fullpath)
	            return null;
	        fullpath = dosSeperator.matcher(fullpath).replaceAll("/");
	        int pos = fullpath.lastIndexOf("/");
	        if (pos > -1)
	            return fullpath.substring(pos + 1);
	        return fullpath;
	    }
}
