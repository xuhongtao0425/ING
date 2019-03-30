package com.bw.xuhongtao.persenter;

import android.util.Log;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.model.AddressModel;
import com.bw.xuhongtao.view.AddressView;

/**
 * @author xuhongtao
 * @fileName AddressPersenter
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/29/029 14:25     新增地址
 */
public class AddressPersenter extends BasePersenter<AddressView> {

    private final AddressModel addressModel;

    public AddressPersenter(AddressView addressView)
    {
        addressModel = new AddressModel();
        super.getView(addressView);
    }

    public void addressData(int userId, String sessionId, String name, String phone, String address, String zipCode) {
        addressModel.addressData(userId,sessionId,name,phone,address,zipCode);
        addressModel.setOnAddressModel(new AddressModel.OnAddressModel() {
            @Override
            public void AddressModelData(Addshopping addshopping) {
                view.AddressModelData(addshopping);

            }
        });
    }


    public void detach() {

        addressModel.clear();
        Log.i("vvvvvv","走了");
    }
}
