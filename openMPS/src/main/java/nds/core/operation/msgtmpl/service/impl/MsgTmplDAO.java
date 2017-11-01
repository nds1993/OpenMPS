package nds.core.operation.msgtmpl.service.impl;

import java.util.List;

import nds.core.operation.emailTmpl.service.EmailTmplVO;
import nds.core.operation.msgtmpl.service.MsgTmplVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("msgTmplDAO")
public class MsgTmplDAO extends EgovAbstractDAO{

	public MsgTmplDAO() {
		super();
	}
	
	public Object insertMsgTmpl(MsgTmplVO msgTmplVO){
		return insert("MsgTmpl.insertMsgTmpl", msgTmplVO);
	}
	
	public int updateMsgTmpl(MsgTmplVO msgTmplVO){
		return (Integer)update("MsgTmpl.updateMsgTmpl", msgTmplVO);
	}
	
	public List selectMsgTmplList(MsgTmplVO msgTmplVO){
		return list("MsgTmpl.selectMsgTmplList", msgTmplVO);
	}
	
	 public int selectMsgTmplListCount(MsgTmplVO msgTmplVO) {
	        return ((Integer)selectByPk("MsgTmpl.selectMsgTmplListCount", msgTmplVO)).intValue();
	    }
	
	public MsgTmplVO selectMsgTmplInfo(MsgTmplVO msgTmplVO){
		return (MsgTmplVO)selectByPk("MsgTmpl.selectMsgTmplInfo", msgTmplVO);
	}
	
	public int deleteMsgTmpl(MsgTmplVO msgTmplVO){
		return (Integer)delete("MsgTmpl.deleteMsgTmpl", msgTmplVO);
	}
	
	public String checkInsertYn(MsgTmplVO msgTmplVO){
		return (String)selectByPk("MsgTmpl.checkInsertYn", msgTmplVO);
	}
}
