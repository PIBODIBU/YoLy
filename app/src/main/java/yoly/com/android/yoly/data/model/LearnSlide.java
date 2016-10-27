package yoly.com.android.yoly.data.model;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

public class LearnSlide<T extends BaseSliderView> {
    private T slide;
    private int imageId;
    private String description;

    public LearnSlide(T slide, int imageId, String description) {
        this.slide = slide;
        this.imageId = imageId;
        this.description = description;

        getSlide().image(getImageId());
    }

    public T getSlide() {
        return slide;
    }

    public void setSlide(T slide) {
        this.slide = slide;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
