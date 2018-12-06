package fjbermudez.com.sportslist.app.sportsclassification;

import fjbermudez.com.sportslist.data.responses.SportListResponseError;
import fjbermudez.com.sportslist.domain.UseCase;
import fjbermudez.com.sportslist.domain.UseCaseHandler;
import fjbermudez.com.sportslist.domain.usecases.GetSportsListUseCase;

public class SportsClassificationPresenter implements ISportsClassificationPresenter {

    private ISportsClassification mView;

    private GetSportsListUseCase getSportsListUseCase;
    private UseCaseHandler mUseCaseHandler;

    public SportsClassificationPresenter(ISportsClassification mView,
                                         GetSportsListUseCase getSportsListUseCase,
                                         UseCaseHandler useCaseHandler) {
        this.mView = mView;
        this.getSportsListUseCase = getSportsListUseCase;
        this.mUseCaseHandler = useCaseHandler;
    }

    @Override
    public void getSportsList() {

        mUseCaseHandler.execute(getSportsListUseCase, new GetSportsListUseCase.RequestValues(), new UseCase.UseCaseCallback<GetSportsListUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetSportsListUseCase.ResponseValue response) {

                mView.showSportsIcon(response.getSportListResponse());
            }

            @Override
            public void onError(SportListResponseError responseError) {

                mView.showError(responseError.getError());
            }
        });
    }
}
