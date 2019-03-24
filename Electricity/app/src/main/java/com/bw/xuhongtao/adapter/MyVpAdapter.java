package com.bw.xuhongtao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author xuhongtao
 * @fileName MyVpAdapter
 * @package com.bw.xuhongtao.adapter
 * @date 2019/3/20/020 21:04
 */
public class MyVpAdapter extends PagerAdapter {
    Context context;
    List<ImageView> list;
    public MyVpAdapter(Context context, List<ImageView> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        position=position%list.size();
        ImageView imageView = list.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
