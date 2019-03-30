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
import com.bw.xuhongtao.bean.home.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName RxxpAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/21/021 11:26
 */
public class RxxpAdapter extends RecyclerView.Adapter {
    Context context;
    List<HomeBean.ResultEntity.RxxpEntity.CommodityListEntity> rxxplist;
    public RxxpAdapter(Context context, List<HomeBean.ResultEntity.RxxpEntity.CommodityListEntity> rxxplist) {
        this.context=context;
        this.rxxplist=rxxplist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rxxp_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder) viewHolder).name_rxxp.setText(rxxplist.get(i).getCommodityName());
        ((ViewHolder) viewHolder).price_rxxp.setText("¥" + rxxplist.get(i).getPrice() + "");
        ((ViewHolder) viewHolder).image_rxxp.setImageURI(rxxplist.get(i).getMasterPic());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id",rxxplist.get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rxxplist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView price_rxxp, name_rxxp;
        private SimpleDraweeView image_rxxp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_rxxp = itemView.findViewById(R.id.name_rxxp);
            price_rxxp = itemView.findViewById(R.id.price_rxxp);
            image_rxxp = itemView.findViewById(R.id.image_rxxp);

        }
    }
}
