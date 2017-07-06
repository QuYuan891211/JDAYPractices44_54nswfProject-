package cn.qy.core.Utils.pageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qy on 2017/5/3.
 */
public class PageResult {
    private int currentPage;
    private int totalPageCount;
    private long totalCount;
    private int pageSize;
    private List items;


    public PageResult(int currentPage, long totalCount, int pageSize, List items) {

        this.totalCount = totalCount;
        this.items = items==null? new ArrayList():items;
        if (totalCount > 0){
            this.currentPage = currentPage;
            int tem = (int)totalCount/pageSize;
            this.totalPageCount = totalCount%pageSize==0?tem:(tem+1);
        }else {
            this.totalPageCount = 0;
            this.currentPage = 0;
        }
        this.pageSize = pageSize;

    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
