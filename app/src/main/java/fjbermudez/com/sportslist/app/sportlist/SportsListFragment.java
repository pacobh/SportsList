package fjbermudez.com.sportslist.app.sportlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fjbermudez.com.sportslist.R;
import fjbermudez.com.sportslist.app.base.BaseFragment;
import fjbermudez.com.sportslist.data.responses.Players;
import fjbermudez.com.sportslist.data.responses.SportListResponse;

public class SportsListFragment extends BaseFragment implements ISportsList {


    @BindView(R.id.tvToolbarText)
    TextView tvToolbarText;
    @BindView(R.id.rvSport)
    RecyclerView rvSport;


    @OnClick(R.id.ivBackArrow)
    void goToLastFragment(){
        getActivity().onBackPressed();
    }

    private Unbinder unbinder;
    private SportsListAdapter sportsListAdapter;


    private static final String TAG = SportsListFragment.class.getSimpleName();

    private static final String LIST_PLAYERS = "list_players";
    private static final String NAME_SPORT = "name_sport";
    private List<Players> playersList;
    private String sportName = "";

    public static String getTAG() {
        return TAG;
    }

    public static SportsListFragment newInstance(String nameSport, List<Players> playersList) {

        Bundle args = new Bundle();
        args.putSerializable(LIST_PLAYERS, (Serializable) playersList);
        args.putString(NAME_SPORT,nameSport);

        SportsListFragment fragment = new SportsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = null;
        view = inflater.inflate(R.layout.sports_list_fragment,container,false);

        if(getArguments() != null && getArguments().getSerializable(LIST_PLAYERS) != null) {
            playersList = (List<Players>) getArguments().getSerializable(LIST_PLAYERS);
        }
        if(getArguments() != null && getArguments().getSerializable(NAME_SPORT) != null) {
            sportName = getArguments().getString(NAME_SPORT);
        }
        unbinder = ButterKnife.bind(this,view);

        tvToolbarText.setText(sportName);
        rvSport.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvSport.setHasFixedSize(true);
        sportsListAdapter = new SportsListAdapter(getContext(),playersList);
        rvSport.setAdapter(sportsListAdapter);

        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
