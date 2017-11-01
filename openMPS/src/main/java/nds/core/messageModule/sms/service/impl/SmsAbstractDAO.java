package nds.core.messageModule.sms.service.impl;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

public class SmsAbstractDAO extends EgovAbstractDAO {

    /**
     * DB별 sqlMapClient 지정
     */
    @Resource(name = "sqlMapClientSMS")
    public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {

        super.setSuperSqlMapClient(sqlMapClient);

    }

}
