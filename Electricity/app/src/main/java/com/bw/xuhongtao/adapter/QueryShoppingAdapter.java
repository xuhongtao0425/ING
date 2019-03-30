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
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;
import com.bw.xuhongtao.widget.AddMinus;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName QueryShoppingAdapter
 * @package com.bw.xuhongtao.adapter    查询购物车的适配器
 * @date 2019/3/28/028 16:07
 */
public class QueryShoppingAdapter extends RecyclerView.Adapter {
    Context context;
    List<QueryShopping.Result> result;

    public QueryShoppingAdapter(Context context, List<QueryShopping.Result> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.queryshoppingitem, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder)viewHolder).checkboxQuery.setChecked(result.get(i).isChecked());
        ((ViewHolder)viewHolder).sdvQuery.setImageURI(result.get(i).getPic());
        ((ViewHolder) viewHolder).titleQuery.setText(result.get(i).getCommodityName());
        ((ViewHolder) viewHolder).priceQuery.setText("¥:"+result.get(i).getPrice());

        //加减器中传输局
        ((ViewHolder) viewHolder).addminusQuery.getconut(result,i,this);
        //选中
        ((ViewHolder) viewHolder).checkboxQuery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                result.get(i).setChecked(isChecked);
                if(onsumListener!=null){
                    onsumListener.getsum(result);
                }
            }
        });
        //加减器的回调数据
        ((ViewHolder) viewHolder).addminusQuery.setOnAddMinusClicked(new AddMinus.OnAddMinusClicked() {
            @Override
            public void getAddMinus() {
                if(onsumListener!=null){
                    onsumListener.getsum(result);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.checkbox_query)
        CheckBox checkboxQuery;
        @BindView(R.id.sdv_query)
        SimpleDraweeView sdvQuery;
        @BindView(R.id.title_query)
        TextView titleQuery;
        @BindView(R.id.price_query)
        TextView priceQuery;
        @BindView(R.id.addminus_query)
       AddMinus addminusQuery;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             ButterKnife.bind(this, itemView);
         }
    }
    //总价的接口回调
    public interface OnsumListener{
        void getsum( List<QueryShopping.Result> result);
    }
    private OnsumListener onsumListener;

    public void setOnsumListener(OnsumListener onsumListener) {
        this.onsumListener = onsumListener;
    }
}
