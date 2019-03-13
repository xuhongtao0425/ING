package com.bw.xuhongtao.model.bean;

/**
 * @author xuhongtao
 * @fileName ShouYeBeans
 * @package com.bw.xuhongtao.model.bean
 * @date 2019/3/3/003 13:55
 */
public class ShouYeBeans {
    ShouYeBean.ResultEntity.MlssEntity mlss;
    ShouYeBean.ResultEntity.PzshEntity pzsh;
    ShouYeBean.ResultEntity.RxxpEntity rxxp;

    @Override
    public String toString() {
        return "ShouYeBeans{" +
                "mlss=" + mlss +
                ", pzsh=" + pzsh +
                ", rxxp=" + rxxp +
                '}';
    }

    public ShouYeBean.ResultEntity.MlssEntity getMlss() {
        return mlss;
    }

    public void setMlss(ShouYeBean.ResultEntity.MlssEntity mlss) {
        this.mlss = mlss;
    }

    public ShouYeBean.ResultEntity.PzshEntity getPzsh() {
        return pzsh;
    }

    public void setPzsh(ShouYeBean.ResultEntity.PzshEntity pzsh) {
        this.pzsh = pzsh;
    }

    public ShouYeBean.ResultEntity.RxxpEntity getRxxp() {
        return rxxp;
    }

    public void setRxxp(ShouYeBean.ResultEntity.RxxpEntity rxxp) {
        this.rxxp = rxxp;
    }

    public ShouYeBeans() {
    }

    public ShouYeBeans(ShouYeBean.ResultEntity.MlssEntity mlss, ShouYeBean.ResultEntity.PzshEntity pzsh, ShouYeBean.ResultEntity.RxxpEntity rxxp) {
        this.mlss = mlss;
        this.pzsh = pzsh;
        this.rxxp = rxxp;
    }
}
