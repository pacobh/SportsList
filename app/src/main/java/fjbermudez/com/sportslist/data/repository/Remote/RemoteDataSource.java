package fjbermudez.com.sportslist.data.repository.Remote;

import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fjbermudez.com.sportslist.BuildConfig;
import fjbermudez.com.sportslist.R;
import fjbermudez.com.sportslist.app.base.AppSports;
import fjbermudez.com.sportslist.data.repository.DataSource;
import fjbermudez.com.sportslist.data.responses.SportListResponse;
import fjbermudez.com.sportslist.data.responses.SportListResponseError;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {

    private static final String BASE_URL = "https://api.myjson.com";

    private ApiServices apiServices;
    private static RemoteDataSource INSTANCE;


    public RemoteDataSource() {

        OkHttpClient httpClient = configureOkHttpClient();

        Gson gson = new Gson();

        if (BuildConfig.DEBUG) {
            gson = new GsonBuilder().setLenient().create();
        }
//        Type collectionType = new TypeToken<Collection<SportListResponse>>(){}.getType();
//        List<SportListResponse> posts = gson.fromJson(jsonOutput, listType);
//        gson.

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(getBaseUrl())
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        apiServices = retrofit.create(ApiServices.class);
    }


    public static RemoteDataSource getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }


    private String getBaseUrl() {

        return BASE_URL;
    }

    @NonNull
    private OkHttpClient configureOkHttpClient() {

        String userAgentStr = "";

        try {
            PackageInfo pInfo = AppSports.getAppSportsContext().getPackageManager().getPackageInfo(AppSports.getAppSportsContext().getPackageName(), 0);

        } catch (Exception e) {
        }

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();


        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder().header("Content-Type", "application/json");

                builder.cacheControl(CacheControl.FORCE_NETWORK);

                return chain.proceed(builder.build());
            }
        });

        //setting timeouts


        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(45, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);


        httpClientBuilder.addInterceptor(createLogInterceptor());

        return httpClientBuilder.build();
    }

    public HttpLoggingInterceptor createLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Override
    public void getSportsList(final GetSportsListCallback getSportsListCallback) {

            final Call<List<SportListResponse>> sportListResponseCall = apiServices.getSportsList();
            try {
                sportListResponseCall.enqueue(new Callback<List<SportListResponse>>() {


                    @Override
                    public void onResponse(Call<List<SportListResponse>> call, Response<List<SportListResponse>> response) {

                        if (response != null && response.isSuccessful() && response.body() != null) {
                            getSportsListCallback.onGetSportsListSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SportListResponse>> call, Throwable t) {

                        getSportsListCallback.onGetSportsListFailure(new SportListResponseError(t.getMessage()));
                    }
                });


            } catch (Exception e) {

                getSportsListCallback.onGetSportsListFailure(new SportListResponseError(e.getMessage()));
            }
        }

}
