/**
 * 
 */
package nds.mpm.common.vo;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author jerry
 *
 */
public class UploadFileVO 
{
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

	
}
