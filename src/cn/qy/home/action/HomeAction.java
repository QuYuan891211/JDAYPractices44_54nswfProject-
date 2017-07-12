package cn.qy.home.action;

import cn.qy.core.Utils.QueryHelper;
import cn.qy.nswf.complain.entity.Complain;
import cn.qy.nswf.complain.service.IComplainService;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.service.IUserService;
import cn.qy.nswf.user.service.imp.UserService;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qy on 2017/4/19.
 */
public class HomeAction extends ActionSupport {
    @Resource
    private IUserService userService;

    @Resource
    private IComplainService complainService;

    private Map<String,Object> map;



    private Complain comp;

    public String execute(){
        return "home";
    }
    //跳转到我要投诉
    public String complainAddUI(){
        return "complainAddUI";
    }

    public void getUserJson(){
        String dept = ServletActionContext.getRequest().getParameter("dept");
        if(StringUtils.isNotBlank(dept)){
            QueryHelper queryHelper = new QueryHelper(User.class,"u");
            queryHelper.addCondition("dept like ?",dept);
            List<User> userList = userService.findAll(queryHelper);
            //创建Json对象
            JSONObject jso = new JSONObject();
            //后台给前台的信息，任意信息都可以，这里返回一个成功信息
            jso.put("msg","success");
            //转为Json对象
            jso.accumulate("userList",userList);

            //输出
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                //转换为字符串以比特流形式
                outputStream.write(jso.toString().getBytes("utf-8"));
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public void complainAdd(){
        if(comp != null){
            comp.setState(Complain.COMPLAIN_STATE_UNDONE);
            comp.setCompTime(new Timestamp(new Date().getTime()));
            complainService.save(comp);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html");
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write("success".getBytes("utf-8"));
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public String getUserJson2(){
        String dept = ServletActionContext.getRequest().getParameter("dept");
        if(StringUtils.isNotBlank(dept)){
            QueryHelper queryHelper = new QueryHelper(User.class,"u");
            queryHelper.addCondition("dept like ?",dept);
            List<User> userList = userService.findAll(queryHelper);
            map = new HashMap<String,Object>();
            map.put("msg","success");
            map.put("userList",userList);


        }
        //类似JSONobj的put中的信息
        return SUCCESS;
    }




    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Complain getComp() {
        return comp;
    }

    public void setComp(Complain comp) {
        this.comp = comp;
    }

}

