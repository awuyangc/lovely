package org.wy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wy.service.TestService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 * 添加测试数据到数据库
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    private TestService testService;
    //注册用户
    @RequestMapping(value="/addTestData")
    @ResponseBody
    public int addTestData(HttpSession session, String param ) throws Exception{
        System.out.println("****************"+param+"******************");
        int result=testService.insertTestData();
        System.out.println("****************插入数据结束，一共插入 "+result+" 条数据******************");
        return result;
    }
}
