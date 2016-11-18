package yoly.com.android.yoly.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.AccountLookModel;
import yoly.com.android.yoly.ui.activity.base.BaseNavigationDrawerActivity;
import yoly.com.android.yoly.ui.adapter.AccountLookAdapter;

public class AccountActivity extends BaseNavigationDrawerActivity {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private AccountLookAdapter adapter;
    private GridLayoutManager layoutManager;
    private ArrayList<AccountLookModel> dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        getDrawer();

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        dataSet = new ArrayList<>();

        dataSet.add(new AccountLookModel("http://nauguide.esy.es/yoly/model.jpg"));
        dataSet.add(new AccountLookModel("http://nauguide.esy.es/yoly/model.jpg"));
        dataSet.add(new AccountLookModel("http://nauguide.esy.es/yoly/model.jpg"));
        dataSet.add(new AccountLookModel("http://nauguide.esy.es/yoly/model.jpg"));

        adapter = new AccountLookAdapter(dataSet, this);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @OnClick(R.id.btn_buy_pro)
    public void buyPro() {
        startActivity(new Intent(AccountActivity.this, ProLooksActivity.class));
    }
}
