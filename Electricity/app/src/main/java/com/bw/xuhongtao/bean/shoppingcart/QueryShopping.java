package com.bw.xuhongtao.bean.shoppingcart;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName QueryShopping
 * @package com.bw.xuhongtao.bean.shoppingcart
 * @date 2019/3/23/023 16:13
 */
public class QueryShopping {
    String status;
    String message;
    List<Result> result;

    @Override
    public String toString() {
        return "QueryShopping{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {
        int commodityId;
        String commodityName;
        String pic;
        double price;
        int count;

        @Override
        public String toString() {
            return "Result{" +
                    "commodityId=" + commodityId +
                    ", commodityName='" + commodityName + '\'' +
                    ", pic='" + pic + '\'' +
                    ", price=" + price +
                    ", count=" + count +
                    '}';
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
