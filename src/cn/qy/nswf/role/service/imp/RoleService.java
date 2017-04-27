package cn.qy.nswf.role.service.imp;

import cn.qy.core.service.imp.BaseService;
import cn.qy.nswf.role.dao.IRoleDao;
import cn.qy.nswf.role.dao.imp.RoleDao;
import cn.qy.nswf.role.entity.Role;
import cn.qy.nswf.role.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/3/6.
 */
@Service("roleService")
public class RoleService extends BaseService<Role> implements IRoleService {

    private IRoleDao roleDao;
    @Resource
    public void setRoleDao(IRoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }



    @Override
    public void update(Role role) {
        //更新前先清空原来的权限
        roleDao.deletePrivilegesByRoleId(role.getRoleId());
        roleDao.update(role);
    }


}
