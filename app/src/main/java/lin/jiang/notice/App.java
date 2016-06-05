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
//微信    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx12342956d1cab4f9", "a5ae111de7d9ea137e88a5e02c07c94d");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        //新浪微博
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        deviceId = DeviceUtil.getID(this);
    }
}
