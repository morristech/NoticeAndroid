package lin.jiang.notice;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/3/23.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(this, Constant.ID_BUGLY, Constant.DEBUG);
        ShareSDK.initSDK(this);
    }
}
