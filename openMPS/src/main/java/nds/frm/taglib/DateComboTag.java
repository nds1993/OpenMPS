package nds.frm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import nds.frm.util.DateTimeUtil;
import nds.frm.util.StringUtil;



/**
 * <p>Title: 날짜관련 자동으로 콤보박스를 구성하는 taglib</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: NDS</p>
 * @author 임진식
 * @version 1.0
 */
public class DateComboTag
    extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8340222086768835012L;

	private String type;
    private String name;
    private String dateType;
    private String term;
    private String orderBy;
    private String dateCnt;
    private String selectedValue;
    private String defaultValue;
    private String notnull;
    private String disabled;
    private String colname;
    private String selectType;
    private String style;
    private String className;
    private String onChange;
    private String onClick;
    private String nextFocus;
    private String except;

	public void setExcept(String except) {
		this.except = except;
	}
	public void setType(String type) {
		this.type = type;
	}
    public void setName(String name) {
        this.name = name;
    }  
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}    
    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }    
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}	
	public String getDateCnt() {
		return dateCnt;
	}
	public void setDateCnt(String dateCnt) {
		this.dateCnt = dateCnt;
	}	
    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}    
    public void setNotnull(String notnull) {
        this.notnull = notnull;
    }
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
    public void setColname(String colname) {
        this.colname = colname;
    }    
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    } 
    public void setStyle(String style) {
        this.style = style;
    } 
    public void setClassName(String className) {
        this.className = className;
    } 
    public void setOnChange(String onChange) {
        this.onChange = onChange;
    } 
    public void setOnClick(String onClick) {
        this.onClick = onClick;
    } 
    public String getNextFocus() {
        return nextFocus;
    }
    public void setNextFocus(String nextFocus) {
        this.nextFocus = nextFocus;
    }
    
    public int doStartTag() {
        try {
        	type = StringUtil.null2void(type);
        	name = StringUtil.null2void(name);
        	dateType = StringUtil.null2void(dateType);
            term = StringUtil.null2void(term);
        	orderBy = StringUtil.null2void(orderBy);
        	dateCnt = StringUtil.null2zeroString(dateCnt);
        	selectedValue = StringUtil.null2void(selectedValue).replace("<c:out value=\"","").replace("\"/>","");
        	defaultValue = StringUtil.null2void(defaultValue);
        	notnull = StringUtil.null2void(notnull);
        	disabled = StringUtil.null2void(disabled);
        	colname = StringUtil.null2void(colname);
        	selectType = StringUtil.null2void(selectType);
        	style = StringUtil.null2void(style);
        	className = StringUtil.null2void(className);
        	onChange = StringUtil.null2void(onChange);
        	onClick = StringUtil.null2void(onClick);
            nextFocus = StringUtil.null2void(nextFocus);
        	except = StringUtil.null2void(except);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspException {
    	
    	StringBuffer sb = new StringBuffer();
    	String rtnValue ="";
    	String styleType = "";
    	boolean typeChk = true;
    	
    	try
    	{    		
    		if (!className.equals(""))
    			styleType += "class='"+className+"' ";
    		if (!style.equals(""))
    			styleType += "style='"+style+"' ";
    		if (!onChange.equals(""))
    			styleType += "onChange='"+onChange+"' ";
    		if (!onClick.equals(""))
    			styleType += "onClick='"+onClick+"' ";    		
    		if (notnull.toUpperCase().equals("Y"))
    			styleType += "notnull ";
    		if (disabled.toUpperCase().equals("Y"))
    			styleType += "disabled ";
    		if (!colname.equals(""))
    			styleType += "colname='"+colname+"' ";
    		
    		if (type.toUpperCase().equals("RADIO") || type.toUpperCase().equals("CHECKBOX"))
    			typeChk = false;
    		
    		if (typeChk)
    		{    		
                if (!nextFocus.equals("")) styleType += "nextFocus='"+nextFocus+"' ";

                sb.append("<select id='"+name+"' name='"+name+"' "+ styleType +">");
	    		
	    		if (!selectType.equals(""))
	    		{
	    			sb.append("<option value='' ");
	    			
	    			if (selectedValue.equals("")) sb.append("selected ");
	    			
	    			sb.append(">");
	    			
	    			if (selectType.equals("S")) sb.append("-선택-");
	    			else if (selectType.equals("A")) sb.append("-전체-");
                    else if (selectType.equals("T")) sb.append("직접입력");
	    			
	    			sb.append("</option>");
	    		}
    		}
    		else
    		{
    		
	    		if (!selectType.equals(""))
	    		{
	    			sb.append("<input type='"+type+"' id='"+name+"' name='"+name+"' value='' "+ styleType);
	    			
	    			if (selectedValue.equals(""))
	    				sb.append("checked ");
	    			
	    			sb.append(">");
	    			
	    			if (selectType.equals("S"))
	    				sb.append("선택");
	    			else if (selectType.equals("A"))
	    				sb.append("전체");
	    		}    			
    		}
    		
	    	 int startIdx = 0;
	    	 int endIdx = 0;
             int termValue = ("".equals(term)) ? 1 : Integer.parseInt(term);
	    	 int valueLen = 0;	    	 
    		
    		if (dateType.toUpperCase().equals("YYYY"))
    		{	 
    			valueLen = 4;
    	    	 if (orderBy.toUpperCase().equals("ASC"))
    	    	 {
    	    		 startIdx = DateTimeUtil.getNowDate()[0];
    	    	 	 endIdx = DateTimeUtil.getNowDate()[0] + Integer.parseInt(StringUtil.null2zeroString(dateCnt));
    	    	 }
    	    	 else if (orderBy.toUpperCase().equals("DESC"))
    	    	 {
    	    		 startIdx = DateTimeUtil.getNowDate()[0] - Integer.parseInt(StringUtil.null2zeroString(dateCnt));
    	    		 endIdx = DateTimeUtil.getNowDate()[0];
    	    	 }
    	    	 //조회시작년도 기본 set (2008.3.11 추가)
    	    	 else if (orderBy.toUpperCase().equals("DEFDESC"))
    	    	 {
    	    		 startIdx = Integer.parseInt(StringUtil.null2zeroString(dateCnt));
    	    		 endIdx = DateTimeUtil.getNowDate()[0];
    	    	 }
    	    	 else
    	    	 {
    	    		 startIdx = DateTimeUtil.getNowDate()[0] - Integer.parseInt(StringUtil.null2zeroString(dateCnt));
    	    		 endIdx = DateTimeUtil.getNowDate()[0] + Integer.parseInt(StringUtil.null2zeroString(dateCnt));    	 
    	    	 }   
    		}
    		else if (dateType.toUpperCase().equals("MM"))
    		{
    			valueLen = 2;
    			startIdx = 1;
    			endIdx = 12;
    		}
    		else if (dateType.toUpperCase().equals("HH"))
    		{
    			valueLen = 2;
    			startIdx = 0;
    			endIdx = 23;
    		}
    		else if (dateType.toUpperCase().equals("MI"))
    		{
    			valueLen = 2;
    			startIdx = 0;
    			endIdx = 59;
    		}    		
    		
    		
	         for(int i=startIdx; i <= endIdx; i+= termValue)
	         {  
	        	 
				if (typeChk)
				{
					sb.append("<option value='"+StringUtil.padLeft(String.valueOf(i), '0', valueLen)+"' ");
					if ((selectedValue.length() > 0 && !selectedValue.equals("")) && (i == Integer.parseInt(selectedValue)))
						sb.append("selected ");
					else if (selectedValue.equals("") && ((defaultValue.length() > 0 && !defaultValue.equals("")) && i == Integer.parseInt(defaultValue)))
						sb.append("selected ");
					sb.append(">");
					
					sb.append(StringUtil.padLeft(String.valueOf(i), '0', valueLen) + "</option>");
				}
				else
				{
					sb.append("<input type='"+type+"' id='"+name+"' name='"+name+"' value='"+StringUtil.padLeft(String.valueOf(i), '0', valueLen)+"' "+styleType);
					if ((selectedValue.length() > 0 && !selectedValue.equals("")) && (i == Integer.parseInt(selectedValue)))
						sb.append("checked ");
					else if (selectedValue.equals("") && ((defaultValue.length() > 0 && !defaultValue.equals("")) && i == Integer.parseInt(defaultValue)))
						sb.append("checked ");
					sb.append(">");	
					
					sb.append(StringUtil.padLeft(String.valueOf(i), '0', valueLen) + " ");
				}
	         }    	    	 

    		
    		if (typeChk)
    			sb.append("</select>");
    		//-----------------------
    		
    		JspWriter out = pageContext.getOut();

    		if (type.equals("value"))
    			out.print(rtnValue);
    		else
    			out.print(sb.toString());	
    	}
    	catch(Exception e)
    	{
    		throw new JspException("DateComboTag", e);    		
    	}
    	
    	return EVAL_PAGE;    	
    }
    
    public void release()
    {
    	super.release();
    	type = null;
    	name = null;
    	dateType = null;
        term = null;
    	orderBy = null;
    	dateCnt = null;
    	selectedValue = null;
    	defaultValue  = null;
    	notnull	 = null;
    	disabled = null;
    	colname    = null;
    	selectType = null;
    	style	  = null;
    	className = null;
    	onChange  = null;
    	onClick	  = null;
    	nextFocus = null;
    	except	  = null;
    }


}
