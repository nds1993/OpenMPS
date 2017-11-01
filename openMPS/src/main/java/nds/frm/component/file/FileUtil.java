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
package nds.frm.component.file;

import java.io.File;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

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

}