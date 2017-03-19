package org.wy.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wuyang on 2017/3/19.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @RequestMapping(value="/check")
    @ResponseBody
    public boolean checklogin(HttpSession session, String username, String password) throws Exception{
        int resultCount=(Integer)jdbcTemplate.queryForObject("SELECT count(*) from user where user_id=? and password=password(?)",Integer.class,username,password);
        //在Session里保存信息
        if(resultCount==0){
            //用户名或密码错误
            return false;
        }
        else{
            //登录成功
            session.setAttribute("username", username);
            return true;
        }
    }

    //退出系统
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        session.invalidate();
        return "redirect:hello.action";
    }
}
