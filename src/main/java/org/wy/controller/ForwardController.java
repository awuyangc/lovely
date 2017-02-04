package org.wy.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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
    public String index(){
        List list=jdbcTemplate.queryForList("SELECT * from user");
        return "login";
    }
}
