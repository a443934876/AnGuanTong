package cn.fjmz.agt;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table YHZG_COMMIT_INFO.
*/
public class YhzgCommitInfoDao extends AbstractDao<YhzgCommitInfo, Long> {

    public static final String TABLENAME = "YHZG_COMMIT_INFO";

    /**
     * Properties of companyEntity YhzgCommitInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Troubleid = new Property(1, Integer.class, "troubleid", false, "TROUBLEID");
        public final static Property TroubleName = new Property(2, String.class, "troubleName", false, "TROUBLE_NAME");
        public final static Property FinishedDate = new Property(3, String.class, "finishedDate", false, "FINISHED_DATE");
        public final static Property Results = new Property(4, String.class, "results", false, "RESULTS");
        public final static Property Evalstr = new Property(5, String.class, "evalstr", false, "EVALSTR");
        public final static Property FactCost = new Property(6, String.class, "factCost", false, "FACT_COST");
        public final static Property Imgpath = new Property(7, String.class, "Imgpath", false, "IMGPATH");
    };


    public YhzgCommitInfoDao(DaoConfig config) {
        super(config);
    }
    
    public YhzgCommitInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'YHZG_COMMIT_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'TROUBLEID' INTEGER," + // 1: troubleid
                "'TROUBLE_NAME' TEXT," + // 2: troubleName
                "'FINISHED_DATE' TEXT," + // 3: finishedDate
                "'RESULTS' TEXT," + // 4: results
                "'EVALSTR' TEXT," + // 5: evalstr
                "'FACT_COST' TEXT," + // 6: factCost
                "'IMGPATH' TEXT);"); // 7: Imgpath
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'YHZG_COMMIT_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, YhzgCommitInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer troubleid = entity.getTroubleid();
        if (troubleid != null) {
            stmt.bindLong(2, troubleid);
        }
 
        String troubleName = entity.getTroubleName();
        if (troubleName != null) {
            stmt.bindString(3, troubleName);
        }
 
        String finishedDate = entity.getFinishedDate();
        if (finishedDate != null) {
            stmt.bindString(4, finishedDate);
        }
 
        String results = entity.getResults();
        if (results != null) {
            stmt.bindString(5, results);
        }
 
        String evalstr = entity.getEvalstr();
        if (evalstr != null) {
            stmt.bindString(6, evalstr);
        }
 
        String factCost = entity.getFactCost();
        if (factCost != null) {
            stmt.bindString(7, factCost);
        }
 
        String Imgpath = entity.getImgpath();
        if (Imgpath != null) {
            stmt.bindString(8, Imgpath);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public YhzgCommitInfo readEntity(Cursor cursor, int offset) {
        YhzgCommitInfo entity = new YhzgCommitInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // troubleid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // troubleName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // finishedDate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // results
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // evalstr
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // factCost
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // Imgpath
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, YhzgCommitInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTroubleid(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setTroubleName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFinishedDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setResults(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEvalstr(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFactCost(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setImgpath(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(YhzgCommitInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(YhzgCommitInfo entity) {
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
