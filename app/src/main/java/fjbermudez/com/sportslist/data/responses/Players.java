package fjbermudez.com.sportslist.data.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Players {

    @SerializedName("image")
    @Expose
    private String urlImage;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
