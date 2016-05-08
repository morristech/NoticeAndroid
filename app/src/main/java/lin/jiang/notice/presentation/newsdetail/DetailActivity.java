package lin.jiang.notice.presentation.newsdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

import lin.jiang.notice.R;
import lin.jiang.notice.domain.entity.CommentList;
import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.presentation.base.BaseActivtiy;
import lin.jiang.notice.presentation.base.BaseDialogFragment;
import lin.jiang.notice.util.DeviceUtil;
import lin.jiang.notice.util.StringUtil;
import lin.jiang.notice.util.TimeUtil;
import lin.jiang.notice.util.ViewStateUtil;

public class DetailActivity extends BaseActivtiy implements DetailContract.View, View.OnClickListener, ViewStateUtil.OnStateClickListener {
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_TITLE = "extra_title";

    private WebView mWebView;
    private BottomSheetBehavior behavior;
    private ViewStateUtil mViewStateUtil;
    private DetailContract.Presenter mPresenter;
    private CommentListAdapter mAdapter;

    public static void startAction(Context context, int aid, String title) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_ID, aid);
        intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id._detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(getIntent().getStringExtra(EXTRA_TITLE));
        toolbar.setNavigationIcon(R.drawable.abc_ic_clear_mtrl_alpha);
        toolbar.setNavigationOnClickListener(this);
        behavior = BottomSheetBehavior.from(findViewById(R.id._detail_recyclerview));
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        findViewById(R.id._detail_input).setOnClickListener(this);
        findViewById(R.id._detail_bar_wrapper).setOnClickListener(this);
        mViewStateUtil = new ViewStateUtil(getWindow().getDecorView(), this);

        initWebView();
        initData();
    }

    private void initData() {
        mWebView.loadUrl("http://115.159.63.67:8000/news/html/?_aid=" + getIntent().getIntExtra(EXTRA_ID, 0));
        mViewStateUtil.changeState(ViewStateUtil.STATE.NORMAL);
    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id._detail_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(null, "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._detail_input:
                InputCommentDialog.newInstance(getSupportFragmentManager(), "InputCommentDialog", new BaseDialogFragment.OnClickEventCallback<String>() {
                    @Override
                    public void handleEvent(String object) {
                    }
                });
                break;
            case R.id._detail_bar_wrapper:
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            default:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showNoneView() {

    }

    @Override
    public void showDataView() {

    }

    @Override
    public void showOriginView() {

    }

    @Override
    public void showAddCommentView() {

    }

    @Override
    public void showFileView() {

    }

    @Override
    public void notifyDetailJsonSuccess(NewsDetail newsDetail) {

    }

    @Override
    public void notifyCommentListJsonSuccess(List<CommentList.Comment> commentList) {

    }

    @Override
    public void notifyAddCommentSuccess() {

    }

    @Override
    public void notifyShareSuccess() {

    }

    @Override
    public void showHint(String msg) {

    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStateClick(ViewStateUtil.STATE state) {

    }


    public static class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

        private final List<CommentList.Comment> mValues;

        public CommentListAdapter(List<CommentList.Comment> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_comment, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tvTitle.setText(mValues.get(position).getMessage());
            holder.tvTime.setText(TimeUtil.convertUTC(mValues.get(position).getMessage()));
            holder.tvTool.setText(mValues.get(position).getTool());
            String id = mValues.get(position).getUserId();
            if (!StringUtil.isBlank(id)) {
                if (id.equals(DeviceUtil.getID(holder.isMe.getContext()))) {
                    holder.isMe.setVisibility(View.VISIBLE);
                } else holder.isMe.setVisibility(View.GONE);
            } else {
                holder.isMe.setVisibility(View.GONE);
            }

        }

        @Override
        public long getItemId(int position) {
            return mValues.get(position).getId();
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final TextView tvTitle;
            public final TextView tvTime;
            public final TextView tvTool;
            public final TextView isMe;

            public ViewHolder(View view) {
                super(view);
                tvTitle = (TextView) view.findViewById(R.id._item_comment_title);
                tvTime = (TextView) view.findViewById(R.id._item_comment_time);
                tvTool = (TextView) view.findViewById(R.id._item_comment_tool);
                isMe = (TextView) view.findViewById(R.id._item_comment_fromme);
            }
        }
    }
}