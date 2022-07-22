package com.sinkiang.ddd.sample.common.page;

/**
 * @author dengxj
 * @date 2022/7/21 9:43
 */
public class PageRequest<T> {

    private long current;
    private long pageSize;
    private T data;

    public PageRequest() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
