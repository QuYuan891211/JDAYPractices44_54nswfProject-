package cn.qy.nswf.user.entity;

import cn.qy.nswf.role.entity.Role;

import java.io.Serializable;

/**
 * Created by qy on 2017/4/15.
 */
public class UserRoleID implements Serializable {
    private Role role;
    private String userId;

    public UserRoleID() {
    }

    public UserRoleID(Role role, String userId) {
        this.role = role;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoleID that = (UserRoleID) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
