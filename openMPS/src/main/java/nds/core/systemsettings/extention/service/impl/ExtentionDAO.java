package nds.core.systemsettings.extention.service.impl;

import java.util.List;

import nds.core.systemsettings.extention.service.ExtentionVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("extentionDAO")
public class ExtentionDAO extends EgovAbstractDAO{

	public ExtentionDAO(){
		super();
	}
	
	 public void insertExtention(ExtentionVO extentionVO) {
        insert("Extention.insertExtention", extentionVO);
    }
    
    public int updateExtention(ExtentionVO extentionVO) {
        return (Integer) update("Extention.updateExtention", extentionVO);
    }
    
    public int deleteExtention(String fileExt) {
        ExtentionVO key = new ExtentionVO();
        key.setFileExt(fileExt);
        return (Integer) delete("Extention.deleteExtention", key);
    }
    
    @SuppressWarnings("unchecked")
    public List selectExtention(ExtentionVO extentionVO) {
        return list("Extention.selectExtention", extentionVO);
    }
    public int selectExtentionCount(ExtentionVO extentionVO) {
        return (Integer) selectByPk("Extention.selectExtentionCount", extentionVO);
    }
    
    public int selectExtentionIdCount(ExtentionVO extentionVO) {
        return (Integer) selectByPk("Extention.selectExtentionIdCount", extentionVO);
    }
    
}
