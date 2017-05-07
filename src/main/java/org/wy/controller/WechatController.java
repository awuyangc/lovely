package org.wy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wy.model.AccessToken;
import org.wy.service.WechatService;
import org.wy.util.SignUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    private String DNBX_TOKEN="yourareworthit";
    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);

    @Resource
    private WechatService wechatService;

    /**
     * 微信接入
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/connect",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void connectWeixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter out = response.getWriter();
        try {
            if (isGet) {
                String signature = request.getParameter("signature");// 微信加密签名
                String timestamp = request.getParameter("timestamp");// 时间戳
                String nonce = request.getParameter("nonce");// 随机数
                String echostr = request.getParameter("echostr");//随机字符串

                // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
                if (SignUtil.checkSignature(DNBX_TOKEN, signature, timestamp, nonce)) {
                    LOGGER.info("微信服务器对接成功.");
                    response.getWriter().write(echostr);
                } else {
                    LOGGER.error("Failed to verify the signature!");
                }
            } else {
                String respMessage = "异常消息！";

                try {
                    respMessage = wechatService.weixinPost(request);
                    out.write(respMessage);
                    LOGGER.info("The request completed successfully");
                    LOGGER.info("to weixin server " + respMessage);
                } catch (Exception e) {
                    LOGGER.error("转换微信信息失败!");
                }

            }
        } catch (Exception e) {
            LOGGER.error("连接微信服务器失败.");
        } finally {
            out.close();
        }
    }

    //进行签名
    @RequestMapping("/JSSDK_Config")
    @ResponseBody
    public AccessToken JSSDK_Config(HttpServletRequest request,String url) {
        AccessToken access_token=(AccessToken) request.getSession().getAttribute("access_token");
        System.out.println("当前access_token为："+access_token.getToken());
        return access_token;
    }

}
