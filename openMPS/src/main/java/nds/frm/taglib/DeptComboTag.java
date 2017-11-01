package nds.frm.taglib;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.DataSource;

import nds.frm.config.StaticConfig;
import nds.frm.startup.VocDataSource;
import nds.frm.util.StringUtil;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;



/**
 * <p>Title: 부서(조직)정보를 자동으로 콤보박스를 구성하는 taglib</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: NDS</p>
 * @author 임진식
 * @version 1.0
 */
public class DeptComboTag
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
    private String orgCd;    
    private String upOrgCd;
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
    private String salYn;

    public void setShape(String shape) {
        this.shape = shape;
    }
	public void setType(String type) {
		this.type = type;
	}
    public void setName(String name) {
        this.name = name;
    }
    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }
    public void setUpOrgCd(String upOrgCd) {
        this.upOrgCd = upOrgCd;
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
    public void setSalYn(String salYn) {
        this.salYn = salYn;
    }
    
    public int doStartTag() {
        try {
            shape = StringUtil.null2void(shape);
            if("".equals(shape)) {shape = "none";}
        	type = StringUtil.null2void(type);
        	name = StringUtil.null2void(name);
        	orgCd = StringUtil.null2void(orgCd);
        	upOrgCd = StringUtil.null2void(upOrgCd);  	
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
            salYn = StringUtil.null2void(salYn);
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
    	boolean typeChk = true;
    	
    	try
    	{    		
    		if (!className.equals(""))
    			styleType += "class='"+className+"' ";
    		else{
    			styleType += "class='table_button' ";
    		}
    		if (!style.equals(""))
    			styleType += "style='"+style+"' ";
    		else{
    			styleType += "style='width:120px;' ";
    		}
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
    		
    		Query += " SELECT DEPT_CODE as DEP_CD, DEPT_NAME_ABBR as DEP_NM       ";
    		if("debug".equals(StaticConfig.PRODUCT_MODE)) {
                Query += " FROM NCOMT001                                ";
    		}
    		else {
                Query += " FROM NCOMT001                                ";
    		}
    		Query += " WHERE 1 = 1                                       ";
    		Query += "   AND APPL_END_DATE = '99991231'             ";
    		
    		
    		if("Y".equals(salYn)){

        		Query += "   AND PART_CODE = '000111'                        ";
        		Query += "   AND ORGT_GRAD = '3'                            ";
        		Query += " and uporg_dept_code not in ('3350','7200','7202','3303')                  ";
        		
    		}else{
                if (!upOrgCd.equals("")) Query += " AND PART_CODE = '" + upOrgCd + "'  ";
    		}

    		Query += " ORDER BY SORT_KEY   asc                     ";
    		
    		
    		jt = new JdbcTemplate(ds);
    		List rows = jt.queryForList(Query);
    		
    		for(int i=0; i<rows.size(); i++)
    		{    			
            	LinkedCaseInsensitiveMap map = (LinkedCaseInsensitiveMap)rows.get(i);
            	Iterator mi = map.entrySet().iterator();
    			    			    			
    			while(mi.hasNext())
    			{
                	Entry entry = (Entry) mi.next();
                    String key = (String) entry.getKey();
                    String value = (String) entry.getValue();
                    
    				if (key.equals("DEP_CD"))
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
    				else if (key.equals("DEP_NM"))
    				{
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
    		out.print(sb.toString());
    	}
    	catch(Exception e)
    	{
    		throw new JspException("DeptComboTag", e);    		
    	}
    	
    	return EVAL_PAGE;    	
    }
    
    public void release()
    {
    	super.release();
        shape = null;        
    	type = null;
    	name = null;
    	orgCd =null;
    	upOrgCd = null;
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
