package lin.jiang.notice.domain.interactor;

import lin.jiang.notice.domain.entity.VisitNum;
import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.repository.DataSource;

public class VisitNumUseCase extends UseCase<VisitNumUseCase.RequestValues, VisitNumUseCase.ResponseValue> {
    DataSource dataSource;

    public VisitNumUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getVisitNum("" + requestValues.aid, new DataSource.Callback<VisitNum>() {
            @Override
            public void onSuccess(VisitNum data) {
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
        public final int aid;

        public RequestValues(int aid) {
            this.aid = aid;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public final VisitNum visitNum;

        public ResponseValue(VisitNum visitNum) {
            this.visitNum = visitNum;
        }
    }
}
