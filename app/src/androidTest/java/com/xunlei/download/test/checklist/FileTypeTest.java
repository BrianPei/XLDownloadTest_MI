package com.xunlei.download.test.checklist;


import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;

public class FileTypeTest extends BaseCase {

    public void testAPK() {
        printDivideLine();
        //添加APK文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DownloadAPK/TalkingDic_cracked.apk";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/vnd.android.package-archive");
        sleep(1);
    }

    public void testMP3() {
        printDivideLine();
        //添加MP3文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DownloadAudio/mp3/INTRODUCTION3.3M.mp3";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "audio/mpeg");
        sleep(1);
    }

    public void testWAV() {
        printDivideLine();
        //添加WAV文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DRM/Others/non.wav";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "audio/x-wav");
        sleep(1);
    }

    public void testWMA() {
        printDivideLine();
        //添加WMA文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DownloadAudio/FormatTest/(WMA)WMA-128kbps.wma";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "audio/x-ms-wma");
        sleep(1);
    }

    public void testMP4() {
        printDivideLine();
        //添加MP4文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DRM/Others/160-120_1.mp4";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "video/mp4");
        sleep(1);
    }

    public void testAVI() {
        printDivideLine();
        //添加AVI文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DRM/Others/Download.avi";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "video/x-msvideo");
        sleep(1);
    }

    public void test3GP() {
        printDivideLine();
        //添加3GP文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DRM/Others/Ray.3gp";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "video/3gpp");
        sleep(1);
    }

    public void testJPG() {
        printDivideLine();
        //添加JPG文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DownloadImage/JPG/1024x768_JPG.jpg";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "image/jpeg");
        sleep(1);
    }

    public void testPNG() {
        printDivideLine();
        //添加PNG文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DownloadImage/PNG/640x480_PNG.png";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "image/png");
        sleep(1);
    }

    public void testBMP() {
        printDivideLine();
        //添加BMP文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DownloadImage/BMP/1024x768_8bitBMP.bmp";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "image/x-ms-bmp");
        sleep(1);
    }

    public void testGIF() {
        printDivideLine();
        //添加GIF文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/ShowPicture/agif.gif";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "image/gif");
        sleep(1);
    }

    public void testTXT() {
        printDivideLine();
        //添加TXT文件下载任务
        String downloadUrl = "http://dzs.qisuu.com/txt/%E8%B6%85%E7%BA%A7%E6%97%A0%E6%95%8C%E5%8F%AC%E5%94%A4%E7%A9%BA%E9%97%B4.txt";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "text/plain");
        sleep(1);
    }

    public void testDOC() {
        printDivideLine();
        //添加DOC文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/WatchDog.docx";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        sleep(1);
    }

    public void testXLS() {
        printDivideLine();
        //添加XLS文件下载任务
        String downloadUrl = "http://u1.htexam.com/jilin/2014jlsszzfgs20140820fj1.xls";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/vnd.ms-excel");
        sleep(1);
    }

    public void testPPT() {
        printDivideLine();
        //添加PPT文件下载任务
        String downloadUrl = "http://cdeg.tcu.edu.tw/Userdata/File/aaa%2825%29.ppt";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/vnd.ms-powerpoint");
        sleep(1);
    }

    public void testPDF() {
        printDivideLine();
        //添加PDF文件下载任务
        String downloadUrl = "https://www.ocbc.com/assets/pdf/Media/2008/nov/081128%20Sunday%20Banking%20Media%20Release.pdf";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/pdf");
        sleep(1);
    }

    public void testZIP() {
        printDivideLine();
        //添加ZIP文件下载任务
        String downloadUrl = "http://mnt.pc6.com/rm/xqpc_client.zip";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/zip");
        sleep(1);
    }

    public void testRAR() {
        printDivideLine();
        //添加RAR文件下载任务
        String downloadUrl = "http://cdn1.mydown.yesky.com/55b1e731/a6886a4e8581b46ee638943c75a57d2c/soft/201405/sougou.rar";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/rar");
        sleep(1);
    }

    public void testIMG() {
        printDivideLine();
        //添加IMG文件下载任务
        String downloadUrl = "http://nj.bs.baidu.com/recovery/p6-recovery.img";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/octet-stream");
        sleep(1);
    }

    public void testMDS() {
        printDivideLine();
        //添加MDS文件下载任务
        String downloadUrl = "http://f5.market.xiaomi.com/download/AppStore/0bc4d4cd9739e4e323aac2330234036633243b98c/WiFi%E4%BC%B4%E4%BE%A3_2.7.8_278.mds";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/octet-stream");
        sleep(1);
    }

    public void testJAR() {
        printDivideLine();
        //添加JAR文件下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/DRM/FL/0301_CL_Java.jar";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFileType(downloadManager, id, "application/java-archive");
        sleep(1);
    }
}
