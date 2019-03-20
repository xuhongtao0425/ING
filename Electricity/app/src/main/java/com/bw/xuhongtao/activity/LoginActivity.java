package com.bw.xuhongtao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.base.ActivityBase;
import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.persenter.LoginPersenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends ActivityBase {


    @BindView(R.id.phone_Main)
    EditText phoneMain;//手机号
    @BindView(R.id.pwd_Main)
    EditText pwdMain;//密码
    @BindView(R.id.cut_Main)
    ImageView cutMain;//显示/隐藏密码
    @BindView(R.id.jizhumima)
    CheckBox jizhumima;//记住密码
    @BindView(R.id.reg_main)
    TextView regMain;//跳转注册
    @BindView(R.id.login_main)
    Button loginMain;//登录按钮
    //显示隐藏的状态
    boolean frag=true;
    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePersenter getPersenter() {
        persenter = new LoginPersenter();
        return persenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.cut_Main, R.id.jizhumima, R.id.reg_main, R.id.login_main})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.cut_Main://显示/隐藏密码
                if(frag){
                    //显示
                    pwdMain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    frag=false;
                }else {
                    //隐藏
                   pwdMain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    frag=true;
                }
                break;
            case R.id.jizhumima://记住密码
                break;
            case R.id.reg_main://跳转注册页面
                startActivity(new Intent(LoginActivity.this,RegActivity.class));
                break;
            case R.id.login_main://登录按钮
                break;
        }
    }
}
