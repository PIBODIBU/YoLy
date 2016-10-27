package yoly.com.android.yoly.ui.presenter.implementation;

import yoly.com.android.yoly.ui.processor.implementation.BeforeLoginProcessorImpl;
import yoly.com.android.yoly.ui.presenter.BeforeLoginPresenter;
import yoly.com.android.yoly.ui.view.BeforeLoginView;

public class BeforeLoginPresenterImpl implements BeforeLoginPresenter{
    private BeforeLoginView beforeLoginView;
    private BeforeLoginProcessorImpl beforeLoginProcessorImpl;

    public BeforeLoginPresenterImpl(BeforeLoginView beforeLoginView) {
        this.beforeLoginView = beforeLoginView;
    }

    @Override
    public void onDestroy() {

    }
}