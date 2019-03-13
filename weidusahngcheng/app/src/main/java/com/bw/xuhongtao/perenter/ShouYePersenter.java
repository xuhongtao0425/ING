package com.bw.xuhongtao.perenter;

import com.bw.xuhongtao.model.ShouYeModel;
import com.bw.xuhongtao.model.bean.ShouYeBeans;
import com.bw.xuhongtao.view.ShouYeView;
import com.bw.xuhongtao.view.fragment.FragmentShouYe;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author xuhongtao
 * @fileName ShouYePersenter
 * @package com.bw.xuhongtao.perenter
 * @date 2019/3/3/003 13:41
 */
public class ShouYePersenter<T> {
    //声明
    private Reference<T> reference;
    private final ShouYeModel sym;
    private final ShouYeView shouYeView;

    //构造方法


    public ShouYePersenter(ShouYeView view) {
        sym = new ShouYeModel();
        shouYeView = view;
    }

    // 管理
    public void attachData(T t) {
        reference = new WeakReference<>(t);
    }

    //关联
    public void shouyePersenter() {
        sym.shouyeModel();
        //回调数据
        sym.setOnShouYeModelListener(new ShouYeModel.OnShouYeModelListener() {
            @Override
            public void getShouYeModel(List<ShouYeBeans> list) {

                shouYeView.getShouYeModel(list);

            }
        });

    }

    //解除管理防止泄露
    public void dataachData() {
        if (reference.get() != null) {
            reference.clear();
            reference = null;
        }
    }
}
