package com.bw.xuhongtao.bean.regbean;

/**
 * @author xuhongtao
 * @fileName RegBean
 * @package com.bw.xuhongtao.bean
 * @date 2019/3/20/020 14:18
 */
public class RegBean {

    String message;
    String status;

    @Override
    public String toString() {
        return "RegBean{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
