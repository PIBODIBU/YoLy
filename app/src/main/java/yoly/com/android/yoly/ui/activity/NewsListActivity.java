package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.NewsModel;
import yoly.com.android.yoly.helper.IntentKeys;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;
import yoly.com.android.yoly.ui.adapter.NewsListAdapter;
import yoly.com.android.yoly.ui.helper.decorator.NewsListVerticalSpaceItemDecoration;
import yoly.com.android.yoly.ui.presenter.NewsListPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.NewsListPresenterImpl;
import yoly.com.android.yoly.ui.view.NewsListView;

public class NewsListActivity extends BaseNavigationDrawerActivity implements NewsListView {
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
        setContentView(R.layout.activity_news_list);

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
        recyclerView.addItemDecoration(new NewsListVerticalSpaceItemDecoration());
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new NewsListAdapter.OnClickListener() {
            @Override
            public void onItemClick(NewsModel model) {
                startActivity(new Intent(NewsListActivity.this, NewsActivity.class)
                        .putExtra(IntentKeys.OBJECT_NEWS_MODEL, model));
            }
        });
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