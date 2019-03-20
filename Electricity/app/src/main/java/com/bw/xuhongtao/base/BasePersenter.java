package com.bw.xuhongtao.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author xuhongtao
 * @fileName BasePersenter
 * @package com.bw.xuhongtao.base
 * @date 2019/3/17/017 11:03
 */
public abstract class BasePersenter<T>{
    //声明
    private Reference<T> reference;

    //管理activity    通过弱引用管理
    public void attach(T t) {
        reference = new WeakReference<>(t);
    }

    //解除管理  防止内存泄漏
    public void detach() {
        if (reference.get() != null) {
            reference.clear();
            reference = null;
        }
    }


}
