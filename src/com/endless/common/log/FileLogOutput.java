package com.endless.common.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

/**
 * Created by zhanglei on 14-8-12.
 */
public class FileLogOutput extends LogOutput {

    private static final String LOG_FILE = "log.txt";
    private static final String LOG_BACKUP_FILE = "log_old.txt";
    private static final int LOG_MAXSIZE = 1024 * 1024;
    private String mFileFolder;
    private OutputStream mLogStream;
    private long mFileSize;
    private Object lockObj = new Object();
    Calendar mDate = Calendar.getInstance();
    StringBuffer mBuffer = new StringBuffer();

    public FileLogOutput() {
        super(LogOutputType.FILE_LOG_OUTPUT);
    }

    public void setFileFolder(String fileFolder) {
        mFileFolder = fileFolder;
    }

    private String getLogStr(String tag, String msg) {
        mDate.setTimeInMillis(System.currentTimeMillis());
        mBuffer.setLength(0);
        mBuffer.append("[");
        mBuffer.append(tag);
        mBuffer.append(":");
        mBuffer.append(mDate.get(Calendar.MONTH) + 1);
        mBuffer.append("-");
        mBuffer.append(mDate.get(Calendar.DATE));
        mBuffer.append(" ");
        mBuffer.append(mDate.get(Calendar.HOUR_OF_DAY));
        mBuffer.append(":");
        mBuffer.append(mDate.get(Calendar.MINUTE));
        mBuffer.append(":");
        mBuffer.append(mDate.get(Calendar.SECOND));
        mBuffer.append(":");
        mBuffer.append(mDate.get(Calendar.MILLISECOND));
        mBuffer.append("] ");
        mBuffer.append(msg);

        return mBuffer.toString();
    }

    private boolean renameLogFile() {
        synchronized (lockObj) {
            File file = new File(mFileFolder, LOG_FILE);
            File destFile = new File(mFileFolder, LOG_BACKUP_FILE);
            if (destFile.exists()) {
                destFile.delete();
            }
            if (file.renameTo(destFile)) {
                if (file.exists()) {
                    return file.delete();
                }
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void writeLog(String tag, String content) {
        synchronized (lockObj) {
            if (mLogStream != null) {
                try {
                    byte[] d = getLogStr(tag, content).getBytes("utf-8");
                    if (mFileSize > LOG_MAXSIZE) {
                        close();
                        renameLogFile();
                        if (!open()) return;
                    }
                    mLogStream.write(d);
                    mLogStream.write("\r\n".getBytes());
                    mLogStream.flush();
                    mFileSize += d.length;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean open() {
        if (mFileFolder == null) return false;
        if (mLogStream != null) return true;

        try {
            File folder = new File(mFileFolder);
            if (!folder.exists()) {
                if (!folder.mkdirs()) return false;
            }
            File file = new File(mFileFolder, LOG_FILE);
            mLogStream = new FileOutputStream(file, true);
            mFileSize = file.length();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void close() {
        try {
            if (mLogStream != null) {
                mLogStream.close();
                mLogStream = null;
                mFileSize = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isOpened() {
        return mLogStream != null;
    }
}
