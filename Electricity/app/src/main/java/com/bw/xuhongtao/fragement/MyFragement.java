package com.bw.xuhongtao.fragement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.activity.LoginActivity;
import com.bw.xuhongtao.base.BaseFragement;
import com.bw.xuhongtao.bean.loginbean.LoginBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xuhongtao
 * @fileName MyFragement
 * @package com.bw.xuhongtao.fragement
 * @date 2019/3/17/017 10:42
 */
public class MyFragement extends BaseFragement {
    @BindView(R.id.head_my)
    SimpleDraweeView headMy;//头像
    @BindView(R.id.username_my)
    TextView usernameMy;//用户名
    @BindView(R.id.information)
    TextView information;//个人资料
    @BindView(R.id.circle)
    TextView circle;//我的圈子
    @BindView(R.id.foot)
    TextView foot;//我的足迹
    @BindView(R.id.wallet)
    TextView wallet;//我的钱包
    @BindView(R.id.shippingaddress)
    TextView shippingaddress;//收货地址
    Unbinder unbinder;//绑定
    private String nickName="登录/注册";
    private String headPic;


    @Override
    protected int layoutResID() {
        EventBus.getDefault().register(this);
        return R.layout.layout_my;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {
        usernameMy.setText(nickName);
        headMy.setImageURI(headPic);
    }

    //点击事件
    @OnClick({R.id.head_my, R.id.username_my, R.id.information, R.id.circle, R.id.foot, R.id.wallet, R.id.shippingaddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_my:
                break;
            case R.id.username_my:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.information:
                break;
            case R.id.circle:
                break;
            case R.id.foot:
                break;
            case R.id.wallet:
                break;
            case R.id.shippingaddress:
                break;
        }
    }



    //接收登录页面传来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLogin(LoginBean loginBean) {
//        Log.i("xxxxs", loginBean.toString());
        LoginBean.ResultEntity result = loginBean.getResult();
        nickName = result.getNickName();
        headPic = result.getHeadPic();
//        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
//        String nickName = login.getString("nickName", "登录/注册");
//        String headPic = login.getString("headPic", "");
        usernameMy.setText(nickName);
        headMy.setImageURI(headPic);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();//解绑
        EventBus.getDefault().unregister(this);
    }
}
