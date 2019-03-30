package com.bw.xuhongtao.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.xuhongtao.R;
import com.bw.xuhongtao.base.ActivityBase;
import com.bw.xuhongtao.bean.detailed.Result;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.persenter.DetailsPersenter;
import com.bw.xuhongtao.view.AddshoppingView;
import com.bw.xuhongtao.view.DetailedView;
import com.bw.xuhongtao.view.QueryShoppingView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends ActivityBase<DetailsPersenter> implements DetailedView ,QueryShoppingView,AddshoppingView {


    @BindView(R.id.xbanner_details)
    XBanner xbannerDetails;
    @BindView(R.id.price_details)
    TextView priceDetails;
    @BindView(R.id.goodsname_details)
    TextView goodsnameDetails;
    @BindView(R.id.web_details)
    WebView webDetails;
    @BindView(R.id.fab_details)
    FloatingActionButton fabDetails;
    private int id;
    private String commodityId;

    @Override
    protected int layoutResID() {
        return R.layout.activity_details;
    }

    @Override
    protected DetailsPersenter getPersenter() {
        persenter = new DetailsPersenter(this,this,this);
        return persenter;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        Log.i("DetailsActivity", id + "");

    }

    @Override
    protected void initData() {
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean aBoolean = login.getBoolean("登录", false);
        int  userId = login.getInt("userId", 0);
        String  sessionId = login.getString("sessionId", "");

        persenter.getDetails(id, userId, sessionId);

    }


    @Override
    public void getData(Result result) {
        //
//        Log.i("login",result.toString());
        //轮播图
        final List<String> list = new ArrayList<>();
        String picture = result.getPicture();
        final String[] split = picture.split(",");
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
      xbannerDetails.setData(list,null);
        xbannerDetails.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(DetailsActivity.this).load(list.get(position)).into((ImageView) view);
            }
        });
        //商品的ID
        commodityId = result.getCommodityId();
        //价格
        String price = result.getPrice();
        priceDetails.setText("¥:" + price);
        //商品名
        String name = result.getCommodityName();
        goodsnameDetails.setText(name);
        //详情
        String details = result.getDetails();
        String standard = "<html> \n" +
                "<head> \n" +
                "<style type=\"text/css\"> \n" +
                "body {font-size:13px;}\n" +
                "</style> \n" +
                "</head> \n" +
                "<body>" +
                "<script type='text/javascript'>" +
                "window.onload = function(){\n" +
                "var $img = document.getElementsByTagName('img');\n" +
                "for(var p in  $img){\n" +
                " $img[p].style.width = '100%%';\n" +
                "$img[p].style.height ='auto'\n" +
                "}\n" +
                "}" +
                "</script>" +
                details
                + "</body>" +
                "</html>";
        String mimeType = "text/html";
        String enCoding = "utf-8";
        webDetails.loadDataWithBaseURL(null, standard, mimeType, enCoding, null);
    }

    //点击加入购物车
    @OnClick(R.id.fab_details)
    public void getFab() {
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean aBoolean = login.getBoolean("登录", false);
        int  userId = login.getInt("userId", 0);
        String  sessionId = login.getString("sessionId", "");
        if (!aBoolean) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher_round);
            builder.setTitle("提示");
            builder.setMessage("请先登录");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(DetailsActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailsActivity.this,LoginActivity.class));
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();
            return;
        }else{
            //        Toast.makeText(this, "加入" + userId + sessionId, Toast.LENGTH_SHORT).show();
            //查询购物车
            persenter.queryShoping(userId, sessionId);
        }


    }
    //查询购物车的数据
    @Override
    public void getData(List<QueryShopping.Result> result) {
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean aBoolean = login.getBoolean("登录", false);
        int  userId = login.getInt("userId", 0);
        String  sessionId = login.getString("sessionId", "");
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
//        Log.i("result",result.toString()+"");
        if(result.isEmpty()){
            for (int i = 0; i < result.size(); i++) {
                int commodityId = result.get(i).getCommodityId();
                int count = result.get(i).getCount();
                try {
                    jsonObject.put("commodityId",commodityId);
                    jsonObject.put("count",count);
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            persenter.addShoping(userId, sessionId, jsonArray.toString());

        }else{
            try {
                int i = Integer.parseInt(commodityId);
                jsonObject.put("commodityId",i);
                jsonObject.put("count",1);
                jsonArray.put(jsonObject);
                persenter.addShoping(userId, sessionId, jsonArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    //加入购物车的数据
    @Override
    public void getAddshoppingData(Addshopping addshopping) {
        if(addshopping.getStatus().equals("0000")){
            Toast.makeText(this, addshopping.getMessage(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, addshopping.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.removeSubscriber();
    }

}
