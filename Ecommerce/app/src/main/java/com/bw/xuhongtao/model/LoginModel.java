package com.bw.xuhongtao.model;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.loginbean.LoginBean;
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
 * @fileName LoginModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/20/020 14:57
 */
public class LoginModel {


    //定义接口
    public interface OnLoginListener{
        void loginData(LoginBean loginBean);
    }
    private OnLoginListener onLoginListener;

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }
    CompositeDisposable disposable=new CompositeDisposable();
    Map<String ,String > body=new HashMap<>();
    public void loginModel(String phone, String pwd) {
        body.put("phone",phone);
        body.put("pwd",pwd);
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<LoginBean> login = apiServer.getLogin(body);
        DisposableSubscriber<LoginBean> disposableSubscriber = login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        if (onLoginListener != null) {
                            onLoginListener.loginData(loginBean);
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
    //取消绑定
    public void removeSubscriber() {
        //判断是否接触订阅
        boolean disposed = disposable.isDisposed();
        if(!disposed){
            //消除订阅
            disposable.clear();
            //解除订阅
            disposable.dispose();
        }
    }
}
