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
 * @fileName LoginModel
 * @package com.bw.xuhongtao.model
 * @date 2019/2/16/016 18:46
 */
public class LoginModel {
//定义接口
    public interface OnLoginListener{
        void loginData(String message,String status);
}
private OnLoginListener onLoginListener;

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }

    private String url="http://172.17.8.100/small/user/v1/login";
    private Map<String , String> map=new HashMap<>();
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
                        if(onLoginListener!=null){
                            onLoginListener.loginData(message,status);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    };
    public void loginModel(String phone, String pwd) {
        map.put("phone",phone);
        map.put("pwd",pwd);
        OkhttpUtils.doPost(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String loginJsonData = response.body().string();
                Message message=new Message();
                message.what=0;
                message.obj=loginJsonData;
                handler.sendMessage(message);
            }
        });
    }
}
