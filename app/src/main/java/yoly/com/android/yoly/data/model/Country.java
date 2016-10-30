package yoly.com.android.yoly.data.model;

public class Country {
    private String title;
    private String code;
    private boolean isSelected;

    public Country(String title, String code, boolean isSelected) {
        this.title = title;
        this.code = code;
        this.isSelected = isSelected;
    }

    public Country(String title, String code) {
        this.title = title;
        this.code = code;
        this.isSelected = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
