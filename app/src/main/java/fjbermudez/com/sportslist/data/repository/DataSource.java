package fjbermudez.com.sportslist.data.repository;

import java.util.List;

import fjbermudez.com.sportslist.data.responses.SportListResponse;
import fjbermudez.com.sportslist.data.responses.SportListResponseError;

public interface DataSource {

    interface GetSportsListCallback{
        void onGetSportsListSuccess(List<SportListResponse> sportListResponseList);
        void onGetSportsListFailure(SportListResponseError sportListResponseError);
    }

    void getSportsList(GetSportsListCallback getSportsListCallback);
}
