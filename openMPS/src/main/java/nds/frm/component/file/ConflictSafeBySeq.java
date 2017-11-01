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

/**
 * <pre>
 * 기본적으로 제공되는 Fileupload Policy Class이다.
 * 이 Policy Class는 Upload된 file의 name이 target directory에서 충돌할 경우,
 * 파일이름을 변경함으로써 Overwrite를 방지하는 이름변경 정책을 제시하고 있다.
 * 이 Policy Class에서 제공하는 이름변경 정책은 아래와 같다.
 * - 파일이름의 충돌이 발생하지 않는한 파일이름은 변경되지 않고 저장될 것이다.
 * - 동일한 파일이름이 존재하는 경우 파일이름.확장자의 형태를 파일이름[숫자].확장자의 형태로 변경하여 저장한다.
 * - 이 경우, [숫자]는 0에서 부터 순차적으로 증가한다.
 *
 * EX)
 * 1번째 upload : abcd.txt
 * 2번째 upload : abcd[0].txt
 * 3번째 upload : abcd[1].txt
 * 4번째 upload : abcd[2].txt
 * 5번째 upload : abcd[3].txt
 *
 * 참고) 이 Policy Class는 Upload될 파일의 제한을 가하는 정책은 제시하고 있지 않다.
 *
 * @author 이선호
 * @version 1.0
 * @since 1.0
 */
public class ConflictSafeBySeq {

    /**
     * ConflictSafeBySeq Class 생성자
     */
    public ConflictSafeBySeq() {

    }

    /**
    * Upload된 filename이 적합한지 검사하여, 필요에 따라 정책에 적합한 이름으로 재구성한다.<BR>
    * abcd.txt -> abcd[0].txt -> abcd[1].txt 와 같은 형태로 filename이 변경된 file 객체를 리턴한다.
    *
    * @param  file filename 정책을 적용할 file객체
    * @return 정책이 적용된 filename
    */
    public static File getConflictSafeFile(File file) {
        if (!file.exists())
            return file;
        String filename = file.getName();
        int lastDot = filename.lastIndexOf('.');
        String extension = (lastDot == -1) ? "" : filename.substring(lastDot);
        String prefix =
            (lastDot == -1) ? filename : filename.substring(0, lastDot);
        int count = 0;
        do {
            file =
                new File(
                    FileUtil.getCompleteLeadingSeperator(file.getParent())
                        + prefix
                        + "["
                        + count
                        + "]"
                        + extension);
            count++;
        } while (file.exists());

        return file;
    }
}
