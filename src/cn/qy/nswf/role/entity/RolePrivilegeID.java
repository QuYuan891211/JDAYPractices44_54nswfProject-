package cn.qy.nswf.role.entity;

import cn.qy.core.constant.Constant;

import java.io.Serializable;

/**
 * Created by qy on 2017/3/6.
 * 这是联合主键类，里面的属性为Role和权限的中间表的字段，这些字段组成了RolePrivilege的联合住建
 */
public class RolePrivilegeID implements Serializable {
    public Role getRole() {
        return role;
    }

    public RolePrivilegeID(Role role, String code) {
        this.role = role;
        this.code = code;
    }

    public RolePrivilegeID() {
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RolePrivilegeID(String code) {
        this.code = code;
    }

    public RolePrivilegeID(Role role) {
        this.role = role;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private Role role;
    private String code;
}
