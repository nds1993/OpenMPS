package nds.frm.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;



/**
 * <pre>
 * File을 핸들링할 때 필요한 Utility Function을 제공한다.
 *
 * 이 클래스는 JDK V1.4이상에서 동작한다.
 * </pre>
 * @author 이선호
 * @version 1.0
 * @since 1.0
 */
public final class FileUtil {
    private static File file = null;

    /**
     * Don't let anyone instantiate this class
     */
    private FileUtil() {
    }

    /**
     * 도스 파일시스템의 seperator(\)를 Java Style (/)로 변경하기 위해 사용되는 정규식 패턴
     */
    public static final Pattern dosSeperator = Pattern.compile("\\\\");

    /**
     * 파일 시스템의 Full Path에서 마지막이 /로 끝나는지를 검사하기 위해 사용되는 정규식 패턴
     */
    public static final Pattern lastSeperator = Pattern.compile("/$");

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

    /**
    * 주어진 파일의 fullpath중 filname part를 제외한 path 부분만 분리하여 리턴한다.<BR>
    * (new File(fullpath)).getParent()와 동일하나 File 객체를 사용하지 않고 문자열 패턴만으로 분석한다.<BR>
    * 만약 fullpath에 / 혹은 \가 존재하지 않는 경우라면 "./" 을 리턴할 것이다.<BR>
    * 만약 \이 fullpath에 존재한다면 모두 /로 변경할 것이다.
    *
    * @param fullpath Path와 filename으로 이루어진 파일의 fullpath
    *
    * @return fullpath중 path
    */
    public static String getFilePathChop(String fullpath) {
        if (null == fullpath)
            return null;
        fullpath = dosSeperator.matcher(fullpath).replaceAll("/");
        int pos = fullpath.lastIndexOf("/");
        if (pos > -1)
            return fullpath.substring(0, pos + 1);
        else
            return "./";
    }

    /**
    * 주어진 파일의 fullpath의 맨 마지막에 /가 붙어 있는지를 검사하고 없는경우 /를 붙여서 리턴한다.<BR>
    * 만약 \이 fullpath에 존재한다면 모두 /로 변경될 것이다.
    *
    * @param fullpath Path와 filename으로 이루어진 파일의 fullpath
    *
    * @return fullpath의 맨 마지막에 /가 붙어 있는 fullpath
    */
    public static String getCompleteLeadingSeperator(String fullpath) {
        if (null == fullpath)
            return null;
        fullpath = dosSeperator.matcher(fullpath).replaceAll("/");
        if (!fullpath.endsWith(File.separator))
            fullpath += "/";
        return fullpath;
    }

    /**
    * 주어진 파일의 fullpath의 맨 마지막에 /가 붙어 있는지를 검사하고 있는 경우 맨 마지막의 /를 제거하여 리턴한다.<BR>
    * 만약 \이 fullpath에 존재한다면 모두 /로 변경될 것이다.
    *
    * @param fullpath Path와 filename으로 이루어진 파일의 fullpath
    *
    * @return fullpath의 맨 마지막에 /가 제거된 fullpath
    */
    public static String getRemoveLeadingSeperator(String fileName) {
        if (null == fileName)
            return null;
        fileName = dosSeperator.matcher(fileName).replaceAll("/");
        fileName = lastSeperator.matcher(fileName).replaceAll("");
        return fileName;
    }

    /**
    * 주어진 size를 크기에 따라 Kb, Mb 형태의 읽기 좋은 String으로 변경하여 리턴한다.<BR>
    * 변경되는 형태는 아래와 같을 것이다. <BR>
    *  0 ~ 1024 : #,###.00 byte  <BR>
    *  1024 ~ 1048576 : #,###.00 Kb  <BR>
    *  1048576 ~ 1073741824 : #,###.00 Mb  <BR>
    *
    * @param size 변환하기를 원하는 size
    *
    * @return size 값이 Kb, Mb로 변경된 문자
    */
    public static String getFilesizeString(long size) {
        String tail = "";
        if (1024 > size) {
            tail = "byte";
        } else if (1048576 > size) {
            size = size / 1024;
            tail = "Kb";
        } else if (1073741824 > size) {
            size = size / 1024;
            tail = "Mb";
        }
        return new DecimalFormat("#,###.00").format(size) + tail;
    }

