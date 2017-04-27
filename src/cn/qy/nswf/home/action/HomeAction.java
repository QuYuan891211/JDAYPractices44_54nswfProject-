package cn.qy.nswf.home.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by qy on 2017/4/21.
 */
public class HomeAction extends ActionSupport{
    public String frame(){
        return "frame";
    }
    public String top(){
        return "top";
    }
    public String left(){
        return "left";
    }
}
