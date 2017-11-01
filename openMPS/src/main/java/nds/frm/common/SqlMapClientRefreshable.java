package nds.frm.common;


public interface SqlMapClientRefreshable {

	void refresh() throws Exception;
	

	void setCheckInterval(int ms);
	
}
