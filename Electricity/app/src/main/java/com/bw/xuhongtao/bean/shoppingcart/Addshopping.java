package com.bw.xuhongtao.bean.shoppingcart;

/**
 * @author xuhongtao
 * @fileName Addshopping
 * @package com.bw.xuhongtao.bean.shoppingcart    添加地址和添加购物车   设置默认收货地址    创建订单
 * @date 2019/3/23/023 15:05
 */
public class Addshopping {
//    {"message":"请先登陆","status":"1001"}
    String message;
    String status;

    @Override
    public String toString() {
        return "Addshopping{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
