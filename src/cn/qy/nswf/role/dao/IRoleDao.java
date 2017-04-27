package cn.qy.nswf.role.dao;

import cn.qy.core.dao.IBaseDao;
import cn.qy.nswf.role.entity.Role;

import java.io.Serializable;

/**
 * Created by qy on 2017/3/6.
 */
public interface IRoleDao extends IBaseDao<Role> {
    void deletePrivilegesByRoleId(Serializable id);
}
