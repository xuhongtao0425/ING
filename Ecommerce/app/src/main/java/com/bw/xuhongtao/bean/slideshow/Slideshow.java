package com.bw.xuhongtao.bean.slideshow;

import java.util.List;

/**
 * @author xuhongtao   轮播图
 * @fileName Slideshow
 * @package com.bw.xuhongtao.bean.slideshow
 * @date 2019/3/20/020 20:12
 */
public class Slideshow {

    /**
     * result : [{"imageUrl":"http://172.17.8.100/images/small/banner/cj.png","rank":5,"title":"抽奖","jumpUrl":"http://172.17.8.100/htm/lottery/index.html"},{"imageUrl":"http://172.17.8.100/images/small/banner/hzp.png","rank":5,"title":"美妆工具","jumpUrl":"wd://commodity_list?arg=1001007005"},{"imageUrl":"http://172.17.8.100/images/small/banner/lyq.png","rank":5,"title":"连衣裙","jumpUrl":"wd://commodity_info?arg=83"},{"imageUrl":"http://172.17.8.100/images/small/banner/px.png","rank":5,"title":"跑鞋","jumpUrl":"wd://commodity_info?arg=165"},{"imageUrl":"http://172.17.8.100/images/small/banner/wy.png","rank":5,"title":"卫衣","jumpUrl":"wd://commodity_list?arg=1001002004"}]
     * message : 查询成功
     * status : 0000
     */
    public List<ResultEntity> result;
    public String message;
    public String status;

    @Override
    public String toString() {
        return "Slideshow{" +
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
         * imageUrl : http://172.17.8.100/images/small/banner/cj.png
         * rank : 5
         * title : 抽奖
         * jumpUrl : http://172.17.8.100/htm/lottery/index.html
         */
        public String imageUrl;
        public int rank;
        public String title;
        public String jumpUrl;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "imageUrl='" + imageUrl + '\'' +
                    ", rank=" + rank +
                    ", title='" + title + '\'' +
                    ", jumpUrl='" + jumpUrl + '\'' +
                    '}';
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }
    }
}
