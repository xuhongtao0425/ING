package com.bw.xuhongtao.fragement;

import android.util.Log;
import android.view.View;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.base.BaseFragement;

/**
 * @author xuhongtao
 * @fileName CircleFragment    圈子
 * @package com.bw.xuhongtao.fragement
 * @date 2019/3/17/017 10:35
 */
public class CircleFragment extends BaseFragement {
    @Override
    protected int layoutResID() {
        return R.layout.layout_circle;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        Log.i("CircleFragment","CircleFragment");

    }
}
