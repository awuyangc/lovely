package org.wy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by wuyang on 2017/5/4.
 */
public class Question {
    private String id;
    private String imgUrl;
    private String chkA;
    private String chkB;
    private String chkC;
    private String chkD;
    private String chkE;
    private String chkF;
    private String answer;
    private String isDelete;
    private Date createtime;
    private String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getChkA() {
        return chkA;
    }

    public void setChkA(String chkA) {
        this.chkA = chkA;
    }

    public String getChkB() {
        return chkB;
    }

    public void setChkB(String chkB) {
        this.chkB = chkB;
    }

    public String getChkC() {
        return chkC;
    }

    public void setChkC(String chkC) {
        this.chkC = chkC;
    }

    public String getChkD() {
        return chkD;
    }

    public void setChkD(String chkD) {
        this.chkD = chkD;
    }

    public String getChkE() {
        return chkE;
    }

    public void setChkE(String chkE) {
        this.chkE = chkE;
    }

    public String getChkF() {
        return chkF;
    }

    public void setChkF(String chkF) {
        this.chkF = chkF;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
