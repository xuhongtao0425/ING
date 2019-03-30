package com.bw.xuhongtao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.bean.home.Bean;
import com.bw.xuhongtao.bean.home.HomeBean;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName HomeAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/21/021 11:02
 */
public class HomeAdapter extends RecyclerView.Adapter {
    Context context;
    Bean bean;
    int TYPE = 0;
    int TYPE1 = 1;
    int TYPE2 = 2;
    public HomeAdapter(Context context, Bean bean) {
        this.context=context;
        this.bean=bean;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE;
        }else  if(position==1){
            return TYPE1;
        }else{
            return TYPE2;
        }

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(i==TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.layout_rxxp, null, false);
            return new RxxpViewHolder(view);
        }else if(i==TYPE1){
            View view = LayoutInflater.from(context).inflate(R.layout.layout_mssl, null, false);
            return new MlssViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.layout_pzsh, null, false);
            return new PzshViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        HomeBean.ResultEntity.MlssEntity mlss = bean.getMlss();
        HomeBean.ResultEntity.PzshEntity pzsh = bean.getPzsh();
        HomeBean.ResultEntity.RxxpEntity rxxp = bean.getRxxp();
        if(type==TYPE){
            ((RxxpViewHolder)viewHolder).title_rxxp.setText(rxxp.getName());
            //布局管理器    水平方向
            ((RxxpViewHolder) viewHolder).rlv_rxxp.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            //获取集合数据
            List<HomeBean.ResultEntity.RxxpEntity.CommodityListEntity> rxxplist = rxxp.getCommodityList();
            //适配器
            RxxpAdapter adapter=new RxxpAdapter(context,rxxplist);
            ((RxxpViewHolder) viewHolder).rlv_rxxp.setAdapter(adapter);

        }else if (type==TYPE1){
            //赋值标题
            ((MlssViewHolder)viewHolder).title_mlss.setText(mlss.getName());
            //获取集合数据
            List<HomeBean.ResultEntity.MlssEntity.CommodityListEntity> mlsslist = mlss.getCommodityList();
            //设置布局管理器
            ((MlssViewHolder) viewHolder).rlv_mlss.setLayoutManager(new LinearLayoutManager(context));
            //适配器
            ((MlssViewHolder) viewHolder).rlv_mlss.setAdapter(new MlssAdapter(context,mlsslist));
        }else{
            ((PzshViewHolder) viewHolder).title_pzsh.setText(pzsh.getName());
            //获取集合数据
            List<HomeBean.ResultEntity.PzshEntity.CommodityListEntity> commodityList = pzsh.getCommodityList();
            //布局管理器
            ((PzshViewHolder) viewHolder).rlv_pzsh.setLayoutManager(new GridLayoutManager(context,2));
            ((PzshViewHolder) viewHolder).rlv_pzsh.setAdapter(new PzshAdapter(context,commodityList));
        }


    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public class RxxpViewHolder extends RecyclerView.ViewHolder {


        private  RecyclerView rlv_rxxp;
        private  TextView title_rxxp;
        private  ImageView gengduo_rxxp;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            rlv_rxxp = itemView.findViewById(R.id.rlv_rxxp);
            title_rxxp = itemView.findViewById(R.id.title_rxxp);
            gengduo_rxxp = itemView.findViewById(R.id.gengduo_rxxp);

        }
    }

    public class MlssViewHolder extends RecyclerView.ViewHolder {

        private  RecyclerView rlv_mlss;
        private  TextView title_mlss;
        private  ImageView gengduo_mlss;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            rlv_mlss = itemView.findViewById(R.id.rlv_mlss);
            title_mlss = itemView.findViewById(R.id.title_mlss);
            gengduo_mlss = itemView.findViewById(R.id.gengduo_mlss);
        }
    }

    public class PzshViewHolder extends RecyclerView.ViewHolder {
        private  RecyclerView rlv_pzsh;
        private TextView title_pzsh;
        private ImageView gengduo_pzsh;
        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            rlv_pzsh = itemView.findViewById(R.id.rlv_pzsh);
            title_pzsh = itemView.findViewById(R.id.title_pzsh);
            gengduo_pzsh = itemView.findViewById(R.id.gengduo_pzsh);
        }
    }
}
