package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean;

/**
 * Created by david zheng on 2017/5/9.
 * <p>
 * http://www.anenda.com
 */

public class UpdateAppBean {

    /**
     * _ticket : jl4f3k6ha45bqlt1vttnr7m252
     * msg : {"apiVersion":"210000","description":"【更新说明】1. 提升用户流畅度  2.提升用户流畅度提升用户流畅度. 修复已知BUG","downloadUrl":"http://localhost:8080/davidandroid.apk","versionCode":"3","versionName":"1.2"}
     * status : success
     */

    private String _ticket;
    private MsgBean msg;
    private String status;

    public String get_ticket() {
        return _ticket;
    }

    public void set_ticket(String _ticket) {
        this._ticket = _ticket;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MsgBean {
        /**
         * apiVersion : 210000
         * description : 【更新说明】1. 提升用户流畅度  2.提升用户流畅度提升用户流畅度. 修复已知BUG
         * downloadUrl : http://localhost:8080/davidandroid.apk
         * versionCode : 3
         * versionName : 1.2
         */

        private String apiVersion;
        private String description;
        private String downloadUrl;
        private String versionCode;
        private String versionName;

        public String getApiVersion() {
            return apiVersion;
        }

        public void setApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }


}
