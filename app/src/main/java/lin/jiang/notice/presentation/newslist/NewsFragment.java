package lin.jiang.notice.presentation.newslist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lin.jiang.notice.R;
import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.presentation.base.BaseViewPagerFragment;
import lin.jiang.notice.presentation.componet.PullToRefreshView;
import lin.jiang.notice.presentation.newsdetail.DetailActivity;
import lin.jiang.notice.util.ImgLoadUtil;
import lin.jiang.notice.util.StringUtil;
import lin.jiang.notice.util.TimeUtil;
import lin.jiang.notice.util.ViewStateUtil;

public class NewsFragment extends BaseViewPagerFragment implements NewsContract.View, PullToRefreshView.OnRefreshListener, ViewStateUtil.OnStateClickListener {

    private static final String ARG_PAGE = "arg_page";
    private int PAGE = 0;
    private boolean canLoad = true;
    private List<NewsList.News> datas = new ArrayList<>();
    private NewsRecyclerViewAdapter mAdapter;
    private ViewStateUtil mViewStateUtil;
    private NewsContract.Presenter mPresenter;

    private PullToRefreshView pullToRefreshView;

    public NewsFragment() {
    }

    public static NewsFragment newInstance(int columnCount) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            PAGE = getArguments().getInt(ARG_PAGE);
        }
        new NewsPresenter(PAGE, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        initRecyclerView(view);
        mViewStateUtil = new ViewStateUtil(view, this);
        pullToRefreshView = (PullToRefreshView) view.findViewById(R.id._main_fragment_pulltorefreshview);
        pullToRefreshView.setOnRefreshListener(this);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id._main_fragment_recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter = new NewsRecyclerViewAdapter(datas, PAGE));
        // 推荐不添加“加载更多”
        if (PAGE == 0) return;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visible = layoutManager.getChildCount();
                int total = layoutManager.getItemCount();
                int past = layoutManager.findFirstCompletelyVisibleItemPosition();
                if ((visible + past) >= total && canLoad) {
                    canLoad = false;
                    mPresenter.loadMore(datas.get(datas.size() - 1).getId() - 1);
                }
            }
        });
    }

    @Override
    protected void InitData(boolean isFirst) {
        if (isFirst) mPresenter.initData();
    }

    @Override
    public void showLoadingView() {
        if (pullToRefreshView != null) pullToRefreshView.setRefreshing(false);
        if (mViewStateUtil != null) mViewStateUtil.changeState(ViewStateUtil.STATE.Loading);
    }

    @Override
    public void showErrorView() {
        if (pullToRefreshView != null) pullToRefreshView.setRefreshing(false);
        if (mViewStateUtil != null) mViewStateUtil.changeState(ViewStateUtil.STATE.ERROR);
    }

    @Override
    public void showNoneView() {
        if (pullToRefreshView != null) pullToRefreshView.setRefreshing(false);
        if (mViewStateUtil != null) mViewStateUtil.changeState(ViewStateUtil.STATE.EMPTY);
    }

    @Override
    public void showDataView() {
        if (pullToRefreshView != null) pullToRefreshView.setRefreshing(false);
        if (mViewStateUtil != null) mViewStateUtil.changeState(ViewStateUtil.STATE.NORMAL);
    }

    @Override
    public void notifyData(List<NewsList.News> lists) {
        canLoad = true;
        datas.clear();
        datas.addAll(lists);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyDataMore(List<NewsList.News> lists) {
        canLoad = true;
        datas.addAll(lists);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public int getCurrentDatasSize() {
        return datas.size();
    }

    @Override
    public void showHint(String msg) {
        toast(msg);
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        mPresenter.loadData(true);
    }

    @Override
    public void onStateClick(ViewStateUtil.STATE state) {
        if (state == ViewStateUtil.STATE.ERROR || state == ViewStateUtil.STATE.NET || state == ViewStateUtil.STATE.EMPTY) {
            mPresenter.initData();
        }
    }


    public static class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

        private final List<NewsList.News> mValues;
        private int PAGE;

        public NewsRecyclerViewAdapter(List<NewsList.News> items, int page) {
            mValues = items;
            PAGE = page;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_news, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tvTitle.setText(mValues.get(position).getTitle());
            holder.tvTime.setText(TimeUtil.formatToday(mValues.get(position).getTime()));
            holder.tvSource.setText(PAGE == 2 ? ("作者：" + mValues.get(position).getAuthor()) : ("来源：" + mValues.get(position).getSource()));
            if (!StringUtil.isBlank(mValues.get(position).getImg())) {
                holder.imageView.setVisibility(View.VISIBLE);
                ImgLoadUtil.load(mValues.get(position).getImg(), holder.imageView);
            } else holder.imageView.setVisibility(View.GONE);
            if (TimeUtil.isJustNow(mValues.get(position).getTime())) {
                holder.justNow.setVisibility(View.VISIBLE);
            } else holder.justNow.setVisibility(View.GONE);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DetailActivity.startAction(holder.imageView.getContext(),mValues.get(position).getId(),mValues.get(position).getTitle());
                }
            });
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
            public final View mView;
            public final TextView tvTitle;
            public final TextView tvTime;
            public final TextView tvSource;
            public final ImageView imageView;
            public final TextView justNow;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                tvTitle = (TextView) view.findViewById(R.id._item_news_title);
                tvTime = (TextView) view.findViewById(R.id._item_news_time);
                tvSource = (TextView) view.findViewById(R.id._item_news_source_author);
                imageView = (ImageView) view.findViewById(R.id._item_news_img);
                justNow = (TextView) view.findViewById(R.id._item_news_justnow);
            }
        }
    }

}
