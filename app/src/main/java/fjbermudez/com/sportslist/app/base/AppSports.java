package fjbermudez.com.sportslist.app.base;

import android.app.Application;
import android.content.Context;

public class AppSports extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getAppSportsContext() {
        return mContext;
    }
}