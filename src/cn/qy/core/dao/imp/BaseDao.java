package cn.qy.core.dao.imp;

import cn.qy.core.Utils.QueryHelper;
import cn.qy.core.Utils.pageUtil.PageResult;
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

    public List findAll(QueryHelper queryHelper) {
        String hql = queryHelper.getListQueryHql();
        Query query = getSession().createQuery(hql);
        if (null != queryHelper.getParameters()) {
            for (int j = 0; j<queryHelper.getParameters().size();j++){
                query.setParameter(j,queryHelper.getParameters().get(j));
            }
        }
        return query.list();
    }

    /**分页查询
     * @param queryHelper
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageResult getPageResult(QueryHelper queryHelper, int currentPage, int pageSize){

        String hql = queryHelper.getListQueryHql();
        Query listQuery = getSession().createQuery(hql);
        if (null != queryHelper.getParameters()) {
            for (int j = 0; j<queryHelper.getParameters().size();j++){
                listQuery.setParameter(j,queryHelper.getParameters().get(j));
            }
        }

        if(currentPage == 0){currentPage=1;}
        listQuery.setFirstResult((currentPage-1)*pageSize);
        listQuery.setMaxResults(pageSize);

        List result =  listQuery.list();

        Query query = getSession().createQuery(queryHelper.getCountHql());
        List items = queryHelper.getParameters();
        if(items!=null){
            for(int i = 0; i< items.size();i++){
                query.setParameter(i,items.get(i));
            }
        }
        long totalCount = (long) query.uniqueResult();
        return  new PageResult(currentPage,totalCount,pageSize,result);

    }

}
