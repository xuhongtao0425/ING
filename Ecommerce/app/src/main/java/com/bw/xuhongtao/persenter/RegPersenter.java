package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.regbean.RegBean;
import com.bw.xuhongtao.model.RegModel;
import com.bw.xuhongtao.view.RegView;

/**
 * @author xuhongtao
 * @fileName RegPersenter
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/20/020 13:50
 */
public class RegPersenter extends BasePersenter<RegView> {

    private final RegModel regModel;

    public RegPersenter(RegView view) {
        regModel = new RegModel();
        super.getView(view);

    }

    public void regPersenter(String phone, String pwd) {
        //关联
        regModel.regModel(phone,pwd);
        //接收时间
        regModel.setOnRegListener(new RegModel.OnRegListener() {
            @Override
            public void regData(RegBean regBean) {
                view.regData(regBean);

            }
        });

    }

    public void removeSubscriber() {
        regModel.removeSubscriber();
    }
}
