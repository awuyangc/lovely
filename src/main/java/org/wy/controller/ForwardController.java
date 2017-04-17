package org.wy.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wuyang on 2017/1/15.
 */
@Controller
@RequestMapping("/forward")
public class ForwardController {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }
    @RequestMapping("/index")
    public String toIndex(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        return "index";
    }
    @RequestMapping("/iecheck")
    public String toIndex(){
        return "iecheck";
    }
}
