package yoly.com.android.yoly.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import yoly.com.android.yoly.R;
import yoly.com.android.yoly.data.model.Country;
import yoly.com.android.yoly.ui.viewholder.CountryListViewHolder;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private OnClickListener onClickListener;
    private LinkedList<Country> dataSet;
    private Context context;

    public CountryListAdapter(LinkedList<Country> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public CountryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(final CountryListViewHolder holder, int position) {
        Country country = dataSet.get(position);

        if (country == null) {
            Log.e(TAG, "onBindViewHolder()-> data set item is null");
            return;
        }

        holder.TVCountry.setText(country.getTitle());
        holder.TVCountry.setTypeface(null, country.isSelected() ? Typeface.BOLD : Typeface.NORMAL);
        holder.TVCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(holder.TVCountry, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    public LinkedList<Country> getDataSet() {
        return dataSet;
    }

    public interface OnClickListener {
        void onClick(TextView textView, int position);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
