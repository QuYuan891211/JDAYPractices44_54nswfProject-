package cn.qy.nswf.user.dao.imp;

import cn.qy.core.dao.imp.BaseDao;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.dao.IUserDao;
import cn.qy.nswf.user.entity.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import java.io.Serializable;
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

    @Override
    public void saveUserRole(UserRole userRole) {
        getHibernateTemplate().save(userRole);
    }

    @Override
    public List<UserRole> getUserRolesByUserId(Serializable id) {
        Query query = getSession().createQuery("FROM UserRole WHERE id.userId = ?");
        query.setParameter(0,id);
        return query.list();
    }

    @Override
    public void deleteUserRolesByUserId(Serializable id) {
        Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId = ?");
        query.setParameter(0,id);
        query.executeUpdate();
    }

    @Override
    public List<User> findUserByAccountAndPassword(String account, String password) {
        Query query = getSession().createQuery("FROM User WHERE account = ? AND password = ? AND state = ?");
        query.setParameter(0,account);
        query.setParameter(1,password);
        query.setParameter(2,User.USER_STATE_VALID);
        return query.list();
    }


}
