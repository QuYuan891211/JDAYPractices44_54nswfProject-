package cn.qy.nswf.user.dao;

import cn.qy.core.dao.IBaseDao;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.entity.UserRole;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
public interface IUserDao extends IBaseDao<User> {
    List<User> findUsersByIdAndAccount(String id, String account);
    void saveUserRole(UserRole userRole);
    List<UserRole> getUserRolesByUserId(Serializable id);
    void deleteUserRolesByUserId(Serializable id);
    List<User> findUserByAccountAndPassword(String account, String password);
}
