package com.baizhi.dao;

import com.baizhi.entity.Permission;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {


     List<Permission> getAllPermission(@Param("id") Integer id);

     List<User> queryAllUser();

     User queryUser(@Param("name") String name,@Param("password") String password);
}
