package yoly.com.android.yoly.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.LikedModel;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;
import yoly.com.android.yoly.ui.adapter.LikedAdapter;
import yoly.com.android.yoly.ui.helper.decorator.NewsListVerticalSpaceItemDecoration;
import yoly.com.android.yoly.ui.presenter.LikedPresenter;
import yoly.com.android.yoly.ui.presenter.implementation.LikedPresenterImpl;
import yoly.com.android.yoly.ui.view.LikedView;

public class LikedActivity extends BaseNavigationDrawerActivity implements LikedView {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private LikedPresenter presenter;
    private StaggeredGridLayoutManager layoutManager;
    private LikedAdapter adapter;
    private ArrayList<LikedModel> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked);

        ButterKnife.bind(this);
        getDrawer();

        presenter = new LikedPresenterImpl(this);

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
        adapter = new LikedAdapter(this, dataSet);

        presenter.fillDataSet();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new NewsListVerticalSpaceItemDecoration());
        adapter.setOnItemClickListener(new LikedAdapter.OnItemClickListener() {
            @Override
            public void onDelete(int position) {
                adapter.getDataSet().remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
    }

    @Override
    public ArrayList<LikedModel> getDataSet() {
        return dataSet;
    }

    @Override
    public LikedAdapter getAdapter() {
        return adapter;
    }

    @OnClick(R.id.iv_arrow_back)
    public void back() {
        this.finish();
    }
}
