package com.bw.xuhongtao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.xuhongtao.R;
import com.bw.xuhongtao.adapter.QueryShoppingAdapter;
import com.bw.xuhongtao.bean.shoppingcart.QueryShopping;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xuhongtao
 * @fileName AddMinus
 * @package com.bw.xuhongtao.widget    加减器
 * @date 2019/3/28/028 16:09
 */
public class AddMinus extends RelativeLayout {
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.num)
    EditText num;
    @BindView(R.id.del)
    Button del;
    Context mcontext;
    //下标
    int i=0;
    //数量
    int count;
    //集合
    List<QueryShopping.Result> result;
    //查询购物车适配器
    QueryShoppingAdapter queryShoppingAdapter;
    public AddMinus(Context context) {
        super(context);
    }

    public AddMinus(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AddMinus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context) {
        mcontext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.addminus, null);
        ButterKnife.bind(this,inflate);
        addView(inflate);


    }

    @OnClick({R.id.add, R.id.del})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                String n = num.getText().toString().trim();
                int i = Integer.parseInt(n);
                i++;
                num.setText(""+i);
                //获取当前的对象
                QueryShopping.Result result = this.result.get(this.i);
                //设置数量
                result.setCount(i);
                //刷新适配器
                queryShoppingAdapter.notifyDataSetChanged();
                //回调
                if(onAddMinusClicked!=null){
                    onAddMinusClicked.getAddMinus();
                }
                break;
            case R.id.del:
                String s = num.getText().toString().trim();
                int j = Integer.parseInt(s);
                if (s.equals("0")) {
                    Toast.makeText(mcontext, "停,停,停......", Toast.LENGTH_SHORT).show();

                } else {
                    j--;
                    num.setText(""+j);
                    QueryShopping.Result result1 = this.result.get(this.i);
                    result1.setCount(j);
                    queryShoppingAdapter.notifyDataSetChanged();
                    if(onAddMinusClicked!=null){
                        onAddMinusClicked.getAddMinus();
                    }
                }
                break;
        }
    }

    //获取适配器的数据    参数  1.适配器集合   2.当前下标   3.适配器
    public void getconut(List<QueryShopping.Result> result,int n,  QueryShoppingAdapter queryShoppingAdapter) {
        Log.i("zzzz",n+"");
        this.queryShoppingAdapter=queryShoppingAdapter;
        this.result=result;
        this.i=n;
     this.count=result.get(n).getCount();
        this.num.setText(""+this.count);
    }
    //加减器的接口回调
    public interface OnAddMinusClicked{
        void  getAddMinus();
    }
    private OnAddMinusClicked onAddMinusClicked;

    public void setOnAddMinusClicked(OnAddMinusClicked onAddMinusClicked) {
        this.onAddMinusClicked = onAddMinusClicked;
    }
}
