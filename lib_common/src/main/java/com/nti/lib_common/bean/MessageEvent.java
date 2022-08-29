package com.nti.lib_common.bean;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class MessageEvent {
    public int what;
    public int flag;
    public String obj;

    public MessageEvent() {
    }

    public MessageEvent(int what, String obj) {
        this.what = what;
        this.obj = obj;
    }

    public MessageEvent(int what, int flag) {
        this.what = what;
        this.flag = flag;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
