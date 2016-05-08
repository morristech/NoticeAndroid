package lin.jiang.notice.presentation.base;

import android.app.Dialog;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import lin.jiang.notice.R;

/**
 * @author YangLinjiang
 * @date 2015-10-24下午5:55:46
 * @description 延迟加载, 针对于show() 和 hide()
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {

    private boolean canInit = true;

    /**
     * 进行初始化工作：比如获取数据；调用时期在onCreateView之后,onStart()之前
     */
    protected abstract void InitData();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            handInit();
        }
    }

    private void handInit() {
        if (canInit) {
            canInit = false;
            InitData();
        }
    }

    @Override
    public void onClick(View v) {
    }

    private Toast mToast;

    protected void showToast(String msg, boolean normal) {
        if (mToast == null) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.layout_toast, null);
            if (view instanceof TextView) {
                ((TextView) view).setText(msg);
            }
            mToast = new Toast(getActivity());
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
    protected void toastErr(String msg) {
        showToast(msg, false);
    }
    protected void toast(@StringRes int resId) {
        showToast(getString(resId), true);
    }
    protected void toast(String msg) {
        showToast(msg, true);
    }
}
