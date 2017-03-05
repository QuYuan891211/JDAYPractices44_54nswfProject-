package cn.qy.core.dao.imp;

import cn.qy.core.dao.IBaseDao;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
public abstract class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T>{
    Class<T> clazz;

    public BaseDao(){
        ParameterizedType pt =  (ParameterizedType)this.getClass().getGenericSuperclass();//BaseDaoImpl<User>
        clazz = (Class<T>)pt.getActualTypeArguments()[0];
    }
    @Override
    public void save(T o) {
        getHibernateTemplate().save(o);
    }

    @Override
    public void delete(Serializable id) {
        getHibernateTemplate().delete(findById(id));
    }

    @Override
    public void update(Object o) {
        getHibernateTemplate().update(o);
    }

    @Override
    public T findById(Serializable id) {
        return getHibernateTemplate().get(clazz,id);
    }

    @Override
    public List findAll() {
        Query query = getSession().createQuery("FROM " + clazz.getSimpleName());
        return query.list();
    }
}
