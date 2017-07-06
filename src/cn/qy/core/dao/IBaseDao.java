package cn.qy.core.dao;

import cn.qy.core.Utils.QueryHelper;
import cn.qy.core.Utils.pageUtil.PageResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
public interface IBaseDao<T> {
    void save(T t);
    void delete(Serializable id);
    void update(T t);
    T findById(Serializable id);
    List<T> findAll();
    List<T> findAll(QueryHelper queryHelper);
    PageResult getPageResult(QueryHelper queryHelper,int currentPage, int pageSize);
}
