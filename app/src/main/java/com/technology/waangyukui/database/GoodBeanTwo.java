package com.technology.waangyukui.database;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lenvo on 2018/6/15.
 */

public class GoodBeanTwo {
    @SerializedName("shopName")
    private String shopName;
    @SerializedName("image")
    private String image;
    @SerializedName("taobaoId")
    private String taobaoId;
    @SerializedName("couponPromotUrl")
    private String couponPromotUrl;
    @SerializedName("sellCount")
    private String sellCount;
    @SerializedName("goodsName")
    private String goodsName;
    @SerializedName("endTime")
    private String endTime;
    @SerializedName("cateId")
    private String cateId;
    @SerializedName("price")
    private String price;
    @SerializedName("couponDenomination")
    private String couponDenomination;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTaobaoId() {
        return taobaoId;
    }

    public void setTaobaoId(String taobaoId) {
        this.taobaoId = taobaoId;
    }

    public String getCouponPromotUrl() {
        return couponPromotUrl;
    }

    public void setCouponPromotUrl(String couponPromotUrl) {
        this.couponPromotUrl = couponPromotUrl;
    }

    public String getSellCount() {
        return sellCount;
    }

    public void setSellCount(String sellCount) {
        this.sellCount = sellCount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCouponDenomination() {
        return couponDenomination;
    }

    public void setCouponDenomination(String couponDenomination) {
        this.couponDenomination = couponDenomination;
    }
}

