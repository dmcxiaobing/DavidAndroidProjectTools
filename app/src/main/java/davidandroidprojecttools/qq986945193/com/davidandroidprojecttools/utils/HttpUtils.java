package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;
/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @交流Qq ：986945193
 * @GitHub: https://github.com/QQ986945193
 */

/**
 * 工具类，用于获取网络json数据和图片数据
 * <p/>
 * 注：要开启一个子线程请求网络。
 */
public class HttpUtils {
    // 通过url获取Json数据
    /*public static String getJsonFromUrl(String url) {
        String json = "";
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                InputStream is = httpResponse.getEntity().getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is));
                String line = null;
                while ((line = br.readLine()) != null) {
                    json += line;
                }
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    // 通过url获取网络Bitmap数据
    public static Bitmap getBitmapFromUrl(String url) {
        Bitmap bitmap = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                InputStream is = httpResponse.getEntity().getContent();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
*/

}
