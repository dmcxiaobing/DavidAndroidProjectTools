package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.bean;
/**
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
import java.util.List;

/**
 * http://apis.baidu.com/txapi/apple/apple
 *
 * Bean实体类
 */
public class TxApiAppleBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-10-11 00:00","title":"无线充电和快充 下一代iPhone进军哪一个？","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476164892368.jpg","url":"http://www.i4.cn/news_detail_10797.html"},{"ctime":"2016-10-11 00:00","title":"强上加强！为 iPhone 7 系列准备个 Olloclip 镜头","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476165625125.jpg","url":"http://www.i4.cn/news_detail_10798.html"},{"ctime":"2016-10-11 00:00","title":"黑客讲述：看我如何用技术逼小偷把iPhone还回来","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476152246162.jpg","url":"http://www.i4.cn/news_detail_10788.html"},{"ctime":"2016-10-11 00:00","title":"把目光投向十周年的iPhone 8    \u201c小清新\u201d  萌萌滴！","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476152781367.jpg","url":"http://www.i4.cn/news_detail_10789.html"},{"ctime":"2016-10-11 00:00","title":"别再炸了，我只想安静地用一下iPhone手机","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476153068641.jpg","url":"http://www.i4.cn/news_detail_10791.html"},{"ctime":"2016-10-11 00:00","title":"钓鱼网站iPhone 6s只卖1280元","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476153318405.jpg","url":"http://www.i4.cn/news_detail_10792.html"},{"ctime":"2016-10-11 00:00","title":"iPhone 7性能最全测试！让安卓哭一会","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476155573439.jpg","url":"http://www.i4.cn/news_detail_10794.html"},{"ctime":"2016-10-11 00:00","title":"iPhone 7爆炸电池却完好，疑似非质量问题","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476156642794.jpg","url":"http://www.i4.cn/news_detail_10796.html"},{"ctime":"2016-10-11 00:00","title":"苹果iOS10.1beta3测试版推送 赶快来更新","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476148755292.jpg","url":"http://www.i4.cn/news_detail_10782.html"},{"ctime":"2016-10-11 00:00","title":"真给力！苹果iOS10最新市占率高达66%","description":"爱思助手","picUrl":"http://d.image.i4.cn/i4web/image/news/2016-10-11/1476149901552.jpg","url":"http://www.i4.cn/news_detail_10785.html"}]
     */

    private String code;
    private String msg;
    /**
     * ctime : 2016-10-11 00:00
     * title : 无线充电和快充 下一代iPhone进军哪一个？
     * description : 爱思助手
     * picUrl : http://d.image.i4.cn/i4web/image/news/2016-10-11/1476164892368.jpg
     * url : http://www.i4.cn/news_detail_10797.html
     */

    private List<NewslistBean> newslist;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
