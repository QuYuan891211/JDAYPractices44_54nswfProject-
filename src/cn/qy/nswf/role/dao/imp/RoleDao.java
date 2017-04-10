package cn.qy.nswf.role.dao.imp;

import cn.qy.core.dao.imp.BaseDao;
import cn.qy.nswf.role.dao.IRoleDao;
import cn.qy.nswf.role.entity.Role;
import org.hibernate.Query;

import java.io.Serializable;

/**
 * Created by qy on 2017/3/6.
 */
public class RoleDao extends BaseDao<Role> implements IRoleDao {
    //根据角色ID删除其对应的权限
    public void deletePrivilegesByRoleId(Serializable id){
        String SQL = "DELETE FROM RolePrivilege WHERE id.role.roleId=?";
        Query query = getSession().createQuery(SQL);
        query.setParameter(0,id);
        query.executeUpdate();
    }
}
