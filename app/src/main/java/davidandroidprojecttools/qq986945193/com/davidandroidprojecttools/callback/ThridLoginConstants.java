package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback;

/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */

/**
 * 一些第三方登录的appID
 */
public class ThridLoginConstants {
    /**
     * QQ登录的key
     */
    public interface QQ {
        /*这里填写自己的appId*/
        String CLIENT_ID = "986945193";//按需求更换
        //应用需要获得哪些API的权限，由“，”分隔。
//        例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
        String SCOPE = "all";
    }

    /**
     * 微博登录
     */
    public interface SinaWeibo {
        /*这里填写自己的appId*/
        String CLIENT_ID = "986945193";//按需求更换
        String REDIRECT_URL = "http://shouji.baidu.com/software/10410249.html";
        String SCOPE = "";
   /*     String SCOPE =
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";
        String GET_ACCOUNT_INFO = "https://api.weibo.com/oauth2/access_token";*/
//        String GET_ACCOUNT_INFO = "https://api.weibo.com/2/users/show.json";
    }

    /**
     * 微信登录
     */
    public interface WeiXin {
        String CLIENT_ID = "wx852b5d9aca286364986977";//按需求更换
        String APP_SECRET = "";//按需求更换
        String SCOPE = "snsapi_userinfo";
        String GET_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token";
        String REFRESH_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/refresh_token";
        String GET_USER_INFO_URL="https://api.weixin.qq.com/sns/userinfo";
        String GRANT_TYPE="authorization_code";

//        https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    }

}
