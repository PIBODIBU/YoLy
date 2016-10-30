package yoly.com.android.yoly.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.Country;
import yoly.com.android.yoly.ui.adapter.CountryListAdapter;
import yoly.com.android.yoly.ui.presenter.CountryChoosePresenter;
import yoly.com.android.yoly.ui.presenter.implementation.CountryChoosePresenterImpl;
import yoly.com.android.yoly.ui.view.CountryChooseView;

public class CountryChooseActivity extends AppCompatActivity implements CountryChooseView {
    private final String TAG = getClass().getSimpleName();

    private CountryChoosePresenter presenter;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    @BindView(R.id.tv_country_name)
    public TextView TVChosenCountry;

    private LinkedList<Country> dataSet;
    private RecyclerView.LayoutManager layoutManager;
    private CountryListAdapter adapter;
    private Country chosenCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_choose);

        ButterKnife.bind(this);
        presenter = new CountryChoosePresenterImpl(this);

        presenter.start();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setupRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        dataSet = new LinkedList<>();
        adapter = new CountryListAdapter(dataSet, this);

        presenter.fillDataSet();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setChosenCountry(Country country) {
        chosenCountry = country;
        setChosenCountryTitle(country.getTitle());
    }

    @Override
    public Country getChosenCountry() {
        return chosenCountry;
    }

    @Override
    public CountryListAdapter getAdapter() {
        return adapter;
    }

    @Override
    public void setChosenCountryTitle(String countryName) {
        TVChosenCountry.setText(countryName);
    }

    @Override
    public LinkedList<Country> getDataSet() {
        return dataSet;
    }
}