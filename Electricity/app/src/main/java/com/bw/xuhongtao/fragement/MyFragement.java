package com.bw.xuhongtao.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.activity.LoginActivity;
import com.bw.xuhongtao.base.FragementBase;

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
public class MyFragement extends FragementBase {
    @BindView(R.id.head_my)
    ImageView headMy;//头像
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


    @Override
    protected int layoutResID() {
        return R.layout.layout_my;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {


    }
    //点击事件
    @OnClick({R.id.head_my, R.id.username_my, R.id.information, R.id.circle, R.id.foot, R.id.wallet, R.id.shippingaddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_my:

                break;
            case R.id.username_my:
                startActivity(new Intent(getActivity(),LoginActivity.class));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();//解绑
    }
}
