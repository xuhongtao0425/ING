package com.bw.xuhongtao.bean;

/**
 * @author xuhongtao
 * @fileName Quty
 * @package com.bw.xuhongtao.bean
 * @date 2019/3/23/023 15:46
 */
public class Quty {
    int  commodityId;
    int count;

    public Quty(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Quty{" +
                "commodityId=" + commodityId +
                ", count=" + count +
                '}';
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
