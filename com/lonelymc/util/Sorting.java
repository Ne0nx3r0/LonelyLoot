package com.lonelymc.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Sorting {
    public static Map sortByValueAsc(Map unsortMap) {	 
            List list = new LinkedList(unsortMap.entrySet());

            Collections.sort(list, new Comparator() {
                    public int compare(Object o1, Object o2) {
                            return ((Comparable) ((Map.Entry) (o1)).getValue())
                                                    .compareTo(((Map.Entry) (o2)).getValue());
                    }
            });

            Map sortedMap = new LinkedHashMap();
            for (Iterator it = list.iterator(); it.hasNext();) {
                    Map.Entry entry = (Map.Entry) it.next();
                    sortedMap.put(entry.getKey(), entry.getValue());
            }
            return sortedMap;
    } 
    
    public static Map sortByValueDesc(Map unsortMap) {	 
            List list = new LinkedList(unsortMap.entrySet());

            Collections.sort(list, new Comparator() {
                    public int compare(Object o1, Object o2) {
                            return ((Comparable) ((Map.Entry) (o2)).getValue())
                                                    .compareTo(((Map.Entry) (o1)).getValue());
                    }
            });

            Map sortedMap = new LinkedHashMap();
            for (Iterator it = list.iterator(); it.hasNext();) {
                    Map.Entry entry = (Map.Entry) it.next();
                    sortedMap.put(entry.getKey(), entry.getValue());
            }
            return sortedMap;
    } 
}
