package com.xunlei.download.test;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.test.ApplicationTestCase;

import com.xunlei.download.MyApplication;
import com.xunlei.download.utils.LogUtil.DebugLog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseCase extends ApplicationTestCase<MyApplication> {
    protected DownloadManager downloadManager;
    private WifiManager wifiManager;
    private ConnectivityManager connectivityManager;

    public static final String DOWNLOADPATH = "Download/download_test/";

    public BaseCase() {
        super(MyApplication.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        createApplication();
        MyApplication application = getApplication();
        downloadManager = (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);
        wifiManager = (WifiManager) application.getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        //删除用例中建立的下载任务
        Query query = new Query();
        Cursor cursor = downloadManager.query(query);
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                long id = cursor.getLong(cursor.getColumnIndex("_id"));
                downloadManager.remove(id);
            }
        }
        sleep(1);
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printDivideLine() {
        String caseName = Thread.currentThread().getStackTrace()[3]
                .getMethodName();
        DebugLog.d("TEST", "*******" + caseName + " Start!*******");
        sleep(1);
    }

    /**
     * 设置wifi开关
     *
     * @param isEnable
     */
    public void setWifi(boolean isEnable) {
        if (isEnable) {
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
            DebugLog.d("TEST", "WIFI is open");
        } else {
            if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
            DebugLog.d("TEST", "WIFI is closed");
        }
    }

    /**
     * 设置移动网络开关
     *
     * @param isEnable
     */
    public void setMobileNetwork(boolean isEnable) {
        Class ownerClass = connectivityManager.getClass();
        Class[] argsClass = new Class[1];
        argsClass[0] = boolean.class;
        try {
            Method method = ownerClass.getMethod("setMobileDataEnabled", argsClass);
            if (isEnable) {
                method.invoke(connectivityManager, true);
                DebugLog.d("TEST", "Mobile Network is open");
            } else {
                method.invoke(connectivityManager, false);
                DebugLog.d("TEST", "Mobile Network is closed");
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            DebugLog.d("TEST", "移动网络设置错误" + e.toString());
        }
    }
}
