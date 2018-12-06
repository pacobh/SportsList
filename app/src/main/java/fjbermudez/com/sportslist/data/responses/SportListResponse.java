package fjbermudez.com.sportslist.data.responses;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SportListResponse implements Serializable{

//    private List<Sport> sportList;
//
//    public List<Sport> getSportList() {
//        return sportList;
//    }
//
//    public void setSportList(List<Sport> sportList) {
//        this.sportList = sportList;
//    }
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("players")
    @Expose
    private List <Players> playersList;

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

    public List<Players> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Players> playersList) {
        this.playersList = playersList;
    }
}
