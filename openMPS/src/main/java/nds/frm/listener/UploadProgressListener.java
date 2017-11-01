package nds.frm.listener;

import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.ProgressListener;

/**
 * Title : Apache Common FileUpload Progress Listener
 * Description : Apache Common FileUpload ��� ���Ͼ��ε��  					
 * 					  ���α׷����� Listen�ϸ� ����� ���ǿ� �����Ѵ�.
 * Author : Someone, ���μ�
 *
 */

public class UploadProgressListener implements ProgressListener{

	private long num100Ks = 0;

	private long theBytesRead = 0;
	private long theContentLength = -1;
	private int whichItem = 0;
	private int percentDone = 0;
	private long startTime = System.currentTimeMillis();
	private long timeInSeconds = 0;
	private double uploadRate = 0;
	private double kbps = 0;	
	private boolean contentLengthKnown = false;	
	private HttpSession prvSession;

	public void update(long bytesRead, long contentLength, int items) {

		if (contentLength > -1) {
			contentLengthKnown = true;
		}
		theBytesRead = bytesRead;
		theContentLength = contentLength;
		whichItem = items;

		long nowNum100Ks = bytesRead / 100000;
		// Only run this code once every 100K
		if (nowNum100Ks > num100Ks) {
			num100Ks = nowNum100Ks;
			if (contentLengthKnown) {
				percentDone = (int) Math.round(100.00 * bytesRead / contentLength);
				//kbps
				timeInSeconds = (System.currentTimeMillis() - startTime) / 1000;
				uploadRate = (double) (bytesRead / (timeInSeconds + 0.00001));				
				kbps = (long)Math.round(uploadRate / 1024);				
			}
		}
				
		//��Ʈ�ѷ����� ���� ����� ������ü�� �������༭ �Ѿ�� 
		//���ǿ� ���α׷��� ���¸� �����Ѵ�. ProgressListener�� update()��
		//� �۵��� �ϴ��� ��Ȯ���� ������ ���� ���ε�� �ݺ������� ���α׷�����
		//������Ʈ �Ǵ°����� ���� ���ǿ� ����ؼ� ���� ����� ���� ��������
		//����ϸ� �ȴ�.
		prvSession.setAttribute("percent", percentDone);
		prvSession.setAttribute("bytesread", theBytesRead);
		prvSession.setAttribute("contentlength", theContentLength);
		prvSession.setAttribute("kbps", kbps);
		//System.out.println(getMessage());
	}
	
	
	public String getMessage() {
		if (theContentLength == -1) {
			return "" + theBytesRead + " of Unknown-Total bytes have been read.";
		} else {
			return "" + theBytesRead + " of " + theContentLength + " bytes have been read (" + percentDone + "% done)";
		}
	}

	public long getNum100Ks() {
		return num100Ks;
	}

	public void setNum100Ks(long num100Ks) {
		this.num100Ks = num100Ks;
	}

	public long getTheBytesRead() {
		return theBytesRead;
	}

	public void setTheBytesRead(long theBytesRead) {
		this.theBytesRead = theBytesRead;
	}

	public long getTheContentLength() {
		return theContentLength;
	}

	public void setTheContentLength(long theContentLength) {
		this.theContentLength = theContentLength;
	}

	public int getWhichItem() {
		return whichItem;
	}

	public void setWhichItem(int whichItem) {
		this.whichItem = whichItem;
	}

	public int getPercentDone() {
		return percentDone;
	}

	public void setPercentDone(int percentDone) {
		this.percentDone = percentDone;
	}

	public boolean isContentLengthKnown() {
		return contentLengthKnown;
	}

	public void setContentLengthKnown(boolean contentLengthKnown) {
		this.contentLengthKnown = contentLengthKnown;
	}

	public void setHttpSession(HttpSession session) {
		this.prvSession = session;
	}
	public HttpSession getHttpSession(){
		return prvSession;
	}
}