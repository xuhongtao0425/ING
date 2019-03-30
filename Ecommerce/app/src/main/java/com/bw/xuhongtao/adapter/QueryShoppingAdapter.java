package com.bw.xuhongtao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName QueryShoppingAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/26/026 19:35
 */
public class QueryShoppingAdapter extends RecyclerView.Adapter {
    Context context;
    List<QueryShopping.Result> result;
    public QueryShoppingAdapter(Context context, List<QueryShopping.Result> result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
