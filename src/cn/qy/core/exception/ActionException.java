package cn.qy.core.exception;

import javax.swing.*;

/**
 * Created by qy on 2017/3/6.
 */
public class ActionException extends SysException{
    public ActionException(){
        super("Action error");
    }
    public ActionException(String m){
        super(m);
    }
}
