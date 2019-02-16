package com.bw.xuhongtao.perenter;

import com.bw.xuhongtao.model.LoginModel;
import com.bw.xuhongtao.view.LoginView;
import com.bw.xuhongtao.view.activity.MainActivity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author xuhongtao
 * @fileName LoginPersenter
 * @package com.bw.xuhongtao.perenter
 * @date 2019/2/16/016 18:42
 */
public class LoginPersenter<T> {
    private Reference<T> myReference;
    private final LoginModel loginModel;
    private final LoginView loginView;

    //构造器
    public LoginPersenter(LoginView view) {
        loginModel = new LoginModel();
        loginView = view;
    }

    //管理外部类(防止内存泄露)
    public void relyView(T view) {
        myReference = new WeakReference<>(view);
    }

    //V关联P
    public void loginPersenter(String phone, String pwd) {
        //P关联M
        loginModel.loginModel(phone, pwd);
        //回调数据
        loginModel.setOnLoginListener(new LoginModel.OnLoginListener() {
            @Override
            public void loginData(String message, String status) {
                //
                loginView.loginView(message, status);
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
