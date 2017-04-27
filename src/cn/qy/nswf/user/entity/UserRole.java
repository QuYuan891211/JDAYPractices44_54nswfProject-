package cn.qy.nswf.user.entity;

import java.io.Serializable;

/**
 * Created by qy on 2017/4/15.
 */
public class UserRole implements Serializable {
    private UserRoleID id;

    public UserRole(){

    }
    public UserRole(UserRoleID id){
        this.id = id;
    }

    public UserRoleID getId() {
        return id;
    }

    public void setId(UserRoleID id) {
        this.id = id;
    }
}
