package lin.jiang.notice;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

import cn.sharesdk.framework.ShareSDK;
import lin.jiang.notice.util.DeviceUtil;

/**
 * Created by Administrator on 2016/3/23.
 */
public class App extends Application {

    private static App application;

    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public static App instance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        CrashReport.initCrashReport(this, Constant.ID_BUGLY, Constant.DEBUG);
        ShareSDK.initSDK(this);

        deviceId = DeviceUtil.getID(this);
    }
}
