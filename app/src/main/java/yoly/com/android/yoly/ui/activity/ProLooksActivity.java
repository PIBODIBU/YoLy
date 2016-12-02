package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.ProLookModel;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;
import yoly.com.android.yoly.ui.adapter.ProLooksAdapter;

public class ProLooksActivity extends BaseNavigationDrawerActivity {
    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private ArrayList<ProLookModel> dataSet;
    private ProLooksAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_looks);
        getDrawer();
        ButterKnife.bind(this);

        dataSet = new ArrayList<>();

        dataSet.add(new ProLookModel(
                "99",
                "Весна",
                "Popular",
                "http://nauguide.esy.es/yoly/paid_collection_1.png"));
        dataSet.add(new ProLookModel(
                "159",
                "Весна",
                "Lux",
                "http://nauguide.esy.es/yoly/paid_collection_2.png"));
        dataSet.add(new ProLookModel(
                "259",
                "Весна",
                "Premium",
                "http://nauguide.esy.es/yoly/paid_collection_3.png"));
        dataSet.add(new ProLookModel(
                "99",
                "Весна",
                "Popular",
                "http://nauguide.esy.es/yoly/paid_collection_1.png"));
        dataSet.add(new ProLookModel(
                "159",
                "Весна",
                "Lux",
                "http://nauguide.esy.es/yoly/paid_collection_2.png"));
        dataSet.add(new ProLookModel(
                "259",
                "Весна",
                "Premium",
                "http://nauguide.esy.es/yoly/paid_collection_3.png"));

        layoutManager = new LinearLayoutManager(this);
        adapter = new ProLooksAdapter(dataSet, this);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProLooksActivity.this, ProLookInfoActivity.class));
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @OnClick(R.id.iv_arrow_back)
    public void back() {
        this.finish();
    }
}
