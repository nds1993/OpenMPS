package nds.tmm.common.TMCOUR30.vo;

import java.util.List;

/**
 * @Class Name : TmMenuxmVO.java
 * @Description : TmMenuxm VO class
 * @Modification Information
 *
 * @author MPM TEAM
 * @since 2017.07.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class MenuTreeVO {
    private static final long serialVersionUID = 1L;
    
    private String[] navi;
    /** menu_code */
    private java.lang.String menuCode;
    
    /** menu_name */
    private java.lang.String menuName;
    
    /** up_code */
    private java.lang.String upCode;
    private java.lang.String upName;
    private int level;
    
    /** sort_order */
    private int sortOrder;


	public String[] getNavi() {
		return navi;
	}

	public void setNavi(String[] navi) {
		this.navi = navi;
	}

	public java.lang.String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(java.lang.String menuCode) {
		this.menuCode = menuCode;
	}

	public java.lang.String getMenuName() {
		return menuName;
	}

	public void setMenuName(java.lang.String menuName) {
		this.menuName = menuName;
	}

	public java.lang.String getUpCode() {
		return upCode;
	}

	public void setUpCode(java.lang.String upCode) {
		this.upCode = upCode;
	}

	public java.lang.String getUpName() {
		return upName;
	}

	public void setUpName(java.lang.String upName) {
		this.upName = upName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

    
    
}
