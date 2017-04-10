package cn.qy.core.exception;

/**
 * Created by qy on 2017/3/6.
 */
public class ServiceException extends SysException{
    public ServiceException(){
        super("Service error");
    }
    public ServiceException(String s){
        super(s);
    }
}
