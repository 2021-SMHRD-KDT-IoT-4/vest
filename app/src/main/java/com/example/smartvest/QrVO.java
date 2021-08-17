package com.example.smartvest;

public class QrVO {


    private String tv_w_name, tv_inout, tv_edu, tv_limit, tv_live;

    public QrVO(String tv_w_name, String tv_inout, String tv_edu, String tv_limit, String tv_live) {
        this.tv_w_name = tv_w_name;
        this.tv_inout = tv_inout;
        this.tv_edu = tv_edu;
        this.tv_limit = tv_limit;
        this.tv_live = tv_live;
    }

    public String getTv_w_name() {
        return tv_w_name;
    }

    public void setTv_w_name(String tv_w_name) {
        this.tv_w_name = tv_w_name;
    }

    public String getTv_inout() {
        return tv_inout;
    }

    public void setTv_inout(String tv_inout) {
        this.tv_inout = tv_inout;
    }

    public String getTv_edu() {
        return tv_edu;
    }

    public void setTv_edu(String tv_edu) {
        this.tv_edu = tv_edu;
    }

    public String getTv_limit() {
        return tv_limit;
    }

    public void setTv_limit(String tv_limit) {
        this.tv_limit = tv_limit;
    }

    public String getTv_live() {
        return tv_live;
    }

    public void setTv_live(String tv_live) {
        this.tv_live = tv_live;
    }
}
