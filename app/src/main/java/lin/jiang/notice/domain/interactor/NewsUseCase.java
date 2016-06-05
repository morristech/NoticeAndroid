package lin.jiang.notice.domain.interactor;

import java.util.ArrayList;
import java.util.List;

import lin.jiang.notice.domain.entity.NewsList;
import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.repository.DataSource;
import lin.jiang.notice.util.L;

/**
 * Created by Summer on 2016/5/4.
 */
public class NewsUseCase extends UseCase<NewsUseCase.RequestValues,NewsUseCase.ResponseValue> {

    DataSource dataSource;
    public NewsUseCase(DataSource dataSource) {
        this.dataSource =dataSource;
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getNewsList(requestValues.force,requestValues.type,
                requestValues.source, requestValues.startId, requestValues.pageNum, new DataSource.Callback<NewsList>() {
            @Override
            public void onSuccess(NewsList data) {
                L.i("NewsUseCase getNewsList onSuccess");
                if (data.getData() == null) {
                    data.setData(new ArrayList<NewsList.News>());
                }
                ResponseValue responseValue = new ResponseValue(data.getData());
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onError(int code, String msg) {
                L.i("NewsUseCase getNewsList onError");
                getUseCaseCallback().onError(new ResponeException(code,msg));
            }
        });

    }

    public static final class RequestValues implements UseCase.RequestValues {

        public boolean force;
        public String type,  source, startId,  pageNum;

        public RequestValues(boolean force,String type, String source, String startId, String pageNum) {
            this.force =force;
            this.type = type;
            this.source = source;
            this.startId = startId;
            this.pageNum = pageNum;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue {
        public final List<NewsList.News> news;
        public ResponseValue(List<NewsList.News> news) {
            this.news = news;
        }
    }
}
