package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.model.QueryShoppingModel;
import com.bw.xuhongtao.view.QueryShoppingView;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName QueryShoppingPersenter    查询购物车
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/27/027 20:54
 */
public class QueryShoppingPersenter extends BasePersenter<QueryShoppingView> {

    private  QueryShoppingModel queryShoppingModel;

    public QueryShoppingPersenter(QueryShoppingView queryShoppingView) {
        queryShoppingModel = new QueryShoppingModel();
        super.getView(queryShoppingView);
    }

    public  void queryShopping(int userId, String sessionId) {
        queryShoppingModel.queryShopping(userId,sessionId);
        queryShoppingModel.setOnQueryShopping(new QueryShoppingModel.OnQueryShopping() {
            @Override
            public void getData(List<QueryShopping.Result> result) {
                view.getData(result);
            }
        });

    }
}
