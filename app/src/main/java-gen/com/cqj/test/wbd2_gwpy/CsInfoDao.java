package com.cqj.test.wbd2_gwpy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.cqj.test.wbd2_gwpy.CsInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CS_INFO.
*/
public class CsInfoDao extends AbstractDao<CsInfo, Long> {

    public static final String TABLENAME = "CS_INFO";

    /**
     * Properties of entity CsInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Mplid = new Property(1, String.class, "mplid", false, "MPLID");
        public final static Property Mplname = new Property(2, String.class, "mplname", false, "MPLNAME");
        public final static Property Is_choose = new Property(3, Boolean.class, "is_choose", false, "IS_CHOOSE");
    };


    public CsInfoDao(DaoConfig config) {
        super(config);
    }
    
    public CsInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CS_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'MPLID' TEXT," + // 1: mplid
                "'MPLNAME' TEXT," + // 2: mplname
                "'IS_CHOOSE' INTEGER);"); // 3: is_choose
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CS_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CsInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String mplid = entity.getMplid();
        if (mplid != null) {
            stmt.bindString(2, mplid);
        }
 
        String mplname = entity.getMplname();
        if (mplname != null) {
            stmt.bindString(3, mplname);
        }
 
        Boolean is_choose = entity.getIs_choose();
        if (is_choose != null) {
            stmt.bindLong(4, is_choose ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public CsInfo readEntity(Cursor cursor, int offset) {
        CsInfo entity = new CsInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // mplid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // mplname
            cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0 // is_choose
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CsInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMplid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMplname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIs_choose(cursor.isNull(offset + 3) ? null : cursor.getShort(offset + 3) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CsInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(CsInfo entity) {
        if(entity != null) {
            return entity.getId();
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