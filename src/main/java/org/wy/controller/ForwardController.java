package org.wy.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.wy.model.Oauth2Token;
import org.wy.model.SNSUserInfo;
import org.wy.service.WechatService;
import org.wy.util.WeiXinUtil;

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
    @Resource
    private WechatService wechatService;

    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response){
        request.getHeader("user-agent");
        String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            return "login";
        }
        else{
            return "redirect:/admin/login.action";
        }
    }

    @RequestMapping("/index")
    public String toIndex(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        request.getHeader("user-agent");
        String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
        if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
            return "index";
        }
        else{
            return "wxcheck";
        }
    }
    @RequestMapping("/complete")
    public String toComplete(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        return "complete";
    }

    @RequestMapping("/vote")
    public String toVote(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        String redirect_uris="http://awuyangc.iask.in/forward/oauth.action";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeiXinUtil.getAPPID()+"&redirect_uri="+redirect_uris+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        //snsapi_userinfo
        //snsapi_base
        System.out.println(url);
        return "redirect:"+url;
    }
    @RequestMapping("/voteIndex")
    public String toVoteIndex(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        return "vote";
    }

    @RequestMapping("/bind")
    public String toBind(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        return "bind";
    }

    @RequestMapping("/test")
    public String toTest(HttpSession session, HttpServletRequest request, HttpServletResponse response){
        return "test";
    }

    //进行签名
    @RequestMapping("/oauth")
    public String oauth(HttpServletRequest request, HttpServletResponse response, String url) {
        //用户同意授权后可以获得code
        String code = request.getParameter("code");
        System.out.println("用户同意授权后的code: "+code);
        //用户同意授权
        if(!"authdeny".equals(code)){
            //获取网页授权access_token
            Oauth2Token oauth2Token = wechatService.getOauth2AccessToken(WeiXinUtil.getAPPID(), WeiXinUtil.getAPPSECRET(), code);
            //获取用户信息
            SNSUserInfo suser = wechatService.getSNSUserInfo(oauth2Token.getAccessToken(), oauth2Token.getOpenId());
            if(suser!=null){
                //执行保存绑定
                //String openid = suser.getOpenid();
                //logger.info("绑定的openid="+openid);
                request.getSession().setAttribute("suser",suser);
                //request.setAttribute("suser", suser);
            }
            else{
               return  "redirect:/forward/vote.action";
            }
        }
        //
        return "redirect:/forward/voteIndex.action"; //转发到指定的url
    }
}
