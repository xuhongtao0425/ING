package com.bw.xuhongtao.model;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.regbean.RegBean;
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
 * @fileName RegModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/20/020 14:04
 */
public class RegModel {


    //定义接口
    public interface OnRegListener{
        void regData(RegBean regBean);
    }
    private OnRegListener onRegListener;

    public void setOnRegListener(OnRegListener onRegListener) {
        this.onRegListener = onRegListener;
    }
    //实例化订阅者管理器
    CompositeDisposable disposable=new CompositeDisposable();
    Map<String ,String > body=new HashMap<>();
    public void regModel(String phone, String pwd) {
        body.put("phone",phone);
        body.put("pwd",pwd);
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<RegBean> reg = apiServer.getReg(body);
        //得到订阅者
        DisposableSubscriber<RegBean> subscriber = reg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RegBean>() {
                    @Override
                    public void onNext(RegBean regBean) {
//                        Log.i("regBean",regBean.toString());
                        if (onRegListener != null) {
                            onRegListener.regData(regBean);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //添加到订阅者管理器中
        disposable.add(subscriber);
    }
    //取消订阅
    public void removeSubscriber() {
            //判断是否订阅
        boolean disposed = disposable.isDisposed();
        if(!disposed){
            //消除订阅
            disposable.clear();
            //解除订阅
            disposable.dispose();
        }
    }

}
