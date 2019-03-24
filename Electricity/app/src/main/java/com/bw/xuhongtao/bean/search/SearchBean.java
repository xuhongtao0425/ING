package com.bw.xuhongtao.bean.search;

import java.util.List;

/**
 * @author xuhongtao   搜索框
 * @fileName SearchBean
 * @package com.bw.xuhongtao.bean
 * @date 2019/3/21/021 15:10
 */
public class SearchBean {

    /**
     * result : [{"masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/4/1.jpg","price":449,"commodityId":138,"saleNum":0,"commodityName":"秋男鞋时尚男士休闲鞋系带防磨脚男士皮鞋潮流休闲板鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/nfbx/5/1.jpg","price":129,"commodityId":146,"saleNum":0,"commodityName":"时尚潮流 男鞋 套脚休闲板鞋帆布鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/px/6/1.jpg","price":469,"commodityId":154,"saleNum":0,"commodityName":"三接头秋冬男鞋 布洛克商务休闲皮鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/7/1.jpg","price":258,"commodityId":162,"saleNum":0,"commodityName":"冬季新款 牛皮纯色保暖绒里纯色套脚休闲鞋英伦风商务休闲鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/ydx/5/1.jpg","price":138,"commodityId":167,"saleNum":0,"commodityName":"鸿星尔克 新款气垫跑鞋复古轻便休闲鞋男款革面针织运动鞋 男鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/1/1.jpg","price":149,"commodityId":135,"saleNum":0,"commodityName":"青春时尚 潮流男鞋 韩版舒适简约百搭板鞋男士休闲鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/nfbx/2/1.jpg","price":44,"commodityId":143,"saleNum":0,"commodityName":"素雅净色男士套脚帆布鞋懒人鞋平底休闲鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/px/3/1.jpg","price":498,"commodityId":151,"saleNum":0,"commodityName":"明星同款西装商务皮鞋男韩版潮真皮头层牛皮系带英伦正装鞋布洛克雕花男鞋男士皮鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/4/1.jpg","price":249,"commodityId":159,"saleNum":0,"commodityName":"富贵鸟 头层牛皮系带百搭商务休闲鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/ydx/2/1.jpg","price":258,"commodityId":164,"saleNum":0,"commodityName":"耐克Nike KAISHI RUN 奥利奥轻便透气 男款运动休闲鞋"}]
     * message : 查询成功
     * status : 0000
     */
    public List<ResultEntity> result;
    public String message;
    public String status;

    @Override
    public String toString() {
        return "SearchBean{" +
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
         * masterPic : http://172.17.8.100/images/small/commodity/nx/nbx/4/1.jpg
         * price : 449
         * commodityId : 138
         * saleNum : 0
         * commodityName : 秋男鞋时尚男士休闲鞋系带防磨脚男士皮鞋潮流休闲板鞋
         */
        public String masterPic;
        public int price;
        public int commodityId;
        public int saleNum;
        public String commodityName;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "masterPic='" + masterPic + '\'' +
                    ", price=" + price +
                    ", commodityId=" + commodityId +
                    ", saleNum=" + saleNum +
                    ", commodityName='" + commodityName + '\'' +
                    '}';
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }
    }
}
