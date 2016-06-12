package lin.jiang.notice.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import lin.jiang.notice.R;
import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.presentation.base.BaseActivtiy;

/**
 * Created by Summer on 2016/6/7.
 */
public class SearchActivity extends BaseActivtiy implements View.OnClickListener, SearchContract.View {
    private FrameLayout frameLayout;
    private SearchFragment searchFragment;
    private EditText editText;
    private SearchContract.Presenter mPresenter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getArea();
        editText = (EditText) findViewById(R.id.search_edit);
        frameLayout = (FrameLayout) findViewById(R.id.search_frame);
        findViewById(R.id.search_btn).setOnClickListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.search_frame, searchFragment = new SearchFragment()).commit();
        frameLayout.setVisibility(View.INVISIBLE);
        new SearchPresenter(this);
    }
    public void getArea() {
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    @Override
    public void onClick(View v) {
        mPresenter.load(editText.getText().toString(), "20");
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void showDataView(NewsList newsList) {
        frameLayout.setVisibility(View.VISIBLE);
//        if (newsList.isResult()) {
            searchFragment.notifyData(newsList.getData());
//        } else {
//            showNoData();
//        }
    }

    @Override
    public void showNoData() {
        frameLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
