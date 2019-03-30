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
public abstract class BaseFragement extends Fragment {
    boolean isuser;
    boolean isview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutResID(), container, false);
        initView(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isuser = true;
        LazyLoading();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isuser = true;
            LazyLoading();
        }

    }

    protected void LazyLoading() {
        if (isuser && isview) {
            initData();
        }

    }

    ;

    protected abstract int layoutResID();

    protected abstract void initView(View view);

    protected abstract void initData();
}
