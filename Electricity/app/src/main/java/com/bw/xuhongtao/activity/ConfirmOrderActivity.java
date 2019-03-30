package com.bw.xuhongtao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.adapter.QueryAdapter;
import com.bw.xuhongtao.base.ActivityBase;
import com.bw.xuhongtao.bean.queryshippingaddress.QueryAddress;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.persenter.CreateOrderPersenter;
import com.bw.xuhongtao.persenter.ShippingAddressPersenter;
import com.bw.xuhongtao.view.CreateOrderView;
import com.bw.xuhongtao.view.QueryShoppingView;
import com.bw.xuhongtao.view.ShippingAddressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends ActivityBase<ShippingAddressPersenter> implements ShippingAddressView, CreateOrderView {


    @BindView(R.id.name_addressitem)
    TextView nameAddressitem;//用户名
    @BindView(R.id.phone_address)
    TextView phoneAddress;//手机号
    @BindView(R.id.cargo_address)
    TextView cargoAddress;//地址
    @BindView(R.id.more)
    ImageView more;//更多地址
    @BindView(R.id.rlv_confirm)
    RecyclerView rlvConfirm;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.commit)
    Button commit;
    private int id;
    List<QueryShopping.Result> list;
    private CreateOrderPersenter createOrderPersenter;
    private int userId;
    private String sessionId;

    @Override
    protected int layoutResID() {

        return R.layout.activity_confirm_order;
    }

    @Override
    protected ShippingAddressPersenter getPersenter() {
        persenter = new ShippingAddressPersenter(this);
        return persenter;
    }

    @Override
    protected void initView() {
        rlvConfirm.setLayoutManager(new LinearLayoutManager(this));
        createOrderPersenter = new CreateOrderPersenter(this);


    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        userId = login.getInt("userId", 0);
        sessionId = login.getString("sessionId", "");
        //查询地址
        persenter.queryShippingAddress(userId, sessionId);


    }


    @OnClick(R.id.more)//更多的点击事件
    public void onViewClicked() {
    }

    //提交订单
    @OnClick(R.id.commit)
    public void oncommitClicked() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            int commodityId = list.get(i).getCommodityId();
            int count = list.get(i).getCount();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("commodityId", commodityId);
                jsonObject.put("amount", count);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //获取总价
        Intent intent = getIntent();
//        int sum = intent.getIntExtra("sum", 0);
        double sum = intent.getDoubleExtra("sum", 0);
        createOrderPersenter.createOrder(sessionId, userId, sum, id, jsonArray.toString());

    }

    //查询地址数据
    @Override
    public void getShippingAddressModel(List<QueryAddress.ResultEntity> result) {
        for (int i = 0; i < result.size(); i++) {
            QueryAddress.ResultEntity resultEntity = result.get(i);
            if (resultEntity.getWhetherDefault() == 1) {
                //   获取地址id
                id = resultEntity.getId();
                //赋值
                nameAddressitem.setText(resultEntity.getRealName());
                phoneAddress.setText(resultEntity.getPhone());
                cargoAddress.setText(resultEntity.getAddress());
            }
        }
        EventBus.getDefault().register(this);
    }

    //接受传来的值
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getQuery(List<QueryShopping.Result> query) {
        list = query;
        num.setText(list.size() + "");
        //适配器
        QueryAdapter adapter = new QueryAdapter(this, list);
        rlvConfirm.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //创建订单数据
    @Override
    public void getCreateOrder(Addshopping addshopping) {
        Toast.makeText(this, addshopping.getMessage(), Toast.LENGTH_SHORT).show();
        //跳转到主页面
        Intent intent = new Intent(ConfirmOrderActivity.this, MainActivity.class);
        //存个你所需要到的fragment的索引
        intent.putExtra("id", 3);
        startActivity(intent);
    }
}
