package lin.jiang.notice.domain.interactor;

import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.repository.DataSource;

/**
 * Created by Summer on 2016/5/10.
 */
public class SearchUseCase extends UseCase<SearchUseCase.RequestValues, SearchUseCase.ResponseValue> {
    DataSource dataSource;

    public SearchUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.search(requestValues._param, requestValues._pageNum, new DataSource.Callback<NewsList>() {
            @Override
            public void onSuccess(NewsList data) {
                if (data.isResult()) {
                    getUseCaseCallback().onSuccess(new ResponseValue(data));
                } else {
                    getUseCaseCallback().onError(new ResponeException(1103, data.getMsg()));
                }
            }

            @Override
            public void onError(int code, String msg) {
                getUseCaseCallback().onError(new ResponeException(code, msg));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        public final String _param, _pageNum;

        public RequestValues(String _param, String _pageNum) {
            this._param = _param;
            this._pageNum = _pageNum;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public final NewsList newsList;

        public ResponseValue(NewsList newsList) {
            this.newsList = newsList;
        }
    }
}
