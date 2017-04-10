package cn.qy.core.exception;

/**
 * Created by qy on 2017/3/6.
 */
public abstract class SysException extends Exception{
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    private String errorMessage;
    public SysException(String m){
        super(m);
        this.errorMessage = m;
    }
    public SysException(Throwable cause){
        super(cause);
    }
    public SysException(String m,Throwable cause){
        super(m,cause);
    }
    public SysException(){

    }
}
