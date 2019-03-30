package com.bw.xuhongtao.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.base.ActivityBase;
import com.bw.xuhongtao.base.BaseActivity;
import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.persenter.AddressPersenter;
import com.bw.xuhongtao.view.AddressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

/**
 * 编写地址
 */

public class WriteAddressActivity extends ActivityBase<AddressPersenter> implements CityPickerListener, AddressView {
    @BindView(R.id.recipients)
    EditText recipients;   //收件人
    @BindView(R.id.phonenumber)
    EditText phonenumber;   //手机号
    @BindView(R.id.area)
    TextView area;  //所在地区
    @BindView(R.id.better)
    EditText better;  //详细区域
    @BindView(R.id.postal)
    EditText postal;//编码
    @BindView(R.id.suo)
    TextView suo;
    @BindView(R.id.xiang)
    TextView xiang;
    @BindView(R.id.save)
    Button save;   //保存
    @BindView(R.id.level)
    ImageView level; //图标三级联动
    //声明三级联动对象
    private CityPicker cityPicker;

    @Override
    protected int layoutResID() {
        cityPicker = new CityPicker(WriteAddressActivity.this, this);
        return R.layout.activity_write_address;
    }

    @Override
    protected AddressPersenter getPersenter() {
        persenter = new AddressPersenter(this);
        return persenter;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    //三级联动重写方法
    @Override
    public void getCity(String s) {
        area.setText(s);

    }

    //保存
    @OnClick(R.id.save)
    public void onsaveClicked() {
        //获取收件人的名字
        String name = recipients.getText().toString().trim();
        //手机号
        String phone = phonenumber.getText().toString().trim();
        //省市区
        String districts = area.getText().toString().trim();
        //详细地址
        String detailed = better.getText().toString().trim();
        //邮政编码
        String zipCode = postal.getText().toString().trim();
        //判断收件人
        if (!TextUtils.isEmpty(name)) {
            //判断手机号
            if (!TextUtils.isEmpty(phone)) {
                //判断省市区
                if (!TextUtils.isEmpty(districts)) {
                    //判断详细地址
                    if (!TextUtils.isEmpty(detailed)) {
                        //判断邮政编码
                        if (!TextUtils.isEmpty(zipCode)) {
                            String address = districts + " " + detailed;
                            SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                            int userId = login.getInt("userId", 0);
                            String sessionId = login.getString("sessionId", "");
                            persenter.addressData(userId, sessionId, name, phone, address, zipCode);


                        } else {
                            Toast.makeText(this, "邮政编码不能为空", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "省市区不能为空", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "收件人不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    //三及联动
    @OnClick(R.id.level)
    public void onViewClicked() {
        cityPicker.show();
    }

    //数据
    @Override
    public void AddressModelData(Addshopping addshopping) {
        if(addshopping.getStatus().equals("0000")){

            Toast.makeText(this, addshopping.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, addshopping.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.detach();
    }
}
