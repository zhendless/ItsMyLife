package com.endless.itsmylife.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.endless.common.log.ZLog;

import java.util.Objects;

/**
 * Created by zhanglei on 14-8-12.
 */
public class MetaDataHelper {

    private static final String META_DATA_NAME_LOG_LEVEL = "LOG_LEVEL";
    public static int LOG_LEVEL = 1;

    public static void init(Context context) {
        String logLevel = getMetaValue(context, META_DATA_NAME_LOG_LEVEL);
        if (logLevel != null) {
            try {
                LOG_LEVEL = Integer.parseInt(logLevel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getMetaValue(Context context, String key) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo appInfo = packageManager.getApplicationInfo(context.getPackageName(), packageManager.GET_META_DATA);
                if (appInfo.metaData != null) {
                    Object value = appInfo.metaData.get(key);
                    if (value != null) return value.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
