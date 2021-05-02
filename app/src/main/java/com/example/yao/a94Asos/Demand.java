package com.example.yao.a94Asos;

import cn.bmob.v3.BmobObject;

/**
 * Created by yaojunl on 2017/3/8.
 */

public class Demand extends BmobObject {

    private String user;
    private String name;
    private String target;
    private String scale;
    private String details;

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }

    public String getScale() {
        return scale;
    }
    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

}
