package cn.qy.nswf.complain.service.imp;

import cn.qy.core.Utils.QueryHelper;
import cn.qy.core.service.imp.BaseService;
import cn.qy.nswf.complain.dao.IComplainDao;
import cn.qy.nswf.complain.entity.Complain;
import cn.qy.nswf.complain.service.IComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Override
    public List<Map> getAnnualStatisticData(int year) {
        List<Map> resList = new ArrayList<Map>();
        List<Object[]> list = complainDao.getAnnualStatisticDataByYear(year);
        if(list != null && list.size()>0) {
            //1.是否为当前年
            boolean isCurYear = (Calendar.getInstance().get(Calendar.YEAR) == year);
            //2.获取当月
            int curMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; //月份从0开始
            int temMonth = 0;
            Map<String, Object> map = null;
            //格式化数据，每一个map里面包含一个label和一个value
            for (Object[] objects : list) {
                temMonth = Integer.valueOf(objects[0] + "");
                map = new HashMap<String, Object>();
                map.put("label", temMonth + "月");
                //3.若为当年：在当月之前，0或者null均为0，若在当月之后，均为null
                if (isCurYear) {
                    if (temMonth < curMonth) {
                        map.put("value", objects[1] == null ? 0 : objects[1]);
                    } else {
                        map.put("value", "");
                    }
                } else {
                    //4.若不为当年:0或者null均为0
                    map.put("value", objects[1] == null ? 0 : objects[1]);
                }
                resList.add(map);
            }

        }

        return resList;
    }


}
