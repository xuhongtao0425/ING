package com.bw.xuhongtao.view;

import com.bw.xuhongtao.bean.FirstCategory.UserWallet;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName UserWalletView
 * @package com.bw.xuhongtao.view
 * @date 2019/3/22/022 19:28
 */
public interface UserWalletView {
    void userWalletData(List<UserWallet.ResultEntity> result);
}
