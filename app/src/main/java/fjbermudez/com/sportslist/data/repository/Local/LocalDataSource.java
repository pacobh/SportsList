package fjbermudez.com.sportslist.data.repository.Local;

import android.content.Context;

import java.util.HashMap;

import fjbermudez.com.sportslist.data.repository.DataSource;

public class LocalDataSource implements DataSource {

    //region instace_methods

    private static LocalDataSource INSTANCE;
    private static HashMap<String, String> cache = new HashMap<>();

    private LocalDataSource(Context context) {

    }


    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }
    @Override
    public void getSportsList(GetSportsListCallback getSportsListCallback) {
        // Method not implemented
    }
}
