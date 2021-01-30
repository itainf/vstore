package com.vstore.framework.base.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapAttrHandler<K,V> {

    private Map<K,V> map = new HashMap<>();

    public Map<K, V> getMap() {
        return map;
    }

    public Map<K,V> getResultMap(Map<K,V> oMap){
        if (oMap != null && oMap.size()>0){
            for(K k:oMap.keySet()){
                V v= oMap.get(k);
                if (v instanceof Map && v != null){
                    Map attrMap = new HashMap();
                    Map<String,Object> mapv = (Map<String, Object>) v;
                    for(Iterator iter = mapv.entrySet().iterator(); iter.hasNext();){
                        Map.Entry e = (Map.Entry) iter.next();
                        attrMap.put( MapUtils.getAttrKey(e.getKey().toString()), e.getValue());
                    }
                    map.put(k, (V) attrMap);
                }else {
                    map.put(k,v);
                }
            }
        }
        return map;
    }





}
