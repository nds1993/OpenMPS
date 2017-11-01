package nds.core.systemsettings.service.service.impl;

import java.util.List;

import javax.annotation.Resource;

import nds.core.systemsettings.service.service.VocServiceService;
import nds.core.systemsettings.service.service.VocServiceVO;
import nds.frm.exception.ExceptionHelper;
import nds.frm.exception.MainException;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("vocServiceService")
public class VocServiceServiceImpl extends AbstractServiceImpl  implements VocServiceService {

	
	@Resource(name="vocServiceServiceDAO")
    private VocServiceServiceDAO vocServiceServiceDAO;
	
    /**
     * 서비스관리 수정
     * @param tbcr9900
     * @throws MainException
     */
    public void updateService(VocServiceVO vocServiceVO) throws MainException {
        try{
        	vocServiceServiceDAO.updateByPrimaryKeySelective(vocServiceVO);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "updateService() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"서비스관리 수정",ex.getMessage()} 
            );
        }        
    }
    
    /**
     * 서비스관리 건수조회
     * @param key
     * @return int
     * @throws MainException
     */
    public int selectServiceCount(VocServiceVO key) throws MainException {
        int intTotalCount = 0;
        try{

        	intTotalCount = vocServiceServiceDAO.selectCount(key);
            
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectServiceCount() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"서비스관리  건수조회",ex.getMessage()} 
            );
        }        
        return intTotalCount;
    }
    
    
    /**
     * 서비스관리 조회
     * @param key
     * @return List
     * @throws MainException
     */
    public List selectService(VocServiceVO key) throws MainException {
        List list = null;
        try{
            list = vocServiceServiceDAO.select(key);
        }catch(Exception ex){
            throw ExceptionHelper.getException(ex
                    , this.getClass().getName() + " : " + "selectService() 에러 발생"
                    ,"SYS001"
                    ,new Object[] {"서비스관리 조회",ex.getMessage()} 
            );
        }        
        return list;
    }   
	
}
