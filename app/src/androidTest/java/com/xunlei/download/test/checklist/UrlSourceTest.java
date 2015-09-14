package com.xunlei.download.test.checklist;

import android.app.DownloadManager.Request;
import android.net.Uri;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;
import com.xunlei.download.utils.LogUtil.DebugLog;


public class UrlSourceTest extends BaseCase {

    public void testMarket1() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f1.market.xiaomi.com/download/AppStore/0398f84ca60e84a9e35138f2f4b236ab9311d8575/%E7%99%BE%E5%BA%A6%E5%9C%B0%E5%9B%BE_8.4.0_650.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMarket2() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f2.market.xiaomi.com/download/AppStore/05de64b4457f5b560cc399c48129ca3b08c43ab68/%E6%90%9C%E7%8B%90%E8%A7%86%E9%A2%91_5.0.0_5000.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    /*
    public void testMarket3() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f3.market.mi-img.com/download/AppStore/01187f4c3c3994e770f44b2bbd4002f8835a27aea/%E9%99%8C%E9%99%8C_6.2_354.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMarket4() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f4.market.xiaomi.com/download/AppStore/0ca4ea41a417b42bd1dc4db1df9b4636eee9d4a58/UC%E6%B5%8F%E8%A7%88%E5%99%A8_10.5.2_172.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMarket5() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f5.market.xiaomi.com/download/AppStore/08abe35adfde244270c723f281e13ae74f5a3bda4/%E8%85%BE%E8%AE%AF%E6%96%B0%E9%97%BB_4.7.3_473.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }*/

    public void testTheme1() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://market.xiaomi.com/thm/download/f7b2b7b7-dec7-431d-a52c-c790d85b3d7e?region=CN&homeOpenCount=2&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testTheme2() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://market.xiaomi.com/thm/download/e50c3bf4-ad27-489d-8b13-5e218697238c?region=CN&homeOpenCount=3&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    /*
    public void testTheme3() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://market.xiaomi.com/thm/download/54ab624b-02f6-47f0-badc-336f41d9ae30?region=CN&homeOpenCount=3&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testTheme4() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://market.xiaomi.com/thm/download/10a5e055-6f17-42dc-9a28-485276a26153?region=CN&homeOpenCount=2&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testTheme5() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://market.xiaomi.com/thm/download/19d732ff-cc64-43a2-bbd6-3477369b1b81?region=CN&homeOpenCount=4&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }*/

    public void testVideo1() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://jobsfe.funshion.com/play/v1/mp4/f85362eb013b0e7dd6f20f0bdfd8d9fe3da962d6.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjjq1Zcq34g%3D%3D";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testVideo2() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://data.vod.itc.cn/?new=/38/178/7Ma7A9mDIg5VYMm38cE00K.mp4&vid=1001804639&plat=17&mkey=9rVyxQTXW8ROimGTH5nD5RS9xcfMMz2i&ch=tv&vid=2380275&uid=1435891440659902&plat=17&pt=5&prod=h5&pg=1&eye=0&cv=1.0.0&qd=680&src=1105&cateCode=101";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    /*
    public void testVideo3() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://jobsfe.funshion.com/play/v1/mp4/18190A6AEF0F885586ED29A9D5EFD491C9F70B67.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjs%2FvEQRcxA%3D%3D";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testVideo4() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://data.vod.itc.cn/?new=/224/206/4m0nmuYsQoCL7uPikA3WVB.mp4&vid=1001785273&plat=17&mkey=Kh_g3xQzH8AlnYpVvDEl9GBmI1dHu0lg&ch=tv&vid=2348593&uid=1435891440659902&plat=17&pt=5&prod=h5&pg=1&eye=0&cv=1.0.0&qd=680&src=1105&cateCode=101";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testVideo5() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://jobsfe.funshion.com/play/v1/mp4/C3280233E20E09988E2855875E3AB78F84BA9D2A.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjjq1dc2XNw%3D%3D";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getRealName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }*/

    public void testRadio1() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026016077_24.m4a";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testRadio2() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026023065_24.m4a";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    /*
    public void testRadio3() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026011395_24.m4a";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testRadio4() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026001920_24.m4a";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testRadio5() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://aliod.qingting.fm/vod/00/00/0000000000000000000025972422_24.m4a";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }*/

