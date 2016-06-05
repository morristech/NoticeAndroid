package lin.jiang.notice.presentation.newsdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lin.jiang.notice.R;
import lin.jiang.notice.domain.entity.CommentList;
import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.entity.VisitNum;
import lin.jiang.notice.presentation.base.BaseActivtiy;
import lin.jiang.notice.presentation.base.BaseDialogFragment;
import lin.jiang.notice.util.DeviceUtil;
import lin.jiang.notice.util.StringUtil;
import lin.jiang.notice.util.TimeUtil;
import lin.jiang.notice.util.ViewStateUtil;

public class DetailActivity extends BaseActivtiy implements DetailContract.View, View.OnClickListener, ViewStateUtil.OnStateClickListener {
    public static final String EXTRA_ID = "extra_id";
    public static final String EXTRA_TITLE = "extra_title";

    private TextView tvRead, tvComment;
    private RecyclerView rcListView;
    private WebView mWebView;
    private BottomSheetBehavior behavior;
    private ViewStateUtil mViewStateUtil;
    private DetailContract.Presenter mPresenter;
    private CommentListAdapter mAdapter;
    private NewsDetail newsDetail;
    private List<CommentList.Comment> commentList = new ArrayList<>();

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
        initView();
        initWebView();

        mViewStateUtil = new ViewStateUtil(getWindow().getDecorView(), this);
        new DetailPresenter(getIntent().getIntExtra(EXTRA_ID, 0), this);
        mPresenter.initData();
    }

    private void initView() {
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

        tvRead = (TextView) findViewById(R.id._detail_read_num);
        tvComment = (TextView) findViewById(R.id._detail_comment_num);
        rcListView = (RecyclerView) findViewById(R.id._detail_recyclerview);
        rcListView.setLayoutManager(new LinearLayoutManager(this));
        rcListView.setAdapter(mAdapter = new CommentListAdapter(commentList));
    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id._detail_webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(null, "");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showLoadingView();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                showDataView();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                showErrorView();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._detail_input:
                showAddCommentView();
                break;
            case R.id._detail_bar_wrapper:
//                if (commentList.size() == 0) {
//                    showHint("暂无评论");
//                    return;
//                }
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
            mPresenter.doShare(this,newsDetail);
            return true;
        } else if (id == R.id.action_origin) {
            showOriginView();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingView() {
        mViewStateUtil.changeState(ViewStateUtil.STATE.Loading);
    }

    @Override
    public void showErrorView() {
        mViewStateUtil.changeState(ViewStateUtil.STATE.ERROR);
    }

    @Override
    public void showNoneView() {
        mViewStateUtil.changeState(ViewStateUtil.STATE.EMPTY);
    }

    @Override
    public void showDataView() {
        mViewStateUtil.changeState(ViewStateUtil.STATE.NORMAL);
    }

    @Override
    public void showOriginView() {
        mWebView.loadUrl(newsDetail.getData().getArticle().getUrl());
    }

    @Override
    public void showAddCommentView() {
        InputCommentDialog.newInstance(getSupportFragmentManager(), "InputCommentDialog", new BaseDialogFragment.OnClickEventCallback<String>() {
            @Override
            public void handleEvent(String object) {
                mPresenter.addComment(object);
            }
        });
    }

    @Override
    public void showFileView() {

    }

    @Override
    public void notifyDetailJsonSuccess(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }

    @Override
    public void notifyCommentListJsonSuccess(List<CommentList.Comment> commentList) {
        this.commentList.clear();
        this.commentList.addAll(commentList);
        tvComment.setText(""+commentList.size());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyVisitNumJsonSuccess(VisitNum visitNum) {
        tvRead.setText(""+visitNum.getData().getNum());
    }

    @Override
    public void notifyAddCommentSuccess() {
        mPresenter.loadCommentListJson();
        toast("评论成功");
    }

    @Override
    public void notifyShareSuccess() {

    }

    @Override
    public void showHint(String msg) {
        toast(msg);
    }

    @Override
    public WebView getWebView() {
        return mWebView;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_comment, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tvTitle.setText(mValues.get(position).getMessage());
            holder.tvTime.setText(TimeUtil.convertUTC(mValues.get(position).getDatetime()));
            holder.tvTool.setText(mValues.get(position).getTool());
            String id = mValues.get(position).getDeviceId();
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