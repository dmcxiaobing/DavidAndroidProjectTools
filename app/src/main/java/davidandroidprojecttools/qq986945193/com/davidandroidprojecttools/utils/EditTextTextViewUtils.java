package davidandroidprojecttools.qq986945193.com.davidandroidprojecttools.utils;

import android.widget.EditText;
import android.widget.TextView;

/**
 * @author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 * @CSDN博客: http://blog.csdn.net/qq_21376985
 */

/**
 * EdittextView与TextView常用的工具类
 */
public class EditTextTextViewUtils {
    /**
     * 判断EditTextview输入的内容是否为空
     *
     * @param mEv
     * @return
     */
    public static boolean getEditTextIsEmpty(EditText mEv) {
        if (mEv != null) {
            if (mEv.getText().toString().trim() == null || mEv.getText().toString().trim().equals("")) {
                return true;
            } else {
                return false;
            }

        }
        return true;

    }

    /**
     * 获取到Textview的字符串
     *
     * @param tv
     * @return
     */
    public static String getTextViewStr(TextView tv) {
        if (tv != null) {
            return tv.getText().toString().trim();
        } else {
            return "";
        }
    }

    /**
     * 获取到EditTextView的字符串
     *
     * @param ev
     * @return
     */
    public static String getEditTextStr(EditText ev) {
        if (ev != null) {
            return ev.getText().toString().trim();
        } else {
            return "";
        }
    }


}
