package com.nti.nti_business_qrcode.application;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

import org.litepal.LitePal;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

/**
 * @author: weiqiyuan
 * @date: 2022/8/1
 * @describe
 */
public class MyApplication extends Application {

    private boolean isDebug = true;
    private boolean isOnline = false;
    private boolean isOffline = false;
    public static MyApplication myApp;

    public static Application getApplication() {
        return myApp;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setOffline(boolean offline) {
        isOffline = offline;
    }

    public static MyApplication getMyApp() {
        return myApp;
    }

    public static void setMyApp(MyApplication myApplication) {
        MyApplication.myApp = myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(false)
                .setSupportSubunits(Subunits.MM);
        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) ;

        LitePal.initialize(this);
    }
}
