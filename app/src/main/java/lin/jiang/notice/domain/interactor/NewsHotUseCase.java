package lin.jiang.notice.domain.interactor;

import java.util.ArrayList;
import java.util.List;

import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.repository.DataSource;
import lin.jiang.notice.util.L;

public class NewsHotUseCase extends UseCase<NewsHotUseCase.RequestValues,NewsHotUseCase.ResponseValue> {


    DataSource dataSource;
    public NewsHotUseCase(DataSource dataSource) {
        this.dataSource =dataSource;
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getNewsHot(requestValues.force,new DataSource.Callback<NewsList>() {
            @Override
            public void onSuccess(NewsList data) {
                L.i("NewsHotUseCase getNewsHot onSuccess");
                if (data.getData() == null) {
                    data.setData(new ArrayList<NewsList.News>());
                }
                ResponseValue responseValue = new ResponseValue(data.getData());
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onError(int code, String msg) {
                L.i("NewsHotUseCase getNewsHot onError");
                getUseCaseCallback().onError(new ResponeException(code,msg));
            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {
        public boolean force;

        public RequestValues(boolean force) {
            this.force =force;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue {
        public final List<NewsList.News> news;
        public ResponseValue(List<NewsList.News> news) {
            this.news = news;
        }
    }
}
