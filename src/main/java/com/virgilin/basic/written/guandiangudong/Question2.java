package com.virgilin.basic.written.guandiangudong;

import java.util.*;
/**
 * 自定定义字典集合，HashMap中无法同时存放Object和Map类型的数据
 */
class Dict{
    String key;
    List<Dict> dicts;
    Integer value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Dict> getDicts() {
        return dicts;
    }

    public void setDicts(List<Dict> dicts) {
        this.dicts = dicts;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value != null) {
            return "{" + key + ':' + value + '}';
        }
        return "{" + key + ':' + dicts + '}';
    }
}

public class Question2 {
    public static void main(String[] args) {
        //{ ‘A’: 1, ‘B.A’: 2, ‘B.B’: 3, ‘CC.D.E’: 4, ‘CC.D.F’: 5}
        Map<String ,Integer> map = new HashMap<>();
        map.put("A",1);
        map.put("B.A",2);
        map.put("B.B",3);
        map.put("CC.D.E",4);
        map.put("CC.D.F",5);
        List<Dict> dicts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] split = key.split("\\.");
            //当前操作的字典集合
            List<Dict> current = dicts;
            for (int i = 0; i < split.length; i++) {
                //从当前操作的集合中
                Dict theDict = getDict(current, split[i]);
                if (theDict == null) {
                    theDict = new Dict();
                    theDict.setKey(split[i]);
                    current.add(theDict);
                }
                List<Dict> dicts1 = theDict.getDicts();
                //不是最后一个字符串需要存放到集合字典中
                if (i < split.length - 1) {
                    if (dicts1 == null) {
                        dicts1 = new ArrayList<>();
                    }
                    Dict next = getDict(dicts1, split[i + 1]);
                    if (next == null) {
                        next = new Dict();
                        next.setKey(split[i+1]);
                        dicts1.add(next);
                    }
                    theDict.setDicts(dicts1);
                    current = dicts1;
                } else {
                    //最后一个字符串存放数据
                    theDict.setValue(entry.getValue());
                }
            }
        }
        System.out.println(dicts);
    }

    /**
     * 从当前操作的集合字典中找出对应的字典
     * @param dicts
     * @param s
     * @return
     */
    public static Dict getDict( List<Dict> dicts,String s){
        for (Dict dict : dicts) {
            if (dict.getKey().endsWith(s)) {
                return dict;
            }
        }
        return null;
    }
}
