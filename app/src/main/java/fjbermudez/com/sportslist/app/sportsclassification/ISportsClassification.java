package fjbermudez.com.sportslist.app.sportsclassification;

import java.util.List;

import fjbermudez.com.sportslist.data.responses.SportListResponse;

public interface ISportsClassification {

    void showSportsIcon(SportListResponse sportListResponse);

    void showError(String error);
}
