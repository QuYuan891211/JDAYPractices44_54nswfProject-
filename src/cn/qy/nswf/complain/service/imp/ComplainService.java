package cn.qy.nswf.complain.service.imp;

import cn.qy.core.Utils.QueryHelper;
import cn.qy.core.service.imp.BaseService;
import cn.qy.nswf.complain.dao.IComplainDao;
import cn.qy.nswf.complain.entity.Complain;
import cn.qy.nswf.complain.service.IComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Created by qy on 2017/6/27.
 */
@Service("complainService")
public class ComplainService extends BaseService<Complain> implements IComplainService {

    private IComplainDao complainDao;

    @Resource
    public void setComplainDao(IComplainDao complainDao) {
        super.setBaseDao(complainDao);
        this.complainDao = complainDao;
    }

    public void autoDeal(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        //1.查询本月之前的待受理投诉列表
        QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
        queryHelper.addCondition("state=?",Complain.COMPLAIN_STATE_UNDONE);
        queryHelper.addCondition("compTime < ?",c.getTime());

        List<Complain> list = findAll(queryHelper);
        if (list != null && list.size()>0){
            //2.更新投诉信息为已失效
            for(Complain complain:list){
                complain.setState(Complain.COMPLAIN_STATE_INVALID);
                System.out.println("quartz更改状态");
                update(complain);
            }
        }

    }

}
