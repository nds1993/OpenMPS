/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.core.logsearch.loginlog.service.impl;



import java.util.List;

import nds.core.logsearch.loginlog.service.LoginLogVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * <b>class : LoginLogDAO </b>
 * <b>Class Description</b><br>
 * 로그인로그를 담당하는 Class
 * <b>History</b><br>
 * <pre>      : 2013.12.23 초기작성(김태호)</pre>
 * @author <a href="mailto:thkim@nds.co.kr">김태호</a>
 * @version 1.0
 */
@Repository("loginLogDAO")
public class LoginLogDAO extends EgovAbstractDAO {

    public LoginLogDAO() {
        super();
    }

    /**
     * 로그인로그 업데이트(LoginManager에서 처리)
     * @param LoginLogVO
     * @return int
     * @throws Exception
     */
    public Object updateByPrimaryKeySelective(LoginLogVO loginLogVO) throws Exception{
        return (Integer)update("LoginLog.updateByPrimaryKeySelective", loginLogVO);
    }
    
    /**
     * 로그인로그 저장
     * @param LoginLogVO
     * @return Object
     * @throws Exception
     */
    public Object insert(LoginLogVO loginLogVO) throws Exception{
        return (String)insert("LoginLog.insert", loginLogVO);
    }
    
    /**
     * 로그인로그 조회
     * @param LoginLogVO
     * @return List
     * @throws Exception
     */
	public List selectLoginLogList(LoginLogVO loginLogVO) throws Exception{
		return list("LoginLog.selectLoginList", loginLogVO);
	}
	
    /**
     * 로그인로그카운트 조회
     * @param LoginLogVO
     * @return int
     * @throws Exception
     */
	public int selectLoginLogListCount(LoginLogVO loginLogVO) throws Exception{
		return (Integer)selectByPk("LoginLog.selectLoginListCount", loginLogVO);
	}

    /**
     * 접속자 총카운트 조회
     * @param LoginLogVO
     * @return int
     * @throws Exception
     */
	public int selectConnectCount(LoginLogVO loginLogVO) throws Exception{
		return (Integer)selectByPk("LoginLog.selectConnectCount", loginLogVO);
	}
	
}