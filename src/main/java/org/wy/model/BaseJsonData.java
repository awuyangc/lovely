package org.wy.model;

/**
 * Created by wuyang on 2017/5/12.
 */
import java.util.ArrayList;
import java.util.List;

public class BaseJsonData {
    List data=new ArrayList<Object>();

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

}
