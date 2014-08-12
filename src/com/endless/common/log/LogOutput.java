package com.endless.common.log;

/**
 * Created by zhanglei on 14-8-12.
 */
public abstract class LogOutput {
    private LogOutputType mType;

    public LogOutput(LogOutputType type) {
        mType = type;
    }

    public LogOutputType getOutputType() {
        return mType;
    }

    public abstract void writeLog(String tag, String content);

    public abstract boolean open();

    public abstract void close();

    public abstract boolean isOpened();

    public enum LogOutputType {
        CONSOLE_LOG_OUTPUT,
        FILE_LOG_OUTPUT,
        CUSTOMER_LOG_OUTPUT;
    }
}
