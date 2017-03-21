package org.wy.realm;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/3/21.
 */
@Configuration
public class ShiroConfig implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        Realm userRealm = (Realm) ac.getBean("myRealm");
        DefaultWebSecurityManager defaultWebSecurityManager = (DefaultWebSecurityManager) ac.getBean("securityManager");
        defaultWebSecurityManager.setRealm(userRealm);
    }
}
