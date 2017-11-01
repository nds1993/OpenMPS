/**
 * 
 */
package nds.mpm.common.vo;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author jerry
 *
 */
public class CommonFileVO 
{
	private String fullpath;
	private String orginalName;
	private String newName;
	private String mimeType;
	public String getFullpath() {
		return fullpath;
	}
	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}
	public String getOrginalName() {
		return orginalName;
	}
	public void setOrginalName(String orginalName) {
		this.orginalName = orginalName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	
	
}
