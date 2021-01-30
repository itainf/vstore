package com.vstore.framework.base.paging;

import org.apache.ibatis.session.RowBounds;

import java.util.List;


public class Page<T> extends RowBounds {

    private Integer prevNo;

    private Integer nextNo;

    private Integer count;

    private Integer current;

    private Integer size = 10;

    // 记录总数
    private Integer total = 0;

    private List<T> records;
    private List<T> statistics;

    private Integer pages = 0;

    public Page() {

    }

    public Page(int pageNo, int pageSize) {
        super((pageNo - 1) * pageSize, pageSize);
        this.current = pageNo;
        this.size = pageSize;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    // 计算总页数
    public Integer getPages() {
        if(total ==0 || size == 0){
            return 0;
        }
        if(total % size == 0){
            pages = total / size;
        }else{
            pages = (total / size) + 1;
        }
        return pages;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public int getLimit() {
        return size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public void setTotal(Integer total) {
        this.total = total;
        this.count = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public Integer getPrevNo() {
        if(0>= current -1){
            prevNo = 1;
        }else {
            prevNo = current -1;
        }
        return prevNo;
    }

    public Integer getNextNo() {
        if(getPages()<= current +1){
            nextNo = getPages();
        }else {
            nextNo = current +1;
        }
        return nextNo;
    }

    /**
     * @return the count
     */
    @Deprecated
    public Integer getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    @Deprecated
    public void setCount(Integer count) {
        this.count = count;
        this.total = count;
    }

    public List<T> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<T> statistics) {
        this.statistics = statistics;
    }
}
