package nds.core.systemsettings.menu.service;



import java.util.List;

import nds.core.common.common.service.Service;
import nds.core.systemsettings.button.service.BtnVO;


/**
 * <b>class : MenuService </b>
 * <b>Class Description</b><br>
 * 로그인로그정보 처리를 담당하는 Interface
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
public interface MenuService extends Service {
    
    /**
     * 컨텐츠 조회
     * @return List
     * @throws Exception
     */
    List selectContentsAll() throws Exception;
    /**
     * 컨텐츠 건수조회
     * @param menuVO
     * @return int
     * @throws Exception
     */
    int selectContentsCount(MenuVO menuVO) throws Exception;
    
    /**
     * 컨텐츠 등록
     * @param menuVO
     * @throws Exception
     */
    void insertContents(MenuVO menuVO) throws Exception;
    
    /**
     * 컨텐츠 수정
     * @param menuVO
     * @throws Exception
     */
    void updateContents(MenuVO menuVO) throws Exception;
    
    /**
     * 컨텐츠 삭제
     * @param contsId
     * @throws Exception
     */
    void deleteContents(String contsId) throws Exception;
    
    /**
     * 컨텐츠사용버튼 등록
     * @param menuBtnVO
     * @throws Exception
     */
    void insertContentsButton(MenuBtnVO menuBtnVO) throws Exception;
    
    /**
     * 컨텐츠사용버튼 삭제
     * @param menuBtnVO
     * @throws Exception
     */
    void deleteContentsButton(MenuBtnVO menuBtnVO) throws Exception;
    
    /**
     * 컨텐츠 미사용 버튼목록 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    List selectContsButton(BtnVO btnVO) throws Exception;
    
    /**
     * 컨텐츠사용버튼 조회
     * @param btnVO
     * @return List
     * @throws Exception
     */
    List selectContsUseButton(BtnVO btnVO) throws Exception;
    
    /**
     * 뷰 페이지 목록 조회
     * @return
     * @throws Exception
     */
    List<MenuVO> selectPageList(MenuVO menuVO) throws Exception;
    
    List<MenuVO> selectPageGubn(MenuVO menuVO) throws Exception;
    
    /**
     * 상당대기중인 상담원수
     * @return
     * @throws Exception
     */
    List<MenuVO> getAgentWaitCount(MenuVO menuVO) throws Exception;
    
}
