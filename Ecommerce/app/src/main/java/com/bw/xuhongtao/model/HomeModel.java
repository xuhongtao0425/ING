package com.bw.xuhongtao.model;

import android.util.Log;

import com.bw.xuhongtao.Apk.ApkServier;
import com.bw.xuhongtao.api.AipUrl;
import com.bw.xuhongtao.bean.FirstCategory.FirstCategory;
import com.bw.xuhongtao.bean.FirstCategory.UserWallet;
import com.bw.xuhongtao.bean.home.Bean;
import com.bw.xuhongtao.bean.home.HomeBean;
import com.bw.xuhongtao.bean.search.SearchBean;
import com.bw.xuhongtao.bean.slideshow.Slideshow;
import com.bw.xuhongtao.utils.RetrofitUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author xuhongtao
 * @fileName HomeModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/17/017 11:59
 */
public class HomeModel {
    //定义轮播图接口
    public interface OnSlideshowListener {
        void slideshowData(Slideshow slideshow);
    }

    private OnSlideshowListener onSlideshowListener;

    public void setOnSlideshowListener(OnSlideshowListener onSlideshowListener) {
        this.onSlideshowListener = onSlideshowListener;
    }

    //定义展示图接口
    public interface OnHomeBeanListener {
        void homeBeanData(Bean bean);
    }

    private OnHomeBeanListener onHomeBeanListener;

    public void setOnHomeBeanListener(OnHomeBeanListener onHomeBeanListener) {
        this.onHomeBeanListener = onHomeBeanListener;
    }

    //定义搜索框接口
    public interface OnSearchListener {
        void searchData(List<SearchBean.ResultEntity> result);
    }

    private OnSearchListener onsearchListener;

    public void setOnSearchListener(OnSearchListener onsearchListener) {
        this.onsearchListener = onsearchListener;
    }

    //定义一级分类接口
    public interface OnFirstListener {
        void FirstData(List<FirstCategory.ResultEntity> result);
    }

    private OnFirstListener onfirstListener;

    public void setOnFirstListener(OnFirstListener OnfirstListener) {
        this.onfirstListener = OnfirstListener;
    }

    //定义二级分类接口
    public interface OnuserWalletListener {
        void userWalletData(List<UserWallet.ResultEntity> result);
    }

    private OnuserWalletListener onuserwalletListener;

    public void setOnuserWalletListener(OnuserWalletListener onuserwalletListener) {
        this.onuserwalletListener = onuserwalletListener;
    }

    //获取订阅管理器
    CompositeDisposable disposable = new CompositeDisposable();

    //轮播图
    public void getViewPagerModel() {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<Slideshow> slideshow = apiServer.getSlideshow();//获取被观察者
        DisposableSubscriber<Slideshow> subscriber = slideshow.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接收事件
                .subscribeWith(new DisposableSubscriber<Slideshow>() {
                    @Override
                    public void onNext(Slideshow slideshow) {
//                Log.i("slideshow",slideshow.toString());
                        if (onSlideshowListener != null) {
                            onSlideshowListener.slideshowData(slideshow);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
//                Log.i("slideshow",t.toString());
                    }

                    @Override
                    public void onComplete() {//事件完成

                    }
                });
        //把订阅者添加到管理器中
        disposable.add(subscriber);
    }

    //一级分类
    public void getTwoList() {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<FirstCategory> firstCategory = apiServer.getFirstCategory();
        //获取到订阅者
        DisposableSubscriber<FirstCategory> disposableSubscriber = firstCategory.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接受事件
                .subscribeWith(new DisposableSubscriber<FirstCategory>() {
                    @Override
                    public void onNext(FirstCategory firstCategory) {
//                Log.i("FirstCategory",firstCategory.toString());
                        List<FirstCategory.ResultEntity> result = firstCategory.getResult();
                        if (onfirstListener != null) {
                            onfirstListener.FirstData(result);
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

    //二级分类
    public void getUserWallet(String id) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        //获取订阅者
        Flowable<UserWallet> userWallet = apiServer.getUserWallet(id);
        userWallet.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<UserWallet>() {
                    @Override
                    public void onNext(UserWallet userWallet) {
                        List<UserWallet.ResultEntity> result = userWallet.getResult();
                        if (onuserwalletListener != null) {
                            onuserwalletListener.userWalletData(result);
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

    //二级列表的查询商品信息
    public void getData(String id) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<SearchBean> searchBean = apiServer.getData(id,1,30);
        DisposableSubscriber<SearchBean> subscribeWith = searchBean.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接受事件
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.ResultEntity> result = searchBean.getResult();
//                            Log.i("searchBean",searchBean.toString());
                        if (onsearchListener != null) {
                            onsearchListener.searchData(result);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //把订阅者添加到管理器中
        disposable.add(subscribeWith);
    }

    //展示
    public void getHome() {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<HomeBean> homeBean = apiServer.getHomeBean();
        DisposableSubscriber<HomeBean> disposableSubscriber = homeBean.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接受事件
                .subscribeWith(new DisposableSubscriber<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
//                Log.i("homeBean",homeBean.toString());
                        HomeBean.ResultEntity result = homeBean.getResult();
                        HomeBean.ResultEntity.RxxpEntity rxxp = result.getRxxp();
                        HomeBean.ResultEntity.MlssEntity mlss = result.getMlss();
                        HomeBean.ResultEntity.PzshEntity pzsh = result.getPzsh();
                        Bean bean = new Bean(rxxp, mlss, pzsh);
                        if (onHomeBeanListener != null) {
                            onHomeBeanListener.homeBeanData(bean);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
//                Log.i("homeBean",t.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //把订阅者添加到管理器中
        disposable.add(disposableSubscriber);
    }

    //搜索框
    public void getSearch(String goodName, int page) {
        ApkServier apiServer = RetrofitUtil.getRetrofitUtil().getApiServer(AipUrl.Url, ApkServier.class);
        Flowable<SearchBean> searchBean = apiServer.getSearchBean(goodName, page, 30);
        DisposableSubscriber<SearchBean> subscribeWith = searchBean.subscribeOn(Schedulers.io())//指定发送事件
                .observeOn(AndroidSchedulers.mainThread())//接受事件
                .subscribeWith(new DisposableSubscriber<SearchBean>() {
                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.ResultEntity> result = searchBean.getResult();
//                            Log.i("searchBean",searchBean.toString());
                        if (onsearchListener != null) {
                            onsearchListener.searchData(result);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //把订阅者添加到管理器中
        disposable.add(subscribeWith);

    }

    public void removeSubscriber() {
        //判断是否解除订阅
        boolean disposed = disposable.isDisposed();
        if (!disposed) {
            //消除订阅
            disposable.clear();
            //解绑订阅
            disposable.dispose();
            Log.i("xxx", "解绑了");
        }
    }
}
