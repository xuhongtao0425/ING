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
 * @fileName PzshAdapter
 * @package com.bw.xuhongtao.view.adapter
 * @date 2019/3/3/003 15:33
 */
class PzshAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ShouYeBean.ResultEntity.PzshEntity.CommodityListEntity> pzsh;

    public PzshAdapter(Context context, List<ShouYeBean.ResultEntity.PzshEntity.CommodityListEntity> pzsh) {
        this.context = context;
        this.pzsh = pzsh;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.pzshitem, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).name_pzsh.setText(pzsh.get(i).getCommodityName());
        ((ViewHolder) viewHolder).price_pzsh.setText("¥"+pzsh.get(i).getPrice()+"");
        Glide.with(context).load(pzsh.get(i).getMasterPic()).into(((ViewHolder) viewHolder).image_pzsh);
    }

    @Override
    public int getItemCount() {
        return pzsh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name_pzsh, price_pzsh;
        private ImageView image_pzsh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_pzsh = itemView.findViewById(R.id.name_pzsh);
            price_pzsh = itemView.findViewById(R.id.price_pzsh);
            image_pzsh = itemView.findViewById(R.id.image_pzsh);

        }
    }
}
