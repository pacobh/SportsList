package fjbermudez.com.sportslist.domain.usecases;

import fjbermudez.com.sportslist.data.repository.DataSource;
import fjbermudez.com.sportslist.data.responses.SportListResponse;
import fjbermudez.com.sportslist.data.responses.SportListResponseError;
import fjbermudez.com.sportslist.domain.UseCase;

public class GetSportsListUseCase extends UseCase<GetSportsListUseCase.RequestValues, GetSportsListUseCase.ResponseValue> {

    private final DataSource dataSource;

    public GetSportsListUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getSportsList(new DataSource.GetSportsListCallback() {
            @Override
            public void onGetSportsListSuccess(SportListResponse sportListResponse) {
                getUseCaseCallback().onSuccess(new ResponseValue(sportListResponse));
            }

            @Override
            public void onGetSportsListFailure(SportListResponseError sportListResponseError) {
                getUseCaseCallback().onError(sportListResponseError);
            }
        });
    }

    /**
     * Conforma el tipo de petición
     */
    public static final class RequestValues implements UseCase.RequestValues {

        public RequestValues() {
        }

    }

    /**
     * Conforma el tipo de respuesta
     */
    public static final class ResponseValue implements UseCase.ResponseValue {
        private final SportListResponse sportListResponse;

        public ResponseValue(SportListResponse sportListResponse) {
            this.sportListResponse = sportListResponse;
        }

        public SportListResponse getSportListResponse() {
            return sportListResponse;
        }
    }


}
