package com.baizhi.service.Impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Permission;
import com.baizhi.entity.User;
import com.baizhi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Permission> getAllPermission(Integer id) {
        return userDao.getAllPermission(id);
    }

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public User queryUser(String name,String password) {
        return userDao.queryUser(name,password);
    }
}
