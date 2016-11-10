package yoly.com.android.yoly.ui.presenter;

import android.content.Intent;

public interface NewsPresenter {
    void start();

    void onDestroy();

    boolean isIntentValid(Intent intent);

    void fillDataSet();
}
