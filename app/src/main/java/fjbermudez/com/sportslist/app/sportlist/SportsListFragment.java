package fjbermudez.com.sportslist.app.sportlist;

import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

import fjbermudez.com.sportslist.app.base.BaseFragment;
import fjbermudez.com.sportslist.data.responses.SportListResponse;

public class SportsListFragment extends BaseFragment implements ISportsList {

    private static final String TAG = SportsListFragment.class.getSimpleName();

    private static final String LIST_SPORTS = "list_sports";
    private List<SportListResponse> sportListResponses;

    public static String getTAG() {
        return TAG;
    }

    public static SportsListFragment newInstance(List<SportListResponse> sportListResponses) {

        Bundle args = new Bundle();
        args.putSerializable(LIST_SPORTS, (Serializable) sportListResponses);

        SportsListFragment fragment = new SportsListFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
