package com.xunlei.download.test.checklist;


import android.app.DownloadManager.Request;
import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;
import com.xunlei.download.utils.LogUtil.DebugLog;

public class RequestTest extends BaseCase {

    public void testSetTitle() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f3.market.xiaomi.com/download/AppStore/0aa303591fffc4ef82478ae0622537660540617d6/%E5%94%B1%E5%90%A7_6.3.1_631.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        //设置标题
        String titleString = "Test Title";
        request.setTitle(titleString);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证标题结果
        Cursor cursor = CaseUtils.selectTask(downloadManager, id);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "Title = " + title);
        assertEquals("标题设置失败", titleString, title);
        sleep(1);
    }

    public void testSetDescription() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f5.market.mi-img.com/download/AppStore/0268c503aacd3de69103d6bff839b72b7b5402c22/%E5%BF%AB%E6%89%8B_4.22_422.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        //设置任务描述
        String descString = "Test Description";
        request.setDescription(descString);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证描述结果
        Cursor cursor = CaseUtils.selectTask(downloadManager, id);
        String description = cursor.getString(cursor.getColumnIndex("description"));
        DebugLog.d("TEST", "Description = " + description);
        assertEquals("描述设置失败", descString, description);
        sleep(1);
    }

    public void testSetMimeType() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f2.market.xiaomi.com/download/AppStore/011f65debd2e4659e601515abedc6e3e78641ebba/%E4%B9%90%E8%A7%86%E8%A7%86%E9%A2%91_5.9.1_88.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        //设置文件类型
        String mimeTypeString = "Plain/text";
        request.setMimeType(mimeTypeString);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        Cursor cursor1 = CaseUtils.selectTask(downloadManager, id);
        String mimeType1 = cursor1.getString(cursor1.getColumnIndex("media_type"));
        DebugLog.d("TEST", "MimeType = " + mimeType1);
        assertEquals("文件类型设置失败", mimeTypeString, mimeType1);
        sleep(5);
        //验证结果
        Cursor cursor = CaseUtils.selectTask(downloadManager, id);
        String mimeType = cursor.getString(cursor.getColumnIndex("media_type"));
        DebugLog.d("TEST", "MimeType = " + mimeType);
        assertEquals("真实文件类型错误", "application/vnd.android.package-archive", mimeType);
        sleep(1);
    }
}
