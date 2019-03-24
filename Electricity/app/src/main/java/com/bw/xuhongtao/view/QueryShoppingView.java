package com.bw.xuhongtao.view;

import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName QueryShoppingView
 * @package com.bw.xuhongtao.view   查询购物车
 * @date 2019/3/23/023 16:30
 */
public interface QueryShoppingView  {
    void getData(List<QueryShopping.Result> result);

}
