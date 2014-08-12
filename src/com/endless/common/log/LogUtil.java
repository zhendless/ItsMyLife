package com.endless.common.log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhanglei on 14-8-12.
 */
public class LogUtil {
    private List<LogOutput> mOutputList;
    private boolean mIsEnable;
    private int mLogLevel;
    private String mFileFolder;

    public LogUtil() {
        mOutputList = new LinkedList<LogOutput>();
        mIsEnable = true;
        mLogLevel = -1;
    }

    /**
     * set whether the log function is enabled
     */
    public void setEnable(boolean isEnable) {
        mIsEnable = isEnable;
    }

    public void setLogLevel(int logLevel) {
        mLogLevel = logLevel;
    }

    public void setFileFolder(String fileFolder) {
        mFileFolder = fileFolder;
    }

    public void writeLog(String tag, String msg, int level) {
        if (!mIsEnable) return;
        if (tag == null) tag = "TAG_NULL";
        if (msg == null) msg = "MSG_NULL";
        if (level >= mLogLevel) {
            for (LogOutput output : mOutputList) {
                if (output.isOpened() || output.open()) {
                    output.writeLog(tag, msg);
                }
            }
        }
    }

    public void writeExceptionStack(Throwable e, int level) {
        StringBuilder msg = new StringBuilder(e.toString() + "\r\n");
        StackTraceElement[] elements = e.getStackTrace();
        for (StackTraceElement element : elements) {
            msg.append(element.getClassName());
            msg.append(".");
            msg.append(element.getMethodName());
            msg.append(" line: ");
            msg.append(element.getLineNumber());
            msg.append("\r\n");
        }
        writeLog("Exception Stack", msg.toString(), level);
    }

    /**
     * add the supported log type
     */
    public void addLogOutput(LogOutput.LogOutputType outputType) {
        if (findOutputByType(outputType) == null) {
            switch (outputType) {
                case CONSOLE_LOG_OUTPUT:
                    ConsoleLogOutput consoleOutput = new ConsoleLogOutput();
                    addLogOutput(consoleOutput);
                    break;
                case FILE_LOG_OUTPUT:
                    FileLogOutput fileOutput = new FileLogOutput();
                    fileOutput.setFileFolder(mFileFolder);
                    addLogOutput(fileOutput);
                    break;
                default:
                    break;
            }
        }
    }

    public void removeLogOutput(LogOutput.LogOutputType outputType) {
        LogOutput output = findOutputByType(outputType);
        removeLogOutput(output);
    }

    public void addLogOutput(LogOutput output) {
        if (output != null && findOutputByType(output.getOutputType()) == null) {
            mOutputList.add(output);
        }
    }

    public void removeLogOutput(LogOutput output) {
        if (output != null) {
            mOutputList.remove(output);
        }
    }

    private LogOutput findOutputByType(LogOutput.LogOutputType outputType) {
        for (LogOutput output : mOutputList) {
            if (output.getOutputType() == outputType) {
                return output;
            }
        }
        return null;
    }
}
