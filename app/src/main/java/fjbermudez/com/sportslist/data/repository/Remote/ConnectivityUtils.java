package fjbermudez.com.sportslist.data.repository.Remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import fjbermudez.com.sportslist.app.base.AppSports;

public class ConnectivityUtils {

    /**
     * Method to check network connectivity
     * @return
     */
    public static boolean isConnected(){

        ConnectivityManager cm =
                (ConnectivityManager) AppSports.getAppSportsContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;

        if (cm != null) {
            netInfo = cm.getActiveNetworkInfo();
        }

        if(netInfo!=null && netInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }
}
