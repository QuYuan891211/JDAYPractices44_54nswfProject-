package cn.qy.nswf.user.dao;

import cn.qy.core.dao.IBaseDao;
import cn.qy.nswf.user.entity.User;

import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
public interface IUserDao extends IBaseDao<User> {
    List<User> findUsersByIdAndAccount(String id, String account);
}
