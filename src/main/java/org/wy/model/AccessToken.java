package org.wy.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Created by wuyang on 2017/5/7.
 */
public class AccessToken {
    private String token;
    private String expiresIn;
    private static AccessToken accessToken = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public static AccessToken getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(AccessToken accessToken) {
        AccessToken.accessToken = accessToken;
    }

    private AccessToken() {
    }

    public static AccessToken getInstance(){
        if (accessToken == null) {
            accessToken = new AccessToken();
        }
        return accessToken;
    }

}
