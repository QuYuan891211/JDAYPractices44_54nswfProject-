package cn.qy.nswf.user.service.imp;

import cn.qy.core.Utils.POIExcelUtil;
import cn.qy.core.exception.ServiceException;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.dao.IUserDao;
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
    public List<User> findAll() throws ServiceException {
        try{

        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }

        return userDao.findAll();
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
}
