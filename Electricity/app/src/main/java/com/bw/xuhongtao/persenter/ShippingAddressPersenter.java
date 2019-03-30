package com.bw.xuhongtao.persenter;

import android.util.Log;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.queryshippingaddress.QueryAddress;
import com.bw.xuhongtao.model.ShippingAddressModel;
import com.bw.xuhongtao.view.ShippingAddressView;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName ShippingAddressPersenter   查询地址
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/29/029 15:50
 */
public class ShippingAddressPersenter extends BasePersenter<ShippingAddressView> {

    private final ShippingAddressModel shippingAddressModel;

    public ShippingAddressPersenter(ShippingAddressView shippingAddressView) {
        shippingAddressModel = new ShippingAddressModel();
        super.getView(shippingAddressView);
    }

    public void queryShippingAddress(int userId, String sessionId) {
        shippingAddressModel.queryShippingAddress(userId,sessionId);
        shippingAddressModel.setOnShippingAddressModel(new ShippingAddressModel.OnShippingAddressModel() {
            @Override
            public void getShippingAddressModel(List<QueryAddress.ResultEntity> result) {
                view.getShippingAddressModel(result);

            }
        });

    }
    public void detach() {

        shippingAddressModel.clear();
        Log.i("vvvvvv","走了");
    }
}
