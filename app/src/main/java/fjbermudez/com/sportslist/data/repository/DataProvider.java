package fjbermudez.com.sportslist.data.repository;

public class DataProvider implements DataSource {

    private static DataProvider INSTANCE = null;
    private final DataSource remoteDataSource ;
    private final DataSource localDataSource ;

    public DataProvider(DataSource remoteDataSource, DataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    /**
     * Devuelve la instancia de la clase, creandola si es necesario.
     * @param remoteDataSource el servicio web.
     * @param localDataSource el almacenamiento local de datos.
     * @return la instancia actual
     */
    public static synchronized DataProvider getInstance(DataSource remoteDataSource, DataSource localDataSource){

        if (INSTANCE == null){
            INSTANCE = new DataProvider(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getSportsList(GetSportsListCallback getSportsListCallback) {
        remoteDataSource.getSportsList(getSportsListCallback);
    }
}
