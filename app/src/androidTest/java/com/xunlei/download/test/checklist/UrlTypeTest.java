package com.xunlei.download.test.checklist;

import com.xunlei.download.test.BaseCase;
import com.xunlei.download.utils.CaseUtils;

import junit.framework.Assert;


public class UrlTypeTest extends BaseCase {

    public void testBaiduImage() {
        printDivideLine();
        //建立百度图片下载链接
        String downloadUrl = "http://image.baidu.com/search/down?tn=download&word=download&ie=utf8&fr=wiseala&url=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1402%2F11%2Fc0%2F31161317_1392103056256.jpg";
        long id = CaseUtils.addWebTask(downloadManager, downloadUrl);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testHttps() {
        printDivideLine();
        //建立https下载链接
        String downloadUrl = "https://download.alipay.com/ukey/cf/alicert.exe";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testFtp() {
        printDivideLine();
        //建立ftp下载链接
        String downloadUrl = "ftp://ftp.ntu.edu.tw/gnu/emacs";
        try {
            CaseUtils.addTask(downloadManager, downloadUrl);
            //验证下载结果
            Assert.fail("ftp下载未报错");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 无法下载
    public void testBaiduPan() {
        printDivideLine();
        //建立百度网盘下载链接
        String downloadUrl = "http://nj.poms.baidupcs.com/file/77f89787b1660cb99442ba9d0e9b055e?bkt=p2-nb-770&fid=3271990690-250528-610393273362237&time=1437648829&sign=FDTAXGERLBH-DCb740ccc5511e5e8fedcff06b081203-6fPMv5nqtzoPG0szEFlnOsGIm%2Bk%3D&to=nb&fm=Nan,B,T,t&sta_dx=10&sta_cs=0&sta_ft=pdf&sta_ct=5&fm2=Nanjing,B,T,t&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=140077f89787b1660cb99442ba9d0e9b055e69761aa5000000a648e6&sl=82116687&expires=8h&rt=pr&r=383742927&mlogid=830316151&vuk=3271990690&vbdid=3187414778&fin=%E9%BE%99%E8%85%BE%E4%B8%96%E7%BA%AA3%E7%B4%A0%E6%9D%90%E5%8F%8A%E5%88%B6%E4%BD%9C.pdf&fn=%E9%BE%99%E8%85%BE%E4%B8%96%E7%BA%AA3%E7%B4%A0%E6%9D%90%E5%8F%8A%E5%88%B6%E4%BD%9C.pdf&slt=pm&uta=0&rtype=1&iv=0";
        long id = CaseUtils.addWebTask(downloadManager, downloadUrl);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void test115Pan() {
        printDivideLine();
        //建立115网盘下载链接
        String downloadUrl = "http://fscdntel.115.com/gdown_group783/M00/0C/2A/CnUAHFR4AKMAAAAAAQxR7UfUD3A8087642/Wildlife.mov?k=RFVc6msJat2Xgo111Rg1fw&t=1437658191&u=1780960133-5070119-a9hfmchqx08eahfyq&s=102400&file=Wildlife.mov";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void testWeiYun() {
        printDivideLine();
        //建立微云盘下载链接
        String downloadUrl = "http://sz-btfs-yun-ftn.weiyun.com/ftn_handler/15130243bb3a945cff149e340df297930558e76488019feef95bae1eee38a81ecafbfc45c2c21d9feaa3a479227d1b4a26624e22ebb1fd61a67cd69e5837a771/?fname=%E7%A7%9F%E6%88%BF.doc&cv=30013&cn=0";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void test360Pan() {
        printDivideLine();
        //建立360云盘下载链接
        String downloadUrl = "http://tb-dl2.yunpan.360.cn/intf.php?method=Download.downloadFile&qid=740402625&fname=%2F%E5%8D%AB%E5%85%B0-%E4%BD%A0%E7%9A%84%E7%9C%BC%E7%A5%9E.ape&fhash=6f923fd88834be9a11d72fae396469c2284bcf89&dt=43_43.9cdbece7538f799366eded1b5118673d&v=1.0.1&rtick=14373728635309&open_app_id=0&devtype=web&sign=a20a4b71b785ceac3f628d02d3d3193e&";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }

    public void test163Mail() {
        printDivideLine();
        //建立163邮箱下载链接
        String downloadUrl = "http://mail.163.com/js6/read/readdata.jsp?sid=CDDuZKddzKflBtXQkzddnzRMfuiuLLiK&mid=107:1S2maxfyVFD+J+KOlgAAsp&part=3&mode=download&l=read&action=download_attach";
        long id = CaseUtils.addTask(downloadManager, downloadUrl);
        //验证下载结果
        CaseUtils.checkDownloadResult(downloadManager, id);
        sleep(1);
    }
    */
}
