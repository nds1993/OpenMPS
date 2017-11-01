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
package nds.core.operation.notice.service.impl;



import java.util.ArrayList;
import java.util.List;

import nds.core.operation.notice.service.NoticeVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("noticeDAO")
public class NoticeDAO extends EgovAbstractDAO {

    public NoticeDAO() {
        super();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<NoticeVO> selectTopNotiInfo(NoticeVO noticeVO) {
        return (ArrayList<NoticeVO>) list("Notice.selectTopNotiInfo", noticeVO);
    }
    
    public Object insert(NoticeVO noticeVO) {
    	return insert("Notice.insert", noticeVO);
    }

    public int updateByPrimaryKey(NoticeVO noticeVO) {
        return (Integer)update("Notice.updateByPrimaryKey", noticeVO);
    }
    @SuppressWarnings("unchecked")
    public List selectByHelper(NoticeVO noticeVO) {
        return list("Notice.selectByHelper", noticeVO);
    }

    public int selectCountByHelper(NoticeVO noticeVO) {
        return (Integer)selectByPk("Notice.selectCountByHelper", noticeVO);
    }

    public NoticeVO selectByPrimaryKey(NoticeVO noticeVO) {
        return (NoticeVO)selectByPk("Notice.selectByPrimaryKey", noticeVO);
    }

    public int deleteByPrimaryKey(NoticeVO noticeVO) {
        return (Integer)delete("Notice.deleteByPrimaryKey", noticeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List selectDeleteByRecord(NoticeVO noticeVO) {
        return list("Notice.selectDeleteTbvcBlBdByRecord", noticeVO);
    }
    
    public void updateDocuCnt(NoticeVO noticeVO){
    	update("Notice.updateDocuCnt", noticeVO);
    }
    
    @SuppressWarnings("unchecked")
    public List seleteDbrdDocuCommentList(NoticeVO noticeVO) {
        return list("Notice.seleteDbrdDocuCommentList", noticeVO);
    }
}