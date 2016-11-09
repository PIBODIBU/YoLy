package yoly.com.android.yoly.ui.presenter.implementation;

import yoly.com.android.yoly.data.model.NewsModel;
import yoly.com.android.yoly.ui.presenter.NewsListPresenter;
import yoly.com.android.yoly.ui.view.NewsListView;

public class NewsListPresenterImpl implements NewsListPresenter {
    private NewsListView view;

    public NewsListPresenterImpl(NewsListView view) {
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
    public void fillDataSet() {
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "https://img.buzzfeed.com/buzzfeed-static/static/2015-04/3/16/enhanced/webdr15/grid-cell-7569-1428094081-16.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "http://www.hausmodels.com.au/wp-content/uploads/2016/02/Haxhija-Model-Square.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "http://asset1.modelmanagement.com/images/home/everyone_search.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "https://img.buzzfeed.com/buzzfeed-static/static/2015-04/3/16/enhanced/webdr15/grid-cell-7569-1428094081-16.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "http://www.hausmodels.com.au/wp-content/uploads/2016/02/Haxhija-Model-Square.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "http://asset1.modelmanagement.com/images/home/everyone_search.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "https://img.buzzfeed.com/buzzfeed-static/static/2015-04/3/16/enhanced/webdr15/grid-cell-7569-1428094081-16.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "http://www.hausmodels.com.au/wp-content/uploads/2016/02/Haxhija-Model-Square.jpg", 60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Long long long title", "20.06.2016", "http://asset1.modelmanagement.com/images/home/everyone_search.jpg", 60));

        view.getAdapter().notifyDataSetChanged();
    }
}