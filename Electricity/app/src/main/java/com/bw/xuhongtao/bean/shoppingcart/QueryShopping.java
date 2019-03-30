package com.bw.xuhongtao.bean.shoppingcart;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @author xuhongtao
 * @fileName QueryShopping
 * @package com.bw.xuhongtao.bean.shoppingcart
 * @date 2019/3/23/023 16:13
 */
public class QueryShopping implements Serializable{
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

    public class Result implements Serializable {
        int commodityId;
        String commodityName;
        String pic;
        double price;
        int count;
        boolean checked;

        @Override
        public String toString() {
            return "Result{" +
                    "commodityId=" + commodityId +
                    ", commodityName='" + commodityName + '\'' +
                    ", pic='" + pic + '\'' +
                    ", price=" + price +
                    ", count=" + count +
                    ", checked=" + checked +
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

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
    }
}
