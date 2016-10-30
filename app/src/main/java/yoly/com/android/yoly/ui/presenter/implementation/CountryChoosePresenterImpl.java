package yoly.com.android.yoly.ui.presenter.implementation;

import android.util.Log;
import android.widget.TextView;

import java.util.LinkedList;

import yoly.com.android.yoly.data.model.Country;
import yoly.com.android.yoly.ui.adapter.CountryListAdapter;
import yoly.com.android.yoly.ui.presenter.CountryChoosePresenter;
import yoly.com.android.yoly.ui.view.CountryChooseView;


public class CountryChoosePresenterImpl implements CountryChoosePresenter, CountryListAdapter.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    private CountryChooseView view;

    public CountryChoosePresenterImpl(CountryChooseView view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.setupRecyclerView();
        view.getAdapter().setOnClickListener(this);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void fillDataSet() {
        LinkedList<Country> dataSet = view.getDataSet();

        view.getAdapter().getDataSet().add(new Country("Армения", ""));
        dataSet.add(new Country("Румыния", ""));
        dataSet.add(new Country("Россия", ""));
        dataSet.add(new Country("Украина", ""));
        dataSet.add(new Country("Беларусь", ""));
        dataSet.add(new Country("США", ""));
        dataSet.add(new Country("США", ""));
        dataSet.add(new Country("США", ""));
        dataSet.add(new Country("США", ""));
        dataSet.add(new Country("США", ""));
        dataSet.add(new Country("США", ""));
        dataSet.add(new Country("США", ""));
        dataSet.add(new Country("США", ""));

        view.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onClick(TextView textView, int position) {
        deselectPreviousPosition();

        Country country = view.getDataSet().get(position);
        country.setSelected(true);

        view.setChosenCountry(country);
        view.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void deselectPreviousPosition() {
        if (view.getChosenCountry() != null)
            view.getChosenCountry().setSelected(false);
    }
}
