package fjbermudez.com.sportslist.data.responses;

public class SportListResponseError{

    private String error;
    public SportListResponseError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
