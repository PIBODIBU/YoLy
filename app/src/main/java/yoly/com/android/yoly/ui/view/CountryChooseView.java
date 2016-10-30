package yoly.com.android.yoly.ui.view;

import java.util.LinkedList;

import yoly.com.android.yoly.data.model.Country;
import yoly.com.android.yoly.ui.adapter.CountryListAdapter;

public interface CountryChooseView {
    void setChosenCountry(Country country);

    void setChosenCountryTitle(String countryName);

    void setupRecyclerView();

    LinkedList<Country> getDataSet();

    Country getChosenCountry();

    CountryListAdapter getAdapter();
}