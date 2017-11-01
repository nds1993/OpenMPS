package nds.core.logsearch.dwloadlog.service.impl;

import java.util.List;

import nds.core.logsearch.dwloadlog.service.DwloadLogVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


@Repository("dwloadLogDAO")
public class DwloadLogDAO extends EgovAbstractDAO {
	
	public DwloadLogDAO() {
        super();
    }

    public void insertDwLogInfo(DwloadLogVO dwloadLogVO) {
        insert("DwloadLog.insertDwLogInfo", dwloadLogVO);
    }
    
    public List selectDwInfoLogList(DwloadLogVO dwloadLogVO) {
        return list("DwloadLog.selectDwInfoLogList", dwloadLogVO);
    }

    public int selectDwInfoLogListCnt(DwloadLogVO dwloadLogVO) {
        return (Integer) selectByPk("DwloadLog.selectDwInfoLogListCount", dwloadLogVO);
    }
}
