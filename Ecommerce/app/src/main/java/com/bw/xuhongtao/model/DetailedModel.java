package com.bw.xuhongtao.model;

import android.util.Log;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.detailed.Detailed;
import com.bw.xuhongtao.bean.detailed.Result;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.utils.RetrofitUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author xuhongtao
 * @fileName DetailedModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/21/021 19:20
 */
public class DetailedModel {

    //查询接口回调
    public interface OnQueryShopping {
        void getData(List<QueryShopping.Result> result);
    }

    private OnQueryShopping onQueryShopping;

    public void setOnQueryShopping(OnQueryShopping onQueryShopping) {
        this.onQueryShopping = onQueryShopping;
    } //同步接口回调
    public interface OnAddshopping {
        void getData(Addshopping addshopping);
    }

    private OnAddshopping onAddshopping;

    public void setOnAddshopping(OnAddshopping onAddshopping) {
        this.onAddshopping = onAddshopping;
    }



    //接口回调
    public interface OnDetailedModel {
        void getData(Result result);
    }

    private OnDetailedModel onDetailedModel;

    public void setOnDetailedModel(OnDetailedModel onDetailedModel) {
        this.onDetailedModel = onDetailedModel;
    }
    //创建订阅管理器
    CompositeDisposable disposable=new CompositeDisposable();
    public void detailedModel(int id, int userId, String sessionId) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url,userId+"",sessionId,ApkServier.class);
        Flowable<Detailed> deprecated = apiServer.getDeprecated(id);
        DisposableSubscriber<Detailed> disposableSubscriber = deprecated.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接受事件
                .subscribeWith(new DisposableSubscriber<Detailed>() {
                    @Override
                    public void onNext(Detailed detailed) {
                        Result result = detailed.getResult();
                      //  Log.i("xxxx",result.getCategoryName());
                        if (onDetailedModel != null) {
                            onDetailedModel.getData(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //添加
        disposable.add(disposableSubscriber);
    }
    //查询购物车
    public void queryShoping(int userId, String sessionId) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, userId + "", sessionId, ApkServier.class);
        Flowable<QueryShopping> queryShopping = apiServer.getQueryShopping();
        DisposableSubscriber<QueryShopping> disposableSubscriber = queryShopping.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<QueryShopping>() {
                    @Override
                    public void onNext(QueryShopping queryShopping) {
                        Log.i("mmmmmmmm", queryShopping.toString());
                        List<QueryShopping.Result> result = queryShopping.getResult();
                        if(onQueryShopping!=null){
                        onQueryShopping.getData(result);
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
        disposable.add(disposableSubscriber);

    }
    //加入购物车
    public void addShoping(int userId, String sessionId,String json) {
        Log.i("jsonss",json);
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url,userId+"",sessionId, ApkServier.class);
        final Flowable<Addshopping> addShopping = apiServer.getAddShopping(RequestBody.create(MediaType.parse("text/plain"),json));
        addShopping.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Addshopping>() {
                    @Override
                    public void onNext(Addshopping addshopping) {
//                        Log.i("addshopping",addshopping.toString());
                        if(onAddshopping!=null){
                            onAddshopping.getData(addshopping);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i("shopping",t.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void removeSubscriber() {
        //判断是否解除订阅
        boolean disposed = disposable.isDisposed();
        if(!disposed){
            //消除订阅
            disposable.clear();
            //解绑订阅
            disposable.dispose();
            Log.i("xxx","解绑了");
        }
    }
}
