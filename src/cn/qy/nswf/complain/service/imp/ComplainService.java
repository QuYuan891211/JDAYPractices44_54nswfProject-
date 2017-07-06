package cn.qy.nswf.complain.service.imp;

import cn.qy.core.service.imp.BaseService;
import cn.qy.nswf.complain.dao.IComplainDao;
import cn.qy.nswf.complain.entity.Complain;
import cn.qy.nswf.complain.service.IComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
