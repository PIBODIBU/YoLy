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
                new NewsModel("Правнучка Эрнеста Хемингуэя в рекламной кампании Miu Miu",
                        "12.06.2016",
                        "http://nauguide.esy.es/yoly/news_7.png",
                        65));
        view.getAdapter().getDataSet().add(
                new NewsModel("Eres представил купальники для города",
                        "12.06.2016",
                        "http://nauguide.esy.es/yoly/news_3.png",
                        65));
        view.getAdapter().getDataSet().add(
                new NewsModel("Саша Мельничук в специальной съемке Vetements",
                        "20.06.2016",
                        "http://nauguide.esy.es/yoly/news_2.png",
                        60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Adidas анонинсировал кроссовки, созданные из океанического мусора",
                        "20.06.2016",
                        "http://nauguide.esy.es/yoly/news_6.png",
                        60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Марион Котийяр",
                        "20.06.2016",
                        "http://nauguide.esy.es/yoly/news_1.png",
                        60));
        view.getAdapter().getDataSet().add(
                new NewsModel("Как носить одежду российских дизайнеров: 7КА",
                        "20.06.2016",
                        "http://nauguide.esy.es/yoly/news_5.png",
                        60));
        view.getAdapter().getDataSet().add(
                new NewsModel("",
                        "20.06.2016",
                        "http://nauguide.esy.es/yoly/news_4.png",
                        60));

        view.getAdapter().notifyDataSetChanged();
    }
}