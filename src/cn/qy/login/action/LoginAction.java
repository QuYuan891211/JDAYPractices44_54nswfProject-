package cn.qy.login.action;

import cn.qy.core.constant.Constant;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.service.IUserService;
import cn.qy.nswf.user.service.imp.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qy on 2017/4/21.
 */
public class LoginAction extends ActionSupport {
    @Resource
    private IUserService userService;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    private User user;
    private String loginResult;

    public String toLoginUI() {
        return "loginUI";
    }

    public String toNoPermissionUI() {
        return "noPermissionUI";
    }

    public String login() {
        if (user != null) {
            if (StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())) {

                List<User> list = userService.findUsersByAccountAndPassword(user.getAccount(), user.getPassword());

                //判断list是否符合要求的标准方式----因为list有可能为null,只调用size方式会出现空指针异常错误
                if (list != null && list.size() > 0) {
                    User user = list.get(0);
                    user.setUserRole(userService.getUserRolesById(user.getId()));

                    ActionContext.getContext().getSession().put(Constant.User, user);
                    return "home";
                }
            }

        }
        loginResult = "wrong account or password !";
        return "loginUI";
    }
    public String logout(){
        ActionContext.getContext().getSession().remove(Constant.User);
        return "loginUI";
    }
}

