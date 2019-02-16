package com.bw.xuhongtao.view.activity;

import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.perenter.RegPersenter;
import com.bw.xuhongtao.utils.VerificationUtils;
import com.bw.xuhongtao.view.RegView;
import com.bw.xuhongtao.view.base.BaseActivity;

public class RegActivity extends BaseActivity implements View.OnClickListener,RegView {

    private EditText phone_Main, pwd_Main;
    private TextView login;
    private Button reg_button;
    private RegPersenter persenter;
    private ImageView cut;
    private boolean isCut=true;

    @Override
    protected int layoutResID() {
        return R.layout.activity_good;
    }

    @Override
    protected void initView() {
        phone_Main = findViewById(R.id.phone_Main);
        pwd_Main = findViewById(R.id.pwd_Main);
        login = findViewById(R.id.login);
        reg_button = findViewById(R.id.reg_Button);
        cut = findViewById(R.id.cut);
        //实例化
        persenter = new RegPersenter(this);
        //点击事件
        login.setOnClickListener(this);
        reg_button.setOnClickListener(this);
        cut.setOnClickListener(this);



    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                startActivity(new Intent(RegActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.reg_Button:
                String phone = phone_Main.getText().toString();
                String pwd = pwd_Main.getText().toString();
                //让P管理V层
//                persenter.relyView(this);

                boolean b = VerificationUtils.isMobileNO(phone);
                if(!b){
                    Toast.makeText(this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<6){
                    Toast.makeText(this, "密码不对小于6位", Toast.LENGTH_SHORT).show();
                    return;
                }
                //v关联P
                persenter.regPersenter(phone,pwd);
                break;
            case R.id.cut:
                if(isCut){
                    //密码可见
                    pwd_Main.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isCut=false;
                }else{

                    //密码不可见
                    pwd_Main.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isCut=true;
                }
                break;
        }

    }


    @Override
    public void regView(String message, String status) {
        if(status.equals("0000")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegActivity.this,MainActivity.class));
            finish();
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        persenter.separateView();
    }

}
