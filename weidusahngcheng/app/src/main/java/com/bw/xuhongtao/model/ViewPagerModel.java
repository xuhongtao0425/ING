package com.bw.xuhongtao.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bw.xuhongtao.utils.OkhttpUtils;
import com.bw.xuhongtao.utils.UrlPath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author xuhongtao
 * @fileName ViewPagerModel
 * @package com.bw.xuhongtao.model
 * @date 2019/2/26/026 14:24
 */
public class ViewPagerModel {
//定义接口
    public  interface OnViewPagerModelListenet{
        void getData(JSONArray result);
}
private OnViewPagerModelListenet onViewPagerModelListenet;

    public void setOnViewPagerModelListenet(OnViewPagerModelListenet onViewPagerModelListenet) {
        this.onViewPagerModelListenet = onViewPagerModelListenet;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String json = (String) msg.obj;
                    try {
                        JSONObject  jsonObject=new JSONObject(json);
                        JSONArray result = jsonObject.getJSONArray("result");
                        if(onViewPagerModelListenet!=null){
                            onViewPagerModelListenet.getData(result);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };


    public void viewpagerModel() {
        OkhttpUtils.getInstance().doGet(UrlPath.vp,null,null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
//                Log.i("json", json);
                Message message = new Message();
                message.what = 0;
                message.obj = json;
                handler.sendMessage(message);

            }
        });
    }
}
