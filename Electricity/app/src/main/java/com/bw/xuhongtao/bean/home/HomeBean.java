package com.bw.xuhongtao.bean.home;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName HomeBean
 * @package com.bw.xuhongtao.bean.home
 * @date 2019/3/20/020 20:18
 */
public class HomeBean {

    /**
     * result : {"rxxp":{"name":"热销新品","id":1002,"commodityList":[{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39,"commodityId":5,"saleNum":156,"commodityName":"双头两用修容笔"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/1/1.jpg","price":158,"commodityId":25,"saleNum":0,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78,"commodityId":19,"saleNum":0,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋"}]},"pzsh":{"name":"品质生活","id":1004,"commodityList":[{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"commodityId":6,"saleNum":0,"commodityName":"轻柔系自然裸妆假睫毛"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg","price":3499,"commodityId":3,"saleNum":2000,"commodityName":"Lara style女神的魔盒全套彩妆"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/1.jpg","price":44,"commodityId":13,"saleNum":0,"commodityName":"贝览得美妆蛋"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9,"commodityId":11,"saleNum":0,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵"}]},"mlss":{"name":"魔力时尚","id":1003,"commodityList":[{"masterPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg","price":88,"commodityId":32,"saleNum":0,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/2/1.jpg","price":19,"commodityId":4,"saleNum":845,"commodityName":"佩佩防晕染眼线液笔"}]}}
     * message : 查询成功
     * status : 0000
     */
    public ResultEntity result;
    public String message;
    public String status;

    @Override
    public String toString() {
        return "HomeBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
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
         * rxxp : {"name":"热销新品","id":1002,"commodityList":[{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39,"commodityId":5,"saleNum":156,"commodityName":"双头两用修容笔"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/1/1.jpg","price":158,"commodityId":25,"saleNum":0,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78,"commodityId":19,"saleNum":0,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋"}]}
         * pzsh : {"name":"品质生活","id":1004,"commodityList":[{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"commodityId":6,"saleNum":0,"commodityName":"轻柔系自然裸妆假睫毛"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg","price":3499,"commodityId":3,"saleNum":2000,"commodityName":"Lara style女神的魔盒全套彩妆"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/1.jpg","price":44,"commodityId":13,"saleNum":0,"commodityName":"贝览得美妆蛋"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9,"commodityId":11,"saleNum":0,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵"}]}
         * mlss : {"name":"魔力时尚","id":1003,"commodityList":[{"masterPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg","price":88,"commodityId":32,"saleNum":0,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/2/1.jpg","price":19,"commodityId":4,"saleNum":845,"commodityName":"佩佩防晕染眼线液笔"}]}
         */
        public RxxpEntity rxxp;
        public PzshEntity pzsh;
        public MlssEntity mlss;

        public RxxpEntity getRxxp() {
            return rxxp;
        }

        public void setRxxp(RxxpEntity rxxp) {
            this.rxxp = rxxp;
        }

        public PzshEntity getPzsh() {
            return pzsh;
        }

        public void setPzsh(PzshEntity pzsh) {
            this.pzsh = pzsh;
        }

        public MlssEntity getMlss() {
            return mlss;
        }

        public void setMlss(MlssEntity mlss) {
            this.mlss = mlss;
        }

        public class RxxpEntity {
            /**
             * name : 热销新品
             * id : 1002
             * commodityList : [{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39,"commodityId":5,"saleNum":156,"commodityName":"双头两用修容笔"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/1/1.jpg","price":158,"commodityId":25,"saleNum":0,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78,"commodityId":19,"saleNum":0,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋"}]
             */
            public String name;
            public int id;
            public List<CommodityListEntity> commodityList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<CommodityListEntity> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListEntity> commodityList) {
                this.commodityList = commodityList;
            }

            public class CommodityListEntity {
                /**
                 * masterPic : http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg
                 * price : 39
                 * commodityId : 5
                 * saleNum : 156
                 * commodityName : 双头两用修容笔
                 */
                public String masterPic;
                public int price;
                public int commodityId;
                public int saleNum;
                public String commodityName;

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

        public class PzshEntity {
            /**
             * name : 品质生活
             * id : 1004
             * commodityList : [{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"commodityId":6,"saleNum":0,"commodityName":"轻柔系自然裸妆假睫毛"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/1/1.jpg","price":3499,"commodityId":3,"saleNum":2000,"commodityName":"Lara style女神的魔盒全套彩妆"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/1.jpg","price":44,"commodityId":13,"saleNum":0,"commodityName":"贝览得美妆蛋"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9,"commodityId":11,"saleNum":0,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵"}]
             */
            public String name;
            public int id;
            public List<CommodityListEntity> commodityList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<CommodityListEntity> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListEntity> commodityList) {
                this.commodityList = commodityList;
            }

            public class CommodityListEntity {
                /**
                 * masterPic : http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg
                 * price : 39
                 * commodityId : 6
                 * saleNum : 0
                 * commodityName : 轻柔系自然裸妆假睫毛
                 */
                public String masterPic;
                public int price;
                public int commodityId;
                public int saleNum;
                public String commodityName;

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

        public class MlssEntity {
            /**
             * name : 魔力时尚
             * id : 1003
             * commodityList : [{"masterPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg","price":88,"commodityId":32,"saleNum":0,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋"},{"masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/2/1.jpg","price":19,"commodityId":4,"saleNum":845,"commodityName":"佩佩防晕染眼线液笔"}]
             */
            public String name;
            public int id;
            public List<CommodityListEntity> commodityList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<CommodityListEntity> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListEntity> commodityList) {
                this.commodityList = commodityList;
            }

            public class CommodityListEntity {
                /**
                 * masterPic : http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg
                 * price : 88
                 * commodityId : 32
                 * saleNum : 0
                 * commodityName : 唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋
                 */
                public String masterPic;
                public int price;
                public int commodityId;
                public int saleNum;
                public String commodityName;

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
    }
}
