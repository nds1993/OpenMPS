package nds.mpm.common.vo;

/**
 * 페이징에 의한 결과를 담는다.
 * 
 * @author 
 *
 */
public class PageSet
{
	public PageSet()
	{
		
	}
	public PageSet(Object result)
	{
		this.startOrd = 0;
		this.pageSize = 0;
		this.result = result;
	}
	public PageSet(long startOrd, long pageSize)
	{
		this.startOrd = startOrd;
		this.pageSize = pageSize;
		this.result = null;
	}
	public PageSet(long startOrd, long pageSize, Object result)
	{
		this.startOrd = startOrd;
		this.pageSize = pageSize;
		this.result = result;
	}
	
	public long getStartOrd() {
		return startOrd;
	}
	public void setStartOrd(long startOrd) {
		this.startOrd = startOrd;
	}
	public long getPageSize() {
		return pageSize;
	}
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	public final Object getResult() {
		return result;
	}
	public final void setResult(Object result) {
		this.result = result;
	}
	
	
	public long getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}
	

	public long getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}


	private long		startOrd;
	private long		pageIndex;
	private long		pageSize;
	private long		totalRecordCount;
	private long		failCount;
	public long getFailCount() {
		return failCount;
	}
	public void setFailCount(long failCount) {
		this.failCount = failCount;
	}


	private Object		result;
}