package com.bw.xuhongtao.model;

import android.util.Log;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.queryshippingaddress.QueryAddress;
import com.bw.xuhongtao.utils.RetrofitUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author xuhongtao
 * @fileName ShippingAddressModel
 * @package com.bw.xuhongtao.model   查询收货地址
 * @date 2019/3/29/029 15:50
 */
public class ShippingAddressModel {
    public  interface OnShippingAddressModel{
        void getShippingAddressModel(List<QueryAddress.ResultEntity> result);
    }
    private OnShippingAddressModel onShippingAddressModel;

    public void setOnShippingAddressModel(OnShippingAddressModel onShippingAddressModel) {
        this.onShippingAddressModel = onShippingAddressModel;
    }

    CompositeDisposable compositeDisposable=new CompositeDisposable();
    public void queryShippingAddress(int userId, String sessionId) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<QueryAddress> queryAddressFlowable = apiServer.queryAddress(userId, sessionId);
        DisposableSubscriber<QueryAddress> disposableSubscriber = queryAddressFlowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<QueryAddress>() {
                    @Override
                    public void onNext(QueryAddress queryAddress) {
//                        Log.i("vvvvvvv",queryAddress.toString());
                        String status = queryAddress.getStatus();
                        if(status.equals("0000")){
                            List<QueryAddress.ResultEntity> result = queryAddress.getResult();
                            if(onShippingAddressModel!=null){
                                onShippingAddressModel.getShippingAddressModel(result);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }
    public void clear(){
        boolean disposed = compositeDisposable.isDisposed();
        if(!disposed){
            compositeDisposable.dispose();;
            compositeDisposable.clear();
        }
    }
}
