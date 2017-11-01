package nds.core.common.common.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>class : Authority </b>
 * <b>Class Description</b><br>
 * 권한정보를 보관하는 Class
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class Authority implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> ROLE_CD;   
    private Map<String, String> BUTTON_ID;
    private Map<String, String> BUTTON_NM;
    private Map<String, String> BUTTON_CD;
    private List ROLE_LIST;   
    
    public Authority() {
        this.ROLE_CD   = new HashMap<String, String>();
        this.BUTTON_ID = new HashMap<String, String>();
        this.BUTTON_NM = new HashMap<String, String>();
        this.BUTTON_CD = new HashMap<String, String>();
    }

	public Map<String, String> getBUTTON_ID() {
		return BUTTON_ID;
	}

	public void setBUTTON_ID(Map<String, String> button_id) {
		BUTTON_ID = button_id;
	}

	public Map<String, String> getBUTTON_NM() {
		return BUTTON_NM;
	}

	public void setBUTTON_NM(Map<String, String> button_nm) {
		BUTTON_NM = button_nm;
	}
	
	public Map<String, String> getBUTTON_CD() {
		return BUTTON_CD;
	}

	public void setBUTTON_CD(Map<String, String> button_cd) {
		BUTTON_CD = button_cd;
	}

	public Map<String, String> getROLE_CD() {
		return ROLE_CD;
	}

	public void setROLE_CD(Map<String, String> role_cd) {
		ROLE_CD = role_cd;
	}

	public List getROLE_LIST() {
		return ROLE_LIST;
	}

	public void setROLE_LIST(List role_list) {
		ROLE_LIST = role_list;
	}

}