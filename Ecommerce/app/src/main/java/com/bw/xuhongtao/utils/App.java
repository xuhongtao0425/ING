package com.bw.xuhongtao.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author xuhongtao
 * @fileName App
 * @package com.bw.xuhongtao.utils
 * @date 2019/3/18/018 20:05
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
