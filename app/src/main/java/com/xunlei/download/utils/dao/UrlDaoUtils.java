package com.xunlei.download.utils.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class UrlDaoUtils {

    private static final String DB_NAME = "downloadUrl.db";
    private static final int DB_VERSION = 1;
    private static DaoSession sDaoSession;

    public synchronized static DaoSession getDaoSession(Context context) {
        if (sDaoSession == null) {
            SQLiteAssetHelper helper = new SQLiteAssetHelper(context, DB_NAME, null, DB_VERSION);
            helper.setForcedUpgrade();

            SQLiteDatabase database = helper.getWritableDatabase();
            DaoMaster m = new DaoMaster(database);

            sDaoSession = m.newSession();
        }
        return sDaoSession;
    }
}
