package cn.qy.nswf.role.service;

import cn.qy.nswf.role.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qy on 2017/3/6.
 */
public interface IRoleService {
    void save(Role role);
    void update(Role role);
    void delete(Serializable id);
    Role findById(Serializable id);
    List<Role> findAll();
}
