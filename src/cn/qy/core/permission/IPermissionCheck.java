package cn.qy.core.permission;

import cn.qy.nswf.user.entity.User;

/**
 * Created by qy on 2017/4/22.
 */
public interface IPermissionCheck {
    public abstract boolean isAccessible(String module,User user);
}
