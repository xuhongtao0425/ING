package com.bw.xuhongtao.perenter;

import com.bw.xuhongtao.model.RegModel;
import com.bw.xuhongtao.view.RegView;
import com.bw.xuhongtao.view.activity.RegActivity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author xuhongtao
 * @fileName RegPersenter
 * @package com.bw.xuhongtao.perenter
 * @date 2019/2/16/016 17:06
 */
public class RegPersenter<T> {
    private Reference<T> myReference;
    private final RegModel regModel;
    private final RegView regView;

    //构造器
    public RegPersenter(RegView view) {
        regModel = new RegModel();
        regView = view;
    }

    //管理外部类(防止泄露)
    public void relyView(T view) {
        myReference = new WeakReference<>(view);
    }

    //v关联p
    public void regPersenter(String phone, String pwd) {
        //p关联m
        regModel.regModel(phone, pwd);
        //回调数据
        regModel.setOnRegListener(new RegModel.OnRegListener() {
            @Override
            public void regData(String message, String status) {
                //调用接口方法
                regView.regView(message, status);
            }
        });
    }

    //解除管理
    public void separateView() {
        if (myReference.get() != null) {
            myReference.clear();
            myReference = null;
        }
    }
}
