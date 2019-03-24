package com.bw.xuhongtao.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author xuhongtao
 * @fileName BaseFragement
 * @package com.bw.xuhongtao.base
 * @date 2019/3/14/014 10:13
 */
public abstract class FragementBase<V extends BasePersenter> extends Fragment {
    public V persenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResID(), container, false);
        persenter = getPersenter();
        persenter.attach(this);
        initView(view);
        initData();
        return view;


    }


    protected abstract int layoutResID();

    protected abstract V getPersenter();

    protected abstract void initView(View view);

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        persenter.detach();
    }
}
