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
 * @fileName MlssAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/21/021 14:19
 */
class MlssAdapter extends RecyclerView.Adapter {
    Context context;
    List<HomeBean.ResultEntity.MlssEntity.CommodityListEntity> mlsslist;
    public MlssAdapter(Context context, List<HomeBean.ResultEntity.MlssEntity.CommodityListEntity> mlsslist) {
        this.context=context;
        this.mlsslist=mlsslist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlss_item, null, false);
        return new MlssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((MlssViewHolder) viewHolder).name_mlss.setText(mlsslist.get(i).getCommodityName());
        ((MlssViewHolder) viewHolder).price_mlss.setText("¥"+mlsslist.get(i).getPrice()+"");
        ((MlssViewHolder) viewHolder).image_mlss.setImageURI(mlsslist.get(i).getMasterPic());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id",mlsslist.get(i).getCommodityId());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlsslist.size();
    }
    public class MlssViewHolder extends RecyclerView.ViewHolder {
        private TextView price_mlss, name_mlss;
        private SimpleDraweeView image_mlss;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            name_mlss = itemView.findViewById(R.id.name_mlss);
            price_mlss = itemView.findViewById(R.id.price_mlss);
            image_mlss = itemView.findViewById(R.id.image_mlss);

        }
    }
}
