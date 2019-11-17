package cn.fjmz.agt;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table YHFC_INFO.
*/
public class YhfcInfoDao extends AbstractDao<YhfcInfo, Long> {

    public static final String TABLENAME = "YHFC_INFO";

    /**
     * Properties of entity YhfcInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property HTroubleID = new Property(1, String.class, "hTroubleID", false, "H_TROUBLE_ID");
        public final static Property IsYhzg = new Property(2, Boolean.class, "isYhzg", false, "IS_YHZG");
        public final static Property CheckDate = new Property(3, String.class, "checkDate", false, "CHECK_DATE");
        public final static Property LimitDate = new Property(4, String.class, "limitDate", false, "LIMIT_DATE");
        public final static Property TroubleGrade = new Property(5, String.class, "troubleGrade", false, "TROUBLE_GRADE");
        public final static Property CheckObject = new Property(6, String.class, "checkObject", false, "CHECK_OBJECT");
        public final static Property FinishDate = new Property(7, String.class, "finishDate", false, "FINISH_DATE");
        public final static Property SafetyTrouble = new Property(8, String.class, "safetyTrouble", false, "SAFETY_TROUBLE");
        public final static Property ActionOrgName = new Property(9, String.class, "actionOrgName", false, "ACTION_ORG_NAME");
        public final static Property EsCost = new Property(10, String.class, "esCost", false, "ES_COST");
        public final static Property DightCost = new Property(11, String.class, "dightCost", false, "DIGHT_COST");
        public final static Property LiabelEmid = new Property(12, String.class, "LiabelEmid", false, "LIABEL_EMID");
        public final static Property LiabelName = new Property(13, String.class, "LiabelName", false, "LIABEL_NAME");
        public final static Property AreaName = new Property(14, String.class, "areaName", false, "AREA_NAME");
        public final static Property InduName = new Property(15, String.class, "induName", false, "INDU_NAME");
        public final static Property ReviewDate = new Property(16, String.class, "reviewDate", false, "REVIEW_DATE");
    };


    public YhfcInfoDao(DaoConfig config) {
        super(config);
    }
    
    public YhfcInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'YHFC_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'H_TROUBLE_ID' TEXT," + // 1: hTroubleID
                "'IS_YHZG' INTEGER," + // 2: isYhzg
                "'CHECK_DATE' TEXT," + // 3: checkDate
                "'LIMIT_DATE' TEXT," + // 4: limitDate
                "'TROUBLE_GRADE' TEXT," + // 5: troubleGrade
                "'CHECK_OBJECT' TEXT," + // 6: checkObject
                "'FINISH_DATE' TEXT," + // 7: finishDate
                "'SAFETY_TROUBLE' TEXT," + // 8: safetyTrouble
                "'ACTION_ORG_NAME' TEXT," + // 9: actionOrgName
                "'ES_COST' TEXT," + // 10: esCost
                "'DIGHT_COST' TEXT," + // 11: dightCost
                "'LIABEL_EMID' TEXT," + // 12: LiabelEmid
                "'LIABEL_NAME' TEXT," + // 13: LiabelName
                "'AREA_NAME' TEXT," + // 14: areaName
                "'INDU_NAME' TEXT," + // 15: induName
                "'REVIEW_DATE' TEXT);"); // 16: reviewDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'YHFC_INFO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, YhfcInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String hTroubleID = entity.getHTroubleID();
        if (hTroubleID != null) {
            stmt.bindString(2, hTroubleID);
        }
 
        Boolean isYhzg = entity.getIsYhzg();
        if (isYhzg != null) {
            stmt.bindLong(3, isYhzg ? 1l: 0l);
        }
 
        String checkDate = entity.getCheckDate();
        if (checkDate != null) {
            stmt.bindString(4, checkDate);
        }
 
        String limitDate = entity.getLimitDate();
        if (limitDate != null) {
            stmt.bindString(5, limitDate);
        }
 
        String troubleGrade = entity.getTroubleGrade();
        if (troubleGrade != null) {
            stmt.bindString(6, troubleGrade);
        }
 
        String checkObject = entity.getCheckObject();
        if (checkObject != null) {
            stmt.bindString(7, checkObject);
        }
 
        String finishDate = entity.getFinishDate();
        if (finishDate != null) {
            stmt.bindString(8, finishDate);
        }
 
        String safetyTrouble = entity.getSafetyTrouble();
        if (safetyTrouble != null) {
            stmt.bindString(9, safetyTrouble);
        }
 
        String actionOrgName = entity.getActionOrgName();
        if (actionOrgName != null) {
            stmt.bindString(10, actionOrgName);
        }
 
        String esCost = entity.getEsCost();
        if (esCost != null) {
            stmt.bindString(11, esCost);
        }
 
        String dightCost = entity.getDightCost();
        if (dightCost != null) {
            stmt.bindString(12, dightCost);
        }
 
        String LiabelEmid = entity.getLiabelEmid();
        if (LiabelEmid != null) {
            stmt.bindString(13, LiabelEmid);
        }
 
        String LiabelName = entity.getLiabelName();
        if (LiabelName != null) {
            stmt.bindString(14, LiabelName);
        }
 
        String areaName = entity.getAreaName();
        if (areaName != null) {
            stmt.bindString(15, areaName);
        }
 
        String induName = entity.getInduName();
        if (induName != null) {
            stmt.bindString(16, induName);
        }
 
        String reviewDate = entity.getReviewDate();
        if (reviewDate != null) {
            stmt.bindString(17, reviewDate);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public YhfcInfo readEntity(Cursor cursor, int offset) {
        YhfcInfo entity = new YhfcInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // hTroubleID
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // isYhzg
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // checkDate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // limitDate
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // troubleGrade
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // checkObject
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // finishDate
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // safetyTrouble
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // actionOrgName
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // esCost
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // dightCost
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // LiabelEmid
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // LiabelName
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // areaName
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // induName
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16) // reviewDate
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, YhfcInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHTroubleID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIsYhzg(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setCheckDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setLimitDate(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTroubleGrade(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCheckObject(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFinishDate(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSafetyTrouble(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setActionOrgName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setEsCost(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setDightCost(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setLiabelEmid(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setLiabelName(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setAreaName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setInduName(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setReviewDate(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(YhfcInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(YhfcInfo entity) {
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