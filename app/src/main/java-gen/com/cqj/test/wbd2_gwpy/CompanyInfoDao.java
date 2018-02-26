package com.cqj.test.wbd2_gwpy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.cqj.test.wbd2_gwpy.CompanyInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table COMPANY_INFO.
*/
public class CompanyInfoDao extends AbstractDao<CompanyInfo, Long> {

    public static final String TABLENAME = "COMPANY_INFO";

    /**
     * Properties of entity CompanyInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Com_id = new Property(1, String.class, "com_id", false, "COM_ID");
        public final static Property Emid = new Property(2, String.class, "emid", false, "EMID");
        public final static Property Com_fullname = new Property(3, String.class, "com_fullname", false, "COM_FULLNAME");
        public final static Property Com_name = new Property(4, String.class, "com_name", false, "COM_NAME");
        public final static Property Org_id = new Property(5, String.class, "org_id", false, "ORG_ID");
        public final static Property Org_idstr = new Property(6, String.class, "org_idstr", false, "ORG_IDSTR");
        public final static Property Is_choose = new Property(7, Boolean.class, "is_choose", false, "IS_CHOOSE");
    };


    public CompanyInfoDao(DaoConfig config) {
        super(config);
    }
    
    public CompanyInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'COMPANY_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'COM_ID' TEXT," + // 1: com_id
                "'EMID' TEXT," + // 2: emid
                "'COM_FULLNAME' TEXT," + // 3: com_fullname
                "'COM_NAME' TEXT," + // 4: com_name
                "'ORG_ID' TEXT," + // 5: org_id
                "'ORG_IDSTR' TEXT," + // 6: org_idstr
                "'IS_CHOOSE' INTEGER);"); // 7: is_choose
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'COMPANY_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CompanyInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String com_id = entity.getCom_id();
        if (com_id != null) {
            stmt.bindString(2, com_id);
        }
 
        String emid = entity.getEmid();
        if (emid != null) {
            stmt.bindString(3, emid);
        }
 
        String com_fullname = entity.getCom_fullname();
        if (com_fullname != null) {
            stmt.bindString(4, com_fullname);
        }
 
        String com_name = entity.getCom_name();
        if (com_name != null) {
            stmt.bindString(5, com_name);
        }
 
        String org_id = entity.getOrg_id();
        if (org_id != null) {
            stmt.bindString(6, org_id);
        }
 
        String org_idstr = entity.getOrg_idstr();
        if (org_idstr != null) {
            stmt.bindString(7, org_idstr);
        }
 
        Boolean is_choose = entity.getIs_choose();
        if (is_choose != null) {
            stmt.bindLong(8, is_choose ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public CompanyInfo readEntity(Cursor cursor, int offset) {
        CompanyInfo entity = new CompanyInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // com_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // emid
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // com_fullname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // com_name
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // org_id
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // org_idstr
            cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0 // is_choose
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CompanyInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCom_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEmid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCom_fullname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCom_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setOrg_id(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setOrg_idstr(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIs_choose(cursor.isNull(offset + 7) ? null : cursor.getShort(offset + 7) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(CompanyInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(CompanyInfo entity) {
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