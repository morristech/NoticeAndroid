package lin.jiang.notice.presentation.newsdetail;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import lin.jiang.notice.R;
import lin.jiang.notice.presentation.base.BaseActivtiy;
import lin.jiang.notice.presentation.base.BaseDialogFragment;
import lin.jiang.notice.util.StringUtil;

/**
 * Created by Summer on 2016/5/8.
 */
public class InputCommentDialog extends BaseDialogFragment {

    EditText editText;
    TextView textView;
    BaseActivtiy activtiy;
    OnClickEventCallback<String> callback;

    public InputCommentDialog() {
    }

    public InputCommentDialog(OnClickEventCallback<String> callback) {
        this.callback = callback;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.dialog_input_comment;
    }

    @Override
    protected void initView(View v) {
        editText = (EditText) v.findViewById(R.id._input_comment_edit);
        textView = (TextView) v.findViewById(R.id._input_comment_ok);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id._input_comment_ok) {
            if (StringUtil.isBlank(editText.getText().toString())) {
                if (activtiy!=null) activtiy.toastErr("请输入内容");
                return;
            }
            if (callback!=null) {
                callback.handleEvent(editText.getText().toString());
                dismiss();
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivtiy) {
            this.activtiy = (BaseActivtiy) activity;
        }
    }

    public static void newInstance(FragmentManager manager, String tag,OnClickEventCallback<String> callback) {
        InputCommentDialog dialog = new InputCommentDialog(callback);
        dialog.show(manager,tag);
    }
}
