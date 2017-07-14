package cn.qy.home.action;

import cn.qy.core.Utils.QueryHelper;
import cn.qy.core.constant.Constant;
import cn.qy.nswf.complain.entity.Complain;
import cn.qy.nswf.complain.service.IComplainService;
import cn.qy.nswf.info.entity.Info;
import cn.qy.nswf.info.service.IInfoService;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.service.IUserService;
import cn.qy.nswf.user.service.imp.UserService;
import com.opensymphony.xwork2.ActionContext;
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

    @Resource
    private IInfoService infoService;

    private Complain comp;

    private Info info;

    /**
     * @return
     */
    public String execute(){
        //1.获取消息
        QueryHelper queryHelper = new QueryHelper(Info.class, "i");
        queryHelper.addOrderByProperty("createTime",QueryHelper.ORDER_BY_DESC);
        List<Info> infoList = infoService.getPageResult(queryHelper,1,5).getItems();
        ActionContext.getContext().getContextMap().put("infoList",infoList);

        //2.获取投诉信息

        QueryHelper queryHelper2 = new QueryHelper(Complain.class, "c");
        queryHelper2.addOrderByProperty("compTime",QueryHelper.ORDER_BY_DESC);
        List<Complain> complainList = complainService.getPageResult(queryHelper2,1,5).getItems();
        ActionContext.getContext().getContextMap().put("complainList",complainList);

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

    /**
     * @return
     */
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


    public String infoViewUI(){
        ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);
        if(info != null){
            info  = infoService.findById(info.getInfoId());
        }
        return "infoViewUI";
    }

    public String complainViewUI(){
        ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
        if(comp != null){
            comp = complainService.findById(comp.getCompId());
        }
        return "complainViewUI";
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

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}

