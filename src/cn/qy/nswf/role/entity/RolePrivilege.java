package cn.qy.nswf.role.entity;

import java.io.Serializable;

/**
 * Created by qy on 2017/3/6.
 */
public class RolePrivilege implements Serializable {
    public RolePrivilegeID getId() {
        return id;
    }

    public void setId(RolePrivilegeID id) {
        this.id = id;
    }

    public RolePrivilege(RolePrivilegeID id) {
        this.id = id;
    }

    public RolePrivilege() {
    }

    private RolePrivilegeID id;
}
