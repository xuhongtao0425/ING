package com.bw.xuhongtao.fragement;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.xuhongtao.R;
import com.bw.xuhongtao.adapter.FirstAdapter;
import com.bw.xuhongtao.adapter.HomeAdapter;
import com.bw.xuhongtao.adapter.MyVpAdapter;
import com.bw.xuhongtao.adapter.SearchAdapter;
import com.bw.xuhongtao.adapter.UserWalletAdapter;
import com.bw.xuhongtao.base.FragementBase;
import com.bw.xuhongtao.bean.FirstCategory.FirstCategory;
import com.bw.xuhongtao.bean.FirstCategory.UserWallet;
import com.bw.xuhongtao.bean.home.Bean;
import com.bw.xuhongtao.bean.search.SearchBean;
import com.bw.xuhongtao.bean.slideshow.Slideshow;
import com.bw.xuhongtao.persenter.HomePersenter;
import com.bw.xuhongtao.utils.NetWork;
import com.bw.xuhongtao.utils.ScaleAlphaPageTransformer;
import com.bw.xuhongtao.view.DataView;
import com.bw.xuhongtao.view.FirstView;
import com.bw.xuhongtao.view.HomeBeanView;
import com.bw.xuhongtao.view.SearchView;
import com.bw.xuhongtao.view.SlideshowView;
import com.bw.xuhongtao.view.UserWalletView;
import com.bw.xuhongtao.widget.Search;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xuhongtao
 * @fileName HomeFragement   首页
 * @package com.bw.xuhongtao.fragement
 * @date 2019/3/14/014 14:26
 */
