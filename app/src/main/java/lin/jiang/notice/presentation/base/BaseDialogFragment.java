package lin.jiang.notice.presentation.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;


import lin.jiang.notice.R;
import lin.jiang.notice.util.L;

public abstract class BaseDialogFragment extends DialogFragment implements OnClickListener {

    public Dialog dialog;

    public View getLayoutView() {
        return mView;
    }

    private View mView;

    public BaseDialogFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout((int) (dm.widthPixels), getDialog().getWindow().getAttributes().height);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(getActivity(), R.style.style_dialog_common);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mView = LayoutInflater.from(getContext()).inflate(getLayoutRes(), null);
        dialog.setContentView(mView);
        initView(mView);
        return dialog;
    }



    @Override
    public void dismiss() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return;
        }
        super.dismiss();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (IllegalStateException e) {
            L.e(e.getMessage());
        }
    }

    public interface OnClickEventCallback<T> {
        void handleEvent(T object);
    }

    protected abstract @LayoutRes int getLayoutRes();
    protected abstract void initView(View v);
}
