package fjbermudez.com.sportslist.data.responses;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SportListResponse implements Serializable{

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("players")
    @Expose
    private Players players;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Players getPlayers() {
        return players;
    }

    public void setPlayers(Players players) {
        this.players = players;
    }
}
