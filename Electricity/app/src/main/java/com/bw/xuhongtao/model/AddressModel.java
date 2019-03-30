package com.bw.xuhongtao.model;

import android.util.Log;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.utils.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author xuhongtao
 * @fileName AddressModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/29/029 14:36
 */
public class AddressModel {
    public interface OnAddressModel{
        void AddressModelData(Addshopping addshopping);
    }
    private OnAddressModel onAddressModel;

    public void setOnAddressModel(OnAddressModel onAddressModel) {
        this.onAddressModel = onAddressModel;
    }

    Map<String, String> map = new HashMap<>();
    //实例化订阅管理器
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    public void addressData(int userId, String sessionId, String name, String phone, String address, String zipCode) {
        map.put("realName", name);
        map.put("phone", phone);
        map.put("address", address);
        map.put("zipCode", zipCode);
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<Addshopping> address1 = apiServer.getAddress(userId, sessionId, map);
        DisposableSubscriber<Addshopping> disposableSubscriber = address1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Addshopping>() {
                    @Override
                    public void onNext(Addshopping addshopping) {
                        if(onAddressModel!=null){
                            onAddressModel.AddressModelData(addshopping);
                        }
//                        Log.i("zzzz",addshopping.toString());

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //添加订阅者;
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
