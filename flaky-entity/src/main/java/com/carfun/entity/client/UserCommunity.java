package com.carfun.entity.client;

import java.util.Date;

public class UserCommunity {
    private int id;
    private String userId;
    private String provinceName;
    private int provinceId;
    private String cityName;
    private int cityId;
    private String quartersName;  //楼盘名称
    private int appState;  //APP注册状态，0为未注册，1为已注册
    private String doorNum;  //门牌号
    private Date createDate;

    UserCommunity() {
    }

    public UserCommunity(String userId, int provinceId, String provinceName, int cityId, String cityName, String quartersName, String doorNum,int appState) {
        this.userId = userId;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.quartersName = quartersName;
        this.doorNum = doorNum;
        this.appState = appState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getQuartersName() {
        return quartersName;
    }

    public void setQuartersName(String quartersName) {
        this.quartersName = quartersName;
    }

    public int getAppState() {
        return appState;
    }

    public void setAppState(int appState) {
        this.appState = appState;
    }

    public String getDoorNum() {
        return doorNum;
    }

    public void setDoorNum(String doorNum) {
        this.doorNum = doorNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
