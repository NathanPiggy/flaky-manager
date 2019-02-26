/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carfun.entity.mechanic;

import java.util.Objects;

/**
 *
 * @author zhangjianlong
 */
public class ShopDto implements Comparable<ShopDto> {

    /**
     * 门店Id
     */
    private Integer id;
    
    /**
     * 门店类型 0:全部，1:社区店,2:品牌体验店
     */
    private Integer type;

    /**
     * 门店名
     */
    private String shopName;

    /**
     * 门店店长用户ID
     */
    private Integer shopManagerId;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 门店图
     */
    private String pic;

    /**
     * 距离(米)
     */
    private Double distance;

    @Override
    public int compareTo(ShopDto o) {
        if (o == null) {
            return -1;
        }
        
        Double distance1 = o.getDistance();
        if (this.distance < distance1) {
            return -1;
        } else {
            if (Objects.equals(this.distance, distance1)) {
                return 0;
            }
        }
        return 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getShopManagerId() {
        return shopManagerId;
    }

    public void setShopManagerId(Integer shopManagerId) {
        this.shopManagerId = shopManagerId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "ShopDto{" + "id=" + id + ", type=" + type + ", shopName=" + shopName + ", shopManagerId=" + shopManagerId + ", longitude=" + longitude + ", latitude=" + latitude + ", address=" + address + ", pic=" + pic + ", distance=" + distance + '}';
    }

}
