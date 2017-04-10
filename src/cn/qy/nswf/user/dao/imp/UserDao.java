package cn.qy.nswf.user.dao.imp;

import cn.qy.core.dao.imp.BaseDao;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.dao.IUserDao;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
public class UserDao extends BaseDao<User> implements IUserDao{
    @Override
    public List<User> findUsersByIdAndAccount(String id, String account) {
        String SQL = "FROM User WHERE account=?";
        if (StringUtils.isNotBlank(id)){
            SQL +=" AND id !=?";
        }
        Query query = getSession().createQuery(SQL);
        query.setParameter(0,account);
        if (StringUtils.isNotBlank(id)){
            query.setParameter(1,id);
        }
        return query.list();
    }


}
