package fjbermudez.com.sportslist.app.sportsclassification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fjbermudez.com.sportslist.Injection;
import fjbermudez.com.sportslist.R;
import fjbermudez.com.sportslist.app.base.BaseFragment;
import fjbermudez.com.sportslist.app.sportlist.SportsListFragment;
import fjbermudez.com.sportslist.data.responses.SportListResponse;

public class SportsClassificationFragment extends BaseFragment implements ISportsClassification {


    //region views
    @BindView(R.id.rlGolf)
    RelativeLayout rlGolf;

    @BindView(R.id.rlFootball)
    RelativeLayout rlFootball;

    @BindView(R.id.rlTennis)
    RelativeLayout rlTennis;

    @BindView(R.id.rlFormula1)
    RelativeLayout rlFormula1;

    //endregion

    //region onClick
    @OnClick(R.id.rlGolf)
    void goToGolfDetail(){
        mPresenter.goToGolfPlayers();
    }

    @OnClick(R.id.rlFootball)
    void goToFootballDetail(){
        mPresenter.goToFootballPlayers();
    }

    @OnClick(R.id.rlTennis)
    void goToTennisDetail(){
        mPresenter.goToTennisPlayers();
    }

    @OnClick(R.id.rlFormula1)
    void goToFormulaOneDetail(){
        mPresenter.goToFormulaOnePlayers();
    }
    //endregion
    private static final String TAG = SportsClassificationFragment.class.getSimpleName();

    public static String getTAG() {
        return TAG;
    }

    private ISportsClassificationPresenter mPresenter;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        view = inflater.inflate(R.layout.sports_classification_fragment,container,false);

        unbinder = ButterKnife.bind(this,view);

        mPresenter = new SportsClassificationPresenter(this, Injection.provideGetSportsListUseCase(getContext()),
                Injection.provideUseCaseHandler());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.getSportsList();
    }

    @Override
    public void showError(String error) {

        AlertDialog.Builder builder = null;

        builder.setTitle(getString(R.string.error_title)).setMessage(error).
                setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    @Override
    public void goToFormulaOnePlayersView(SportListResponse sportListResponse) {
        SportsListFragment sportsListFragment = SportsListFragment.newInstance(SportsClassificationConstants.SPORT_FORMULA_ONE,
                sportListResponse.getPlayersList());
        showSportFragment(sportsListFragment);

    }

    @Override
    public void goToTennisPlayersView(SportListResponse sportListResponse) {
        SportsListFragment sportsListFragment = SportsListFragment.newInstance(SportsClassificationConstants.SPORT_TENNIS,
                sportListResponse.getPlayersList());
        showSportFragment(sportsListFragment);

    }

    @Override
    public void goToFootballPlayersView(SportListResponse sportListResponse) {
        SportsListFragment sportsListFragment = SportsListFragment.newInstance(SportsClassificationConstants.SPORT_FOOTBALL,
                sportListResponse.getPlayersList());
        showSportFragment(sportsListFragment);

    }

    @Override
    public void goToGolfPlayersView(SportListResponse sportListResponse) {

        SportsListFragment sportsListFragment = SportsListFragment.newInstance(SportsClassificationConstants.SPORT_GOLF,
                sportListResponse.getPlayersList());

        showSportFragment(sportsListFragment);
    }

    private void showSportFragment(SportsListFragment sportsListFragment) {


        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.left_in, R.anim.left_out, R.anim.right_in, R.anim.right_out);
        transaction.replace(R.id.container, sportsListFragment, sportsListFragment.getTag());
        transaction.addToBackStack(sportsListFragment.getTag());
        transaction.commitAllowingStateLoss();

    }

    /**
     * Method to hide or show buttons
     * @param visibilityGolf
     * @param visibilityFootball
     * @param visibilityTennis
     * @param visibilityFormulaOne
     */
    @Override
    public void showSportsIcon(boolean visibilityGolf, boolean visibilityFootball, boolean visibilityTennis, boolean visibilityFormulaOne) {

        rlGolf.setVisibility((visibilityGolf) ? View.VISIBLE : View.GONE);
        rlFootball.setVisibility((visibilityFootball) ? View.VISIBLE : View.GONE);
        rlTennis.setVisibility((visibilityTennis) ? View.VISIBLE : View.GONE);
        rlFormula1.setVisibility((visibilityFormulaOne) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
