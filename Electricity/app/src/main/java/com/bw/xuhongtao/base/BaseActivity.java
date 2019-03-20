package com.bw.xuhongtao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName ActivityBase
 * @package com.bw.xuhongtao.base
 * @date 2019/3/14/014 9:01
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        ButterKnife.bind(this);

        initView();

        initData();
    }


    protected abstract int layoutResID();


    protected abstract void initView();

    protected abstract void initData();


}
