package com.xunlei.download.utils.ExcelUtil;

import android.content.Context;
import android.os.Environment;

import com.xunlei.download.utils.LogUtil.DebugLog;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 读取Excel文件
 */
public class ExcelReader {
    private final String excelName = "";

    public Workbook _wb;
    public Sheet _sheet;

    public ExcelReader(Context context) {
        try {
            InputStream is = context.getAssets().open(excelName);
            _wb = Workbook.getWorkbook(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            DebugLog.d("TEST", "excel文件读取错误!");
        } catch (BiffException e) {
            e.printStackTrace();
            DebugLog.d("TEST", "excel文件读取错误!");
        }
    }


    /**
     * 获取sheet数量
     *
     * @return
     */
    public int getSheetNum() {
        return _wb.getNumberOfSheets();
    }

    /**
     * 切换sheet
     *
     * @param index
     */
    public void switchSheet(int index) {
        _sheet = _wb.getSheet(index);
    }

    /**
     * 获取分类来源名称
     *
     * @return
     */
    public String getSourceName() {
        return _sheet.getName();
    }

    /**
     * 获取下载链接数量
     *
     * @return
     */
    public int getUrlNum() {
        return _sheet.getRows();
    }

    /**
     * 获取该页中所有下载链接Url
     *
     * @return
     */
    public ArrayList<String> getDownloadUrl() {
        ArrayList<String> urlList = new ArrayList<>();
        int rowNum = this.getUrlNum();
        for (int i = 0; i < rowNum; i++) {
            Cell cell = _sheet.getCell(0, i + 1);
            String url = cell.getContents();
            urlList.add(url);
        }
        return urlList;
    }
}
