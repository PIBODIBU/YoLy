package yoly.com.android.yoly.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.Country;
import yoly.com.android.yoly.data.model.NewsModel;
import yoly.com.android.yoly.ui.adapter.CountryListAdapter;
import yoly.com.android.yoly.ui.adapter.NewsListAdapter;
import yoly.com.android.yoly.ui.presenter.NewsListPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.CountryChoosePresenterImpl;
import yoly.com.android.yoly.ui.presenter.implementation.NewsListPresenterImpl;
import yoly.com.android.yoly.ui.view.NewsListView;

public class NewsActivity extends BaseNavigationDrawerActivity implements NewsListView {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private NewsListPresenter presenter;
    private StaggeredGridLayoutManager layoutManager;
    private NewsListAdapter adapter;
    private ArrayList<NewsModel> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ButterKnife.bind(this);
        getDrawer();

        presenter = new NewsListPresenterImpl(this);

        presenter.start();
    }


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setupRecyclerView() {
        layoutManager = new StaggeredGridLayoutManager(2, 1);
        dataSet = new ArrayList<>();
        adapter = new NewsListAdapter(this, dataSet);

        presenter.fillDataSet();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public ArrayList<NewsModel> getDataSet() {
        return dataSet;
    }

    @Override
    public NewsListAdapter getAdapter() {
        return adapter;
    }
}