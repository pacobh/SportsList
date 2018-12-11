package fjbermudez.com.sportslist.app.sportsclassification;

import java.util.List;

import fjbermudez.com.sportslist.data.responses.SportListResponse;

public interface ISportsClassification {


    void showError(String error);

    void goToFormulaOnePlayersView(SportListResponse sportListResponse);

    void goToTennisPlayersView(SportListResponse sportListResponse);

    void goToFootballPlayersView(SportListResponse sportListResponse);

    void goToGolfPlayersView(SportListResponse sportListResponse);

    void showSportsIcon(boolean visibilityGolf, boolean visibilityFootball, boolean visibilityTennis, boolean visibilityFormulaOne);
}
