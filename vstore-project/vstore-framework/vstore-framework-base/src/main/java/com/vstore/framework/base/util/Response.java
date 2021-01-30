package com.vstore.framework.base.util;

import java.util.List;

/**
 * @author vstore
 */
public class Response<T> {

    private int   resCode;
    private String   resMsg;
    private T   result ;
    private List<T>   resultList ;
    private String   isSuccess;


    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Response<T> ok(){
        this.setIsSuccess("true");
        return this;
    }

    public Response<T> ok(T result){
        this.setIsSuccess("true");
        this.setResult(result);
        return this;
    }

    public Response<T> ok(List<T> result){
        this.setIsSuccess("true");
        this.setResultList(result);
        return this;
    }
}
