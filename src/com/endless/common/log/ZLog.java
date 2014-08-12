package com.endless.common.log;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by zhanglei on 14-8-12.
 */
public class ZLog {
    public static final int VERBOSE_LOG_LEVEL = 1;
    public static final int DEBUG_LOG_LEVEL = 2;
    public static final int INFO_LOG_LEVEL = 3;
    public static final int WARN_LOG_LEVEL = 4;
    public static final int ERROR_LOG_LEVEL = 5;
    public static final String FILE_FOLDER_NAME = "/ItsMyLife/logs";

    private static LogUtil mLog;

    public static void init(Context context, int logLevel) {
        mLog = new LogUtil();
        mLog.setEnable(true);
        mLog.setLogLevel(logLevel);
        File folder;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            folder = Environment.getExternalStorageDirectory();
        } else {
            folder = context.getFilesDir();
        }
        mLog.setFileFolder(folder.getParent() + FILE_FOLDER_NAME);
        mLog.addLogOutput(LogOutput.LogOutputType.CONSOLE_LOG_OUTPUT);
        mLog.addLogOutput(LogOutput.LogOutputType.FILE_LOG_OUTPUT);
    }

    /**
     * Print the verbose log
     */
    public static void v(String tag, String msg) {
        mLog.writeLog(tag, msg, VERBOSE_LOG_LEVEL);
    }

    /**
     * Print the debug log
     */
    public static void d(String tag, String msg) {
        mLog.writeLog(tag, msg, DEBUG_LOG_LEVEL);
    }

    /**
     * Print the info log
     */
    public static void i(String tag, String msg) {
        mLog.writeLog(tag, msg, INFO_LOG_LEVEL);
    }

    /**
     * Print the warn log
     */
    public static void w(String tag, String msg) {
        mLog.writeLog(tag, msg, WARN_LOG_LEVEL);
    }

    /**
     * Print the error log
     */
    public static void e(String tag, String msg) {
        mLog.writeLog(tag, msg, ERROR_LOG_LEVEL);
    }

    public static void exception(Throwable ex) {
        mLog.writeExceptionStack(ex, ERROR_LOG_LEVEL);
    }
}
