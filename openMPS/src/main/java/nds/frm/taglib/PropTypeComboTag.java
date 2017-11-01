package nds.frm.taglib;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.DataSource;

import nds.frm.startup.VocDataSource;
import nds.frm.util.StringUtil;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;



/**
 * <p>Title: 제안업무유형을 자동으로 콤보박스를 구성하는 taglib</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: NDS</p>
 * @author 김태호
 * @version 1.0
 */
public class PropTypeComboTag  extends BodyTagSupport {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8340222086768835012L;
    private JdbcTemplate jt;
    private DataSource ds;

    private String shape;
    private String type;
    private String name;
    private String lvl;
    private String selectedValue;
    private String defaultValue;
    private String notnull;
    private String disabled;
    private String colname;
    private String selectType;
    private String style;
    private String className;
    private String onChange;
    private String onKeyUp;
    private String onKeyDown;
    private String onKeyPress;
    private String onClick;
    private String nextFocus;
    private String except;
    private String useYn;
    private String arrYn;
    private String srtBetween;
    private String endBetween; 

    public void setExcept(String except) {
        this.except = except;
    }
    
    public void setSrtBetween(String srtBetween) {
        this.srtBetween = srtBetween;
    }
    
    public void setEndBetween(String endBetween) {
        this.endBetween = endBetween;
    }
        
    public void setShape(String shape) {
        this.shape = shape;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLvl(String lvl) {
        this.lvl = lvl;
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
    public void setOnKeyUp(String onKeyUp) {
        this.onKeyUp = onKeyUp;
    } 
    public void setOnKeyDown(String onKeyDown) {
        this.onKeyDown = onKeyDown;
    } 
    public void setOnKeyPress(String onKeyPress) {
        this.onKeyPress = onKeyPress;
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
    public String getUseYn() {
        return useYn;
    }
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
    public void setArrYn(String arrYn) {
        this.arrYn = arrYn;
    }
    
    public int doStartTag() {
        try {
            shape = StringUtil.null2void(shape);
            if("".equals(shape)) {shape = "none";}
            type = StringUtil.null2void(type);
            name = StringUtil.null2void(name);
            lvl = StringUtil.null2void(lvl);
            selectedValue = StringUtil.null2void(selectedValue).replace("<c:out value=\"","").replace("\"/>","");
            defaultValue = StringUtil.null2void(defaultValue);
            notnull = StringUtil.null2void(notnull);
            disabled = StringUtil.null2void(disabled);
            colname = StringUtil.null2void(colname);
            selectType = StringUtil.null2void(selectType);
            style = StringUtil.null2void(style);
            className = StringUtil.null2void(className);
            onChange = StringUtil.null2void(onChange);
            onKeyUp = StringUtil.null2void(onKeyUp);
            onKeyDown = StringUtil.null2void(onKeyDown);
            onKeyPress = StringUtil.null2void(onKeyPress);
            onClick = StringUtil.null2void(onClick);
            nextFocus = StringUtil.null2void(nextFocus);
            except = StringUtil.null2void(except);
            useYn = StringUtil.null2void(useYn);
            arrYn = StringUtil.null2void(arrYn);
            srtBetween = StringUtil.null2void(srtBetween);
            endBetween = StringUtil.null2void(endBetween);
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
            if (!onKeyUp.equals(""))
                styleType += "onKeyUp='"+onKeyUp+"' ";
            if (!onKeyDown.equals(""))
                styleType += "onKeyDown='"+onKeyDown+"' ";
            if (!onKeyPress.equals(""))
                styleType += "onKeyPress='"+onKeyPress+"' ";
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
                    if(selectType.equals("E")) {
                        sb.append("<option value=''></option>");
                        sb.append("<option value='T'>직접입력</option>");
                    }
                    else {
                        sb.append("<option value='' ");
                        
                        if (selectedValue.equals("") && !defaultValue.equals(""))
                            sb.append("selected ");
                        
                        sb.append(">");
                        
                        if (selectType.equals("S"))
                            sb.append("-선택-");
                        else if (selectType.equals("A"))
                            sb.append("-전체-");
                        else if (selectType.equals("T"))
                            sb.append("직접입력");
                        
                        sb.append("</option>");
                    }
                }
            }
            else
            {
            
                if (!selectType.equals(""))
                {
                    sb.append("<input type='"+type+"' id='"+name+"' name='"+name+"' value='' "+ styleType);
                    
                    if (selectedValue.equals("") && !defaultValue.equals(""))
                        sb.append("checked ");
                    
                    sb.append(">");
                    
                    if (selectType.equals("S"))
                        sb.append("선택");
                    else if (selectType.equals("A"))
                        sb.append("전체");
                }               
            }
            
            ds = VocDataSource.getDataSource();
            
            Query = " select LCLS as CD_TP, TYPE_CAT_NM as CD_KNM from CLT_PROP_TYPE where USE_YN='Y' AND LVL = '"+ lvl +"'";
            Query += " order by LCLS, MCLS asc ";
            
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
                    
                    //number 타입은 오류가 나므로 제외
                    if (key.equals("CD_TP"))
                    {
                        if (typeChk)
                        {
                            sb.append("<option value='"+value+"' ");
                            if (selectedValue.equals(value)) {
                                sb.append("selected ");
                                
//                                MapIterator m = map.mapIterator();
//                                while(m.hasNext()) if("CD_KNM".equals((String)m.next())) rtnValue = (String)m.getValue();
                            }
                            else if (selectedValue.equals("") && defaultValue.equals(value)) {
                                sb.append("selected ");

//                                MapIterator m = map.mapIterator();
//                                while(m.hasNext()) if("CD_KNM".equals((String)m.next())) rtnValue = (String)m.getValue();
                            }
                            sb.append(">");
                        }
                        else
                        {
                            sb.append("<label><input type='"+type+"' id='"+name+"' name='"+name+"' value='"+value+"' "+styleType);
                            
                            if(arrYn.equals("")) {
                                if (selectedValue.equals(value))
                                    sb.append("checked ");
                                else if (selectedValue.equals("") && defaultValue.equals(value))
                                    sb.append("checked ");
                                sb.append(">");
                            }
                            // 2008-02-11 수정
                            else {
                                StringTokenizer st = new StringTokenizer(selectedValue, ",");
                                String[] names = new String[st.countTokens()];
                                for(int j = 0; j < names.length; j++) {
                                    names[j] = st.nextToken().trim();

                                    if (value.equals(names[j]))
                                        sb.append("checked ");
                                    else if (selectedValue.equals("") && defaultValue.equals(value))
                                        sb.append("checked ");
                                }
                                sb.append(">");
                            }
                        }
                    }
                    else if (key.equals("CD_KNM"))
                    {
                        if (typeChk)
                        {
                            sb.append(value + "</option>");
                        }
                        else
                        {
                            sb.append(value + " </label>");
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

            if (type.equals("value")) {
                out.print(rtnValue);
            }
            else
                out.print(sb.toString());   
        }
        catch(Exception e)
        {
            throw new JspException("PropTypeComboTag", e);          
        }
        
        return EVAL_PAGE;       
    }
    
    public void release()
    {
        super.release();
        shape = null;        
        type = null;        
        defaultValue = null;        
        name = null;
        lvl =null;
        selectedValue = null;
        notnull = null;
        disabled = null;
        selectType = null;
        style = null;
        className = null;
        onChange = null;
        onKeyUp = null;
        onKeyDown = null;
        onKeyPress = null;
        onClick = null;
        nextFocus = null;
        except = null;
        useYn = null;
        arrYn = null;
        srtBetween = null;
        endBetween = null;
    }

}
