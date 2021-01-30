package com.vstore.framework.base.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class MapUtils {

    public static Map<String, Object> toMap(Object...objs){
        Map<String, Object> map = new HashMap<>();
        int size = objs.length;
        if(size % 2 != 0){
            size = size - 1;
        }
        for(int i = 1; i < size; i++) {
            map.put(objs[i-1].toString(),objs[i]);
            i++;
        }
        return map;
    }



    public static String getAttrKey(String key) {
        if(StringUtils.isNotBlank(key)){
            if(StringUtils.containsIgnoreCase(key, "_")){
                boolean flag = true;
                StringBuilder sbd = new StringBuilder();
                for(String str: StringUtils.split(key, "_")){
                    if(flag){
                        sbd.append(StringUtils.lowerCase(str));
                    }else{
                        sbd.append(StringUtils.capitalize(StringUtils.lowerCase(str)));
                    }
                    flag = false;
                }
                return sbd.toString();
            }else{
                return StringUtils.lowerCase(key);
            }
        }
        return key;
    }

    public static String setAttrKey(String key){
        if (StringUtils.isNotBlank(key)){
            StringBuilder sb=new StringBuilder(key);
            int temp=0;
            if (!key.contains("_")) {
                for(int i=0;i<key.length();i++){
                    if(Character.isUpperCase(key.charAt(i))){
                        sb.insert(i+temp, "_");
                        temp+=1;
                    }
                }
            }
            return sb.toString().toLowerCase();
        }
        return key;
    }
}
