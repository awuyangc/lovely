package org.wy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wy.model.User;
import org.wy.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/4/25.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    //注册用户
    @RequestMapping(value="/register")
    @ResponseBody
    public boolean register(HttpSession session, User user ) throws Exception{
        int resultInsertNum=userService.addUser(user);
        return resultInsertNum==1?true:false;
    }
}
