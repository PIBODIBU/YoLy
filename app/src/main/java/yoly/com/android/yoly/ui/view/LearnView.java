package yoly.com.android.yoly.ui.view;

import android.content.Context;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

public interface LearnView {
    SliderLayout getSlider();

    PagerIndicator getIndicator();

    Context getContext();

    void setSlideDescription(String description);
}
