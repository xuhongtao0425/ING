package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.loginbean.LoginBean;
import com.bw.xuhongtao.model.LoginModel;
import com.bw.xuhongtao.view.LoginView;

/**
 * @author xuhongtao
 * @fileName LoginPersenter
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/19/019 10:16
 */
public class LoginPersenter extends BasePersenter<LoginView> {

    private final LoginModel loginModel;

    public LoginPersenter(LoginView view) {
        loginModel = new LoginModel();
        super.getView(view);
    }

    public void loginPersenter(String phone, String pwd) {
        loginModel.loginModel(phone,pwd);
        loginModel.setOnLoginListener(new LoginModel.OnLoginListener() {
            @Override
            public void loginData(LoginBean loginBean) {
                view.loginData(loginBean);

            }
        });
    }

    public void removeSubscriber() {
        loginModel.removeSubscriber();
    }
}
