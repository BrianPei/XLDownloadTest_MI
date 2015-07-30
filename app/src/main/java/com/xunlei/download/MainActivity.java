package com.xunlei.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xunlei.download.test.R;
import com.xunlei.download.utils.CaseUtils;
import com.xunlei.download.utils.LogUtil.DebugLog;
import com.xunlei.download.utils.dao.AD;
import com.xunlei.download.utils.dao.ADDao;
import com.xunlei.download.utils.dao.DaoSession;
import com.xunlei.download.utils.dao.FTP;
import com.xunlei.download.utils.dao.FTPDao;
import com.xunlei.download.utils.dao.HTTP;
import com.xunlei.download.utils.dao.HTTPDao;
import com.xunlei.download.utils.dao.HTTPS;
import com.xunlei.download.utils.dao.HTTPSDao;
import com.xunlei.download.utils.dao.MAGNET;
import com.xunlei.download.utils.dao.MAGNETDao;
import com.xunlei.download.utils.dao.MARKET;
import com.xunlei.download.utils.dao.MARKETDao;
import com.xunlei.download.utils.dao.TESTURL;
import com.xunlei.download.utils.dao.TESTURLDao;
import com.xunlei.download.utils.dao.UrlDaoUtils;

import java.util.List;
import java.util.Random;


public class MainActivity extends Activity {
    private DownloadManager downloadManager;
    private String table;

    TextView textView;
    RadioGroup radioGroup;
    RadioButton radio1, radio2, radio3, radio4, radio5, radio6, radio7;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        textView = (TextView) findViewById(R.id.Title);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radio1 = (RadioButton) findViewById(R.id.radioButton1);
        radio2 = (RadioButton) findViewById(R.id.radioButton2);
        radio3 = (RadioButton) findViewById(R.id.radioButton3);
        radio4 = (RadioButton) findViewById(R.id.radioButton4);
        radio5 = (RadioButton) findViewById(R.id.radioButton5);
        radio6 = (RadioButton) findViewById(R.id.radioButton6);
        radio7 = (RadioButton) findViewById(R.id.radioButton7);
        button = (Button) findViewById(R.id.button);

