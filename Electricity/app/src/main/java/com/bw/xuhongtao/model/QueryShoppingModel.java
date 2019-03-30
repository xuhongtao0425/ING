package com.bw.xuhongtao.model;

import android.util.Log;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.utils.RetrofitUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author xuhongtao
 * @fileName QueryShoppingModel
 * @package com.bw.xuhongtao.model   查询购物车
 * @date 2019/3/27/027 21:01
 */
public class QueryShoppingModel {
    //查询接口回调
    public interface OnQueryShopping {
        void getData(List<QueryShopping.Result> result);
    }

    private OnQueryShopping onQueryShopping;

    public void setOnQueryShopping(OnQueryShopping onQueryShopping) {
        this.onQueryShopping = onQueryShopping;
    }

    public void queryShopping(int userId, String sessionId) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<QueryShopping> queryShopping = apiServer.getQueryShopping(userId, sessionId);
        DisposableSubscriber<QueryShopping> disposableSubscriber = queryShopping.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<QueryShopping>() {
                    @Override
                    public void onNext(QueryShopping queryShopping) {
                        Log.i("queryShopping", queryShopping.toString());
                        String status = queryShopping.getStatus();
                        if (status.equals("0000")) {
                            List<QueryShopping.Result> result = queryShopping.getResult();
                            if (onQueryShopping != null) {
                                onQueryShopping.getData(result);
                            }
                        }


                    }

                    @Override
                    public void onError(Throwable t) {
//                        Log.i("queryShopping", t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
