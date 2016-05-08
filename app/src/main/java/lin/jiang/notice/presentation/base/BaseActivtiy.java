package lin.jiang.notice.presentation.base;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import lin.jiang.notice.R;

/**
 * Created by Summer on 2016/5/5.
 */
public class BaseActivtiy extends AppCompatActivity {

    private Toast mToast;

    protected void showToast(String msg, boolean normal) {
        if (mToast == null) {
            View view = getLayoutInflater().inflate(R.layout.layout_toast, null);
            if (view instanceof TextView) {
                ((TextView) view).setText(msg);
            }
            mToast = new Toast(this);
            mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 180);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(view);
        } else {
            if (mToast.getView() instanceof TextView) {
                ((TextView) mToast.getView()).setText(msg);
            }
        }
        if (normal) {
            mToast.getView().setBackgroundResource(R.drawable.shape_toast_bg);
        } else {
            mToast.getView().setBackgroundResource(R.drawable.shape_toast_bg_err);
        }
        mToast.show();
    }

    protected void toastErr(@StringRes int resId) {
        showToast(getString(resId), false);
    }

    public void toastErr(String msg) {
        showToast(msg, false);
    }

    protected void toast(@StringRes int resId) {
        showToast(getString(resId), true);
    }

    public void toast(String msg) {
        showToast(msg, true);
    }
}
