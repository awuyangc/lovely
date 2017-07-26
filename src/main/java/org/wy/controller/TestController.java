package org.wy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wy.model.*;
import org.wy.service.TestService;
import org.wy.util.WeiXinUtil;

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
    //添加数据
    @RequestMapping(value="/addTestData")
    @ResponseBody
    public int addTestData(HttpSession session, String param ) throws Exception{
        System.out.println("****************"+param+"******************");
        int result=testService.insertTestData();
        System.out.println("****************插入数据结束，一共插入 "+result+" 条数据******************");
        return result;
    }
    @RequestMapping(value="/createMenu")
    @ResponseBody
    public int createMenu(HttpSession session) throws Exception{
        AccessToken accessToken=AccessToken.getInstance();
        System.out.println("****************开始创建菜单******************");
        int result=testService.createMenu(accessToken.getToken());
        System.out.println("****************创建菜单完毕******************");
        if (0 == result)
           System.out.println("菜单创建成功！");
        else
           System.out.println("菜单创建失败，错误码：" + result);
        return result;
    }

}
