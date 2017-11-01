package nds.frm.taglib;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import nds.frm.config.StaticConfig;
import nds.frm.util.PageUtil;
import nds.frm.util.StringUtil;




/**
 * <p>Title: 페이지 단위로 리스트시 페이지 표시</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: NDS</p>
 * @author 이선호
 * @version 1.0
 */
public class PageViewer extends TagSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9216983180635970652L;
	private int intTotalCount; // 전체 레코드 수
    private int intPageSize; // 화면에 표시 될 로우 수
    private int intPageNo; // 현재 페이지 번호
    private String function = "movePage";	
    
    public void setIntTotalCount(int intTotalCount) {
        this.intTotalCount = intTotalCount;
    }
    public void setIntTotalCount(String intTotalCount) {
        this.intTotalCount = StringUtil.null2zeroint(intTotalCount);
    }

    public void setIntPageSize(int intPageSize) {
        this.intPageSize = intPageSize;
    }
    public void setIntPageSize(String intPageSize) {
        this.intPageSize = Integer.parseInt(intPageSize);
    }

    public void setIntPageNo(int intPageNo) {
        this.intPageNo = intPageNo;
    }
    public void setIntPageNo(String intPageNo) {
        this.intPageNo = StringUtil.null2zeroint(intPageNo);
    }

    public void setFunction(String function) {
		this.function = function;
	}
    
	public int doStartTag() {

        try {
            JspWriter out = pageContext.getOut();

            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageNo(String.valueOf(intPageNo));
            pageUtil.setRowsPerPage(String.valueOf(intPageSize));
            pageUtil.setTotalRowCount(String.valueOf(intTotalCount));
            
            if(intPageSize < intTotalCount){
                    if (intTotalCount > 0 && intPageNo  < 1)
                    	pageUtil.setPageNo("1");            	
                    
                    out.println("<table style=\"height:16px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">");
                    out.println("<tr>");
        
                    ////////////// 맨앞 설정 , Before 설정 //////////////////////
                    if (pageUtil.getPageNo() > pageUtil.getPagesPerScreent()) {
                        out.println("<td style=\"height:16px;\"><a href=\"javascript:" + function + "(" + 1 + ");\" onMouseOver=\"status='첫페이지로 이동';return true;\" onMouseOut=\"status=''\"><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_prevend.gif\" alt=\"첫페이지\" border=\"0\"></a></td>");
                        out.println("<td width=\"3\"></td>");
                        out.println("<td><a href=\"javascript:" + function + "(" + (pageUtil.getStartPageNo() - 1) + ");\" onMouseOver=\"status='이전 10개 페이지';return true;\" onMouseOut=\"status=''\"><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_prev.gif\" alt=\"이전\" border=\"0\"></a></td>");
                        out.println("<td width=\"2\"></td>");
                    }
                    else {
                        out.println("<td style=\"height:16px;\"><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_prevend_d.gif\" alt=\"첫페이지\" border=\"0\"></td>");
                        out.println("<td width=\"3\"></td>");
                        out.println("<td><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_prev_d.gif\" alt=\"이전\" border=\"0\"></td>");
                        out.println("<td width=\"2\"></td>");
                    }
        
                    ////////////// 페이지 ///////////////////////////
                    out.println("<td class=\"page_txt\" nowrap>");
                    for (int inx = pageUtil.getStartPageNo();
                         inx <= pageUtil.getEndPageNo(); inx++) {       	
                        if (inx == pageUtil.getPageNo()) {  
                        	
                            out.println("&nbsp;<B>" + inx + "</B>&nbsp;");
                        }
                        else {
                            out.println("&nbsp;<a href=\"javascript:" + function + "(" + inx + ");\" onMouseOver=\"status='" + inx + " 페이지로 이동'; return true;\" onMouseOut=\"status=''\">" + inx + "</a>&nbsp;");
                        }
                    }
                    out.println("</td>");
        
                    ////////////// Next , 맨끝 //////////////////////////////
                    if (pageUtil.getEndPageNo() < pageUtil.getTotalPageCount()) {
                    	out.println("<td width=\"2\"></td>");
                        out.println("<td style=\"height:16px;\"><a href=\"javascript:" + function + "(" + (pageUtil.getEndPageNo() + 1) + ");\" onMouseOver=\"status='다음 10개 페이지'; return true;\" onMouseOut=\"status=''\"><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_next.gif\" alt=\"다음\" border=\"0\"></a></td>");
                        out.println("<td width=\"3\"></td>");
                        out.println("<td><a href=\"javascript:" + function + "(" + pageUtil.getTotalPageCount() + ");\" onMouseOver=\"status='마지막 페이지로 이동'; return true;\" onMouseOut=\"status=''\"><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_nextend.gif\" alt=\"마지막페이지\" border=\"0\"></a></td>");
                    }
                    else {
                    	out.println("<td width=\"2\"></td>");
                        out.println("<td style=\"height:16px;\"><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_next_d.gif\" alt=\"다음\" border=\"0\"></td>");
                        out.println("<td width=\"3\"></td>");
                        out.println("<td><img src=\"" + StaticConfig.CONTEXT_PATH + "/html/images/icon/btn_nextend_d.gif\" alt=\"마지막페이지\" border=\"0\"></td>");
                    }
        
                    out.println("  </tr>");
                    out.println("</table>");
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
