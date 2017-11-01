package nds.core.common.common.service;



import java.util.List;



/**
 * <b>class : AttachfileService </b>
 * <b>Class Description</b><br>
 * 첨부파일 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2012.06.25 초기작성(소건)</pre>
 * @author <a href="mailto:gso@nds.co.kr">소건</a>
 * @version 1.0
 */
public interface AttachfileService extends Service{

    /**
     * 첨부파일정보 등록
     * @param model
     */
    void insertAttachFile(AttachFileVO model) throws Exception;
    
    /**
     * 첨부파일정보 등록
     * @param model
     */
    void insertHomepageAttachFile(AttachFileVO model) throws Exception;
    
    /**
     * 첨부파일정보 삭제
     * @param key
     */
    void deleteAttachFile(AttachFileVO key) throws Exception;
    /**
     * 첨부파일일괄 삭제
     * @param list
     */
    void deleteAttachFile(List list) throws Exception;
    
    /**
     * 첨부파일정보 조회
     * @param srNo
     * @param cntn
     * @return
     */
    List getAttachFileList(String docRegNo, String contsId) throws Exception;
    /**
     * 첨부파일정보 조회 	PIS
     * @param srNo
     * @param cntn
     * @return
     */
    List getAttachFileList(String keyVal01,String keyVal02,String keyVal03,String keyVal04) throws Exception;
    
    /**
     * 첨부파일 임시정보 삭제
     * @param key
     */
    void deleteTempFile(String key) throws Exception;

    /**
     * 파일 리스트 조회
     * @param helper
     * @return List
     * @throws Exception
     */    
    List selectFileList(AttachFileVO tbvcAtchFile) throws Exception;
    
    /**
     * 시험성적 농심 파일 리스트 조회
     * @param helper
     * @return List
     * @throws Exception
     */    
    List selectNsTestFileList(AttachFileVO tbvcAtchFile) throws Exception;

    /**
     * 첨부파일정보 조회
     * @param srNo
     * @param cntn
     * @return
     */
    List selectByRegNo(String cntn, String contsId) throws Exception;
    

    
    /**
     * 파일 개별 조회
     * @param helper
     * @return AttachFileVO
     * @throws Exception
     */    
    AttachFileVO selectByPk(AttachFileVO attachFileVO) throws Exception;

	
    /**
     * 사진등록건요청조회 파일조회
     * @param docRegNo
     * @param contsId
     * @return
     */
    List getPotoAttachFileList(String docRegNo, String contsId) throws Exception;
    
    /**
     * 사진등록건요청조회 파일조회
     * @param docRegNo
     * @param contsId
     * @return
     */
   
}
