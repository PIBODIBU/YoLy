package yoly.com.android.yoly.data.model;

public class ProLookModel {
    private String cost;
    private String season;
    private String type;
    private String photoUrl;

    public ProLookModel(String cost, String season, String type, String photoUrl) {
        this.cost = cost;
        this.season = season;
        this.type = type;
        this.photoUrl = photoUrl;
    }

    public String getCost() {
        return cost;
    }

    public String getSeason() {
        return season;
    }

    public String getType() {
        return type;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
