package com.bw.xuhongtao.fragement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.activity.ConfirmOrderActivity;
import com.bw.xuhongtao.activity.LoginActivity;
import com.bw.xuhongtao.adapter.QueryShoppingAdapter;
import com.bw.xuhongtao.base.FragementBase;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.persenter.QueryShoppingPersenter;
import com.bw.xuhongtao.view.QueryShoppingView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author xuhongtao
 * @fileName ShoppingFragment    购物车
 * @package com.bw.xuhongtao.fragement
 * @date 2019/3/17/017 10:39
 */
public class ShoppingFragment extends FragementBase<QueryShoppingPersenter> implements QueryShoppingView {

    @BindView(R.id.rlv_shoppingi)
    RecyclerView rlvShoppingi;
    @BindView(R.id.checkbox_shopping)
    CheckBox checkboxShopping;
    @BindView(R.id.sum_shopping)
    TextView sumShopping;
    @BindView(R.id.submit_shopping)
    Button submitShopping;
    Unbinder unbinder;
    List<QueryShopping.Result> list=null;
    List<QueryShopping.Result> query=null;
    private QueryShoppingAdapter adapter;
    private double sums=0.0;

    @Override
    protected int layoutResID() {
        return R.layout.layout_shopping;
    }

    @Override
    protected QueryShoppingPersenter getPersenter() {
        persenter = new QueryShoppingPersenter(this);
        return persenter;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        //查询登录数据
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean aBoolean = login.getBoolean("登录", false);
        if (aBoolean) {
            int userId = login.getInt("userId", 0);
            String sessionId = login.getString("sessionId", "");
            persenter.queryShopping(userId, sessionId);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setIcon(R.mipmap.ic_launcher_round);
            builder.setTitle("提示");
            builder.setMessage("请先登录");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Toast.makeText(DetailsActivity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();
        }


    }

    @Override
    protected void initData() {

    }

    //查询数据
    @Override
    public void getData(List<QueryShopping.Result> result) {
        if(result.size()==0){
            Toast.makeText(getActivity(), "空空如也", Toast.LENGTH_SHORT).show();
            return;
        }
        //不为空赋值全选全不选用
        list=result;
     //布局管理器
        rlvShoppingi.setLayoutManager(new LinearLayoutManager(getActivity()));
        //适配器
        adapter = new QueryShoppingAdapter(getActivity(), list);
        rlvShoppingi.setAdapter(adapter);
        //回调接口数据
        adapter.setOnsumListener(new QueryShoppingAdapter.OnsumListener() {
            @Override
            public void getsum(List<QueryShopping.Result> result) {
               double sum=0;
               int ischeck=0;
                for (int i = 0; i < result.size(); i++) {
                    //循环获取条目复选框状态
                    boolean checked = result.get(i).isChecked();
                    if(checked){//选中就计算价格
                        int count = result.get(i).getCount();
                        double price = result.get(i).getPrice();
                        sum+=((double) count*price);
                        //这个用于判断是否全选
                        ischeck++;
                    }
                }
                //判断ischeck是否等于集合长度
                if(ischeck==(result.size())){
                    checkboxShopping.setChecked(true);
                }else {
                    checkboxShopping.setChecked(false);
                }
                //设置总价
              sums=sum;
                sumShopping.setText(sums+"");
            }
        });
    }
    //点击事件
    @OnClick({R.id.checkbox_shopping, R.id.submit_shopping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox_shopping:
                if(checkboxShopping.isChecked()){
//                    全选选中
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(true);
                        int count = list.get(i).getCount();
                        double price = list.get(i).getPrice();
                        sums +=(count*(int)price);
                    }
                    sumShopping.setText(sums+"");
                }else{
                    //  全选不选中
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setChecked(false);
                    }
                    sums=0.0;
                    sumShopping.setText(sums+"");
                }
                adapter.notifyDataSetChanged();
                break;
            case R.id.submit_shopping:
                if(sums==0.0){
                    Toast.makeText(getActivity(), "请选择您要购买的商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                query=new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    boolean checked = list.get(i).isChecked();
                    if(checked){
                        query.add(list.get(i));
                    }
                }
                Toast.makeText(getActivity(), ""+sums, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), ""+query.size(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ConfirmOrderActivity.class);
                intent.putExtra("sum",sums);

                //传值
                EventBus.getDefault().postSticky(query);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean aBoolean = login.getBoolean("登录", false);
        if (aBoolean) {
            int userId = login.getInt("userId", 0);
            String sessionId = login.getString("sessionId", "");
            persenter.queryShopping(userId, sessionId);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
