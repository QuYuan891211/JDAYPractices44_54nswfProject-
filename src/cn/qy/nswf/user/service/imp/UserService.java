package cn.qy.nswf.user.service.imp;

import cn.qy.core.Utils.POIExcelUtil;
import cn.qy.core.exception.ServiceException;
import cn.qy.core.service.imp.BaseService;
import cn.qy.nswf.role.entity.Role;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.dao.IUserDao;
import cn.qy.nswf.user.entity.UserRole;
import cn.qy.nswf.user.entity.UserRoleID;
import cn.qy.nswf.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.List;

/**
 * Created by qy on 2017/2/27.
 */
@Service("userService")
public class UserService extends BaseService<User> implements IUserService {

    private IUserDao userDao;
    @Resource
    public void setUserDao(IUserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }




    @Override
    public void delete(Serializable id) {
        userDao.delete(id);
        userDao.deleteUserRolesByUserId(id);
    }


    @Override
    public void exportExcel(List<User> list, ServletOutputStream outputStream) throws IOException {
        POIExcelUtil.export(list,outputStream,"UserList");
    }

    @Override
    public void importExcel(File excel, String name) {
        boolean is03Excel = name.matches("^.+\\/(?i)(xls)$");
        try {
            FileInputStream inputStream = new FileInputStream(excel);
           List<User> list =  POIExcelUtil.importExcel(inputStream,is03Excel);
            for (User user:list
                 ) {
                save(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findUsersByIdAndAccount(String id, String account) {
        return userDao.findUsersByIdAndAccount(id,account);
    }
    //使用Java不定参数

    public void saveUserAndRole(User user, String... userRoleIds){
        save(user);
        if(userRoleIds != null){
            for(String roleId : userRoleIds){

                userDao.saveUserRole(new UserRole(new UserRoleID(new Role(roleId),user.getId())));
            }
        }
    }

    @Override
    public List<UserRole> getUserRolesById(String id) {
        return userDao.getUserRolesByUserId(id);
    }

    @Override
    public void updateUserAndRole(User user, String... userRoleIds) {
        userDao.deleteUserRolesByUserId(user.getId());
        update(user);
        if (userRoleIds !=null){
            for(String roleId : userRoleIds ){
                userDao.saveUserRole(new UserRole(new UserRoleID(new Role(roleId),user.getId())));
            }
        }
    }

    @Override
    public List<User> findUsersByAccountAndPassword(String account, String password) {
        return userDao.findUserByAccountAndPassword(account,password);
    }
}
