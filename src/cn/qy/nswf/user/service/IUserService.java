package cn.qy.nswf.user.service;

import cn.qy.core.exception.ServiceException;
import cn.qy.core.service.IBaseService;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.entity.UserRole;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
public interface IUserService extends IBaseService<User> {
    void exportExcel(List<User> list, ServletOutputStream outputStream) throws IOException;
    void importExcel(File excel, String name);
    List<User> findUsersByIdAndAccount(String id,String account);
    void saveUserAndRole(User user, String... userRoleIds);
    List<UserRole> getUserRolesById(String id);
    void updateUserAndRole(User user,String... userRoleIds);
    List<User> findUsersByAccountAndPassword(String account,String password);

}
