package com.bw.xuhongtao.model;

import android.os.Handler;
import android.os.Message;

import com.bw.xuhongtao.utils.OkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author xuhongtao
 * @fileName RegModel
 * @package com.bw.xuhongtao.model
 * @date 2019/2/16/016 17:21
 */
public class RegModel {
    //定义接口
    public interface OnRegListener{
        void regData(String message,String status);
    }
    private OnRegListener onRegListener;

    public void setOnRegListener(OnRegListener onRegListener) {
        this.onRegListener = onRegListener;
    }

    private String url="http://172.17.8.100/small/user/v1/register";
    private Map<String ,String> mapReg=new HashMap<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    String json=(String)msg.obj;
                    try {
                        JSONObject jsonObject=new JSONObject(json);
                        String message = jsonObject.getString("message");
                        String status = jsonObject.getString("status");
                        if(onRegListener!=null){
                            onRegListener.regData(message,status);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
    public void regModel(String phone, String pwd) {
        mapReg.put("phone",phone);
        mapReg.put("pwd",pwd);
        //调用方法
        OkhttpUtils.doPost(url, mapReg, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String regJsonData = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=regJsonData;
                handler.sendMessage(message);


            }
        });

    }
}
