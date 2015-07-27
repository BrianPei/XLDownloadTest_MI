package com.xunlei.download.test.checklist;

import android.database.Cursor;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;
import com.xunlei.download.utils.LogUtil.DebugLog;

import java.io.File;


public class DeleteTest extends BaseCase {

    public void testDeleteRunning() {
        printDivideLine();
        //添加一条下载任务
        String downloadUrl = "http://f4.market.xiaomi.com/download/AppStore/0373e245abd3341d706dba26cdb25917a5a7c427f/%E8%8A%92%E6%9E%9CTV_4.4.3_55.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        sleep(5);
        //获取下载任务文件位置
        String data = CaseUtils.selectDownloadPath(downloadManager, id);
        DebugLog.d("TEST", "File path = " + data);
        //删除该任务
        int result = downloadManager.markRowDeleted(id);
        DebugLog.d("TEST", "删除任务");
        DebugLog.d("TEST", "Remove Result = " + result);
        assertEquals("删除失败", 1, result);
        sleep(1);
        //验证删除结果
        Cursor cursor = CaseUtils.selectNewTask(downloadManager);
        if (cursor.getCount() > 0) {
            assertTrue("数据库中仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id);
        }
        File file = new File(data);
        assertTrue("本地文件也被删除", !file.exists());
    }

    public void testDeletePaused() {
        printDivideLine();
        //添加一条下载任务
        String downloadUrl = "http://jobsfe.funshion.com/play/v1/mp4/C3280233E20E09988E2855875E3AB78F84BA9D2A.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjjq1dc2XNw%3D%3D";
        long id = CaseUtils.addRelocateTask(downloadManager, downloadUrl);
        sleep(5);
        //获取下载任务文件位置
        String data = CaseUtils.selectDownloadPath(downloadManager, id);
        DebugLog.d("TEST", "File path = " + data);
        //暂停任务
        downloadManager.pauseDownload(id);
        DebugLog.d("TEST", "暂停任务");
        sleep(3);
        //删除该任务
        int result = downloadManager.markRowDeleted(id);
        DebugLog.d("TEST", "删除任务");
        DebugLog.d("TEST", "Remove Result = " + result);
        assertEquals("删除失败", 1, result);
        sleep(1);
        //验证删除结果
        Cursor cursor = CaseUtils.selectNewTask(downloadManager);
        if (cursor.getCount() > 0) {
            assertTrue("数据库中仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id);
        }
        File file = new File(data);
        assertTrue("本地文件也被删除", !file.exists());
    }

    public void testDeleteSuccessed() {
        printDivideLine();
        //添加一条下载成功的任务
        String downloadUrl = "http://f2.market.xiaomi.com/download/AppStore/0d3d74dd3647350c18db922d36c870e5aa84352ca/%E9%A5%BF%E4%BA%86%E4%B9%88_5.0.2_55.apk";
        long id = CaseUtils.insertSuccessTask(downloadManager, downloadUrl);
        sleep(2);
        //获取下载任务文件位置
        String data = CaseUtils.selectDownloadPath(downloadManager, id);
        DebugLog.d("TEST", "File path = " + data);
        //删除该任务
        int result = downloadManager.markRowDeleted(id);
        DebugLog.d("TEST", "删除任务");
        DebugLog.d("TEST", "Delete Result = " + result);
        assertEquals("删除失败", 1, result);
        sleep(3);
        //验证删除结果
        Cursor cursor = CaseUtils.selectNewTask(downloadManager);
        if (cursor.getCount() > 0) {
            assertTrue("数据库中仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id);
        }
        File file = new File(data);
        assertTrue("本地文件也被删除", !file.exists());
    }

    public void testDeleteFailed() {
        printDivideLine();
        //添加一条下载失败的任务
        long id = CaseUtils.insertFailedTask(downloadManager);
        sleep(2);
        //删除该任务
        int result = downloadManager.markRowDeleted(id);
        DebugLog.d("TEST", "删除任务");
        DebugLog.d("TEST", "Delete Result = " + result);
        assertEquals("删除失败", 1, result);
        sleep(3);
        //验证删除结果
        Cursor cursor = CaseUtils.selectNewTask(downloadManager);
        if (cursor.getCount() > 0) {
            assertTrue("数据库中仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id);
        }
    }

    public void testRemoveRecordOnly() {
        printDivideLine();
        //添加一条下载成功的任务
        String downloadUrl = "http://f2.market.xiaomi.com/download/AppStore/0d3d74dd3647350c18db922d36c870e5aa84352ca/%E9%A5%BF%E4%BA%86%E4%B9%88_5.0.2_55.apk";
        long id = CaseUtils.insertSuccessTask(downloadManager, downloadUrl);
        sleep(3);
        //获取下载任务文件位置
        String data = CaseUtils.selectDownloadPath(downloadManager, id);
        DebugLog.d("TEST", "File path = " + data);
        //仅删除任务下载记录
        int result = downloadManager.removeRecordOnly(id);
        DebugLog.d("TEST", "Delete Result = " + result);
        assertEquals("删除失败", 1, result);
        sleep(3);
        //验证结果
        Cursor cursor = CaseUtils.selectNewTask(downloadManager);
        if (cursor.getCount() > 0) {
            assertTrue("数据库中仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id);
        }
        File file = new File(data);
        assertTrue("本地文件也被删除", file.exists());
    }

    public void testDeleteTwo() {
        printDivideLine();
        //添加两条下载任务
        String downloadUrl1 = "http://f5.market.xiaomi.com/download/AppStore/0ff2549c3826b73a234a7a62a866f4bc37540ce1c/%E6%89%8B%E6%9C%BA%E8%90%A5%E4%B8%9A%E5%8E%85_2.1.5_24.apk";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        String downloadUrl2 = "http://f4.market.xiaomi.com/download/AppStore/0879e5dcb379a22faf5eead36b4f7a90a1042d58a/%E5%B0%8F%E7%B1%B3%E6%96%97%E5%9C%B0%E4%B8%BB_4.1.3_11.apk";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //获取下载任务文件位置
        String data1 = CaseUtils.selectDownloadPath(downloadManager, id1);
        DebugLog.d("TEST", "File path = " + data1);
        String data2 = CaseUtils.selectDownloadPath(downloadManager, id2);
        DebugLog.d("TEST", "File path = " + data2);
        //删除任务
        int result = downloadManager.markRowDeleted(id1, id2);
        DebugLog.d("TEST", "删除任务");
        DebugLog.d("TEST", "Delete Result = " + result);
        assertEquals("删除失败", 2, result);
        //验证删除结果
        Cursor cursor = CaseUtils.selectNewTask(downloadManager);
        if (cursor.getCount() > 0) {
            assertTrue("任务1仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id1);
            assertTrue("任务2仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id2);
        }
        File file1 = new File(data1);
        assertTrue("本地文件也被删除", !file1.exists());
        File file2 = new File(data2);
        assertTrue("本地文件也被删除", !file2.exists());
    }

    public void testDeleteThree() {
        printDivideLine();
        //添加3条下载任务
        String downloadUrl1 = "http://market.xiaomi.com/thm/download/17bf8fc6-b931-49d1-92e6-f796ca4921cb?region=CN&homeOpenCount=3&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id1 = CaseUtils.addRelocateTask(downloadManager, downloadUrl1);
        String downloadUrl2 = "http://jobsfe.funshion.com/play/v1/mp4/842865B3BBE958675FA4CA07F4A69DD58651A88E.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjjq1M7gZWA%3D%3D";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        String downloadUrl3 = "http://data.vod.itc.cn/?new=/24/135/PzFMr0TwSH9DZGjYs1U6FC.mp4&vid=1001837784&plat=17&mkey=h31hVKoWY7zvP0wgbidkijGL-iNkNxbv&ch=tv&vid=2432293&uid=1435891440659902&plat=17&pt=5&prod=h5&pg=1&eye=0&cv=1.0.0&qd=680&src=1105&cateCode=101";
        long id3 = CaseUtils.addRelocateTask(downloadManager, downloadUrl3);
        sleep(5);
        //获取下载任务文件位置
        String data1 = CaseUtils.selectDownloadPath(downloadManager, id1);
        DebugLog.d("TEST", "File path = " + data1);
        String data2 = CaseUtils.selectDownloadPath(downloadManager, id2);
        DebugLog.d("TEST", "File path = " + data2);
        //删除任务
        int result = downloadManager.markRowDeleted(id1, id2, id3);
        DebugLog.d("TEST", "删除任务");
        DebugLog.d("TEST", "Delete Result = " + result);
        assertEquals("删除失败", 3, result);
        //验证删除结果
        Cursor cursor = CaseUtils.selectNewTask(downloadManager);
        if (cursor.getCount() > 0) {
            assertTrue("任务1仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id1);
            assertTrue("任务2仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id2);
            assertTrue("任务3仍未删除", cursor.getLong(cursor.getColumnIndex("_id")) != id3);
        }
        File file1 = new File(data1);
        assertTrue("本地文件也被删除", !file1.exists());
        File file2 = new File(data2);
        assertTrue("本地文件也被删除", !file2.exists());
    }
}
