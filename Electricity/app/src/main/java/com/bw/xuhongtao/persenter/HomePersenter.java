package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.FirstCategory.FirstCategory;
import com.bw.xuhongtao.bean.FirstCategory.UserWallet;
import com.bw.xuhongtao.bean.search.SearchBean;
import com.bw.xuhongtao.bean.home.Bean;
import com.bw.xuhongtao.bean.slideshow.Slideshow;
import com.bw.xuhongtao.model.HomeModel;
import com.bw.xuhongtao.view.DataView;
import com.bw.xuhongtao.view.FirstView;
import com.bw.xuhongtao.view.HomeBeanView;
import com.bw.xuhongtao.view.SearchView;
import com.bw.xuhongtao.view.SlideshowView;
import com.bw.xuhongtao.view.UserWalletView;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName HomePersenter
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/17/017 11:41
 */
public class HomePersenter extends BasePersenter {

    private final HomeModel homeModel;
    private final SlideshowView sView;
    private final HomeBeanView hView;
    private final SearchView shview;
    private final FirstView fview;
    private final UserWalletView uwview;
    private final DataView dview;

    public HomePersenter(SlideshowView slideshowView, HomeBeanView homeBeanView, SearchView searchView, FirstView firstView, UserWalletView userWalletView, DataView dataView) {
        homeModel = new HomeModel();
        sView = slideshowView;
        hView = homeBeanView;
        shview = searchView;
        fview = firstView;
        uwview = userWalletView;
        dview = dataView;
    }

    //轮播图
    public void getViewPagerPersenter() {
        homeModel.getViewPagerModel();
        homeModel.setOnSlideshowListener(new HomeModel.OnSlideshowListener() {
            @Override
            public void slideshowData(Slideshow slideshow) {
                sView.slideshowData(slideshow);

            }
        });
    }

    //展示
    public void getHome() {
        homeModel.getHome();
        homeModel.setOnHomeBeanListener(new HomeModel.OnHomeBeanListener() {
            @Override
            public void homeBeanData(Bean bean) {
                hView.HomeBeanData(bean);

            }
        });
    }

    //搜索框
    public void getSearch(String goodName, int page) {
        homeModel.getSearch(goodName, page);
        homeModel.setOnSearchListener(new HomeModel.OnSearchListener() {
            @Override
            public void searchData(List<SearchBean.ResultEntity> result) {
                shview.searchData(result);

            }
        });
    }

    ///二级列表
    public void getTwoList() {
        homeModel.getTwoList();
        homeModel.setOnFirstListener(new HomeModel.OnFirstListener() {
            @Override
            public void FirstData(List<FirstCategory.ResultEntity> result) {
                fview.FirstData(result);

            }
        });
    }
//    二级商品类目
    public void getUserWallet(String id) {
        homeModel.getUserWallet( id);
        homeModel.setOnuserWalletListener(new HomeModel.OnuserWalletListener() {
            @Override
            public void userWalletData(List<UserWallet.ResultEntity> result) {
                uwview.userWalletData(result);

            }
        });
    }
    //二级列表的查询商品信息
    public void getData(String id) {
        homeModel.getData(id);
        homeModel.setOnSearchListener(new HomeModel.OnSearchListener() {
            @Override
            public void searchData(List<SearchBean.ResultEntity> result) {
                dview.getData(result);

            }
        });
    }
    //解除订阅
    public void removeSubscriber() {
        homeModel.removeSubscriber();
    }



}
