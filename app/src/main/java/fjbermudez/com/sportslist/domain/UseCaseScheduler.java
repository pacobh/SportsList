package fjbermudez.com.sportslist.domain;

import fjbermudez.com.sportslist.data.responses.SportListResponseError;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */
public interface UseCaseScheduler {

    void execute(Runnable runnable);

    <V extends UseCase.ResponseValue> void notifyResponse(final V response,
                                                          final UseCase.UseCaseCallback<V> useCaseCallback);

    <V extends UseCase.ResponseValue> void onError(final SportListResponseError responseError,
                                                   final UseCase.UseCaseCallback<V> useCaseCallback);
}
