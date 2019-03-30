package com.bw.xuhongtao.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.activity.DetailsActivity;
import com.bw.xuhongtao.bean.search.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xuhongtao     搜索框
 * @fileName SearchAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/21/021 16:02
 */
public class SearchAdapter extends RecyclerView.Adapter {
    Context context;
    List<SearchBean.ResultEntity> result;
    public SearchAdapter(Context context, List<SearchBean.ResultEntity> result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchitem, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder) viewHolder).titleShowitem.setText(result.get(i).getCommodityName());
        ((ViewHolder) viewHolder).priceShowitem.setText("¥:" + result.get(i).getPrice());
        ((ViewHolder) viewHolder).imageShowitem.setImageURI(result.get(i).getMasterPic());
        final int id = result.get(i).getCommodityId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id",result.get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_showitem)
        SimpleDraweeView imageShowitem;
        @BindView(R.id.title_showitem)
        TextView titleShowitem;
        @BindView(R.id.price_showitem)
        TextView priceShowitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
