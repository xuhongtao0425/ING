package com.bw.xuhongtao.view.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author xuhongtao
 * @fileName BaseFragment
 * @package com.bw.xuhongtao.view.base
 * @date 2019/2/17/017 13:37
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResID(), container, false);
        //初始化页面
        initView();
        //初始化数据
        initData();
        return view;
    }

    protected abstract int layoutResID();

    protected abstract void initView();

    protected abstract void initData();
}
