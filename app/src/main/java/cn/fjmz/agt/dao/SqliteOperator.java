package cn.fjmz.agt.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cn.fjmz.agt.App;
import cn.fjmz.agt.AqjcCommitInfoDao;
import cn.fjmz.agt.CompanyInfoDao;
import cn.fjmz.agt.CsInfoDao;
import cn.fjmz.agt.DaoMaster;
import cn.fjmz.agt.DaoSession;
import cn.fjmz.agt.JcbDetailInfoDao;
import cn.fjmz.agt.JcbInfoDao;
import cn.fjmz.agt.RwInfoDao;
import cn.fjmz.agt.SbInfoDao;
import cn.fjmz.agt.SbjcCommitInfoDao;
import cn.fjmz.agt.SbjcListInfoDao;
import cn.fjmz.agt.UserInfoDao;
import cn.fjmz.agt.YhfcCommitInfoDao;
import cn.fjmz.agt.YhfcInfoDao;
import cn.fjmz.agt.YhzgCommitInfoDao;

/**
 *
 * Created by Administrator on 2016/3/7.
 */
public enum  SqliteOperator {
    INSTANCE;

    private UserInfoDao mUserInfoDao;
    private CompanyInfoDao mCompanyInfoDao;
    private RwInfoDao mRwInfoDao;
    private CsInfoDao mCsInfoDao;
    private JcbInfoDao mJcbInfoDao;
    private JcbDetailInfoDao mJcbDetailInfoDao;
    private SbInfoDao mSbInfoDao;
    private AqjcCommitInfoDao mCommitInfoDao;
    private SbjcCommitInfoDao mSbCommitDao;
    private SbjcListInfoDao mSbjcListInfoDao;
    private YhfcInfoDao mYhfcInfoDao;
    private YhfcCommitInfoDao mYhfcCommitInfoDao;
    private YhzgCommitInfoDao mYhzgCommitInfoDao;

    public SbjcListInfoDao getSbjcListInfoDao(Context context){
        if(mSbjcListInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(), App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mSbjcListInfoDao =sDaoSession.getSbjcListInfoDao();
        }
        return mSbjcListInfoDao;
    }

    public SbjcCommitInfoDao getSbCommitDao(Context context){
        if(mSbCommitDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mSbCommitDao =sDaoSession.getSbjcCommitInfoDao();
        }
        return mSbCommitDao;
    }

    public UserInfoDao getUserInfoDao(Context context){
        if(mUserInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mUserInfoDao =sDaoSession.getUserInfoDao();
        }
        return mUserInfoDao;
    }

    public CompanyInfoDao getCompanyInfoDao(Context context){
        if(mCompanyInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mCompanyInfoDao =sDaoSession.getCompanyInfoDao();
        }
        return mCompanyInfoDao;
    }

    public RwInfoDao getRwInfoDao(Context context){
        if(mRwInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mRwInfoDao =sDaoSession.getRwInfoDao();
        }
        return mRwInfoDao;
    }

    public CsInfoDao getCsInfoDao(Context context){
        if(mCsInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mCsInfoDao =sDaoSession.getCsInfoDao();
        }
        return mCsInfoDao;
    }

    public SbInfoDao getSbInfoDao(Context context){
        if(mSbInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mSbInfoDao =sDaoSession.getSbInfoDao();
        }
        return mSbInfoDao;
    }

    public JcbInfoDao getJcbInfoDao(Context context){
        if(mJcbInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mJcbInfoDao =sDaoSession.getJcbInfoDao();
        }
        return mJcbInfoDao;
    }

    public JcbDetailInfoDao getJcbDetailInfoDao(Context context){
        if(mJcbDetailInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mJcbDetailInfoDao =sDaoSession.getJcbDetailInfoDao();
        }
        return mJcbDetailInfoDao;
    }

    public YhfcInfoDao getYhfcInfoDao(Context context){
        if(mYhfcInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mYhfcInfoDao =sDaoSession.getYhfcInfoDao();
        }
        return mYhfcInfoDao;
    }

    public AqjcCommitInfoDao getCommitInfo(Context context){
        if(mCommitInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mCommitInfoDao =sDaoSession.getAqjcCommitInfoDao();
        }
        return mCommitInfoDao;
    }

    public YhfcCommitInfoDao getYhfcCommitInfo(Context context){
        if(mYhfcCommitInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mYhfcCommitInfoDao =sDaoSession.getYhfcCommitInfoDao();
        }
        return mYhfcCommitInfoDao;
    }

    public YhzgCommitInfoDao getYhzgCommitInfo(Context context){
        if(mYhzgCommitInfoDao ==null){
            DaoMaster.DevOpenHelper helper =new DaoMaster.DevOpenHelper(context.getApplicationContext(),App.OFFLINE_DB,null);
            SQLiteDatabase sqLiteDatabase =helper.getWritableDatabase();
            DaoMaster daoMaster =new DaoMaster(sqLiteDatabase);
            DaoSession sDaoSession = daoMaster.newSession();
            mYhzgCommitInfoDao =sDaoSession.getYhzgCommitInfoDao();
        }
        return mYhzgCommitInfoDao;
    }


}
