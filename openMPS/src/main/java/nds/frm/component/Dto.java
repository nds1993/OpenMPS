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

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Dto implements DtoMBean{//, DynamicMBean {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Log log = LogFactory.getLog(Dto.class);

    ObjectName objectName;
    
    public Dto() {
        setUID(getUID());
    }

    public Dto(String key) {
        try {
            objectName = new ObjectName("kocean.com.component:type=Dto,id="+key);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setUID(uid);
    }

    public String uid = "";
    public int index;
    abstract public String getUID();

    public void setUID(String uid) {
        this.uid = uid;
    }

    public void makePersistent() {
        //listeners.firePropertyChange("makePersistent()",null,this);
    }

    public int compareTo(Object obj) {
        return new DefaultComparator().compare(this, obj);
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();

        Class c = this.getClass();
        String fullname = c.getName();
        String name = null;
        int index = fullname.lastIndexOf('.');
        if (index == -1)
            name = fullname;
        else
            name = fullname.substring(index + 1);
        buf.append(name + ":{");

        Field[] fields = c.getFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                if (i != 0)
                    buf.append(',');
                buf.append(fields[i].getName() + '=');
                Object f = fields[i].get(this);
                Class fc = fields[i].getType();
                if (fc.isArray()) {
                    buf.append('[');
                    int length = Array.getLength(f);
                    for (int j = 0; j < length; j++) {
                        if (j != 0)
                            buf.append(',');
                        Object element = Array.get(f, j);
                        buf.append(element.toString());
                    }
                    buf.append(']');
                } else
                    buf.append(f.toString());
            } catch (Exception e) {
            }
        }
        buf.append('}');
        return buf.toString();
    }

//    public void initialize() {
//        try {
//            PropertyDescriptor[] descriptors = PropertyUtils
//                    .getPropertyDescriptors(this);
//            for (int i = 1; i < descriptors.length; i++) {
//                String name = descriptors[i].getName();
//                PropertyDescriptor descriptor = PropertyUtils
//                        .getPropertyDescriptor(this, name);
//                if (PropertyUtils.isReadable(this, name)) {
//
//                    Object beanValue = PropertyUtils.getSimpleProperty(this,
//                            name);
//
//                    //if (beanValue!=null && beanValue.equals(this.getKey())) continue;
//
//                    if (beanValue == null && beanValue instanceof String) {
//                        PropertyUtils.setProperty(this, name, new String(""));
//                    } else if (beanValue instanceof String) {
//                        PropertyUtils.setProperty(this, name, new String(""));
//                    } else if (beanValue instanceof Integer) {
//                        PropertyUtils.setSimpleProperty(this, name,
//                                new Integer(0));
//                    } else if (beanValue instanceof Date) {
//
//                    } else if (beanValue instanceof Float) {
//                        PropertyUtils.setSimpleProperty(this, name,
//                                new Float(0));
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            if (log.isErrorEnabled())
//                log.debug("exception when evaluating bean property");
//        }
//    }

//    public String getFilterKey() {
//        return getKey();
//    };
    
    public Dto createClone(){
        Dto dto = null;
        try {
            dto = (Dto)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return dto;
    }

}


