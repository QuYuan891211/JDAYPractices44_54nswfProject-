package Test.Test.Action;

import Test.Test.Service.Imp.TestService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.dispatcher.DefaultActionSupport;

import javax.annotation.Resource;

/**
 * Created by qy on 2017/2/23.
 */
public class TestAction extends ActionSupport {
    @Resource
    TestService ts;

    public String excute(){
        ts.Say();
        return SUCCESS;
    }

}
