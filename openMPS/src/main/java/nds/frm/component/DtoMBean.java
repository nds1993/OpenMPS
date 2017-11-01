/**
*
* Copyright (C) 2009 NDS. All Rights
*
* Created on   : 2009-6-11 10:23:05
* Target OS    : Java VM 1.7.0
*
* ------------------------------
* CHANGE REVISION
* ------------------------------
* AUTHOR   : NDS
* DATE     : 2009-6-11 10:23:05
* REVISION : 1.0   First release.
* -------------------------------
* CLASS DESCRIPTION
* -------------------------------
*
*/
package nds.frm.component;


public interface DtoMBean extends java.io.Serializable, Cloneable,
		Comparable {

	String getKey();
	void setUID(String sKey);
	Dto createClone();
//	Map validate();
//	void initialize();
}
