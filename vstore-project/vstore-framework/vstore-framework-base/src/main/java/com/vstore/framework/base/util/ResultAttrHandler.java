package com.vstore.framework.base.util;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.*;
import java.util.Map.Entry;

public class ResultAttrHandler implements ResultHandler {

    private List<Object>   list;

    public ResultAttrHandler() {
        list = new ArrayList<Object>();
    }

    @Override
    public void handleResult(ResultContext context) {
        Object obj = context.getResultObject();
        Map<String,Object> attrMap = new HashMap<>();
        if(obj instanceof HashMap){
            HashMap<String,Object> map = (HashMap<String,Object>)  obj;
            for(Iterator iter = map.entrySet().iterator();iter.hasNext();){
                Entry e = (Entry) iter.next();
                attrMap.put(MapUtils.getAttrKey(e.getKey().toString()), e.getValue());
            }
            list.add(attrMap);
        }else{
            list.add(context.getResultObject());
        }
    }



    public List<Object> getResultList() {
        return list;
    }

    public List<Object> getResultList(List resultList) {
        for (Object obj : resultList){
            Map<String,Object> attrMap = new HashMap<>();
            if(obj instanceof HashMap){
                HashMap map = (HashMap) obj;
                for(Iterator iter = map.entrySet().iterator();iter.hasNext();){
                    Entry e = (Entry) iter.next();
                    attrMap.put(MapUtils.getAttrKey(e.getKey().toString()), e.getValue());
                }
                list.add(attrMap);
            }else{
                list.add(obj);
            }
        }
        return list;
    }
}
