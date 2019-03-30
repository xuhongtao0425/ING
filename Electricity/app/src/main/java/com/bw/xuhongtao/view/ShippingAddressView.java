package com.bw.xuhongtao.view;

import com.bw.xuhongtao.bean.queryshippingaddress.QueryAddress;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName ShippingAddressView
 * @package com.bw.xuhongtao.view
 * @date 2019/3/29/029 16:50
 */
public interface ShippingAddressView {
    void getShippingAddressModel(List<QueryAddress.ResultEntity> result);
}
