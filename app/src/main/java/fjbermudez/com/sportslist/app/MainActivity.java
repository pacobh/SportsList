package fjbermudez.com.sportslist.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import fjbermudez.com.sportslist.R;
import fjbermudez.com.sportslist.app.base.BaseActivity;
import fjbermudez.com.sportslist.app.sportsclassification.SportsClassificationFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showInitialFragment();
    }

    private void showInitialFragment() {

        SportsClassificationFragment sportsClassificationFragment = new SportsClassificationFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.left_in, R.anim.left_out, R.anim.right_in, R.anim.right_out);
        transaction.replace(R.id.container, sportsClassificationFragment, sportsClassificationFragment.getTag());
        transaction.addToBackStack(sportsClassificationFragment.getTag());
        transaction.commitAllowingStateLoss();
    }
}
