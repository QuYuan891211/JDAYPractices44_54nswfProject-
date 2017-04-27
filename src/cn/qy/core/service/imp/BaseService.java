package cn.qy.core.service.imp;

import cn.qy.core.dao.IBaseDao;
import cn.qy.core.service.IBaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/4/27.
 */
public class BaseService<T> implements IBaseService<T>{

    public void setBaseDao(IBaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    private IBaseDao<T> baseDao;


    @Override
    public void save(T  t) {
        baseDao.save(t);
    }

    @Override
    public void update(T t) {
        baseDao.update(t);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public T findById(Serializable id) {
        return baseDao.findById(id);
    }

    @Override
    public List findAll() {
        return baseDao.findAll();
    }
}
