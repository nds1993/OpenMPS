package nds.frm.util;

/**
 * <b>class : </b> PageUtil
 * <b>Class Description</b><br>
 * Page 조회시에 총페이지 수와 시작페이지, 종료페이지등을 계산하는 기능을 제공하는 Utility Class 이다.
 * <b>History</b><br>
 * <pre>      : 2009.06.11 초기작성(임진식)</pre>
 * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
 * @version 1.0
 */
public class PageUtil {

    int pagesPerScreen;	    // 화면당 페이지의 수

    int pageNo;		        // 현재 페이지
    int rowsPerPage;		// 페이지당 열의 수
    int totalRowCount;		// 총 열의 수

    int totalPageCount;		// 총 페이지의 수
    int startPageNo;		// 시작 페이지
    int endPageNo;			// 마지막 페이지

    public PageUtil() {
        pageNo 	    = 1;
        startPageNo	= 1;
        endPageNo	= 1;

        rowsPerPage     = 10;
        pagesPerScreen	= 10;

        totalRowCount 	= 0;
        totalPageCount	= 1;
    }

    /**
     * Gets the pageNo
     * @return Returns a int
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * Sets the pageNo
     * @param pageNo The pageNo to set
     */
    public void setPageNo(String pageNo) {
        if(! CommonUtil.isEmpty(pageNo)) {
            try {
                this.pageNo = Integer.parseInt(pageNo);
                setAssociatedFields();
            } catch(Exception e) {
            }
        }
    }

    /**
     * Gets the rowsPerPage
     * @return Returns a int
     */
    public int getRowsPerPage() {
        return rowsPerPage;
    }

    /**
     * Sets the rowsPerPage
     * @param rowsPerPage The rowsPerPage to set
     */
    public void setRowsPerPage(String rowsPerPage) {
        if(!CommonUtil.isEmpty(rowsPerPage)) {
            try {
                this.rowsPerPage = Integer.parseInt(rowsPerPage);
                setAssociatedFields();
            } catch(Exception e) {
            }
        }
    }

    /**
     * Gets the totalRowCount
     * @return Returns a int
     */
    public int getTotalRowCount() {
        return totalRowCount;
    }

    /**
     * Sets the totalRowCount
     * @param totalRowCount The totalRowCount to set
     */
    public void setTotalRowCount(String totalRowCount) {
        if(!CommonUtil.isEmpty(totalRowCount)) {
            try {
                this.totalRowCount = Integer.parseInt(totalRowCount);
                setAssociatedFields();
            } catch(Exception e) {
            }
        }
    }

    public int getStartPageNo() {
        return this.startPageNo;
    }

    public int getEndPageNo() {
        return this.endPageNo;
    }

    public int getTotalPageCount() {
        return this.totalPageCount;
    }

    /**
     * 현재 페이지번호에 따른 화면에 보여지는 데이터의 시작위치를 리턴
     * @param intPageSize int
     * @param intPageNo int
     * @return int
     */
    public int getCalcStartRowNo(int intPageSize, int intPageNo) {
        int intReturn;
        intReturn = (intPageNo * intPageSize) - intPageSize;
        return intReturn;
    }

    /**
     * Sets the value of associated fields
     */
    protected void setAssociatedFields() {
        totalPageCount = new Double(Math.ceil((double)this.totalRowCount/this.rowsPerPage)).intValue();
        if(totalPageCount == 0) {
            totalPageCount = 1;
        }

        startPageNo = (pageNo-1) / pagesPerScreen * pagesPerScreen + 1;
        int temp = startPageNo + (pagesPerScreen - 1);
        if( temp > totalPageCount) {
            endPageNo = totalPageCount;
        } else {
            endPageNo = temp;
        }
    }

    /**
     * Gets the pagesPerScreen
     * @return Returns a int
     */
    public int getPagesPerScreent() {
        return pagesPerScreen;
    }

    /**
     * Sets the pagesPerScreen
     * @param pagesPerScreen The pagesPerScreen to set
     */
    public void setPagesPerScreen(String pagesPerScreen) {
        if(!CommonUtil.isEmpty(pagesPerScreen)) {
            try {
                this.pagesPerScreen = Integer.parseInt(pagesPerScreen);
                setAssociatedFields();
            } catch(Exception e) {
            }
        }
    }
    
    /**
     * 총 페이지 정보를 보여주는 메소드
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     * @param totalCount 총 데이터건수
     * @param pageSize 페이지 출력 수
     * @param pageNo 현재 페이지번호
     * @return 페이지 네이게이션 - 현재 페이지/총페이지 (총건수)
     */
    public static String getPageNavi(int totalCount, int pageSize, int pageNo) {
    	return getPageNavi(totalCount, pageSize, pageNo, totalCount);
    } 

    /**
     * 총 페이지 정보를 보여주는 메소드
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     * @param totalCount 총 데이터건수
     * @param pageSize 페이지 출력 수
     * @param pageNo 현재 페이지번호
     * @param detailCount 실제 데이터건수
     * @return 페이지 네이게이션 - 현재 페이지/총페이지 (총건수)
     */
    public static String getPageNavi(int totalCount, int pageSize, int pageNo, int detailCount) {
        String pageNavi = "";
        int totPageCount = 0;
                
        if (pageNo < 1)
            pageNo = 1;     

        totPageCount = new Double(Math.ceil((double)totalCount/pageSize)).intValue();
        if(totPageCount == 0) {
            totPageCount = 1;
        }
        
        pageNavi = StringUtil.getMoneyForm(String.valueOf(pageNo))+"/"+ StringUtil.getMoneyForm(String.valueOf(totPageCount)) +" (총 "+ 
            StringUtil.getMoneyForm(String.valueOf(detailCount))+" 건)";

        return pageNavi;
    } 
    
    /**
     * 리스트의 순번을 반환
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     * @param pageNo 현재 페이지번호
     * @param pageSize 페이지 출력 수
     * @param idx 현재 리스트번호
     * @return 리스트 순번
     */
    public static String getListSeq(String pageNo, int pageSize, int idx) {
    	int listSeq = 0;
    	int pagecount = 0;
    	
    	if (StringUtil.null2zeroint(pageNo) < 1) pagecount = 1; 
    	else pagecount = StringUtil.null2zeroint(pageNo);
    	if (pageSize < 1) pageSize = 10;
    	
    	listSeq = ((pagecount-1)*pageSize) + idx + 1;
       
    	return String.valueOf(listSeq);
    }

    /**
     * 리스트의 순번을 반환
     * @author <a href="mailto:jsyim1@nds.co.kr">임진식</a>
     * @param pageNo 현재 페이지번호
     * @param pageSize 페이지 출력 수
     * @param idx 현재 리스트번호
     * @return 리스트 순번
     */
    public static String getListSeqDesc(int totalCount, String pageNo, int pageSize, int idx) {
        int listSeq = 0;
        int pagecount = 0;
        //전체건수-((현재페이지번호-1)*페이지당표시건수+로우인덱스)
        if (StringUtil.null2zeroint(pageNo) < 1) pagecount = 1; 
        else pagecount = StringUtil.null2zeroint(pageNo);
        if (pageSize < 1) pageSize = 10;
        
        listSeq = totalCount - ((pagecount-1)*pageSize + idx);
       
        return String.valueOf(listSeq);
    }    
    
}
