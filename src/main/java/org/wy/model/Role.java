package org.wy.model;





import java.util.List;

/**
 * Created by wuyang on 2017/3/20.
 */
public class Role {
    private String name;
    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }


}
