package cn.qy.nswf.user.service;

import cn.qy.nswf.user.Entity.User;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
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
    void exportExcel(List<User> list, ServletOutputStream outputStream) throws IOException;
    void importExcel(File excel, String name);
}
