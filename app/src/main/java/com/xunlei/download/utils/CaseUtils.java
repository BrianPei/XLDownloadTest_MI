package com.xunlei.download.utils;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.database.Cursor;
import android.net.Uri;

import com.xunlei.download.utils.LogUtil.DebugLog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CaseUtils {
    protected static final String DOWNLOAD_PATH = "Download/download_test";

    /**
     * 以默认设置添加无需重定向(Market/Radio)的下载任务
     *
     * @param downloadManager
     * @param downloadUrl
     * @return ID
     */
    public static long addTask(DownloadManager downloadManager, String downloadUrl) {
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        return id;
    }

    /**
     * 以默认设置添加需要重定向(Theme/Video)的下载任务
     *
     * @param downloadManager
     * @param downloadUrl
     * @return ID
     */
    public static long addRelocateTask(DownloadManager downloadManager, String downloadUrl) {
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        return id;
    }

    /**
     * 以默认设置添加网络链接下载任务
     *
     * @param downloadManager
     * @param downloadUrl
     * @return ID
     */
    public static long addWebTask(DownloadManager downloadManager, String downloadUrl) {
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getUrlName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        return id;
    }

    /**
     * 添加一个下载成功的任务
     *
     * @param downloadManager
     * @return ID
     */
    public static long insertSuccessTask(DownloadManager downloadManager, String downloadUrl) {
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        int status;
        do {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cursor cursor = selectTask(downloadManager, id);
            status = cursor.getInt(cursor.getColumnIndex("status"));
        } while (status < 8);
        DebugLog.d("TEST", "Final Status = " + StatusEnum.getName(status));
        return id;
    }

    /**
     * 添加一个下载失败的任务
     *
     * @param downloadManager
     * @return
     */
    public static long insertFailedTask(DownloadManager downloadManager) {
        String downloadUrl = "http://upodg.fm/vod/00/00/0000000000000000000026079877_24.m4a";
        Request request = new Request(
                Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("Test_Debug", "Task ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        int status;
        do {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cursor cursor = selectTask(downloadManager, id);
            status = cursor.getInt(cursor.getColumnIndex("status"));
        } while (status < 16);
        DebugLog.d("TEST", "Fail Status = " + StatusEnum.getName(status));
        return id;
    }

    /**
     * 获取指定的下载任务
     *
     * @param downloadManager
     * @param ids
     * @return
     */
    public static Cursor selectTask(DownloadManager downloadManager, long... ids) {
        Query query = new Query();
        query.setFilterById(ids);
        Cursor cursor = downloadManager.query(query);
        cursor.moveToLast();
        return cursor;
    }

    /**
     * 获取最新一条下载任务
     *
     * @param downloadManager
     * @return
     */
    public static Cursor selectNewTask(DownloadManager downloadManager) {
        Query query = new Query();
        Cursor cursor = downloadManager.query(query);
        cursor.moveToFirst();
        return cursor;
    }

    /**
     * 获取任务的下载速度
     *
     * @param downloadManager
     * @param id
     * @return
     */
    public static int selectDownloadSpeed(DownloadManager downloadManager, long id) {
        Cursor cursor = selectTask(downloadManager, id);
        int speed = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "Task Speed = " + speed / 1024 + "KB/S");
        return speed;
    }

    /**
     * 获取任务的下载状态
     *
     * @param downloadManager
     * @param id
     * @return
     */
    public static int selectDownloadStatus(DownloadManager downloadManager, long id) {
        Cursor cursor = selectTask(downloadManager, id);
        int status = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "Task Status = " + StatusEnum.getName(status));
        return status;
    }

    /**
     * 获取任务的异常原因
     *
     * @param downloadManager
     * @param id
     * @return
     */
    public static int selectReason(DownloadManager downloadManager, long id) {
        Cursor cursor = selectTask(downloadManager, id);
        int reason = cursor.getInt(cursor.getColumnIndex("reason"));
        DebugLog.d("TEST", "Reason = " + reason);
        return reason;
    }

    /**
     * 获取下载文件类型
     *
     * @param downloadManager
     * @param id
     * @return
     */
    public static String selectMimeType(DownloadManager downloadManager, long id) {
        Cursor cursor = selectTask(downloadManager, id);
        String mimeType = cursor.getString(cursor.getColumnIndex("media_type"));
        DebugLog.d("TEST", "Mime Type = " + mimeType);
        return mimeType;
    }

    /**
     * 获取下载文件的本地路径
     *
     * @param downloadManager
     * @param id
     * @return
     */
    public static String selectDownloadPath(DownloadManager downloadManager, long id) {
        Cursor cursor = selectTask(downloadManager, id);
        String localPath = cursor.getString(cursor.getColumnIndex("local_filename"));
        try {
            localPath = URLDecoder.decode(localPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return localPath;
    }

    /**
     * 监控下载状况，任务下载成功后获取平均速度
     *
     * @param downloadManager
     * @param id
     * @return
     */
    public static void checkDownloadResult(DownloadManager downloadManager, long id) {
        int status;
        List<Integer> speedList = new ArrayList<>();
        //获取任务预计大小
        Cursor cursorBegin = selectTask(downloadManager, id);
        long totalSize = cursorBegin.getLong(cursorBegin.getColumnIndex("total_size"));
        DebugLog.d("TEST", "任务预计大小为" + totalSize / 1024 + "KB");
        //轮询下载状态至下载完成
        do {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cursor cursorRunning = selectTask(downloadManager, id);
            status = cursorRunning.getInt(cursorRunning.getColumnIndex("status"));
            DebugLog.d("TEST", "Downloading Status = " + StatusEnum.getName(status));
            if (status == 1) {
                continue;
            }
            int totalSpeed = cursorRunning.getInt(cursorRunning.getColumnIndex("downloading_current_speed"));
            DebugLog.d("TEST", "Downloading Speed = " + totalSpeed / 1024 + "KB/s");
            if (totalSpeed > 0) {
                speedList.add(totalSpeed);
            }
            int vipSpeed = cursorRunning.getInt(cursorRunning.getColumnIndex("xl_accelerate_speed"));
            int p2sSpeed = cursorRunning.getInt(cursorRunning.getColumnIndex("download_surplus_time"));
            DebugLog.d("TEST", "XL VIP Speed = " + (vipSpeed + p2sSpeed) / 1024 + "KB/s");
        } while (status == 2);
        DebugLog.d("TEST", "任务最终状态为" + StatusEnum.getName(status));
        //验证下载结果
        assertEquals("下载状态异常", 8, status);
        //验证下载完成度
        Cursor cursorAfter = selectTask(downloadManager, id);
        long downloadedSize = cursorAfter.getLong(cursorAfter.getColumnIndex("bytes_so_far"));
        DebugLog.d("TEST", "任务实际大小为" + downloadedSize / 1024 + "KB");
        if (totalSize > 0) {
            assertEquals("下载完成度异常", totalSize, downloadedSize);
        }
        //计算平均速度
        int count = 0;
        for (int i = 0; i < speedList.size(); i++) {
            count += speedList.get(i);
        }
        if (count > 0) {
            int averageSpeed = count / speedList.size();
            DebugLog.d("TEST", "任务平均速度为" + averageSpeed / 1024 + "KB/S");
        } else {
            DebugLog.d("TEST", "任务文件太小，未能计算下载速度");
        }
    }

    /**
     * 检查下载失败的异常任务
     *
     * @param downloadManager
     * @param id
     */
    public static void checkFailedResult(DownloadManager downloadManager, long id) {
        int status;
        //轮询下载状态至下载失败
        do {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cursor cursorRunning = selectTask(downloadManager, id);
            status = cursorRunning.getInt(cursorRunning.getColumnIndex("status"));
            DebugLog.d("TEST", "Downloading Status = " + StatusEnum.getName(status));
            if (status == 8) {
                break;
            }
        } while (status < 16);
        DebugLog.d("TEST", "任务最终状态为" + StatusEnum.getName(status));
        //验证下载结果
        assertEquals("下载状态异常", 16, status);
        //输出失败原因
        selectReason(downloadManager, id);
    }

    /**
     * 检查下载任务的文件类型
     *
     * @param downloadManager
     * @param id
     * @param fileType
     */
    public static void checkFileType(DownloadManager downloadManager, long id, String fileType) {
        int status;
        do {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cursor cursorRunning = selectTask(downloadManager, id);
            status = cursorRunning.getInt(cursorRunning.getColumnIndex("status"));
            DebugLog.d("TEST", "Downloading Status = " + StatusEnum.getName(status));
            if (status == 1) {
                continue;
            }
            int totalSpeed = cursorRunning.getInt(cursorRunning.getColumnIndex("downloading_current_speed"));
            DebugLog.d("TEST", "Downloading Speed = " + totalSpeed / 1024 + "KB/s");
        } while (status == 2);
        DebugLog.d("TEST", "任务最终状态为" + StatusEnum.getName(status));
        //验证下载结果
        assertEquals("下载状态异常", 8, status);
        //验证文件类型
        String mimeType = selectMimeType(downloadManager, id);
        assertEquals("文件类型错误", fileType, mimeType);
    }

    /**
     * 删除指定下载任务
     *
     * @param downloadManager
     * @param ids
     */
    public static void deleteTasks(DownloadManager downloadManager, long... ids) {
        int result = downloadManager.remove(ids);
        assertTrue("删除任务失败", result > 0);
    }

    /**
     * 获取url中的文件名
     *
     * @param urlString
     * @return fileName
     */
    public static String getFileName(String urlString) {
        String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
        try {
            fileName = URLDecoder.decode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 获取重定向的url的真实文件名
     *
     * @param urlString
     * @return
     */
    public static String getRealName(String urlString) {
        String fileName = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            String realUrl = conn.getHeaderField("Location");
            DebugLog.d("TEST", realUrl);
            fileName = getFileName(realUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 获取浏览器下载url的文件名
     *
     * @param urlString
     * @return
     */
    public static String getUrlName(String urlString) {
        String fileName = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setInstanceFollowRedirects(false);
            String contentDisposition = conn.getHeaderField("Content-Disposition");
            fileName = contentDisposition.split("filename=")[1];
            fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
