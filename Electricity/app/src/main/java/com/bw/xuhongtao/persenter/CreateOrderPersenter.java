package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.model.CreateOrderModel;
import com.bw.xuhongtao.view.CreateOrderView;

/**
 * @author xuhongtao
 * @fileName CreateOrderPersenter
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/29/029 22:05
 */
public class CreateOrderPersenter extends BasePersenter<CreateOrderView> {

    private final CreateOrderModel createOrderModel;

    public CreateOrderPersenter(CreateOrderView createOrderView) {
        createOrderModel = new CreateOrderModel();
        super.getView(createOrderView);
    }

    public void createOrder(String sessionId, int userId, double sum, int id, String json) {
        createOrderModel.createOrder(sessionId,userId,sum,id,json);
        createOrderModel.setOnCreateOrder(new CreateOrderModel.OnCreateOrder() {
            @Override
            public void getCreateOrder(Addshopping addshopping) {
                view.getCreateOrder(addshopping);
            }
        });

    }
}
