package lin.jiang.notice.presentation.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lin.jiang.notice.R;
import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.presentation.base.BaseFragment;
import lin.jiang.notice.presentation.newslist.NewsFragment;

public class SearchFragment extends BaseFragment {
    private NewsFragment.NewsRecyclerViewAdapter mAdapter;
    private List<NewsList.News> datas = new ArrayList<>();

    @Override
    protected void InitData() {

    }

    public void notifyData(List<NewsList.News> datas) {
        this.datas.clear();
        if (datas == null || datas.size() == 0) {
            //
        } else {
            this.datas.addAll(datas);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_list, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id._main_fragment_recyclerview);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter = new NewsFragment.NewsRecyclerViewAdapter(datas, 0));
    }

}
