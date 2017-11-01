package nds.core.systemsettings.service.service.impl;

import java.util.List;

import nds.core.systemsettings.service.service.VocServiceVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("vocServiceServiceDAO")
public class VocServiceServiceDAO extends EgovAbstractDAO {

    public VocServiceServiceDAO() {
        super();
    }

    public int updateByPrimaryKeySelective(VocServiceVO record) {
        int rows = getSqlMapClientTemplate().update("VocService.updateByPrimaryKeySelective", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
    public List<VocServiceVO> select(VocServiceVO key) {
        List<VocServiceVO> list = (List<VocServiceVO>) list("VocService.select", key);
        return list;
    }

    public int selectCount(VocServiceVO key) {
        int counts = Integer.parseInt(selectByPk("VocService.selectCount", key).toString());
        return counts;
    }
    

}
