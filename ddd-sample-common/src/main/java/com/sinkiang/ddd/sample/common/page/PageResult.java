package com.sinkiang.ddd.sample.common.page;

import java.util.List;

/**
 * @author dengxj
 * @date 2022/7/21 9:44
 */
public class PageResult<T> {

    private long total;
    private long current;
    private long pageSize;
    private List<T> list;

    public PageResult() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
