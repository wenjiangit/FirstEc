package com.mac.latte.core.refresh;

/**
 * 实现上拉加载更多的bean
 * Created by douliu on 2017/9/23.
 */

public class PagingBean {

    //当前是第几页
    private int pageIndex;
    //总的数目条数
    private int total;
    //总共有多少页
    private int pageSize;
    //当前的条目个数
    private int currentCount;
    //加载延迟
    private int delay;


    public int getPageIndex() {
        return pageIndex;
    }

    public PagingBean setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public PagingBean setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PagingBean setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public PagingBean setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
        return this;
    }

    public int getDelay() {
        return delay;
    }

    public PagingBean setDelay(int delay) {
        this.delay = delay;
        return this;
    }

    public PagingBean addIndex() {
        pageIndex++;
        return this;
    }
}
