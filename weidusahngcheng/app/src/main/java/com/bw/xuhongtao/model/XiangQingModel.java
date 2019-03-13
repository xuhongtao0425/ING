package com.bw.xuhongtao.model;

import android.os.Handler;
import android.os.Message;

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
 * @fileName XiangQingModel
 * @package com.bw.xuhongtao.model
 * @date 2019/3/4/004 20:25
 */
public class XiangQingModel {

    public interface OnXiangQingModelListener{
        void getXiangQingData( JSONObject result );
    }
private OnXiangQingModelListener onXiangQingModelListener;

    public void setOnXiangQingModelListener(OnXiangQingModelListener onXiangQingModelListener) {
        this.onXiangQingModelListener = onXiangQingModelListener;
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json=(String )msg.obj;
                    try {
                        JSONObject jsonObject=new JSONObject(json);
                        JSONObject result = jsonObject.getJSONObject("result");
                            if(onXiangQingModelListener!=null){
                                onXiangQingModelListener.getXiangQingData(result);
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    public void xiangqingModel(String userId, String sessionId, int id) {
        OkhttpUtils.getInstance().doGet(UrlPath.details + id, userId, sessionId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.what = 0;
                message.obj = json;
                handler.sendMessage(message);

            }
        });
    }
}
