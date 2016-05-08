package lin.jiang.notice.presentation.newslist;

import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.excutor.UseCaseHandler;
import lin.jiang.notice.domain.interactor.NewsHotUseCase;
import lin.jiang.notice.domain.interactor.NewsUseCase;
import lin.jiang.notice.domain.interactor.UseCase;
import lin.jiang.notice.util.InjectUtil;
import lin.jiang.notice.util.L;

/**
 * Created by Summer on 2016/5/4.
 */
public class NewsPresenter implements NewsContract.Presenter {

    private int type;
    private NewsContract.View mView;
    private final NewsUseCase mNewsUseCase;
    private final NewsHotUseCase mNewsHotUseCase;

    public NewsPresenter(int type, NewsContract.View view) {
        this.type = type;
        mView = view;
        mView.setPresenter(this);
        mNewsUseCase = new NewsUseCase(InjectUtil.getDataSource());
        mNewsHotUseCase = new NewsHotUseCase(InjectUtil.getDataSource());
    }

    @Override
    public void loadData(boolean force) {
        String _type = null;
        switch (type) {
            case 0:
                loadHot(force);
                return;
            case 1:
                break;
            case 2:
                _type = "1";
                break;
            case 3:
                _type = "2";
                break;
        }
        load(force, _type, null, null, "20");
    }

    @Override
    public void loadMore(int startId) {
        mView.showHint("加载中……");
        String _type = null;
        switch (type) {
            case 0:
                return;
            case 1:
                break;
            case 2:
                _type = "1";
                break;
            case 3:
                _type = "2";
                break;
        }
        load(true, _type, null, ""+startId, "10");
    }

    @Override
    public void load(final boolean force, final String type, String source, final String startId, final String pageNum) {
        NewsUseCase.RequestValues requestValues = new NewsUseCase.RequestValues(force, type, source, startId, pageNum);
        UseCaseHandler.getInstance().execute(mNewsUseCase, requestValues, new UseCase.UseCaseCallback<NewsUseCase.ResponseValue>() {
            @Override
            public void onSuccess(NewsUseCase.ResponseValue response) {
                L.i("NewsPresenter load onSuccess," + force + " startId is " + startId);
                if (startId == null) {
                    if (response.news == null || response.news.size() == 0) {
                        mView.showNoneView();
                    } else {
                        mView.showHint("刷新完成");
                        mView.showDataView();
                    }
                    mView.notifyData(response.news);
                } else {
                    if (response.news == null) {
                        mView.showHint("没有更多了");
                    } else {
                        if (response.news.size() < Integer.parseInt(pageNum)) {
                            mView.showHint("全部加载完毕");
                        }
                        if (mView.getCurrentDatasSize()%10!=0) {
                            return;
                        }
                        mView.notifyDataMore(response.news);
                    }
                }
                if (!force) {
//                    loadData(true);
                }
            }

            @Override
            public void onError(ResponeException e) {
                L.i("NewsPresenter load onError, " + e.msg);
                mView.showErrorView();
                if (!force) {
                    loadData(true);
                }
            }
        });
    }

    @Override
    public void initData() {
        mView.showLoadingView();
        loadData(false);
    }

    private void loadHot(final boolean force) {
        NewsHotUseCase.RequestValues requestValues = new NewsHotUseCase.RequestValues(force);
        UseCaseHandler.getInstance().execute(mNewsHotUseCase, requestValues, new UseCase.UseCaseCallback<NewsHotUseCase.ResponseValue>() {
            @Override
            public void onSuccess(NewsHotUseCase.ResponseValue response) {
                L.i("NewsPresenter loadHot onSuccess," + force);
                if (response.news == null || response.news.size() == 0) {
                    mView.showNoneView();
                } else {
                    mView.showDataView();
                }
                mView.notifyData(response.news);
                if (!force) {
                    loadData(true);
                }
            }

            @Override
            public void onError(ResponeException e) {
                L.i("NewsPresenter loadHot onError, " + e.msg);
                mView.showErrorView();
                if (!force) {
                    loadData(true);
                }
            }
        });
    }
}
