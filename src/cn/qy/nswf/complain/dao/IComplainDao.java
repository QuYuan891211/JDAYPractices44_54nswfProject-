package cn.qy.nswf.complain.dao;

import cn.qy.core.dao.IBaseDao;
import cn.qy.nswf.complain.entity.Complain;

import java.util.List;

/**
 * Created by qy on 2017/6/27.
 */
public interface IComplainDao extends IBaseDao<Complain> {
    List<Object[]> getAnnualStatisticDataByYear(int year);
}
