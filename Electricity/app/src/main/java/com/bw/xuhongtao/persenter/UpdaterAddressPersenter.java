package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.model.UpdaterAddressModel;
import com.bw.xuhongtao.view.UpdaterAddressView;

/**
 * @author xuhongtao
 * @fileName UpdaterAddressPersenter   修改地址
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/29/029 19:41
 */
public class UpdaterAddressPersenter extends BasePersenter<UpdaterAddressView> {

    private final UpdaterAddressModel updaterAddressModel;

    public UpdaterAddressPersenter(UpdaterAddressView updaterAddressView) {
        updaterAddressModel = new UpdaterAddressModel();
        super.getView(updaterAddressView);
    }

    public void upadterAddress(int id, int userId, String sessionId) {
        updaterAddressModel.upadterAddress(id,userId,sessionId);
        updaterAddressModel.setOnUpdaterAddressModel(new UpdaterAddressModel.OnUpdaterAddressModel() {
            @Override
            public void getUpdaterAddressModel(Addshopping addshopping) {
                view.getUpdaterAddressModel(addshopping);

            }
        });
    }
}
