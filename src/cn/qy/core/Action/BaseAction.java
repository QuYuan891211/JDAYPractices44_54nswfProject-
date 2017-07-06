package cn.qy.core.Action;

import cn.qy.core.Utils.pageUtil.PageResult;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by qy on 2017/3/23.
 */
public class BaseAction extends ActionSupport {
    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }

    protected String[] selectedRow;

    private int currentPage;

    private int pageSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        if(pageSize<1){pageSize=DEFAULT_PAGE_SIZE;}
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }

    public static int DEFAULT_PAGE_SIZE = 5;

    protected PageResult pageResult;
}
