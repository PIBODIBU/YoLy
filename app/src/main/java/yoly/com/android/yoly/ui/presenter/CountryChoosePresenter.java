package yoly.com.android.yoly.ui.presenter;

public interface CountryChoosePresenter {
    void start();

    void onDestroy();

    void fillDataSet();

    void deselectPreviousPosition();
}
