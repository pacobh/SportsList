package fjbermudez.com.sportslist;

import android.content.Context;
import android.support.annotation.NonNull;

import fjbermudez.com.sportslist.data.repository.DataProvider;
import fjbermudez.com.sportslist.data.repository.Local.LocalDataSource;
import fjbermudez.com.sportslist.data.repository.Remote.RemoteDataSource;
import fjbermudez.com.sportslist.domain.UseCaseHandler;
import fjbermudez.com.sportslist.domain.usecases.GetSportsListUseCase;

public class Injection {

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    private static DataProvider provideDataSource(@NonNull Context context) {
        return DataProvider.getInstance(RemoteDataSource.getInstance(),
                LocalDataSource.getInstance(context));
    }

    public static GetSportsListUseCase provideGetSportsListUseCase(Context context) {
        return new GetSportsListUseCase(Injection.provideDataSource(context));
    }
}
