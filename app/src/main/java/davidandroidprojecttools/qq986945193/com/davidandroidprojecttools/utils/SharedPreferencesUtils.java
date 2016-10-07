package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "config";

    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {

        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context ctx, String key, boolean value) {
        if (ctx == null) {
            return;
        }
        if (key == null) {
            return;

        }
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

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
        return null;

    }

    public static void putInt(Context context, String xmlName, String key, int value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    public static void putBoolean(Context context, String xmlName, String key, boolean value) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String xmlName, String key) {
        sharedPreferences = context.getSharedPreferences(xmlName, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void putFloat(Context context, String xmlName, String key, float value) {
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


//    public static void saveString(Context context, String key, String value) {
//        if (sharedPreferences != null){
//
//            sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
//        sharedPreferences.edit().putString(key, value).commit();}
//    }
//    public static void delete(Context context, String key) {
//        sharedPreferences = context.getSharedPreferences(PREF_NAME,
//                Context.MODE_PRIVATE);
//        sharedPreferences.edit().remove(key).commit();
//    }
}

