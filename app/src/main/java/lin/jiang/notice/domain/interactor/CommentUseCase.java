package lin.jiang.notice.domain.interactor;

import lin.jiang.notice.domain.entity.BaseEntity;
import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.repository.DataSource;

public class CommentUseCase extends UseCase<CommentUseCase.RequestValues, CommentUseCase.ResponseValue> {
    DataSource dataSource;

    public CommentUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.addComment(requestValues._articleId, requestValues._tool, requestValues._msg, new DataSource.Callback<BaseEntity>() {
            @Override
            public void onSuccess(BaseEntity data) {
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
        public final String _articleId, _tool, _msg;

        public RequestValues(String _articleId, String _tool, String _msg) {
            this._articleId = _articleId;
            this._tool = _tool;
            this._msg = _msg;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public final BaseEntity baseEntity;

        public ResponseValue(BaseEntity baseEntity) {
            this.baseEntity = baseEntity;
        }
    }
}
