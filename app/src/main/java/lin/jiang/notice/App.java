package lin.jiang.notice;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;

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
        PlatformConfig.setWeixin("wxfc17a2d23a946056", "fc9a65c851d8e5ab61c8e36010bda220");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        deviceId = DeviceUtil.getID(this);
    }
}
