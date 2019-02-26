package com.carfun.entity.client;

import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private String userName;
    private int age;
    private String address;
    private Date createDate;
    private String password;
    private int userType;  //用户类型：0为普通,1为认证，3为会员
    private int userState;  //用户状态：0为启用,1为未启用
    private String phone;  //手机号
    private int appState;  //0为未注册，1为已注册
    private List<UserCar> userCars;

    public User(String userName, String password, Date createDate) {
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAppState() {
        return appState;
    }

    public void setAppState(int appState) {
        this.appState = appState;
    }

    public List<UserCar> getUserCars() {
        return userCars;
    }

    public void setUserCars(List<UserCar> userCars) {
        this.userCars = userCars;
    }
}
