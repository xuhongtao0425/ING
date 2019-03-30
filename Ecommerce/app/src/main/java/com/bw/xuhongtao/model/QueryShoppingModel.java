package com.bw.xuhongtao.model;

import android.util.Log;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.utils.RetrofitUtil;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author xuhongtao
 * @fileName QueryShoppingModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/26/026 13:51
 */
public class QueryShoppingModel {
    public interface OnQueryShoppingModel {
        void getQueryShoppingData(QueryShopping queryShopping);
    }

    private OnQueryShoppingModel onQueryShoppingModel;

    public void setOnQueryShoppingModel(OnQueryShoppingModel onQueryShoppingModel) {
        this.onQueryShoppingModel = onQueryShoppingModel;
    }

    //获取订阅这管理器
    CompositeDisposable disposable = new CompositeDisposable();

    public void queryShopping(int userId, String sessionId) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, userId + "", sessionId, ApkServier.class);
        Flowable<QueryShopping> queryShopping = apiServer.getQueryShopping();
        DisposableSubscriber<QueryShopping> disposableSubscriber = queryShopping.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接收事件
                .subscribeWith(new DisposableSubscriber<QueryShopping>() {
                    @Override
                    public void onNext(QueryShopping queryShopping) {
//                        Log.i("DisposableSubscriber", queryShopping.toString());
                        if (onQueryShoppingModel != null) {
                            onQueryShoppingModel.getQueryShoppingData(queryShopping);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        disposable.add(disposableSubscriber);

    }
}
