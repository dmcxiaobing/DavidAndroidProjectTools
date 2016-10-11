package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.callback;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */
import okhttp3.Request;
import okhttp3.Response;

public abstract class OkHttpStopCallback<T> extends OkHttpBaseCallback<T> {
    @Override
    public void onBeforeRequest(Request request) {

    }

    @Override
    public void onFailure(Request request, Exception e) {

    }

    /**
     * json数据获取成功
     *
     * @param response
     */
    @Override
    public  void onResponse(Response response){

    }

    /**
     * 网络连接成功 code == 200
     *
     * @param response
     * @param t
     */
    @Override
    public abstract void onSuccess(Response response, T t);

    @Override
    public void onError(Response response, int code, Exception e) {

    }
}
