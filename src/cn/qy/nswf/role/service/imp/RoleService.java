package cn.qy.nswf.role.service.imp;

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
public class RoleService implements IRoleService {
    @Resource
    private IRoleDao roleDao;

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        //更新前先清空原来的权限
        roleDao.deletePrivilegesByRoleId(role.getRoleId());
        roleDao.update(role);
    }

    @Override
    public void delete(Serializable id) {
        roleDao.delete(id);
    }

    @Override
    public Role findById(Serializable id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
