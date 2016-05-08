package lin.jiang.notice.presentation.newslist;

import java.util.List;

import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.presentation.base.BasePresenter;
import lin.jiang.notice.presentation.base.BaseView;

/**
 * Created by Summer on 2016/5/4.
 */
public interface NewsContract {
    interface Presenter extends BasePresenter {
        void loadData(boolean force);
        void loadMore(int startId);
        void load(boolean force,String type,String source,String startId,String pageNum);
    }
    interface View extends BaseView<Presenter>{
        void showLoadingView();
        void showErrorView();
        void showNoneView();
        void showDataView();
        void notifyData(List<NewsList.News> lists);
        void notifyDataMore(List<NewsList.News> lists);
        int getCurrentDatasSize();
        void showHint(String msg);
    }
}
