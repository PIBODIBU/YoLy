package yoly.com.android.yoly.data.model;

public class NewsModel {
    private String title;
    private String date;
    private String photoUrl;
    private int commentsNum;

    public NewsModel(String title, String date, String photoUrl, int commentsNum) {
        this.title = title;
        this.date = date;
        this.photoUrl = photoUrl;
        this.commentsNum = commentsNum;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getCommentsNum() {
        return commentsNum;
    }
}
