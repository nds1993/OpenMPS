package nds.frm.taglib;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.DataSource;

import nds.frm.startup.VocDataSource;
import nds.frm.util.StringUtil;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.jdbc.core.JdbcTemplate;



/**
 * <p>Title: 그룹정보를 자동으로 콤보박스를 구성하는 taglib</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007. 10. 30</p>
 * <p>Company: NDS</p>
 * @author 임진식
 * @version 1.0
 */
public class GroupComboTag
    extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -882631244802021429L;
	private JdbcTemplate jt;
	private DataSource ds;

    private String shape;
	private String type;
    private String name;
    private String userNo;
    private String orgCd;
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

    public void setShape(String shape) {
        this.shape = shape;
    }
	public void setType(String type) {
		this.type = type;
	}
    public void setName(String name) {
        this.name = name;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    } 
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
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
            shape = StringUtil.null2void(shape);
            if("".equals(shape)) {shape = "none";}
        	type = StringUtil.null2void(type);
        	name = StringUtil.null2void(name);
        	userNo = StringUtil.null2void(userNo);  
        	orgCd = StringUtil.null2void(orgCd);
        	selectedValue = StringUtil.null2void(selectedValue);
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
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    public int doEndTag() throws JspException {
    	
    	String Query = "";
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
                if (!nextFocus.equals(""))
                    styleType += "nextFocus='"+nextFocus+"' ";

                //-------------
                if(shape.toUpperCase().equals("EDIT")) { 
                    sb.append("<script type='text/javascript' language='javascript'>NSelectEdit(\"" + name + "\", \"<select id='"+name+"' name='"+name+"' "+ styleType +">");
                }
                else if(shape.toUpperCase().equals("NONE")) {
                    sb.append("<select id='"+name+"' name='"+name+"' "+ styleType +">");
                }
                else {
                    sb.append("<script type='text/javascript' language='javascript'>NSelect(\"" + name + "\", \"<select id='"+name+"' name='"+name+"' "+ styleType +">");
                }
	    		
	    		if (!selectType.equals(""))
	    		{
	    			sb.append("<option value='' ");
	    			
	    			if (selectedValue.equals(""))
	    				sb.append("selected ");
	    			
	    			sb.append(">");
	    			
	    			if (selectType.equals("S"))
	    				sb.append("-선택-");
	    			else if (selectType.equals("A"))
	    				sb.append("-전체-");
	    			
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
    		
    		ds = VocDataSource.getDataSource();
    		
    		Query = " select GRP_NO, GRP_NM from TBCR_5400 A ";
    		Query += "where ( ";
    		Query += "  (A.OPEN_RANG='U' and A.USER_NO='"+userNo+"') ";
    		Query += "  or ";
    		Query += "  (A.OPEN_RANG='A') ";
    		Query += "  or ";
    		Query += "  (A.OPEN_RANG='D' and '"+orgCd+"' = (select B.DEP_CD from HRMAM.TBHR_0010 B where B.EMP_NO=A.USER_NO) ) ";
    		Query += ")";	
    		Query += " and A.USE_YN ='Y' ";
    		
    		Query += " order by A.GRP_NM, A.SORT_ORD ";
            
    		jt = new JdbcTemplate(ds);
    		List rows = jt.queryForList(Query);
    		
    		for(int i=0; i<rows.size(); i++)
    		{    			
    			ListOrderedMap map = (ListOrderedMap)rows.get(i);
    			MapIterator mi = map.mapIterator();
    			    			    			
    			while(mi.hasNext())
    			{
    				String key = (String)mi.next();
    				String value = (String)mi.getValue();
    				
    				if (key.equals("GRP_NO"))
    				{
    					if (typeChk)
    					{
	    					sb.append("<option value='"+value+"' ");
	    					if (selectedValue.equals(value))
	    						sb.append("selected ");
	    					else if (selectedValue.equals("") && defaultValue.equals(value))
	    						sb.append("selected ");
	    					sb.append(">");
    					}
    					else
    					{
	    					sb.append("<input type='"+type+"' id='"+name+"' name='"+name+"' value='"+value+"' "+styleType);
	    					if (selectedValue.equals(value))
	    						sb.append("checked ");
	    					else if (selectedValue.equals("") && defaultValue.equals(value))
	    						sb.append("checked ");
	    					sb.append(">");	    		    						
    					}
    				}
    				else if (key.equals("GRP_NM"))
    				{
    					rtnValue = value;
    					if (typeChk)
    					{
    						sb.append(value + "</option>");
    					}
    					else
    					{
    						sb.append(value + " ");
    					}
    				}
    			}	    			
    		}
    		
            if (typeChk) {
                if(shape.toUpperCase().equals("EDIT")) { 
                    sb.append("</select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>");
                }
                else if(shape.toUpperCase().equals("NONE")) {
                    sb.append("</select>");
                }
                else {
                    sb.append("</select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>");
                }
            }
    		//-----------------------
                
    		JspWriter out = pageContext.getOut();
    		
    		if (type.equals("value"))
    			out.print(rtnValue);
    		else
    			out.print(sb.toString());	 	    		
    	}
    	catch(Exception e)
    	{
    		throw new JspException("GroupComboTag", e);    		
    	}
    	
    	return EVAL_PAGE;    	
    }
    
    public void release()
    {
    	super.release();
        shape = null;        
    	type = null;
    	name = null;
    	userNo = null;
    	orgCd = null;
    	selectedValue = null;
    	defaultValue = null;
    	notnull = null;
    	disabled = null;
    	selectType = null;
    	style = null;
    	className = null;
    	onChange = null;
    	onClick = null;        
        nextFocus = null;
    }


}
