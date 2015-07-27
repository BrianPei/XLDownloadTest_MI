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
import com.xunlei.download.utils.ExcelUtil.ExcelReader;
import com.xunlei.download.utils.LogUtil.DebugLog;

import java.util.ArrayList;


public class MainActivity extends Activity {
    public DownloadManager downloadManager;
    public int index = 99;
    public int num, result;

    TextView textView;
    RadioGroup radioGroup;
    RadioButton radio1, radio2, radio3, radio4, radio5;
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
        button = (Button) findViewById(R.id.button);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radio1.getId()) {
                    index = 0;
                } else if (checkedId == radio2.getId()) {
                    index = 1;
                } else if (checkedId == radio3.getId()) {
                    index = 2;
                } else if (checkedId == radio4.getId()) {
                    index = 3;
                } else {
                    index = 4;
                }
            }
        });

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String text = editText.getText().toString();
                if (index < 10 && text != null) {
                    num = Integer.parseInt(text);
                    if (num <= 1000){
                        ExcelReader er = new ExcelReader(MainActivity.this);
                        er.switchSheet(index);
                        ArrayList<String> downloadList = er.getDownloadUrl();
                        for (int i = 0; i < num; i++) {
                            String downloadUrl = downloadList.get(i);
                            excute(downloadUrl);
                            result++;
                        }
                        showToast("执行成功，共插入" + result + "条下载任务");
                    } else {
                        showToast("最多只能插入1000条任务");
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
//        DebugLog.d("TEST", "URL = " + downloadUrl);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        String fileName = CaseUtils.getFileName(downloadUrl);
        request.setDestinationInExternalPublicDir("Download/download_test", fileName);
        long id = downloadManager.enqueue(request);
        DebugLog.d("TEST", "ID = " + id);
    }

    public void showToast(String content) {
        Toast toast = Toast.makeText(MainActivity.this, content, Toast.LENGTH_LONG);
        toast.show();
    }
}
