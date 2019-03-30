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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName PzshAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/21/021 13:41
 */
public class PzshAdapter extends RecyclerView.Adapter {
    public Context context;
    public List<HomeBean.ResultEntity.PzshEntity.CommodityListEntity> commodityList;

    public PzshAdapter(Context context, List<HomeBean.ResultEntity.PzshEntity.CommodityListEntity> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pzsh_item, null, false);
        MyPzshHiewHolder myPzshHiewHolder = new MyPzshHiewHolder(view);
        return myPzshHiewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((MyPzshHiewHolder)viewHolder).pzshName.setText(commodityList.get(i).getCommodityName());
        ((MyPzshHiewHolder) viewHolder).pzshPrice.setText("¥:"+commodityList.get(i).price);
        ((MyPzshHiewHolder) viewHolder).pzshImg.setImageURI(commodityList.get(i).masterPic);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("id",commodityList.get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class MyPzshHiewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pzsh_img)
        SimpleDraweeView pzshImg;
        @BindView(R.id.pzsh_name)
        TextView pzshName;
        @BindView(R.id.pzsh_price)
        TextView pzshPrice;
        public MyPzshHiewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
