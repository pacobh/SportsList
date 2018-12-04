package fjbermudez.com.sportslist.data.repository;

import fjbermudez.com.sportslist.data.responses.SportListResponse;
import fjbermudez.com.sportslist.data.responses.SportListResponseError;

public interface DataSource {

    interface GetSportsListCallback{
        void onGetSportsListSuccess(SportListResponse sportListResponse);
        void onGetSportsListFailure(SportListResponseError sportListResponseError);
    }

    void getSportsList(GetSportsListCallback getSportsListCallback);
}
