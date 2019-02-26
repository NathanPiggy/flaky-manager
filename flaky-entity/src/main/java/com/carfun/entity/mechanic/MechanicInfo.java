package com.carfun.entity.mechanic;


import java.util.Date;
import java.util.List;

public class MechanicInfo {
    private int id;
    private int userId;
    private int shopId;  //门店ID
    private String name;
    private String position;  //职位
    private int sex;  //性别，0为女性，1为男性
    private String mailbox;  //邮箱
    private String phone;
    private String shopName; //门店名称
    private int communtityId;  //社区ID
    private int deptId;  //部门ID
    private int cityId;  //城市ID
    private int jobId;  //职位ID
    private int accountState;  //账户状态，0：未启用，1：已启用
    private List<ShopDto> shops;  //获取门店列表
    private Date createDate;

    public MechanicInfo(int userId, String name, String position, String mailbox, String phone) {
        this.userId = userId;
        this.shopId = shopId;
        this.name = name;
        this.position = position;
        this.sex = sex;
        this.mailbox = mailbox;
        this.phone = phone;
    }


    public MechanicInfo(int userId, int sex, String mailbox, String phone) {
        this.userId = userId;
        this.shopId = shopId;
        this.name = name;
        this.position = position;
        this.sex = sex;
        this.mailbox = mailbox;
        this.phone = phone;
    }

    public MechanicInfo(int id, String name, String phone, int deptId, int jobId, int communtityId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.deptId = deptId;
        this.jobId = jobId;
        this.communtityId = communtityId;
    }


    MechanicInfo() {

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

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getCommuntityId() {
        return communtityId;
    }

    public void setCommuntityId(int communtityId) {
        this.communtityId = communtityId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getAccountState() {
        return accountState;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountState = accountStatus;
    }

    public List<ShopDto> getShops() {
        return shops;
    }

    public void setShops(List<ShopDto> shops) {
        this.shops = shops;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
