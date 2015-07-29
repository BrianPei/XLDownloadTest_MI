package com.xunlei.download.utils.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.xunlei.download.utils.dao.MAGNET;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MAGNET".
*/
public class MAGNETDao extends AbstractDao<MAGNET, Integer> {

    public static final String TABLENAME = "MAGNET";

    /**
     * Properties of entity MAGNET.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property ID = new Property(0, Integer.class, "ID", true, "ID");
        public final static Property URL = new Property(1, String.class, "URL", false, "URL");
    };


    public MAGNETDao(DaoConfig config) {
        super(config);
    }
    
    public MAGNETDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MAGNET\" (" + //
                "\"ID\" INTEGER PRIMARY KEY ," + // 0: ID
                "\"URL\" TEXT);"); // 1: URL
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MAGNET\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MAGNET entity) {
        stmt.clearBindings();
 
        Integer ID = entity.getID();
        if (ID != null) {
            stmt.bindLong(1, ID);
        }
 
        String URL = entity.getURL();
        if (URL != null) {
            stmt.bindString(2, URL);
        }
    }

    /** @inheritdoc */
    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public MAGNET readEntity(Cursor cursor, int offset) {
        MAGNET entity = new MAGNET( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1) // URL
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MAGNET entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setURL(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected Integer updateKeyAfterInsert(MAGNET entity, long rowId) {
        return entity.getID();
    }
    
    /** @inheritdoc */
    @Override
    public Integer getKey(MAGNET entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
