package cn.qy.nswf.user.service.imp;

import cn.qy.nswf.user.Entity.User;
import cn.qy.nswf.user.dao.IUserDao;
import cn.qy.nswf.user.dao.imp.UserDao;
import cn.qy.nswf.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
@Service("userService")
public class UserService implements IUserService {


    @Resource()
    private IUserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(Serializable id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User findById(Serializable id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