    /*
    public void testMusic1() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://xz.97ting.com/content/01/245/1245282-MP3-128K-FTD-mp3.atmd?sig=YmQ4OGY4MjU3ZjI4OGZlYmI2NTM4YWFkMTEwNDQwMDA&exp=55af30fe&transDeliveryCode=19BB230DEA544C92B4F5F16EA66C2257FFD05F2A5A019D1033CE302FFB8EFFBCB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMusic2() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://xz.97ting.com/content/01/245/1245640-MP3-128K-FTD-mp3.atmd?sig=NmJiY2RlNDg0MzRiNDQwNDcwYjc0OTdhNDBhNTIxYTE&exp=55af3102&transDeliveryCode=F98CFF1357F8BFB71829F68592AF9C91E65B49B8669BF136C4EC27F2F2F92B88B0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMusic3() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://xz.97ting.com/content/01/246/1246233-MP3-128K-FTD-mp3.atmd?sig=Mzc1NDRlYjgxNGFkMzY3ZWEyNjYzNjhlMWQ5ZTY5MWE&exp=55af3103&transDeliveryCode=3E22F9FD4C70ACE8DF9FDB1C4A889CF68DABE27E4924BCA0BB09271E97BDE73BB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMusic4() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://xz.97ting.com/content/01/248/1248498-MP3-128K-FTD-mp3.atmd?sig=M2RkZDIyNDUxOGVkOWU1NDllZmRkNjA4ZWQ0MjM0NjM&exp=55af3108&transDeliveryCode=A956EBDDB5AC8EF6EB46C3AD0DEB521A3D46BE32DFAE67D98D5B63C651A7073CB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMusic5() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://xz.97ting.com/content/01/248/1248892-MP3-128K-FTD-mp3.atmd?sig=ZjYwMjBlYmE2MWFkNmVlMzEwODgyYjRjOTRmNzgwOGU&exp=55af310d&transDeliveryCode=216E2E65465504CF19AEDE280FC6D4E1CAF0AD01CDE994745D641DC3BD106D91B0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOAD_PATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }
    */

    public void testBrowser1() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://w.gdown.baidu.com/data/wisegame/502ed415dae04258/baidushoujizhushou_16785292.apk?f=m1101";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
    }

    public void testBrowser2() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=wiseala&url=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fh%253D200%2Fsign%3D44e31a11d5c8a786a12a4d0e570bc9c7%2Fa50f4bfbfbedab6462b3f72bf336afc378311e13.jpg";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testBrowser3() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://218.211.38.207/Download_Test/LargeFile/5mb.mp3";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    /*
    public void testBrowser4() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://mirror.hust.edu.cn/gnu/emacs/emacs-23.4.tar.bz2";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testBrowser5() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://app.iqiyi.com/common/iqiyi_931.apk?qyid=06c289fef01daf5ec9ae2b9ace6b550e&qypid=-1_2031";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }*/

    public void testMarketUpdate1() {
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f3.market.xiaomi.com/download/AppStore/0d1e641e66e5577c744b749dcb6b1b54151421d08/%E5%B0%8F%E7%B1%B3%E5%95%86%E5%9F%8E_3.0.20150520_20150520.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    /*
    public void testMarketUpdate2(){
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f4.market.xiaomi.com/download/AppStore/0f41b4c7c228fb7552a385aaee46316780c418f6c/%E5%B0%8F%E7%B1%B3%E8%BF%90%E5%8A%A8_1.5.453_1112.apk";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testMarketUpdate3(){
        printDivideLine();
        //创建下载任务
        String downloadUrl = "http://f4.market.mi-img.com/download/AppStore/0efacb47a25fd4ce53fa74c2641e0e994af823dcf/%E5%B0%8F%E7%B1%B3%E6%99%BA%E8%83%BD%E5%AE%B6%E5%BA%AD_2.6.13_60157.mds";
        Request request = new Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        DebugLog.d("TEST", "FILE NAME = " + fileName);
        request.setDestinationInExternalPublicDir(DOWNLOADPATH, fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
        assertTrue("下载任务建立失败", id > 0);
        sleep(3);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }*/
}