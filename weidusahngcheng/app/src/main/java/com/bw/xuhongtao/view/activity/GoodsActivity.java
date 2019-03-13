package com.bw.xuhongtao.view.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.xuhongtao.R;

import com.bw.xuhongtao.utils.UrlPath;
import com.bw.xuhongtao.view.base.BaseActivity;
import com.bw.xuhongtao.view.fragment.FragmentDingdan;
import com.bw.xuhongtao.view.fragment.FragmentGeren;
import com.bw.xuhongtao.view.fragment.FragmentShouYe;
import com.bw.xuhongtao.view.fragment.FragmentGouwuche;
import com.bw.xuhongtao.view.fragment.FragmentQuanzi;

public class GoodsActivity extends BaseActivity {


    private RadioGroup rg_goods;
    private FragmentShouYe fragmentGoods = null;
    private FragmentQuanzi fragmentQuanzi = null;
    private FragmentGouwuche fragmentGouwuche = null;
    private FragmentDingdan fragmentDingdan = null;
    private FragmentGeren fragmentGeren = null;
    private RadioButton shouye,quanzi,dingdan,geren;

    @Override
    protected int layoutResID() {
        return R.layout.activity_goods;
    }

    @Override
    protected void initView() {

        rg_goods = findViewById(R.id.rg_goods);
        shouye = findViewById(R.id.shouye);
        quanzi = findViewById(R.id.quanzi);
        dingdan = findViewById(R.id.dingdan);
        geren = findViewById(R.id.geren);



    }

    @Override
    protected void initData() {
        //默认第一个选中
        rg_goods.check(rg_goods.getChildAt(0).getId());
        //事务添加
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame, fragmentGoods = new FragmentShouYe()).commit();
        //点击事件
        rg_goods.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                hideFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                boolean sy = true;
                boolean qz = false;
                boolean dd = false;
                boolean gr = false;

                switch (checkedId) {
                    case R.id.shouye:
                        if (fragmentGoods == null) {
                            fragmentGoods = new FragmentShouYe();
                            transaction.add(R.id.frame, fragmentGoods);
                        } else {
                            transaction.show(fragmentGoods);
                        }
                        sy = true;
                        qz = false;
                        dd = false;
                        gr = false;
                        break;
                    case R.id.quanzi:
                        if (fragmentQuanzi == null) {
                            fragmentQuanzi = new FragmentQuanzi();
                            transaction.add(R.id.frame, fragmentQuanzi);
                        } else {
                            transaction.show(fragmentQuanzi);
                        }
                        sy = false;
                        qz = true;
                        dd = false;
                        gr = false;
                        break;
                    case R.id.gouwuche:
                        if (fragmentGouwuche == null) {
                            fragmentGouwuche = new FragmentGouwuche();
                            transaction.add(R.id.frame, fragmentGouwuche);
                        } else {
                            transaction.show(fragmentGouwuche);
                        }
                        sy = false;
                        qz = false;
                        dd = false;
                        gr = false;
                        break;
                    case R.id.dingdan:
                        if (fragmentDingdan == null) {
                            fragmentDingdan = new FragmentDingdan();
                            transaction.add(R.id.frame, fragmentDingdan);
                        } else {
                            transaction.show(fragmentDingdan);
                        }
                        sy = false;
                        qz = false;
                        dd = true;
                        gr = false;
                        break;
                    case R.id.geren:
                        if (fragmentGeren == null) {
                            fragmentGeren = new FragmentGeren();
                            transaction.add(R.id.frame, fragmentGeren);
                        } else {
                            transaction.show(fragmentGeren);
                        }
                        sy = false;
                        qz = false;
                        dd = false;
                        gr = true;
                        break;
                }
                transaction.commit();
                if (sy) {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_shouye_true);
                    shouye.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                } else {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_shouye);
                    shouye.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                }
                if (qz) {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_quanzi_true);
                    quanzi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                } else {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_quanzi);
                    quanzi.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                }

                if (dd) {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_dingdan_true);
                    dingdan.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                } else {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_dingdan);
                    dingdan.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                }
                if (gr) {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_geren_true);
                    geren.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                } else {
                    Drawable drawable = GoodsActivity.this.getResources().getDrawable(R.mipmap.daohang_geren);
                    geren.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable, null, null);
                }
               }
        });
    }

    public void hideFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentGoods != null && fragmentGoods.isAdded()) {
            transaction.hide(fragmentGoods);
        }
        if (fragmentQuanzi != null && fragmentQuanzi.isAdded()) {
            transaction.hide(fragmentQuanzi);
        }
        if (fragmentGouwuche != null && fragmentGouwuche.isAdded()) {
            transaction.hide(fragmentGouwuche);
        }
        if (fragmentDingdan != null && fragmentDingdan.isAdded()) {
            transaction.hide(fragmentDingdan);
        }
        if (fragmentGeren != null && fragmentGeren.isAdded()) {
            transaction.hide(fragmentGeren);
        }
        transaction.commit();

    }
}
