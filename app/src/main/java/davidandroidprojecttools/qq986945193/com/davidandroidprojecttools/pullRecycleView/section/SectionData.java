package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.pullRecycleView.section;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
public class SectionData<T> {
    public boolean isHeader;
    public int headerIndex;//用于索引ABC...的index定位
    public T t;
    public String header;

    public SectionData(boolean isHeader, int headerIndex, String header) {
        this.isHeader = isHeader;
        this.header = header;
        this.headerIndex = headerIndex;
        this.t = null;
    }

    public SectionData(T t) {
        this.isHeader = false;
        this.header = null;
        this.t = t;
    }
}
