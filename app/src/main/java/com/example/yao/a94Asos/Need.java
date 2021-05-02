package com.example.yao.a94Asos;

import cn.bmob.v3.BmobObject;

/**
 * Created by yaojunl on 2017/3/9.
 */

public class Need extends BmobObject {

    private String user;
    private String tel;
    private String details;

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}
