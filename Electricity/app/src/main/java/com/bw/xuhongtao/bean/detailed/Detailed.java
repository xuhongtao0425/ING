package com.bw.xuhongtao.bean.detailed;

/**
 * @author xuhongtao
 * @fileName Detailed    详情
 * @package com.bw.xuhongtao.bean
 * @date 2019/3/19/019 20:10
 */
public class Detailed {
    Result result;

    @Override
    public String toString() {
        return "Detailed{" +
                "result=" + result +
                '}';
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
