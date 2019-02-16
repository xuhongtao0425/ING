package com.bw.xuhongtao.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.xuhongtao.R;

/**
 * @author xuhongtao
 * @fileName BaseActivity
 * @package com.bw.xuhongtao.view.base
 * @date 2019/2/15/015 14:20
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        //初始化页面
        initView();
        //初始化数据
        initData();
    }

    protected abstract int layoutResID();

    protected abstract void initView();

    protected abstract void initData();
}
