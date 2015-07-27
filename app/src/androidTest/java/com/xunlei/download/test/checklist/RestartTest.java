package com.xunlei.download.test.checklist;

import android.database.Cursor;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;
import com.xunlei.download.utils.LogUtil.DebugLog;
import com.xunlei.download.utils.StatusEnum;

public class RestartTest extends BaseCase{

    public void testRestartSuccessed() {
        printDivideLine();
        //添加一条下载成功的任务
        String downloadUrl = "http://f2.market.xiaomi.com/download/AppStore/0d3d74dd3647350c18db922d36c870e5aa84352ca/%E9%A5%BF%E4%BA%86%E4%B9%88_5.0.2_55.apk";
        long id = CaseUtils.insertSuccessTask(downloadManager, downloadUrl);
        sleep(2);
        //重启该任务
        downloadManager.restartDownload(id);
        DebugLog.d("TEST", "重新下载任务");
        sleep(5);
        //验证下载状态
        Cursor cursor = CaseUtils.selectTask(downloadManager, id);
        int status = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Restart Status = " + StatusEnum.getName(status));
        assertEquals("下载状态异常", 2, status);
        //验证下载速度
        int speed = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "Downloading Speed = " + speed / 1024 + "KB/s");
        assertTrue("下载速度异常", speed > 0);
        sleep(1);
    }

    public void testRestartFailed() {
        printDivideLine();
        //添加一条下载失败的任务
        long id = CaseUtils.insertFailedTask(downloadManager);
        sleep(2);
        //重启该任务
        downloadManager.restartDownload(id);
        DebugLog.d("TEST", "重新下载任务");
        sleep(2);
        //验证下载状态
        Cursor cursor = CaseUtils.selectTask(downloadManager, id);
        int status = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Restart Status = " + StatusEnum.getName(status));
        assertEquals("下载状态异常", 2, status);
    }
}
