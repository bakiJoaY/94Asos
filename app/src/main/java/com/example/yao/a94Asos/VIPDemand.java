package com.example.yao.a94Asos;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by yaojunl on 2017/2/28.
 */

public class VIPDemand extends BmobObject {


    private String user;
    private String name;
    private String target;
    private String scale;
    private String details;
    private BmobFile image;

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

    public BmobFile getImage(){return  image;}
    public void setImage(BmobFile image){
        this.image = image;
    }
}
