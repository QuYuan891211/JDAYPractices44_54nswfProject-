package cn.qy.nswf.info.service.imp;

import cn.qy.core.service.imp.BaseService;
import cn.qy.nswf.info.dao.IInfoDao;
import cn.qy.nswf.info.entity.Info;
import cn.qy.nswf.info.service.IInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/4/26.
 */
@Service("infoService")
public class InfoService extends BaseService<Info> implements IInfoService {

    private IInfoDao infoDao;
    @Resource
    public void setInfoDao(IInfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }


}
