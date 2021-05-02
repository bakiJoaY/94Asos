package com.example.yao.a94Asos;

import cn.bmob.v3.BmobObject;

/**
 * Created by yaojunl on 2017/3/9.
 */

public class Messages extends BmobObject {

    private String recipient;
    private String name;
    private String tel;
    private String details;
    private String project;

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }

}
