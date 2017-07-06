package cn.qy.core.service;

import cn.qy.core.Utils.QueryHelper;
import cn.qy.core.Utils.pageUtil.PageResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/4/27.
 */
public interface IBaseService<T> {
    void save(T t);
    void update(T t);
    void delete(Serializable id);
    T findById(Serializable id);
    List<T> findAll();
    List<T> findAll(QueryHelper queryHelper);
    PageResult getPageResult(QueryHelper queryHelper, int currentPage, int pageSize);
}
