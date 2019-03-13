package com.bw.xuhongtao.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bw.xuhongtao.R;

/**
 * @author xuhongtao
 * @fileName CustomSouSuo
 * @package com.bw.xuhongtao.view.widget
 * @date 2019/2/25/025 21:03
 */
public class CustomSouSuo extends RelativeLayout {
    private ImageView sousuo, mulv;
    private EditText good;

    public CustomSouSuo(Context context) {
        super(context);
    }

    public CustomSouSuo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public CustomSouSuo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.customsousuo, null, false);
        addView(view);
        mulv = view.findViewById(R.id.mulv);
        good = view.findViewById(R.id.good);
        sousuo = view.findViewById(R.id.sousuo);
        //监听
        mulv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onMulvListener.getData();
            }
        });
        sousuo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OnsousuoListener.getData();
            }
        });

    }

    //目录的回调
    public interface OnMulvListener {
        void getData();
    }

    private OnMulvListener onMulvListener;

    public void setOnMulvListener(OnMulvListener onMulvListener) {
        this.onMulvListener = onMulvListener;
    }
    //搜索的回调
    public interface OnSousuoListener {
        void getData();
    }

    private OnSousuoListener OnsousuoListener;

    public void setOnSousuoListener(OnSousuoListener OnsousuoListener) {
        this.OnsousuoListener = OnsousuoListener;
    }
}
