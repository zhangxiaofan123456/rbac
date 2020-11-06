package com.baizhi.controller;


import com.baizhi.entity.Permission;
import com.baizhi.entity.Response;
import com.baizhi.entity.User;

import com.baizhi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;



    @PostMapping("/login")
    public Response login(@RequestBody @Valid User user, HttpSession session, BindingResult bindingResult){
        List<User> users = iUserService.queryAllUser();
        for (User tempUser : users) {
            if (user.equals(tempUser)){
                User user1 = iUserService.queryUser(user.getName(), user.getPassword());
                List<Permission> allPermission = iUserService.getAllPermission(user1.getId());
                session.putValue("userPermission",allPermission);
                return Response.success("登录成功",null);

            }
        }
        return Response.failure("登录失败",null);
    }






}
