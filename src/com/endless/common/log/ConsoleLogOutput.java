package com.endless.common.log;

import android.util.Log;

/**
 * Created by zhanglei on 14-8-12.
 */
public class ConsoleLogOutput extends LogOutput {

    public ConsoleLogOutput() {
        super(LogOutputType.CONSOLE_LOG_OUTPUT);
    }

    @Override
    public void writeLog(String tag, String content) {
        Log.d(tag, content);
    }

    @Override
    public boolean open() {
        return true;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean isOpened() {
        return true;
    }
}
