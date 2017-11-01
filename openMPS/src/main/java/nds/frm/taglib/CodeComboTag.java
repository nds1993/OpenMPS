package nds.frm.taglib;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.sql.DataSource;

import nds.frm.startup.VocDataSource;
import nds.frm.util.StringUtil;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.LinkedCaseInsensitiveMap;



/**
 * <p>Title: 공통코드로 자동으로 콤보박스를 구성하는 taglib</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: NDS</p>
 * @author 임진식
 * @version 1.0
 */
public class CodeComboTag  extends BodyTagSupport {
    
    /**
     * 
     */
    private static final long serialVersionUID = 8340222086768835012L;
    private JdbcTemplate jt;
    private DataSource ds;

    private String shape;
    private String type;
    private String name;
    private String cdId;
    private String upCdId;
    private String upCdTp;
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
    private String tabId;  //멀티일 경우 탭ID필요
    private String mgDvn;
    private String placeholder;
    private String mgItem;

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
    public void setCdId(String cdId) {
        this.cdId = cdId;
    }
    public void setUpCdId(String upCdId) {
        this.upCdId = upCdId;
    }
    public void setUpCdTp(String upCdTp) {
        this.upCdTp = upCdTp;
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
    public void setTabId(String tabId) {
        this.tabId = tabId;
    }
    public void setMgDvn(String mgDvn) {
        this.mgDvn = mgDvn;
    }
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
    public void setMgItem(String mgItem) {
    	this.mgItem = mgItem;
    }
    
    
    public int doStartTag() {
        try {
            shape = StringUtil.null2void(shape);
            if("".equals(shape)) {shape = "none";}
            type = StringUtil.null2void(type);
            name = StringUtil.null2void(name);
            cdId = StringUtil.null2void(cdId);
            upCdId = StringUtil.null2void(upCdId);
            upCdTp = StringUtil.null2void(upCdTp);          
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
            tabId = StringUtil.null2void(tabId).replace("<c:out value=\"","").replace("\"/>","");
            mgDvn = StringUtil.null2void(mgDvn);
            placeholder = StringUtil.null2void(placeholder);
            mgItem = StringUtil.null2void(mgItem);
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
    		else{
    			if(type.toUpperCase().equals("RADIO") || type.toUpperCase().equals("CHECKBOX")){
    				styleType += "class='"+className+"' ";
    			}else{
        			styleType += "class='table_button' ";
    			}
    		}
    		if (!style.equals(""))
    			styleType += "style='"+style+"' ";
    		else{
    			styleType += "style='width:120px;' ";
    		}
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
                else if(shape.toUpperCase().equals("MUTI")) {

                	String mutiText = "-전체-";
                    if (selectType.equals("S"))
                    	mutiText = "-선택-";
                    else if (selectType.equals("U"))
                    	mutiText = placeholder;
                    
                    sb.append("<script type='text/javascript' language='javascript'>"
                    		+ "$('#"+tabId+" #" + name + "').SumoSelect({placeholder:'" + mutiText +"'});"
                    		+ "</script>"
                    		+ " <select id='"+name+"' name='"+name+"[]' multiple='multiple' "+ styleType +" >");
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
                        if(!shape.toUpperCase().equals("MUTI")){
                        	
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
	                        else if (selectType.equals("U"))
	                            sb.append(placeholder);
	                        
	                        sb.append("</option>");

                        }
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
            
            
            
            String tpText = "CD_TP";
            
            if(!mgDvn.equals("")){
            	tpText = "MG_ITEM_" + mgDvn; 
            }
            
    
            ds = VocDataSource.getDataSource();
            
            Query = " select CD_TP, MG_ITEM_1, MG_ITEM_2, MG_ITEM_3, MG_ITEM_4, MG_ITEM_5, MG_ITEM_6, CD_KNM "
            		+ " from CLT_COMM_CD where USE_YN='Y' AND CD_ID='"+cdId+"' ";
            
            if (!upCdId.equals(""))
                Query += " and UP_CD_ID = '"+upCdId+"' ";
            if (!upCdTp.equals(""))
                Query += " and UP_CD_TP = '"+upCdTp+"' ";   
            if (!useYn.equals(""))
                Query += " and USE_YN = '"+useYn+"' ";   
            if (!except.equals(""))
            {
                Query += " and (CD_TP not in (";
                    String[] temp = except.split(";");
                    
                    for (int i=0; i<temp.length; i++ )
                    {
                        Query += "'"+temp[i]+"'";
                        if (i<temp.length-1)
                            Query += ", ";
                    }
                Query += ") )";
            }
            
            // 2011. 3. 9 시작값과 끝 값사이의 코드들만 가져오기 위해 between 조건 추가 (최성기)
            // 체크는 srtBetween만 하지만 실제 코드에서는 srtBetween과 endBetween을 함께 조건에 넣어 사용
            if (!srtBetween.equals(""))
            {
                Query += "and CD_TP between '" + srtBetween + "' and '" + endBetween + "' ";
            }
            
            Query += " order by cd_ord asc ";
            
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

                    if("1".equals(mgItem)){
                        Iterator<?> m1 = map.entrySet().iterator();
                        while(m1.hasNext()){
                        	Entry<?, ?> en = (Entry<?, ?>) m1.next();
                        	if("MG_ITEM_1".equals((String)en.getKey())) value = (String)en.getValue();
                        }                    	
                    }
                    //number 타입은 오류가 나므로 제외
                    if (key.equals(tpText))
                    {
                        if (typeChk)
                        {
                        	

                            sb.append("<option value=\""+value+"\" ");
                            if(!shape.toUpperCase().equals("MUTI")) {
                            	
                                if (selectedValue.equals(value)) {
                                    sb.append("selected ");
                                    
//                                    MapIterator m = map.mapIterator();
//                                    while(m.hasNext()) if("CD_KNM".equals((String)m.next())) rtnValue = (String)m.getValue();
                                    Iterator<?> m = map.entrySet().iterator();
                                    while(m.hasNext()){
                                    	Entry<?, ?> en = (Entry<?, ?>) m.next();
                                    	if("CD_KNM".equals((String)en.getKey())) rtnValue = (String)en.getValue();
                                    }                                
                                }
                                else if (selectedValue.equals("") && defaultValue.equals(value)) {
                                    sb.append("selected ");

//                                    MapIterator m = map.mapIterator();
//                                    while(m.hasNext()) if("CD_KNM".equals((String)m.next())) rtnValue = (String)m.getValue();
                                    Iterator<?> m = map.entrySet().iterator();
                                    while(m.hasNext()){
                                    	Entry<?, ?> en = (Entry<?, ?>) m.next();
                                    	if("CD_KNM".equals((String)en.getKey())) rtnValue = (String)en.getValue();
                                    }                                
                                }
                                sb.append(">");
                            }
                            else {
                                StringTokenizer st = new StringTokenizer(selectedValue, ",");
                                String[] names = new String[st.countTokens()];
                                for(int j = 0; j < names.length; j++) {
                                    names[j] = st.nextToken().trim();

                                    if (value.equals(names[j]))
                                        sb.append("selected ");
                                    else if (selectedValue.equals("") && defaultValue.equals(value))
                                        sb.append("selected ");
                                }
                                sb.append(">");
                            }
                        	
                        	
                        	
                        	
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
                            //2012-07-11 추가
                            if("정보보호".equals(value)){
                                sb.append("<br>");
                            }
                        }
                    }
                }
                
            }
            
            if (typeChk) {
                if(shape.toUpperCase().equals("EDIT")) { 
                    sb.append("</select>\", \"\", \"#ffffff\", \"#D5F7FF\", \"1px solid #CCCCCC\", \"\");</script>");
                }
                else if(shape.toUpperCase().equals("MUTI")) {
                    sb.append("</select>");
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
            throw new JspException("CodeComboTag", e);          
        }
        
        return EVAL_PAGE;       
    }
    
    public void release()
    {
        super.release();
        shape = null;        
        type = null;        
        upCdId = null;
        upCdTp = null;      
        defaultValue = null;        
        name = null;
        cdId =null;
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
        tabId = null;
        mgDvn = null;
        placeholder = null;
        		
    }

}
