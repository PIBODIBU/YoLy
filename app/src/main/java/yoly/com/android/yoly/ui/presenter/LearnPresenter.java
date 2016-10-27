package yoly.com.android.yoly.ui.presenter;

import android.support.annotation.DrawableRes;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

public interface LearnPresenter {
    void onStart();

    void onStop();

    void onDestroy();

    void fillSlider();

    <T extends BaseSliderView> void addSlide(T slide, @DrawableRes int imageId);

    void stopSliderAutoCycle();

    boolean validateText(String text);
}
