package cn.qy.nswf.user.service;

import cn.qy.nswf.user.Entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
public interface IUserService {
    void save(User user);
    void delete(Serializable id);
    void update(User user);
    User findById(Serializable id);
    List<User> findAll();
}
