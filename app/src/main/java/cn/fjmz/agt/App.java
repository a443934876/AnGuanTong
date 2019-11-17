package cn.fjmz.agt;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import cn.fjmz.agt.activity.NetworkStateService;
import com.hjq.toast.ToastUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends MultiDexApplication {
    public static Context mContext;
    public static boolean isConnection = true;
    public static final String OFFLINE_DB = "offline_db";
    private CompanyInfo comInfo;
    public boolean isRefresh = true;


    public CompanyInfo getComInfo() {
        return comInfo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//		CrashHandler crashHandler = CrashHandler.getInstance() ;
//		crashHandler.init(this) ;
        // 在 Application 中初始化
        ToastUtils.init(this);
        realm();
        mContext = this;
        initLogger();
        //io.reactivex.exceptions.UndeliverableException:java.net.UnknownHostException
        setRxJavaErrorHandler();
    }

    private void realm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("agt.realm") //文件名
                .schemaVersion(2) //版本
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Intent intent = new Intent(this, NetworkStateService.class);
        stopService(intent);
    }

    public static Context getContext() {
        return mContext;
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 是否显示线程信息 默认显示 上图Thread Infrom的位置
                .methodCount(0)         // 展示方法的行数 默认是2  上图Method的行数
                .methodOffset(7)        // 内部方法调用向上偏移的行数 默认是0
//                .logStrategy(customLog) // 改变log打印的策略一种是写本地，一种是logcat显示 默认是后者（当然也可以自己定义）
                .tag("My custom tag")   // 自定义全局tag 默认：PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });
    }

    /**
     * RxJava2 当取消订阅后(dispose())，RxJava抛出的异常后续无法接收(此时后台线程仍在跑，可能会抛出IO等异常),全部由RxJavaPlugin接收，需要提前设置ErrorHandler
     * 详情：http://engineering.rallyhealth.com/mobile/rxjava/reactive/2017/03/15/migrating-to-rxjava-2.html#Error Handling
     */
    private void setRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
            }
        });
    }


}
