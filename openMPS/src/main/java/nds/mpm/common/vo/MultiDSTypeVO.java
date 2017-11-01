/**
 * 
 */
package nds.mpm.common.vo;

import java.io.Serializable;

/**
 * @author 
 *
 */
public class MultiDSTypeVO implements Serializable
{
	public MultiDSTypeVO()
	{
		
	}
	
	Object rowData;
	String sqlId;
	public Object getRowData() {
		return rowData;
	}
	public void setRowData(Object rowData) {
		this.rowData = rowData;
	}
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
    
	
}
