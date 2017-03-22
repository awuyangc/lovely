package org.wy.controller;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.wy.model.User;
import org.wy.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wuyang on 2017/3/19.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;
    @RequestMapping(value="/check")
    @ResponseBody
    public String checklogin(HttpSession session, HttpServletRequest request, String username, String password) throws Exception {
        String result="";
        password = DigestUtils.md5DigestAsHex(password.getBytes()) ;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            result="未知账户";
        } catch (IncorrectCredentialsException ice) {
            result="密码不正确";
        } catch (LockedAccountException lae) {
            result="账户已锁定";
        } catch (ExcessiveAttemptsException eae) {
            result="用户名或密码错误次数过多";
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            result="用户名不存在";
        }
        /*
        //验证是否登录成功
        if (currentUser.isAuthenticated()) {
            System.out.println("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
        } else {
            token.clear();
        }
        */
        return result;
    }

    //退出系统
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        SecurityUtils.getSubject().logout();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
    }
}
