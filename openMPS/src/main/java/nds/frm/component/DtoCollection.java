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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DtoCollection implements DtoCollectionMBean {
    private static final long serialVersionUID = 1L;

    public Comparator comparator = new DefaultComparator();

    public Object key;
    public Map<String, DtoMBean> content = new Hashtable<String, DtoMBean>();
    List<String> keyList = new ArrayList<String>();

    public String id;
    public int currentIndex = 1;

    public DtoCollection() {
    }

    public DtoCollection(String sKey) {
        this.key = sKey;
    }

    public Iterator iterator() {
        return content.values().iterator();
    }

    public void removeObject(String key) {
        for (int i = 0; i < keyList.size(); i++) {
            keyList.remove(key);
            content.remove(key);
            break;
        }
    }

    public int countObject() {
        return content.size();
    }

    public int count() {
        return countObject();
    }

    public void removeAll() {
        content.clear();
        keyList.clear();
    }

    public synchronized DtoMBean getObject(String key) {
        if (key == null)
            return null;

        return (DtoMBean) content.get(key);
    }

    public DtoCollectionMBean addObject(DtoMBean dto) {
        if (keyList.contains(dto.getKey()))
            return this;
        keyList.add(dto.getKey());
        content.put(dto.getKey(), dto);

        return this;
    }

    public DtoCollectionMBean add(DtoMBean dto) {
        addObject(dto);
        return this;
    }

    /**
     @return java.util.Enumeration (Key)
     */
    public Set getKeys() {
        return content.keySet();
    }


    public Collection getValues() {
        return content.values();
    }

    @SuppressWarnings("unchecked")
    public LinkedList sort(Comparator comparator) {
        Collection collection = content.values();
        LinkedList list = new LinkedList(collection);
        Collections.sort(list, comparator);
        return list;
    }

    public void add(int index, DtoMBean element) {
        if ((index < 0 || index > count())) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        if (!keyList.contains(element.getKey())) {
            keyList.add(index, element.getKey());
            content.put(element.getKey(), element);
        }
    }

    public LinkedList sort() {
        return sort(comparator);
    }

    public Object[] toArray() {
        Object[] result = new Object[count()];
        for (int i = 0; i < count(); i++) {
            result[i] = getObject(i);
        }
        return result;
    }

    public DtoMBean getObject(int index) {
        Object obj = keyList.get(index);
        return (DtoMBean) content.get(obj);
    }

//    public static DtoCollectionMBean searchObject(
//            DtoCollectionMBean collection, Map map) {
//        DtoCollection bc = new DtoCollection();
//
//        Iterator iter1 = collection.iterator();
//        while (iter1.hasNext()) {
//            Dto bo = (Dto) iter1.next();
//            Map objectMap = DtoCollection.getBeanDetailsMap(bo);
//
//            Set keySet = map.keySet();
//            Iterator iter2 = keySet.iterator();
//
//            boolean isMatched = true;
//            while (iter2.hasNext()) {
//                String key = (String) iter2.next();
//                if (objectMap.containsKey(key)
//                        && map.get(key).equals(objectMap.get(key))) {
//                    continue;
//                } else {
//                    isMatched = false;
//                    break;
//                }
//            }
//
//            if (isMatched)
//                bc.add(bo);
//        }
//        return bc;
//    }

    public Object[] getObjects() {
        Collection col = content.values();
        return col.toArray();
    }

    public Object getKey() {
        return this.key;
    }

    /**
     * @return Returns the content.
     */
    public Map getContent() {
        return content;
    }

    /**
     * @param content The content to set.
     */
    @SuppressWarnings("unchecked")
    public void setContent(Map content) {
        this.content = content;
    }

//    static public DtoCollection filter(DtoCollectionMBean bc, String filter) {
//        DtoCollection collection = new DtoCollection();
//        for (int i = 0; i < bc.countObject(); i++) {
//            Dto bo = (Dto) bc.getObject(i);
//            String s = bo.getFilterKey();
//
//            if (filter.equals("*") || filter.trim().equals("")) {
//                collection.addObject(bo);
//            } else if (filter.startsWith("*") && filter.endsWith("*")) {
//                for (int j = 0; j < filter.length(); j++) {
//                    if (s.regionMatches(j, filter.substring(1,
//                            filter.length() - 1), 0, filter.length() - 2)) {
//                        collection.addObject(bo);
//                        break;
//                    }
//                }
//            } else if (filter.startsWith("*")) {
//                if (s.endsWith(filter.substring(1, filter.length()))) {
//                    collection.addObject(bo);
//                }
//            } else if (filter.endsWith("*")) {
//                if (s.startsWith(filter.substring(0, filter.length() - 1))) {
//                    collection.addObject(bo);
//                }
//            } else {
//                if (isRegExpMatch(bo.getFilterKey(), filter)) {
//                    collection.addObject(bo);
//                }
//            }
//        }
//        return collection;
//    }

//    public Map getBeanDetailsMap(Dto bean) {
//        // result map to be returned
//        Map detailsMap = new HashMap();
//
//        try {
//            PropertyDescriptor[] descriptors = PropertyUtils
//                    .getPropertyDescriptors(bean);
//            for (int i = 1; i < descriptors.length; i++) {
//                String name = descriptors[i].getName();
//                PropertyDescriptor descriptor = PropertyUtils
//                        .getPropertyDescriptor(bean, name);
//                if (PropertyUtils.isReadable(bean, name)) {
//                    Object beanValue = PropertyUtils.getSimpleProperty(bean,
//                            name);
//                    detailsMap.put(name, beanValue);
//                }
//            }
//        } catch (Exception ex) {
//            if (log.isErrorEnabled())
//                log.debug("exception when evaluating bean property");
//        }
//
//        return detailsMap;
//    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }

    public int getIndex(String key) {
        return keyList.indexOf(key);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer().append("[");
        for (int i = 0; i < countObject(); i++) {
            buffer.append(getObject(i));
            if (i != countObject() - 1) {
                buffer.append(", ");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }

    public DtoMBean next() {
        if ((count() - 1) <= currentIndex)
            return getObject(currentIndex);
        else
            return getObject(++currentIndex);
    }

    public DtoMBean previous() {
        if (0 >= currentIndex)
            return getObject(0);
        else
            return getObject(--currentIndex);
    }

    public DtoMBean last() {
        currentIndex = count() - 1;
        return getObject(currentIndex);
    }

    public DtoMBean first() {
        currentIndex = 0;
        return getObject(currentIndex);
    }

    public DtoMBean current() {
        return getObject(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public DtoCollection add(List list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof DtoMBean) {
                add((DtoMBean) list.get(i));
            }
        }
        return this;
    }

    public DtoCollectionMBean add(DtoCollectionMBean bc) {
        for (int i = 0; i < bc.countObject(); i++) {
            if (bc.getObject(i) instanceof DtoMBean) {
                add((DtoMBean) bc.getObject(i));
            }
        }
        return this;
    }


    public void removeObject(DtoMBean object) {
        keyList.remove(object.getKey());
        content.remove(object.getKey());
    }


    public boolean isExist(DtoMBean bean) {
        if (content.containsValue(bean)) return true;
        return false;
    }
    public boolean isExist(String key) {
        if (content.containsKey(key)) return true;
        return false;
    }
    
    public DtoCollection createClone(){
        DtoCollection col = (DtoCollection) clone();
        DtoCollection temp = new DtoCollection();
        Iterator iter = col.iterator();
        while(iter.hasNext()){
            temp.add(((Dto)iter.next()).createClone());
        }
        col = null;
        return temp;
    }
//    public static boolean isRegExpMatch(String value, String pattern) {
//        Perl5Compiler compiler = new Perl5Compiler();
//        Pattern compiledPattern;
//        try {
//            compiledPattern = compiler.compile(pattern,
//                    Perl5Compiler.READ_ONLY_MASK);
//        } catch (MalformedPatternException ex) {
//            throw new IllegalArgumentException(ex.getMessage());
//        }
//        PatternMatcher matcher = new Perl5Matcher();
//
//        boolean result = matcher.matches(value, compiledPattern);
//        return result;
//    }

}
