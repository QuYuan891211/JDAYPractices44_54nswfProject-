package cn.qy.core.Action;

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
}
