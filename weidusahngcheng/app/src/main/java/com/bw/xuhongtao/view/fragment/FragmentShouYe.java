package com.bw.xuhongtao.view.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.xuhongtao.R;
import com.bw.xuhongtao.model.bean.ShouYeBeans;
import com.bw.xuhongtao.perenter.ShouYePersenter;
import com.bw.xuhongtao.perenter.ViewPagerPersenter;
import com.bw.xuhongtao.utils.ScaleAlphaPageTransformer;
import com.bw.xuhongtao.view.ShouYeView;
import com.bw.xuhongtao.view.ViewPagerView;
import com.bw.xuhongtao.view.activity.XiangQingActivity;
import com.bw.xuhongtao.view.adapter.MyVpAdapter;
import com.bw.xuhongtao.view.adapter.ShouYeAdapter;
import com.bw.xuhongtao.view.base.BaseFragment;
import com.bw.xuhongtao.view.widget.CustomSouSuo;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhongtao
 * @fileName FragmentShouYe
 * @package com.bw.xuhongtao.view.fragment
 * @date 2019/2/17/017 13:36
 */
public class FragmentShouYe extends BaseFragment implements ViewPagerView, ShouYeView {

    private CustomSouSuo css;
    private ViewPager vp;
    private ViewPagerPersenter persenter;
    private RecyclerView rlv_shouye;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    int currentItem = vp.getCurrentItem() + 1;
                    vp.setCurrentItem(currentItem);
                    handler.sendEmptyMessageDelayed(0, 2000);
                    break;
            }
        }
    };
    private ShouYePersenter syp;


    @Override
    protected int layoutResID() {
        return R.layout.fragmentshouye;
    }

    @Override
    protected void initView(View view) {
        //控件
        css = view.findViewById(R.id.css);
        vp = view.findViewById(R.id.vp_shouye);
        rlv_shouye = view.findViewById(R.id.rlv_shouye);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rlv_shouye.setLayoutManager(layoutManager);
        //实例化
        persenter = new ViewPagerPersenter(this);
        syp = new ShouYePersenter(this);

    }

    @Override
    protected void initData() {
        //自定义回调数据
        onListener();
        //调用viewpager工具类
        ScaleAlphaPageTransformer mScaleAlphaPageTransformer = new ScaleAlphaPageTransformer();
        mScaleAlphaPageTransformer.setType(true, true);
        vp.setPageTransformer(true, mScaleAlphaPageTransformer);
        vp.setCurrentItem(3);

//        管理
        persenter.attachData(this);
        syp.attachData(this);
        //关联
        persenter.shouyePersenter();
        syp.shouyePersenter();

    }

    private void onListener() {
        //目录
        css.setOnMulvListener(new CustomSouSuo.OnMulvListener() {
            @Override
            public void getData() {

            }
        });
        //搜索
        css.setOnSousuoListener(new CustomSouSuo.OnSousuoListener() {
            @Override
            public void getData() {

            }
        });
    }

    //轮播数据
    @Override
    public void getData(JSONArray result) {
        List<ImageView> list = new ArrayList<>();
        for (int i = 0; i < result.length(); i++) {
            ImageView imageView = new ImageView(getActivity());
            try {
                Glide.with(getActivity()).load(result.getJSONObject(i).getString("imageUrl")).into(imageView);
                list.add(imageView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        vp.setAdapter(new MyVpAdapter(getActivity(), list));
        //轮播
        handler.sendEmptyMessageDelayed(0, 200);
    }
    //首页数据
    @Override
    public void getShouYeModel(List<ShouYeBeans> list) {
        ShouYeAdapter shouYeAdapter = new ShouYeAdapter(getActivity(), list);
        rlv_shouye.setAdapter(shouYeAdapter);
        //回调数据
        shouYeAdapter.setOnShouYeListener(new ShouYeAdapter.OnShouYeListener() {
            @Override
            public void getShouYeData(int id) {
//                Toast.makeText(getActivity(), id+"", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),XiangQingActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        persenter.dataachData();
        syp.dataachData();

        handler.removeCallbacksAndMessages(null);
    }


}
