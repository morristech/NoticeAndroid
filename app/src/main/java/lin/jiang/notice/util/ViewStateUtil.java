package lin.jiang.notice.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import lin.jiang.notice.R;

public class ViewStateUtil implements View.OnClickListener {

    private ImageView imageView;
    private TextView textView;
    private View vClick;
    private View wrapper;

    private OnStateClickListener listener;
    private STATE current;

    public ViewStateUtil(View decor) {
        this(decor, null);
    }

    public ViewStateUtil(View decor, OnStateClickListener listener) {
        if (decor == null) throw new RuntimeException("state layout's parent view cannot be null!");
        this.listener = listener;
        initView(decor);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onStateClick(current);
        }
    }

    public interface OnStateClickListener {
        void onStateClick(STATE state);
    }

    public enum STATE {
        NORMAL, EMPTY, ERROR, NET, Loading,
    }

    private void initView(View v) {
        imageView = (ImageView) v.findViewById(R.id._state_ic);
        textView = (TextView) v.findViewById(R.id._state_info);
        vClick = v.findViewById(R.id._state_click);
        wrapper = v.findViewById(R.id._state_wrapper);
        vClick.setOnClickListener(this);
    }

    public void changeState(STATE state) {
        imageView.clearAnimation();
        current = state;
        switch (state) {
            case NORMAL:
                wrapper.setVisibility(View.GONE);
                vClick.setEnabled(false);
                break;
            case EMPTY:
                wrapper.setVisibility(View.VISIBLE);
                vClick.setEnabled(true);
                imageView.setImageResource(R.drawable._bg_no_data);
                textView.setText(textView.getContext().getResources().getText(R.string._str_state_empty));
                break;
            case ERROR:
                wrapper.setVisibility(View.VISIBLE);
                vClick.setEnabled(true);
                imageView.setImageResource(R.drawable._bg_no_data);
                textView.setText(textView.getContext().getResources().getText(R.string._str_state_error));
                break;
            case NET:
                wrapper.setVisibility(View.VISIBLE);
                vClick.setEnabled(true);
                imageView.setImageResource(R.drawable._bg_no_net);
                textView.setText(textView.getContext().getResources().getText(R.string._str_state_net));
                break;
            case Loading:
                wrapper.setVisibility(View.VISIBLE);
                vClick.setEnabled(false);
                imageView.setImageResource(R.drawable._bg_loading);
                textView.setText(textView.getContext().getResources().getText(R.string._str_state_loading));
                imageView.startAnimation(AnimationUtils.loadAnimation(textView.getContext(),R.anim.anim_loading));
                break;
        }
    }
}
