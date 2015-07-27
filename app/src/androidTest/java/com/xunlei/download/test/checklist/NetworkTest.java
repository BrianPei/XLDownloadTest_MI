package com.xunlei.download.test.checklist;


import android.app.DownloadManager;
import android.database.Cursor;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;

/**
 * 测试前需设置移动网络下载限制为20m
 */
public class NetworkTest extends BaseCase {

    public void tearDown(){
        //删除用例中建立的下载任务
        Cursor cursor = downloadManager.query(new DownloadManager.Query());
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            downloadManager.remove(id);
        }
        //确保wifi打开
        setWifi(true);
        sleep(5);
    }

    public void testWifiDisconnect() {
        printDivideLine();
        //确保wifi打开
        setWifi(true);
        sleep(3);
        //关闭3G
        setMobileNetwork(false);
        sleep(3);
        //添加下载任务
        String downloadUrl = "http://f1.market.mi-img.com/download/AppStore/0c25355324fcae4edad799002132954696343505e/%E6%A4%8D%E7%89%A9%E5%A4%A7%E6%88%98%E5%83%B5%E5%B0%B8%E5%85%A8%E6%98%8E%E6%98%9F_2.0.70_20.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //验证此时下载状态
        int statusBegin = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("下载状态异常", 2, statusBegin);
        int speedBegin = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("下载速度异常", speedBegin >= 0);
        sleep(1);
        //关闭wifi
        setWifi(false);
        sleep(3);
        //验证此时下载状态
        int statusDisconnect = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("断网后状态异常", 4, statusDisconnect);
        int reason = CaseUtils.selectReason(downloadManager, id);
        assertEquals("断网原因异常", 2, reason);
        int speedDisconnect = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("断网后速度异常", speedDisconnect == 0);
        sleep(1);
        //再打开wifi续传
        setWifi(true);
        sleep(10);
        //验证此时下载状态
        int statusResume = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("续传状态异常", 2, statusResume);
        int speedResume = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("续传速度异常", speedResume >= 0);
        sleep(1);
    }

    public void testWifiTo3G() {
        printDivideLine();
        //确保wifi打开
        setWifi(true);
        sleep(3);
        //确保3G打开
        setMobileNetwork(true);
        sleep(3);
        //添加下载任务(小于20m)
        String downloadUrl = "http://f4.market.mi-img.com/download/AppStore/0c93215243f0f4d9927ca7da14285cf9220edec27/%E7%B1%B3%E8%81%8A_1.0.1265_1265.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //验证此时下载状态
        int statusWifi = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("Wifi下载状态异常", 2, statusWifi);
        int speedWifi = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("Wifi下载速度异常", speedWifi >= 0);
        sleep(1);
        //关闭wifi
        setWifi(false);
        sleep(10);
        //验证此时下载状态
        int status3G = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("3G下载状态异常", 2, status3G);
        int speed3G = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("3G下载速度异常", speed3G >= 0);
        sleep(1);
    }

    public void testWifiToLimited3G() {
        printDivideLine();
        //确保wifi打开
        setWifi(true);
        sleep(3);
        //确保3G打开
        setMobileNetwork(true);
        sleep(3);
        //添加下载任务(大于20m)
        String downloadUrl = "http://f3.market.xiaomi.com/download/AppStore/099db646a7dbf4f331f91ada388c27dcfba43cba0/%E8%A5%BF%E6%B8%B8%E9%99%8D%E9%AD%94%E7%AF%87%E5%8A%A8%E4%BD%9C%E7%89%88_0.5.0_50.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //验证此时下载状态
        int statusWifi = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("Wifi下载状态异常", 2, statusWifi);
        int speedWifi = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("Wifi下载速度异常", speedWifi >= 0);
        sleep(1);
        //关闭wifi
        setWifi(false);
        sleep(10);
        //验证此时下载状态
        int status3G = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("3G下载状态异常", 4, status3G);
        int reason = CaseUtils.selectReason(downloadManager, id);
        assertEquals("3G下载原因异常", 2, reason);
        int speed3G = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("3G下载速度异常", speed3G == 0);
        sleep(1);
    }

    public void test3GDisconnect() {
        printDivideLine();
        //关闭wifi
        setWifi(false);
        sleep(3);
        //打开3G
        setMobileNetwork(true);
        sleep(5);
        //添加下载任务(小于20m)
        String downloadUrl = "http://f4.market.mi-img.com/download/AppStore/0c93215243f0f4d9927ca7da14285cf9220edec27/%E7%B1%B3%E8%81%8A_1.0.1265_1265.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //验证此时下载状态
        int statusBegin = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("下载状态异常", 2, statusBegin);
        int speedBegin = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("下载速度异常", speedBegin >= 0);
        sleep(1);
        //关闭3G
        setMobileNetwork(false);
        sleep(5);
        //验证此时下载状态
        int statusDisconnect = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("断网后状态异常", 4, statusDisconnect);
        int reason = CaseUtils.selectReason(downloadManager, id);
        assertEquals("断网原因异常", 2, reason);
        int speedDisconnect = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("断网后速度异常", speedDisconnect == 0);
        sleep(1);
        //再打开3G续传
        setMobileNetwork(true);
        sleep(5);
        //验证此时下载状态
        int statusResume = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("续传状态异常", 2, statusResume);
        int speedResume = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("续传速度异常", speedResume >= 0);
        sleep(1);
    }

    public void test3GtoWifi(){
        printDivideLine();
        //关闭wifi
        setWifi(false);
        sleep(3);
        //确保3G打开
        setMobileNetwork(true);
        sleep(5);
        //添加下载任务(小于20m)
        String downloadUrl = "http://f4.market.mi-img.com/download/AppStore/0c93215243f0f4d9927ca7da14285cf9220edec27/%E7%B1%B3%E8%81%8A_1.0.1265_1265.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //验证此时下载状态
        int status3G = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("3G下载状态异常", 2, status3G);
        int speed3G = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("3G下载速度异常", speed3G >= 0);
        sleep(1);
        //打开wifi
        setWifi(true);
        sleep(10);
        //验证此时下载状态
        int statusWifi = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("Wifi下载状态异常", 2, statusWifi);
        int speedWifi = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("Wifi下载速度异常", speedWifi >= 0);
        sleep(1);
    }

    public void testLimited3GtoWifi(){
        printDivideLine();
        //关闭wifi
        setWifi(false);
        sleep(3);
        //确保3G打开
        setMobileNetwork(true);
        sleep(5);
        //添加下载任务(大于20m)
        String downloadUrl = "http://f5.market.mi-img.com/download/AppStore/080b4b5eace69473d39f5c7f4daa11ef9acc6ac45/%E5%85%A8%E6%B0%91%E7%AA%81%E5%87%BB_1.6.5_2467.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //验证此时下载状态
        int status3G = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("3G下载状态异常", 4, status3G);
        int reason = CaseUtils.selectReason(downloadManager, id);
        assertEquals("3G下载原因异常", 2, reason);
        int speed3G = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("3G下载速度异常", speed3G == 0);
        sleep(1);
        //打开wifi
        setWifi(true);
        sleep(10);
        //验证此时下载状态
        int statusWifi = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("Wifi下载状态异常", 2, statusWifi);
        int speedWifi = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("Wifi下载速度异常", speedWifi >= 0);
        sleep(1);
    }
}
