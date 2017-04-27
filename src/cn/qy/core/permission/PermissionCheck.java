package cn.qy.core.permission;

import cn.qy.nswf.role.entity.Role;
import cn.qy.nswf.role.entity.RolePrivilege;
import cn.qy.nswf.user.entity.User;
import cn.qy.nswf.user.entity.UserRole;
import cn.qy.nswf.user.service.IUserService;
import cn.qy.nswf.user.service.imp.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qy on 2017/4/22.
 */
public class PermissionCheck implements IPermissionCheck {
    @Resource
    private IUserService userService;

    @Override
    public boolean isAccessible(String module, User user) {
        //在登录的时候就存入userRole
        List<UserRole> list = user.getUserRole();
        if(list==null){
            list = userService.getUserRolesById(user.getId());
        }
        if(list !=null&&list.size()>0){
            for(UserRole userRole:list){
                Role role = userRole.getId().getRole();
                for (RolePrivilege rp:role.getRolePrivileges()){
                    if (module.equals(rp.getId().getCode())){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
