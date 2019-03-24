package com.bw.xuhongtao.view;

import com.bw.xuhongtao.bean.search.SearchBean;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName SearchView
 * @package com.bw.xuhongtao.view
 * @date 2019/3/21/021 15:31
 */
public interface DataView {
    void getData(List<SearchBean.ResultEntity> result);
}
