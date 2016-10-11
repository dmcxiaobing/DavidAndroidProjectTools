package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 */

import java.util.List;

/**
 * 身份证查询结果的bean
 *
 */
public class IdCardBean {


    /**
     * errNum : -1
     * retMsg : 身份证号码不合法！
     * retData : []
     */

    private int errNum;
    private String retMsg;
    private List<?> retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public List<?> getRetData() {
        return retData;
    }

    public void setRetData(List<?> retData) {
        this.retData = retData;
    }
}
