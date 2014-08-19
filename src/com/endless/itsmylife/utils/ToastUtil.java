package com.endless.itsmylife.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Toast提示工具类
 *
 * @author houmiao.xiong
 * @time 2013-10-30 下午4:52:27
 */
public class ToastUtil {

    /**
     * tosat提示
     *
     * @param ctx
     * @param msg
     * @time 2013-10-30 下午4:52:41
     */
    public static void showMessage(final Context ctx, final String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * toast提示
     *
     * @param ctx
     * @param resId
     * @time 2013-10-30 下午4:52:52
     */
    public static void showMessage(final Context ctx, final int resId) {
        Toast.makeText(ctx, resId, Toast.LENGTH_LONG).show();
    }


}
