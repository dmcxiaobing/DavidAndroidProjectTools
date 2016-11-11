package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;
/*
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 * @OsChina空间: https://my.oschina.net/mcxiaobing
 */
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "config";


    /**
     * 写入文件 string格式
     *
     * @param context
     * @param xmlName
     * @param key
     * @param value
     */
    public static void putString(Context context, String xmlName, String key, String value) {
        if (xmlName == null) {
            return;
        }
        if (key == null) {
            return;

        }
        if (value == null) {
            return;
        }
        if (context == null) {
            return;
        }
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }


    /**
     * 删除String的值
     *
     * @param context
     * @param xmlName
     * @param key
     */
    public static void deleteString(Context context, String xmlName, String key) {
        if (context == null) {
            return;
        }
        if (key == null) {
            return;

        }
        if (xmlName == null) {
            return;
        }
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }


    /**
     * 取出String的值
     *
     * @param context
     * @param xmlName
     * @param key
     * @return
     */
    public static String getString(Context context, String xmlName, String key) {
        if (key != null && context != null & xmlName != null) {
            sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, "");
        }
        return "";

    }



    public static void putBoolean(Context ctx, String key, boolean value) {
        if (ctx == null) {
            return;
        }
        if (key == null) {
            return;

        }
        sharedPreferences = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {

        sharedPreferences = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    public static void putInt(Context context, String xmlName, String key, int value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    public static void putFloat(Context context, String xmlName, String key, float value) {
        if (context == null) {
            return;
        }
        if (key == null) {
            return;

        }
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putFloat(key, value).commit();
    }

    public static float getFloat(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, -1);
    }

    public static void putLong(Context context, String xmlName, String key, long value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, -1);
    }


}

