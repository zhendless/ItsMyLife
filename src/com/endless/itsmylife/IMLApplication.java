package com.endless.itsmylife;

import android.app.Application;
import com.endless.common.log.ZLog;
import com.endless.itsmylife.utils.MetaDataHelper;

/**
 * Created by zhanglei on 14-8-12.
 */
public class IMLApplication extends Application {

    private static IMLApplication instance;

    public static IMLApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        MetaDataHelper.init(this);
        ZLog.init(this, MetaDataHelper.LOG_LEVEL);

    }
}
