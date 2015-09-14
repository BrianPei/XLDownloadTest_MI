package com.xunlei.download.test.checklist;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;

public class AbnormalUrlTest extends BaseCase {

    public void testUnauthorizedBaiduPan() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://nj.poms.baidupcs.com/file/77f89787b1660cb99442ba9d0e9b055e?bkt=p2-nb-770&fid=3271990690-250528-610393273362237&time=1437648829&sign=FDTAXGERLBH-DCb740ccc5511e5e8fedcff06b081203-6fPMv5nqtzoPG0szEFlnOsGIm%2Bk%3D&to=nb&fm=Nan,B,T,t&sta_dx=10&sta_cs=0&sta_ft=pdf&sta_ct=5&fm2=Nanjing,B,T,t&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=140077f89787b1660cb99442ba9d0e9b055e69761aa5000000a648e6&sl=82116687&expires=8h&rt=pr&r=383742927&mlogid=830316151&vuk=3271990690&vbdid=3187414778&fin=%E9%BE%99%E8%85%BE%E4%B8%96%E7%BA%AA3%E7%B4%A0%E6%9D%90%E5%8F%8A%E5%88%B6%E4%BD%9C.pdf&fn=%E9%BE%99%E8%85%BE%E4%B8%96%E7%BA%AA3%E7%B4%A0%E6%9D%90%E5%8F%8A%E5%88%B6%E4%BD%9C.pdf&slt=pm&uta=0&rtype=1&iv=0";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testUnauthorized115Pan() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://fscdntel.115.com/gdown_group783/M00/0C/2A/CnUAHFR4AKMAAAAAAQxR7UfUD3A8087642/Wildlife.mov?k=RFVc6msJat2Xgo111Rg1fw&t=1437658191&u=1780960133-5070119-a9hfmchqx08eahfyq&s=102400&file=Wildlife.mov";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testUnauthorizedWeiYun() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://sz-btfs-yun-ftn.weiyun.com/ftn_handler/15130243bb3a945cff149e340df297930558e76488019feef95bae1eee38a81ecafbfc45c2c21d9feaa3a479227d1b4a26624e22ebb1fd61a67cd69e5837a771/?fname=%E7%A7%9F%E6%88%BF.doc&cv=30013&cn=0";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testUnauthorized360Pan() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://tb-dl2.yunpan.360.cn/intf.php?method=Download.downloadFile&qid=740402625&fname=%2F%E5%8D%AB%E5%85%B0-%E4%BD%A0%E7%9A%84%E7%9C%BC%E7%A5%9E.ape&fhash=6f923fd88834be9a11d72fae396469c2284bcf89&dt=43_43.9cdbece7538f799366eded1b5118673d&v=1.0.1&rtick=14373728635309&open_app_id=0&devtype=web&sign=a20a4b71b785ceac3f628d02d3d3193e&";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testOverdueMusic() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://xz.97ting.com/content/01/193/1193603-MP3-128K-FTD-mp3.atmd?sig=MGI5YWQ3ZTdkZjQ4NGI1Nzk3ZmI2NWQ5MmQ3OTE4NmI&exp=55960ab3&transDeliveryCode=8870B17A89CC8951169FBCE184318B99647DF60301680709DDB10CF5E1A628E2B0CC8A7CAFDF6BCE5A6554E5BEB22F7C";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl1() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://f41m.txtdown.com/chm/12/12524/12524.chm";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl2() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://d.vod.itc.cn/?new=/241/237/3KzX48MroWPKhIOsZyRKTH.mp4&vid=1001827481&plat=17&mkey=wj3jEKO2yVoQns7YPkGJoFe5OqUk4Lfn&ch=tv&vid=2416240&uid=1437552373918902&plat=17&pt=5&prod=h5&pg=1&eye=0&cv=1.0.0&qd=680&src=1105&cateCode=101";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl3() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://f4.@@@@market.mi-img.com/download/ThemeMarket/0c54474ebb3494bae05320082c722a5cb10fe7a0c/%E6%B0%A7PD-1.0.0.2.mtz";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl4() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://upodg.fm/vod/00/00/0000000000000000000026079877_24.m4a";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl5() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://sdl71.@@yunpan.cn/share.php?method=Share.download&cqid=7a4d4886a6170dc8b0328057147d1433&dt=71.bf152a5b09a0b686595682b316c2eb9c&e=1437623075&fhash=48e88aff478fed89e29779b11b945434e409a562&fname=DownloadProvider-Phone-150721-1137.zip&fsize=5005216&nid=14374499489482873&st=187a0385b94a9197f893d67645efec82&xqid=1392492380";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl6() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "https://e.hiphotos.baidu.com/zhidao/pic/item/d01373f082025aafd82e2468feedab64034f1a70.jpg";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl7() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "http://v.yicai.com/kalturaCE/content/entry/data/0/48/j78cjoxfsi_100000.flv aa";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }

    public void testAbnormalUrl8() {
        printDivideLine();
        //建立下载任务
        String downloadUrl = "https://dzs.qisuu.com/txt/8210.txt";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证结果
        CaseUtils.checkFailedResult(downloadManager, id);
        sleep(1);
    }
}
