package yoly.com.android.yoly.ui.presenter.implementation;

import android.text.TextUtils;
import android.util.Log;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.LinkedList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.LearnSlide;
import yoly.com.android.yoly.ui.presenter.LearnPresenter;
import yoly.com.android.yoly.ui.view.LearnView;

public class LearnPresenterImpl implements LearnPresenter, ViewPagerEx.OnPageChangeListener {
    private final String TAG = getClass().getSimpleName();

    private LinkedList<LearnSlide> slides;
    private LearnView view;

    public LearnPresenterImpl(LearnView view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.getSlider().addOnPageChangeListener(this);
        view.getSlider().setCustomIndicator(view.getIndicator());

        slides = new LinkedList<>();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void stopSliderAutoCycle() {
        view.getSlider().stopAutoCycle();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void fillSlider() {
        slides.add(new LearnSlide<>(
                new DefaultSliderView(view.getContext()),
                R.drawable.splash_clothes,
                "Slide 1"
        ));
        slides.add(new LearnSlide<>(
                new DefaultSliderView(view.getContext()),
                R.drawable.splash_clothes,
                "Slide 2"
        ));
        slides.add(new LearnSlide<>(
                new DefaultSliderView(view.getContext()),
                R.drawable.splash_clothes,
                "Slide 3"
        ));
        slides.add(new LearnSlide<>(
                new DefaultSliderView(view.getContext()),
                R.drawable.splash_clothes,
                "Slide 4"
        ));

        for (LearnSlide slide : slides) {
            view.getSlider().addSlider(slide.getSlide());
        }
    }

    @Override
    public <T extends BaseSliderView> void addSlide(T slide, int imageId) {
        slide.image(imageId);
        view.getSlider().addSlider(slide);
    }

    @Override
    public boolean validateText(String text) {
        if (text == null) {
            Log.e(TAG, "setSlideDescription()-> text is null");
            return false;
        }

        if (TextUtils.isEmpty(text)) {
            Log.e(TAG, "setSlideDescription()-> text is empty");
            return false;
        }

        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        LearnSlide slide = slides.get(position);
        if (slide == null) {
            Log.e(TAG, "onPageSelected()-> slide is null");
            return;
        }

        view.setSlideDescription(slide.getDescription());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}