/**
 * 
 */
package nds.mpm.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 
 *
 */
public class MultiListVO implements Serializable
{
	public MultiListVO()
	{
		
	}
	
	List<Object> rowData;
	String sqlId;
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public List<Object> getRowData() {
		return rowData;
	}
	public void setRowData(List<Object> rowData) {
		this.rowData = rowData;
	}
    
	
}
