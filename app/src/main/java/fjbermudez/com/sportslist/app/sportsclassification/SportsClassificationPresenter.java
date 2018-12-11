package fjbermudez.com.sportslist.app.sportsclassification;

import java.util.ArrayList;
import java.util.List;

import fjbermudez.com.sportslist.data.responses.SportListResponse;
import fjbermudez.com.sportslist.data.responses.SportListResponseError;
import fjbermudez.com.sportslist.domain.UseCase;
import fjbermudez.com.sportslist.domain.UseCaseHandler;
import fjbermudez.com.sportslist.domain.usecases.GetSportsListUseCase;

public class SportsClassificationPresenter implements ISportsClassificationPresenter {

    private ISportsClassification mView;

    private GetSportsListUseCase getSportsListUseCase;
    private UseCaseHandler mUseCaseHandler;
    private List<SportListResponse> sportListResponseList = new ArrayList<>();

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

                if(response != null) {
                    sportListResponseList = response.getSportListResponseList();

                    configureViewButtons(sportListResponseList);
                }
            }

            @Override
            public void onError(SportListResponseError responseError) {

                if(responseError != null) {
                    mView.showError(responseError.getError());
                }
            }
        });
    }

    /**
     * Method to configure visibility buttons of each sport
     * @param sportListResponseList
     */
    private void configureViewButtons(List<SportListResponse> sportListResponseList) {

        boolean visibilityGolf = false;
        boolean visibilityFootball = false;
        boolean visibilityTennis = false;
        boolean visibilityFormulaOne = false;

        for (SportListResponse sportListresponse:sportListResponseList) {
            if(sportListresponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_GOLF)){
                visibilityGolf = true;
            }else if(sportListresponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_FOOTBALL)){
                visibilityFootball = true;
            }else if(sportListresponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_TENNIS)){
                visibilityTennis = true;
            }else if(sportListresponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_FORMULA_ONE)){
                visibilityFormulaOne = true;
            }
        }

        mView.showSportsIcon(visibilityGolf,visibilityFootball,visibilityTennis,visibilityFormulaOne);
    }

    @Override
    public void goToGolfPlayers() {

        for (SportListResponse sportListResponse :sportListResponseList) {

            if(sportListResponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_GOLF)){
                mView.goToGolfPlayersView(sportListResponse);
                break;
            }
        }
    }

    @Override
    public void goToFootballPlayers() {
        for (SportListResponse sportListResponse :sportListResponseList) {

            if(sportListResponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_FOOTBALL)){
                mView.goToFootballPlayersView(sportListResponse);
                break;
            }
        }
    }

    @Override
    public void goToTennisPlayers() {
        for (SportListResponse sportListResponse :sportListResponseList) {

            if(sportListResponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_TENNIS)){
                mView.goToTennisPlayersView(sportListResponse);
                break;
            }
        }
    }

    @Override
    public void goToFormulaOnePlayers() {
        for (SportListResponse sportListResponse :sportListResponseList) {

            if(sportListResponse.getTitle().equalsIgnoreCase(SportsClassificationConstants.SPORT_FORMULA_ONE)){
                mView.goToFormulaOnePlayersView(sportListResponse);
                break;
            }
        }
    }
}
