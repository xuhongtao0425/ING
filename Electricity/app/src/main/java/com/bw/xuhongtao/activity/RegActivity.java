package com.bw.xuhongtao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.base.ActivityBase;
import com.bw.xuhongtao.base.BaseActivity;
import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.regbean.RegBean;
import com.bw.xuhongtao.persenter.RegPersenter;
import com.bw.xuhongtao.utils.NetWork;
import com.bw.xuhongtao.utils.VerificationUtils;
import com.bw.xuhongtao.view.RegView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegActivity extends ActivityBase<RegPersenter> implements RegView {


    @BindView(R.id.phone_reg)
    EditText phoneReg;
    @BindView(R.id.pwd_reg)
    EditText pwdReg;
    @BindView(R.id.cut_reg)
    ImageView cutReg;
    @BindView(R.id.login_reg)
    TextView loginReg;
    @BindView(R.id.reg_reg)
    Button regReg;
    boolean frag=true;

    @Override
    protected int layoutResID() {
        return R.layout.activity_reg;
    }

    @Override
    protected RegPersenter getPersenter() {
        persenter=new RegPersenter(this);
        return persenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({ R.id.cut_reg, R.id.login_reg, R.id.reg_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cut_reg://显示/隐藏密码
                if(frag){
                    //显示
                    pwdReg.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    frag=false;
                }else {
                    //隐藏
                    pwdReg.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    frag=true;
                }
                break;
            case R.id.login_reg://退回登录页面
               finish();
                break;
            case R.id.reg_reg://注册
                //获取输入矿的值
                String phone = phoneReg.getText().toString();
                String pwd = pwdReg.getText().toString();
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
                    persenter.regPersenter(phone,pwd);
                }else{
                    Toast.makeText(this, "检查一下网络", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }

    @Override
    public void regData(RegBean regBean) {
        String message = regBean.getMessage();
        String status = regBean.getStatus();
        if(status.equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.removeSubscriber();
    }
}
