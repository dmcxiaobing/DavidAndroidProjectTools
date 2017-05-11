package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean;

import java.util.List;

/**
 * Created by david zheng on 2017/5/9.
 * <p>
 * https://github.com/QQ986945193
 */

public class UpdateAppBean {


    /**
     * status : success
     * msg : [{"description":"你好。this is app description","downloadApkUrl":"http://192.168.1.36:8080/javaweb/download/dandroid.apk","versionCode":"2"}]
     */

    private String status;
    private List<MsgBean> msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * description : 你好。this is app description
         * downloadApkUrl : http://192.168.1.36:8080/javaweb/download/dandroid.apk
         * versionCode : 2
         */

        private String description;
        private String downloadApkUrl;
        private String versionCode;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDownloadApkUrl() {
            return downloadApkUrl;
        }

        public void setDownloadApkUrl(String downloadApkUrl) {
            this.downloadApkUrl = downloadApkUrl;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }
    }
}
