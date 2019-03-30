package com.bw.xuhongtao.persenter;

import com.bw.xuhongtao.base.BasePersenter;
import com.bw.xuhongtao.bean.detailed.Result;
import com.bw.xuhongtao.bean.shoppingcart.Addshopping;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.model.DetailedModel;
import com.bw.xuhongtao.view.AddshoppingView;
import com.bw.xuhongtao.view.DetailedView;
import com.bw.xuhongtao.view.QueryShoppingView;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName DetailsPersenter   详情
 * @package com.bw.xuhongtao.persenter
 * @date 2019/3/21/021 17:05
 */
public class DetailsPersenter extends BasePersenter<DetailedView> {
    private final DetailedModel detailedModel;
    private final QueryShoppingView qsView;
    private final AddshoppingView ahview;

    public DetailsPersenter(DetailedView view, QueryShoppingView queryShoppingView, AddshoppingView addshoppingView) {
        super.getView(view);
        qsView = queryShoppingView;
        ahview = addshoppingView;
        detailedModel = new DetailedModel();
    }
    public void getDetails(int id, int userId, String sessionId) {
        detailedModel.detailedModel(id,userId,sessionId);
        detailedModel.setOnDetailedModel(new DetailedModel.OnDetailedModel() {
            @Override
            public void getData(Result result) {
                view.getData(result);

            }
        });

    }
    //加入购物车
    public void addShoping(int userId, String sessionId,String json) {
        detailedModel.addShoping(userId,sessionId,json);
        detailedModel.setOnAddshopping(new DetailedModel.OnAddshopping() {
            @Override
            public void getData(Addshopping addshopping) {
                ahview.getAddshoppingData(addshopping);

            }
        });
    }
    public void removeSubscriber() {
        detailedModel.removeSubscriber();
    }

    //查询购物车
    public void queryShoping(int userId, String sessionId) {
        detailedModel.queryShoping(userId,sessionId);
        detailedModel.setOnQueryShopping(new DetailedModel.OnQueryShopping() {
            @Override
            public void getData(List<QueryShopping.Result> result) {
                qsView.getData(result);

            }
        });
    }
}
