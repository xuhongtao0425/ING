package com.bw.xuhongtao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bw.xuhongtao.R;

import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName ActivityBase
 * @package com.bw.xuhongtao.base
 * @date 2019/3/14/014 9:01
 */
public abstract class ActivityBase<T extends BasePersenter> extends AppCompatActivity {
    public T persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());
        ButterKnife.bind(this);
        persenter = getPersenter();
        persenter.attach(this);
        initView();

        initData();
    }


    protected abstract int layoutResID();

    protected abstract T getPersenter();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        persenter.detach();
    }
}
