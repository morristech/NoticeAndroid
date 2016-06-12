package lin.jiang.notice.presentation.main;

import java.util.List;

import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.presentation.base.BasePresenter;
import lin.jiang.notice.presentation.base.BaseView;

/**
 * Created by Summer on 2016/6/7.
 */
public interface SearchContract {

    interface Presenter extends BasePresenter {
        void load(String param,String pageNum);
    }
    interface View extends BaseView<Presenter> {
        void showLoadingView();
        void showDataView( NewsList newsList);
        void showNoData();
    }

}
