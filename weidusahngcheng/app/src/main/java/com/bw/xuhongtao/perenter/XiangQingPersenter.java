package com.bw.xuhongtao.perenter;

import com.bw.xuhongtao.model.XiangQingModel;
import com.bw.xuhongtao.view.XiangQingView;
import com.bw.xuhongtao.view.activity.XiangQingActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author xuhongtao
 * @fileName XiangQingPersenter
 * @package com.bw.xuhongtao.perenter
 * @date 2019/3/4/004 19:44
 */
public class XiangQingPersenter<T> {
    private Reference<T> reference;
    private final XiangQingModel xiangQingModel;
    private final XiangQingView xiangQingView;


    //构造方法
    public XiangQingPersenter(XiangQingView view) {
        xiangQingModel = new XiangQingModel();
        xiangQingView = view;
    }

    public void attchData(T t) {
        reference = new WeakReference<>(t);
    }

    public void xiangqingPersenter(String userId, String sessionId, int id) {
        xiangQingModel.xiangqingModel(userId,sessionId,id);

        xiangQingModel.setOnXiangQingModelListener(new XiangQingModel.OnXiangQingModelListener() {
            @Override
            public void getXiangQingData(JSONObject result) {
                xiangQingView.getXiangQingData(result);
            }
        });

    }

    public void datachData() {
        if (reference.get() != null) {
            reference.clear();
            reference = null;
        }
    }
}
