package cn.fjmz.agt;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table JCB_DETAIL_INFO.
*/
public class JcbDetailInfoDao extends AbstractDao<JcbDetailInfo, Long> {

    public static final String TABLENAME = "JCB_DETAIL_INFO";

    /**
     * Properties of companyEntity JcbDetailInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Jcb_id = new Property(1, String.class, "jcb_id", false, "JCB_ID");
        public final static Property Oblititle = new Property(2, String.class, "oblititle", false, "OBLITITLE");
        public final static Property Odetail = new Property(3, String.class, "odetail", false, "ODETAIL");
        public final static Property SCheckListtype = new Property(4, String.class, "sCheckListtype", false, "S_CHECK_LISTTYPE");
        public final static Property Induname = new Property(5, String.class, "induname", false, "INDUNAME");
        public final static Property InseName = new Property(6, String.class, "inseName", false, "INSE_NAME");
        public final static Property MalName = new Property(7, String.class, "malName", false, "MAL_NAME");
        public final static Property RAdvise = new Property(8, String.class, "rAdvise", false, "R_ADVISE");
        public final static Property Is_choose = new Property(9, Boolean.class, "is_choose", false, "IS_CHOOSE");
    };


    public JcbDetailInfoDao(DaoConfig config) {
        super(config);
    }
    
    public JcbDetailInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'JCB_DETAIL_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'JCB_ID' TEXT," + // 1: jcb_id
                "'OBLITITLE' TEXT," + // 2: oblititle
                "'ODETAIL' TEXT," + // 3: odetail
                "'S_CHECK_LISTTYPE' TEXT," + // 4: sCheckListtype
                "'INDUNAME' TEXT," + // 5: induname
                "'INSE_NAME' TEXT," + // 6: inseName
                "'MAL_NAME' TEXT," + // 7: malName
                "'R_ADVISE' TEXT," + // 8: rAdvise
                "'IS_CHOOSE' INTEGER);"); // 9: is_choose
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'JCB_DETAIL_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, JcbDetailInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String jcb_id = entity.getJcb_id();
        if (jcb_id != null) {
            stmt.bindString(2, jcb_id);
        }
 
        String oblititle = entity.getOblititle();
        if (oblititle != null) {
            stmt.bindString(3, oblititle);
        }
 
        String odetail = entity.getOdetail();
        if (odetail != null) {
            stmt.bindString(4, odetail);
        }
 
        String sCheckListtype = entity.getSCheckListtype();
        if (sCheckListtype != null) {
            stmt.bindString(5, sCheckListtype);
        }
 
        String induname = entity.getInduname();
        if (induname != null) {
            stmt.bindString(6, induname);
        }
 
        String inseName = entity.getInseName();
        if (inseName != null) {
            stmt.bindString(7, inseName);
        }
 
        String malName = entity.getMalName();
        if (malName != null) {
            stmt.bindString(8, malName);
        }
 
        String rAdvise = entity.getRAdvise();
        if (rAdvise != null) {
            stmt.bindString(9, rAdvise);
        }
 
        Boolean is_choose = entity.getIs_choose();
        if (is_choose != null) {
            stmt.bindLong(10, is_choose ? 1l: 0l);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public JcbDetailInfo readEntity(Cursor cursor, int offset) {
        JcbDetailInfo entity = new JcbDetailInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // jcb_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // oblititle
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // odetail
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sCheckListtype
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // induname
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // inseName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // malName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // rAdvise
            cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0 // is_choose
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, JcbDetailInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setJcb_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setOblititle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOdetail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSCheckListtype(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setInduname(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setInseName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMalName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setRAdvise(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIs_choose(cursor.isNull(offset + 9) ? null : cursor.getShort(offset + 9) != 0);
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(JcbDetailInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(JcbDetailInfo entity) {
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
