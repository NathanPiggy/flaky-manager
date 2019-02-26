package com.carfun.entity.mechanic;

import java.util.Date;

//技工端用户实体类
public class MechanicUser {
    private int id;
    private String userName;
    private String password;
    private int storeId;  //门店ID
    private Date createDate;


    public MechanicUser(String userName, String password, Date createDate) {
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
    }


    public MechanicUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    MechanicUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
