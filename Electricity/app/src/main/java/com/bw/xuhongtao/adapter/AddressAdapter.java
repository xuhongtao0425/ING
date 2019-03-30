package com.bw.xuhongtao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.bean.queryshippingaddress.QueryAddress;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author xuhongtao
 * @fileName AddressAdapter
 * @package com.bw.xuhongtao.adapter   查询购物车
 * @date 2019/3/29/029 17:01
 */
public class AddressAdapter extends RecyclerView.Adapter {
    List<QueryAddress.ResultEntity> result;
    Context context;

    public AddressAdapter(List<QueryAddress.ResultEntity> result, Context context) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.addressitem, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        ((ViewHolder) viewHolder).nameAddressitem.setText(result.get(i).getRealName());
        ((ViewHolder) viewHolder).phoneAddress.setText(result.get(i).getPhone());
        ((ViewHolder) viewHolder).cargoAddress.setText(result.get(i).getAddress());
        if (result.get(i).getWhetherDefault() == 1) {
            ((ViewHolder) viewHolder).defaultaddress.setChecked(true);
        }
        ((ViewHolder) viewHolder).defaultaddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ((ViewHolder) viewHolder).defaultaddress.setChecked(isChecked);
                int id = result.get(i).getId();
                if(isChecked){
                   if(onUpdater!=null){
                       onUpdater.getUpdater(id);
                   }
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_addressitem)
        TextView nameAddressitem;//名字
        @BindView(R.id.phone_address)
        TextView phoneAddress;//手机号
        @BindView(R.id.cargo_address)
        TextView cargoAddress;//地址
        @BindView(R.id.defaultaddress)
        CheckBox defaultaddress;//默认
        @BindView(R.id.updete_address)
        Button updeteAddress;//修改
        @BindView(R.id.delete_address)
        Button deleteAddress;//删除

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface OnUpdater{
        void getUpdater(int id);
    }
    private OnUpdater onUpdater;

    public void setOnUpdater(OnUpdater onUpdater) {
        this.onUpdater = onUpdater;
    }
}
