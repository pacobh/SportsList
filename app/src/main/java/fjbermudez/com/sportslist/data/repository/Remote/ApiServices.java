package fjbermudez.com.sportslist.data.repository.Remote;

import fjbermudez.com.sportslist.data.responses.SportListResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("/bins/66851")
    Call<SportListResponse> getSportsList();
}
