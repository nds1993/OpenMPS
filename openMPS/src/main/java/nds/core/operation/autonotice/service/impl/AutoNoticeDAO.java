package nds.core.operation.autonotice.service.impl;

import java.util.List;

import nds.core.operation.autonotice.service.AutoNoticeVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("autoNoticeDAO")
public class AutoNoticeDAO extends EgovAbstractDAO{

	public AutoNoticeDAO(){
        super();
    }

	@SuppressWarnings("unchecked")
	public List selectAutoList(AutoNoticeVO autoNoticeVO) {
		return list("AutoNotice.selectAutoList", autoNoticeVO);
	}
	
	public int selectAutoListCount(AutoNoticeVO autoNoticeVO) {
		return (Integer)selectByPk("AutoNotice.selectAutoListCount", autoNoticeVO);
	}

	public String selectInsertAuto(AutoNoticeVO autoNoticeVO) {
		return (String)selectByPk("AutoNotice.selectInsertAuto", autoNoticeVO);
	}

	public Object mergeIntoAuto(AutoNoticeVO autoNoticeVO) {
	    return insert("AutoNotice.mergeIntoAuto", autoNoticeVO);
	}

	public Integer updateByPrimaryKey(AutoNoticeVO autoNoticeVO) {
		 return (Integer)delete("AutoNotice.updateByPrimaryKey", autoNoticeVO);
	}
	
}
