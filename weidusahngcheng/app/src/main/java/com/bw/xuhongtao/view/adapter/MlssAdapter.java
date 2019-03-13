package com.bw.xuhongtao.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.xuhongtao.R;
import com.bw.xuhongtao.model.bean.ShouYeBean;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName MlssAdapter
 * @package com.bw.xuhongtao.view.adapter
 * @date 2019/3/3/003 15:28
 */
class MlssAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ShouYeBean.ResultEntity.MlssEntity.CommodityListEntity> mlss;

    public MlssAdapter(Context context, List<ShouYeBean.ResultEntity.MlssEntity.CommodityListEntity> mlss) {
        this.context = context;
        this.mlss = mlss;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlssitem, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).name_mlss.setText(mlss.get(i).getCommodityName());
        ((ViewHolder) viewHolder).price_mlss.setText("¥"+mlss.get(i).getPrice()+"");
        Glide.with(context).load(mlss.get(i).getMasterPic()).into(((ViewHolder) viewHolder).image_mlss);

    }

    @Override
    public int getItemCount() {
        return mlss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView price_mlss, name_mlss;
        private ImageView image_mlss;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_mlss = itemView.findViewById(R.id.name_mlss);
            price_mlss = itemView.findViewById(R.id.price_mlss);
            image_mlss = itemView.findViewById(R.id.image_mlss);

        }
    }
}
