package lin.jiang.notice.util;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Summer on 2016/5/5.
 */
public class DeviceUtil {

    public static String getID(Context context) {
        String m_szImei, m_szDevIDShort, m_szWLANMAC, m_szAndroidID;
        // IMEI
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        m_szImei = TelephonyMgr.getDeviceId();
        // 硬件参数拼接
        m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;
        // Android ID
        m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        // WLAN MAC Address
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        m_szWLANMAC = wm.getConnectionInfo().getMacAddress();

        // MD5计算出32位
        String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID + m_szWLANMAC;
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (m == null) return null;
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        byte p_md5Data[] = m.digest();
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            if (b <= 0xF)
                m_szUniqueID += "0";
            m_szUniqueID += Integer.toHexString(b);
        }
        m_szUniqueID = m_szUniqueID.toUpperCase();
        return m_szUniqueID;
    }

    public static String getPlatform() {
        return "android_"+android.os.Build.VERSION.RELEASE;
    }

    public static String getModel() {
        return android.os.Build.MODEL;
    }

    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
