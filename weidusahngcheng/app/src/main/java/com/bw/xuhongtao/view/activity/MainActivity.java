package com.bw.xuhongtao.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.perenter.LoginPersenter;
import com.bw.xuhongtao.utils.VerificationUtils;
import com.bw.xuhongtao.view.LoginView;
import com.bw.xuhongtao.view.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity implements View.OnClickListener, LoginView {


    private EditText phone_Main, pwd_Main;
    private CheckBox jizhumima;
    private TextView reg;
    private Button login;
    private ImageView cut_main;
    private boolean isCut = true;
    private LoginPersenter persenter;
    private SharedPreferences.Editor edit;

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //创建SharedPreferences
        SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);


        //创建编辑器
        edit = preferences.edit();


        phone_Main = findViewById(R.id.phone_Main);
        pwd_Main = findViewById(R.id.pwd_Main);
        jizhumima = findViewById(R.id.jizhumima);
        reg = findViewById(R.id.reg_main);
        login = findViewById(R.id.login_main);
        cut_main = findViewById(R.id.cut_Main);
        boolean pass = preferences.getBoolean("记住密码", false);
        if (pass) {
            phone_Main.setText(preferences.getString("phone", ""));
            pwd_Main.setText(preferences.getString("pwd", ""));
            jizhumima.setChecked(pass);
        }

        //实例化
        persenter = new LoginPersenter(this);
        //点击事件
        reg.setOnClickListener(this);
        login.setOnClickListener(this);
        cut_main.setOnClickListener(this);
    }

    @Override
    protected void initData() {


    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_main:
                startActivity(new Intent(MainActivity.this, RegActivity.class));
                break;
            case R.id.login_main:
                String phone = phone_Main.getText().toString();
                String pwd = pwd_Main.getText().toString();
                edit.putString("phone", phone);
                edit.putString("pwd", pwd);
                edit.commit();
                //让P管理V层
//                persenter.relyView(this);

                boolean b = VerificationUtils.isMobileNO(phone);
                if (!b) {
                    Toast.makeText(this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.length() < 6) {
                    Toast.makeText(this, "密码不对小于6位", Toast.LENGTH_SHORT).show();
                    return;
                }
                persenter.loginPersenter(phone, pwd);

                break;
            case R.id.cut_Main:
                if (isCut) {
                    //密码可见
                    pwd_Main.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isCut = false;
                } else {

                    //密码不可见
                    pwd_Main.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isCut = true;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        persenter.separateView();
    }

    @Override
    public void loginView(String message, String status, JSONObject result) {
        try {
            //获取SharedPreferences存入userId,sessionId
            SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor useredit = user.edit();
            String userId = result.getString("userId");
            String sessionId = result.getString("sessionId");
            useredit.putString("userId", userId);
            useredit.putString("sessionId", sessionId);
            useredit.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (status.equals("0000")) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                edit.putBoolean("记住密码", jizhumima.isChecked()).commit();

                Intent intent = new Intent(MainActivity.this, GoodsActivity.class);

                startActivity(intent);
                finish();
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
