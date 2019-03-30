package com.bw.xuhongtao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.*;
import com.bw.xuhongtao.base.ActivityBase;
import com.bw.xuhongtao.bean.loginbean.LoginBean;
import com.bw.xuhongtao.persenter.LoginPersenter;
import com.bw.xuhongtao.utils.NetWork;
import com.bw.xuhongtao.utils.VerificationUtils;
import com.bw.xuhongtao.view.LoginView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends ActivityBase<LoginPersenter> implements LoginView {


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
    private SharedPreferences login;
    private SharedPreferences.Editor edit;

    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPersenter getPersenter() {
        persenter = new LoginPersenter(this);
        return persenter;
    }

    @Override
    protected void initView() {

        login = getSharedPreferences("login", Context.MODE_PRIVATE);
        edit = login.edit();
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
                //获取输入矿的值
                String phone = phoneMain.getText().toString();
                String pwd = pwdMain.getText().toString();
                //获取手机号验证
                boolean b = VerificationUtils.isMobileNO(phone);
                if(!b){
                    Toast.makeText(this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<6){
                    Toast.makeText(this, "密码不能小于6位", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取网络状态
                boolean networkConnected = NetWork.isNetworkConnected(this);
                if(networkConnected){
                    //关联
                    persenter.loginPersenter(phone,pwd);
                }else{
                    Toast.makeText(this, "检查一下网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }

    @Override
    public void loginData(LoginBean loginBean) {
        EventBus.getDefault().post(loginBean);
        String message = loginBean.getMessage();
        String status = loginBean.getStatus();
        LoginBean.ResultEntity result = loginBean.getResult();
        if(status.equals("0000")){
            Log.i("textsss",result.getUserId()+"ssss"+result.getSessionId());
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            edit.putBoolean("登录",true);
            edit.putInt("userId",result.getUserId());
            edit.putString("sessionId",result.getSessionId());
            String nickName = result.getNickName();
            edit.putString("nickName", nickName);
            String headPic = result.getHeadPic();
            edit.putString("headPic", headPic);
            edit.commit();
            finish();
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //接触订阅
        persenter.removeSubscriber();
    }
}
