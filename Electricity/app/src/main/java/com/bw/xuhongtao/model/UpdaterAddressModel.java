package com.bw.xuhongtao.model;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.utils.RetrofitUtil;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author xuhongtao
 * @fileName UpdaterAddressModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/29/029 19:43
 */
public class UpdaterAddressModel {
    public interface OnUpdaterAddressModel{
        void getUpdaterAddressModel(Addshopping addshopping);
    }
    private OnUpdaterAddressModel onUpdaterAddressModel;

    public void setOnUpdaterAddressModel(OnUpdaterAddressModel onUpdaterAddressModel) {
        this.onUpdaterAddressModel = onUpdaterAddressModel;
    }

    public void upadterAddress(int id, int userId, String sessionId) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<Addshopping> updaterAddress = apiServer.getUpdaterAddress(userId, sessionId, id);
        updaterAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<Addshopping>() {
                    @Override
                    public void onNext(Addshopping addshopping) {
                        if(onUpdaterAddressModel!=null){
                            onUpdaterAddressModel.getUpdaterAddressModel(addshopping);
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
