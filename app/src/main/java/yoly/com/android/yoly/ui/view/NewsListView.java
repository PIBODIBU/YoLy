package yoly.com.android.yoly.ui.view;

import java.util.ArrayList;

import yoly.com.android.yoly.data.model.NewsModel;
import yoly.com.android.yoly.ui.adapter.NewsListAdapter;

public interface NewsListView {
    void setupRecyclerView();

    ArrayList<NewsModel> getDataSet();

    NewsListAdapter getAdapter();
}
