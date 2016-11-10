package yoly.com.android.yoly.ui.presenter.implementation;

import android.content.Intent;

import yoly.com.android.yoly.data.model.NewsComment;
import yoly.com.android.yoly.data.model.NewsModel;
import yoly.com.android.yoly.helper.IntentKeys;
import yoly.com.android.yoly.ui.presenter.NewsPresenter;
import yoly.com.android.yoly.ui.view.NewsView;

public class NewsPresenterImpl implements NewsPresenter {
    private NewsView view;
    private NewsModel dataModel;

    public NewsPresenterImpl(NewsView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setupRecyclerView();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean isIntentValid(Intent intent) {
        if (intent == null)
            return false;
        if (!intent.getExtras().containsKey(IntentKeys.OBJECT_NEWS_MODEL))
            return false;
        if (intent.getExtras().getSerializable(IntentKeys.OBJECT_NEWS_MODEL) == null)
            return false;

        dataModel = ((NewsModel) intent.getExtras().getSerializable(IntentKeys.OBJECT_NEWS_MODEL));
        return true;
    }

    @Override
    public void fillDataSet() {
        view.getAdapter().getDataSet().add(new NewsComment());
        view.getAdapter().getDataSet().add(new NewsComment());
        view.getAdapter().getDataSet().add(new NewsComment());
        view.getAdapter().getDataSet().add(new NewsComment());
        view.getAdapter().getDataSet().add(new NewsComment());
        view.getAdapter().getDataSet().add(new NewsComment());

        view.getAdapter().notifyDataSetChanged();
    }
}
