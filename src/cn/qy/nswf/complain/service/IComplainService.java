package cn.qy.nswf.complain.service;

import cn.qy.core.service.IBaseService;
import cn.qy.nswf.complain.entity.Complain;

import java.util.List;
import java.util.Map;

/**
 * Created by qy on 2017/6/27.
 */
public interface IComplainService extends IBaseService<Complain> {
    public void autoDeal();
    List<Map> getAnnualStatisticData(int year);
}