public class HomeFragement extends FragementBase<HomePersenter> implements DataView,UserWalletView,SlideshowView, HomeBeanView, SearchView, FirstView {
    @BindView(R.id.search_home)
    Search searchHome;//搜索框
    @BindView(R.id.vp_home)
    ViewPager vpHome;//轮播
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;//RecyclerView首页展示
    @BindView(R.id.sllv_home)
    ScrollView sllvHome;
    @BindView(R.id.li)
    LinearLayout li;
    @BindView(R.id.xrlv_home)
    XRecyclerView xrlv_home;
    @BindView(R.id.rl)
    RelativeLayout rl;
    Unbinder unbinder;
    int page = 1;
    List<SearchBean.ResultEntity> list;
    String name = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    int currentItem = vpHome.getCurrentItem() + 1;
                    vpHome.setCurrentItem(currentItem);
                    handler.sendEmptyMessageDelayed(0, 2000);
                    break;
            }
        }
    };
    private RecyclerView rlv_poptwo;

    @Override
    protected int layoutResID() {
        return R.layout.layout_home;
    }

    @Override
    protected HomePersenter getPersenter() {
        persenter = new HomePersenter(this, this, this, this,this,this);
        return persenter;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        //调用viewpager工具类
        ScaleAlphaPageTransformer mScaleAlphaPageTransformer = new ScaleAlphaPageTransformer();
        //调用viewpager工具类中方法设置是否模糊和改变大小
        mScaleAlphaPageTransformer.setType(true, true);
        vpHome.setPageTransformer(true, mScaleAlphaPageTransformer);
        vpHome.setCurrentItem(3);
        //判断网络
        final boolean b = NetWork.isNetworkConnected(getActivity());
        if (b) {
            //轮播图
            persenter.getViewPagerPersenter();
            //展示
            persenter.getHome();
        } else {
            Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
            return;
        }
        //二级列表
        searchHome.setOnTwoList(new Search.OnTwoList() {
            @Override
            public void getData() {
                persenter.getTwoList();
//
            }
        });

        //搜索框
        searchHome.setOnSouSuo(new Search.OnSouSuo() {
            @Override
            public void getData(String goodName) {
                if (TextUtils.isEmpty(goodName)) {
                    Toast.makeText(getActivity(), "亲,你要输入呦", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (b) {
                    name = goodName;
                    persenter.getSearch(goodName, page);
                } else {
                    Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        xrlv_home.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (b) {
                    page = 1;
                    persenter.getSearch(name, page);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrlv_home.refreshComplete();
                    }
                }, 2000);


            }

            @Override
            public void onLoadMore() {
                if (b) {
                    page++;
                    persenter.getSearch(name, page);
                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        xrlv_home.loadMoreComplete();
                    }
                }, 2000);
            }
        });

    }

    //轮播图
    @Override
    public void slideshowData(Slideshow slideshow) {
        List<Slideshow.ResultEntity> result = slideshow.getResult();
        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            ImageView imageView = new ImageView(getActivity());

            Glide.with(getActivity()).load(result.get(i).getImageUrl()).into(imageView);
            list.add(imageView);

        }
        vpHome.setAdapter(new MyVpAdapter(getActivity(), list));
        //轮播发送
        handler.sendEmptyMessageDelayed(0, 200);

    }

    //展示
    @Override
    public void HomeBeanData(Bean bean) {
//        Log.i("bean", bean.toString());
        //设置布局管理器
        rlvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        //适配器
        rlvHome.setAdapter(new HomeAdapter(getActivity(), bean));

    }

    //搜索框
    @Override
    public void searchData(List<SearchBean.ResultEntity> result) {
        if (result.size() == 0) {
            li.setVisibility(View.VISIBLE);
        } else {
            rl.setVisibility(View.VISIBLE);
            li.setVisibility(View.GONE);
            //判断
            if (page == 1) {
                list = new ArrayList<>();
            }
            list.addAll(result);
            //布局管理器
            xrlv_home.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            //适配器
            xrlv_home.setAdapter(new SearchAdapter(getActivity(), list));
            //显示当前页
            xrlv_home.scrollToPosition(list.size() - (result.size() - 1));

        }

    }

    //一级分类
    @Override
    public void FirstData(List<FirstCategory.ResultEntity> result) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow, null, false);
        //RecyclerView列表控件
        RecyclerView rlv_pop=view.findViewById(R.id.rlv_pop);
        rlv_poptwo = view.findViewById(R.id.rlv_poptwo);
        //布局管理器
        rlv_pop.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rlv_pop.setBackgroundColor(Color.YELLOW);
        //设置适配器
        FirstAdapter adapter = new FirstAdapter(getActivity(), result);
        rlv_pop.setAdapter(adapter);
        PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //这是背景色
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        //显示
        popupWindow.showAsDropDown(searchHome.findViewById(R.id.san));
        //默认选中
        persenter.getUserWallet("1001002");

    }
    //获取FirstAdapter传来的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
        public void getFirstId(String id){
        Log.i("firstid",id);
//        二级商品类目
        persenter.getUserWallet(id);

    }
    //        二级商品类目数据
    @Override
    public void userWalletData(List<UserWallet.ResultEntity> result) {
        //布局管理器
        rlv_poptwo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rlv_poptwo.setBackgroundColor(Color.YELLOW);
        //设置适配器
        UserWalletAdapter adapter = new UserWalletAdapter(getActivity(), result);
        rlv_poptwo.setAdapter(adapter);
        adapter.setOnUserWallet(new UserWalletAdapter.OnUserWallet() {
            @Override
            public void getUserWallet(String id) {
                persenter.getData(id);
            }
        });

    }
    //二级列表的查询商品信息
    @Override
    public void getData(List<SearchBean.ResultEntity> result) {
        Log.i("ssssss",result.toString());
        if (result.size() == 0) {
            li.setVisibility(View.VISIBLE);
        } else {
            rl.setVisibility(View.VISIBLE);
            li.setVisibility(View.GONE);
            //判断
            if (page == 1) {
                list = new ArrayList<>();
            }
            list.addAll(result);
            //布局管理器
            xrlv_home.setLayoutManager(new GridLayoutManager(getActivity(), 3));
            //适配器
            xrlv_home.setAdapter(new SearchAdapter(getActivity(), list));
            //显示当前页
            xrlv_home.scrollToPosition(list.size() - (result.size() - 1));

        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
        handler.removeCallbacksAndMessages(null);
        persenter.removeSubscriber();
    }



}
