package yoly.com.android.yoly.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.Brand;
import yoly.com.android.yoly.ui.activity.base.BaseActivity;
import yoly.com.android.yoly.ui.adapter.BrandListAdapter;

public class BrandChooseActivity extends BaseActivity implements BrandListAdapter.OnBrandSelectListener {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private BrandListAdapter adapter;
    private LinearLayoutManager layoutManager;
    private LinkedList<Brand> brands;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_choose);
        ButterKnife.bind(this);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        brands = new LinkedList<>();

        brands.add(new Brand("Adidas", ""));
        brands.add(new Brand("Reebok", ""));
        brands.add(new Brand("Zara", ""));
        brands.add(new Brand("Bershka", ""));
        brands.add(new Brand("Dolce and Gabana", ""));
        brands.add(new Brand("Gucci", ""));
        brands.add(new Brand("Test 1", ""));
        brands.add(new Brand("Test 2", ""));
        brands.add(new Brand("Test 3", ""));
        brands.add(new Brand("Test 4", ""));
        brands.add(new Brand("Test 5", ""));
        brands.add(new Brand("Test 6", ""));

        adapter = new BrandListAdapter(brands, this);
        adapter.notifyDataSetChanged();
        adapter.setOnBrandSelectListener(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBrandSelect(Brand brand, int position) {

    }

    @Override
    public void onBrandDeselect(Brand brand, int position) {

    }

    @OnClick(R.id.btn_next)
    public void done() {

    }
}
