package com.bw.xuhongtao.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.perenter.XiangQingPersenter;
import com.bw.xuhongtao.view.XiangQingView;
import com.bw.xuhongtao.view.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class XiangQingActivity extends BaseActivity implements XiangQingView {


    private TextView goodprice_xiangqing,goodnaem_xiangqing,goodweight_xiangqing;
    private ViewPager vp_xiangqing;
    private String userId;
    private String sessionId;
    private XiangQingPersenter xqp;

    @Override
    protected int layoutResID() {
        return R.layout.activity_xiang_qing;
    }
    @Override
    protected void initView() {
        goodprice_xiangqing = findViewById(R.id.goodprice_xiangqing);
        goodnaem_xiangqing= findViewById(R.id.goodnaem_xiangqing);
        goodweight_xiangqing=  findViewById(R.id.goodweight_xiangqing);
        vp_xiangqing = findViewById(R.id.vp_xiangqing);
        //获取值
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        userId = user.getString("userId", "");
        sessionId = user.getString("sessionId", "");
        //实例化
        xqp = new XiangQingPersenter(this);


    }

    @Override
    protected void initData() {
        //获取intent传来的值
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        //管理
        xqp.attchData(this);
        //关联
        xqp.xiangqingPersenter(userId,sessionId,id);



    }



    @Override
    public void getXiangQingData(JSONObject result) {
        try {
            //获取轮播数据
            String picture = result.getString("picture");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        xqp.datachData();
    }
}
