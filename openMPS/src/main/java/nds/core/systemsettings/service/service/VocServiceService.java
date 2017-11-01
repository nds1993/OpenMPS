package nds.core.systemsettings.service.service;

import java.util.List;

import nds.frm.exception.MainException;

public interface VocServiceService {

	
    /**
     * 서비스관리 수정
     * @param tbcr9900
     * @throws MainException
     */
    public void updateService(VocServiceVO vocServiceVO) throws MainException;
    
    /**
     * 서비스관리 건수조회
     * @param key
     * @return int
     * @throws MainException
     */
    public int selectServiceCount(VocServiceVO vocServiceVO) throws MainException;
    /**
     * 서비스관리 조회
     * @param key
     * @return List
     * @throws MainException
     */
    public List selectService(VocServiceVO vocServiceVO) throws MainException;
	
	
}
