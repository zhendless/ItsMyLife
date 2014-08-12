package com.endless.common.log;


import java.lang.Thread.UncaughtExceptionHandler;


public class CrashHandler implements UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";

    private UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler mInstance = new CrashHandler();

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return mInstance;
    }


    public void init() {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                ZLog.exception(e);
            }
            System.exit(0);
        }
    }

    private boolean handleException(Throwable ex) {

        if (ex == null) {
            ZLog.e(TAG, "handle null exception");
            return false;
        }
        ZLog.exception(ex);
        return false;
    }
}