    /**
     * 지정 위치의 디렉토리 혹은 파일을 삭제한다.
     *
     * @param String path   삭제 대상 파일의 위치 경로
     * @param String name   삭제 대상 파일명
     */
    public static void fileDelete(String path, String name) throws MainException {
        try {
            /* 인자의 null 여부를 체크해 File 생성 */
            file = checkNull(path, name);

            if (file.exists()) {

                /* 파일이 존재할 경우 해당 디렉토리가 담고 있는 파일의 리스트를 추출 */
                String[] fileList = file.list();

                /* 파일리스트를 for문으로 돌리면서 디렉토리 내부의 파일을 삭제 */
                if (file.isDirectory() && (fileList.length != 0)) {
                    for (int index = 0; index < fileList.length; index++) {
                        File chileFile = checkNull(file.getAbsolutePath(), "");
                        if (chileFile.isDirectory())

                            /* 파일리스트의 인자가 디렉토리일 경우 해당 디렉토리의 내부를 검사*/
                            deleteChild(chileFile, fileList[index]);
                        else if (chileFile.isFile())
                            chileFile.delete();
                    }
                }

                /* 리스트 파일을 전부 삭제한 빈 디렉토리 삭제 */
                file.delete();
            }

        }
        catch (Exception ex) {
            throw ExceptionHelper.getException(ex,"fileDelete() 중 에러 발생");
        }
    }

    /**
     * 지정 위치의 디렉토리 혹은 파일을 삭제한다.
     *
     * @param String path   삭제 대상 파일의 위치 경로
     * @param String name   삭제 대상 파일명
     */
    public static void fileDel(String path, String name) {
        try {
            /* 인자의 null 여부를 체크해 File 생성 */
            File delfile = checkNull(path, name);

            if (delfile.exists()) {

                /* 파일이 존재할 경우 해당 디렉토리가 담고 있는 파일의 리스트를 추출 */
                String[] fileList = delfile.list();

                /* 파일리스트를 for문으로 돌리면서 디렉토리 내부의 파일을 삭제 */
                if (delfile.isDirectory() && (fileList.length != 0)) {
                    for (int index = 0; index < fileList.length; index++) {
                        File chileFile = checkNull(delfile.getAbsolutePath(), "");
                        if (chileFile.isDirectory())

                            /* 파일리스트의 인자가 디렉토리일 경우 해당 디렉토리의 내부를 검사*/
                            deleteChild(chileFile, fileList[index]);
                        else if (chileFile.isFile())
                            chileFile.delete();
                    }
                }

                /* 리스트 파일을 전부 삭제한 빈 디렉토리 삭제 */
                delfile.delete();
            }

        }
        catch (Exception ex) {
           //Logging4J.logWrite(DownLoadServlet.class,Logging4JMode.ERROR_LEVEL,"파일 삭제시 에러 발생");
        }
    }

    /**
     * 디렉토리 삭제 시 디렉토리의 내부에 존재하는 파일을 먼저 삭제하고 빈 디렉토리를 삭제한다.
     *
     * @param file      삭제 대상 디렉토리 정보로 생성된 File
     * @param fileName  삭제 대상 파일명
     */
    public static void deleteChild(File file, String fileName) throws MainException {
        File childFile = null;
        try {
            /* 검색 대상 파일이 존재하는 디렉토리 일 경우 실행  */
            if (file.exists() && file.isDirectory()) {

                /* 대상 디렉토리 내부 파일의 리스트를 추출  */
                String[] fileList = file.list();

                if (fileList.length != 0) {

                    for (int index = 0; index < fileList.length; index++) {

                        childFile = checkNull(file.getAbsolutePath(), fileList[index]);

                        /* 리스트의 파일을 삭제 */
                        if (childFile.isDirectory())
                            deleteChild(childFile, fileList[index]);
                        else if (childFile.isFile())
                            childFile.delete();
                    }
                }

                /* 리스트 파일을 전부 삭제한 빈 디렉토리 삭제 */
                file.delete();
            }
        }
        catch (Exception ex) {
            throw ExceptionHelper.getException(ex,"deleteChild() 중 에러 발생");
        }
    }

    /**
     * 디렉토리나 파일의 경로와 이름을 입력받아 File 객체를 리턴한다.
     *
     * @param String path   파일 혹은 디렉토리 위치 경로
     * @param String name   파일 혹은 디렉토리명
     * @return File
     */
    public static File checkNull(String path, String name) throws MainException {
        try {
            /* 디렉토리나 파일의 경로와 이름의 null여부를 체크. 조건에 맞는 File을 생성 */
            if (name == "")
                file = new File(path);
            else
                file = new File(path, name);
            return file;
        }
        catch (Exception ex) {
            throw ExceptionHelper.getException(ex,"checkNull() 중 에러 발생");
        }
    }
}
