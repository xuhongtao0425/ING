package com.bw.xuhongtao.bean.queryshippingaddress;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName QueryAddress
 * @package com.bw.xuhongtao.bean.queryshippingaddress
 * @date 2019/3/29/029 16:37   查询收货地址
 */
public class QueryAddress {

    /**
     * result : [{"realName":"诸葛","zipCode":"100001","address":"北京","createTime":1542474327000,"phone":"18736970210","id":6,"userId":12,"whetherDefault":1}]
     * message : 查询成功
     * status : 0000
     */
    public List<ResultEntity> result;
    public String message;
    public String status;

    @Override
    public String toString() {
        return "QueryAddress{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class ResultEntity {
        /**
         * realName : 诸葛
         * zipCode : 100001
         * address : 北京
         * createTime : 1542474327000
         * phone : 18736970210
         * id : 6
         * userId : 12
         * whetherDefault : 1
         */
        public String realName;
        public String zipCode;
        public String address;
        public long createTime;
        public String phone;
        public int id;
        public int userId;
        public int whetherDefault;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "realName='" + realName + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    ", address='" + address + '\'' +
                    ", createTime=" + createTime +
                    ", phone='" + phone + '\'' +
                    ", id=" + id +
                    ", userId=" + userId +
                    ", whetherDefault=" + whetherDefault +
                    '}';
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }
    }
}
