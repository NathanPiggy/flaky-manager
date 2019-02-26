package com.carfun.entity.client;

import java.util.Date;

public class UserInfo {
    private int id;
    private int userId;
    private String name;
    private String nickName;
    private int sex;  //性别，0为女性，1为男性
    private String mailbox;  //邮箱
    private String phone;
    private Date createDate;

    public UserInfo(int userId,String name,String nickName,int sex,String mailbox,String phone){
        this.userId =  userId;
        this.name = name;
        this.nickName = nickName;
        this.sex = sex;
        this.mailbox = mailbox;
        this.phone = phone;
    }

    UserInfo(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
