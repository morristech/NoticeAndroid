package lin.jiang.notice.domain.excutor;

import lin.jiang.notice.domain.exception.ResponeException;
import lin.jiang.notice.domain.interactor.UseCase;

public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response, final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final UseCase.UseCaseCallback<V> useCaseCallback, final ResponeException e);
}