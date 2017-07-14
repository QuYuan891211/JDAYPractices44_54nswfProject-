package cn.qy.nswf.complain.action;

import cn.qy.core.Action.BaseAction;
import cn.qy.core.Utils.QueryHelper;
import cn.qy.nswf.complain.entity.Complain;
import cn.qy.nswf.complain.entity.ComplainReply;
import cn.qy.nswf.complain.service.IComplainService;
import com.opensymphony.xwork2.ActionContext;
import freemarker.template.utility.DateUtil;
import freemarker.template.utility.StringUtil;
import jdk.nashorn.internal.ir.RuntimeNode;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by qy on 2017/6/27.
 */
public class ComplainAction extends BaseAction {
    @Resource
    private IComplainService complainService;

    private Complain complain;

    private Map<String, Object> map;

    private ComplainReply reply;

    private String startTime;

    private String endTime;


    public String listUI() {
        ActionContext.getContext().getContextMap().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
        try {
            QueryHelper queryHelper = new QueryHelper(Complain.class, "i");

            if(StringUtils.isNotBlank(startTime)){
                startTime = URLDecoder.decode(startTime,"utf-8");
                queryHelper.addCondition("compTime >= ?", DateUtils.parseDate(startTime,"yyyy-MM-dd HH:mm"));
            }
            if(StringUtils.isNotBlank(endTime)){
                startTime = URLDecoder.decode(endTime,"utf-8");
                queryHelper.addCondition("compTime <= ?", DateUtils.parseDate(endTime,"yyyy-MM-dd HH:mm"));
            }
            if (complain != null) {
                if (StringUtils.isNotBlank(complain.getState())) {
                    //complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
                    queryHelper.addCondition("state=?", "%" + complain.getState()+"%");
                }
                if (StringUtils.isNotBlank(complain.getCompTitle())) {
                    complain.setCompTitle(URLDecoder.decode(complain.getCompTitle(), "utf-8"));
                    queryHelper.addCondition("compTitle like ?", "%" + complain.getCompTitle()+"%");
                }

//                if (StringUtils.isNotBlank(complain.getState()))
            }
            //按照投诉时间升序排序
            queryHelper.addOrderByProperty("compTime",QueryHelper.ORDER_BY_DESC);
            //按照状态升序排序
            queryHelper.addOrderByProperty("state",QueryHelper.ORDER_BY_ASC);
            pageResult = complainService.getPageResult(queryHelper, getCurrentPage(), getPageSize());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "listUI";
    }

    public String dealUI(){
        ActionContext.getContext().getContextMap().put("complainStateMap",Complain.COMPLAIN_STATE_MAP);
        if(complain !=null){
            complain = complainService.findById(complain.getCompId());
        }
        return "dealUI";
    }

    public String deal(){
        if(complain != null){
            Complain tem = complainService.findById(complain.getCompId());
            if(!Complain.COMPLAIN_STATE_DONE.equals(tem.getState())){
                tem.setState(Complain.COMPLAIN_STATE_DONE);
            }
            if (reply != null){
                reply.setComplain(tem);
                reply.setReplyTime(new Timestamp(new Date().getTime()));
                tem.getComplainReplies().add(reply);
            }
            complainService.update(tem);
        }
        return "list";
    }

    public String annualStatisticChartUI(){
        return "annualStatisticChartUI";
    }

    /**
     * 统计数返回给前台Json格式
     */
    public String getAnnualStatisticData(){
        //1.获取年份
        HttpServletRequest request = ServletActionContext.getRequest();
        int year = 0;
        if(request.getParameter("curYear")!=null) {
            year = Integer.valueOf(request.getParameter("curYear"));
        }else {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        //2.查出统计数
        map = new HashMap<String,Object>();
        //3.封装为json格式
        map.put("msg","success");
        map.put("chartData",complainService.getAnnualStatisticData(year));
        return "annualStatisticData";
    }


    /**
     * 统计数返回给前台Json格式（不使用插件的方法）
     */
    public String getAnnualStatisticData2(){
        //1.获取年份
        HttpServletRequest request = ServletActionContext.getRequest();
        Integer year = Integer.parseInt(request.getParameter("curYear"));

        //2.查出统计数

        //3.封装为json格式
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","success");
        jsonObject.accumulate("chartData",map);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(jsonObject.toString().getBytes("utf-8"));
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "annualStatisticData";
    }
    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public ComplainReply getReply() {
        return reply;
    }

    public void setReply(ComplainReply reply) {
        this.reply = reply;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
