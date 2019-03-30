package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.model.QueryShoppingModel;
import com.bw.xuhongtao.view.QueryView;

/**
 * @author xuhongtao
 * @fileName QueryShoppingPersenter
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/26/026 13:50
 */
public class QueryShoppingPersenter extends BasePersenter<QueryView> {

    private final QueryShoppingModel queryShoppingModel;

    public QueryShoppingPersenter(QueryView view) {
        queryShoppingModel = new QueryShoppingModel();
        super.getView(view);
    }

    public void queryShopping(int userId, String sessionId) {
        queryShoppingModel.queryShopping( userId, sessionId);
        queryShoppingModel.setOnQueryShoppingModel(new QueryShoppingModel.OnQueryShoppingModel() {
            @Override
            public void getQueryShoppingData(QueryShopping queryShopping) {
                view.getQueryShoppingData(queryShopping);
            }
        });
    }
}
