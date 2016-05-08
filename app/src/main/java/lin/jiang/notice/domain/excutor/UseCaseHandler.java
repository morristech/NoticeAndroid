package lin.jiang.notice.domain.excutor;

import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.interactor.UseCase;

public class UseCaseHandler {

    private static UseCaseHandler INSTANCE;

    private final UseCaseThreadPoolScheduler mUseCaseScheduler;

    private UseCaseHandler(UseCaseThreadPoolScheduler useCaseScheduler) {
        mUseCaseScheduler = useCaseScheduler;
    }

    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute(
            final UseCase<T, R> useCase, T values, UseCase.UseCaseCallback<R> callback) {
        useCase.setRequestValues(values);
        useCase.setUseCaseCallback(new UiCallbackWrapper(callback, this));
        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }

    public <V extends UseCase.ResponseValue> void notifyResponse(final V response, final UseCase.UseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    private <V extends UseCase.ResponseValue> void notifyError(final UseCase.UseCaseCallback<V> useCaseCallback,ResponeException e) {
        mUseCaseScheduler.onError(useCaseCallback,e);
    }

    private static final class UiCallbackWrapper<V extends UseCase.ResponseValue> implements UseCase.UseCaseCallback<V> {
        private final UseCase.UseCaseCallback<V> mCallback;
        private final UseCaseHandler mUseCaseHandler;

        public UiCallbackWrapper(UseCase.UseCaseCallback<V> callback, UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        @Override
        public void onError(ResponeException e) {
            mUseCaseHandler.notifyError(mCallback,e);
        }
    }

    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }
}