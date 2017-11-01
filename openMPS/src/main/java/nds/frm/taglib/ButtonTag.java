package nds.frm.taglib;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.DataSource;

import nds.frm.util.StringUtil;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * <p>Title: 공통코드로 자동으로 버튼을 구성하는 taglib</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: NDS</p>
 * @author 최찬형
 * @version 1.0
 */
public class ButtonTag extends BodyTagSupport{
	/**
     * 
     */
    private static final long serialVersionUID = 8340222086768835012L;
    
    private String btnColor;
    
    private String btnName;
    
    private String webFont;
    
    private String onclick;
    
    private String fontWeight;
    
    private String fontColor;

    


	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getFontWeight() {
		return fontWeight;
	}

	public void setFontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
	}

	public String getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(String btnColor) {
		this.btnColor = btnColor;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public String getWebFont() {
		return webFont;
	}

	public void setWebFont(String webFont) {
		this.webFont = webFont;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
    
	public int doStartTag() {
		try {
            webFont = StringUtil.null2void(webFont);
            btnColor = StringUtil.null2void(btnColor);
            btnName = StringUtil.null2void(btnName);
            onclick = StringUtil.null2void(onclick);
            fontColor = StringUtil.null2void(fontColor);
            fontWeight = StringUtil.null2void(fontWeight);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return SKIP_BODY;
	}
    
	public int doEndTag() throws JspException {
		String Query = "";
		StringBuffer sb = new StringBuffer();
		String styleType = "";
		try{
			
			if(!onclick.equals(""))
				styleType = "onclick="+onclick;
			
			if(btnColor.equals("")){
				sb.append("<style>");
				sb.append(".btn1 .fontButton {");
				sb.append("	height:20px;");
				sb.append("}");
				sb.append(".btn1 .ui-button-text-only .ui-button-text {");
				sb.append("padding: 3px 10px;}");
				sb.append(".btn1 .ui-state-default, ");
				sb.append(".btn1 .ui-widget-content .ui-state-default, ");
				sb.append(".btn1 .ui-widget-header .ui-state-default {");
				sb.append("border: 1px solid #707474;");
				sb.append("background: #7B8080;");
				sb.append("font-weight: normal;");
				sb.append("color: #fff;");
				sb.append("margin: 0;");
				sb.append("padding: 0;}");
				sb.append("	}");
				sb.append("</style>");
				
				sb.append("<span class='btn1' "+styleType+"> ");

			}else if(btnColor.equals("O")){
				sb.append("<style>");
				sb.append(".btn2 .fontButton {");
				sb.append("	height:20px;");
				sb.append("}");
				sb.append(".btn2 .ui-button-text-only .ui-button-text {");
				sb.append("padding: 3px 10px;}");
				sb.append(".btn2 .ui-state-default, ");
				sb.append(".btn2 .ui-widget-content .ui-state-default, ");
				sb.append(".btn2 .ui-widget-header .ui-state-default {");
				sb.append("border: 1px solid #E9680C;");
				sb.append("background: #F58C0F;");
				sb.append("font-weight: normal;");
				sb.append("color: #fff;");
				sb.append("margin: 0;");
				sb.append("padding: 0;}");
				sb.append("	}");
				sb.append("</style>");
				
				sb.append("<span class='btn2' "+styleType+"> ");
			}else if(btnColor.equals("G")){
				sb.append("<style>");
				sb.append(".btn3 .fontButton {");
				sb.append("	height:20px;");
				sb.append("}");
				sb.append(".btn3 .ui-button-text-only .ui-button-text {");
				sb.append("padding: 3px 10px;}");
				sb.append(".btn3 .ui-state-default, ");
				sb.append(".btn3 .ui-widget-content .ui-state-default, ");
				sb.append(".btn3 .ui-widget-header .ui-state-default {");
				sb.append("border: 1px solid #555;");
				sb.append("background: #EEE;");
				sb.append("font-weight: normal;");
				sb.append("color: #333;");
				sb.append("margin: 0;");
				sb.append("padding: 0;}");
				sb.append("	}");
				sb.append("</style>");
				
				sb.append("<span class='btn3' "+styleType+"> ");
			}else{
				if(fontWeight.equals("")){
					fontWeight = "normal";
				}
				
				if(fontColor.equals("")){
					fontColor = "#fff";
				}
				
				sb.append("<style>");
				sb.append(".customize .fontButton {");
				sb.append("	height:20px;");
				sb.append("}");
				sb.append(".customize .ui-button-text-only .ui-button-text {");
				sb.append("padding: 3px 10px;}");
				sb.append(".customize .ui-state-default, ");
				sb.append(".customize .ui-widget-content .ui-state-default, ");
				sb.append(".customize .ui-widget-header .ui-state-default {");
				sb.append("border: 1px solid "+btnColor+";");
				sb.append("background: "+btnColor+";");
				sb.append("font-weight: "+fontWeight+";");
				sb.append("color: "+fontColor+";");
				sb.append("margin: 0;");
				sb.append("padding: 0;}");
				sb.append("	}");
				sb.append("</style>");
				
				sb.append("<span class='customize'; "+styleType+"> ");
			}
			if(!webFont.equals("")){
				sb.append("<a class='fontButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' href='#' style='font-family:FontAwesome;'>");
				sb.append("<span class='ui-button-text' style='font-size:14px;'>"+webFont+" &nbsp;<span style='font-family:돋움; font-size:12px;'>"+btnName+"</span></span>");
			}else{
				sb.append("<a class='fontButton ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only' href='#' style='height:16px;'>");
				sb.append("<span class='ui-button-text' style='font-family:돋움; font-size:12px; padding:2px 6px;'>"+btnName+"</span>");
			}
			sb.append("</a>");
			sb.append("</span>");
			
			JspWriter out = pageContext.getOut();
			out.print(sb.toString());
		}catch(Exception e)
        {
            throw new JspException("ButtonTag", e);          
        }
		return EVAL_PAGE;  
	}
	
	public void release(){
	        super.release();
	        btnColor = null;        
	        btnName = null;        
	        onclick = null;
	        webFont = null;
	}
}
