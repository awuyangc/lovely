package org.wy.model;

import java.sql.Array;
import java.util.List;

/**
 * Created by wuyang on 2017/3/20.
 */
public class User {
    private String  user_id;
    private String  user_name;
    private String  password;
    private String  user_nickname;
    private List<Role> roles;
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles ) {
        this.roles = roles;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }
}
