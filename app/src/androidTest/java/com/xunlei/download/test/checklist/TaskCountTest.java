package com.xunlei.download.test.checklist;


import android.database.Cursor;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;
import com.xunlei.download.utils.LogUtil.DebugLog;
import com.xunlei.download.utils.StatusEnum;


public class TaskCountTest extends BaseCase {

    public void testTwoMarketDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://f5.market.mi-img.com/download/AppStore/076f54400cfe844592ce98c2a8159ddb5fdc3fe1a/%E7%BE%8E%E5%9B%BE%E7%A7%80%E7%A7%80_4.3.3_433.apk";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://f4.market.mi-img.com/download/AppStore/011a55bdd8e9ed5ce7748cbdfbc37da8d4b419171/%E7%99%BE%E5%BA%A6%E4%BA%91_7.9.0_420.apk";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证2条下载任务均在正常下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        sleep(1);
    }

    public void testThreeMarketDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://f2.market.mi-img.com/download/AppStore/09ed52460ff6b45f015c3e4cbb6a44a99108f7d8c/%E4%BA%AC%E4%B8%9C_4.2.1_18996.apk";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://f3.market.mi-img.com/download/AppStore/0d04651f1bb0cabc519217d25f44e489c2343b527/%E5%BE%AE%E5%8D%9A_5.3.0_2022.apk";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        //建立下载任务3
        String downloadUrl3 = "http://f1.market.xiaomi.com/download/AppStore/0e4d74eccb23055ed4cf7487cf22c2b3a4d421441/%E7%88%B1%E5%A5%87%E8%89%BAPPS_4.2.0_80420.apk";
        long id3 = CaseUtils.addTask(downloadManager, downloadUrl3);
        sleep(5);
        //验证前两条任务在下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2, id3);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        //验证第三条任务未下载
        cursor.moveToPrevious();
        String title3 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK3 TITLE = " + title3);
        int status3 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK3 STATUS = " + StatusEnum.getName(status3));
        assertEquals("下载状态异常", 2, status1);
        int speed3 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed3 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed3 == 0);
        sleep(1);
    }

    public void testRepeatMarketDownload() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://f2.market.mi-img.com/download/AppStore/05fce5e57450d64d3edf8dd062b0c2cd5f5433aff/%E8%98%91%E8%8F%87%E8%A1%97_7.1.3.150624001_713000.apk";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://f2.market.mi-img.com/download/AppStore/05fce5e57450d64d3edf8dd062b0c2cd5f5433aff/%E8%98%91%E8%8F%87%E8%A1%97_7.1.3.150624001_713000.apk";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证两条任务id相同，未新建重复任务
        assertEquals("相同下载链接创建了重复任务", id1, id2);
        sleep(2);
    }

    public void testTwoThemeDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://market.xiaomi.com/thm/download/10a5e055-6f17-42dc-9a28-485276a26153?region=CN&homeOpenCount=2&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id1 = CaseUtils.addRelocateTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://market.xiaomi.com/thm/download/1457d8f7-0870-4ff3-a262-e29eed91dae9?region=CN&homeOpenCount=3&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证2条下载任务均在正常下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        sleep(1);
    }

    public void testThreeThemeDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://market.xiaomi.com/thm/download/8bfdca3a-0188-4f6a-b754-832611f909e2?region=CN&homeOpenCount=3&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id1 = CaseUtils.addRelocateTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://market.xiaomi.com/thm/download/b2fe3eb1-e0f9-4b2a-9ff8-a0ca9b95fd13?region=CN&homeOpenCount=3&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        //建立下载任务3
        String downloadUrl3 = "http://market.xiaomi.com/thm/download/b1d2e79d-2e80-488d-ad25-0fd58d708354?region=CN&homeOpenCount=4&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id3 = CaseUtils.addRelocateTask(downloadManager, downloadUrl3);
        sleep(5);
        //验证前两条任务在下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2, id3);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        //验证第三条任务未下载
        cursor.moveToPrevious();
        String title3 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK3 TITLE = " + title3);
        int status3 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK3 STATUS = " + StatusEnum.getName(status3));
        assertEquals("下载状态异常", 2, status1);
        int speed3 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed3 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed3 == 0);
        sleep(1);
    }

    public void testRepeatThemeDownload() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://market.xiaomi.com/thm/download/66b8dd17-ed26-4ea5-a06f-523fa644a55a?region=CN&homeOpenCount=4&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id1 = CaseUtils.addRelocateTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://market.xiaomi.com/thm/download/66b8dd17-ed26-4ea5-a06f-523fa644a55a?region=CN&homeOpenCount=4&imei=303a10fbba3fac25338d3e8965a53d01&downloadupdate=false&version=4.4.4_5.7.1-internal&system=miui&category=Compound&entryType=thememanager&freshInterval=0&alpha=false&device=virgo&language=zh_CN&isGlobal=false&capability=w%2Cb%2Cs%2Cv%3A6&apk=105&fromType=pagekeys_CompoundFine__%E7%B2%BE%E5%93%81%3ACompoundSortFree__%E5%85%8D%E8%B4%B9";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证两条任务id相同，未新建重复任务
        assertEquals("相同下载链接创建了重复任务", id1, id2);
        sleep(1);
    }

    public void testTwoVideoDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://data.vod.itc.cn/?new=/100/43/bltZE8qro1tABhSI635f0A.mp4&vid=1001842823&plat=17&mkey=DpxcVGiinL50w1uFAXFGmrHPP9fj8rX6&ch=tv&vid=2440001&uid=1435891440659902&plat=17&pt=5&prod=h5&pg=1&eye=0&cv=1.0.0&qd=680&src=1105&cateCode=101";
        long id1 = CaseUtils.addRelocateTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://jobsfe.funshion.com/play/v1/mp4/D8C578DAE01C84BAF273221B51F715AA718E586A.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjjq1t%2BIKrA%3D%3D";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证2条下载任务均在正常下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        sleep(1);
    }

    public void testThreeVideoDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://jobsfe.funshion.com/play/v1/mp4/3135EA67D916BFE45027A4E3B39A00F367EE8011.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjs%2Fv%2BKCFKw%3D%3D";
        long id1 = CaseUtils.addRelocateTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://jobsfe.funshion.com/play/v1/mp4/2C10B034AD5652B1AEE44D694566AA31FC279ACA.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjs%2FvEQTAQw%3D%3D";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        //建立下载任务3
        String downloadUrl3 = "http://data.vod.itc.cn/?new=/224/206/4m0nmuYsQoCL7uPikA3WVB.mp4&vid=1001785273&plat=17&mkey=Kh_g3xQzH8AlnYpVvDEl9GBmI1dHu0lg&ch=tv&vid=2348593&uid=1435891440659902&plat=17&pt=5&prod=h5&pg=1&eye=0&cv=1.0.0&qd=680&src=1105&cateCode=101";
        long id3 = CaseUtils.addRelocateTask(downloadManager, downloadUrl3);
        sleep(5);
        //验证前两条任务在下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2, id3);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        //验证第三条任务未下载
        cursor.moveToPrevious();
        String title3 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK3 TITLE = " + title3);
        int status3 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK3 STATUS = " + StatusEnum.getName(status3));
        assertEquals("下载状态异常", 2, status1);
        int speed3 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed3 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed3 == 0);
        sleep(1);
    }

    public void testRepeatVideoDownload() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://jobsfe.funshion.com/play/v1/mp4/cc1d10a9c379b47de207b39b6cd4cd7e0158e59f.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjjq16fQCWA%3D%3D";
        long id1 = CaseUtils.addRelocateTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://jobsfe.funshion.com/play/v1/mp4/cc1d10a9c379b47de207b39b6cd4cd7e0158e59f.mp4?token=OVKHzVc57%2BmVfV1qDkAtYcmYKqbLRsoRlzeW4LfisOWv6V12n0G%2BiWV0rXmhiMoRn4uvjjq16fQCWA%3D%3D";
        long id2 = CaseUtils.addRelocateTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证两条任务id相同，未新建重复任务
        assertEquals("相同下载链接创建了重复任务", id1, id2);
        sleep(1);
    }

    public void testTwoRadioDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026022251_24.m4a";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026014144_24.m4a";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证2条下载任务均在正常下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        sleep(1);
    }

    public void testThreeRadioDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026007064_24.m4a";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://aliod.qingting.fm/vod/00/00/0000000000000000000026001920_24.m4a";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        //建立下载任务3
        String downloadUrl3 = "http://aliod.qingting.fm/vod/00/00/0000000000000000000025987649_24.m4a";
        long id3 = CaseUtils.addTask(downloadManager, downloadUrl3);
        sleep(5);
        //验证前两条任务在下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2, id3);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        //验证第三条任务未下载
        cursor.moveToPrevious();
        String title3 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK3 TITLE = " + title3);
        int status3 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK3 STATUS = " + StatusEnum.getName(status3));
        assertEquals("下载状态异常", 2, status1);
        int speed3 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed3 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed3 == 0);
        sleep(1);
    }

    public void testRepeatRadioDownload() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://aliod.qingting.fm/vod/00/00/0000000000000000000025965634_24.m4a";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://aliod.qingting.fm/vod/00/00/0000000000000000000025965634_24.m4a";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证两条任务id相同，未新建重复任务
        assertEquals("相同下载链接创建了重复任务", id1, id2);
        sleep(1);
    }

    /*
    public void testTwoMusicDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://xz.97ting.com/content/01/246/1246233-MP3-128K-FTD-mp3.atmd?sig=Mzc1NDRlYjgxNGFkMzY3ZWEyNjYzNjhlMWQ5ZTY5MWE&exp=55af3103&transDeliveryCode=3E22F9FD4C70ACE8DF9FDB1C4A889CF68DABE27E4924BCA0BB09271E97BDE73BB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://xz.97ting.com/content/01/240/1240207-MP3-128K-FTD-mp3.atmd?sig=MzA3MzZhNTI5NmI1ZDQ0MGQ5NDJhNjEzNDcyNjJmNDU&exp=55af3105&transDeliveryCode=1AD2C4583066373FBFDA995ED31B17B0CB165A9236330B06BC020A26B0AEEAADB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证2条下载任务均在正常下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        sleep(1);
    }

    public void testThreeMusicDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://xz.97ting.com/content/01/246/1246235-MP3-128K-FTD-mp3.atmd?sig=NzlhZjc4OTFhYWU2NDI0NmFhOWM1NmEyYzJiMTMyM2M&exp=55af310e&transDeliveryCode=F8D2D94FC61891A672CA44E23B55ADFB65D15BD5B6586EE64F683B2E1FD52598B0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://xz.97ting.com/content/01/247/1247736-MP3-128K-FTD-mp3.atmd?sig=MTc4YWY1ZmRlODBhNTdiNTczMTYzNGE0MGExZmJhNzU&exp=55af3110&transDeliveryCode=C7E6D37EA2766D960C575016D87232421259E300335E235B74B94624ADE1C56FB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        //建立下载任务3
        String downloadUrl3 = "http://xz.97ting.com/content/01/247/1247101-MP3-128K-FTD-mp3.atmd?sig=NDM3ZDJkYzJhNGFjOTEzNTRlNmM2Y2FiYjZjMzhiYmY&exp=55af3112&transDeliveryCode=9596310CE4BDEA28448E9EE4C6422168835D01926FC0F426475954425387EE0AB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id3 = CaseUtils.addTask(downloadManager, downloadUrl3);
        sleep(5);
        //验证前两条任务在下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2, id3);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        //验证第三条任务未下载
        cursor.moveToPrevious();
        String title3 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK3 TITLE = " + title3);
        int status3 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK3 STATUS = " + StatusEnum.getName(status3));
        assertEquals("下载状态异常", 2, status1);
        int speed3 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed3 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed3 == 0);
        sleep(1);
    }

    public void testRepeatMusicDownload() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://xz.97ting.com/content/00/024/24374-MP3-128K-FTD-mp3.atmd?sig=ZTE4NjgyNDVmZWM5ZDRjMjFhN2IxNzkzN2ViZDMyYWU&exp=55af311c&transDeliveryCode=C12FD393B53354AF823BA1B35F9B8C332BBB4DA2D0018707376A3BF385B8C1FBB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://xz.97ting.com/content/00/024/24374-MP3-128K-FTD-mp3.atmd?sig=ZTE4NjgyNDVmZWM5ZDRjMjFhN2IxNzkzN2ViZDMyYWU&exp=55af311c&transDeliveryCode=C12FD393B53354AF823BA1B35F9B8C332BBB4DA2D0018707376A3BF385B8C1FBB0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证两条任务id相同，未新建重复任务
        assertEquals("相同下载链接创建了重复任务", id1, id2);
        sleep(1);
    }
    */

    public void testTwoBrowserDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://w.gdown.baidu.com/data/wisegame/ecce19775e7d340e/zhifubao_77.apk?f=m1101";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://w.gdown.baidu.com/data/wisegame/3fb982c0fe5e0a6e/lefengwang_200070.apk?f=m1101";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证2条下载任务均在正常下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        sleep(1);
    }

    public void testThreeBrowserDownloads() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://218.211.38.207/Download_Test/LargeFile/11mb.mp3";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://mirror.hust.edu.cn/gnu/emacs/emacs-23.2b.tar.bz2";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        //建立下载任务3
        String downloadUrl3 = "http://218.211.38.207/Download_Test/DRM/OMADL/testdd_cd.dd";
        long id3 = CaseUtils.addTask(downloadManager, downloadUrl3);
        sleep(5);
        //验证前两条任务在下载
        Cursor cursor = CaseUtils.selectTask(downloadManager, id1, id2, id3);
        String title1 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK1 TITLE = " + title1);
        int status1 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK1 STATUS = " + StatusEnum.getName(status1));
        assertEquals("下载状态异常", 2, status1);
        int speed1 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK1 SPEED = " + speed1 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed1 > 0);
        cursor.moveToPrevious();
        String title2 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK2 TITLE = " + title2);
        int status2 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK2 STATUS = " + StatusEnum.getName(status2));
        assertEquals("下载状态异常", 2, status2);
        int speed2 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed2 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed2 > 0);
        //验证第三条任务未下载
        cursor.moveToPrevious();
        String title3 = cursor.getString(cursor.getColumnIndex("title"));
        DebugLog.d("TEST", "TASK3 TITLE = " + title3);
        int status3 = cursor.getInt(cursor.getColumnIndex("status"));
        DebugLog.d("TEST", "TASK3 STATUS = " + StatusEnum.getName(status3));
        assertEquals("下载状态异常", 2, status1);
        int speed3 = cursor.getInt(cursor.getColumnIndex("downloading_current_speed"));
        DebugLog.d("TEST", "TASK2 SPEED = " + speed3 / 1024 + "KB/S");
        assertTrue("下载速度异常", speed3 == 0);
        sleep(1);
    }

    public void testRepeatBrowserDownload() {
        printDivideLine();
        //建立下载任务1
        String downloadUrl1 = "http://down.joygame.cn/joygame/qmqz-wappz-P13666A-0606.apk";
        long id1 = CaseUtils.addTask(downloadManager, downloadUrl1);
        //建立下载任务2
        String downloadUrl2 = "http://down.joygame.cn/joygame/qmqz-wappz-P13666A-0606.apk";
        long id2 = CaseUtils.addTask(downloadManager, downloadUrl2);
        sleep(5);
        //验证两条任务id相同，未新建重复任务
        assertEquals("相同下载链接创建了重复任务", id1, id2);
        sleep(1);
    }
}
