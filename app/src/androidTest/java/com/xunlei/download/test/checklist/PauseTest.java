package com.xunlei.download.test.checklist;


import android.database.Cursor;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;
import com.xunlei.download.utils.LogUtil.DebugLog;
import com.xunlei.download.utils.StatusEnum;

public class PauseTest extends BaseCase {

    public void testPauseOneMarket() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://f2.market.mi-img.com/download/AppStore/05fce5e57450d64d3edf8dd062b0c2cd5f5433aff/%E8%98%91%E8%8F%87%E8%A1%97_7.1.3.150624001_713000.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //暂停该任务
        downloadManager.pauseDownload(id);
        DebugLog.d("TEST", "暂停任务");
        sleep(3);
        //验证暂停结果
        int status = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("暂停状态异常", 4, status);
        int reason = CaseUtils.selectReason(downloadManager, id);
        assertEquals("暂停原因异常", 5, reason);
        int speed = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("下载速度异常", speed == 0);
        sleep(1);
    }

    public void testPauseTwoMarket() {
        printDivideLine();
        //建立2条下载任务
        String downloadUrl1 = "http://f1.market.xiaomi.com/download/AppStore/07db654ad9f055c22a7878eaf050861de07400002/%E6%BB%B4%E6%BB%B4%E6%89%93%E8%BD%A6_3.9.4_93.apk";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        String downloadUrl2 = "http://f1.market.xiaomi.com/download/AppStore/01450446212e7499518bd2a39cd48aed4899d12c3/%E8%8A%82%E5%A5%8F%E5%A4%A7%E5%B8%88_2.5.3.2_2532.apk";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //同时暂停2条任务
        DebugLog.d("TEST", "暂停两条任务");
        downloadManager.pauseDownload(id1, id2);
        sleep(3);
        //验证暂停结果
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Task1 Status = " + StatusEnum.getName(status1));
        assertEquals("暂停异常", 4, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        assertTrue("下载速度异常", speed1 == 0);
        cursor.moveToPrevious();
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Task2 Status = " + StatusEnum.getName(status2));
        assertEquals("暂停异常", 4, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        assertTrue("下载速度异常", speed2 == 0);
        sleep(1);
    }

    public void testPauseDifferentSource() {
        printDivideLine();
        //添加三条不同来源的下载任务
        String marketUrl = "http://f3.market.xiaomi.com/download/AppStore/0d1e641e66e5577c744b749dcb6b1b54151421d08/%E5%B0%8F%E7%B1%B3%E5%95%86%E5%9F%8E_3.0.20150520_20150520.apk";
        long id1 = CaseUtils.addTask(downloadManager, marketUrl);
        String themeUrl = "http://market.xiaomi.com/thm/download/4e11ae48-c595-4f78-9e64-215909993b28?region=CN&homeOpenCount=5&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundHistoryClassics__%E5%8E%86%E5%8F%B2%E7%BB%8F%E5%85%B8";
        long id2 = CaseUtils.addRelocateTask(downloadManager, themeUrl);
        String browserUrl = "http://image.box.xiaomi.com/mfsv2/download/s010/Hp01lL9FMqbr/sHMuXevh3GgqSI.mp3";
        long id3 = CaseUtils.addTask(downloadManager, browserUrl);
        sleep(5);
        //全部暂停
        downloadManager.pauseDownload(id1, id2, id3);
        DebugLog.d("TEST", "暂停全部任务");
        sleep(3);
        //验证暂停结果
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2, id3);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Task1 Status = " + StatusEnum.getName(status1));
        assertEquals("暂停异常", 4, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        assertTrue("下载速度异常", speed1 == 0);
        cursor.moveToPrevious();
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Task1 Status = " + StatusEnum.getName(status1));
        assertEquals("暂停异常", 4, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        assertTrue("下载速度异常", speed2 == 0);
        cursor.moveToPrevious();
        int status3 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Task1 Status = " + StatusEnum.getName(status1));
        assertEquals("暂停异常", 4, status3);
        int speed3 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        assertTrue("下载速度异常", speed3 == 0);
        sleep(1);
    }

    public void testResumeOne() {
        printDivideLine();
        //添加一条下载任务
        String downloadUrl = "http://f3.market.mi-img.com/download/AppStore/0d04651f1bb0cabc519217d25f44e489c2343b527/%E5%BE%AE%E5%8D%9A_5.3.0_2022.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //暂停任务
        downloadManager.pauseDownload(id);
        DebugLog.d("TEST", "暂停任务");
        sleep(2);
        int status1 = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("暂停异常", 4, status1);
        sleep(2);
        //再续传任务
        downloadManager.resumeDownload(id);
        DebugLog.d("TEST", "续传任务");
        sleep(5);
        //验证结果
        int status = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("续传状态异常", 2, status);
        int speed = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("下载速度异常", speed > 0);
        sleep(1);
    }

    public void testResumeTwo() {
        printDivideLine();
        //添加两条下载任务
        String downloadUrl1 = "http://f3.market.mi-img.com/download/AppStore/0d04651f1bb0cabc519217d25f44e489c2343b527/%E5%BE%AE%E5%8D%9A_5.3.0_2022.apk";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        String downloadUrl2 = "http://market.xiaomi.com/thm/download/974fadfc-f86b-4b4f-ac1e-33783818630b?region=CN&homeOpenCount=3&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        sleep(5);
        //暂停任务
        downloadManager.pauseDownload(id1, id2);
        DebugLog.d("TEST", "暂停任务");
        sleep(2);
        int pauseStatus1 = CaseUtils.selectDownloadStatus(downloadManager, id1);
        assertEquals("暂停异常", 4, pauseStatus1);
        int pauseStatus2 = CaseUtils.selectDownloadStatus(downloadManager, id2);
        assertEquals("暂停异常", 4, pauseStatus2);
        sleep(2);
        //续传任务
        downloadManager.resumeDownload(id1, id2);
        DebugLog.d("TEST", "续传任务");
        sleep(5);
        //验证结果
        int resumeStatus1 = CaseUtils.selectDownloadStatus(downloadManager, id1);
        assertEquals("续传状态异常", 2, resumeStatus1);
        int resumeSpeed1 = CaseUtils.selectDownloadSpeed(downloadManager, id1);
        assertTrue("下载速度异常", resumeSpeed1 > 0);
        int resumeStatus2 = CaseUtils.selectDownloadStatus(downloadManager, id2);
        assertEquals("续传状态异常", 2, resumeStatus2);
        int resumeSpeed2 = CaseUtils.selectDownloadSpeed(downloadManager, id2);
        assertTrue("下载速度异常", resumeSpeed2 > 0);
        sleep(1);
    }

    public void testRepeatPause() {
        printDivideLine();
        //添加一条下载任务
        String downloadUrl = "http://f1.market.mi-img.com/download/AppStore/0c990e5a6f13648200d1f03d8ae84d843a99a076d/%E6%88%91%E7%9A%84%E4%B8%96%E7%95%8C_1.0_1.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //暂停任务
        downloadManager.pauseDownload(id);
        DebugLog.d("TEST", "第一次暂停");
        sleep(2);
        int pauseStatus1 = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("暂停异常", 4, pauseStatus1);
        sleep(1);
        //续传任务
        downloadManager.resumeDownload(id);
        DebugLog.d("TEST", "第一次续传");
        sleep(5);
        int resumeStatus1 = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("续传状态异常", 2, resumeStatus1);
        int resumeSpeed1 = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("下载速度异常", resumeSpeed1 > 0);
        //再次暂停任务
        downloadManager.pauseDownload(id);
        DebugLog.d("TEST", "第二次暂停");
        sleep(2);
        int pauseStatus2 = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("暂停异常", 4, pauseStatus2);
        sleep(1);
        //再次续传任务
        downloadManager.resumeDownload(id);
        DebugLog.d("TEST", "第二次续传");
        sleep(5);
        int resumeStatus2 = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("续传状态异常", 2, resumeStatus2);
        int resumeSpeed2 = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("下载速度异常", resumeSpeed2 > 0);
        sleep(2);
        //第三次暂停任务
        downloadManager.pauseDownload(id);
        DebugLog.d("TEST", "第三次暂停");
        sleep(2);
        int pauseStatus3 = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("暂停异常", 4, pauseStatus3);
        sleep(1);
        //第三次续传任务
        downloadManager.resumeDownload(id);
        DebugLog.d("TEST", "第三次续传");
        sleep(5);
        int resumeStatus3 = CaseUtils.selectDownloadStatus(downloadManager, id);
        assertEquals("续传状态异常", 2, resumeStatus3);
        int resumeSpeed3 = CaseUtils.selectDownloadSpeed(downloadManager, id);
        assertTrue("下载速度异常", resumeSpeed3 > 0);
        sleep(1);
    }
}
