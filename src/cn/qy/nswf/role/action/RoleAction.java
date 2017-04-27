package cn.qy.nswf.role.action;

import cn.qy.core.Action.BaseAction;

import cn.qy.core.constant.Constant;
import cn.qy.core.exception.SysException;
import cn.qy.nswf.role.entity.Role;
import cn.qy.nswf.role.entity.RolePrivilege;
import cn.qy.nswf.role.entity.RolePrivilegeID;
import cn.qy.nswf.role.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;


import javax.annotation.Resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by qy on 2017/3/6.
 */
public class RoleAction extends BaseAction {
    @Resource
    private IRoleService roleService;
    private List<Role> roleList;
    private Role role;

    private String[] privilegeIds;


    public String TestUI(){
        return "TestUI";
    }
    //列表页面
    public String listUI() throws SysException {
        //put 方法仅用于一次请求，类似servlet里面的HttpServletRequest里面的set
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        roleList = roleService.findAll();
        return "listUI";
    }
    //跳转到新增页面
    public String addUI(){
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        return "addUI";
    }
    //保存新增
    public String add(){
        if(role != null){
            if(privilegeIds !=null && privilegeIds.length > 0){
                HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
                for(int i = 0; i < privilegeIds.length; i++){
                    set.add(new RolePrivilege(new RolePrivilegeID(role, privilegeIds[i])));
                }
                role.setRolePrivileges(set);
            }
        }
        roleService.save(role);
        return "list";
    }
    //跳转到编辑页面
    public String editUI(){
        if (role != null && role.getRoleId() != null) {
            ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);
            role = roleService.findById(role.getRoleId());
            if(role.getRolePrivileges()!=null){
                privilegeIds = new String[role.getRolePrivileges().size()];
                int i =0;
                for(RolePrivilege rp : role.getRolePrivileges()){
                    privilegeIds[i++] = rp.getId().getCode();
                }
            }

        }
        return "editUI";
    }
    //保存编辑
    public String edit(){
        if(role != null){
            ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVILEGE_MAP);
            if(privilegeIds !=null && privilegeIds.length > 0){
                HashSet<RolePrivilege>  set= new HashSet<RolePrivilege>();
                for(int i = 0; i < privilegeIds.length; i++){
                    set.add(new RolePrivilege(new RolePrivilegeID(role,privilegeIds[i])));
                }
                role.setRolePrivileges(set);
            }
            roleService.update(role);
        }
        return "list";
    }
    //删除
    public String delete(){
        if(role != null && role.getRoleId() != null){
            roleService.delete(role.getRoleId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if (selectedRow.length>0){
            for (String id: selectedRow) {
                roleService.delete(id);
            }
        }
        return  "list";
    }
    public List<Role> getRoleList() {
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public IRoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    public String[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }
}




