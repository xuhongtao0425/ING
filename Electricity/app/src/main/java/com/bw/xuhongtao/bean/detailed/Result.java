package com.bw.xuhongtao.bean.detailed;

/**
 * @author xuhongtao
 * @fileName Result
 * @package com.bw.xuhongtao.bean
 * @date 2019/3/19/019 20:12
 */
public class Result {
    String categoryId;
    String stock;
    String weight;
    String categoryName;
    String commentNum;
    String commodityId;
    String commodityName;
    String describe;
    String details;
    String picture;
    String price;
    String saleNum;

    @Override
    public String toString() {
        return "Result{" +
                "categoryId='" + categoryId + '\'' +
                ", stock='" + stock + '\'' +
                ", weight='" + weight + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", describe='" + describe + '\'' +
                ", details='" + details + '\'' +
                ", picture='" + picture + '\'' +
                ", price='" + price + '\'' +
                ", saleNum='" + saleNum + '\'' +
                '}';
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }
}
