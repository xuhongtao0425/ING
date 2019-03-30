package com.bw.xuhongtao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.activity.ConfirmOrderActivity;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.widget.AddMinus;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName QueryAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/29/029 21:41
 */
public class QueryAdapter extends RecyclerView.Adapter {
    Context context;
    List<QueryShopping.Result> result;

    public QueryAdapter(Context context, List<QueryShopping.Result> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.queryitem, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ((ViewHolder)viewHolder).sdvQuery.setImageURI(result.get(i).getPic());
        ((ViewHolder) viewHolder).titleQuery.setText(result.get(i).getCommodityName());
        ((ViewHolder) viewHolder).priceQuery.setText("¥:"+result.get(i).getPrice());




    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.sdv_query)
        SimpleDraweeView sdvQuery;
        @BindView(R.id.title_query)
        TextView titleQuery;
        @BindView(R.id.price_query)
        TextView priceQuery;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
