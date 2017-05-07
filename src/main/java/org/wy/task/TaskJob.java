package org.wy.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wy.model.AccessToken;
import org.wy.util.WeiXinUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * 微信凭证刷新控制
 * Created by Administrator on 2017/5/5.
 */
@Component("taskJob")
public class TaskJob {
    @Scheduled(fixedRate=1000*3600)
    public void job1() throws IOException {
        System.out.println("taskJob：：："+new Date());
        System.out.println("***获取凭证信息***");
        AccessToken accessToken=WeiXinUtil.getAccessToken();
    }

}