        final DaoSession session = UrlDaoUtils.getDaoSession(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radio1.getId()) {
                    table = "market";
                } else if (checkedId == radio2.getId()) {
                    table = "http";
                } else if (checkedId == radio3.getId()) {
                    table = "magnet";
                } else if (checkedId == radio4.getId()) {
                    table = "https";
                } else if (checkedId == radio5.getId()) {
                    table = "ftp";
                } else if (checkedId == radio7.getId()) {
                    table = "ad";
                } else {
                    table = "testurl";
                }
            }
        });

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String text = editText.getText().toString();
                if (table != null && text != null) {
                    int num = Integer.parseInt(text);
                    switch (table) {
                        case "market":
                            MARKETDao marketDao = session.getMARKETDao();
                            insertMarketUrl(marketDao, num);
                            break;
                        case "http":
                            HTTPDao httpDao = session.getHTTPDao();
                            insertHttpUrl(httpDao, num);
                            break;
                        case "magnet":
                            MAGNETDao magnetDao = session.getMAGNETDao();
                            insertMagnetUrl(magnetDao, num);
                            break;
                        case "https":
                            HTTPSDao httpsDao = session.getHTTPSDao();
                            insertHttpsUrl(httpsDao, num);
                            break;
                        case "ftp":
                            FTPDao ftpDao = session.getFTPDao();
                            insertFtpUrl(ftpDao, num);
                            break;
                        case "ad":
                            ADDao adDao = session.getADDao();
                            insertADUrl(adDao, num);
                            break;
                        default:
                            TESTURLDao testurlDao = session.getTESTURLDao();
                            insertTestUrl(testurlDao, num);
                            break;
                    }
                } else {
                    showToast("任务来源或任务条数不能为空");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void excute(String downloadUrl) {
        DebugLog.d("TEST", "URL = " + downloadUrl);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        request.setDestinationInExternalPublicDir("Download/download_test", fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
    }

    public void excuteAD(String downloadUrl, String packageName) {
        DebugLog.d("TEST", "URL = " + downloadUrl);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        request.setDestinationInExternalPublicDir("Download/download_test", fileName);
        request.setApkPackageName(packageName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "TASK ID = " + id);
    }

    public void insertMarketUrl(MARKETDao marketDao, int num) {
        int[] ids = new int[num];
        for (int n = 0; n < num; n++) {
            ids[n] = n + 1;
        }
        int count = ids.length - 1;
        for (int i = 0; i < num; i++) {
            int index;
            //获取随机脚标
            if (count > 0) {
                Random random = new Random();
                index = random.nextInt(count) + 1;
            } else {
                index = 0;
            }
            int id = ids[index];
            //获取对应url，添加下载任务
            List<MARKET> marketList = marketDao.queryBuilder().where(MARKETDao.Properties.ID.eq(id)).build().forCurrentThread().list();
            MARKET market = marketList.get(0);
            String url = market.getURL();
            excute(url);
            //将获取到的随机id与数组最后一位交换，作为去重
            int temp = ids[index];
            ids[index] = ids[count];
            ids[count] = temp;
            count--;
        }
        showToast("执行成功，共插入" + num + "条Market下载任务");
    }

    public void insertHttpUrl(HTTPDao httpDao, int num) {
        int[] ids = new int[num];
        for (int n = 0; n < num; n++) {
            ids[n] = n + 1;
        }
        int count = ids.length - 1;
        for (int i = 0; i < num; i++) {
            int index;
            //获取随机脚标
            if (count > 0) {
                Random random = new Random();
                index = random.nextInt(count) + 1;
            } else {
                index = 0;
            }
            int id = ids[index];
            //获取对应url，添加下载任务
            List<HTTP> httpList = httpDao.queryBuilder().where(HTTPDao.Properties.ID.eq(id)).build().forCurrentThread().list();
            HTTP http = httpList.get(0);
            String url = http.getURL();
            excute(url);
            //将获取到的随机id与数组最后一位交换，作为去重
            int temp = ids[index];
            ids[index] = ids[count];
            ids[count] = temp;
            count--;
        }
        showToast("执行成功，共插入" + num + "条Http下载任务");
    }

    public void insertMagnetUrl(MAGNETDao magnetDao, int num) {
        int[] ids = new int[num];
        for (int n = 0; n < num; n++) {
            ids[n] = n + 1;
        }
        int count = ids.length - 1;
        for (int i = 0; i < num; i++) {
            int index;
            //获取随机脚标
            if (count > 0) {
                Random random = new Random();
                index = random.nextInt(count) + 1;
            } else {
                index = 0;
            }
            int id = ids[index];
            //获取对应url，添加下载任务
            List<MAGNET> magnetList = magnetDao.queryBuilder().where(MAGNETDao.Properties.ID.eq(id)).build().forCurrentThread().list();
            MAGNET magnet = magnetList.get(0);
            String url = magnet.getURL();
            excute(url);
            //将获取到的随机id与数组最后一位交换，作为去重
            int temp = ids[index];
            ids[index] = ids[count];
            ids[count] = temp;
            count--;
        }
        showToast("执行成功，共插入" + num + "条Magnet下载任务");
    }

    public void insertHttpsUrl(HTTPSDao httpsDao, int num) {
        int[] ids = new int[num];
        for (int n = 0; n < num; n++) {
            ids[n] = n + 1;
        }
        int count = ids.length - 1;
        for (int i = 0; i < num; i++) {
            int index;
            //获取随机脚标
            if (count > 0) {
                Random random = new Random();
                index = random.nextInt(count) + 1;
            } else {
                index = 0;
            }
            int id = ids[index];
            //获取对应url，添加下载任务
            List<HTTPS> httpsList = httpsDao.queryBuilder().where(HTTPSDao.Properties.ID.eq(id)).build().forCurrentThread().list();
            HTTPS https = httpsList.get(0);
            String url = https.getURL();
            excute(url);
            //将获取到的随机id与数组最后一位交换，作为去重
            int temp = ids[index];
            ids[index] = ids[count];
            ids[count] = temp;
            count--;
        }
        showToast("执行成功，共插入" + num + "条Https下载任务");
    }

    public void insertFtpUrl(FTPDao ftpDao, int num) {
        int[] ids = new int[num];
        for (int n = 0; n < num; n++) {
            ids[n] = n + 1;
        }
        int count = ids.length - 1;
        for (int i = 0; i < num; i++) {
            int index;
            //获取随机脚标
            if (count > 0) {
                Random random = new Random();
                index = random.nextInt(count) + 1;
            } else {
                index = 0;
            }
            int id = ids[index];
            //获取对应url，添加下载任务
            List<FTP> ftpList = ftpDao.queryBuilder().where(FTPDao.Properties.ID.eq(id)).build().forCurrentThread().list();
            FTP ftp = ftpList.get(0);
            String url = ftp.getURL();
            excute(url);
            //将获取到的随机id与数组最后一位交换，作为去重
            int temp = ids[index];
            ids[index] = ids[count];
            ids[count] = temp;
            count--;
        }
        showToast("执行成功，共插入" + num + "条Https下载任务");
    }

    public void insertTestUrl(TESTURLDao testurlDao, int num) {
        int[] ids = new int[num];
        for (int n = 0; n < num; n++) {
            ids[n] = n + 1;
        }
        int count = ids.length - 1;
        for (int i = 0; i < num; i++) {
            int index;
            //获取随机脚标
            if (count > 0) {
                Random random = new Random();
                index = random.nextInt(count) + 1;
            } else {
                index = 0;
            }
            int id = ids[index];
            //获取对应url，添加下载任务
            List<TESTURL> testurlList = testurlDao.queryBuilder().where(TESTURLDao.Properties.ID.eq(id)).build().forCurrentThread().list();
            TESTURL testurl = testurlList.get(0);
            String url = testurl.getURL();
            excute(url);
            //将获取到的随机id与数组最后一位交换，作为去重
            int temp = ids[index];
            ids[index] = ids[count];
            ids[count] = temp;
            count--;
        }
        showToast("执行成功，共插入" + num + "条Test下载任务");
    }

    public void insertADUrl(ADDao adDao, int num) {
        int[] ids = new int[num];
        for (int n = 0; n < num; n++) {
            ids[n] = n + 1;
        }
        int count = ids.length - 1;
        for (int i = 0; i < num; i++) {
            int index;
            //获取随机脚标
            if (count > 0) {
                Random random = new Random();
                index = random.nextInt(count) + 1;
            } else {
                index = 0;
            }
            int id = ids[index];
            //获取对应url，添加下载任务
            List<AD> adList = adDao.queryBuilder().where(ADDao.Properties.ID.eq(id)).build().forCurrentThread().list();
            AD ad = adList.get(0);
            String url = ad.getURL();
            DebugLog.d("TEST",url);
            String apkName = ad.getAPKNAME();
            DebugLog.d("TEST",apkName);
            excuteAD(url, apkName);
            //将获取到的随机id与数组最后一位交换，作为去重
            int temp = ids[index];
            ids[index] = ids[count];
            ids[count] = temp;
            count--;
        }
        showToast("执行成功，共插入" + num + "条Test下载任务");
    }

    public void showToast(String content) {
        Toast toast = Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG);
        toast.show();
    }
}
