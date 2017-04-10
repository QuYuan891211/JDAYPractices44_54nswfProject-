package cn.qy.nswf.role.action;

import cn.qy.core.Action.BaseAction;

import cn.qy.core.exception.SysException;
import cn.qy.nswf.role.entity.Role;
import cn.qy.nswf.role.service.IRoleService;


import javax.annotation.Resource;

import java.util.List;


/**
 * Created by qy on 2017/3/6.
 */
public class RoleAction extends BaseAction {
    @Resource
    private IRoleService roleService;

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

    private List<Role> roleList;
    private Role role;


    public String TestUI(){
        return "TestUI";
    }
    //列表页面
    public String listUI() throws SysException {
        roleList = roleService.findAll();
        return "listUI";
    }
    //跳转到新增页面
    public String addUI(){
        return "addUI";
    }
    //保存新增
    public String add(){

        roleService.save(role);
        return "list";
    }
    //跳转到编辑页面
    public String editUI(){
        if (role != null && role.getRoleId() != null) {
            role = roleService.findById(role.getRoleId());
        }
        return "editUI";
    }
    //保存编辑
    public String edit(){
        if(role != null){
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


}




