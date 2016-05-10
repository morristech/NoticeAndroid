package lin.jiang.notice.domain.interactor;

import lin.jiang.notice.domain.entity.CommentList;
import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.repository.DataSource;

public class CommentListUseCase extends UseCase<CommentListUseCase.RequestValues, CommentListUseCase.ResponseValue> {
    DataSource dataSource;

    public CommentListUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getCommentList(requestValues._articleId, requestValues._startId, "" + requestValues.page, new DataSource.Callback<CommentList>() {
            @Override
            public void onSuccess(CommentList data) {
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
        public final String _articleId, _startId;
        public final int page = 10;

        public RequestValues(String _articleId, String _startId) {
            this._articleId = _articleId;
            this._startId = _startId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public final CommentList commentList;

        public ResponseValue(CommentList commentList) {
            this.commentList = commentList;
        }
    }
}
