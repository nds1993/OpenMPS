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
import java.util.Comparator;

public class DefaultComparator implements Serializable, Comparator {
    private static final long serialVersionUID = 1L;

    public int compare(Object o1, Object o2) {
        String s1 = ((Dto) o1).getKey();
        String s2 = ((Dto) o2).getKey();
        return s1.compareTo(s2);
    }

    public boolean equals(Object o) {
        return (o != null) && (o instanceof Dto);
    }
}
