package com.baizhi.service;

import com.baizhi.entity.Permission;
import com.baizhi.entity.User;

import java.util.List;

public interface IUserService {
    List<Permission> getAllPermission(Integer id);

    List<User> queryAllUser();

    User queryUser(String name,String password);
}
