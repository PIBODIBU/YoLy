package yoly.com.android.yoly.ui.view;

import java.util.ArrayList;

import yoly.com.android.yoly.data.model.NewsComment;
import yoly.com.android.yoly.ui.adapter.NewsAdapter;

public interface NewsView {
    void setupRecyclerView();

    ArrayList<NewsComment> getDataSet();

    NewsAdapter getAdapter();
}
