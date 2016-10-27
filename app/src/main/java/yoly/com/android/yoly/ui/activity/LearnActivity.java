package yoly.com.android.yoly.ui.activity;

import android.content.Context;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.ui.presenter.LearnPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.LearnPresenterImpl;
import yoly.com.android.yoly.ui.view.LearnView;

public class LearnActivity extends AppCompatActivity implements LearnView {
    private final String TAG = getClass().getSimpleName();

    private LearnPresenter presenter;

    @BindView(R.id.slider_layout)
    public SliderLayout sliderLayout;

    @BindView(R.id.page_indicator)
    public PagerIndicator pagerIndicator;

    @BindView(R.id.slide_description)
    public TextView TVSlideDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        ButterKnife.bind(this);
        presenter = new LearnPresenterImpl(this);

        presenter.onStart();
        presenter.fillSlider();
        presenter.stopSliderAutoCycle();
    }

    @Override
    protected void onStop() {
        presenter.stopSliderAutoCycle();
        presenter.onStop();
        super.onStop();
    }

    @Override
    public SliderLayout getSlider() {
        return sliderLayout;
    }

    @Override
    public PagerIndicator getIndicator() {
        return pagerIndicator;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setSlideDescription(String description) {
        if (presenter.validateText(description))
            TVSlideDescription.setText(description);
    }
}
