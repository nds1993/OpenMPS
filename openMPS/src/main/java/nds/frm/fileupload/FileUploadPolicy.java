package nds.frm.fileupload;

/**
 * <pre>
 * FileUpload시에 사용될 정책(policy)를 정의한다.
 * 정의될 수 있는 정책은 아래와 같다.
 * - File이 저장될 위치 (폴더)
 * - 한번의 Request를 통하여 Upload될 수 있는 File size의 총합의 제한
 * </pre>
 * @author 이선호
 * @version 1.0
 * @since 1.0
 */
public class FileUploadPolicy {
    public static final String UPLOADPATH = "/attach/Asr/XlsUpload"; // 파일 업/다운로드 경로
    public static final String TEMPFOLDER = "/attach/Asr/TempUpload"; // 파일 임시 보관 폴더
    public static final String TEMPPATH = UPLOADPATH + TEMPFOLDER; // 파일이 임시 업로드될 경로
    public static final int MaxMemorySize = 5*1024*1024; // 한번에 메모리에 저장할 사이즈 설정
    public static final int MaxRequestSize = 100*1024*1024; // 파일 업로드 최대 사이즈를 설정
    public static final String UPFOLDER = "uploadFolder"; // 파일업로드를 처리하는 Hidden변수
}
