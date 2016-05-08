package lin.jiang.notice.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import lin.jiang.notice.R;

/**
 * @author YangLinjiang
 * @date 2015-10-24下午5:53:32
 * @description API 19+
 */
public class StatusBarUtil {
	/**
	 * 默认color_base的颜色
	 * @param act
	 */
	public static void applyBaseColor(Activity act) {
		applyCustomColor(act, act.getResources().getColor(R.color.colorPrimary));
	}
	/**
	 * 指定颜色
	 * @param act
	 * @param color
	 */
	@SuppressLint("NewApi")
	public static void applyCustomColor(Activity act, int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			act.getWindow().setStatusBarColor(color);
			return;
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup contentView = (ViewGroup) act.findViewById(android.R.id.content);
            View statusBarView = new View(act);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(act));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
		}
	}
	
	public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
	
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static void tranlateStatusBar(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		    Window window = ((Activity) context).getWindow();
		    window.setFlags(
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static void tranlateNavigationBar(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		    Window window = ((Activity) context).getWindow();
		    window.setFlags(
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static void tranlateStatusBar(Dialog context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
		    Window window = context.getWindow();
		    window.setFlags(
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
	}
	@TargetApi(Build.VERSION_CODES.KITKAT)
	public static void tranlateNavigationBarDialog(Dialog context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = context.getWindow();
		    window.setFlags(
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
		        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}
}
