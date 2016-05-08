package lin.jiang.notice.domain.interactor;

import lin.jiang.notice.domain.entity.NewsDetail;
import lin.jiang.notice.domain.repository.DataSource;

/**
 * Created by Summer on 2016/5/7.
 */
public class NewsDetailUseCase extends UseCase<NewsDetailUseCase.RequestValues, NewsDetailUseCase.ResponseValue> {
    DataSource dataSource;

    public NewsDetailUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getNewsDetail("" + requestValues.aid, new DataSource.Callback<NewsDetail>() {
            @Override
            public void onSuccess(NewsDetail data) {

            }

            @Override
            public void onError(int code, String msg) {

            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        public int aid;

        public RequestValues(int aid) {
            this.aid = aid;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public final NewsDetail newsDetail;

        public ResponseValue(NewsDetail newsDetail) {
            this.newsDetail = newsDetail;
        }
    }
}
