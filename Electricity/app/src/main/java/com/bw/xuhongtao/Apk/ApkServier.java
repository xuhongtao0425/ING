package com.bw.xuhongtao.Apk;

import com.bw.xuhongtao.bean.FirstCategory.FirstCategory;
import com.bw.xuhongtao.bean.FirstCategory.UserWallet;
import com.bw.xuhongtao.bean.Quty;
import com.bw.xuhongtao.bean.detailed.Detailed;
import com.bw.xuhongtao.bean.queryshippingaddress.QueryAddress;
import com.bw.xuhongtao.bean.search.SearchBean;
import com.bw.xuhongtao.bean.home.HomeBean;
import com.bw.xuhongtao.bean.loginbean.LoginBean;
import com.bw.xuhongtao.bean.regbean.RegBean;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.bean.slideshow.Slideshow;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author xuhongtao
 * @fileName ApkServier
 * @package com.bw.xuhongtao.Apk
 * @date 2019/3/20/020 14:17
 */
public interface ApkServier {
    //    http://172.17.8.100/small/user/v1/register
    //注册
    @POST("user/v1/register")
    Flowable<RegBean> getReg(@QueryMap Map<String, String> reg);

    //    http://172.17.8.100/small/user/v1/login
    //登录
    @POST("user/v1/login")
    Flowable<LoginBean> getLogin(@QueryMap Map<String, String> login);

    //    http://172.17.8.100/small/commodity/v1/bannerShow
    //轮播图
    @GET("commodity/v1/bannerShow")
    Flowable<Slideshow> getSlideshow();

    //http://172.17.8.100/small/commodity/v1/commodityList
    //展示
    @GET("commodity/v1/commodityList")
    Flowable<HomeBean> getHomeBean();

    //搜索
//    http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=鞋&page=1&count=10
    @GET("commodity/v1/findCommodityByKeyword")
    Flowable<SearchBean> getSearchBean(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);

    //    http://172.17.8.100/small/commodity/v1/findCommodityDetailsById
    //详情
    @GET("commodity/v1/findCommodityDetailsById")
    Flowable<Detailed> getDeprecated(@Query("commodityId") int commodityId);

    //http://172.17.8.100/small/commodity/v1/findFirstCategory
    //一级商品类目
    @GET("commodity/v1/findFirstCategory")
    Flowable<FirstCategory> getFirstCategory();

    //http://172.17.8.100/small/commodity/v1/findSecondCategory?firstCategoryId=1001002
    //二级商品类目
    @GET("commodity/v1/findSecondCategory")
    Flowable<UserWallet> getUserWallet(@Query("firstCategoryId") String firstCategoryId);

    //    http://172.17.8.100/small/commodity/v1/findCommodityByCategory
    //二级列表的查询商品信息
    @GET("commodity/v1/findCommodityByCategory")
    Flowable<SearchBean> getData(@Query("categoryId") String categoryId, @Query("page") int page, @Query("count") int count);

    //http://172.17.8.100/small/order/verify/v1/findShoppingCart
    //查询购物车
    @GET("order/verify/v1/findShoppingCart")
    Flowable<QueryShopping> getQueryShopping(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //    http://172.17.8.100/small/order/verify/v1/syncShoppingCart
    //同步购物车
    @Multipart
    @PUT("order/verify/v1/syncShoppingCart")
    Flowable<Addshopping> getAddShopping(@Part("data") RequestBody json);

    //新增地址
//    http://172.17.8.100/small/user/verify/v1/addReceiveAddress
    @POST("user/verify/v1/addReceiveAddress")
    Flowable<Addshopping> getAddress(@Header("userId") int userId, @Header("sessionId") String sessionId, @QueryMap Map<String, String> map);

    //查询收货地址
//    http://172.17.8.100/small/user/verify/v1/receiveAddressList
    @GET("user/verify/v1/receiveAddressList")
    Flowable<QueryAddress> queryAddress(@Header("userId") int userId, @Header("sessionId") String sessionId);

    //设置默认收货地址
//    http://172.17.8.100/small/user/verify/v1/setDefaultReceiveAddress
    @POST("user/verify/v1/setDefaultReceiveAddress")
    Flowable<Addshopping> getUpdaterAddress(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("id") int id);
    //创建订单
//    http://172.17.8.100/small/order/verify/v1/createOrder
    @Multipart
    @POST("order/verify/v1/createOrder")
    Flowable<Addshopping> getCreateOrder(@Header("userId") int userid,@Header("sessionId") String sessionId,@Part("orderInfo") RequestBody data,@Query("totalPrice") double totalPrice,@Query("addressId") int addressId);


}
