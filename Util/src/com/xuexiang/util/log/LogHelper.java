package com.xuexiang.util.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;

import com.xuexiang.util.common.StringUtil;
import com.xuexiang.util.data.DateUtil;
import com.xuexiang.util.file.LocalFileUtil;

/**
 * 日志工具类 用来保存debug、error、Crash等Log
 */
@SuppressLint("SimpleDateFormat")
public class LogHelper {

    private static Context mContext;
    private static boolean mIsDebugMode = false;// 标识DebugMode的状态，获取堆栈信息会影响性能，发布应用时记得关闭DebugMode,在需要时打开。
    private static final String CLASS_METHOD_LINE_FORMAT = "%s.%s()  Line:%d  (%s)\r\n\n";// 格式化日志信息
    private static final String LINE_BREAK = "\r\n";// 换行符

    private static String mCaptureLogPath;
    private static String mPushLogPath;
    private static FileOutputStream mDebugLogWriter = null;
    private static FileOutputStream mEcgCaptureLogWriter = null;
    private static FileOutputStream mPushLogWriter = null;

    private static FileOutputStream mPacketLossLogWriter = null;
    private static FileOutputStream mInterfaceLogWriter = null;

    private static PrintStream mErrorLogWriter;
    /**
     * 格式化日期，用于打印每行日志的时间 yyyy-MM-dd HH:mm:ss.SSS
     */
    private static final ThreadLocal<DateFormat> mLineTime = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }
    };
    /**
     * 错误日志的格式 yyyy-MM-dd__HH-mm-ss.SSS
     */
    private static final ThreadLocal<DateFormat> mErrorLogDateFormat = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss.SSS");
        }
    };

    /**
     * 设置心电采集地址
     *
     * @param ecgCaptureLogPath
     */
    public static void setEcgCaptureLogPath(String ecgCaptureLogPath) {
        mCaptureLogPath = ecgCaptureLogPath;
    }

    /**
     * 设置消息推送的日志地址
     *
     * @param pushLogPath
     */
    public static void setPushLogPath(String pushLogPath) {
        mPushLogPath = pushLogPath;
    }

    /**
     * 设置打印丢包信息的日志路径（不在debug模式下也能打印日志）
     *
     * @param packetLossLogPath
     * @param deleteOld
     *            是否删除旧日志
     */
    public static void setPacketLossLogPath(String packetLossLogPath, boolean deleteOld) {
        try {
            mPacketLossLogWriter = setCustomLogPath(packetLossLogPath, "packetloss_log_", deleteOld);
        } catch (IOException e) {
            e.printStackTrace();
            saveExceptionStackInfo(e);
        }
    }

    /**
     * 设置接口调用信息的日志路径（不在debug模式下也能打印日志）
     *
     * @param interfaceLogPath
     * @param deleteOld
     *            是否删除旧日志
     */
    public static void setInterfaceLogPath(String interfaceLogPath, boolean deleteOld) {
        try {
            mInterfaceLogWriter = setCustomLogPath(interfaceLogPath, "interface_log_", deleteOld);
        } catch (IOException e) {
            e.printStackTrace();
            saveExceptionStackInfo(e);
        }
    }

    /**
     * 设置自定义日志路径
     *
     * @param customLogDirectoryPath
     *            自定义日志路径
     * @param logFlag
     *            日志标志
     * @param isDeleteOld
     *            是否需要删除旧日志
     * @throws IOException
     */
    private static FileOutputStream setCustomLogPath(String customLogDirectoryPath, String logFlag, boolean isDeleteOld) throws IOException {
        if (!StringUtil.isEmpty(customLogDirectoryPath)) {
            if (isDeleteOld) {
                LocalFileUtil.deleteFileList(new File(customLogDirectoryPath));
            }
            String logPath = customLogDirectoryPath + logFlag + DateUtil.formatDate(new Date()) + ".txt";
            File logFile = LocalFileUtil.isFileNotExistCreate(logPath);
            FileOutputStream customLogWriter = new FileOutputStream(logFile, true);
            return customLogWriter;
        } else {
            return null;
        }

    }

    /**
     * 打开日志助手
     *
     * @param context
     * @param isDeleteOld
     *            是否删除旧日志
     */
    public static boolean open(Context context, boolean isDeleteOld) {
        if (!mIsDebugMode) {
            mContext = context.getApplicationContext();
            String debugLogPath = LocalFileUtil.LOG_DIR + "debug_log/";

            try {
                mDebugLogWriter = setCustomLogPath(debugLogPath, "debug_log_", isDeleteOld);
                mEcgCaptureLogWriter = setCustomLogPath(mCaptureLogPath, "capture_log_", isDeleteOld);
                mPushLogWriter = setCustomLogPath(mPushLogPath, "push_log_", isDeleteOld);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            mIsDebugMode = true;
            LogUtils.setDebugMode(true);
            writeLog(mDebugLogWriter, getDevicesInfo(mContext));
            trace("Open Log Helper!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 关闭Log Helper
     */
    public static void close() {
        if (mIsDebugMode) {
            trace("Close Log Helper!");
            mIsDebugMode = false;
            LogUtils.setDebugMode(false);
            try {
                if (mDebugLogWriter != null) {
                    mDebugLogWriter.close();
                }
                if (mEcgCaptureLogWriter != null) {
                    mEcgCaptureLogWriter.close();
                }
                if (mPushLogWriter != null) {
                    mPushLogWriter.close();
                }
                if (mPacketLossLogWriter != null) {
                    mPacketLossLogWriter.close();
                }
                if (mInterfaceLogWriter != null) {
                    mInterfaceLogWriter.close();
                }
                if (mErrorLogWriter != null) {
                    mErrorLogWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                saveExceptionStackInfo(e);
            }
        }
    }

    /**
     * 保存一个异常的堆栈信息
     *
     * @param e
     */
    public static void saveExceptionStackInfo(Throwable e) {
        if (mErrorLogWriter != null) {
            writeErrorLog(mErrorLogWriter, e);
        } else {
            String errorLogPath = LocalFileUtil.LOG_DIR + "error_log/error_log_" + DateUtil.formatDate(new Date()) + ".txt";
            File file = LocalFileUtil.isFileNotExistCreate(errorLogPath);
            try {
                mErrorLogWriter = new PrintStream(new FileOutputStream(file, true));
                writeErrorLog(mErrorLogWriter, e);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 追踪 某个操作所调用的具体类和方法
     *
     * @throws
     */
    public static void trace() {
        if (mIsDebugMode) {
            writeLog(mDebugLogWriter, getLogStackInfo().toString());
        }
    }

    public static void trace(String info) {
        if (mIsDebugMode) {
            writeLog(mDebugLogWriter, getLogStackInfo(info).toString());
        }
    }

    /**
     * 跟踪采集时的数据验证信息
     *
     * @param info
     */
    public static void traceEcgCapture(String info) {
        if (mIsDebugMode) {
            writeLog(mEcgCaptureLogWriter, getLogStackInfo(info).toString());
        }
    }

    /**
     * 跟踪消息推送的信息
     *
     * @param info
     */
    public static void tracePush(String info) {
        if (mIsDebugMode) {
            writeLog(mPushLogWriter, getLogStackInfo(info).toString());
        }
    }

    /**
     * 打印丢包信息
     *
     * @param info
     */
    public static void tracePacketLossLog(String info) {
        if (mPacketLossLogWriter != null) {
            writeLog(mPacketLossLogWriter, getSimpleLogInfo(info).toString());
        }
    }

    /**
     * 打印接口信息
     *
     * @param info
     */
    public static void traceInterfaceLog(String info) {
        if (mInterfaceLogWriter != null) {
            writeLog(mInterfaceLogWriter, getSimpleLogInfo(info).toString());
        }
    }

    public static String getDevicesInfo(Context paramContext) {
        Map<String, String> logInfo = new HashMap<String, String>();
        try {
            // 获得包管理器
            PackageManager packageManager = paramContext.getPackageManager();
            // 得到该应用的信息，即主Activity
            PackageInfo mPackageInfo = packageManager.getPackageInfo(paramContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (mPackageInfo != null) {
                String versionName = mPackageInfo.versionName == null ? "null" : mPackageInfo.versionName;
                String versionCode = mPackageInfo.versionCode + "";
                logInfo.put("versionName", versionName);
                logInfo.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        // 反射机制
        Field[] mFields = Build.class.getDeclaredFields();
        // 迭代Build的字段key-value 此处的信息主要是为了在服务器端手机各种版本手机报错的原因
        for (Field field : mFields) {
            try {
                field.setAccessible(true);
                logInfo.put(field.getName(), field.get("").toString());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : logInfo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuffer.append(key + "=" + value + "\r\n");
        }
        stringBuffer.append(LINE_BREAK);
        stringBuffer.append(LINE_BREAK);
        stringBuffer.append("-------------------------------------------------------------" + LINE_BREAK);

        return stringBuffer.toString();
    }

    /**
     * 获取打印位置方法的堆栈信息
     *
     * @return
     */
    private static StringBuffer getLogStackInfo() {
        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[3];// 从堆栈信息中获取当前被调用的方法信息
        return new StringBuffer().append(mLineTime.get().format(new Date())).append(LINE_BREAK).append("stack: ")
                .append(String.format(CLASS_METHOD_LINE_FORMAT, traceElement.getClassName(), traceElement.getMethodName(), traceElement.getLineNumber(), traceElement.getFileName()))
                .append(LINE_BREAK).append(LINE_BREAK).append(LINE_BREAK).append("-------------------------------------------------------------").append(LINE_BREAK);
    }

    /**
     * 获取打印位置方法的堆栈信息
     *
     * @return
     */
    private static StringBuffer getLogStackInfo(String info) {
        StackTraceElement traceElement = Thread.currentThread().getStackTrace()[3];// 从堆栈信息中获取当前被调用的方法信息
        return new StringBuffer().append(mLineTime.get().format(new Date())).append(LINE_BREAK).append("stack: ")
                .append(String.format(CLASS_METHOD_LINE_FORMAT, traceElement.getClassName(), traceElement.getMethodName(), traceElement.getLineNumber(), traceElement.getFileName()))
                .append(LINE_BREAK).append("【trace info: ").append(info).append("】").append(LINE_BREAK).append(LINE_BREAK).append(LINE_BREAK).append(LINE_BREAK)
                .append("-------------------------------------------------------------").append(LINE_BREAK);
    }

    /**
     * 获取简要的日志信息
     *
     * @param info
     * @return
     */
    private static StringBuffer getSimpleLogInfo(String info) {
        return new StringBuffer().append(mLineTime.get().format(new Date())).append(LINE_BREAK).append("【trace info: ").append(info).append("】").append(LINE_BREAK).append(LINE_BREAK)
                .append(LINE_BREAK).append(LINE_BREAK).append("-------------------------------------------------------------").append(LINE_BREAK);
    }

    /**
     * 写日志
     *
     * @param logWriter
     * @param logText
     */
    public static void writeLog(FileOutputStream logWriter, String logText) {
        if (logWriter != null) {
            try {
                logWriter.write(logText.getBytes());
                logWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写错误日志
     *
     * @param errorLogWriter
     * @param e
     */
    public static synchronized void writeErrorLog(PrintStream errorLogWriter, Throwable e) {
        if (errorLogWriter != null) {
            try {
                errorLogWriter.write(getErrorTimeInfo().toString().getBytes());
                e.printStackTrace(errorLogWriter);
                errorLogWriter.write(getSpliteLine().toString().getBytes());
                errorLogWriter.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获取出错日志时间信息
     *
     * @return
     */
    private static StringBuffer getErrorTimeInfo() {
        return new StringBuffer().append(mErrorLogDateFormat.get().format(new Date())).append(LINE_BREAK).append(LINE_BREAK);
    }

    /**
     * 获取分割线
     *
     * @return
     */
    private static StringBuffer getSpliteLine() {
        return new StringBuffer().append(LINE_BREAK).append(LINE_BREAK).append("-------------------------------------------------------------").append(LINE_BREAK);
    }

    /**************************************************** 程序重要部分debug日志 ***********************************************************************************/
    public static void v(Object obj) {
        if (mIsDebugMode) {
            LogUtils.v(obj);
        }
    }

    public static void v(String tag, String msg) {
        if (mIsDebugMode) {
        	LogUtils.v(tag, msg);
        }
    }

    public static void d(Object obj) {
        if (mIsDebugMode) {
        	LogUtils.d(obj);
        }
    }

    public static void d(String tag, String msg) {
        if (mIsDebugMode) {
        	LogUtils.d(tag, msg);
        }
    }

    public static void i(Object obj) {
        if (mIsDebugMode) {
        	LogUtils.i(obj);
        }
    }

    public static void i(String tag, String msg) {
        if (mIsDebugMode) {
        	LogUtils.i(tag, msg);
        }
    }

    public static void w(Object obj) {
        if (mIsDebugMode) {
        	LogUtils.w(obj);
        }
    }

    public static void w(String tag, String msg) {
        if (mIsDebugMode) {
        	LogUtils.w(tag, msg);
        }
    }

    public static void e(Object obj) {
        if (mIsDebugMode) {
        	LogUtils.e(obj);
        }
    }

    public static void e(String tag, String msg) {
        if (mIsDebugMode) {
        	LogUtils.e(tag, msg);
        }
    }

}
