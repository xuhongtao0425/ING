package com.bw.xuhongtao.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.model.bean.ShouYeBean;
import com.bw.xuhongtao.model.bean.ShouYeBeans;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName ShouYeAdapter
 * @package com.bw.xuhongtao.view.adapter
 * @date 2019/3/3/003 14:11
 */
public class ShouYeAdapter extends RecyclerView.Adapter {
  private   Context context;
    private  List<ShouYeBeans> list;
    private static final int TYPE=0;
    private static final int TYPE1=1;
    private static final int TYPE2=2;


    public ShouYeAdapter(Context context, List<ShouYeBeans> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        Log.i("position",position+"");
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
           View view = LayoutInflater.from(context).inflate(R.layout.rxxp, null, false);
           return new ViewHolder(view);
       }else if (i==TYPE1){
           View view1 = LayoutInflater.from(context).inflate(R.layout.mlss, null, false);
           return new ViewHolder1(view1);
       }else{
           View view2 = LayoutInflater.from(context).inflate(R.layout.pzsh, null, false);
           return new ViewHolder2(view2);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if(type==TYPE){
            ((ViewHolder)viewHolder).title_rxxp.setText(list.get(0).getRxxp().getName());
            //布局管理器
            LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            //设置布局管理器
            ((ViewHolder)viewHolder).rlv_rxxp.setLayoutManager(layoutManager);
            //获取热销新品数据
            List<ShouYeBean.ResultEntity.RxxpEntity.CommodityListEntity> rxxp = list.get(0).getRxxp().getCommodityList();
            RxxpAdapter rxxpAdapter = new RxxpAdapter(context, rxxp);
            ((ViewHolder)viewHolder).rlv_rxxp.setAdapter(rxxpAdapter);
            //回调数据
            rxxpAdapter.setOnRxxpListener(new RxxpAdapter.OnRxxpListener() {
                @Override
                public void getRxxpData(int id) {
                    if(onShouYeListener!=null){
                        onShouYeListener.getShouYeData(id);
                    }
                }
            });
        }else if(type==TYPE1){
            ((ViewHolder1)viewHolder).title_mlss.setText(list.get(0).getMlss().getName());
            //布局管理器
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            //设置布局管理器
            ((ViewHolder1)viewHolder).rlv_mlss.setLayoutManager(layoutManager);
            //获取热销新品数据
            List<ShouYeBean.ResultEntity.MlssEntity.CommodityListEntity> mlss = list.get(0).getMlss().getCommodityList();
            ((ViewHolder1)viewHolder).rlv_mlss.setAdapter(new MlssAdapter(context,mlss));
        }else if(type==TYPE2){
            ((ViewHolder2)viewHolder).title_pzsh.setText(list.get(0).getPzsh().getName());
            //布局管理器
            GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
            //设置布局管理器
            ((ViewHolder2)viewHolder).rlv_pzsh.setLayoutManager(gridLayoutManager);
            //获取热销新品数据
            List<ShouYeBean.ResultEntity.PzshEntity.CommodityListEntity> pzsh = list.get(0).getPzsh().getCommodityList();
            ((ViewHolder2)viewHolder).rlv_pzsh.setAdapter(new PzshAdapter(context,pzsh));
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private  RecyclerView rlv_rxxp;
        private  TextView title_rxxp;
        private  ImageView gengduo_rxxp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rlv_rxxp = itemView.findViewById(R.id.rlv_rxxp);
            title_rxxp = itemView.findViewById(R.id.title_rxxp);
            gengduo_rxxp = itemView.findViewById(R.id.gengduo_rxxp);

        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private  RecyclerView rlv_mlss;
        private  TextView title_mlss;
        private  ImageView gengduo_mlss;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            rlv_mlss = itemView.findViewById(R.id.rlv_mlss);
            title_mlss = itemView.findViewById(R.id.title_mlss);
            gengduo_mlss = itemView.findViewById(R.id.gengduo_mlss);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private  RecyclerView rlv_pzsh;
        private  TextView title_pzsh;
        private  ImageView gengduo_pzsh;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            rlv_pzsh = itemView.findViewById(R.id.rlv_pzsh);
            title_pzsh = itemView.findViewById(R.id.title_pzsh);
            gengduo_pzsh = itemView.findViewById(R.id.gengduo_pzsh);
        }
    }
    public interface OnShouYeListener {
        void getShouYeData(int id);

    }
    private OnShouYeListener onShouYeListener;

    public void setOnShouYeListener(OnShouYeListener onShouYeListener) {
        this.onShouYeListener = onShouYeListener;
    }
}
