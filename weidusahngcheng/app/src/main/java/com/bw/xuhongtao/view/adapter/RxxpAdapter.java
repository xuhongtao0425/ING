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
 * @fileName RxxpAdapter
 * @package com.bw.xuhongtao.view.adapter
 * @date 2019/3/3/003 14:58
 */
class RxxpAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ShouYeBean.ResultEntity.RxxpEntity.CommodityListEntity> rxxp;

    public RxxpAdapter(Context context, List<ShouYeBean.ResultEntity.RxxpEntity.CommodityListEntity> rxxp) {
        this.context = context;
        this.rxxp = rxxp;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rxxpitem, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder) viewHolder).name_rxxp.setText(rxxp.get(i).getCommodityName());
        ((ViewHolder) viewHolder).price_rxxp.setText("¥" + rxxp.get(i).getPrice() + "");
        Glide.with(context).load(rxxp.get(i).getMasterPic()).into(((ViewHolder) viewHolder).image_rxxp);
        //点击事件
        ((ViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRxxpListener!=null){
                    int id = rxxp.get(i).getCommodityId();
                    onRxxpListener.getRxxpData(id);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return rxxp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView price_rxxp, name_rxxp;
        private ImageView image_rxxp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_rxxp = itemView.findViewById(R.id.name_rxxp);
            price_rxxp = itemView.findViewById(R.id.price_rxxp);
            image_rxxp = itemView.findViewById(R.id.image_rxxp);

        }
    }

    //接口
    public interface OnRxxpListener {
        void getRxxpData(int id);
    }
    private OnRxxpListener onRxxpListener;

    public void setOnRxxpListener(OnRxxpListener onRxxpListener) {
        this.onRxxpListener = onRxxpListener;
    }
}
