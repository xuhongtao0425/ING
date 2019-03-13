package com.bw.xuhongtao.perenter;

import com.bw.xuhongtao.model.ViewPagerModel;
import com.bw.xuhongtao.view.ViewPagerView;
import com.bw.xuhongtao.view.fragment.FragmentShouYe;

import org.json.JSONArray;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author xuhongtao
 * @fileName ViewPagerPersenter
 * @package com.bw.xuhongtao.perenter
 * @date 2019/2/26/026 14:14
 */
public class ViewPagerPersenter<T> {

    private Reference<T> reference;
    private final ViewPagerModel viewPagerModel;
    private final ViewPagerView viewPagerView;

    public ViewPagerPersenter(ViewPagerView view) {
        viewPagerModel = new ViewPagerModel();
        viewPagerView = view;
    }

    public void attachData(T t) {
        reference = new WeakReference<>(t);
    }

    public void shouyePersenter() {
        viewPagerModel.viewpagerModel();
        viewPagerModel.setOnViewPagerModelListenet(new ViewPagerModel.OnViewPagerModelListenet() {
            @Override
            public void getData(JSONArray result) {
                viewPagerView.getData(result);
            }
        });

    }

    public void dataachData() {
        if (reference.get() != null) {
            reference.clear();
            reference = null;
        }
    }


}
