package com.bw.xuhongtao.bean.loginbean;

/**
 * @author xuhongtao
 * @fileName LoginBean
 * @package com.bw.xuhongtao.bean.loginbean
 * @date 2019/3/20/020 14:28
 */
public class LoginBean {


    public ResultEntity result;
    public String message;
    public String status;

    @Override
    public String toString() {
        return "LoginBean{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
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

    public class ResultEntity {

        public String phone;
        public String nickName;
        public int sex;
        public String sessionId;
        public String headPic;
        public int userId;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "phone='" + phone + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", sex=" + sex +
                    ", sessionId='" + sessionId + '\'' +
                    ", headPic='" + headPic + '\'' +
                    ", userId=" + userId +
                    '}';
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
