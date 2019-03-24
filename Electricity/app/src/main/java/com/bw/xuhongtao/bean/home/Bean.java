package com.bw.xuhongtao.bean.home;

/**
 * @author xuhongtao
 * @fileName Bean
 * @package com.bw.xuhongtao.bean.home
 * @date 2019/3/20/020 20:22
 */
public class Bean {
    HomeBean.ResultEntity.RxxpEntity rxxp;
    HomeBean.ResultEntity.MlssEntity mlss;
    HomeBean.ResultEntity.PzshEntity pzsh;

    public Bean() {
    }

    @Override
    public String toString() {
        return "Bean{" +
                "rxxp=" + rxxp +
                ", mlss=" + mlss +
                ", pzsh=" + pzsh +
                '}';
    }

    public HomeBean.ResultEntity.RxxpEntity getRxxp() {
        return rxxp;
    }

    public void setRxxp(HomeBean.ResultEntity.RxxpEntity rxxp) {
        this.rxxp = rxxp;
    }

    public HomeBean.ResultEntity.MlssEntity getMlss() {
        return mlss;
    }

    public void setMlss(HomeBean.ResultEntity.MlssEntity mlss) {
        this.mlss = mlss;
    }

    public HomeBean.ResultEntity.PzshEntity getPzsh() {
        return pzsh;
    }

    public void setPzsh(HomeBean.ResultEntity.PzshEntity pzsh) {
        this.pzsh = pzsh;
    }

    public Bean(HomeBean.ResultEntity.RxxpEntity rxxp, HomeBean.ResultEntity.MlssEntity mlss, HomeBean.ResultEntity.PzshEntity pzsh) {
        this.rxxp = rxxp;
        this.mlss = mlss;
        this.pzsh = pzsh;
    }
}
