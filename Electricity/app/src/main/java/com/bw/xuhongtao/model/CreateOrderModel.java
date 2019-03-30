package com.bw.xuhongtao.model;

import android.util.Log;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.utils.RetrofitUtil;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author xuhongtao
 * @fileName CreateOrderModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/29/029 22:11
 */
public class CreateOrderModel {
    public interface OnCreateOrder{
        void getCreateOrder(Addshopping addshopping);
    }
    private OnCreateOrder onCreateOrder;

    public void setOnCreateOrder(OnCreateOrder onCreateOrder) {
        this.onCreateOrder = onCreateOrder;
    }

    public void createOrder(String sessionId, int userId, double sum, int id, String json) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<Addshopping> createOrder = apiServer.getCreateOrder(userId, sessionId, RequestBody.create(MediaType.parse("text/plain"), json), sum, id);
        createOrder.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Addshopping>() {
                    @Override
                    public void onNext(Addshopping addshopping) {
//                        Log.i("xxxxx",addshopping.toString());
                                if(onCreateOrder!=null){
                                    onCreateOrder.getCreateOrder(addshopping);
                                }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
