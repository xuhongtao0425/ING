package com.bw.xuhongtao.fragement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.activity.LoginActivity;
import com.bw.xuhongtao.adapter.QueryShoppingAdapter;
import com.bw.xuhongtao.base.FragementBase;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.persenter.QueryShoppingPersenter;
import com.bw.xuhongtao.view.QueryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xuhongtao
 * @fileName ShoppingFragment    购物车
 * @package com.bw.xuhongtao.fragement
 * @date 2019/3/17/017 10:39
 */
public class ShoppingFragment extends FragementBase<QueryShoppingPersenter> implements QueryView {

    @BindView(R.id.rlv_shoppingi)
    RecyclerView rlvShoppingi;
    @BindView(R.id.checkbox_shopping)
    CheckBox checkboxShopping;
    @BindView(R.id.sum_shopping)
    TextView sumShopping;
    @BindView(R.id.submit_shopping)
    Button submitShopping;
    Unbinder unbinder;

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
    }

    @Override
    protected void initData() {
        Log.i("CircleFragment", "ShoppingFragment");
        //获取登录的状态
        SharedPreferences login = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean loginBoolean = login.getBoolean("登录", false);
        if (loginBoolean) {
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
//                    Toast.makeText(getActivity(), "点击了确定", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            });
            builder.setNegativeButton("取消", null);
            builder.show();
        }

    }

    @Override
    public void getQueryShoppingData(QueryShopping queryShopping) {
        Log.i("sohhppp",queryShopping.toString());
        if (queryShopping != null) {
            List<QueryShopping.Result> result = queryShopping.getResult();
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            rlvShoppingi.setLayoutManager(layoutManager);
//            rlvShoppingi.setAdapter(new QueryShoppingAdapter(getActivity(),result));

        }

    }

    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
