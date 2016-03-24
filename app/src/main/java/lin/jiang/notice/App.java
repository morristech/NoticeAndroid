package lin.jiang.notice;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(this, Constant.ID_BUGLY, Constant.DEBUG);
        ShareSDK.initSDK(this);
        JPushInterface.init(this);
    }
}
