package com.bw.xuhongtao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.xuhongtao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xuhongtao
 * @fileName Search
 * @package com.bw.xuhongtao.widget
 * @date 2019/3/20/020 19:32
 */
public class Search extends RelativeLayout {
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.sousuo)
    TextView sousuo;
    @BindView(R.id.san)
    ImageView san;

    public Search(Context context) {
        super(context);
        getinit(context);
    }

    public Search(Context context, AttributeSet attrs) {
        super(context, attrs);
        getinit(context);
    }

    public Search(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getinit(context);
    }

    private void getinit(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.searchbox, null, false);
        addView(view);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sousuo)
    public void onViewClicked() {
        String goodName = search.getText().toString();
        if (onSouSuo != null) {
            onSouSuo.getData(goodName);
        }
    }

    @OnClick(R.id.san)
    public void onsanClicked() {
       if(ontwoList!=null){
           ontwoList.getData();
       }
    }


    //搜索框接口回调
    public interface OnSouSuo {
        void getData(String goodName);
    }

    private OnSouSuo onSouSuo;

    public void setOnSouSuo(OnSouSuo onSouSuo) {
        this.onSouSuo = onSouSuo;
    }

    //列表接口回调
    public interface OnTwoList {
        void getData();
    }

    private OnTwoList ontwoList;

    public void setOnTwoList(OnTwoList ontwoList) {
        this.ontwoList = ontwoList;
    }
}
