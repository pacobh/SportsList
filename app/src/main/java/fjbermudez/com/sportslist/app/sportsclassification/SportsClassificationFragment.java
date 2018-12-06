package fjbermudez.com.sportslist.app.sportsclassification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fjbermudez.com.sportslist.Injection;
import fjbermudez.com.sportslist.R;
import fjbermudez.com.sportslist.app.base.BaseFragment;
import fjbermudez.com.sportslist.data.responses.SportListResponse;

public class SportsClassificationFragment extends BaseFragment implements ISportsClassification {

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

        view = inflater.inflate(R.layout.sports_list_fragment,container,false);

        unbinder = ButterKnife.bind(view);

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
    public void showSportsIcon(SportListResponse sportListResponse) {

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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
