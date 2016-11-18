package yoly.com.android.yoly.ui.activity;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.NewsComment;
import yoly.com.android.yoly.ui.adapter.NewsAdapter;
import yoly.com.android.yoly.ui.presenter.NewsPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.NewsPresenterImpl;
import yoly.com.android.yoly.ui.view.NewsView;

public class NewsActivity extends AppCompatActivity implements NewsView {
    @BindView(R.id.scroll_view)
    public NestedScrollView scrollView;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private NewsPresenter presenter;
    private LinearLayoutManager layoutManager;
    private NewsAdapter adapter;
    private ArrayList<NewsComment> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);

        presenter = new NewsPresenterImpl(this);

        if (!presenter.isIntentValid(getIntent()))
            return;

        presenter.start();
    }

    @Override
    public void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        dataSet = new ArrayList<>();
        adapter = new NewsAdapter(this, dataSet);

        presenter.fillDataSet();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public ArrayList<NewsComment> getDataSet() {
        return dataSet;
    }

    @Override
    public NewsAdapter getAdapter() {
        return adapter;
    }
}
