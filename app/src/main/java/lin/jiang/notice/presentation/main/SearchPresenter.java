package lin.jiang.notice.presentation.main;

import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.excutor.UseCaseHandler;
import lin.jiang.notice.domain.interactor.SearchUseCase;
import lin.jiang.notice.domain.interactor.UseCase;
import lin.jiang.notice.util.InjectUtil;
import lin.jiang.notice.util.L;
import lin.jiang.notice.util.StringUtil;

/**
 * Created by Summer on 2016/6/7.
 */
public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private SearchUseCase searchUseCase;
    public SearchPresenter(SearchContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
        searchUseCase = new SearchUseCase(InjectUtil.getDataSource());
    }
    @Override
    public void load(String param, String pageNum) {
        if (StringUtil.isBlank(param)) {

            return;
        }
        SearchUseCase.RequestValues requestValues = new SearchUseCase.RequestValues(param,pageNum);
        UseCaseHandler.getInstance().execute(searchUseCase, requestValues, new UseCase.UseCaseCallback<SearchUseCase.ResponseValue>() {
            @Override
            public void onSuccess(SearchUseCase.ResponseValue response) {
                mView.showDataView(response.newsList);
            }

            @Override
            public void onError(ResponeException e) {
                mView.showNoData();
            }
        });
    }

    @Override
    public void initData() {

    }
}
