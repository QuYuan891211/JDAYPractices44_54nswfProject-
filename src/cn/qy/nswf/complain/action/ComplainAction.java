package cn.qy.nswf.complain.action;

import cn.qy.core.Action.BaseAction;
import cn.qy.core.Utils.QueryHelper;
import cn.qy.nswf.complain.entity.Complain;
import cn.qy.nswf.complain.entity.ComplainReply;
import cn.qy.nswf.complain.service.IComplainService;
import com.opensymphony.xwork2.ActionContext;
import freemarker.template.utility.DateUtil;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by qy on 2017/6/27.
 */
public class ComplainAction extends BaseAction {
    @Resource
    private IComplainService complainService;

    private Complain complain;



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
}
