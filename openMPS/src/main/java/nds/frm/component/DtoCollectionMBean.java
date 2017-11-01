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

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DtoCollectionMBean extends Serializable,Cloneable {
    abstract Iterator iterator();

    abstract void removeObject(String key);

    abstract int countObject();

    abstract int count();

    abstract void removeAll();

    abstract DtoMBean getObject(String key);

    abstract DtoCollectionMBean addObject(DtoMBean dto);

    abstract DtoCollectionMBean add(DtoMBean dto);

    /**
     @return java.util.Enumeration (Key)
     */
    abstract Set getKeys();

    /**
     @return java.util.Enumeration (Value)
     */
    abstract Collection getValues();

    abstract LinkedList sort(Comparator comparator);

    abstract void add(int index, DtoMBean element);

    abstract LinkedList sort();

    abstract Object[] toArray();

    abstract DtoMBean getObject(int index);

    //    static DtoCollectionMBean searchObject(
    abstract Object[] getObjects();

    abstract Object getKey();

    /**
     * @return Returns the content.
     */
    abstract Map getContent();

    /**
     * @param content The content to set.
     */
    abstract void setContent(Map content);

    //    static DtoCollection filter(DtoCollectionMBean bc, String filter) {
    abstract Object clone();

    abstract int getIndex(String key);

    abstract String toString();

    abstract DtoMBean next();

    abstract DtoMBean previous();

    abstract DtoMBean last();

    abstract DtoMBean first();

    abstract DtoMBean current();

    abstract int getCurrentIndex();

    /**
     * add all BusinessCollectionMBean objects in the list to this collection
     * @param list
     * @return
     */
    abstract DtoCollection add(List list);

    abstract DtoCollectionMBean add(DtoCollectionMBean bc);

    /* (non-Javadoc)
     * @see com.mot.motos.common.BusinessCollectionMBean#removeObject(com.mot.motos.common.IBusinessObject)
     */
    abstract void removeObject(DtoMBean object);

    /* (non-Javadoc)
     * @see com.tsb.tsf.object.DtoCollectionMBean#isExist(com.tsb.tsf.object.DtoMBean)
     */
    abstract boolean isExist(DtoMBean bean);

    abstract boolean isExist(String key);

    abstract DtoCollection createClone();

    //    static boolean isRegExpMatch(String value, String pattern) {

}