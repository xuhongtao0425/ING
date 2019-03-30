package com.bw.xuhongtao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.adapter.AddressAdapter;
import com.bw.xuhongtao.base.ActivityBase;
import com.bw.xuhongtao.base.BaseActivity;
import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.queryshippingaddress.QueryAddress;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.persenter.ShippingAddressPersenter;
import com.bw.xuhongtao.persenter.UpdaterAddressPersenter;
import com.bw.xuhongtao.view.ShippingAddressView;
import com.bw.xuhongtao.view.UpdaterAddressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址
 */

public class ShippingAddressActivity extends ActivityBase<ShippingAddressPersenter> implements ShippingAddressView ,UpdaterAddressView {


    @BindView(R.id.rlv_address)
    RecyclerView rlvAddress;
    @BindView(R.id.add_address)
    Button addAddress;
    private int userId;
    private String sessionId;

    @Override
    protected int layoutResID() {
        return R.layout.activity_shipping_address;
    }

    @Override
    protected ShippingAddressPersenter getPersenter() {
        persenter = new ShippingAddressPersenter(this);
        return persenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        boolean aBoolean = login.getBoolean("登录", false);
        if (aBoolean) {
            persenter.queryShippingAddress(userId, sessionId);
        } else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick(R.id.add_address)
    public void onViewClicked() {
        //添加地址
        startActivity(new Intent(ShippingAddressActivity.this, WriteAddressActivity.class));
    }

    //数据
    @Override
    public void getShippingAddressModel(List<QueryAddress.ResultEntity> result) {
        //布局管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rlvAddress.setLayoutManager(layoutManager);
        //集合是否有数据
        if(result.size()==0){
            Toast.makeText(this, "亲,快去添加收货地址吧", Toast.LENGTH_SHORT).show();
        }else{
            //适配器
            AddressAdapter adapter = new AddressAdapter(result, this);
            rlvAddress.setAdapter(adapter);
            adapter.setOnUpdater(new AddressAdapter.OnUpdater() {
                @Override
                public void getUpdater(int id) {
                    //修改默认地址
                    UpdaterAddressPersenter updaterAddressPersenter=new UpdaterAddressPersenter(ShippingAddressActivity.this);
                    updaterAddressPersenter.upadterAddress(id,userId,sessionId);
                }
            });
        }

    }
//  设置默认收货地址
    @Override
    public void getUpdaterAddressModel(Addshopping addshopping) {
        if(addshopping.getStatus().equals("0000")){
            persenter.queryShippingAddress(userId, sessionId);
        }else {
            Toast.makeText(this, addshopping.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
