package com.bw.xuhongtao.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.base.BaseActivity;
import com.bw.xuhongtao.fragement.CircleFragment;
import com.bw.xuhongtao.fragement.HomeFragement;
import com.bw.xuhongtao.fragement.MyFragement;
import com.bw.xuhongtao.fragement.OrderFragement;
import com.bw.xuhongtao.fragement.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.home_main)
    RadioButton homeMain;
    @BindView(R.id.circle_main)
    RadioButton circleMain;
    @BindView(R.id.shoppingcar_main)
    RadioButton shoppingcarMain;
    @BindView(R.id.order_main)
    RadioButton orderMain;
    @BindView(R.id.my_main)
    RadioButton myMain;

    private FragmentManager manager;
    private HomeFragement homeFragement;
    private CircleFragment circleFragment;
    private ShoppingFragment shoppingFragment;
    private OrderFragement orderFragement;
    private MyFragement myFragement;


    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        //设置首页显示选中
        rgMain.check(R.id.home_main);

    }

    @Override
    protected void initData() {
        //管理者
        manager = getSupportFragmentManager();
        //事务
        homeFragement = new HomeFragement();
        circleFragment = new CircleFragment();
        shoppingFragment = new ShoppingFragment();
        orderFragement = new OrderFragement();
        myFragement = new MyFragement();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frame_main, homeFragement);
        transaction.add(R.id.frame_main, circleFragment);
        transaction.add(R.id.frame_main, shoppingFragment);
        transaction.add(R.id.frame_main, orderFragement);
        transaction.add(R.id.frame_main, myFragement);
        //显示页面
        transaction.show(homeFragement).hide(circleFragment).hide(shoppingFragment)
                .hide(orderFragement).hide(myFragement).commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @OnClick({R.id.home_main, R.id.circle_main, R.id.shoppingcar_main, R.id.order_main, R.id.my_main})
    public void onViewClicked(View view) {
        FragmentTransaction transaction = manager.beginTransaction();

        switch (view.getId()) {
            case R.id.home_main:
                transaction.show(homeFragement).hide(circleFragment).hide(shoppingFragment)
                        .hide(orderFragement).hide(myFragement).commit();
                break;
            case R.id.circle_main:
                transaction.show(circleFragment).hide(homeFragement).hide(shoppingFragment)
                        .hide(orderFragement).hide(myFragement).commit();
                break;
            case R.id.shoppingcar_main:
                transaction.show(shoppingFragment).hide(homeFragement).hide(circleFragment)
                        .hide(orderFragement).hide(myFragement).commit();
                break;
            case R.id.order_main:
                transaction.show(orderFragement).hide(homeFragement).hide(circleFragment)
                        .hide(shoppingFragment).hide(myFragement).commit();
                break;
            case R.id.my_main:
                transaction.show(myFragement).hide(homeFragement).hide(circleFragment)
                        .hide(shoppingFragment).hide(orderFragement).commit();
                break;
        }
    }



}
