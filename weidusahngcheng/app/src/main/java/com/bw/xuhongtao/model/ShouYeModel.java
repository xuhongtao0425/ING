package com.bw.xuhongtao.model;

import android.os.Handler;
import android.os.Message;

import com.bw.xuhongtao.model.bean.ShouYeBean;
import com.bw.xuhongtao.model.bean.ShouYeBeans;
import com.bw.xuhongtao.utils.OkhttpUtils;
import com.bw.xuhongtao.utils.UrlPath;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author xuhongtao
 * @fileName ShouYeModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/3/003 13:48
 */
public class ShouYeModel {
    public interface OnShouYeModelListener{
        void getShouYeModel( List<ShouYeBeans> list);
    }
    private OnShouYeModelListener onShouYeModelListener;

    public void setOnShouYeModelListener(OnShouYeModelListener onShouYeModelListener) {
        this.onShouYeModelListener = onShouYeModelListener;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json=(String) msg.obj;
                    List<ShouYeBeans> list=new ArrayList<>();
                    Gson gson=new Gson();
                    ShouYeBean shouYeBean = gson.fromJson(json, ShouYeBean.class);
                    ShouYeBean.ResultEntity result = shouYeBean.getResult();
                    ShouYeBean.ResultEntity.MlssEntity mlss = result.getMlss();
                    ShouYeBean.ResultEntity.PzshEntity pzsh = result.getPzsh();
                    ShouYeBean.ResultEntity.RxxpEntity rxxp = result.getRxxp();
                    ShouYeBeans syb=new ShouYeBeans(mlss,pzsh,rxxp);
                    list.add(syb);
                    if(onShouYeModelListener!=null){
                        onShouYeModelListener.getShouYeModel(list);
                    }

                    break;
            }
        }
    };
    public void shouyeModel() {
        OkhttpUtils.getInstance().doGet(UrlPath.shouye,null,null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=json;
                handler.sendMessage(message);

            }
        });
    }
}
