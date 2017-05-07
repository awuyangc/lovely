package org.wy.model;

/**
 * Created by wuyang on 2017/5/7.
 */
public class JSTicket {
    private int errcode;
    private String errmsg;
    private String ticket;
    private String  expires_in;
    private static JSTicket jsTicket=null;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public JSTicket getJsTicket() {
        return jsTicket;
    }

    public void setJsTicket(JSTicket jsTicket) {
        this.jsTicket = jsTicket;
    }

    private JSTicket(){

    }

    public static JSTicket getInstance(){
        if (jsTicket == null) {
            jsTicket = new JSTicket();
        }
        return jsTicket;
    }

}
